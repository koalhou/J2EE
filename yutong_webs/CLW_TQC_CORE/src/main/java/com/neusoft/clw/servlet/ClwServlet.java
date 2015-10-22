package com.neusoft.clw.servlet;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.info.bean.RequestBean;
import com.neusoft.clw.info.bean.ResponseBean;
import com.neusoft.clw.info.business.GetSendInfo;
import com.neusoft.clw.info.business.SetRequestBean;
import com.neusoft.clw.info.business.SetResponseBean;
import com.neusoft.clw.info.exception.BusyAnalyzeException;
import com.neusoft.clw.info.exception.DBErrorException;
import com.neusoft.clw.info.exception.InvalidXmlFormatException;
import com.neusoft.clw.info.exception.SendInfoException;
import com.neusoft.clw.info.global.Globals;
import com.neusoft.clw.info.util.tool.StringUtil;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.vncs.cachemanager.AppConfigCacheManager;
import com.neusoft.clw.vncs.domain.AppConfigBean;


public class ClwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NAME = "ClwServlet";
	private static final Logger LOGGER = Logger.getLogger(ClwServlet.class); // 日志			

	public static boolean isAnalyzing = false;    
	public static long nRequestCounter = 0;       

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		
		 
//		// 每隔10秒更新数据库中服务器活跃状态,表名服务器工作状态正常(心跳)
//		Timer timerActive = new Timer();
//		ActiveMarkingTask activeTask =  ActiveMarkingTask.getInstance();
//		timerActive.schedule(activeTask, 0, 10*1000);  // 周期为 10s = 10*1000ms
//		
		//LOGGER.info(TimeHelpers.dateToString(Calendar.getInstance().getTime()));
		//LOGGER.info(Calendar.getInstance().getTimeZone());
		
		LOGGER.info(LogFormatter.formatMsg("核心接入接口", "启动", "完成"));
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>message</TITLE></HEAD>");
		out.println("  <BODY> <center><h1>");
		out.println("只能使用post方法<br>" +
				"The GET method is not supported by VSPE service!<br>");
		out.println("Only POST method can receive response message in format of XML.");
		out.println(" </h1></center> </BODY>");
		out.println("</HTML>");
		
		out.flush();
		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
	    
        StringBuffer strXmlRequest = new StringBuffer();
        String         line   = null;
        BufferedReader reader = null;
        PrintWriter    writer = null;        
        OlxDocument    xmlDoc = null;
        
        RequestBean requestBean = new RequestBean();
        ResponseBean responseBean = new ResponseBean();
        
        //LOGGER.info(LogFormatter.formatMsg(NAME, "核心应用", "客户端信息 IP:port = " + request.getRemoteHost()+":"+request.getRemotePort()));
        
        String strClientInfo = request.getRemoteHost()+":"+request.getRemotePort();
        try {  
                     
            // 2. 请求头字段
            String xServiceType     = request.getHeader("x-service-type");
            String xProtocolVersion = request.getHeader("x-protocol-version");
            if (null==xServiceType  
            		|| null==xProtocolVersion 
                    || !xProtocolVersion.equals("0.0.1")        // 使用了不支持的协议版本 
                    || !xServiceType.equals("clw:01/1.0") ) {  // 或 请求了不支持的服务类型
                // 留作扩展                
                
            }
            
            // 3. 从request中读取请求xml字符串
            reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            while ((line = reader.readLine()) != null) {
                strXmlRequest.append(line + "\r\n");
            }
            LOGGER.info("请求消息为xml:"+strXmlRequest);
            // 4. xml转换为OlxDocument
            try {
                //xmlDoc =Olx.Factory.(StringUtils.trimToNull(strXmlRequest.toString()));
                xmlDoc = OlxDocument.Factory.parse(StringUtils.trimToNull(strXmlRequest.toString()));
            } catch (Exception e) {
                LOGGER.info(LogFormatter.formatMsg(NAME, "核心应用", strClientInfo+"发来格式错误的请求消息"));
                throw new InvalidXmlFormatException("Bad format of XML: " + e.getMessage());
            }
//            LOGGER.info(LogFormatter.formatMsg(NAME, "核心应用", "请求消息xml: "+xmlDoc.xmlText()) );
            
            // 5. olx文档解析为requestBean
            //    其间,对参数的类型和值的范围进行了详细校验
            SetRequestBean.setBean(requestBean, xmlDoc);
 
            LOGGER.debug(LogFormatter.formatMsg(NAME, "核心应用" ,
            		String.format("%s请求调用function=%s\tseq=%s", strClientInfo, xmlDoc.getOlx().getFunction().getName(), xmlDoc.getOlx().getFunction().getSeq() ) ));
            
            //鉴权
           if( Authentication(requestBean)){
            
            // 6. 从数据库中取数据生成响应responseBean
            SetResponseBean.setBean(responseBean, requestBean);
            
            // 7. responseBean封装为xml
            String strXmlResponse = GetSendInfo.getInfoNormally(responseBean);
            strXmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+strXmlResponse;
            
            // 8. 返回响应xml
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/xml;charset=UTF-8");
            response.setHeader("x-status-code", Globals.RES_CODE_200_STR);
            response.setHeader("x-status-desc", it.sauronsoftware.base64.Base64.encode("请求成功", "UTF-8"));
            writer = response.getWriter();
            writer.write(strXmlResponse+"\n");
            writer.flush();
            
            LOGGER.info(LogFormatter.formatMsg(NAME, "核心应用", "响应消息xml: "+strXmlResponse) );
            
            // debug日志--请求处理成功完成
            LOGGER.info(LogFormatter.formatMsg(NAME, "核心应用", String.format("请求处理完成,IP:port = %s \t function=%s \t seq=%s"
                    , request.getRemoteHost()+":"+request.getRemotePort()
                    , xmlDoc.getOlx().getFunction().getName()
                    , xmlDoc.getOlx().getFunction().getSeq() ) ) );
           }else{//鉴权失败
        	   response.setCharacterEncoding("UTF-8");
               response.setContentType("text/xml;charset=UTF-8");
         
               String strErrorDesc =  Globals.RES_ERROR_CODE_604_DESC ;
               response.setHeader("x-status-code", Globals.RES_ERROR_CODE_604_STR); // 头字段中的状态码
               response.setHeader("x-status-desc", Base64.encode(strErrorDesc, "UTF-8"));                   // 头字段中的状态描述
               SetResponseBean.setBeanWhenExceptionOccurs(responseBean, requestBean);
              // String strXmlResponse = GetSendInfo.getInfoWhenExceptionOccurs(responseBean, 601, strErrorDesc);
               String strXmlResponse = "<olx dir=\"down\" version=\"0.0.1\" compress=\"false\">" +
               		"  <function name=\""+requestBean.getFunctionName()+"\" seq=\""+requestBean.getFunctionSeq()+"\">" +
               		"    <result>" +
               		"      <tree-object version=\"0.0.1\">" +
               		"        <value key=\"c\" class=\"int\" value=\"604\"/>" +
               		"        <value key=\"m\" class=\"bstr\" value=\""+ Base64.encode(strErrorDesc, "UTF-8")+"\"/>" +
               		"      </tree-object>" +
               		"    </result>" +
               		"  </function>" +
               		"</olx>";
               strXmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+strXmlResponse;
               LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "鉴权失败: "+strXmlResponse) );
               writer = response.getWriter();
               writer.write(strXmlResponse+"\n");
               writer.flush();
           }
          

        } catch (InvalidXmlFormatException xmlExp) { // 请求消息格式错误, 返回错误码601
            try {
            	response.setCharacterEncoding("UTF-8");
                response.setContentType("text/xml;charset=UTF-8");
          
                String strErrorDesc =  "请求消息的格式或语法错误:" + xmlExp.toString();
                response.setHeader("x-status-code", Globals.RES_ERROR_CODE_601_STR); // 头字段中的状态码
                response.setHeader("x-status-desc", Base64.encode(strErrorDesc, "UTF-8"));                   // 头字段中的状态描述

                SetResponseBean.setBeanWhenExceptionOccurs(responseBean, requestBean);
                String strXmlResponse = GetSendInfo.getInfoWhenExceptionOccurs(responseBean, 601, strErrorDesc);
                strXmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+strXmlResponse;
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "异常应答XML: "+strXmlResponse) );
                writer = response.getWriter();
                writer.write(strXmlResponse+"\n");
                writer.flush();
           
            } catch (Exception e2) {
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "设置响应代码异常,异常为:"), e2);
            } finally {
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "错误601，请求消息的格式或语法错误, "+request.getRemoteHost()+":"+request.getRemotePort()+"的请求处理失败"), xmlExp);
//                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "错误的请求消息xml:    " + strXmlRequest));
               // LOGGER.debug(LogFormatter.formatMsg(NAME, "核心应用", "中间变量requestBean: " + requestBean.toString()));
                
            }
            
        } catch (BusyAnalyzeException busyExp) { //服务器正忙于分析油耗数据,拒绝请求
        	 try {
                 response.setCharacterEncoding("UTF-8");
                 response.setContentType("text/xml;charset=UTF-8");
           
                 String strErrorDesc =  "服务器正忙于分析油耗数据,拒绝请求,请稍后再发起请求";
                 response.setHeader("x-status-code", Globals.RES_ERROR_CODE_603_STR);         // 头字段中的状态码
                 response.setHeader("x-status-desc", Base64.encode(strErrorDesc, "UTF-8"));   // 头字段中的状态描述

                 SetResponseBean.setBeanWhenExceptionOccurs(responseBean, requestBean);
                 String strXmlResponse = GetSendInfo.getInfoWhenExceptionOccurs(responseBean, 603, strErrorDesc);
                 strXmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+strXmlResponse;
                 LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "异常应答XML: "+strXmlResponse) );
                 writer = response.getWriter();
                 writer.write(strXmlResponse+"\n");
                 writer.flush();
            
             } catch (Exception e2) {
                 LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "设置响应代码异常,异常为:"), e2);
             } finally {
                 LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "错误603，正忙于分析油耗数据,拒绝来自"+request.getRemoteHost()+"的请求"), busyExp);
                 LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "被拒绝的请求消息xml:  " + strXmlRequest));
                 
             }
		}catch(DBErrorException dbexp){
			try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/xml;charset=UTF-8");
          
                String strErrorDesc =  "服务器入库失败，请稍后发起请求";
                response.setHeader("x-status-code", Globals.RES_ERROR_CODE_610_STR);         // 头字段中的状态码
                response.setHeader("x-status-desc", Base64.encode(strErrorDesc, "UTF-8"));   // 头字段中的状态描述

                SetResponseBean.setBeanWhenExceptionOccurs(responseBean, requestBean);
                String strXmlResponse = GetSendInfo.getInfoWhenExceptionOccurs(responseBean, 610, strErrorDesc);
                strXmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+strXmlResponse;
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "异常应答XML: "+strXmlResponse) );
                writer = response.getWriter();
                writer.write(strXmlResponse+"\n");
                writer.flush();
           
            } catch (Exception e2) {
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "设置响应代码异常,异常为:"), e2);
            } finally {
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "错误610，服务器入库失败,拒绝来自"+request.getRemoteHost()+"的请求"), dbexp);
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "被拒绝的请求消息xml:  " + strXmlRequest));
                
            }
		}catch (SendInfoException sendexp) {
			try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/xml;charset=UTF-8");
          
                String strErrorDesc =  "消息下发失败，请稍后发起请求";
                response.setHeader("x-status-code", Globals.RES_ERROR_CODE_611_STR);         // 头字段中的状态码
                response.setHeader("x-status-desc", Base64.encode(strErrorDesc, "UTF-8"));   // 头字段中的状态描述

                SetResponseBean.setBeanWhenExceptionOccurs(responseBean, requestBean);
                String strXmlResponse = GetSendInfo.getInfoWhenExceptionOccurs(responseBean, 611, strErrorDesc);
                strXmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+strXmlResponse;
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "异常应答XML: "+strXmlResponse) );
                writer = response.getWriter();
                writer.write(strXmlResponse+"\n");
                writer.flush();
           
            } catch (Exception e2) {
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "设置响应代码异常,异常为:"), e2);
            } finally {
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "错误611，消息下发失败,拒绝来自"+request.getRemoteHost()+"的请求"), sendexp);
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "被拒绝的请求消息xml:  " + strXmlRequest));  
            }
		}
        catch (Exception innerExp) {  // 服务器内部错误, 返回错误码602
            try {
            	response.setCharacterEncoding("UTF-8");
                response.setContentType("text/xml;charset=UTF-8");
               // String strErrorDesc = Base64.encode("服务器内部错误:"+innerExp.toString(), "UTF-8");
                String strErrorDesc =  "服务器内部错误:"+innerExp.toString();
                response.setHeader("x-status-code", Globals.RES_ERROR_CODE_602_STR); // 头字段中的状态码
                response.setHeader("x-status-desc",Base64.encode(strErrorDesc, "UTF-8") );                   // 头字段中的状态描述
 
                SetResponseBean.setBeanWhenExceptionOccurs(responseBean, requestBean);
                String strXmlResponse = GetSendInfo.getInfoWhenExceptionOccurs(responseBean, 602, strErrorDesc);
                strXmlResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+strXmlResponse+"\n";;
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "异常应答XML: "+strXmlResponse) );
                writer = response.getWriter();
                writer.write(strXmlResponse);
                writer.flush();
                
            } catch (Exception e2) {
            	LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "设置响应代码异常,异常为:"), e2);
            } finally {
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "错误602,服务器内部错误, 来自"+request.getRemoteHost()+"的请求处理失败"), innerExp);
                LOGGER.error(LogFormatter.formatMsg(NAME, "核心应用", "处理失败的请求消息xml:   " + strXmlRequest));
               // LOGGER.debug(LogFormatter.formatMsg(NAME, "核心应用", "中间变量responseBean:  " + responseBean.toString()));
            }
        }finally {
            try {
                reader.close();
                writer.close();
                xmlDoc.set(null);
            } catch (Exception e) { 
            } finally {
                reader = null;
                writer = null;
                xmlDoc = null;
            }             
        }
    }

	private boolean Authentication(RequestBean requestBean) {
		AppConfigCacheManager acc=AppConfigCacheManager.getInstance();
		// Map<String, AppConfigBean> appConfigMap;
		AppConfigBean appConfigBean = acc.getValue(requestBean.getAppid());
		if(appConfigBean==null)
			return false;
		
		//String pass=Base64.encode(new String(Converser.Encryptbyte(appConfigBean.getCore_pass())));
		String pass=Base64.encode(appConfigBean.getCore_pass());
//		System.out.println("appConfigBean.getCore_pass()"+appConfigBean.getCore_pass());
//		System.out.println("appConfigBean.getCore_pass()--md5base64"+pass);
//		System.out.println("requestBean.getPass()--md5base64"+requestBean.getPass());
		
		 if(!pass.toUpperCase().equals(requestBean.getPass().toUpperCase()))	{
			 return false;			
		 }else{
			 return true;
		 }	
	}
}