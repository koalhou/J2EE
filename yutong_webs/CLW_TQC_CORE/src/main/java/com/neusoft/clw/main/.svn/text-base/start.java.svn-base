package com.neusoft.clw.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.log.LogFormatter;


@SuppressWarnings("serial")
public class start extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(HttpServlet.class);
	/**
	 * Constructor of the object.
	 */
	public start() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		 try {
	            Startup startup = new Startup();
	            startup.init();
	            startup.start();
	            
	            log.info("核心服务子系统已成功启动！");
//	            while(true){
////	            	log.info("短信准备发送");
//	            	SendMessageReq req = sendmessage();	
//		            List<SendMessageReq> list = new ArrayList<SendMessageReq>();
//		            if(req!=null){
//		            	list.add(req);	
//		            }else{
////		            	System.out.println("本次处理结束1");
//		            	continue;
//		            }
//		            if(list == null||list.size()==0){
////		            	System.out.println("本次处理结束2");
//		            	continue;
//		            }
////		            if(list!=null&&list.size()<20){
////		            	continue;
////		            }
//		           
//		            SendBuffer.getInstance().add(list);
////		            log.info("放入队列SendBuffer，等待发送");
//		            Thread.sleep(1000);
////		            System.out.println("本次处理结束");
//	            }
	            
	        } catch (Throwable t) {
	            log.error(LogFormatter.formatMsg("servlet", "start failed."), t);
	        }
	}
	
	public static void main(String[] args){
		start start = new start();
		try {
			start.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
