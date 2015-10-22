<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/JQuery zTree v3.1/css/zTreeStyle/zTreeStyle.css'/>" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>
<style type="text/css">
ul.ztree {position:relative;margin-top: 0px;border: 0px solid #617775;float:left; display:block; width:100%; padding:0px;}
.ztree li button.pIcon_ico_close{margin-right:4px; background: url(../images/ico_folderClosed.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.pIcon_ico_open{margin-right:4px; background: url(../images/ico_folderOpened.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.pIcon_ico_docu{margin-right:4px; background: url(../images/ico_sRoute.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li button.online_ico_docu{font-family: 微软雅黑;color: #ffffff;font-size: 12px;text-align: center;width: 31px;height: 15px;line-height: 14px;margin-top: 2px;font-weight: bold;margin-right:4px; background: url(../images/xiaocheImage/online.png) no-repeat center ; vertical-align:top; }/**vertical-align:middle*/
.ztree li button.offline_ico_docu{font-family: 微软雅黑;color: #ffffff;font-size: 12px;text-align: center;width: 31px;height: 15px;line-height: 14px;margin-top: 2px;font-weight: bold;margin-right:4px; background: url(../images/xiaocheImage/offline.png) no-repeat center ; vertical-align:top; }/**vertical-align:middle*/
.sidebar {
	width: 260px;
	height: 572px;
}
.treeTab {
	line-height: 32px;
	width: 100%;;
	background : url(../images/xiaocheImage/title_bg.png) repeat-x left top;
	height: 33px;
	padding-top: 5px;
}
A {cursor: pointer;color: #0078c8;text-decoration : none;}
.treeTab {
	line-height: 32px;
	width: 260px;
	background : url(../images/xiaocheImage/title_bg.png) repeat-x left top;
	height: 33px;
	padding-top: 5px;
}
.treeTab A.tabfocus {
	text-align : center;
	line-height: 32px;
	width: 68px;
	display : block;
	background : url(../images/xiaocheImage/tree_tab.gif) no-repeat left center;
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
	width: 100%;
	overflow: auto;
	background : #fff;
}
.newsearchPlan {
	border-bottom : #b8b8b8 1px solid;
	line-height: 35px;
	width: 100%;
	background: url(../images/xiaocheImage/search_bg.png) #eee repeat-x left top;
	height : 60px;
	padding-top : 4px;
}
#treeDemo {
	background : #fff;
}
.ok{
display:inline-block;
height:18px;
background: url(../images/xiaocheImage/green.png) no-repeat left top;
}
.low{
display:inline-block;
height:18px;
background: url(../images/xiaocheImage/red.png) no-repeat left top;
}
.no{
display:inline-block;
height:18px;
background: url(../images/xiaocheImage/gray.png) no-repeat left top;
}
</style>
<script>
	var curAsyncCount = 0;
	var isFirstLoaded=0
	var isSearch = 1;
	function getAsyncUrl() {
		var isChecked = $("#isTerminal").attr("checked");
		var checkBoo = "";
		if(isChecked){
			checkBoo = "0";
		} else {
			checkBoo = "";
		}
	    return jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
	    	    '<s:url value="/treemanage/searchTreeNodesWithOilState.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, "")))+"&ftlyFlag="+checkBoo : 
			    	'<s:url value="/treemanage/getTreeNodesWithOilState.shtml" />?ftlyFlag='+checkBoo;
	};
	
	var setting = {
		treeId:'orgTree',
		async: {
			enable: true,
			url:getAsyncUrl
		},
		check: {
			enable: true,
			nocheck :false,
			chkboxType : { "Y" : "ps", "N" : "ps" }
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
			onClick: onClick,
			onCheck: onCheck,
			beforeAsync: beforeAsync,
			onAsyncSuccess:onAsyncSuccess
		},
		view: {
			addDiyDom: addDiyDom
		}
	};

	function addDiyDom(treeId, treeNode) {
		var aObj = jQuery("#" + treeNode.tId + "_a");
		var editStr ="";
		jQuery(aObj).find("button").eq(0).html(treeNode.vehicleNo);
		if("pIcon" != treeNode.iconSkin) {
			if (treeNode.oilState == "0") {
				 editStr = "<span id='diyBtn_" +treeNode.id+ "' name='"+ treeNode.id + "' class='ok'  title='正常'>&nbsp&nbsp&nbsp&nbsp</span>";
			}  else if(treeNode.oilState == "1") {
				 editStr = "<span id='diyBtn_" +treeNode.id+"' name='"+ treeNode.id + "' class='low'  title='异常'>&nbsp&nbsp&nbsp&nbsp</span>";
			}
// 			else{
// 				 editStr = "<span id='diyBtn_" +treeNode.id+"' name='"+ treeNode.id + "' class='no'  title='未安装油量监控设备'>&nbsp&nbsp&nbsp&nbsp</span>";
// 			}
			aObj.after(editStr);
		}
	}
	
	function beforeAsync() {
		curAsyncCount++;
	}

	function onClick(event, treeId, treeNode) {
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		if(treeNode.checked) {
			// 当前是复选选择状态
			treeObj.checkNode(treeNode,false,true,true);
		} else {
			// 当前是非选择状态
			treeObj.checkNode(treeNode,true,true,true);
		}
	}
	
	// 复选框选中触发事件
	function onCheck(event, treeId, treeNode) {
		var carList = new Array();
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		var url = window.location.href;
		if(url.indexOf("photomonitor") > 0){
// 			searchList();	
		 }else{//2012.11.13之前执行的业务处理
			 for(var i = 0, len = nodes.length; i < len; i++) {
					if("pIcon" != nodes[i].iconSkin) {
						carList.push("'"+nodes[i].id+"'");
					} 
			  }
			
			 mytreeonClick(carList);
		 }	 
	}

	function check() {
		if (curAsyncCount > 0) {
			return false;
		}
		return true;
	}
	// 节点展开列表
	var openNodesId;
	// 节点关闭列表
	var closeNodesId;
	// 节点选中状态
	var checkNodesId;
	// 查询树
	function searchTree(flag,getFlag) {
		if (!check()) {
			return;
		}
		
		isSearch=flag;
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}
	
	jQuery(document).ready( function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);

		
	});
	function startRequest(){
		jQuery('#popArea').mk_sidelayer("close");
		isSearch == 2;
		searchTree(2);
		$('#fuck').click();
	}
	function expandParents(treeObj,node){
	    treeObj.expandNode(node,true);
	    if(node.getParentNode()){
	        expandParents(treeObj,node.getParentNode());
	    }
	}	
	
	var finalVINs = [];
	
	function onAsyncSuccess() {
		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var refreshNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		if(isSearch != 2 && $.trim($("#vehicleLn").val()).length == 0 && $("#isTerminal").attr("checked") == false){
			newTreeObj.checkAllNodes(true);
		} else if(isSearch == 1 && $.trim($("#vehicleLn").val()).length == 0 && $("#isTerminal").attr("checked") == true){
			for ( var n = 0, lenNodesNew = refreshNodes.length; n < lenNodesNew; n++) {
				if("pIcon" != refreshNodes[n].iconSkin){
						newTreeObj.checkNode(refreshNodes[n],true,true,false);
						refreshNodes[n].checkedOld = true;
				} else if(refreshNodes[n].name.indexOf("(0/0)") == -1){
						newTreeObj.expandNode(refreshNodes[n], true, false, false,false);
				} else if(refreshNodes[n].name.indexOf("(0/0)") != -1){
						newTreeObj.expandNode(refreshNodes[n], false, false, false,false);
				}
			}
		} else if(isSearch != 2 && $.trim($("#vehicleLn").val()).length != 0 && $("#isTerminal").attr("checked") == false){
			newTreeObj.expandAll(true);
		} else if(isSearch != 2 && $.trim($("#vehicleLn").val()).length != 0 && $("#isTerminal").attr("checked") == true){
			newTreeObj.expandAll(true);
		}
// 		else if(isSearch == 2){
// 				if (null != openNodesId) {
// 					if(jQuery("#vehicleLn").val() == "") {
// 						for ( var j = 0, len = openNodesId.length; j < len; j++) {
// 							for ( var k = 0, lenNodes = refreshNodes.length; k < lenNodes; k++) {
// 								if (refreshNodes[k].id == openNodesId[j]) {
// 									newTreeObj.expandNode(refreshNodes[k], true, false, false,false);
// 								}
// 							}
// 						}
// 					} else {
// 						for ( var m = 0, closeLen = closeNodesId.length; m < closeLen; m++) {
// 							for ( var n = 0, lenNodes1 = refreshNodes.length; n < lenNodes1; n++) {
// 								if (refreshNodes[n].id == closeNodesId[m]) {
// 									newTreeObj.expandNode(refreshNodes[n], false, false, false,false);
// 								}
// 							}
// 						}
// 					}
					
// 				}
		
// 				if(null != checkNodesId) {
// 					for( var m =0, lenChecked = checkNodesId.length; m < lenChecked; m++) {
// 						for ( var n = 0, lenNodesNew = refreshNodes.length; n < lenNodesNew; n++) {
// 							if (refreshNodes[n].id == checkNodesId[m]) {
// 								newTreeObj.checkNode(refreshNodes[n],true,false,false);
// 								refreshNodes[n].checkedOld = true;
// 							}
// 						}
// 					}
// 				}
// 		}
		if(refreshNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		
		curAsyncCount = 0;
		$(".alarm_tabs").click();
	}
	
</script>

