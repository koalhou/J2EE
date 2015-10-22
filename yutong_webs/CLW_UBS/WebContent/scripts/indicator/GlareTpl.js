/*
*简单模板解析。可以替换模板字符串中 [[xxxx]] 变量字符串
*/
var GlareTpl=(function(){
	return {
		delimiterleft:"\\[\\[",
		delimiterright:"\\]\\]",
		replaceVar:function(html,obj)//变量替换
		{
			if(typeof obj!='object')return '';

			//换正则表达式来解析
			html=html.replace(new RegExp(this.delimiterleft+"([^\\[\\]]*?)"+this.delimiterright,'igm')
				,function($,$1){
					var o=(typeof(obj[$1])!='undefined') ?obj[$1]:$;
					return o;
				
			});
			return html;
		},
		parse:function()
		{
			var html=arguments[0]||'';
			var obj=arguments[1]||{};
			return this.replaceVar(html,obj);
		}
	
	}

}());
