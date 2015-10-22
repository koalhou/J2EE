<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='/scripts/JQuery zTree v3.1/css/zTreeStyle/zTreeStyle.css'/>" />
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/JQuery zTree v3.1/js/jquery.ztree.core-3.1.js'/>"></script>
<style type="text/css">
ul.ztree {margin-top: 0px;border: 0px solid #617775;width:240px;height:500px;overflow-y:scroll;overflow-x:auto;}
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
			onClick: onClick
		}
	};

	function onClick(event, treeId, treeNode) {
		document.getElementById("enterpriseId").value = treeNode.id;
		searchVehicleList();
	}

	function searchTree(){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	}
	jQuery(document).ready(function(){
		//jQuery(".ztree").css('height','500px');
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting);
	});
</script>

