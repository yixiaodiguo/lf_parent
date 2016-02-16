package com.linfeng.front.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.lifeng.common.entity.Pagination;

@WebServlet("/page/list")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(int i=0;i<20;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", i);
			map.put("name", "name" + i);
			map.put("time", new Date());
			list.add(map);
		}
		Pagination<Map<String, Object>> page = new Pagination<Map<String, Object>>();
		String currPageStr = request.getParameter("page.currentPage");
		if(StringUtils.isEmpty(currPageStr) || !NumberUtils.isNumber(currPageStr)){
			page.setCurrentPage(1);
		}else{
			page.setCurrentPage(Integer.valueOf(currPageStr));
		}
		page.setTotalCount(20);
		page.setItems(list);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pagination.jsp").forward(request, response);
	}

}
