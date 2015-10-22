/*
 *弹出tip提示组件。
 *依赖库：jquery,poshytip,GlareTpl
 *在元素上增加_ubtip自定义属性，将产生提示。_ubtip的值即为tipcontent的key值；
 *_ubtip_iconshow:设置是否显示提示图标，如果不显示，则直接将mouseover事件附加在元素上，否则附加在提示图标上。
 */
(function($) {

  var HelpTip = window.HelpTip = function() {
    return {
        tipContent: {
        	  /*首页*/
        	                                    index_tip1: "账号已发放，允许登录安芯校车智能管理系统的企业客户数",
        	                                    index_tip2: "已安装安芯终端设备，且在平台上注册成功的校车总数",
        	        
        	  /*企业活跃度*/
        	        /*车辆总数*/                ent_vehiclecnt: "已安装安芯终端设备，且在平台上注册成功的校车总数",
        	        /*开通企业客户数*/          ent_openentcnt: "账号已发放，允许登录安芯校车智能管理系统的企业客户数",
        	        /*活跃企业客户数*/          ent_acentcnt: "开通满1个月以上的企业客户且\"工作日日均访问次数\"要求达到1个以上的企业客户数量",
        	        /*企业客户活跃度*/          ent_entac:"活跃企业客户数/开通企业客户数",
        	        /*总访问次数：*/            ent_toatl:"全部开通企业在当前日期区间内累计访问的次数",
        	        /*日均访问次数：*/          ent_dayvis:"总访问次数/日期区间内工作日天数",

        	  /*单个企业：*/
        	        /*车辆总数：*/              ent_sin_vehicletotal: "已安装安芯终端设备，且在平台上注册成功的校车总数",
        	        /*总访问次数：*/            ent_sin_total:"当前日期区间内累计访问的次数",
        	        /*日均访问次数：*/          ent_sin_dayvis:"总访问次数/日期区间内工作日天数",
        	        /*上午访问次数：*/           ent_sin_amvis:"当前日期区间内上午访问次数总和，其中1天内06:00-12:00有访问记为1,访问多次不累加",
        	        /*下午访问次数：*/           ent_sin_pmvis:"当前日期区间内下午访问次数总和，其中1天内12:00-20:00有访问记为1, 访问多次不累加",
        	        /*晚上访问次数：*/           ent_sin_nivis:"当前日期区间内晚上访问次数总和，其中1天内00:00-06:00或20:00-24:00有访问记为2, 访问多次不累加",
        	        /*追加访问次数：*/           ent_sin_addvis:"1天内上午、下午、晚上两个连续区间段均有访问追加1，三个连续区间段均有访问追加3",
        	        /*是否活跃：*/               ent_sin_isac:"日均访问次数小于1不活跃，大于等于1活跃",
        	  /*服务活跃度*/
        	        /*总访问次数：*/            srv_total:"当前日期区间内各个服务访问次数累加",
        	        /*总访问企业数：*/           srv_totalent:"当前日期区间内开通企业中访问过至少一个服务的企业个数",
        	        /*企业访问天次：*/           srv_entdayvis:"当前日期区间内每个服务的企业访问天次之和，其中1个企业1天内有访问过任何1个服务记1个企业访问天次",
        	        /*当前开通企业数：*/          srv_curopenent:"账号已发放，允许登录安芯校车智能管理系统的企业客户数",
        	        /*符合基本要求服务数/比例：*/   srv_basereq:"符合基本要求的服务需满足正式发布且企业访问天次数超过100次",
        	        /*符合质量要求服务数/比例：*/   srv_quareq:"符合质量要求服务需满足在符合基本要求的基础上，单个服务连续3个月的活跃度超过50%",
        	  /*单个服务：*/
        	        /*总访问次数：*/                srv_sin_total:"当前日期区间内访问该服务的访问次数",
        	        /*总访问企业数：*/             srv_sin_totalent:"当前日期区间内开通企业中访问过该服务的企业个数",
        	        /*当前开通企业数：*/            srv_sin_curopenent:"账号已发放，允许登录安芯校车智能管理系统的企业客户数",
        	        /*是否符合基本要求：*/         srv_sin_basereq:"符合基本要求的服务需满足正式发布且企业访问天次数超过100次",
        	        /*是否符合质量要求：*/         srv_sin_quareq:"符合质量要求服务需满足在符合基本要求的基础上，单个服务连续3个月的活跃度超过50%",
        	        /*服务上线时间：*/              srv_sin_publishtime:"服务正式发布上线时间",
        	  /*被动活跃度*/
        	        /*推送企业数：*/                pas_pushent:"各个推送的推送企业数累加",
        	        /*访问企业数：*/                pas_vent:"各个推送的访问企业数累加",
        	        /*访问次数：*/                   pas_vcnt:"各个推送的访问次数累加",
        	        /*访问比例：*/                   pas_vrate:"访问企业数/推送企业数",
        	  /*单个推送*/
        	        /*推送企业数：*/                pas_sin_pushent:"可以接收到该条推送的企业的数量之和",
        	        /*访问企业数：*/                pas_sin_vent:"有用户访问该条推送的企业的数量之和",
        	        /*访问次数：*/                   pas_sin_vcnt:"各个企业在当前日期区间段内访问该条推送的访问次数之和",
        	        /*访问比例：*/                   pas_sin_vrate:"访问企业数/推送企业数"

        	      },
      ahtml: "<a href='javascript:void(0)' class='ubhelp' tip_key='[[key]]'></a>",
      initTip: function() {
        var that = this;
        $.each($("*[_ubtip]"), function(i, val) {
          //var i=$
          var item = $(val);
          var showicon = item.attr("_ubtip_iconshow")|| "1";

          var key = item.attr("_ubtip");
          //var html = that.ahtml.replace("[[key]]", key);
          var html =GlareTpl.parse(that.ahtml,{key:key});
          var eventele = item;
          if (showicon == "1") {
            item.append(html);
            eventele = $("*[tip_key]", item);
            key = eventele.attr("tip_key");
          } else {
            eventele = item;

          }

          eventele.poshytip({
            content: that.getTipContent(key),
            followCursor: false,
            slide: false
          });
        });

      },
      getTipContent: function(key) {
          var text=this.tipContent[key] || '无提示内容。';
          return text;
      }


    }
  }();

})(jQuery);