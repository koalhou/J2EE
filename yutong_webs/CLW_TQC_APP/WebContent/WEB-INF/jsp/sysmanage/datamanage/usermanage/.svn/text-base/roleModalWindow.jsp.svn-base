<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<title><s:text name="common.title" /></title>
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/global.css'/>">
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/list.css'/>">
</head>
<body style="width:480px; min-width:480px;">
<div class="reportPlan">
<input type="hidden" id="roleId"/>
<input type="hidden" id="roleName"/>
 <div class="middletanchu">
			  <div class="msgBoxtanchu">
                  <div class="msgTitle">选择角色</div>
				 
                  <div class="msgCententtanchu">
                    <table width="100%" border="0" cellspacing="2" cellpadding="2">
                      <tr>
                        <td>
                       
						<s:select id="selectLeftos" name="selectLeftos" list="roleMap"  size="10" multiple="true" cssStyle="width:200px;" ondblclick="javascript:moveSelect(document.getElementById('selectLeftos'), document.getElementById('selectRightos'),'os');" />
					
						</td>
						<td class="tbQuerySel">
						<a href="#" class="sbutton" onclick="javascript:moveSelect(document.getElementById('selectLeftos'), document.getElementById('selectRightos'),'os');">></a>
						
						<br>
						<a href="#" class="sbutton" onclick="javascript:deleteSelect(document.getElementById('selectLeftos'), document.getElementById('selectRightos'),'os');"><</a>
						
						</td>
						<td>
						<s:select id="selectRightos" name="selectRightos" multiple="multiple" list="selectMap" size="10" cssStyle="width: 200px;" ondblclick="javascript:deleteSelect(document.getElementById('selectLeftos'), document.getElementById('selectRightos'),'os');"></s:select>
						<select id="ostype" name="selectveh" multiple="multiple" size="10" style="display: none" class="s"></select>
						</td>
                      </tr>
                      
                    </table>
                  </div>
				   <!-- <div class="btnBar">     			    
                     <table align="center" width="100" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					    <td align="right"><a href="#" class="sbutton" onclick="closewindow()">确定</a></td>
					  </tr>
					</table>
					
                  </div>-->
                </div>
				</div>


</div>
<script type="text/javascript">
function moveSelect(sl1, sl2, type) {
           if( sl1 == undefined || sl1 == null) return;
           if( sl2 == undefined || sl2 == null) return;
           for (var i = 0; i < sl1.length; ++i){
              if (sl1.options[i].selected) {
              
              if (findOption(sl1.options[i].value, sl2))
              { 
                 sl1.options[i] = null;
                 --i;
                 continue;
              }
             var pp=document.getElementById(type+'type');
               moveOption(sl1, sl2, i, false);
               moveOption(sl1, pp, i, true);              
                --i;                
               }               
             }
      }
  
  
  
	  function deleteSelect(sl1, sl2, type) {
	  if( sl1 == undefined || sl1 == null) return;
	  if( sl2 == undefined || sl2 == null) return;
	  for (var i = 0; i < sl2.length; ++i)
	      if (sl2.options[i].selected) {
	      
	          if (findOption(sl2.options[i].value, sl1)) {
	              sl2.options[i] = null;
	              --i;
	              continue;
	          }
	          
	          moveOption(sl2, sl1, i, true);
	          document.getElementById(type+'type').options[i]=null;
	          --i;
	     }     
	  }

	  function findOption(op, sl) {
		   if( sl == undefined || sl == null) return false;
	      for (var i = 0; i < sl.length; ++i)
	          if (sl.options[i].value == op) return true;
	          
	      return false;
	  }

     function moveOption(sl1, sl2, i, flagDel) {
      if( sl1 == undefined || sl1 == null) return;
      if( sl2 == undefined || sl2 == null) return;
      if (i < sl1.length) {
          var newOption = new Option(sl1.options[i].text, sl1.options[i].value);
          newOption.selected=true;
          sl2.options.add(newOption);
			newOption.title=sl1.options[i].title;
          if (flagDel) sl1.options[i] = null;
      }
     } 


     function closewindow(){

    	var select =  document.getElementById("selectRightos");
		
		var rname = "";
		var rid = "";
		
    	if(select != null){
    		var arr = new Array(select.length);
    		
			for(var i = 0; i < select.length; ++i){
				var str = select.options[i].value + "," + select.options[i].text;
				arr[i] = str;
				if(i == select.length-1){
					rid += select.options[i].value;
					rname += select.options[i].text;
				}
				else{
					rid += select.options[i].value+",";
					rname += select.options[i].text + ",";
				}

			}
			document.getElementById("roleName").value = rname;
			document.getElementById("roleId").value = rid;
			//window.returnValue = arr;
        }
    	else{
    		document.getElementById("roleName").value = rname;
			document.getElementById("roleId").value = rid;
    		//window.returnValue = null;
        }

		
		//window.close();
    	 
     }
</script>
</body>
</html>