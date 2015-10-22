<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/JQuery zTree v3.1/css/zTreeStyle/zTreeStyle.css'/>" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<style type="text/css">
ul.ztree {margin-top: 0px;border: 0px solid #617775;width:252px;height:200px;overflow-y:scroll;overflow-x:auto;}
</style>
<script>
function getAsyncUrl() {
    return jQuery("#enterpriseName").val()!="" ? '<s:url value="/zdnf/searchTreeData.shtml" />?name='+encodeURI(encodeURI(jQuery("#enterpriseName").val())) : '<s:url value="/zdnf/organizationTreeData.shtml" />';
};
	var setting = {
		treeId:'orgTree',
		async: {
			enable: true,
			url:getAsyncUrl
		},data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: 0
			}
		},
		callback: {
			onClick: onClick,
			onAsyncSuccess: zTreeOnAsyncSuccess
		}
	};

	function onClick(event, treeId, treeNode) {
		document.getElementById("enterpriseId").value = treeNode.id;
		searchVehicleList();
	}

	function setFormEvent() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}

	function searchTree(){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}

	function zTreeOnAsyncSuccess(){
		if(document.getElementById("enterpriseId").value!=""){
			var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
		    // 获取树上所有节点
		    var nodes = treeObj.transformToArray(treeObj.getNodes());
		    for(var i = 0; i < nodes.length; i++) {
			    if(nodes[i].id==document.getElementById("enterpriseId").value){
			         treeObj.selectNode(nodes[i],false);
			         break;
				}
		    }
		}
	}
	jQuery(document).ready(function(){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
	//window.onload = setFormEvent;
</script>

