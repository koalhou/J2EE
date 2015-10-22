<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/jquery-1.5.2.min.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/jquery-easyui-1.2.2/jquery-cookie.js'/>"></script>
<script type="text/javascript">
function savecookies(cookiename,cookievalue){
	jQuery.cookie(cookiename,cookievalue,{ expires: 30 });
}

function getcookies(cookiename){
	var cookievalue=jQuery.cookie(cookiename);
	return cookievalue;
}

/**
 * 为路径最后一段增加\
 */
function addpath(path){
	if(path.substr(path.length-1)!='\\') 
        path+='\\';
    return path;
}

function valid_path(path){
 // 盘符为a-z ,路径不能包含特殊字符 \/:*?"<>|
    //var patrn=/^[a-z]:\\([^\/*\"><|:?*])+$/;
    var temp=path.indexOf("\\\\");
    if(temp!=-1)
    { 
        return false;
    }
    var patrn=/^[a-zA-Z]:\\([^\/*\><|:?*\"])*$/;
    if(patrn.test(path)){
        return true;
    }
    return false;
}


</script>
