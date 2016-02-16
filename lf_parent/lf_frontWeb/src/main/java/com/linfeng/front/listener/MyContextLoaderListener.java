package com.linfeng.front.listener;

import java.util.Arrays;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.linfeng.front.vo.MenuVO;
import com.linfeng.front.vo.UserMenuVO;

public class MyContextLoaderListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		super.contextInitialized(event);

		event.getServletContext().setAttribute("SystemMenu", Arrays.asList(MenuVO.values()));
		event.getServletContext().setAttribute("UserMenu", Arrays.asList(UserMenuVO.values()));
	}

}
