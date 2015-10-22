<#if (actionMessages?exists && actionMessages?size > 0)>
<#list actionMessages as msg>
<li class=success>${msg}</li>
</#list>
</#if>