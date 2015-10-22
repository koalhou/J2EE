<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择车辆</title>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/JQuery zTree v3.1/css/zTreeStyle/zTreeStyle.css'/>" />
<script type="text/javascript" src="../scripts/flexigrid/jquery-1.5.2.min.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>
<style type="text/css">
ul.ztree {position:relative;margin-top: 0px;border: 0px solid #617775;float:left; display:block; width:100%; padding:0px;}
.ztree li button.pIcon_ico_close{margin-right:4px; background: url(../images/xiaocheImage/ico_folderClosed.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.pIcon_ico_open{margin-right:4px; background: url(../images/xiaocheImage/ico_folderOpened.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.pIcon_ico_docu{margin-right:4px; background: url(../images/xiaocheImage/ico_sRoute.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.online_ico_docu{margin-right:4px; background: url(../images/xiaocheImage/ico_SB_online.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.offline_ico_docu{margin-right:4px; background: url(../images/xiaocheImage/ico_SB_online.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.sidebar {
	width: 260px;
	height: 572px;
}
.treeTab {
	line-height: 32px;
	width: 260px;
	background : url(../images/xiaocheImage/title_bg.png) repeat-x left top;
	height: 33px;
	padding-top: 5px;
}
A {cursor: pointer;color: #0078c8;text-decoration : none;}
.treeTab {
	line-height: 32px;
	width: 260px;
	background : url(../images/xiaocheImage/title_bg.png) repeat-x left top;
	float : left;
	height: 33px;
	padding-top: 5px;
}
.treeTab A.tabfocus {
	text-align : center;
	line-height: 32px;
	width: 68px;
	display : block;
	background : url(../images/xiaocheImage/tree_tab.gif) no-repeat left center;
	float: left;
	color : #222;
	margin-left: 4px;
	text-decoration : none;
}
A.hideLeft {
	margin : 4px 6px 0px 0px;
	width: 18px;
	display: block;
	background : url(../images/xiaocheImage/ico_hideLeft.gif) no-repeat left top;
	float : right;
	height : 18px;
}
A.showLeft {
	margin : 8px 2px 0px;
	width : 18px;
	dispay : block;
	background : url(../images/xiaocheImage/ico_showLeft.gif) no-repeat left top;
	float : right;
	height : 18px;
}
A.btnbule {
	text-align : center;
	line-height : 28px;
	width : 76px;
	display : block;
	background : url(../images/xiaocheImage/btn_blue.gif) no-repeat left top;
	height : 28px;
	color : #fff;
	font-weight : blod;
}
.treeBox {
	width: 260px;
	height: 542px;
	overflow: auto;
	background : #fff;
}
.newsearchPlan {
	border-bottom : #b8b8b8 1px solid;
	line-height: 35px;
	width: 100%;
	background: url(../images/xiaocheImage/search_bg.png) #eee repeat-x left top;
	float : left;
	height : 40px;
	padding-top : 4px;
}
#treeDemo {
	background : #fff;
}
</style>
</head>
<body>

<input name="ln" id="ln" value="<s:property value='vehicle_ln'/>" type="hidden">
<div id="lefttree" class="treeBox" style="width: 250px;height: 260px;">
	<ul id="treeDemo" class="ztree"></ul>
</div>	
<script>
	var curAsyncCount = 0;


	var setting = {
		treeId:'orgTree',
		async: {
			enable: true,
			url:'../treemanage/getOrganizationTreeData.shtml'
		},
		view: {
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		}
		,
		callback: {
			beforeAsync: beforeAsync,
			onAsyncSuccess:onAsyncSuccess,
			onClick: onClick
		}
	};

	var allChildrenIDs=new Array();
	var allChildrenNames=new Array();
	function beforeAsync() {
		curAsyncCount++;
	}
	function onClick(event, treeId, treeNode) {
		var str='';
		 allChildrenIDs=new Array();
		 allChildrenNames=new Array();
		 getChildNodes(treeNode,str);
	     parent.setOrg(treeNode.id,treeNode.name,"","");
		 //parent.setOrg(treeNode.id,treeNode.name,unique2(allChildrenIDs).toString(),unique2(allChildrenNames).toString());
	}
	 function  unique2(arr){
		 arr.sort();    //先排序
		    var res = [arr[0]];
		    for(var i = 1; i < arr.length; i++){
		        if(arr[i] !== res[res.length - 1]){
		            res.push(arr[i]);
		        }
		    }
		 return res;
	}
	function getChildNodes(treeNode,result){
	      if (treeNode.isParent) {
	        var childrenNodes = treeNode.children;
	        if (childrenNodes) {
	            for (var i = 0; i < childrenNodes.length; i++) {
	            	allChildrenIDs.push(childrenNodes[i].id);
	            	allChildrenNames.push(childrenNodes[i].name);
	            	result = getChildNodes(childrenNodes[i], result);
	            }
	        }
	    }else{
        	allChildrenIDs.push(treeNode.id);
        	allChildrenNames.push(treeNode.name);
        }
	    return result;
	}
	

	function onAsyncSuccess() {
		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());

		if(treeNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		curAsyncCount = 0;
		
	}
	jQuery(document).ready( function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
		
	});
	
</script>


</body>
</html>