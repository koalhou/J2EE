/** 过滤通配符、空格 **/
function formatSpecialChar(str) {
		return str.replace(/\\/g,"\\\\")
		          .replace(/%/g,"\\%")
		          .replace(/_/g,"\\_")
		          .replace(/％/g,"\\%")
		          .replace(/＿/g,"\\_")
		          .replace(/^\s+|\s+$/g, '');
}
function loger(log)
{
	if(typeof(console)!='undefined' )
	{
		console.log(log);
	}
}
//根据QueryString参数名称获取值
function getQueryStringByName(name){

     var result = location.search.match(new RegExp("[\?\&]" + name+ "=([^\&]+)","i"));

     if(result == null || result.length < 1){

         return "";

     }

     return result[1];

}

function eclipse(text,length)
{
	if(length>=text.length)return text;
	return text.substr(0,length)+"..."; 
}