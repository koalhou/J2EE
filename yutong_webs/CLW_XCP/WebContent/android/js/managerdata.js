/**
 * 客户经理数据帮助
 * 
 */
function ManagerData() {

	this.dataUrl=null;
	this.datas=null;
};

ManagerData.prototype = {

	init : function(managerdataurl) {
		this.dataUrl=managerdataurl;
		//暂时不采用加载外部文件的形式
		this.datas=[
	{"managername":"魏珂","zone":"中部区域","headpic":"../android/images/weike.png","tel":"13674966956"},
	{"managername":"王伟","zone":"黑吉区域","headpic":"images/wangwei.png","tel":"13673627679"},
	{"managername":"黄学艺","zone":"闽浙区域","headpic":"images/huangxueyi.png","tel":"15039093190"},
	{"managername":"曹亚非","zone":"山东区域","headpic":"images/caoyafei.png","tel":"13838544781"},
	{"managername":"张雷","zone":"华南区域","headpic":"images/zhanglei.png","tel":"18937819030"},
];
	},
	getbyzone:function(zone)
	{
		try
		{
			for(var i=0;i<this.datas.length;i++)
			{
				if(this.datas[i].zone==zone)
				{
					return this.datas[i];
				}
			}
			return datas[0];
		}
		catch (e)
		{
			
		}
	},
	getbyIndex:function(index)
	{
		try
		{
			var i=index;
			if(i>=datas.length)
				i=datas.length-1;
			if(i<=0)
				i=0;
			return datas[index];
		}
		catch (e)
		{
			
		}
	}
};

