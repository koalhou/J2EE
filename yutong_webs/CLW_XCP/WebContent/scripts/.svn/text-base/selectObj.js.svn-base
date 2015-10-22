/**
 * 
 */
var newSelect = function(obj,height){
	this.targetObj = obj;
	this.selContent = null;
	this.options = new Array();
	this.height = 120;
//	this.width = 150;
};

newSelect.prototype = {
	instance: function(targetObj){
		jQuery(targetObj).append();
	},
	setHeight: function(objHeight){
		this.height = objHeight;
	},
//	setWidth: function(objWidth){
//		this.width = objWidth;
//	},
	addSelect : function(){
		var objs = this.targetObj;
		var ids = jQuery(objs).attr("id");
		jQuery(objs).parent().append("<div class=\"routeSel selNone\" style=\"cursor: pointer;color:black;\" name=\""+ids+"Sel\" id=\""+ids+"Sel\"></div>");
		this.selContent = jQuery("#"+ids+"Sel");
		jQuery(this.selContent).append("<div class=\"option\" attr=\"\"  >--请选择--</div>");
		jQuery(this.targetObj).val("--请选择--");
		this.selBind();
	},
	addOption : function(val,text){
		var thisObj = this;
		jQuery(this.selContent).append("<div class=\"option\" attr=\""+val+"\"  >"+text+"</div>");
		var size = jQuery("div",this.selContent).length;
		if(size * 15 > this.height){
			jQuery(this.selContent).css({"height": this.height});
		}else{
			jQuery(this.selContent).css({"height": jQuery("div",this.selContent).length * 15});
		}
	},
	getOptions : function(){
		return jQuery("div[class=option]").length();
	},
	filterText : function(val){
		var thisObj = this;
		jQuery("div",thisObj.selContent).css("display","none");
		if(val.length==0){
			jQuery("div",thisObj.selContent).css("display","");
			return ;
		}
		jQuery("div:contains('"+val+"')",thisObj.selContent).css("display","");
	},
	onOver:function(obj){
		jQuery(obj).removeClass("optionOut");
		jQuery(obj).addClass("optionOver");
	},
	onOut:function(obj){
		jQuery(obj).removeClass("optionOver");
		jQuery(obj).addClass("optionOut");
	},
	setRouteText:function(obj){
//		if(jQuery(obj).text().indexOf("请选择") > -1){
//			jQuery(this.targetObj).val("");
//		} else {
			jQuery(this.targetObj).val(jQuery(obj).text());
//		}
	},
	selBind: function(){
		var thisObj = this;
		jQuery(thisObj.targetObj).mousedown(function(){
			if(jQuery(this).val()=="--请选择--"){
				jQuery(this).val("");
			}
			jQuery(thisObj.selContent).removeClass("selNone");
			//thisObj.filterText(jQuery(this).val());
		});
		jQuery(thisObj.targetObj).click(function(){
			jQuery(thisObj.selContent).removeClass("selNone");
			thisObj.filterText(jQuery(this).val());
		});
		jQuery(this.targetObj).keyup(function(){
			//jQuery(thisObj.selContent).removeClass("selNone");
			thisObj.filterText(jQuery(this).val());
		});
		
		jQuery(this.selContent).css({"width": jQuery(this.targetObj).css("width")},{"height": "15"});
	},
	seBindOption : function(fun){
		var thisObj = this;
		jQuery("div",thisObj.selContent).mouseover(function(){
			thisObj.onOver(this);
		}).mouseout(function(){
			thisObj.onOut(this);
		}).click(function(){
			thisObj.setRouteText(this);
			jQuery(thisObj.selContent).addClass("selNone");
			fun.call();
		}).blur(function(){
			jQuery(thisObj.selContent).addClass("selNone");
		});
		document.body.onclick=function(event){
			var e = event ? event : window.event;
			var targetEl = e.target ? e.target : e.srcElement;
			if(targetEl){
				if(targetEl.className!="option" && targetEl.className != "routeSel" && targetEl.id!="routeName"){
					jQuery(thisObj.selContent).addClass("selNone");
					if(jQuery(thisObj.targetObj).val().length==0){
						jQuery(thisObj.targetObj).val("--请选择--");
					}
				}
			} else {
				jQuery(thisObj.selContent).addClass("selNone");
			}
			
			
		};
	}
};