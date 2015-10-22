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
#treeDemo {
	background : #fff;
}

</style>
<script>
	var curAsyncCount = 0;
	var treeType = "";
	var isFirst = 0;//进入页面或查询时，默认选中第一辆辆
	function getAsyncUrl() {
		isFirst = 1;
		if(jQuery("#filterFlag").attr("checked")==true) {
			// 过滤
			return jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
		    	    '<s:url value="/carrun_history/searchPlannedTreeNodesWithoutOnline.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, ""))) : 
		    	    '<s:url value="/carrun_history/getPlannedTreeNodes.shtml" />';
		} else {
			return jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
		    	    '<s:url value="/carrun_history/searchTreeNodesWithoutOnline.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, ""))) : 
				    '<s:url value="/carrun_history/getTreeNodesWithoutOnline.shtml" />';
				    
    	    //return jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
    	    	 //   '<s:url value="/treemanage/searchTreeNodesWithOilState.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#vehicleLn").val().replace(/(^\s*)|(\s*$)/g, ""))): 
    			    	//'<s:url value="/treemanage/getTreeNodesWithOilState.shtml" />';
		}
	};
	
	var setting = {
		treeId:'orgTree',
		async: {
			enable: true,
			url:getAsyncUrl
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		},
		callback: {
			onClick: onClick,
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
		//alert(treeNode.vehicle_code);
		
		var url = window.location.href;
		if(url.indexOf("carrun/ready") >0 ||url.indexOf("driverecord/init") >0 
				||url.indexOf("averagefuelranking/ranking") >0           //发车与客流统计默认第一辆车
				||url.indexOf("averagefuelranking/repare") >0
				){
			
		    var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		    // 获取树上所有节点
		    var nodes = treeObj.transformToArray(treeObj.getNodes());
		    if(isFirst == 1){
			    for(var i = 0, len = nodes.length; i < len; i++) {
			         if("pIcon" != nodes[i].iconSkin) {
				         treeObj.selectNode(nodes[i],false);
				         mytreeonClick('1',nodes[i].id);
				         break;
				      }
			     }
		    }
		}
		if("pIcon" != treeNode.iconSkin) {
		jQuery(aObj).find("button").eq(0).html(treeNode.vehicle_code);
		/*
			if (treeNode.oilState == "0") {
				 editStr = "<span id='diyBtn_" +treeNode.id+ "' name='"+ treeNode.id + "' class='ok'  title='正常'>&nbsp&nbsp&nbsp&nbsp</span>";
			}  else if(treeNode.oilState == "1") {
				 editStr = "<span id='diyBtn_" +treeNode.id+"' name='"+ treeNode.id + "' class='low'  title='异常'>&nbsp&nbsp&nbsp&nbsp</span>";
			}
		aObj.after(editStr);
		*/
		}
		isFirst = 0;

		
		/*
		var url = window.location.href;
		if(url.indexOf("carrun/ready") >0
				||url.indexOf("averagefuelranking/ranking") >0           //发车与客流统计默认第一辆车
				){
		    var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		    // 获取树上所有节点
		    var nodes = treeObj.transformToArray(treeObj.getNodes());
		    for(var i = 0, len = nodes.length; i < len; i++) {
		         if("pIcon" != nodes[i].iconSkin) {
			         treeObj.selectNode(nodes[i],false);
			         mytreeonClick('1',nodes[i].id);
			         break;
			      }
		      }
		}*/
		
		
		
		
	}
	
	function beforeAsync() {
		curAsyncCount++;
	}

	function onClick(event, treeId, treeNode) {
		var url = window.location.href;
		if(url.indexOf("ridealarm/ready.shtml") > 0){
			// 异常乘车统计
			if("pIcon" == treeNode.iconSkin) {
				mytreeonClick('0',treeNode);
				//alert("组织click，id=" + treeNode.id);
			} else {
				mytreeonClick('1',treeNode);
				//alert("车辆click，id=" + treeNode.id);
			}
		} else {
			if("pIcon" == treeNode.iconSkin) {
				mytreeonClick('0',treeNode.id);
				//alert("组织click，id=" + treeNode.id);
			} else {
				mytreeonClick('1',treeNode.id);
				//alert("车辆click，id=" + treeNode.id);
			}
		}
	}

	function onAsyncSuccess() {
		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		if(treeNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		curAsyncCount = 0;
		var url = window.location.href;
		if(url.indexOf("carrun/ready") >0||url.indexOf("driverecord/init") >0
				||url.indexOf("averagefuelranking/ranking") >0           //发车与客流统计默认第一辆车
				||url.indexOf("averagefuelranking/repare") >0
				){
		    var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		    // 获取树上所有节点
		    var nodes = treeObj.transformToArray(treeObj.getNodes());
		    for(var i = 0, len = nodes.length; i < len; i++) {
		         if("pIcon" != nodes[i].iconSkin) {
			         treeObj.selectNode(nodes[i],false);
			         mytreeonClick('1',nodes[i].id);
			         break;
			      }
		      }
		}
	}

	function check() {
		if (curAsyncCount > 0) {
			return false;
		}
		return true;
	}

	// 查询树
	function searchTree() {
		if (!check()) {
			return;
		}
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}


	jQuery(document).ready( function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
	
</script>

