<#if (actionErrors?exists && actionErrors?size > 0)>
<#list actionErrors as err>
<li class=error>${err}</li>
</#list>
</#if>