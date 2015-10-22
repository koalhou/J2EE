
/*
*指标选择面板组件
*/
function Indicator(config)
{
	config=config||{};
	this.selectedItems=[];//e.g: ["pv","vcnt"]

	this.selGroup=config.selGroup||[];
	this.maxSel=config.maxSel||2;
	this.containerId=config.containerId||"";
	this.onChange=config.onChange||this.onChange;

	this.tmp_all='<div id="indicator_con_[[containerId]]" class="indicator_container">'+
					'<div class="wraper" >指标：<a><span class="title">[[title]]</span></a></div>	'+
					'<div class="popup" style="display:none">'+
						'<div class="indicatoritems clearfix">	'+	
							'[[items]]'+
						'</div>		'+
						'<div class="tip">最多可选择<span style="color:#fe9985;margin:0 5px;">[[maxSel]]</span>项指标</div>'+
					'</div>'+	
				'</div>';
	this.tmp_item='<label for="[[id]]" class="item">'+
					'<input name="indicator_[[name]]" type="[[type]]" title="[[title]]" id="[[id]]" [[chk]] value="[[value]]"/>[[title]]'+
					'</label>';
		
		
	uuid=0;
	uuid++;
}
Indicator.prototype={
	guid:function()
	{
		return uuid;
		
	},
	getContainerId:function()
	{
		return  "indicator_con_"+this.containerId+"_"+this.guid();
	},
	init:function()
	{
		
	},
	onChange:function(data)//指标改变事件回调方法
	{
		//此处，由外部赋值，实现事件响应
	},
	popup:function(event)
	{
		event.stopPropagation();

		var str=this.getContainerId();
		
		var pop=jQuery("#"+str+" .popup");
		if(pop.is(":visible"))
		{
			this.hideIndicatorPopup();

		}
		else
		{
			this.showIndicatorPopup();
		}
		

	},
	showIndicatorPopup:function()
	{
		var str=this.getContainerId();		
		var pop=jQuery("#"+str+" .popup");

		this.resizePopup();

		pop.show();
	
		if(pop.is(":visible"))
			jQuery("#"+str+" .wraper").addClass("select");
		else
			jQuery("#"+str+" .wraper").removeClass("select");

		jQuery(document).one("click", jQuery.proxy(this.hideIndicatorPopup,this));

	},
	resizePopup:function()
	{
		var str=this.getContainerId();	
		var pop=jQuery("#"+str+" .popup");

		var h=jQuery("#"+str+" .wraper").height();
		
		var l=jQuery("#"+str+" .wraper").position().left;
		var t=jQuery("#"+str+" .wraper").position().top;
		pop.css("left",l);
		pop.css("top",t+h+12);//12是padding top bottom 10+border 2
		//pop.css("top",t+h);
	},
	hideIndicatorPopup:function()
	{
		var str=this.getContainerId();	
		var pop=jQuery("#"+str+" .popup");
		
		pop.hide();
		if(pop.is(":visible"))
			jQuery("#"+str+" .wraper").addClass("select");
		else
			jQuery("#"+str+" .wraper").removeClass("select");

	},
	remove:function(array,obj){
		var a=array.length;
		while(a--){
			if(a in array&&array[a]===obj){
				array.splice(a,1)
			}
			
		}
		return array;
	},
	setChecked:function(checkbox_value,checked)
	{
		var str=this.getContainerId();	

		jQuery("#"+str+" .indicatoritems input").each(function(i,item){
			if(item.value==checkbox_value)
			{
				item.checked=checked;
				return false;
			}
		});
	},
	updateTitle:function()
	{
		var str=this.getContainerId();
		var t=jQuery("#"+str+" .wraper .title");
		var g=[];
		jQuery("#"+str+" .indicatoritems input").each(function(i,item){
			if(item.checked)
			{
				g.push(item.title);
			}
		});
		t.html(g.join("、"));
		return t.html();
	},
	bind:function()
	{
		var str=this.getContainerId();
		var pop=jQuery("#"+str+" .popup");

		jQuery("#"+str+" .wraper").bind("click",jQuery.proxy(this.popup,this));
		//和document的click配套使用，防止弹出状态，点击面板隐藏。
		pop.bind("click",function(e){
			e.stopPropagation();
			
		});
		var that=this;
		jQuery("#"+str+" .indicatoritems input").bind("click",function(){
			var selitems=that.selectedItems;
			if(this.checked)
			{
				selitems.push(this.value);
			}
			else{
				if(selitems.length==1)
				{
					this.checked=true;
					return false;
				}
				
			   that.remove(selitems,this.value)
			}
			if(selitems.length>that.maxSel){
				
				var e=selitems.splice(0,1)[0];

				that.setChecked(e,false);
			   
			}
			that.updateTitle();
			that.resizePopup();//IE 7下需要重新计算下popup位置
			that.onChange(selitems);
			
		});
		
	},
	itemClick:function()
	{
		
	},
	getChecked:function(v)
	{
		var i=jQuery.inArray(v.value,this.selectedItems);
		return i==-1?"":"checked=\"checked\"";
		
	},
	renderIndicators:function()
	{
		var items=[];
		//var itemtmp=jQuery("#tmp_indicator_item").html();
		var itemtmp=this.tmp_item;
		for(var i =0;i<this.selGroup.length;i++)
		{
			var type=(this.maxSel==1)?"radio":"checkbox";

			var obj={name:this.containerId+"_"+this.guid(),title:this.selGroup[i].title,id:this.getContainerId()+"_"+this.selGroup[i].id,type:type,chk:this.getChecked(this.selGroup[i]),value:this.selGroup[i].value};

			var html=GlareTpl.parse(itemtmp,obj);
			items.push(html);
		}

		var obj={containerId:this.containerId+"_"+this.guid(),title:"加载中...",items:items.join(""),maxSel:this.maxSel};
		//var tmp=jQuery("#tmp_indicator_all").html(); 
		var tmp=this.tmp_all;
		
		var all_html=GlareTpl.parse(tmp,obj);
		
		return all_html;
	},
	setSelectedItems:function()
	{
		for(var i =0;i<this.selGroup.length;i++)
		{
			if(this.selectedItems.length>=this.maxSel)
				return;
			var v=this.selGroup[i];
			if(v.selected)
				this.selectedItems.push(v.value);
		}
	},
	render:function()
	{
		if(this.maxSel<1)this.maxSel=1;

		this.setSelectedItems();
		var html=this.renderIndicators();
		jQuery('#'+this.containerId).html(html);
		this.updateTitle();//只能放在这里执行，否则方法内找不到input
		this.bind();
		
	}

}