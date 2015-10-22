/** 过滤通配符、空格 **/
function formatSpecialChar(str) {
		return str.replace(/\\/g,"\\\\")
		          .replace(/%/g,"\\%")
		          .replace(/_/g,"\\_")
		          .replace(/％/g,"\\%")
		          .replace(/＿/g,"\\_")
		          .replace(/^\s+|\s+$/g, '');
}