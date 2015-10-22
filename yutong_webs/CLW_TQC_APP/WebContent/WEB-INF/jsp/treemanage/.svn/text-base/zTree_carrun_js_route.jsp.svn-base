<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.excheck-3.1.js'/>"></script>
<script>
	var curAsyncCount = 0;
	var treecarlistin = null;

	function getAsyncUrl() {
		return jQuery("#search_route_name").val().replace(/(^\s*)|(\s*$)/g, "")!="" ? 
	    	    '<s:url value="/carrun_history/searchTreeNodesWithoutOnline.shtml" />?name='+encodeURIComponent(encodeURIComponent(jQuery("#search_route_name").val().replace(/(^\s*)|(\s*$)/g, ""))) : 
			    '<s:url value="/carrun_history/getTreeNodesWithoutOnline.shtml" />';
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
			beforeAsync: beforeAsync
			,onAsyncSuccess:checkFirstCar
		},
		view: {
			addDiyDom: addDiyDom
		}
	};
	function addDiyDom(treeId, treeNode) {
		var ii = window.parent.document.getElementById("route_list_ii").value;
		if(ii==1) {
			if(window.parent.data_list_car) {
				treecarlistin = window.parent.data_list_car;
			}else {
				treecarlistin = window.parent.data_list_car1;
			}
		} else if(ii==2) {
			treecarlistin = window.parent.data_list_car2;
		} else if(ii==3) {
			treecarlistin = window.parent.data_list_car3;
		} else if(ii==4) {
			treecarlistin = window.parent.data_list_car4;
		} else if(ii==5) {
			treecarlistin = window.parent.data_list_car5;
		} else if(ii==6) {
			treecarlistin = window.parent.data_list_car6;
		} else if(ii==7) {
			treecarlistin = window.parent.data_list_car7;
		} else {
			treecarlistin = null;
		}
		var aObj = jQuery("#" + treeNode.tId + "_ico");
		if("pIcon" != treeNode.iconSkin) {
			jQuery(aObj).html(treeNode.vehicle_code);
		}
		if(treecarlistin!=null&&treecarlistin.length>0) {
			for(var v = 0;v<treecarlistin.length;v++) {
				if(treeNode.id == treecarlistin[v].VIN) {
					aObj = jQuery("#" + treeNode.tId + "_a");
					aObj.css("width","85px");
					aObj.attr("title",name);
					var name = treeNode.name;
					if(treeNode.name.length > 7){
						treeNode.name = treeNode.name.substring(treeNode.name.length-7, treeNode.name.length);
						var aObjspan = jQuery("#" + treeNode.tId + "_span");
						aObjspan.val(treeNode.name);
						aObjspan.html(treeNode.name);
					}
					/* var editStr = "<span id='diyBtn_" +treeNode.id+ "' name='"+ treeNode.id + "' class='button icon01' style=''></span>";
					aObj.after(editStr); */
				}
			}
		}
	}
	function beforeAsync() {
		curAsyncCount++;
	}

	function onClick(event, treeId, treeNode) {
		if("pIcon" != treeNode.iconSkin) {
			mytreeonClick(treeNode.id,treeNode.name,treeNode.vehicle_code,treeNode.driver_name,treeNode.driver_id);
		}
	}

	//1、车辆运行日志-默认选中第一辆车
	function checkFirstCar() {
		/* var content = document.getElementById("content").className;
		if (content.indexOf("routemonitorDivArea") >= 0){
		    var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		    // 获取树上所有节点
		    var nodes = treeObj.transformToArray(treeObj.getNodes());
		    for(var i = 0, len = nodes.length; i < len; i++) {
		         if("pIcon" != nodes[i].iconSkin) {
			         treeObj.selectNode(nodes[i],false);
			         mytreeonClick(nodes[i].id,nodes[i].name);
			         break;
			      }
		      }
		} */

		var newTreeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		var treeNodes = newTreeObj.transformToArray(newTreeObj.getNodes());
		if(treeNodes.length == 0) {
			jQuery("#treeDemo").html("<div style='text-align:center'>查询不到内容</div>");
		}
		curAsyncCount = 0;
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

	function fliterCars() {
		if (!check()) {
			return;
		}
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}
	
	jQuery(document).ready(function() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
	
</script>

