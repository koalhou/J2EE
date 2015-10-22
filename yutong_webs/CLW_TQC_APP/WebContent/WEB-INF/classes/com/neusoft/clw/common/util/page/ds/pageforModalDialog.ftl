<div id="pageCtrl">
	  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	      <td width="25%" align="left">每页显示
            <select name="pageSize" class="input60" onchange="if (this.options[this.selectedIndex].value != '') {
		modalDialog('${url}?<#if numParam != "">${numParam}&</#if>page=${page}&pageSize=' + this.options[this.selectedIndex].value);
	}">
		${numPage}
		
	</select>条  共${total} 条</td>	
	      <td width="50%" align="center">
		    <div id="gotoPage">
              <table><tr>
              	<td><#if firstUrl != ""><a href="${firstUrl}" class="btnFirst"></a><#else><a class="btnFirstDisable" style="cursor: default"></a></#if></td>
                <td><#if prevUrl != ""><a href="${prevUrl}" class="btnPrevious"></a><#else><a class="btnPreviousDisable" style="cursor: default"></a></#if></td>
                <td>第 ${page}/${totalPageCount} 页</td>
                <td><#if nextUrl != ""><a href="${nextUrl}" class="btnNext2"></a><#else><a class="btnNext2Disable" style="cursor: default"></a></#if></td>
                <td><#if lastUrl != ""><a href="${lastUrl}" class="btnLast"></a><#else><a class="btnLastDisable" style="cursor: default"></a></#if></td>
              </tr></table>
            </div>				  </td>
	      <td width="25%" align="right">跳转到
            <select name="page" class="input60" onchange="if (this.options[this.selectedIndex].value != '') {
		modalDialog('${url}?<#if param != "">${param}&</#if>page=' + this.options[this.selectedIndex].value);
	}">
		${jumpPage}
	</select> 页				  </td>
	    </tr>
	  </table>
</div>