var Wit = {};

Wit.mask = function(msg){
	var bmask = document.createElement("div");
	var mask = document.createElement("iframe");
	var tmask = document.createElement("div");
	var main = document.getElementById('main');
	if (!main) {
		main = document.documentElement;
	}
	bmask.className = "bmask";
	mask.className = "mask";
	tmask.className = "tmask";
	tmask.innerHTML = "<img src='../images/loading.gif' align='absmiddle'>&nbsp;";
	bmask.style.height = (document.compatMode != "CSS1Compat") ? document.body.scrollHeight : document.documentElement.scrollHeight;
	tmask.style.top = (window.screen.height/2)-120+'px';
 	if (!(window.navigator.userAgent.indexOf("MSIE")>=1)){
 		tmask.style.position = 'fixed';
 	}
	main.appendChild(bmask);
	bmask.appendChild(mask);
	main.appendChild(tmask);
};


Wit.commitAll = function(frm) {
	Wit.mask("数据提交中...");
	frm.submit();
};


Wit.delayCommitURL = function(url,time) {
	Wit.mask("数据提交中...");
	setTimeout(function(){Wit.goBack(url);},time);
	//Wit.delayCommitAll($('testExecuteForm'),1000);
};

Wit.goBack = function(path) {
	window.location=path;
};


Wit.checkErr = function(elm, reg) {
	return (reg.test(elm.value));
};


Wit.checkReg = function(msg, reg) {
	return (reg.test(msg));
};


Wit.removeAllSpan = function(elm) {
	var pnode = elm.getParent().getChildren();
	for(var i = 0, len = pnode.length; i < len; i++){
		if(pnode[i].getTag() == 'span'){
			pnode[i].remove();
		}
	}
};


Wit.showErr = function(elm, msg, styleName) {
	Wit.removeAllSpan(elm);
	var spanElement = new Element('span', {
		'class' : 'error'
	});
	spanElement.setHTML('&nbsp;' + msg);
	//spanElement.style.position = "absolute";
	// 填加边框
	//spanElement.style.border = "1px solid #CC0000";
	//spanElement.style.background = "#ffc";
	spanElement.injectAfter(elm);
	var sn = styleName == undefined ? 'tf' : styleName;
	elm.className = sn + ' ' + 'errorInput';
};


Wit.showSucc = function(elm, msg, styleName){
	Wit.removeAllSpan(elm);
	var spanElement = new Element('span', {
		'class' : 'success'
	});
	spanElement.setText(' ' + msg);
	//spanElement.style.position = "absolute";
	// 填加边框
	//spanElement.style.border = "1px solid #00BB00";
	//spanElement.style.background = "#ffc";
	spanElement.injectAfter(elm);
	elm.className = styleName == undefined ? 'tf' : styleName;
};


Wit.showDefault = function(elm, msg, styleName) {
	Wit.removeAllSpan(elm);	
	var spanElement = new Element('span');
	spanElement.setHTML(msg);
	spanElement.injectAfter(elm);
	//elm.className = styleName == undefined ? 'tf' : styleName;
	//alert(getSuccStyle1(elm) + elm.name);
	elm.className = styleName == undefined ? getSuccStyle1(elm) : styleName;
};

/*取元素验证成功时显示样式*/
function getSuccStyle1(elm){
	if(elm.type == 'text' || elm.type=='password'){
		return 'tf';
	}
	if(elm.type == 'textarea'){
		return 'ta';
	}
	if(elm.type == 'select-one'){
		return 'sl';
	}
	if(elm.type == 'radio'){
		return 'ra';
	}
	if(elm.type == 'checkbox'){
		return 'cb';
	}
}

Wit.checkBoxChange = function(chkAll) {
	var chks = $ES('input');
	for (var i = 0, len = chks.length; i < len; i++) {
		if (chks[i].type == 'checkbox') {
			chks[i].checked = chkAll.checked;
		}
	}
};

Wit.initStatus=function(selectAll) {
	var select = true;
	var chks = $ES('input');
	for (var i = 0, len = chks.length; i < len; i++) {
		if (chks[i].type == 'checkbox' && chks[i]!=selectAll) {
			if (chks[i].disabled==false) {
				select=false;
				break;
			}
		}
	}
	$(selectAll).disabled=select;
}

Wit.checkStatus = function(selectAll) {
	var select = true;
	var chks = $ES('input');
	for (var i = 0, len = chks.length; i < len; i++) {
		if (chks[i].type == 'checkbox') {
			if ((chks[i].name != selectAll) && (chks[i].id != selectAll) && chks[i].checked == false) {
				select = false;
				break;
			} 
		}
	}
	
	var sa = $(selectAll);
	if (sa && sa.type == 'checkbox') {
		sa.checked = select;
	}
};

Wit.delConfirm = function(url, confirmMsg) {
	if(window.confirm(confirmMsg)){
		Wit.mask("数据提交中...");
		window.location = url;
	}
};

Wit.delBatchConfirm = function(frm, confirmMsg, errMsg) {
	if (isAble()) {
		$('message').innerHTML = '';
		if(window.confirm(confirmMsg)){
			Wit.mask("数据提交中...");
			$(frm).submit();
		}
	} else {
		$('message').innerHTML=errMsg;
	}
};

Wit.add = function(frm, errMsg) {
	if (isAble()) {
		$('message').innerHTML = '';
		Wit.mask("数据提交中...");
		$(frm).submit();
	} else {
		$('message').innerHTML=errMsg;
	}
};

var isAble = function() {
	var cbs = $ES('input');
	for (var i = 0, len = cbs.length; i < len; i++) {
		if (cbs[i].type == 'checkbox') {
			if (!cbs[i].disabled) {
				if (cbs[i].checked) {
					return true;
				}
			} 
		}
	}
	return false;
};

var hasChecked = function() {
	var cbs = $ES('input');
	for (var i = 0, len = cbs.length; i < len; i++) {
		if (cbs[i].type == 'checkbox') {
			if (cbs[i].checked) {
				return true;
			}
		}
	}
	return false;
};

var trim = function (val){
 return val.replace(/(^\s*)|(\s*$)/g, "");
};
/*Delete Null Of The Names */
Wit.trim = function (val){
 return val.replace(/(^\s*)|(\s*$)/g, "");
};

Wit.getQueryArgs = function() {
	var args = new Object();
	var query = location.search.substring(1);
	var pairs = query.split("&");
	pairs.each(function(item) {
		var pos = item.indexOf('=');
		if (pos != -1) {
			var argName = item.substring(0, pos);
			var value = item.substring(pos + 1);
			args[argName] = value;			
		}
	});
	return args;
};

Wit.move = function(source, target, staticValue) {
	var sel = $(source);
	var tel = $(target);
	if (!$chk(staticValue)) {
		staticValue = '-1';
	}
	
	var options = sel.getChildren();
	options.each(function(option) {
		if (option.value != staticValue) {
			if (option.selected) {
				option.injectInside(tel);
			}
		}
	});
	
	sel.getChildren().getLast().setProperty('selected', true);
};