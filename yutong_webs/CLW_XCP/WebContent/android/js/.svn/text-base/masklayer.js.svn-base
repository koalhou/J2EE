/**
 * 遮罩层相关
 * 
 */
function MaskLayer() {
	this.visible= null;
	this.backgroundid=null;
	this.containerid=null;
};

MaskLayer.prototype = {

	init : function(backgroundid, containerid, closebtnid) {
		this.backgroundid=backgroundid;
		this.containerid=containerid;

		this.background = $("#" + backgroundid);
		this.container = $("#" + containerid);
		this.closebtn = $("#" + closebtnid);

		
		this.closebtn.bind("click", $.proxy(this._closeBtnClick, this));
		this.visible = this.container.is(':visible');

		$(window).resize( $.proxy(this.conPosition, this));
	},
	// 事件处理方法
	_closeBtnClick : function(event) {
		// alert(event.data.host.visible);
		this.close();
	},
	close : function() {

		if (this.visible) {
			// close
			if (this.background != null) {
				this.background.hide();
			}
			if(this.container!=null)
			{
				this.container.hide();
			}
			this.visible = false;
		}
	},
	open:function()
	{
		var selector="#"+this.backgroundid+",#"+this.containerid;
		$(selector).show();
        this.conPosition();
		this.visible = this.container.is(':visible');


	},
	conPosition:function() {
		try
		{
			var oBackground = document.getElementById(this.backgroundid);
			var oContent = document.getElementById(this.containerid);
			if(oBackground && oContent)
			{
				var dw = $(document).width();
				var dh = $(document).height();
				oBackground.style.width = dw+'px';
				oBackground.style.height = dh+'px';
				
				var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
				var l = (document.documentElement.clientWidth - oContent.offsetWidth) / 2;
				var t = ((document.documentElement.clientHeight - oContent.offsetHeight) / 2) + scrollTop;
				oContent.style.left = l + 'px';
				//oContent.style.top = t + 'px';
			}
		}
		catch(e)
		{
		}
    }
};

