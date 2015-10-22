<div id="pageCtrl">
	  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	      <td width="25%" align="left">每页显示
            <select name="detailPageSize" class="input60" onchange="if (this.options[this.selectedIndex].value != '') {
		location = '${url}?<#if detailNumParam != "">${detailNumParam}&</#if>detailPage=${detailPage}&detailPageSize=' + this.options[this.selectedIndex].value;
	}">
		${detailNumPage}
		
	</select>条  共${detailTotal} 条</td>	
	      <td width="50%" align="center">
		    <div id="gotoPage">
              <table><tr>
              	<td><#if detailFirstUrl != ""><a href="${detailFirstUrl}" class="btnFirst"></a><#else><a class="btnFirstDisable" style="cursor: default"></a></#if></td>
                <td><#if detailPrevUrl != ""><a href="${detailPrevUrl}" class="btnPrevious"></a><#else><a class="btnPreviousDisable" style="cursor: default"></a></#if></td>
                <td>第 ${detailPage}/${detailTotalPageCount} 页</td>
                <td><#if detailNextUrl != ""><a href="${detailNextUrl}" class="btnNext2"></a><#else><a class="btnNext2Disable" style="cursor: default"></a></#if></td>
                <td><#if detailLastUrl != ""><a href="${detailLastUrl}" class="btnLast"></a><#else><a class="btnLastDisable" style="cursor: default"></a></#if></td>
              </tr></table>
            </div>				  </td>
	      <td width="25%" align="right">跳转到
            <select name="detailPage" class="input60" onchange="if (this.options[this.selectedIndex].value != '') {
		location = '${url}?<#if param != "">${param}&</#if>detailPage=' + this.options[this.selectedIndex].value;
	}">
		${detailJumpPage}
	</select> 页				  </td>
	    </tr>
	  </table>
</div>