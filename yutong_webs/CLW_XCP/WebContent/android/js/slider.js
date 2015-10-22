/**
 * 简易幻灯片
 * 
 */
function Slider(config) {
	this.sliders= [];
	this.dotContainerId=null;//切换小点容器id
	this.interval=3000;//ms
	this.timerid=null;
	this.index=0;
	
	this.init(config);
};

Slider.prototype = {
	init:function(config)
	{
		this.sliders=config.sliders||[];
		this.dotContainerId=config.dotCotainerId;
		this.interval=config.interval||3000;
		if(this.dotContainerId==null)
		{
			return;
		}
		if(this.sliders.length<=0)return;
		var dom="";
		for(var i=0;i<this.sliders.length;i++)
		{
			var $dom=$("#"+this.sliders[i]);
			//$dom.css("left","-1000px");
			

			//<li><span id="item_1" class="dotcheck"></sapn></li>
			dom+="<li><span id='"+i+"' class='dotuncheck'></sapn></li>";
		}
		$("#"+this.dotContainerId).html(dom);
		//bind("click", $.proxy(this._closeBtnClick, this));
		$("#"+this.dotContainerId+" li span").bind("click", $.proxy(this.dotClick, this));
	}
	
	,start:function()
	{
		if(this.sliders.length<=0)return;
		
		this.timerid=setInterval($.proxy(this.onTick, this),this.interval);

	}
	,onTick:function()
	{
		this.index++;
		if(this.index>=this.sliders.length)this.index=0;
		this.switchSlider();		
	}
	,switchSlider:function()
	{
		for(var i=0;i<this.sliders.length;i++)
		{
			var $dom=$("#"+this.sliders[i]);
			if(this.checkBrowserIsIE6_7_8())
			{
				$dom.hide();
			}
			else
			{
				$dom.animate({"left":"500px","opacity":" 0"},500);		
			}
			
				
			
				
		}
		$("#"+this.dotContainerId).find("span").removeClass("dotcheck");
		$("#"+this.dotContainerId).find("span").addClass("dotuncheck");

		var $cur=$("#"+this.sliders[this.index]);
		
		

		
		if(this.checkBrowserIsIE6_7_8())
		{
			$cur.show();
		}
		else
		{
			$cur.css("left","-1000px");
			$cur.css("opacity","0");
			$cur.animate({"left":"-100px","opacity":" 1"},500);
		}
		
				

		$("#"+this.dotContainerId).find("#"+this.index).removeClass("dotuncheck");
		$("#"+this.dotContainerId).find("#"+this.index).addClass("dotcheck");
		
	}
	
	,stop:function()
	{
		clearInterval(this.timerid);
	}
	,dotClick:function(event)
	{
		this.stop();	
		var tar=event.target;
		if(this.checkCurIndex(tar.id)){
			this.start();
			return;
		}
		
		this.index=tar.id;
		this.switchSlider();
		this.start();
		

	}
	,checkCurIndex:function(curId)
	{
		var id=curId-0;//转换类型
		
		
		return this.index==id;
	}
	,checkBrowserIsIE6_7_8:function()
	{
		var b=$.browser;
		var ver=b.version;
		if(!b.msie)
			return false;
		return ver=="6.0"||ver=="7.0"||ver=="8.0";
	}

	
};

