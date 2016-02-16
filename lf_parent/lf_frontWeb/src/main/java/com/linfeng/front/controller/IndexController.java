package com.linfeng.front.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.lifeng.common.controller.BaseController;
import org.lifeng.common.entity.Pagination;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.linfeng.front.model.UserIdeaModel;
import com.linfeng.front.model.UserInfoModel;
import com.linfeng.front.model.UserVoteModel;
import com.linfeng.front.service.UserIdeaService;
import com.linfeng.front.service.UserInfoService;
import com.linfeng.front.service.UserVoteService;
import com.linfeng.front.vo.MenuVO;

@Controller
public class IndexController extends BaseController{

	@Resource private UserInfoService userInfoService;
	@Resource private UserIdeaService userIdeaService;
	@Resource private UserVoteService userVoteService;
	private Pagination<UserIdeaModel> pagination = new Pagination<UserIdeaModel>();
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView toIdea(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		pagination.setPageSize(100);
		userIdeaService.listByPage(pagination);
		
		UserInfoModel db = (UserInfoModel)request.getSession().getAttribute(BaseController.USER_LOGIN);
		List<UserIdeaModel> ideas = pagination.getItems();
		
		if(!CollectionUtils.isEmpty(ideas)){
			for(UserIdeaModel idea : ideas){
				if(db != null && userVoteService.hasVoteToday(db.getId(), idea.getId())){
					idea.setPraiased(1);
				}
			}
		}
		
		mav.addObject("page", pagination);
		mav.setViewName("index");
		
		chooseMenu(mav, MenuVO.index);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/index/vote/{id}",method=RequestMethod.GET)
	public Object vote(@PathVariable("id") Long id, HttpSession session, HttpServletRequest request){
		Map<String, String> result = new HashMap<String, String>();
		UserInfoModel db = (UserInfoModel)session.getAttribute(BaseController.USER_LOGIN);
		if(null == db){
			result.put("status", "1");
			result.put("msg", "没有登陆！");
			return result;
		}
		UserIdeaModel idea = userIdeaService.get(id);
		if(null == id || id.longValue() < 0 || idea == null){
			result.put("status", "2");
			result.put("msg", "请重试！");
			return result;
		}
		
		if(userVoteService.hasVoteToday(db.getId(), id)){
			result.put("status", "3");
			result.put("msg", "今天您你投票，明天来吧！");
			return result;
		}
		idea.setGreat(idea.getGreat() + 1);
		userIdeaService.saveOrUpdate(idea);
		
		UserVoteModel vote = new UserVoteModel();
		vote.setCreateTime(new Date());
		vote.setIP(getIpAddr(request));
		vote.setUser(db);
		vote.setUserIdeaId(id);
		userVoteService.save(vote);
		
		result.put("status", "0");
		result.put("msg", "投票成功！");
		return result;
	}
	
	private String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}  

	private void chooseMenu(ModelAndView mav, MenuVO menu) {
		mav.addObject("activeMenu", menu);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView toLogin(){
		ModelAndView mav = new ModelAndView();
		chooseMenu(mav, null);
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session){
		session.removeAttribute(USER_LOGIN);
		ModelAndView mav = new ModelAndView();
		chooseMenu(mav, null);
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(UserInfoModel user,String code, HttpSession session, 
			HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		if(null == user || StringUtils.isEmpty(user.getUserName())){
			view.addObject("error", "请填写用户名！");
			view.setViewName("login");
			chooseMenu(view, null);
			return view;
		}
		if(null == user || StringUtils.isEmpty(code) 
				|| StringUtils.isEmpty((String)session.getAttribute("code"))
				|| !code.toUpperCase().trim().equals((String)session.getAttribute("code"))){
			view.addObject("error", "验证码不正确！");
			view.setViewName("login");
			chooseMenu(view, null);
			return view;
		}
		Map<String, String> result = userInfoService.login(user);
		if(result.get(RETURN_CODE).equals(BaseController.SUCCESS)){//登陆成功
			UserInfoModel userInfo = userInfoService.getUserInfoByUserName(user.getUserName().trim());
			session.setAttribute(USER_LOGIN, userInfo);
			chooseMenu(view, MenuVO.index);
			view.setViewName("redirect:/index");
			return view;
		}else{
			view.addObject("error", "账号或密码不正确！");
			view.setViewName("login");
			chooseMenu(view, null);
			return view;
		}
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView toRegister(){
		ModelAndView mav = new ModelAndView();
		chooseMenu(mav, null);
		mav.setViewName("register");
		return mav;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(UserInfoModel user,String code, HttpSession session){
		ModelAndView view = new ModelAndView();
		if(null == user || StringUtils.isEmpty(code) 
				|| StringUtils.isEmpty((String)session.getAttribute("code"))
				|| !code.toUpperCase().trim().equals((String)session.getAttribute("code"))){
			view.addObject("error", "验证码不正确！");
			chooseMenu(view, null);
			view.setViewName("register");
			return view;
		}
		if(null == user || !user.getUserPwd().equals(user.getConfirmPassword())){
			view.addObject("error", "两次输入密码不一致！");
			chooseMenu(view, null);
			view.setViewName("register");
			return view;
		}
		if(null == user || userInfoService.checkIfExistUser(user)){
			view.addObject("error", "此用户名已存在！");
			chooseMenu(view, null);
			view.setViewName("register");
			return view;
		}
		user.setCreateTime(new Date());
		userInfoService.saveOrUpdate(user);
		
		session.setAttribute(USER_LOGIN, user);
		chooseMenu(view, MenuVO.index);
		view.setViewName("index");
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(HttpServletRequest request, HttpServletResponse response, HttpSession session, String icon) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile multipartFile = null;
		InputStream in = null;
		File file = null;
		for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
			multipartFile = set.getValue();// 文件名
			try {
				in = multipartFile.getInputStream();
				String fileName = multipartFile.getOriginalFilename();
				String ext = fileName.substring(fileName.lastIndexOf("."));
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				String rootPath = request.getServletContext().getRealPath("");
				String newFileName = UUID.randomUUID().toString().replace("-", "") + ext;
				String filePath = File.separator + "idea" + File.separator + year + File.separator + month;
				file = new File(rootPath + filePath);
				if(!file.exists()){
					file.mkdirs();
				}
				file = new File(file, newFileName);
				if(!file.exists()){
					file.createNewFile();
				}
				FileUtils.copyInputStreamToFile(in, file);
				String picPath = (filePath+"/"+newFileName).replace("\\", "/");
				return picPath;// 返回给前台的提示信息
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(multipartFile.getOriginalFilename());
		}
		return null;
	}
	
	@RequestMapping(value="/page/list", method=RequestMethod.POST)
	public ModelAndView page(Pagination<Map<String, Object>> page,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, String icon) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		ModelAndView view = new ModelAndView();
		for(int i=0;i<20;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", i);
			map.put("name", "name" + i);
			map.put("time", new Date());
			list.add(map);
		}
		if(page == null){
			page = new Pagination<Map<String,Object>>();
			page.setCurrentPage(1);
		}
		if(page.getCurrentPage() == 0){
			page.setCurrentPage(1);
		}
		page.setTotalCount(20);
		page.setItems(list);
		view.addObject("page", page);
		view.setViewName("pagination");
		return view;
	}

	public Pagination<UserIdeaModel> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<UserIdeaModel> pagination) {
		this.pagination = pagination;
	}
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
