<p class="cutpage">共 ${total} 条记录&nbsp; 每页显示
	<select name="pageSize" onchange="if (this.options[this.selectedIndex].value != '') {
		location = '${url}?<#if numParam != "">${numParam}&</#if>page=${(page)?c}&pageSize=' + this.options[this.selectedIndex].value;
	}">
		${numPage}
		
	</select> 条&nbsp; 当前第 ${page}/${totalPageCount} 页&nbsp;
	<#if firstUrl != ""><a href="${firstUrl}">首页</a><#else>首页</#if>&nbsp;
	<#if prevUrl != ""><a href="${prevUrl}">上一页</a><#else>上一页</#if>&nbsp;
	<#if nextUrl != ""><a href="${nextUrl}">下一页</a><#else>下一页</#if>&nbsp;
	<#if lastUrl != ""><a href="${lastUrl}">尾页</a><#else>尾页</#if>&nbsp;
	跳转到
	<select name="page" onchange="if (this.options[this.selectedIndex].value != '') {
		location = '${url}?<#if param != "">${param}&</#if>page=' + this.options[this.selectedIndex].value;
	}">
		${jumpPage}
	</select>
</p>