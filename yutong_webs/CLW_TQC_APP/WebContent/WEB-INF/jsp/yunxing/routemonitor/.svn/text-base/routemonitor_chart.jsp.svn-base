<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='../mooko-ui/themes/default/mk.routemonitor.css' />">
<script type="text/javascript" src="<s:url value="../mooko-ui/core/mk.routemonitor.js" />"></script>
<script type="text/javascript" src="<s:url value='/scripts/sidelayerMapUitl.js'/>"></script>

<script>
	function arrayRemove(array, item){
		var index = jQuery.inArray(item, array);
		if (index > -1){
			array.splice(index, 1);
		}
	};
	function writetableinfo() {
		/* jQuery("#vehicle_code").html(''); */
		jQuery("#driver_name").html('');
		jQuery("#vehicle_ln").val('');
		jQuery("#vehicle_vin").val('');
		//jQuery("input[name=turnonvehiclechico][value=0]").attr("checked","checked");
		jQuery("#send_time_s").val("07:00");
	}
	function show_rm(data) {
		jQuery('#rm_route1').mk_routemonitor('reload', data);
		jQuery('#rm_route2').mk_routemonitor('reload', data);
		jQuery('#rm_route3').mk_routemonitor('reload', data);
		jQuery('#rm_route4').mk_routemonitor('reload', data);
		jQuery('#rm_route5').mk_routemonitor('reload', data);
		jQuery('#rm_route6').mk_routemonitor('reload', data);
		jQuery('#rm_route7').mk_routemonitor('reload', data);
	}
	var deldata = null; 
	function delcarlistvalue(i,ii) {
		jQuery("#route_list_ii").val(ii);
		if(ii==1) {
			deldata = {
				"routeChart.route_id":jQuery("#routeid1").val(),
				"routeChart.trip_id":data_list_car1[i].trip_id,
				"routeChart.VIN":data_list_car1[i].VIN,
				"routeChart.send_order":data_list_car1[i].send_order,
				"routeChart.week_seq":ii
			};
		} else if (ii==2) {
			deldata = {
					"routeChart.route_id":jQuery("#routeid1").val(),
					"routeChart.trip_id":data_list_car2[i].trip_id,
					"routeChart.VIN":data_list_car2[i].VIN,
					"routeChart.send_order":data_list_car2[i].send_order,
					"routeChart.week_seq":ii
			};
		} else if(ii==3) {
			deldata = {
					"routeChart.route_id":jQuery("#routeid1").val(),
					"routeChart.trip_id":data_list_car3[i].trip_id,
					"routeChart.VIN":data_list_car3[i].VIN,
					"routeChart.send_order":data_list_car3[i].send_order,
					"routeChart.week_seq":ii
			};
		} else if(ii==4) {
			deldata = {
					"routeChart.route_id":jQuery("#routeid1").val(),
					"routeChart.trip_id":data_list_car4[i].trip_id,
					"routeChart.VIN":data_list_car4[i].VIN,
					"routeChart.send_order":data_list_car4[i].send_order,
					"routeChart.week_seq":ii
			};
		} else if(ii==5) {
			deldata = {
					"routeChart.route_id":jQuery("#routeid1").val(),
					"routeChart.trip_id":data_list_car5[i].trip_id,
					"routeChart.VIN":data_list_car5[i].VIN,
					"routeChart.send_order":data_list_car5[i].send_order,
					"routeChart.week_seq":ii
			};
		} else if(ii==6) {
			deldata = {
					"routeChart.route_id":jQuery("#routeid1").val(),
					"routeChart.trip_id":data_list_car6[i].trip_id,
					"routeChart.VIN":data_list_car6[i].VIN,
					"routeChart.send_order":data_list_car6[i].send_order,
					"routeChart.week_seq":ii
			};
		} else if(ii==7) {
			deldata = {
					"routeChart.route_id":jQuery("#routeid1").val(),
					"routeChart.trip_id":data_list_car7[i].trip_id,
					"routeChart.VIN":data_list_car7[i].VIN,
					"routeChart.send_order":data_list_car7[i].send_order,
					"routeChart.week_seq":ii
			};
		} else {
			//alert("请选择行列");
		}
		return deldata;
	}
	function delcarlist(i,ii) {
		var data = delcarlistvalue(i,ii);
		jQuery.post("../route_chart/route_del_car.shtml",data,function(v){
			if(v=='success') {
				RouteChartDWR.getChartCarListReflash(jQuery("#routeid1").val(), ii,"", jQuery('#user_org_id').val(), backFun_getChartInfo_charinforeflush);
			} else {
				alert(v);
				RouteChartDWR.getChartCarListReflash(jQuery("#routeid1").val(), ii,"", jQuery('#user_org_id').val(), backFun_getChartInfo_charinforeflush);
			}
		});
	}
	function delcarlistexe(i,ii) {
		var data = delcarlistvalue(i,ii);
		jQuery.post("../route_chart/route_del_car.shtml",data,function(v){
			addcarlistinfo();
		});
	}
	//i为发车顺序号  ii为页面列值(1,2,3)
	function addcarlist(i,ii) {
		if(jQuery("#treetqcid").hasClass('tabfocus')) {
			document.getElementById("send_condition_1").disabled = true;
			jQuery("input[name=turnonvehiclechico][value=1]").attr("checked",'checked');
		} else {
			document.getElementById("send_condition_1").disabled = false;
		}
		if(jQuery("#treeupid").hasClass('tabfocus')||jQuery("#treedownid").hasClass('tabfocus')) {
			document.getElementById("send_condition_2").disabled = true;
			jQuery("input[name=turnonvehiclechico][value=0]").attr("checked",'checked');
		} else {
			document.getElementById("send_condition_2").disabled = false;
		}
		
		if(jQuery("#siteStuDetail").css("display")=='none') {
			jQuery("#BgDiv").css("display","block");
			jQuery("#BgDiv").width(jQuery(window).width());
			jQuery("#BgDiv").height(jQuery(window).height());
			jQuery("#siteStuDetail").css("display","block");
		} else {
			jQuery("#BgDiv").css("display","none");
			jQuery("#siteStuDetail").css("display","none");
		}
		jQuery("#vehicle_ln").unbind();
		jQuery("#vehicle_ln").bind("click",function(){
			choiceCarln();
		});
		jQuery("#driver_name").css("color","black").unbind();
		jQuery("#driver_name").css("color","#0078c8").bind("click",function(){
			searchDriver('1');
		});
		writetableinfo();
		jQuery("#routecarseqordernum").html(addorderinfoto(i,ii,1));
		jQuery("#route_list_i").val(i);
		jQuery("#route_list_ii").val(ii);
		jQuery("#addupdatetype").val("1");
		jQuery("#detailTile").html("添加发车安排");
		jQuery("#popinfodelbut").hide();
		
		jQuery("#driver_id").val('');
		/* document.getElementById("route_driver_s").disabled = true; */
	}
	function popinfoaddupdate() {
		//jQuery("#addupdatetype").val() == 1 增加	==2更新
		if(jQuery("#addupdatetype").val() == '1') {
			addcarlistinfo();
		} else if(jQuery("#addupdatetype").val() == '2') {
			updateroutecarexe();
		}
	}
	var dateChina = ['一','二','三','四','五','六','日'];
	var data_patch_error = "";
	var data_patch = null;
	function popinfoadd_patch(i) {
		var checkboxmaxsize = 0;
		//开始迭代将错误信息清空
		if(i==0)
			data_patch_error = "";
		i++;
		if(jQuery("#patch_vehicle_vin").val()=='') {
			alert("请选择车辆！");
			return false;
		}
		var nocheck = 0;
		for(var v=1;v<8;v++) {
			if(jQuery("#patch_"+v+"_day").attr("checked")==true) {
				nocheck++;
				checkboxmaxsize = v;
			}
		}
		if(nocheck == 0) {
			alert("请选择执行日发出频次！");
			return false;
		}
		var route_id_=jQuery("#routeid1").val();
		//for(var vvv=1;vvv<8;vvv++) {
			if(jQuery("#patch_"+i+"_day").attr("checked") == true) {
				data_patch = {
					"routeChart.route_id":route_id_,
					"routeChart.VIN":jQuery("#patch_vehicle_vin").val(),
					"routeChart.send_condition":jQuery("input[name=patch_turnonvehiclechico]:checked").val(),
					"routeChart.send_time":jQuery("#patch_send_time_s").val(),
					"routeChart.send_order":jQuery("#patch_"+i+"_sel").val(),
					"routeChart.driver_id":jQuery("#driver_id").val(),
					"routeChart.week_seq":i
				};
				jQuery.post("../route_chart/route_add_car.shtml",data_patch,function(v){
					//成功失败都去刷新列表
					if(v.indexOf("su")>=0) {
						if(i==checkboxmaxsize) {
							closepatch_Detail();
							jQuery("#route_list_ii").val(i);
							if(data_patch_error.length>0) {
								alert(data_patch_error);
							}
							searchCarRunList();
						} else {
							jQuery("#route_list_ii").val(i);
							//searchCarRunList();
							popinfoadd_patch(i);
						} 
					} else {
						//data_patch_error += 
						if(i<checkboxmaxsize) {
							//alert(v); 车辆已存在
							//data_patch_error += "礼拜"+dateChina[i-1]+" "+jQuery("#patch_vehicle_ln").val()+" " + v +"\r\n";
							data_patch_error += "周"+dateChina[i-1]+"、";
							jQuery("#route_list_ii").val(i);
							//showpageaddcar(i,route_id_);
							popinfoadd_patch(i);
						} else {
							//data_patch_error += "礼拜"+dateChina[i-1]+" "+jQuery("#patch_vehicle_ln").val()+" " + v +"\r\n";
							data_patch_error += "周"+dateChina[i-1]+ "已存在发车安排！" +"\r\n";
							data_patch_error="车辆"+jQuery("#patch_vehicle_ln").val()+"在"+data_patch_error;
							jQuery("#route_list_ii").val(i);
							alert(data_patch_error);
							closepatch_Detail();
							jQuery("#route_list_ii").val(i);
						}
					}
				});
			} else {
				if(i==7) {
					closepatch_Detail();
					jQuery("#route_list_ii").val(i);
				} else {
					jQuery("#route_list_ii").val(i);
					popinfoadd_patch(i);
				} 
			}
		//}
		window.setTimeout(function(){searchCarRunList();}, 1500);
	}
	//刷新
	function reflashchart() {
		var route_id_=jQuery("#routeid1").val();
		showpageaddcar(1,route_id_);
		showpageaddcar(2,route_id_);
		showpageaddcar(3,route_id_);
		showpageaddcar(4,route_id_);
		showpageaddcar(5,route_id_);
		showpageaddcar(6,route_id_);
		showpageaddcar(7,route_id_);
	}
	function updateroutecarexe() {
		delcarlistexe(jQuery("#route_list_i").val(),jQuery("#route_list_ii").val());
	}
	var addhtmlcarinfo = "";
	function addorderinfoto(i,ii,type) {
		//type 如果是1则是添加从1到n+1 如果是2则是更改从1到n
		if(type == 1)
			addhtmlcarinfo = "<option>"+(i+2)+"</option>";
		else if(type == 2 && i!=-1)
			addhtmlcarinfo = "<option>"+(i+1)+"</option>";
		if(ii == 1) {
			if(type == 1) {
				for(var v=0;v<data_list_car1.length+1;v++) {
					if(v!=(i+1))
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car1.length;v++) {
					if(v!=i)
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			}
		} else if(ii == 2) {
			if(type == 1) {
				for(var v=0;v<data_list_car2.length+1;v++) {
					if(v!=(i+1))
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car2.length;v++) {
					if(v!=i)
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			}
		} else if(ii == 3) {
			if(type == 1) {
				for(var v=0;v<data_list_car3.length+1;v++) {
					if(v!=(i+1))
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car3.length;v++) {
					if(v!=i)
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			}
		} else if(ii == 4) {
			if(type == 1) {
				for(var v=0;v<data_list_car4.length+1;v++) {
					if(v!=(i+1))
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car4.length;v++) {
					if(v!=i)
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			}
		} else if(ii == 5) {
			if(type == 1) {
				for(var v=0;v<data_list_car5.length+1;v++) {
					if(v!=(i+1))
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car5.length;v++) {
					if(v!=i)
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			}
		} else if(ii == 6) {
			if(type == 1) {
				for(var v=0;v<data_list_car6.length+1;v++) {
					if(v!=(i+1))
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car6.length;v++) {
					if(v!=i)
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			}
		} else if(ii == 7) {
			if(type == 1) {
				for(var v=0;v<data_list_car7.length+1;v++) {
					if(v!=(i+1))
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car7.length;v++) {
					if(v!=i)
						addhtmlcarinfo += "<option>"+(v+1)+"</option>";
				}
			}
		}
		return addhtmlcarinfo;
	}
	function addcarlistinfo() {
		var route_id_=jQuery("#routeid1").val();
		if(jQuery("#vehicle_vin").val()=='') {
			alert("请选择车辆！");
			return false;
		} else if(jQuery("input[name=turnonvehiclechico]:checked").val()==null) {
			alert("请选择发车条件！");
			return false;
		}
		if(jQuery("input[name=turnonvehiclechico]:checked").val()==2&&jQuery("#send_time_s").val()=="") {
			alert("请选择发车时间");
			return false;
		}
		//当添加的时候才做循环发车判断
		if(jQuery("#addupdatetype").val() == '1') {
			if(jQuery("input[name=turnonvehiclechico]:checked").val() == '1') {
				//如果是循环发车校验发车是否存在
				if(sendcar_return(jQuery("#vehicle_vin").val(),jQuery("#route_list_ii").val())) {
					alert("循环发车车辆不可重复添加！");
					return false;
				}
			}
		}
		var data = {
			"routeChart.route_id":route_id_,
			"routeChart.VIN":jQuery("#vehicle_vin").val(),
			"routeChart.send_condition":jQuery("input[name=turnonvehiclechico]:checked").val(),
			"routeChart.send_time":jQuery("#send_time_s").val(),
			"routeChart.send_order":jQuery("#routecarseqordernum").val(),
			"routeChart.week_seq":jQuery("#route_list_ii").val(),
			"routeChart.driver_id":jQuery("#driver_id").val()
		};
		jQuery.post("../route_chart/route_add_car.shtml",data,function(v){
			//成功失败都去刷新列表
			if(v.indexOf("su")>=0) {
				closeViloationDetail();
				showpageaddcar(jQuery("#route_list_ii").val(),route_id_);
			} else {
				alert(v);
				showpageaddcar(jQuery("#route_list_ii").val(),route_id_);
			}
		});
	}
	function popinfodel() {
		closeViloationDetail();
		delcarlist(jQuery("#route_list_i").val(),jQuery("#route_list_ii").val());
	}
	function updateroutecarinfo(i,ii) {
		if(true) {
			document.getElementById("vehicle_ln").disabled = false;
			if(jQuery("#treetqcid").hasClass('tabfocus')) {
				document.getElementById("send_condition_1").disabled = true;
				jQuery("input[name=turnonvehiclechico][value=1]").attr("checked",'checked');
			} else {
				document.getElementById("send_condition_1").disabled = false;
			}
			if(jQuery("#treeupid").hasClass('tabfocus')||jQuery("#treedownid").hasClass('tabfocus')) {
				document.getElementById("send_condition_2").disabled = true;
				jQuery("input[name=turnonvehiclechico][value=0]").attr("checked",'checked');
			} else {
				document.getElementById("send_condition_2").disabled = false;
			}
			document.getElementById("send_condition_3").disabled = false;
			document.getElementById("send_time_s").disabled = false;
			document.getElementById("routecarseqordernum").disabled = false;
			
			document.getElementById("popinfoaddupdatesure").disabled = false;
			document.getElementById("popinfodelbut").disabled = false;
		}
		
		jQuery("#BgDiv").css("display","block");
		jQuery("#BgDiv").width(jQuery(window).width());
		jQuery("#BgDiv").height(jQuery(window).height());
		jQuery("#siteStuDetail").css("display","block");
		
		if(ii == 1) {
			jQuery("#vehicle_ln").val(data_list_car1[i].vehicle_ln+"("+data_list_car1[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car1[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car1[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car1[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car1[i].send_order);
			//jQuery("#vehicle_code").html(data_list_car1[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+data_list_car1[i].driver_name);
			jQuery("#driver_id").val(data_list_car1[i].driver_id);
		} else if (ii == 2) {
			jQuery("#vehicle_ln").val(data_list_car2[i].vehicle_ln+"("+data_list_car2[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car2[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car2[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car2[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car2[i].send_order);
			//jQuery("#vehicle_code").html(data_list_car2[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+data_list_car2[i].driver_name);
			jQuery("#driver_id").val(data_list_car2[i].driver_id);
		} else if (ii == 3) {
			jQuery("#vehicle_ln").val(data_list_car3[i].vehicle_ln+"("+data_list_car3[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car3[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car3[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car3[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car3[i].send_order);
			//jQuery("#vehicle_code").html(data_list_car3[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+data_list_car3[i].driver_name);
			jQuery("#driver_id").val(data_list_car3[i].driver_id);
		} else if (ii == 4) {
			jQuery("#vehicle_ln").val(data_list_car4[i].vehicle_ln+"("+data_list_car4[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car4[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car4[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car4[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car4[i].send_order);
			//jQuery("#vehicle_code").html(data_list_car4[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+data_list_car4[i].driver_name);
			jQuery("#driver_id").val(data_list_car4[i].driver_id);
		} else if (ii == 5) {
			jQuery("#vehicle_ln").val(data_list_car5[i].vehicle_ln+"("+data_list_car5[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car5[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car5[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car5[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car5[i].send_order);
			//jQuery("#vehicle_code").html(data_list_car5[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+data_list_car5[i].driver_name);
			jQuery("#driver_id").val(data_list_car5[i].driver_id);
		} else if (ii == 6) {
			jQuery("#vehicle_ln").val(data_list_car6[i].vehicle_ln+"("+data_list_car6[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car6[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car6[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car6[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car6[i].send_order);
			//jQuery("#vehicle_code").html(data_list_car6[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+data_list_car6[i].driver_name);
			jQuery("#driver_id").val(data_list_car6[i].driver_id);
		} else if (ii == 7) {
			jQuery("#vehicle_ln").val(data_list_car7[i].vehicle_ln+"("+data_list_car7[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car7[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car7[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car7[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car7[i].send_order);
			//jQuery("#vehicle_code").html(data_list_car7[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+data_list_car7[i].driver_name);
			jQuery("#driver_id").val(data_list_car7[i].driver_id);
		}
		jQuery("#vehicle_ln").unbind();
		jQuery("#driver_name").css("color","black").unbind();//#0078c8
		
		jQuery("#routecarseqordernum").html(addorderinfoto(i,ii,2));
		jQuery("#route_list_i").val(i);
		jQuery("#route_list_ii").val(ii);
		jQuery("#addupdatetype").val("2");
		jQuery("#detailTile").html("更改发车安排");
		jQuery("#popinfodelbut").show();
		
		/* document.getElementById("route_driver_s").disabled = false; */
		
		if(showcarchangeinfo=='1') {
			document.getElementById("vehicle_ln").disabled = true;
			
			document.getElementById("send_condition_1").disabled = true;
			document.getElementById("send_condition_2").disabled = true;
			document.getElementById("send_condition_3").disabled = true;
			document.getElementById("send_time_s").disabled = true;
			document.getElementById("routecarseqordernum").disabled = true;
			
			document.getElementById("popinfoaddupdatesure").disabled = true;
			document.getElementById("popinfodelbut").disabled = true;
		} else {
			jQuery("#driver_name").css("color","#0078c8").bind("click",function(){
				searchDriver('1');
			});
		}
	}
	function showpageaddcar(ii,route_id_) {
		RouteChartDWR.getChartCarListReflash(route_id_, ii,"", jQuery('#user_org_id').val(), backFun_getChartInfo_charinforeflush);
	}
	function cutstring(str) {
		return str.length>8?str.substring(0,8):str;
	}
	function isonlinepopcolor(v) {
		return "tableroute_car_info_noline";//v=='offline'?"tableroute_car_info_offline":"tableroute_car_info_online";
	}
	function sendtypechoice(info) {
		var tname = "";
		if(info.send_condition==2) {
			tname= info.send_time;
		} else if(info.send_condition==0) {
			tname= "坐满发车";
		} else if(info.send_condition==1) {
			tname= "循环发车";
		}
		return tname;
	}
	function sendvehicle_codenull(type,v) {
		if(type==null||type.length<1)
			return "未知";
		else {
			if(type=='1') 
				return "外租";
			else
				return v;
		}
	}
	function checklimitnum(num) {
		if(num == null)
			return '0';
		else
			return num;
	}
	var reload_tongqin = null;
	var reload_html = "";
	var reload_changes = null;
	var reload_divshow = null;
	var reload_car_wid = null;
	var reload_station = null;
	var reload_car_cl_div = null;
	function reloadlistcar(data_list,va) {
		if(va==1) {
			reload_tongqin = jQuery("#tonqincar1");
		}else if(va==2){
			reload_tongqin = jQuery("#tonqincar2");
		}else if(va==3){
			reload_tongqin = jQuery("#tonqincar3");
		}else if(va==4){
			reload_tongqin = jQuery("#tonqincar4");
		}else if(va==5){
			reload_tongqin = jQuery("#tonqincar5");
		}else if(va==6){
			reload_tongqin = jQuery("#tonqincar6");
		}else if(va==7){
			reload_tongqin = jQuery("#tonqincar7");
		}
		
		reload_tongqin.empty();
		reload_html = "<ul>";
		if(va==1) {
			reload_changes = jQuery('<div></div>').attr('id', "tonqincar_change1").addClass("tonqincar_list_change").appendTo(reload_tongqin);
			reload_divshow = "<div id='buttonchangecar1' class='weekchangedown'><b>"+selectLn+"</b></div>";
			jQuery(reload_divshow).appendTo(reload_changes);
			reload_html += "<li style='position:relative;width:60px;float:left;margin-left:10px;'>";
			reload_html +="<div style='height:35px;line-height:35px;'><b>发车安排:</b></div>";
			reload_html +="</li>";
			
			reload_html += "<li style='position:relative;width:26px;float:left;'>";
			if(showcarchangeinfo == '1')
				reload_html +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist(-1,1)' ></a>";
			else
				reload_html +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist(-1,1)' ></a>";
			reload_html +="</li>";
		}else if(va==2) {
			reload_changes = jQuery('<div></div>').attr('id', "tonqincar_change2").addClass("tonqincar_list_change").appendTo(reload_tongqin);
			reload_divshow = "<div id='buttonchangecar1' class='weekchangedown'><b>"+selectLn+"</b></div>";
			jQuery(reload_divshow).appendTo(reload_changes);

			reload_html += "<li style='position:relative;width:60px;float:left;margin-left:10px;'>";
			reload_html +="<div style='height:35px;line-height:35px;'><b>发车安排:</b></div>";
			reload_html +="</li>";
			reload_html += "<li style='position:relative;width:26px;float:left;'>";
			if(showcarchangeinfo == '1')
				reload_html +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist(-1,2)' ></a>";
			else
				reload_html +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist(-1,2)' ></a>";
			reload_html +="</li>";
		}else if(va==3) {
			reload_changes = jQuery('<div></div>').attr('id', "tonqincar_change3").addClass("tonqincar_list_change").appendTo(reload_tongqin);
			reload_divshow = "<div id='buttonchangecar1' class='weekchangedown'><b>"+selectLn+"</b></div>";
			jQuery(reload_divshow).appendTo(reload_changes);

			reload_html += "<li style='position:relative;width:60px;float:left;margin-left:10px;'>";
			reload_html +="<div style='height:35px;line-height:35px;'><b>发车安排:</b></div>";
			reload_html +="</li>";
			reload_html += "<li style='position:relative;width:26px;float:left;'>";
			if(showcarchangeinfo == '1')
				reload_html +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist(-1,3)' ></a>";
			else
				reload_html +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist(-1,3)' ></a>";
			reload_html +="</li>";
		}else if(va==4) {
			reload_changes = jQuery('<div></div>').attr('id', "tonqincar_change4").addClass("tonqincar_list_change").appendTo(reload_tongqin);
			reload_divshow = "<div id='buttonchangecar1' class='weekchangedown'><b>"+selectLn+"</b></div>";
			jQuery(reload_divshow).appendTo(reload_changes);

			reload_html += "<li style='position:relative;width:60px;float:left;margin-left:10px;'>";
			reload_html +="<div style='height:35px;line-height:35px;'><b>发车安排:</b></div>";
			reload_html +="</li>";
			reload_html += "<li style='position:relative;width:26px;float:left;'>";
			if(showcarchangeinfo == '1')
				reload_html +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist(-1,4)' ></a>";
			else
				reload_html +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist(-1,4)' ></a>";
			reload_html +="</li>";
		}else if(va==5) {
			reload_changes = jQuery('<div></div>').attr('id', "tonqincar_change5").addClass("tonqincar_list_change").appendTo(reload_tongqin);
			reload_divshow = "<div id='buttonchangecar1' class='weekchangedown'><b>"+selectLn+"</b></div>";
			jQuery(reload_divshow).appendTo(reload_changes);

			reload_html += "<li style='position:relative;width:60px;float:left;margin-left:10px;'>";
			reload_html +="<div style='height:35px;line-height:35px;'><b>发车安排:</b></div>";
			reload_html +="</li>";
			reload_html += "<li style='position:relative;width:26px;float:left;'>";
			if(showcarchangeinfo == '1')
				reload_html +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist(-1,5)' ></a>";
			else 
				reload_html +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist(-1,5)' ></a>";
			reload_html +="</li>";
		}else if(va==6) {
			reload_changes = jQuery('<div></div>').attr('id', "tonqincar_change6").addClass("tonqincar_list_change").appendTo(reload_tongqin);
			reload_divshow = "<div id='buttonchangecar1' class='weekchangedown'><b>"+selectLn+"</b></div>";
			jQuery(reload_divshow).appendTo(reload_changes);

			reload_html += "<li style='position:relative;width:60px;float:left;margin-left:10px;'>";
			reload_html +="<div style='height:35px;line-height:35px;'><b>发车安排:</b></div>";
			reload_html +="</li>";
			reload_html += "<li style='position:relative;width:26px;float:left;'>";
			if(showcarchangeinfo == '1')
				reload_html +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist(-1,6)' ></a>";
			else
				reload_html +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist(-1,6)' ></a>";
			reload_html +="</li>";
		}else if(va==7) {
			reload_changes = jQuery('<div></div>').attr('id', "tonqincar_change7").addClass("tonqincar_list_change").appendTo(reload_tongqin);
			reload_divshow = "<div id='buttonchangecar1' class='weekchangedown'><b>"+selectLn+"</b></div>";
			jQuery(reload_divshow).appendTo(reload_changes);

			reload_html += "<li style='position:relative;width:60px;float:left;margin-left:10px;'>";
			reload_html +="<div style='height:35px;line-height:35px;'><b>发车安排:</b></div>";
			reload_html +="</li>";
			reload_html += "<li style='position:relative;width:26px;float:left;'>";
			if(showcarchangeinfo == '1')
				reload_html +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist(-1,7)' ></a>";
			else
				reload_html +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist(-1,7)' ></a>";
			reload_html +="</li>";
		}
		
		if(data_list.length>4)
			reload_car_wid = data_list.length * 175 +70;
		else
			reload_car_wid = "auto";
		
		
		reload_station = jQuery('<div></div>').attr('id', "tonqincar_list").addClass("tonqincar_list").appendTo(reload_tongqin);//.css("float","left").css("width","100%")
		reload_car_cl_div = jQuery('<div></div>').attr('id', "tonqincar_list_cl"+va).addClass("car_list_style").css("width",reload_car_wid).appendTo(reload_station);
		if (data_list && data_list.length > 0) {
			for(var v=0;v<data_list.length;v++) {
				reload_html += "<li style='position:relative;width:170px;'><div class='tableroute_car_info'>";
				
				reload_html +="<div class='tableroute_car_info_s'>";
				reload_html +="<span><a href='javascript:void(0);' class='"+isonlinepopcolor(data_list[v].car_state)+"' >"+sendvehicle_codenull(data_list[v].vehicle_type,data_list[v].vehicle_code)+"</a></span>";
				
				reload_html +="<span><a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;'>"+cutstring(data_list[v].vehicle_ln)+"("+checklimitnum(data_list[v].limite_number)+"座)"+"</a>";
				reload_html +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;' onclick='updateroutecarinfo("+v+","+va+")'>"+sendtypechoice(data_list[v]);//<a href='javascript:void(0);' class='tableroute_car_info_go_l'></a>
				reload_html +="</a></span>";
				
				
				/* reload_html += "<div class='"+isonlinepopcolor(data_list[v].car_state)+"'>"+data_list[v].vehicle_code+"</div>";
				reload_html +="<div class='tableroute_car_info_s'><a href='javascript:void(0);' onclick='updateroutecarinfo("+v+","+va+")' class='tableroute_car_info_ina' style='color:black;'>"+cutstring(data_list[v].vehicle_ln)+"("+data_list[v].limite_number+"座)"+"</a>";
				if(data_list[v].send_condition == 2) {
					reload_html +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;'>"+data_list[v].send_time+"</a>";//(v+1)+"-"+
				}else if(data_list[v].send_condition == 0) {
					reload_html +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;'>"+"坐满发车"+"</a>";//(v+1)+"-
				}else if(data_list[v].send_condition == 1) {
					reload_html +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;'>"+"循环发车"+"</a>";//(v+1)+"-
				} */
				reload_html +="</div></div></li><li style='position:relative;width:26px;'>";
				if(showcarchangeinfo == '1')
					reload_html +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist("+v+","+va+")'></a>";
				else
					reload_html +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist("+v+","+va+")'></a>";
				reload_html+="</li>";
			}
		} else {
			reload_html +="<li style='position:relative;width:170px;'><div style='line-height: 35px; height: 35px;'><font size='2'>暂无发车安排！</font></div>";
			reload_html +="</li>";
		}
		reload_html += "</ul>";
		jQuery(reload_html).appendTo(reload_car_cl_div);
	}
	
	function clear_rm(v) {
		if(v==1) {
			jQuery('#rm_route1').mk_routemonitor('clear');
			jQuery('#tonqincar1 #tonqincar_change1').empty();
			jQuery("#tonqincar1 #tonqincar_list").empty();
		}else if(v==2) {
			jQuery('#rm_route2').mk_routemonitor('clear');
			jQuery('#tonqincar2 #tonqincar_change2').empty();
			jQuery("#tonqincar2 #tonqincar_list").empty();
		}else if(v==3) {
			jQuery('#rm_route3').mk_routemonitor('clear');
			jQuery('#tonqincar3 #tonqincar_change3').empty();
			jQuery("#tonqincar3 #tonqincar_list").empty();
		}else if(v==4) {
			jQuery('#rm_route4').mk_routemonitor('clear');
			jQuery('#tonqincar4 #tonqincar_change4').empty();
			jQuery("#tonqincar4 #tonqincar_list").empty();
		}else if(v==5) {
			jQuery('#rm_route5').mk_routemonitor('clear');
			jQuery('#tonqincar5 #tonqincar_change5').empty();
			jQuery("#tonqincar5 #tonqincar_list").empty();
		}else if(v==6) {
			jQuery('#rm_route6').mk_routemonitor('clear');
			jQuery('#tonqincar6 #tonqincar_change6').empty();
			jQuery("#tonqincar6 #tonqincar_list").empty();
		}else if(v==7) {
			jQuery('#rm_route7').mk_routemonitor('clear');
			jQuery('#tonqincar7 #tonqincar_change7').empty();
			jQuery("#tonqincar7 #tonqincar_list").empty();
		}
	}

	/* 实体对象定义*/
	function mk_routemonitor(routeid, up_site_arr, down_site_arr, up_car_arr,down_car_arr) {
		this.line_id = routeid;
		this.up_data = up_site_arr;
		this.down_data = down_site_arr;
		this.up_cars = up_car_arr;
		this.down_cars = down_car_arr;
	}

	function mk_routemonitor_site(site_id, site_name, site_status, up_num,down_num) {
		this.id = site_id;
		this.name = site_name;
		this.status = site_status;
		this.up = up_num;
		this.down = down_num;
	}
	function mk_routemonitor_car(vin, ln, site_id, inout_flag) {
		this.vin = vin;
		this.site_id = site_id;
		this.vehicle_ln = ln;
		this.inout_flag = inout_flag;
	}
	var routecopylistvalue = new Array();
	var routecopylistno = new Array();
	function routecopycarlist(li) {
		//jQuery('input:radio[name="routecarcopy"]:checked').val()
		jQuery("#hiddencarcopy").val(li);
		var value = li;
		routecopylistno = value;
		if(value==1)
			routecopylistvalue = data_list_car1;
		else if(value==2)
			routecopylistvalue = data_list_car2;
		else if(value==3)
			routecopylistvalue = data_list_car3;
		else if(value==4)
			routecopylistvalue = data_list_car4;
		else if(value==5)
			routecopylistvalue = data_list_car5;
		else if(value==6)
			routecopylistvalue = data_list_car6;
		else if(value==7)
			routecopylistvalue = data_list_car7;
	}
	function routepastecarlist(li) {
		var value = new Array();
		//jQuery('input:radio[name="routecarcopy"]:checked').val();
		var copyto = li;
		if(copyto=='') {
			alert("请选择复制项！");
			return false;
		}
		jQuery("#route_list_ii").val(copyto);
		if(copyto=="1") {
			if(data_list_car1.length>0) {
				alert("已有发车安排请删除！");
				return;
			}
		}else if(copyto=="2") {
			if(data_list_car2.length>0) {
				alert("已有发车安排请删除！");
				return;
			}
		}else if(copyto=="3") {
			if(data_list_car3.length>0) {
				alert("已有发车安排请删除！");
				return;
			}
		}else if(copyto=="4") {
			if(data_list_car4.length>0) {
				alert("已有发车安排请删除！");
				return;
			}
		}else if(copyto=="5") {
			if(data_list_car5.length>0) {
				alert("已有发车安排请删除！");
				return;
			}
		}else if(copyto=="6") {
			if(data_list_car6.length>0) {
				alert("已有发车安排请删除！");
				return;
			}
		}else if(copyto=="7") {
			if(data_list_car7.length>0) {
				alert("已有发车安排请删除！");
				return;
			}
		}
		value = routecopylistvalue;
		for(var i = 0;i<value.length;i++) {
			value[i].week_seq = copyto;
			returnaddinfo(value[i],i+1,value.length,copyto,value[i].vehicle_ln);
		}
	}
	function returnaddinfo(obj,i,ilength,ii,ln) {
		//jQuery("#vehicle_ln").val(ln);
		var data = {
			"routeChart.route_id":obj.route_id,
			"routeChart.VIN":obj.VIN,
			"routeChart.send_condition":obj.send_condition,
			"routeChart.send_time":obj.send_time,
			"routeChart.send_order":obj.send_order,
			"routeChart.week_seq":obj.week_seq
		};
		jQuery.post("../route_chart/route_add_car_p.shtml",data,function(v){
			if(i==ilength) {
				window.setTimeout(function(){showpageaddcar(ii,jQuery("#routeid1").val())},3000);
			}
		});
	}
	jQuery(function() {
		jQuery('#rm_route1').mk_routemonitor({
				line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
				'station_callback' : function() {
					//sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
				},
				'car_callback' : function() {
				},
				"up_text":"",
				"route_up_id" : 'mk_routemonitor_route_up1',
				"up_station_id" : 'mk_up_station1',
				"route_plan_class" : 'mk-rm-route-plan2',
				"route_class" : 'mk-rm-route2',
				"route_title_class" : 'mk-rm-route-til2',
				"route_station_class" : 'mk-rm-route-station2',
				"tonqincar":"tonqincar1",
				"add_cars":false
		});
		jQuery('#rm_route2').mk_routemonitor({
			line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
			'station_callback' : function() {
				//sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
			},
			'car_callback' : function() {
			},
			"up_text":"",
			"route_up_id" : 'mk_routemonitor_route_up2',
			"up_station_id" : 'mk_up_station2',
			"route_plan_class" : 'mk-rm-route-plan2',
			"route_class" : 'mk-rm-route2',
			"route_title_class" : 'mk-rm-route-til2',
			"route_station_class" : 'mk-rm-route-station2',
			"tonqincar":"tonqincar2",
			"add_cars":false
		});
		jQuery('#rm_route3').mk_routemonitor({
			line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
			'station_callback' : function() {
				//sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
			},
			'car_callback' : function() {
			},
			"up_text":"",
			"route_up_id" : 'mk_routemonitor_route_up3',
			"up_station_id" : 'mk_up_station3',
			"route_plan_class" : 'mk-rm-route-plan2',
			"route_class" : 'mk-rm-route2',
			"route_title_class" : 'mk-rm-route-til2',
			"route_station_class" : 'mk-rm-route-station2',
			"tonqincar":"tonqincar3",
			"add_cars":false
		});
		jQuery('#rm_route4').mk_routemonitor({
			line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
			'station_callback' : function() {
				//sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
			},
			'car_callback' : function() {
			},
			"up_text":"",
			"route_up_id" : 'mk_routemonitor_route_up4',
			"up_station_id" : 'mk_up_station4',
			"route_plan_class" : 'mk-rm-route-plan2',
			"route_class" : 'mk-rm-route2',
			"route_title_class" : 'mk-rm-route-til2',
			"route_station_class" : 'mk-rm-route-station2',
			"tonqincar":"tonqincar4",
			"add_cars":false
		});
		jQuery('#rm_route5').mk_routemonitor({
			line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
			'station_callback' : function() {
				//sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
			},
			'car_callback' : function() {
			},
			"up_text":"",
			"route_up_id" : 'mk_routemonitor_route_up5',
			"up_station_id" : 'mk_up_station5',
			"route_plan_class" : 'mk-rm-route-plan2',
			"route_class" : 'mk-rm-route2',
			"route_title_class" : 'mk-rm-route-til2',
			"route_station_class" : 'mk-rm-route-station2',
			"tonqincar":"tonqincar5",
			"add_cars":false
		});
		jQuery('#rm_route6').mk_routemonitor({
			line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
			'station_callback' : function() {
				//sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
			},
			'car_callback' : function() {
			},
			"up_text":"",
			"route_up_id" : 'mk_routemonitor_route_up6',
			"up_station_id" : 'mk_up_station6',
			"route_plan_class" : 'mk-rm-route-plan2',
			"route_class" : 'mk-rm-route2',
			"route_title_class" : 'mk-rm-route-til2',
			"route_station_class" : 'mk-rm-route-station2',
			"tonqincar":"tonqincar6",
			"add_cars":false
		});
		jQuery('#rm_route7').mk_routemonitor({
			line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
			'station_callback' : function() {
				//sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
			},
			'car_callback' : function() {
			},
			"up_text":"",
			"route_up_id" : 'mk_routemonitor_route_up7',
			"up_station_id" : 'mk_up_station7',
			"route_plan_class" : 'mk-rm-route-plan2',
			"route_class" : 'mk-rm-route2',
			"route_title_class" : 'mk-rm-route-til2',
			"route_station_class" : 'mk-rm-route-station2',
			"tonqincar":"tonqincar7",
			"add_cars":false
		});
	});
	function sendcar_return(vin,ii) {
		if(ii == '1') {
			for(var v=0;v<data_list_car1.length;v++) {
				if(data_list_car1[v].VIN == vin&&data_list_car1[v].send_condition == '1') 
					return true;
			}
		} else if(ii == '2') {
			for(var v=0;v<data_list_car2.length;v++) {
				if(data_list_car2[v].VIN == vin&&data_list_car2[v].send_condition == '1') 
					return true;
			}
		} else if(ii == '3') {
			for(var v=0;v<data_list_car3.length;v++) {
				if(data_list_car3[v].VIN == vin&&data_list_car3[v].send_condition == '1') 
					return true;
			}
		} else if(ii == '4') {
			for(var v=0;v<data_list_car4.length;v++) {
				if(data_list_car4[v].VIN == vin&&data_list_car4[v].send_condition == '1') 
					return true;
			}
		} else if(ii == '5') {
			for(var v=0;v<data_list_car5.length;v++) {
				if(data_list_car5[v].VIN == vin&&data_list_car5[v].send_condition == '1') 
					return true;
			}
		} else if(ii == '6') {
			for(var v=0;v<data_list_car6.length;v++) {
				if(data_list_car6[v].VIN == vin&&data_list_car6[v].send_condition == '1') 
					return true;
			}
		} else if(ii == '7') {
			for(var v=0;v<data_list_car7.length;v++) {
				if(data_list_car7[v].VIN == vin&&data_list_car7[v].send_condition == '1') 
					return true;
			}
		}
		return false;
	}
</script>