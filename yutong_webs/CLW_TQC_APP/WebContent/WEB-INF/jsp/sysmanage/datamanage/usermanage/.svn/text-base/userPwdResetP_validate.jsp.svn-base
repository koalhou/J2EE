<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
jQuery(function() {
	jQuery('#content').mk_autoresize({
        height_include: '#main',
        height_offset: 5 
      });
});

  /** 密码验证 **/
  function checkNewPwd(){
    var elm = $('password');
    if(Mat.checkPWDText(elm)){
      Mat.showSucc(elm);
      return true;
	}else{
      Wit.showErr(elm, "密码为6-15位数字和字母以及符号的组合，不能含有空格");
      return false;
	}
  }
  
  /** 确认密码验证 **/
  function checkConfirmPwd(){
    var elm = $('confirmPassword');
    var confirm=$('password');

    if(Mat.checkRequired(elm)){
      if(elm.value==confirm.value){
        Mat.showSucc(elm);
        return true;
      } else {
        Wit.showErr(elm, "密码输入不一致！");
        return false;
      }	
    } else {
      Wit.showErr(elm, "请填写");
      return false;
    }
  }

  /** 初始化样式 **/
  function setFormMessage() {
	Mat.setDefault($('password'),'<span class="noticeMsg">*</span>');
    Mat.setDefault($('confirmPassword'),'<span class="noticeMsg">*</span>');
  }

  /** 加载事件 **/
  function setFormEvent() {
    $('password').onkeyup = checkNewPwd;
    $('password').onblur = checkNewPwd;
    $('confirmPassword').onkeyup = checkConfirmPwd;
    $('confirmPassword').onblur = checkConfirmPwd;
  }

  /** 重置密码 **/
  function submitForm() {
	//trimAllObjs();
    var f1 = checkNewPwd();
    var f2 = checkConfirmPwd();
	if (f1 && f2) {
      if(confirm("确定进行重置密码操作么？")) {
	    Wit.commitAll($('userReset_form'));
      }
	} else  {
	  return false;
	}
  }

  /** 取消操作 **/
  function goBack(url) {
    Wit.goBack(url);
  }
  
  window.addEvent('domready', setFormEvent);
  window.addEvent('domready', setFormMessage);
</script>