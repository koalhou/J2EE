<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/wit/tools.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/validate/validation.js' />"></script>
<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/lib/mootools/mootools-release-1.11.js' />"></script>
<script type="text/javascript">
  /** 不良驾驶名 **/
  function checkDef_name() {
    var elm = $('def_name');
    if(Mat.checkRequired(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "<s:property value="getText('illdrive.info.def_name')"/><s:property value="getText('must.need')"/>");
      return false;
	}
  }
/**
 * 数字验证
 */
	function checkOverspd_val(){
		var elm = $('overspd_val');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkOverspd_time(){
		var elm = $('overspd_time');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear2_spd(){
		var elm = $('gear2_spd');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkEoil_spd(){
		var elm = $('eoil_spd');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkEoil_rpm(){
		var elm = $('eoil_rpm');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkLidl_spd(){
		var elm = $('lidl_spd');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkLidl_rpm(){
		var elm = $('lidl_rpm');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkLidl_time(){
		var elm = $('lidl_time');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkIdlair_spd(){
		var elm = $('idlair_spd');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkIdlair_rpm(){
		var elm = $('idlair_rpm');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkIdlair_time(){
		var elm = $('idlair_time');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkQgear_rpm(){
		var elm = $('qgear_rpm');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkQgear_ratio(){
		var elm = $('qgear_ratio');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkTired_time(){
		var elm = $('tired_time');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkEgear_spd(){
		var elm = $('egear_spd');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkEgear_ratio(){
		var elm = $('egear_ratio');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear0_spd_l(){
		var elm = $('gear0_spd_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear0_spd_u(){
		var elm = $('gear0_spd_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear0_rpm_l(){
		var elm = $('gear0_rpm_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear0_rpm_u(){
		var elm = $('gear0_rpm_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear1_spd_l(){
		var elm = $('gear1_spd_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear1_spd_u(){
		var elm = $('gear1_spd_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear1_rpm_l(){
		var elm = $('gear1_rpm_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear1_rpm_u(){
		var elm = $('gear1_rpm_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear2_spd_l(){
		var elm = $('gear2_spd_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear2_spd_u(){
		var elm = $('gear2_spd_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear2_rpm_l(){
		var elm = $('gear2_rpm_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear2_rpm_u(){
		var elm = $('gear2_rpm_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear3_spd_l(){
		var elm = $('gear3_spd_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear3_spd_u(){
		var elm = $('gear3_spd_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear3_rpm_l(){
		var elm = $('gear3_rpm_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear3_rpm_u(){
		var elm = $('gear3_rpm_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear4_spd_l(){
		var elm = $('gear4_spd_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear4_spd_u(){
		var elm = $('gear4_spd_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear4_rpm_l(){
		var elm = $('gear4_rpm_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear4_rpm_u(){
		var elm = $('gear4_rpm_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear5_spd_l(){
		var elm = $('gear5_spd_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear5_spd_u(){
		var elm = $('gear5_spd_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear5_rpm_l(){
		var elm = $('gear5_rpm_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear5_rpm_u(){
		var elm = $('gear5_rpm_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear6_spd_l(){
		var elm = $('gear6_spd_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear6_spd_u(){
		var elm = $('gear6_spd_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear6_rpm_l(){
		var elm = $('gear6_rpm_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear6_rpm_u(){
		var elm = $('gear6_rpm_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear7_spd_l(){
		var elm = $('gear7_spd_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear7_spd_u(){
		var elm = $('gear7_spd_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear7_rpm_l(){
		var elm = $('gear7_rpm_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear7_rpm_u(){
		var elm = $('gear7_rpm_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear8_spd_l(){
		var elm = $('gear8_spd_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear8_spd_u(){
		var elm = $('gear8_spd_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear8_rpm_l(){
		var elm = $('gear8_rpm_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}
	function checkGear8_rpm_u(){
		var elm = $('gear8_rpm_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}

	function checkGreenAreaRpmLow() {
		var elm = $('greenarea_rpm_l');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}

	function checkGreenAreaRpmUp() {
		var elm = $('greenarea_rpm_u');
		if(!Mat.checkDigital(elm)&&elm.value!=''){
			Wit.showErr(elm, "<s:property value="getText('js.text.show.neednumber')" />");
			return false;
		}
		Mat.showSucc(elm);
		return true;
	}

 
  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('def_name'),'<span class="noticeMsg">*</span>');
	Mat.setDefault($('overspd_val'),'');
	Mat.setDefault($('overspd_time'),'');
	Mat.setDefault($('gear2_spd'),'');
	Mat.setDefault($('eoil_spd'),'');
	Mat.setDefault($('eoil_rpm'),'');
	Mat.setDefault($('lidl_spd'),'');
	Mat.setDefault($('lidl_rpm'),'');
	Mat.setDefault($('lidl_time'),'');
	Mat.setDefault($('idlair_spd'),'');
	Mat.setDefault($('idlair_rpm'),'');
	Mat.setDefault($('idlair_time'),'');
	Mat.setDefault($('qgear_rpm'),'');
	Mat.setDefault($('qgear_ratio'),'');
	Mat.setDefault($('tired_time'),'');
	Mat.setDefault($('egear_spd'),'');
	Mat.setDefault($('egear_ratio'),'');
	Mat.setDefault($('gear0_spd_l'),'');
	Mat.setDefault($('gear0_spd_u'),'');
	Mat.setDefault($('gear0_rpm_l'),'');
	Mat.setDefault($('gear0_rpm_u'),'');
	Mat.setDefault($('gear1_spd_l'),'');
	Mat.setDefault($('gear1_spd_u'),'');
	Mat.setDefault($('gear1_rpm_l'),'');
	Mat.setDefault($('gear1_rpm_u'),'');
	Mat.setDefault($('gear2_spd_l'),'');
	Mat.setDefault($('gear2_spd_u'),'');
	Mat.setDefault($('gear2_rpm_l'),'');
	Mat.setDefault($('gear2_rpm_u'),'');
	Mat.setDefault($('gear3_spd_l'),'');
	Mat.setDefault($('gear3_spd_u'),'');
	Mat.setDefault($('gear3_rpm_l'),'');
	Mat.setDefault($('gear3_rpm_u'),'');
	Mat.setDefault($('gear4_spd_l'),'');
	Mat.setDefault($('gear4_spd_u'),'');
	Mat.setDefault($('gear4_rpm_l'),'');
	Mat.setDefault($('gear4_rpm_u'),'');
	Mat.setDefault($('gear5_spd_l'),'');
	Mat.setDefault($('gear5_spd_u'),'');
	Mat.setDefault($('gear5_rpm_l'),'');
	Mat.setDefault($('gear5_rpm_u'),'');
	Mat.setDefault($('gear6_spd_l'),'');
	Mat.setDefault($('gear6_spd_u'),'');
	Mat.setDefault($('gear6_rpm_l'),'');
	Mat.setDefault($('gear6_rpm_u'),'');
	Mat.setDefault($('gear7_spd_l'),'');
	Mat.setDefault($('gear7_spd_u'),'');
	Mat.setDefault($('gear7_rpm_l'),'');
	Mat.setDefault($('gear7_rpm_u'),'');
	Mat.setDefault($('gear8_spd_l'),'');
	Mat.setDefault($('gear8_spd_u'),'');
	Mat.setDefault($('gear8_rpm_l'),'');
	Mat.setDefault($('gear8_rpm_u'),'');
	Mat.setDefault($('greenarea_rpm_l'),'');
	Mat.setDefault($('greenarea_rpm_u'),'');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('def_name').onkeyup = checkDef_name;
    $('def_name').onblur = checkDef_name;
    $('overspd_val').onkeyup = checkOverspd_val;
    $('overspd_val').onblur = checkOverspd_val;
    $('overspd_time').onkeyup = checkOverspd_time;
    $('overspd_time').onblur = checkOverspd_time;
    $('gear2_spd').onkeyup = checkGear2_spd;
    $('gear2_spd').onblur = checkGear2_spd;
    $('eoil_spd').onkeyup = checkEoil_spd;
    $('eoil_spd').onblur = checkEoil_spd;
    $('eoil_rpm').onkeyup = checkEoil_rpm;
    $('eoil_rpm').onblur = checkEoil_rpm;
    $('lidl_spd').onkeyup = checkLidl_spd;
    $('lidl_spd').onblur = checkLidl_spd;
    $('lidl_rpm').onkeyup = checkLidl_rpm;
    $('lidl_rpm').onblur = checkLidl_rpm;
    $('lidl_time').onkeyup = checkLidl_time;
    $('lidl_time').onblur = checkLidl_time;
    $('idlair_spd').onkeyup = checkIdlair_spd;
    $('idlair_spd').onblur = checkIdlair_spd;
    $('idlair_rpm').onkeyup = checkIdlair_rpm;
    $('idlair_rpm').onblur = checkIdlair_rpm;
    $('idlair_time').onkeyup = checkIdlair_time;
    $('idlair_time').onblur = checkIdlair_time;
    $('qgear_rpm').onkeyup = checkQgear_rpm;
    $('qgear_rpm').onblur = checkQgear_rpm;
    $('qgear_ratio').onkeyup = checkQgear_ratio;
    $('qgear_ratio').onblur = checkQgear_ratio;
    $('tired_time').onkeyup = checkTired_time;
    $('tired_time').onblur = checkTired_time;
    $('egear_spd').onkeyup = checkEgear_spd;
    $('egear_spd').onblur = checkEgear_spd;
    $('egear_ratio').onkeyup = checkEgear_ratio;
    $('egear_ratio').onblur = checkEgear_ratio;
    $('gear0_spd_l').onkeyup = checkGear0_spd_l;
    $('gear0_spd_l').onblur = checkGear0_spd_l;
    $('gear0_spd_u').onkeyup = checkGear0_spd_u;
    $('gear0_spd_u').onblur = checkGear0_spd_u;
    $('gear0_rpm_l').onkeyup = checkGear0_rpm_l;
    $('gear0_rpm_l').onblur = checkGear0_rpm_l;
    $('gear0_rpm_u').onkeyup = checkGear0_rpm_u;
    $('gear0_rpm_u').onblur = checkGear0_rpm_u;
    $('gear1_spd_l').onkeyup = checkGear1_spd_l;
    $('gear1_spd_l').onblur = checkGear1_spd_l;
    $('gear1_spd_u').onkeyup = checkGear1_spd_u;
    $('gear1_spd_u').onblur = checkGear1_spd_u;
    $('gear1_rpm_l').onkeyup = checkGear1_rpm_l;
    $('gear1_rpm_l').onblur = checkGear1_rpm_l;
    $('gear1_rpm_u').onkeyup = checkGear1_rpm_u;
    $('gear1_rpm_u').onblur = checkGear1_rpm_u;
    $('gear2_spd_l').onkeyup = checkGear2_spd_l;
    $('gear2_spd_l').onblur = checkGear2_spd_l;
    $('gear2_spd_u').onkeyup = checkGear2_spd_u;
    $('gear2_spd_u').onblur = checkGear2_spd_u;
    $('gear2_rpm_l').onkeyup = checkGear2_rpm_l;
    $('gear2_rpm_l').onblur = checkGear2_rpm_l;
    $('gear2_rpm_u').onkeyup = checkGear2_rpm_u;
    $('gear2_rpm_u').onblur = checkGear2_rpm_u;
    $('gear3_spd_l').onkeyup = checkGear3_spd_l;
    $('gear3_spd_l').onblur = checkGear3_spd_l;
    $('gear3_spd_u').onkeyup = checkGear3_spd_u;
    $('gear3_spd_u').onblur = checkGear3_spd_u;
    $('gear3_rpm_l').onkeyup = checkGear3_rpm_l;
    $('gear3_rpm_l').onblur = checkGear3_rpm_l;
    $('gear3_rpm_u').onkeyup = checkGear3_rpm_u;
    $('gear3_rpm_u').onblur = checkGear3_rpm_u;
    $('gear4_spd_l').onkeyup = checkGear4_spd_l;
    $('gear4_spd_l').onblur = checkGear4_spd_l;
    $('gear4_spd_u').onkeyup = checkGear4_spd_u;
    $('gear4_spd_u').onblur = checkGear4_spd_u;
    $('gear4_rpm_l').onkeyup = checkGear4_rpm_l;
    $('gear4_rpm_l').onblur = checkGear4_rpm_l;
    $('gear4_rpm_u').onkeyup = checkGear4_rpm_u;
    $('gear4_rpm_u').onblur = checkGear4_rpm_u;
    $('gear5_spd_l').onkeyup = checkGear5_spd_l;
    $('gear5_spd_l').onblur = checkGear5_spd_l;
    $('gear5_spd_u').onkeyup = checkGear5_spd_u;
    $('gear5_spd_u').onblur = checkGear5_spd_u;
    $('gear5_rpm_l').onkeyup = checkGear5_rpm_l;
    $('gear5_rpm_l').onblur = checkGear5_rpm_l;
    $('gear5_rpm_u').onkeyup = checkGear5_rpm_u;
    $('gear5_rpm_u').onblur = checkGear5_rpm_u;
    $('gear6_spd_l').onkeyup = checkGear6_spd_l;
    $('gear6_spd_l').onblur = checkGear6_spd_l;
    $('gear6_spd_u').onkeyup = checkGear6_spd_u;
    $('gear6_spd_u').onblur = checkGear6_spd_u;
    $('gear6_rpm_l').onkeyup = checkGear6_rpm_l;
    $('gear6_rpm_l').onblur = checkGear6_rpm_l;
    $('gear6_rpm_u').onkeyup = checkGear6_rpm_u;
    $('gear6_rpm_u').onblur = checkGear6_rpm_u;
    $('gear7_spd_l').onkeyup = checkGear7_spd_l;
    $('gear7_spd_l').onblur = checkGear7_spd_l;
    $('gear7_spd_u').onkeyup = checkGear7_spd_u;
    $('gear7_spd_u').onblur = checkGear7_spd_u;
    $('gear7_rpm_l').onkeyup = checkGear7_rpm_l;
    $('gear7_rpm_l').onblur = checkGear7_rpm_l;
    $('gear7_rpm_u').onkeyup = checkGear7_rpm_u;
    $('gear7_rpm_u').onblur = checkGear7_rpm_u;
    $('gear8_spd_l').onkeyup = checkGear8_spd_l;
    $('gear8_spd_l').onblur = checkGear8_spd_l;
    $('gear8_spd_u').onkeyup = checkGear8_spd_u;
    $('gear8_spd_u').onblur = checkGear8_spd_u;
    $('gear8_rpm_l').onkeyup = checkGear8_rpm_l;
    $('gear8_rpm_l').onblur = checkGear8_rpm_l;
    $('gear8_rpm_u').onkeyup = checkGear8_rpm_u;
    $('gear8_rpm_u').onblur = checkGear8_rpm_u;
    $('greenarea_rpm_l').onkeyup = checkGreenAreaRpmLow;
    $('greenarea_rpm_l').onblur = checkGreenAreaRpmLow;
    $('greenarea_rpm_u').onkeyup = checkGreenAreaRpmUp;
    $('greenarea_rpm_u').onblur = checkGreenAreaRpmUp;
        
  }

  function submitPostFrom() {
	  var f1 = checkDef_name();
	  var f2 = checkOverspd_val();
	  var f3 = checkOverspd_time();
	  var f4 = checkGear2_spd();
	  var f5 = checkEoil_spd();
	  var f6 = checkEoil_rpm();
	  var f7 = checkLidl_spd();
	  var f8 = checkLidl_rpm();
	  var f9 = checkLidl_time();
	  var f10 = checkIdlair_spd();
	  var f11 = checkIdlair_rpm();
	  var f12 = checkIdlair_time();
	  var f13 = checkQgear_rpm();
	  var f14 = checkQgear_ratio();
	  var f15 = checkTired_time();
	  var f16 = checkEgear_spd();
	  var f17 = checkEgear_ratio();
	  var f18 = checkGear0_spd_l();
	  var f19 = checkGear0_spd_u();
	  var f20 = checkGear0_rpm_l();
	  var f21 = checkGear0_rpm_u();
	  var f22 = checkGear1_spd_l();
	  var f23 = checkGear1_spd_u();
	  var f24 = checkGear1_rpm_l();
	  var f25 = checkGear1_rpm_u();
	  var f26 = checkGear2_spd_l();
	  var f27 = checkGear2_spd_u();
	  var f28 = checkGear2_rpm_l();
	  var f29 = checkGear2_rpm_u();
	  var f30 = checkGear3_spd_l();
	  var f31 = checkGear3_spd_u();
	  var f32 = checkGear3_rpm_l();
	  var f33 = checkGear3_rpm_u();
	  var f34 = checkGear4_spd_l();
	  var f35 = checkGear4_spd_u();
	  var f36 = checkGear4_rpm_l();
	  var f37 = checkGear4_rpm_u();
	  var f38 = checkGear5_spd_l();
	  var f39 = checkGear5_spd_u();
	  var f40 = checkGear5_rpm_l();
	  var f41 = checkGear5_rpm_u();
	  var f42 = checkGear6_spd_l();
	  var f43 = checkGear6_spd_u();
	  var f44 = checkGear6_rpm_l();
	  var f45 = checkGear6_rpm_u();
	  var f46 = checkGear7_spd_l();
	  var f47 = checkGear7_spd_u();
	  var f48 = checkGear7_rpm_l();
	  var f49 = checkGear7_rpm_u();
	  var f50 = checkGear8_spd_l();
	  var f51 = checkGear8_spd_u();
	  var f52 = checkGear8_rpm_l();
	  var f53 = checkGear8_rpm_u();
	  var f54 = checkGreenAreaRpmLow();
	  var f55 = checkGreenAreaRpmUp();
	  
		if (f1&&f2&&f3&&f4&&f5&&f6&&f7&&f8&&f9&&f10&&f11&&f12&&f13&&f14&&f15&&f16&&f17&&f18&&f19&&f20&&f21&&f22&&f23&&f24&&f25&&f26&&f27&&f28&&f29&&f30&&f31&&f32&&f33&&f34&&f35&&f36&&f37&&f38&&f39&&f40&&f41&&f42&&f43&&f44&&f45&&f46&&f47&&f48&&f49&&f50&&f51&&f52&&f53&&f54&&f55) {
			Wit.commitAll($('clwForm'));
		}
		else  {
			  return false;
		}
	}
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>