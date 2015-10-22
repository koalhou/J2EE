<p class="cutpage"><#if firstUrl != ""><a href="${firstUrl}">首页</a><#else>首页</#if>
	<#if prevUrl != ""><a href="${prevUrl}">上一页</a><#else>上一页</#if>
	<#if nextUrl != ""><a href="${nextUrl}">下一页</a><#else>下一页</#if>
	<#if lastUrl != ""><a href="${lastUrl}">尾页</a><#else>尾页</#if> 跳转到<select name="page" onchange="if (this.options[this.selectedIndex].value != '') {
		location = '${url}?<#if param != "">${param}&</#if>page=' + this.options[this.selectedIndex].value;
	}">${jumpPage}</select>
</p>