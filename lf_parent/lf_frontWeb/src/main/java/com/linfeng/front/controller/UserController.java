package com.linfeng.front.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.lifeng.common.controller.BaseController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.linfeng.front.model.UserIdeaModel;
import com.linfeng.front.model.UserInfoModel;
import com.linfeng.front.service.UserIdeaService;
import com.linfeng.front.service.UserInfoService;
import com.linfeng.front.vo.UserMenuVO;

@Controller
public class UserController extends BaseController{

	@Resource private UserInfoService userInfoService;
	@Resource private UserIdeaService userIdeaService;
	
	@RequestMapping(value="/user/index",method=RequestMethod.GET)
	public ModelAndView toUserCenter(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/userIndex");
		UserInfoModel user = (UserInfoModel)session.getAttribute(USER_LOGIN);
		mav.addObject("user", user);
		mav.addObject("activeUserMenu", UserMenuVO.userInfo);
		return mav;
	}
	
	@RequestMapping(value="/user/info",method=RequestMethod.GET)
	public ModelAndView toUserInfo(HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/userInfo");
		UserInfoModel user = (UserInfoModel)session.getAttribute(USER_LOGIN);
		mav.addObject("user", user);
		mav.addObject("activeUserMenu", UserMenuVO.userInfo);
		return mav;
	}
	
	@RequestMapping(value="/user/updateUserInfo",method=RequestMethod.POST)
	public String updateUserInfo(UserInfoModel user, HttpSession session){
		UserInfoModel db = (UserInfoModel)session.getAttribute(BaseController.USER_LOGIN);
		db.setTrueName(user.getTrueName());
		db.setQq(user.getQq());
		db.setPhone(user.getPhone());
		db.setBirthday(user.getBirthday());
		db.setIcon(user.getIcon());
		userInfoService.saveOrUpdate(db);
		return "redirect:/user/info";
	}
	
	@RequestMapping(value="/user/uploadIcon",method=RequestMethod.POST)
	public ModelAndView updateUserIcon(UserInfoModel user, HttpSession session){
		ModelAndView mav = new ModelAndView();
		UserInfoModel db = (UserInfoModel)session.getAttribute(BaseController.USER_LOGIN);
		db.setIcon(user.getIcon());
		userInfoService.saveOrUpdate(db);
		mav.addObject("user", db);
		mav.setViewName("user/success");
		return mav;
	}
	
	@RequestMapping(value="/user/myWork",method=RequestMethod.GET)
	public ModelAndView toMyIdea(HttpSession session){
		ModelAndView mav = new ModelAndView();
		UserInfoModel user = (UserInfoModel)session.getAttribute(USER_LOGIN);
		List<UserIdeaModel> list = userIdeaService.getMyIdeaList(user.getId());
		mav.addObject("ideaList", list);
		mav.setViewName("user/mywork");
		mav.addObject("activeUserMenu", UserMenuVO.myWork);
		return mav;
	}
	@RequestMapping(value="/user/uploadWork",method=RequestMethod.GET)
	public ModelAndView toUploadWork(HttpSession session, UserIdeaModel idea){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/uploadWork");
		mav.addObject("activeUserMenu", UserMenuVO.uploadWork);
		return mav;
	}
	
	@RequestMapping(value="/user/edit/{id}",method=RequestMethod.GET)
	public ModelAndView toEditWork(@PathVariable Long id, HttpSession session, UserIdeaModel idea){
		ModelAndView mav = new ModelAndView();
		mav.addObject("idea", userIdeaService.get(id));
		mav.setViewName("user/uploadWork");
		mav.addObject("activeUserMenu", UserMenuVO.uploadWork);
		return mav;
	}
	
	@RequestMapping(value="/user/uploadWork",method=RequestMethod.POST)
	public String uploadWork(HttpSession session, UserIdeaModel idea){
		UserInfoModel user = (UserInfoModel)session.getAttribute(USER_LOGIN);
		idea.setUser(user);
		idea.setGreat( (10L + new Random().nextInt(100)));
		userIdeaService.saveOrUpdate(idea);
		return "redirect:/user/myWork";
	}
	
	@RequestMapping(value="/user/deleteWork",method=RequestMethod.POST)
	public String deleteWork(Long id, HttpSession session, UserIdeaModel idea){
		UserInfoModel user = (UserInfoModel)session.getAttribute(USER_LOGIN);
		if(null == id){
			return "error";
		}
		UserIdeaModel work = userIdeaService.get(id);
		if(null == work || work.getUser().getId().longValue() != user.getId().longValue()){
			return "error";
		}
		userInfoService.delete(id);
		return "redirect:/user/myWork";
	}
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
