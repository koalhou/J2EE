
var textApp={
	clearEnter:function(str){
		str=str.replace(/\r/ig,"").replace(/\n/ig,"");
		return str;
	}
};
/** 过滤通配符、空格 **/
function formatSpecialChar(str) {
		return str.replace(/\\/g,"\\\\")
		          .replace(/%/g,"\\%")
		          .replace(/_/g,"\\_")
		          .replace(/％/g,"\\％")
		          .replace(/＿/g,"\\＿")
		          .replace(/^[\s　]*|[\s　]*$/g, '');
}

/**
 * html特殊字符转换
 * @param s
 * @return
 */
function encodeHtml(s){  
	var REGX_HTML_ENCODE = /"|&|'|<|>|[\x00-\x20]|[\x7F-\xFF]|[\u0100-\u2700]/g; 
      return (typeof s != "string") ? s :  
          s.replace(REGX_HTML_ENCODE,  
                    function($0){  
                        var c = $0.charCodeAt(0), r = ["&#"];  
	                        c = (c == 0x20) ? 0xA0 : c;  
                        r.push(c); r.push(";");  
	                       return r.join("");  
	                    });  
}