		var oilMassMonitorObj;
		var vehicleList;
		var isDetailVehicle = new Map();
		//ztree的回调函数
		function mytreeonClick(carList) {
			
		}
		function getSelectedCars(){
			var tempList = new Array();
			var treeObj = jQuery.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			for ( var i = 0, len = nodes.length; i < len; i++) {
				if ("pIcon" != nodes[i].iconSkin) {
					tempList.push(nodes[i].id);
				}
			}
			return tempList;
		}
		function genPageSelect(cur,total){
			total=total<1?1:total;
			$('#curPage').html(total);
			var index=parseInt(cur);
//			$('#pageSelect').empty();
//			for(var i=1;i<=total;i++){
//				$('#pageSelect').append("<option value='"+i+"'>"+i+"</option>");
//			}
			$('#pageSelect').val(index);
			delete index;
		}
		//滑动窗口切换样式
		function sidelayerButtonControl(flag){

			if(flag==2){
				if('selected'==$('#aa2').attr('class')){
					return;
				}else{
					$('#aa2').addClass('selected');
					$('#aa6').removeClass('selected');
					popMapWin($('#popArea').attr('vehicle_code'),$('#popArea').attr('vehicle_vin'),$('#popArea').attr('vehicle_ln'),2);
				}
			}else{
				if('selected'==$('#aa6').attr('class')){
					return;
				}else{
					$('#aa2').removeClass('selected');
					$('#aa6').addClass('selected');
					popMapWin($('#popArea').attr('vehicle_code'),$('#popArea').attr('vehicle_vin'),$('#popArea').attr('vehicle_ln'),1);
				}
			}
		}
		
		var oilMassMonitor = function() {
			this.ajaxReq = null;
			this.BoardArr = [];//存放仪表盘对象数组
			this.isFresh = 0;//判定是否新生成仪表盘对象还是,刷新仪表盘数据
			this.isGetHtml = 0; //判定是否生成仪表盘HTML标签
			this.req = 0; //判断是定时器请求数据(1),或是手动点击请求数据(0)
		};
		oilMassMonitor.prototype = {
			init : function() {
				$('#emptyStr').remove();
				var that = this;
				
				var cars = getSelectedCars();
				finalVINs = cars;
				if (cars.length == 0){
					$("div[id^=car]",$("#dash")).css("display","none");
					$("#pageSelect").val("1");
					$("#curPage").html("1");
						var emptyTxt = "";
						var selType = $('#monitorType').val();
						if(selType == "all"){
							emptyTxt = "暂无车辆";
						} else if(selType == "low"){
							emptyTxt = "暂无低油量车辆";
						} else if(selType == "alarm"){
							emptyTxt = "暂无告警车辆";
						}
						$('#dash').append("<div id='emptyStr' align='center' style='margin-top: 15px;'>"+emptyTxt+"</div>");
						$('#dash').removeClass("loading");
						//去除等待图片样式
					return false;
				} else {
					$("#emptyStr",$("#dash")).remove();
				}
				if($("div[id^=car]",$("#dash")).length == 0 || $("div[id^=car]",$("#dash")).is(":visible")==false){
					$('#dash').addClass("loading");
				}
				
				//TODO 清楚缓存
				$('.car_message').each(function(i,n){
					if($(n).attr('vin')!=null){
						$(n).removeData($(n).attr('vin'));
					};
				});
				
				//判断   请求数据是否未完成,如有则停止上一次请求
				if(this.ajaxReq != null){
					this.ajaxReq.abort();
				}
				
				//每页显示车辆数
				var pageSize = parseInt($('#right_main').width() / 210) * 2;
				
				this.ajaxReq = jQuery.ajax({
							type : 'POST',
							url : "getFtlyInfoList.shtml",
							dataType : "json",
							data : {
								"vehicleVinList" : cars.toString(),
								"type":$('#monitorType').val(),
								"page" : $('#pageSelect').val(),
								"rp" : parseInt($('#right_main').width() / 210) * 2
							},
							success : function(data) {
								//设置总页数
								var pageAmount = parseInt($('#right_main').width() / 210) * 2;
								genPageSelect(data.page,Math.ceil(data.total/pageAmount));
								$("div[id*=car]").css("display","none");
								//去除等待图片样式
								$('#dash').removeClass("loading");
								
								//判断是否有车辆展示告警信息，否则全部展示油量信息
								if(isDetailVehicle.size() == 0){
									$("div[id^=car] .infoList").css("display","none");
								}
								
								//判断初次进入页面,是否生成仪表盘
								if(that.BoardArr.length == 0 || $("#dash").html().length == 0){
									that.isFresh = 1;
								} else {
									that.isFresh = 0;
									that.isGetHtml = 1;
								}
								//判断当前页显示几辆车
								if(parseInt($('#right_main').width() / 210) * 2 == data.rows.length){
									$("div[id^=car]:lt("+pageSize+")").css("display","block");
									var carArr = $("div[id^=car]");
									var carLen = $("div[id^=car]").length;
									for(var i = carLen; i > pageAmount; i--){
										$(carArr[i]).css("display","none");
									}
									delete carArr;
									delete carLen;
									
								}
								//设置分页
								for ( var i = 1, rowsLen = data.rows.length; i <= rowsLen; i++) {
									var board = data.rows[i - 1];
										//生成DOM
									var boardLen = that.BoardArr.length-1;
									if(that.BoardArr.length == 0 || boardLen < data.rows.length && $("#car"+i).length == 0){
										$('#dash').append(oilMassMonitorObj.genDeviceHtml(i,board.ftlyFlag));
									}
										oilMassMonitorObj.setDashBoard(i,board);
									//车牌号 车架号
									$('.vehicleTxt').eq(i-1).html(board.vehicle_code+"号");
									var car=$("#car" + i );
									$("#car" + i ).attr('vin',board.vinCode);
									if(!that.isGetHtml){
										oilMassMonitorObj.genInfoHtml(i);
									}
									car.find('.fuckbaby').attr('ln',board.vehicleLn);
									car.find('.fuckbaby').attr('vin',board.vinCode);
									car.find('.fuckbaby').attr('code',board.vehicle_code);
									oilMassMonitorObj.bindHandle(i);
									if(board.alarmCount=='1'){
										$('.rightArrow',car).find("img").hide();
										$('.leftArrow',car).find("img").hide();
									}
									delete board;
									delete boardLen;
									
								}
								var lessLen = 0;
								//当翻页后,当前页显示内容不够一页的数量,剩余的则隐藏
								if(parseInt($('#right_main').width() / 210) * 2 > data.rows.length){
									lessLen = data.rows.length;
									for(var i = lessLen +1; i <= pageAmount ; i++){
										$("#car"+i).css("display","none");
									}
								} 
								//展示所取得的车辆
								//lessLen = data.rows.length;
								for(var i = 1, lessLen = data.rows.length; i <= lessLen ; i++){
									$("#car"+i).css("display","block");
								}
								if(data.rows.length == 0){
									var emptyTxt = "";
									if($('#monitorType').val() == "all"){
										emptyTxt = "暂无车辆";
									} else if($('#monitorType').val() == "low"){
										emptyTxt = "暂无低油量车辆";
									} else if($('#monitorType').val() == "alarm"){
										emptyTxt = "暂无告警车辆";
									}
									$('#dash').append("<div id='emptyStr' align='center' style='margin-top: 15px;'>"+emptyTxt+"</div>");
									delete emptyTxt;
									
								}
								var vehicleVin = "";
								//判断是否需要展示告警信息
								$("div[class=car_title]").each(function(){
									if(isDetailVehicle.size() == 0){
										return false;
									}
									if($(this).parent().css("display") == "block"){
										if(isDetailVehicle.containsValue($(this).parent().attr("vin")) && $(".oilMonitorContent",$(this).parent()).css("display") == "block"){
											$(".alarmCount",$(this).parent()).trigger("click");
										} else if(isDetailVehicle.containsValue($(this).parent().attr("vin")) && $(".oilMonitorContent",$(this).parent()).css("display") == "none"){
											oilMassMonitorObj.getAlarmList($(this));
										} else if(!isDetailVehicle.containsValue($(this).parent().attr("vin")) && $(".oilMonitorContent",$(this).parent()).css("display") == "block"){
											$(".oilMonitorContent",$(this).parent()).css("display","block");
											$("div[class*=infoList]",$(this).parent()).css("display","none");
										}
										else if(!isDetailVehicle.containsValue($(this).parent().attr("vin")) && $(".oilMonitorContent",$(this).parent()).css("display") == "none"){
											$(".oilMonitorContent",$(this).parent()).css("display","none");
											$("div[class*=infoList]",$(this).parent()).css("display","none");
										}
									}
								});
								//过滤当前页面，没有的车辆
								var vehicleKeys = isDetailVehicle.keys();
								for(var i = 0, keysLen = isDetailVehicle.size(); i < keysLen;i++){
//									var isObj = $("div[id^=car]",$("#dash")).filter("vin="+vehicleKeys[i]);
									var isObj = $("div[vin="+vehicleKeys[i]+"]",$("#dash"));
//									if(isObj == null){
//										isDetailVehicle.removeByKey(vehicleKeys[i]);
//										//i = 0;
//									}
								}
									
//								delete vehicleVin;
//								delete pageAmount;
//								delete lessLen;
//								delete car;
//								delete cars;
								if(trunTimer == null){
									trunTimer = setTimeout("startRequest()",30000);
								}
							}
						});
			},
			//设置仪表盘并填写数据
			setDashBoard:function(i,board){
				var color='#FF0000';
				if (board.oilboxState == "1") {
					color='#11FF00';
				}
				var oilmass = parseFloat(board.oilboxMass);
				if(this.BoardArr[i] == null){
					this.BoardArr[i] = new JustGage({
						id: "g"+i,
						value: oilmass,
						//valueFontColor: "#ffffff",
						min: 0,
						max: Number(board.oilCapacity),
						title: board.vehicleLn,
						label: "",
						gaugeColor: "#fff",
						shadowOpacity: 1,
						levelColorsGradient:false,
						gaugeWidthScale: 0.2,
						levelColors: [color]
					});
				} else {
					this.BoardArr[i].refresh(oilmass,color,board.vehicleLn,parseFloat(board.oilCapacity));
				}
				if(board.ftlyFlag == 1){
					//$("#car"+i + " .oilMonitorContent").addClass("isNone");
					$("#car"+i + " .oilMonitorContent").css("display","none");
					//$("#car"+i + " .black").removeClass("isNone");
					$("#car"+i + " .black").css("display","block");
				} else if(board.ftlyFlag == 0){
					//$("#car"+i + " .oilMonitorContent").removeClass("isNone");
					$("#car"+i + " .oilMonitorContent").css("display","block");
					//$("#car"+i + " .black").addClass("isNone");
					$("#car"+i + " .black").css("display","none");
				}
				
				//告警条数
				if(parseInt(board.alarmCount) > 0 && board.ftlyFlag == 0){
					$(".alarmCount",$("#car"+i)).eq(0).css("display","block");
					$(".alarmCount",$("#car"+i)).eq(0).html("告警&nbsp; "+board.alarmCount);
					$(".alarmCount",$("#car"+i)).attr("total",board.alarmCount);
					$('.leftArrow',$("#car"+i)).attr("prev","0");
					$('.rightArrow',$("#car"+i)).attr("next","1");
//					$(".alarmCount",$("#car"+i)).eq(0).bind("click",function(){
//						$('.baby',$("#car"+i)).eq(0).trigger("click");
//					});
					$(".alarmCount",$("#car"+i)).eq(0).unbind("click");
					$(".alarmCount",$("#car"+i)).eq(0).toggle(function(){
							$('.baby',$("#car"+i)).eq(0).trigger("click");
						},
						function(){
							$('.infoMsg',$("#car"+i)).eq(0).trigger("click");
						}
					);
					//绑定事件
					$('.baby',$("#car"+i)).eq(0).unbind("click");
					$('.baby',$("#car"+i)).eq(0).bind("click", function(){
							var vins = $('.baby',$("#car"+i)).parent().parent().parent().attr("vin");
							if(!isDetailVehicle.containsKey(vins)){
								isDetailVehicle.put(vins,vins);								
							}
						   $('.infoList',$("#car"+i)).eq(0).show();
						   $('.oilMonitorContent',$("#car"+i)).eq(0).hide();
						   oilMassMonitorObj.getAlarmList($(this).parent().parent());
						   delete vins;
					});
				} else {
					$('.baby',$("#car"+i)).eq(0).unbind("click");
					$(".alarmCount",$("#car"+i)).eq(0).css("display","none");
					$(".alarmCount",$("#car"+i)).eq(0).html("");
				}
				// 油量 
				if(board.reportTimeString != null){
					$("#car" + i + "  .oilMass").html(
							board.oilboxMass + "L");
				} else {
					$("#car" + i + "  .oilMass").html("");
				}
				//状态
				var curState = $("#car" + i
						+ "  .curState");

				if (board.oilboxState == "1" && board.reportTimeString != null) {
					$(curState).removeClass(
							"alarmClass");
					$(curState).html("");
					$("#lowOilAlarm",$("#car"+i)).attr("src","../images/xiaocheImage/unOilLower.png");						
				} else {
					$(curState).addClass("alarmClass");
					var oilPercent = board.oilPercent;
					if(oilPercent == "null" || oilPercent == null){
						oilPercent = "0";
					}
					if(board.reportTimeString != null){
						$(curState).html("油量低(" + oilPercent
								+ "%)");
						$("#lowOilAlarm",$("#car"+i)).attr("src","../images/xiaocheImage/oilLower.png");
					} else {
						$(curState).html("今日无数据上报");
						$("#lowOilAlarm",$("#car"+i)).attr("src","../images/xiaocheImage/unOilLower.png");
					}
				}
				//时间
				$("#car" + i + "  .upTime").html(
						board.reportTimeString);
					
				
			},
			//生成仪表盘的html
			genDeviceHtml:function (i,flag){
				return "<div id=\"car"+i+"\" class=\"car_message\" vin=\"\"><div class=\"car_title\" class=\"car_div_class\">"+
				"<div class=\"alarmCount oil_msg\"  ></div><span class=\"vehicleTxt\" ></span></div><div class=\"oilMonitorContent \">"+
				"<div class=\"car_div_class\" style=\"cursor: hand;\"><div class='baby'><span id=\"g"+i+"\" class=\"justgage\"><img id='lowOilAlarm' src='../images/xiaocheImage/oilLower.png' class='oilPic'/></span><div class=\"oilMass\"></div><div class=\"curState\"></div>"+
				"<div class=\"upTime\"></div> </div><div class='maskBtnCenter fuckbaby'>油量记录</div>  </div></div>" +
				"<div class=\"car_div_class black sponsorFlip \" >"+
				"<img style=\"margin-left:20px;margin-top: 40px \" src=\"../images/xiaocheImage/alarmWu.jpg;\"/>"+
				"</div>"+
				"</div>";
			},
			//生成告警的html
			genInfoHtml:function (i){
				var infoHTML="<div class=\"car_div_class infoList\" style=\"cursor: hand;\"><div style='height:215px' ><div class='leftArrow' prev='0'> <img style='' src=\"../images/xiaocheImage/alarmleft.png\"  class=\"imgArrow\" /></div> <div class=\"infoMsg\" style=\"position: relative;float: left;width: 160px;height: 190px\">"+
				   "<div class=\"alarmTitlestore\">油量骤减告警</div><div class=\"oilstore\">异动油量：<span></span></div>	<div class=\"curOilstore\">当前油量：<span></span></div>"+
				   "<div class=\"timestore\">	时间：<span></span></div>	<div class=\"addrstore\">	地点：<span></span></div>	<div class=\"driverstore\">驾驶员：<span></span></div><div class=\"phonestore\">电话：<span></span></div></div> <div next='1' class='rightArrow'> <img  style='' src=\"../images/xiaocheImage/alarmright.png\" class=\"imgArrow\" /></div>"+
				   "</div><div class=\"maskBtnLeft  fuckbaby\"  >油量记录</div><div class=\"maskBtnRight  fuckbaby\">告警处理</div></div>";
				$("#car"+i).append(infoHTML);
			},
			//绑定翻转、点击事件
			bindHandle:function(i){
				$('.infoMsg').eq(i-1).unbind("click");
				$('.maskBtnCenter').eq(i-1).unbind("click");
				$('.maskBtnLeft').eq(i-1).unbind("click");
				$('.maskBtnRight').eq(i-1).unbind("click");
				$('.leftArrow').eq(i-1).unbind("click");
				$('.rightArrow').eq(i-1).unbind("click");
				
				$('.infoMsg').eq(i-1).bind("click", function(){
					isDetailVehicle.removeByKey($(this).parent().parent().parent().attr('vin'));
					$('.infoList').eq(i-1).hide();
					$('.oilMonitorContent').eq(i-1).show();
				});
				$('.maskBtnCenter').eq(i-1).bind("click", function(){
					popMapWin($(this).attr('code'),$(this).attr('vin'),$(this).attr('ln'),2);
				});
				$('.maskBtnLeft').eq(i-1).bind("click", function(){
					popMapWin($(this).attr('code'),$(this).attr('vin'),$(this).attr('ln'),2);
				});
				$('.maskBtnRight').eq(i-1).bind("click", function(){
					popMapWin($(this).attr('code'),$(this).attr('vin'),$(this).attr('ln'),1);
				});
				$('.leftArrow').eq(i-1).bind("click", function(){
					var carObj=$(this).parent().parent().parent();
					var preIdx = parseInt($(this).attr("prev"));
						if(preIdx > 0){
								$('.rightArrow',carObj).attr("next",preIdx);
								preIdx = preIdx -1;
								$(this).attr("prev",preIdx);
								oilMassMonitorObj.setAllAlarmMsg(carObj.data(carObj.attr('vin'))[preIdx], carObj);
						}
					oilMassMonitorObj.hideAlarmArrow(carObj);	
					delete preIdx;
					delete carObj;
				});
				$('.rightArrow').eq(i-1).bind("click", function(){
					var carObj=$(this).parent().parent().parent();
					var nextIdx = parseInt($(this).attr("next"));
					var totalLen = parseInt($(".alarmCount",carObj).attr("total"));
					if(nextIdx < totalLen){
								$('.leftArrow',carObj).attr("prev",nextIdx );
								nextIdx = nextIdx + 1;
								$(this).attr("next",nextIdx);
								oilMassMonitorObj.setAllAlarmMsg(carObj.data(carObj.attr('vin'))[nextIdx-1], carObj);
					}
					oilMassMonitorObj.hideAlarmArrow(carObj);
					delete nextIdx;
					delete totalLen;
					delete carObj;
				});
			},
			//获取指定车辆的告警记录，并保存在jquery的缓存中
			getAlarmList: function(obj){
				var carObj=obj.parent();
				var vehicle_vin = obj.parent().attr('vin');
				if(carObj.data(vehicle_vin)!=null && carObj.data(vehicle_vin).length == 0){
					return;
				}
				jQuery.ajax({
					type: 'POST',
					url: "getStealAlarmList.shtml",
					dataType: "json",
					data:  {
						'oilbox_state': '001',
						'vehicle_vin': vehicle_vin,
						'sortname':'report_time',
						'sortorder':'desc',
						'page':1,
						'rp':100
					},
					success: function(data){
						if(data.returnStr == "success"){
							if(data.ftlyInfo.length>0){
								//保存在jquery的缓存中在right_main节点
								carObj.data(data.ftlyInfo[0].vinCode,data.ftlyInfo);
								$(".alarmCount",carObj).attr("total",data.ftlyInfo.length);
								//$(".alarmCount",carObj).eq(0).html("告警&nbsp; "+data.ftlyInfo.length);
								$('.leftArrow',carObj).attr("prev","0");
								$('.rightArrow',carObj).attr("next","1");
								oilMassMonitorObj.setAllAlarmMsg(data.ftlyInfo[0], obj.parent());
								oilMassMonitorObj.hideAlarmArrow(carObj);
							}
						}
					}
				});
				delete carObj;
				delete vehicle_vin;
			},
			//设置告警详情
			setAllAlarmMsg: function(obj,carObj){
				if(obj==null){
					return;
				}
//				if(obj.oilboxState=="10") {
//					$(".alarmTitlestore").html("异常地点加油");
//				} else if(obj.oilboxState=="12"){
//					$(".alarmTitlestore").html("异常时间加油");
//				} else if(obj.oilboxState=="10,12"){
//					$(".alarmTitlestore").html("异常地点/异常时间");
//				} else if(obj.oilboxState=="001"){
					$(".alarmTitlestore").html("油量骤减告警"); 
//				}
				$(".oilstore",carObj).find('span').html(obj.addOill+"L");
				$(".curOilstore",carObj).find('span').html(obj.oilboxMass+"L");
				if($.trim(obj.reportTimeString).length==19){
					obj.reportTimeString=obj.reportTimeString.substring(2);
				}
				$(".timestore",carObj).find('span').html(obj.reportTimeString);
				if($.trim(obj.zonename).length>0&&obj.zonename.length>21){
					$(".addrstore",carObj).find('span').html(obj.zonename.substring(0,21));
					$(".addrstore",carObj).attr('title',obj.zonename);
				}else{
					$(".addrstore",carObj).find('span').html(obj.zonename);
				}
				$(".driverstore",carObj).find('span').html(obj.driverName);
				$(".phonestore",carObj).find('span').html(obj.driverTel);
				
				//this.hideAlarmArrow(carObj);
				delete obj;
				delete carObj;
			},
			//设置告警翻页的显示
			hideAlarmArrow:function(carObj){
				var preIdx = parseInt($('.leftArrow',carObj).attr("prev"),10);
				var nextIdx = parseInt($('.rightArrow',carObj).attr("next"),10);
				var totalLen = parseInt($(".alarmCount",carObj).attr("total"),10);
				if(preIdx==0){
					$('.leftArrow',carObj).find("img").hide();
				}else{
					$('.leftArrow',carObj).find("img").show();
				}
				if(totalLen<=nextIdx){
					$('.rightArrow',carObj).find("img").hide();
				}else{
					$('.rightArrow',carObj).find("img").show();
				}
				delete preIdx;
				delete nextIdx;
				delete totalLen;
				
			}
		};
		//显示滑动窗口
		function  popMapWin(code,vin,titleln,flag){
			if(titleln.length>7){
				titleln=titleln.substr(titleln.length-7);
				titleln="*"+titleln;
			}
			$('#popArea').attr('vehicle_vin',vin);
			$('#popArea').attr('vehicle_ln',titleln);
			$('#popArea').attr('vehicle_code',code);
			if(flag==2){
					$('#aa2').addClass('selected');
					$('#aa6').removeClass('selected');
			}else{
					$('#aa2').removeClass('selected');
					$('#aa6').addClass('selected');
			}
			$('#popArea').show();
			$('.mk-sidelayer').height(540);
			$('.mk-sidelayer').removeClass('mk-sidelayer-small');
			$('.mk-sidelayer').width(500);
			$('.mk-sidelayer-tools').show();
			$('.mk-sidelayer-bar-close').hide();
			$('.mk-sidelayer-bar-title').html('<font size="5">'+code+'号</font> <font size="3">('+titleln+')</font>');
			if(flag==1){
				$('#mk-iframe').attr('src','getOilMessage.shtml?changePath=success&vehicle_vin='+vin+'&vehicle_ln='+encodeURIComponent(titleln));
			}else{
				$('#mk-iframe').attr('src','getOilMessage.shtml?changePath=success2&vehicle_vin='+vin+'&vehicle_ln='+encodeURIComponent(titleln));
			}
			delete code;
			delete vin;
			delete titleln;
			delete flag;
		}
		function showpop(){
			if($('.mk-sidelayer').width()==500){
				$('.mk-sidelayer').height(56);
				$('.mk-sidelayer').addClass('mk-sidelayer-small');
				$('.mk-sidelayer').width(230);
				$('.mk-sidelayer-tools').hide();
				$('.mk-sidelayer-bar-close').show();
			}else{
				$('.mk-sidelayer').height(540);
				$('.mk-sidelayer').removeClass('mk-sidelayer-small');
				$('.mk-sidelayer').width(500);
				$('.mk-sidelayer-tools').show();
				$('.mk-sidelayer-bar-close').hide();
			}
		}
		function closepop(){
			$('#popArea').hide();
		}
		//页面自适应
		function firstresize(){
			var winH=$(window).height();
			var headerH=$('#header').height();
			var footerH=$('#footer').height();
			var contentH=winH-headerH-footerH;
			if(winH<=600){
				$('#dash').height(contentH);
			}else{
				$('#dash').height(contentH-115);
			}
			$('#content').height($('#content_rightside').height());
			$('#lefttree').height($('#content_rightside').height()-85-25);
			$('#right_main').height(winH-headerH-footerH-38);
			delete winH;
			delete headerH;
			delete footerH;
			delete contentH;
			
		}
		//页面自适应
		function firstrisize(){
			firstresize();
			
			jQuery(window).mk_autoresize({
			     height_include: '#content',
			     height_exclude: ['#header', '#footer'],
			     height_offset: 0,
			     width_include: ['#header', '#content', '#footer'],
			     width_offset: 0
			  });
			
			//计算中区高度
			jQuery('#content').mk_autoresize({
				height_include : [ '#content_rightside', '#content_leftside' ],
				height_offset : 0
			});
			//计算左测区域高度
			jQuery('#content_leftside').mk_autoresize( {
				height_exclude : ['#leftInfoDiv','#newsearchPlan','#lefttree'],
				//height_include : ,
				height_offset : 0
			});
			
			jQuery('#lefttree').mk_autoresize({
				//height_exclude : ['#leftInfoDiv','#newsearchPlan'],
				height_include : '#treeDemo',
				height_offset : 0
			});

			jQuery('#content').mk_autoresize({
			    width_exclude:'#content_leftside',
			    width_include: '.content_rightside',
			    width_offset: 0// 该值各页面根据自己的页面布局调整
			  });

			 
			jQuery('#content_rightside').mk_autoresize({
				height_exclude: '#titleBarMap',
			    height_include: '.right_main',
			    height_offset: 0 // 该值各页面根据自己的页面布局调整
			  });
			
			jQuery('#right_main').mk_autoresize({
				height_exclude : '.alarm_tab',
			    height_include: '.oil_pic_message',
			    height_offset: -3 // 该值各页面根据自己的页面布局调整
			  });
			
			jQuery('.oil_pic_message').mk_autoresize({
				height_exclude : ['#pageLine','#dash'],
			    //height_include: '#dash',
			    height_offset: 0 // 该值各页面根据自己的页面布局调整
			});
			
		}
		
		function firstrisize2(){
			
			
			jQuery(window).mk_autoresize({
			     height_include: '#content',
			     height_exclude: ['#header', '#footer'],
			     height_offset: 0,
			     width_include: ['#header', '#content', '#footer'],
			     width_offset: 0
			  });
			
			//计算中区高度
			jQuery('#content').mk_autoresize({
				height_include : [ '#content_rightside', '#content_leftside' ],
				height_offset : 0
			});
			//计算左测区域高度
			jQuery('#content_leftside').mk_autoresize({
				height_exclude : ['#leftInfoDiv','#newsearchPlan'],
				height_include : '#lefttree',
				height_offset : 65
			});
			
			jQuery('#lefttree').mk_autoresize({
				height_exclude : '#treeDemo',
				height_offset : 65
			});

			jQuery('#content').mk_autoresize({
			    width_exclude:'#content_leftside',
			    width_include: '.content_rightside',
			    width_offset: 0// 该值各页面根据自己的页面布局调整
			  });

			 
			jQuery('#content_rightside').mk_autoresize({
				height_exclude: '#titleBarMap',
			    height_include: '#right_main',
			    height_offset: 0 // 该值各页面根据自己的页面布局调整
			  });
			
			jQuery('#right_main').mk_autoresize({
				height_exclude : '#alarm_tab',
			    height_include: '#oil_pic_message',
			    height_offset: 0 // 该值各页面根据自己的页面布局调整
			  });
			
			jQuery('.oil_pic_message').mk_autoresize({
				height_exclude : '#pageLine',
			    height_include: '#dash',
			    height_offset: 0 // 该值各页面根据自己的页面布局调整
			});
			$("#pageSelect").change();
		}
		
		function HideandShowControl(){//leftdiv
			if(jQuery('#middeldiv').css("display")=="none"){//展开状态
				jQuery('#middeldiv').show();
				jQuery('#leftdiv').hide();
			}else{//隐藏状态
				jQuery('#middeldiv').hide();
				jQuery('#leftdiv').show();
			}
			$("#pageSelect").change();
		}
		
		function pageFirst() {
			if($('#pageSelect').val() == 1){
				$('#pageSelect').val('1');
				return ;
			}
			$('#pageSelect').val("1");
			$('#pageSelect').change();
		}
		
		function pageLast() {
			$('#pageSelect').val($('#curPage').html());
			$('#pageSelect').change();
		}
		
		function pagePrev (){
			if(Number($('#pageSelect').val())-1 < 1){
				return ;
			}
			$('#pageSelect').val(Number($('#pageSelect').val())-1);
			$('#pageSelect').change();
		}
		
		function pageNext(){
			$('#pageSelect').val(Number($('#pageSelect').val())+1);
			$('#pageSelect').change();
		}
		
		$(document).ready(function() {
			$('#popArea').hide();
			oilMassMonitorObj = new oilMassMonitor();
			//tab切换，不同的需要初始化页数
			$('.alarm_tab a').click(function(){
				clearTimeout(trunTimer);
				trunTimer = null;
				$('#popArea').hide();
				$(this).parent().find('a').removeClass('alarm_tabs');
				$(this).addClass('alarm_tabs');
				var type=$(this).attr('type');
				$('#pageSelect').val('1');
				$('#monitorType').val(type);
				oilMassMonitorObj.isFresh = 0;
				oilMassMonitorObj.isGetHtml = 0;
				oilMassMonitorObj.BoardArr = [];
				if(oilMassMonitorObj.ajaxReq != null){
					oilMassMonitorObj.ajaxReq.abort();
					oilMassMonitorObj.ajaxReq = null;
				}
				
				$('#dash').addClass("loading");
				$('#dash').empty();
				isDetailVehicle = null;
				isDetailVehicle = new Map();
				oilMassMonitorObj.init();
				//加载加载中图片样式
			});
			
			
			$('#pageSelect').change(function(){
				clearTimeout(trunTimer);
				trunTimer = null;
				var index=$(this).val();
				
				index = formatSpecialChar(index.split(".")[0]);
				$(this).val(index);
				if(index > parseInt($("#curPage").html(),10)){
					index = $("#curPage").html();
				}
				if(index <= 0 || index.length == 0 || !Number(index)){
					index = 1;
				}
				$(this).val(index);
				oilMassMonitorObj.init();
				//加载加载中图片样式
			});
			firstrisize2();
			$(window).resize(function(){
				firstrisize2();
			});
		});
		window.unload = function(){
			delete oilMassMonitorObj;
			delete vehicleList;
			delete finalVINs;
			delete turnTimer;
		};