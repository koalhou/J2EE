<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"  isErrorPage="true"  import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%
Logger logger = LoggerFactory.getLogger("System.Error");
logger.error("程序异常", exception);
response.addHeader("Content-Type","application/json; charset=UTF-8");
response.getWriter().write("{ \"errorCode\": \"90000\" ,\"errorDes\":\"Internal Server Error\"}");
%>