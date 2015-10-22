package com.neusoft.clw.android.action;



import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.clw.common.util.BaseServlet;

public class DownloadAPK extends BaseServlet {
	private static final long serialVersionUID = -7825355637448948879L;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext sc = config.getServletContext();
	}
	//get方式获取请求
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		request.getRequestDispatcher("/WEB-INF/jsp/android/down.jsp").forward(request,response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		doGet(request,response);
	}
}
