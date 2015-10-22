<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<script>
function getAsyncUrl() {
    //return jQuery("#enterpriseName").val()!="" ? '<s:url value="/zdnf/searchTreeData.shtml" />?name='+encodeURI(encodeURI(jQuery("#enterpriseName").val())) : '<s:url value="/zdnf/organizationTreeData.shtml" />';
    return '<s:url value="/zdnf/organizationTreeData.shtml" />';
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
			onAsyncSuccess:setDefaultLevel
		}
	};

	function onClick(event, treeId, treeNode) {
		document.getElementById("enterpriseId").value = treeNode.id;
		searchVehicleList();
	}
	
	function setDefaultLevel(){
		var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
	    // 获取树上所有节点
	    var nodes = treeObj.transformToArray(treeObj.getNodes());
	    for(var i = 0, len = nodes.length; i < len; i++) {
			if(nodes[i].level<3){
				treeObj.expandNode(nodes[i], true, false, false);
			}else{
				treeObj.expandNode(nodes[i], false, true, false);
			}
	    }
	}
	function setFormEvent() {
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}

	function searchTree(){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}
	jQuery(document).ready(function(){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
	//window.onload = setFormEvent;
</script>