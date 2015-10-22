<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
	

	function checkStartTime() {
		var start_time = $('start_time');
		if (!Mat.checkRequired(start_time))
			return false;
		if (!Mat
				.checkLength(
						start_time,
						32,
						'<s:text name="ocktimeset.start_time"/>' + '<s:text name="val.feild.overlength"/>' + '32'))
			return false;
		return true;
	}
	
	function checkEndTime() {
		var end_time = $('end_time');
		if (!Mat.checkRequired(end_time))
			return false;
		if (!Mat
				.checkLength(
						end_time,
						32,
						'<s:text name="ocktimeset.end_time"/>' + '<s:text name="val.feild.overlength"/>' + '32'))
			return false;
		return true;
	}

	function checkstarendTime(){
		var start_time = document.getElementById('start_time');
		var starttime=document.getElementById('start_time').value;
		var endtime=document.getElementById('end_time').value;
		var start =starttime.replace(/-/g,"/");
		var end=endtime.replace(/-/g,"/");
		var startdate=new Date(start);
		var enddate=new Date(end);
		if (startdate>enddate)
        {
		   Wit.showErr(start_time, "<s:property value="getText('ocktime.startend.time')" />");
           return false;
        }
        return true;

		
	}

	function check_time_code() {
		var check_time_code = $('check_time_code');
		if (!Mat.checkRequired(check_time_code))
			return false;
		return true;
	}

	/** 初始化样式 **/
	function setFormMessage() {

	}

	/** 加载事件 **/
	function setFormEvent() {
		$('start_time').onkeyup = checkStartTime;
		$('end_time').onkeyup = checkEndTime;

		
	}

	/** 检查月度设置唯一性 **/
	  function checkCodeUnique(){
	    var enid=document.getElementById("enid").value;
	    var check_time_code = $('check_time_code');
	    DWREngine.setAsync(false);
		var ret = false; 
		Ocktimeset.checkCodeIdUnique(check_time_code.value, enid, callBackFun); 
		function callBackFun(data)
		{
		  ret = data;
		}   
		DWREngine.setAsync(true);
		if(ret) {
		  Wit.showErr(check_time_code, "<s:property value="getText('codeid.exist')" />");
		  return false;
		} else {
		  return true;
		}
	  }
	
	function submitForm() {
		var f1=  checkStartTime();
		var f2= checkEndTime();
		var f3=check_time_code();
		var f4=checkCodeUnique();
		var f5=checkstarendTime();
		if (f1&&f2&&f3&&f4) {
			if(f5){
				var form = document.getElementById('add_ocktimesetform');
				Wit.commitAll(form);
			}else{
				return false;
			}	
		} else {
			return false;
		}
	}

	function submitalterForm(){
		var f1=  checkStartTime();
		var f2= checkEndTime();
		var f3=check_time_code();
		if (f1&&f2&&f3) {
			var f4=checkstarendTime();
			if(f4){
				var oldcode= document.getElementById("oldocktimecode").value;
				var code=document.getElementById("check_time_code").value;
				if(oldcode != code){
					var f5=checkCodeUnique();
					if(f5){
						var form = document.getElementById('alter_ocktimesetform');
						Wit.commitAll(form);
					}else{
						return false;
					}
				}else{
					var form = document.getElementById('alter_ocktimesetform');
					Wit.commitAll(form);
				}
			}else{
				return false;
			}
		} else {
			return false;
		}
	}

	/** 车主类型用户不需要关联企业 **/
	function changeUserType() {

	}

	function goBack(url) {
		Wit.goBack(url);
	}

	window.addEvent('domready', setFormEvent);
	window.addEvent('domready', setFormMessage);
</script>