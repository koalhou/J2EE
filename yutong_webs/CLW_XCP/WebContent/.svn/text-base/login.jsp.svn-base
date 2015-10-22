<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
</head>
<body >
<script type="text/javascript">
    var url=window.location.href;
    url=url.toLocaleLowerCase();
    if(url.indexOf("xfdz.")>=0){    	
    	window.top.location = "<s:url value='/xfdzlogin.jsp' />";
    }else if(url.indexOf("nyxcgl.")>=0){    
    	window.top.location = "<s:url value='/login/nyxcgllogin.jsp' />";
    }else if(url.indexOf("hy.")>=0){
    	window.top.location = "<s:url value='/login/hylogin.jsp' />";
    }else if(url.indexOf("ah.")>=0){
    	window.top.location = "<s:url value='/login/ahlogin.jsp' />";
    }else if(url.indexOf("e-trans.")>=0){
    	window.top.location = "<s:url value='/login/yclogin.jsp' />";
    }else{    	
    	window.top.location = "<s:url value='/commonlogin.jsp' />";
    }
</script>
</body>
</html>