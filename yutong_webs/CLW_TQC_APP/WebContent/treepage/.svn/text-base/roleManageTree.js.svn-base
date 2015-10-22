/**
 * 用于角色选择控制的JS方法与获取值例子
 * 
 * 
 */

var checkBoxs = null;
// 用于返回所选择的id组 ————用于仿造获取
function checkBoxValueS() {
	var ids = '';
	if (checkBoxs == null) {
		checkBoxs = document.getElementsByName("roleTreeCheckBox");
	}
	if (!checkBoxs || checkBoxs.length == 0) {
		return;
	}
	for ( var i = 0; i < checkBoxs.length; i++) {
		var temp = checkBoxs[i];
		if (temp.checked == true) {
			ids += temp.module_id + ',';
		}
	}
	//alert(ids.substring(0, ids.length - 1));
}
/*
 * checkbox单击事件
 */
function treeCheckBox(obj) {

	if (checkBoxs == null) {
		checkBoxs = document.getElementsByName("roleTreeCheckBox");
	}
	if (!checkBoxs || checkBoxs.length == 0) {
		return;
	}

	if (obj.checked) {

		treebox_checked_true(obj);

	} else {

		treebox_checked_false(obj);

	}

}
function treebox_checked_true(obj) {
	var left_num = Number(obj.attributes.getNamedItem('left_num').value);
	var right_num = Number(obj.attributes.getNamedItem('right_num').value);
	for ( var i = 0; i < checkBoxs.length; i++) {
		var temp = checkBoxs[i];
		if (Number(temp.attributes.getNamedItem('left_num').value) < left_num
				&& Number(temp.attributes.getNamedItem('right_num').value) > right_num) {
			temp.checked = true;
		}
		if (Number(temp.attributes.getNamedItem('left_num').value) > left_num
				&& Number(temp.attributes.getNamedItem('right_num').value) < right_num) {
			temp.checked = true;
		}
		if (Number(temp.attributes.getNamedItem('left_num').value) > right_num) {
			break;
		}
	}
}
function treebox_checked_false(obj) {
	var left_num = Number(obj.attributes.getNamedItem('left_num').value);
	var right_num = Number(obj.attributes.getNamedItem('right_num').value);
	// 记录父节点
	var parnt_tree_nodes = new Array();
	var num = 0;
	for ( var i = 0; i < checkBoxs.length; i++) {
		var temp = checkBoxs[i];
		if (Number(temp.attributes.getNamedItem('left_num').value) < left_num
				&& Number(temp.attributes.getNamedItem('right_num').value) > right_num) {
			//进行记录
			parnt_tree_nodes[num++] = temp;
			temp.checked = false;
		}
		if (Number(temp.attributes.getNamedItem('left_num').value) > left_num
				&& Number(temp.attributes.getNamedItem('right_num').value) < right_num) {
			temp.checked = false;
		}
		if (Number(temp.attributes.getNamedItem('left_num').value) > right_num) {
			break;
		}
	}

	for ( var nodesnum = 0; nodesnum < parnt_tree_nodes.length; nodesnum++) {
		var left_p = Number(parnt_tree_nodes[nodesnum].attributes
				.getNamedItem('left_num').value);
		var right_p = Number(parnt_tree_nodes[nodesnum].attributes
				.getNamedItem('right_num').value);
		var break_flag = true;
		for ( var i = 0; i < checkBoxs.length; i++) {
			break_flag = true;
			var temp = checkBoxs[i];
			if (temp.checked) {
				if (Number(temp.attributes.getNamedItem('left_num').value) > left_p
						&& Number(temp.attributes.getNamedItem('right_num').value) < right_p) {
					parnt_tree_nodes[nodesnum].checked = true;
					break_flag = false;
					break;
				}
			}
		}
		if (break_flag) {
			break;
		}
	}
}

/**
 * 用于企业(组织)选择控制的JS方法与获取值例子
 * 
 * 
 */
var onfocusEnterID = null;
function getClickEnterInfo() {
	if (onfocusEnterID == null) {
		return;
	}
	var enter = document.getElementById(onfocusEnterID);

}

function clickEnter(obj) {
	if (obj.id == onfocusEnterID) {
		return;
	}
	if (onfocusEnterID != null && onfocusEnterID != "") {
		document.getElementById(onfocusEnterID).style.backgroundColor = "";
	}
	document.getElementById(obj.id).style.backgroundColor = "#AABBCC";
	onfocusEnterID = obj.id;
	// 单击事件，自己页面定义该方法即可
	clickEnterEvent(obj);
}

//记录被选择企业，用以其他页面继续显示被选择时使用
function setEnterTreeMode() {

	if (onfocusEnterID == null) {
		document.getElementById('ChooseEnterID_tree').value = '';
	} else {
		var treeStr = '';
		var enter = document.getElementById(onfocusEnterID);
		var en_left = Number(enter.attributes.getNamedItem('left_num').value);
		var en_right = Number(enter.attributes.getNamedItem('right_num').value);
		var spans = document.getElementsByTagName("span");
		if (spans == null) {
			return;
		}
		var spans_length = spans.length;
		for ( var i = 0; i < spans_length; i++) {
			var tempspan = spans[i];
			if (tempspan.attributes.getNamedItem('left_num') != undefined) {
				var templeft = Number(tempspan.attributes
						.getNamedItem('left_num').value);
				var tempright = Number(tempspan.attributes
						.getNamedItem('right_num').value);
				if (templeft < en_left && tempright > en_right) {
					treeStr = treeStr + tempspan.id + '|';
				}
			}
		}
		document.getElementById('ChooseEnterID_tree').value = treeStr
				+ onfocusEnterID;
	}
}

/**
 * !!!!在树形成后运行
 * 
 */
function doAfterOnloadTree() {
	var ceids = document.getElementById('ChooseEnterID_tree');
	if (!ceids || ceids.value == '') {
		var cmids = document.getElementById('ChooseModID_tree');
		if (!cmids || cmids.value == '') {
			return;
		} else {
			var cmidsList = cmids.value.split("|");
			for ( var i = 0; i < cmidsList.length; i++) {
				var templim = document
						.getElementById('tree_li_id_' + cmidsList[i]);
				var tempboxm = document
						.getElementById('tree_box_id_' + cmidsList[i]);

				tempboxm.checked = true;
				if (templim) {
					if (templim.className == 'plus') {
						templim.className = 'minus';
					}else if(templim.className == 'plus-last') {
						templim.className = 'minus-last';
					}else if(templim.className == 'plus-only') {
						templim.className = 'minus-only';
					}
				}
			}
		}
	} else {
		var ceidsList = ceids.value.split("|");
		for ( var i = 0; i < ceidsList.length - 1; i++) {
			var templi = document.getElementById('tree_li_id_' + ceidsList[i]);
			if (templi) {
				if (templi.className == 'plus') {
					templi.className = 'minus';
				}else if(templi.className == 'plus-last') {
					templi.className = 'minus-last';
				}else if(templi.className == 'plus-only') {
					templi.className = 'minus-only';
				}
			}
		}
		var obj = document.getElementById(ceidsList[ceidsList.length - 1]);
		if (obj) {
			obj.onclick();
		}
	}

}
