
/*
*分组组件，封装：按日，按周，按月ui组件
*/
function GroupType(config)
{
	config=config||{};
	this.selectedType=config.selectedType||"day";
	this.startTime=config.startTime||"1970-1-1";
	this.endTime=config.endTime||moment().format("YYYY-MM-DD");
	this.containerId=config.containerId||"";
	this.onChange=config.onChange||this.onChange;
	this.disabled=false;

	this.tmp_all='<div id="ub_timespan_[[id]]" class="ub_timespan" >'+
					'<a id="byday_[[id]]" class="first" href="#">按日</a>'+
					'<a id="byweek_[[id]]" href="#" class="selected">按周</a>'+
					'<a id="bymonth_[[id]]" class="last disable" href="#">按月</a>'+
				'</div>';		
	uuid=0;
	uuid++;
	
}
GroupType.prototype={
	guid:function()
	{
		return uuid;
		
	},
	getContainerId:function()
	{
		return  "ub_timespan_"+this.containerId+"_"+this.guid();
	},
	getTypeId:function(type)
	{
		return  "by"+type+"_"+this.containerId+"_"+this.guid();
	},
	init:function()
	{
		
	},
	onChange:function(data)//选择改变事件回调方法
	{
		//此处，由外部赋值，实现事件响应
	},
	dayClick:function()
	{
		var p="day";
		if(this.selectedType==p)return;
				
		if(this.isDisable(p))
		{
			loger(p+" has disabled.");
			return;
		}
		this.selectedType=p;
		this.updateSelectedUI();
				
		this.onChange(this.selectedType);
		
	},
	weekClick:function()
	{
		var p="week";
		if(this.selectedType==p)return;
		
		if(this.isDisable(p))
		{
			loger(p+" has disabled.");
			return;
		}
		this.selectedType=p;
		this.updateSelectedUI();
				
		this.onChange(this.selectedType);
	},
	monthClick:function()
	{
		var p="month";
		if(this.selectedType==p)return;
		
		if(this.isDisable(p))
		{
			loger(p+" has disabled.");
			return;
		}
		this.selectedType=p;
		this.updateSelectedUI();
				
		this.onChange(this.selectedType);
	},
	bind:function()
	{
		var d=jQuery("#"+this.getTypeId("day"));
		var w=jQuery("#"+this.getTypeId("week"));
		var m=jQuery("#"+this.getTypeId("month"));
		var that=this;
		d.bind('click',jQuery.proxy(this.dayClick,this));
		w.bind('click',jQuery.proxy(this.weekClick,this));
		m.bind('click',jQuery.proxy(this.monthClick,this));
		
	},
	disable:function(isdisabled)
	{
		this.disabled=isdisabled;
		var d=jQuery("#"+this.getTypeId("day"));
		var w=jQuery("#"+this.getTypeId("week"));
		var m=jQuery("#"+this.getTypeId("month"));
		var di=isdisabled?"1":"0";
		d.attr("c_disable",di);
		w.attr("c_disable",di);
		m.attr("c_disable",di);
		if(!isdisabled)
		{
			this.updateState(false);
		}
		else
		{
			d.addClass("disable");
			w.addClass("disable");
			m.addClass("disable");
		}
	},
	isDisable:function(type)
	{
		var o=this.getTypeId(type);
		//return jQuery("#"+o).hasClass("disable");
		
		var d=jQuery("#"+o).attr("c_disable");
		if(typeof(d)=="undefined")return false;
		var r=d=="1"?true:false;
		return r;
	},
	renderItems:function()
	{		
		var items=[];
		var obj={id:this.containerId+"_"+this.guid()};
		var html=GlareTpl.parse(this.tmp_all,obj);		
		return html;
	},
	updateState:function(triggerEnable)
	{		
		var triger=(typeof(triggerEnable)!="undefined") ?triggerEnable:true;
		var endmoment=moment(this.endTime,"YYYY-MM-DD");
		var s=moment(this.startTime,"YYYY-MM-DD").add("months",1);
		
		var d=jQuery("#"+this.getTypeId("day"));
		var w=jQuery("#"+this.getTypeId("week"));
		var m=jQuery("#"+this.getTypeId("month"));
		
		if(s.isBefore(endmoment))//如果大于月
		{
			d.removeClass("disable").attr("c_disable","0");
			w.removeClass("disable").attr("c_disable","0");
			m.removeClass("disable").attr("c_disable","0");
		}
		else 
		{
			
			s=moment(this.startTime,"YYYY-MM-DD").add("weeks",1);
			if(s.isBefore(endmoment))//如果大于周
			{
				d.removeClass("disable").attr("c_disable","0");
				w.removeClass("disable").attr("c_disable","0");
				m.addClass("disable").attr("c_disable","1");
				
				
				if(this.selectedType=="month")
				{
					this.selectedType="week";
					this.updateSelectedUI();
					if(triger)
						this.onChange(this.selectedType);
				}
			}
			else
			{
				d.removeClass("disable").attr("c_disable","0");
				w.addClass("disable").attr("c_disable","1");
				m.addClass("disable").attr("c_disable","1");

				d.addClass("selected");
				w.removeClass("selected");
				m.removeClass("selected");
				
				if(this.selectedType!="day")
				{
					this.selectedType="day";
					this.updateSelectedUI();
					if(triger)
						this.onChange(this.selectedType);
				}
			}
		}
	},
	updateSelectedUI:function()
	{
		jQuery("#"+this.getTypeId("day")).removeClass("selected");
		jQuery("#"+this.getTypeId("week")).removeClass("selected");
		jQuery("#"+this.getTypeId("month")).removeClass("selected");
		var s=this.getTypeId(this.selectedType);
		var so=jQuery("#"+s);
		so.addClass("selected");
		
	},
	setTimeSpan:function(startTime,endTime,triggerEnable)
	{
		if(this.disabled)return;
		var startmoment=moment(startTime,"YYYY-MM-DD");
		var endmoment=moment(endTime,"YYYY-MM-DD");
		if(endmoment.isBefore(startmoment))
		{
			loger("endTime must be after startTime.");
			return false;
		}
		this.startTime=startTime;
		this.endTime=endTime;
		this.updateState(triggerEnable);
	},
	render:function()
	{
		
		var startmoment=moment(this.startTime,"YYYY-MM-DD");
		var endmoment=moment(this.endTime,"YYYY-MM-DD");
		if(endmoment.isBefore(startmoment))
		{
			endmoment=startmoment.add("months",1);//加1月
			this.endTime=endmoment.format("YYYY-MM-DD");
		}
		var html=this.renderItems();
		jQuery('#'+this.containerId).html(html);
		
		this.updateSelectedUI();
		this.updateState();		
		this.bind();
		
	}

}