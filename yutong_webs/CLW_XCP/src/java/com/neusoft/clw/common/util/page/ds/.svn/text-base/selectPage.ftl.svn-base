<div id="pageCtrl">
	  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	      <td width="25%" align="left">每页显示
            <select name="selectPageSize" class="input60" onchange="if (this.options[this.selectedIndex].value != '') {
		location = '${url}?<#if selectNumParam != "">${selectNumParam}&</#if>selectPage=${selectPage}&selectPageSize=' + this.options[this.selectedIndex].value;
	}">
		${selectNumPage}
		
	</select>条  共${selectTotal} 条</td>	
	      <td width="50%" align="center">
		    <div id="gotoPage">
              <table><tr>
              	<td><#if selectFirstUrl != ""><a href="${selectFirstUrl}" class="btnFirst"></a><#else><a class="btnFirstDisable" style="cursor: default"></a></#if></td>
                <td><#if selectPrevUrl != ""><a href="${selectPrevUrl}" class="btnPrevious"></a><#else><a class="btnPreviousDisable" style="cursor: default"></a></#if></td>
                <td>第 ${selectPage}/${selectTotalPageCount} 页</td>
                <td><#if selectNextUrl != ""><a href="${selectNextUrl}" class="btnNext2"></a><#else><a class="btnNext2Disable" style="cursor: default"></a></#if></td>
                <td><#if selectLastUrl != ""><a href="${selectLastUrl}" class="btnLast"></a><#else><a class="btnLastDisable" style="cursor: default"></a></#if></td>
              </tr></table>
            </div>				  </td>
	      <td width="25%" align="right">跳转到
            <select name="selectPage" class="input60" onchange="if (this.options[this.selectedIndex].value != '') {
		location = '${url}?<#if param != "">${param}&</#if>selectPage=' + this.options[this.selectedIndex].value;
	}">
		${selectJumpPage}
	</select> 页				  </td>
	    </tr>
	  </table>
</div>