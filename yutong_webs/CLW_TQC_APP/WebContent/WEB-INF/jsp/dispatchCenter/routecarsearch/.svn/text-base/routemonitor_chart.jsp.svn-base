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
	var deldata = null;
	function delcarlistvalue(i,ii) {
		jQuery("#route_list_ii").val(ii);
		if(ii==1) {
			deldata = {
				"routeChart.route_id":data_list_car[i].route_id,
				"routeChart.trip_id":data_list_car[i].trip_id,
				"routeChart.VIN":data_list_car[i].VIN,
				"routeChart.send_order":data_list_car[i].send_order,
				"routeChart.exe_date":jQuery("#queryTime").val(),
				"routeChart.add_flag":data_list_car[i].add_flag,
				"routeChart.trip_group":data_list_car[i].trip_group
			};
		} else if (ii==2) {
			deldata = {
				"routeChart.route_id":data_list_car2[i].route_id,
				"routeChart.trip_id":data_list_car2[i].trip_id,
				"routeChart.VIN":data_list_car2[i].VIN,
				"routeChart.send_order":data_list_car2[i].send_order,
				"routeChart.exe_date":jQuery("#queryTime").val(),
				"routeChart.add_flag":data_list_car2[i].add_flag,
				"routeChart.trip_group":data_list_car2[i].trip_group
			};
		} else if(ii==3) {
			deldata = {
				"routeChart.route_id":data_list_car3[i].route_id,
				"routeChart.trip_id":data_list_car3[i].trip_id,
				"routeChart.VIN":data_list_car3[i].VIN,
				"routeChart.send_order":data_list_car3[i].send_order,
				"routeChart.exe_date":jQuery("#queryTime").val(),
				"routeChart.add_flag":data_list_car3[i].add_flag,
				"routeChart.trip_group":data_list_car3[i].trip_group
			};
		} else {
			//alert("请选择行列");
		}
		return deldata;
	}
	function writetableinfo() {
		jQuery("#vehicle_ln").val('');
		jQuery("#vehicle_vin").val('');
		jQuery("input[name=turnonvehiclechico]:eq(0)").attr("checked","checked");
		jQuery("#send_time_s").val("07:00");
	}
	function writetableinfo_onlycar() {
		//jQuery("input[name=turnonvehiclechico]:eq(0)").attr("checked","checked");
		jQuery("#send_time_s").val("07:00");
	}
	function popinfoaddupdate() {
		//jQuery("#addupdatetype").val() == 1 增加	==2更新
		if(jQuery("#addupdatetype").val() == '1') {
			addcarlistinfo(0);
		} else if(jQuery("#addupdatetype").val() == '2') {
			updateroutecarexe();
		}
	}
	function updateroutecarexe() {
		if(checkInputifchange(jQuery("#route_list_i").val(),jQuery("#route_list_ii").val())) {
			if(jQuery("input[name=turnonvehiclechico]:checked").val()==2&&jQuery("#send_time_s").val()=="") {
				alert("请选择发车时间");
				return false;
			}
			delcarlistexe(jQuery("#route_list_i").val(),jQuery("#route_list_ii").val());
		} else {
			closeViloationDetail();
			return false;
		}
		//addcarlistinfo();
	}
	function delcarlistexe(i,ii) {
		if(jQuery("input[name=turnonvehiclechico]:checked").val() == '1') {
			//如果是循环发车校验发车是否存在
			if(sendcar_return(jQuery("#vehicle_vin").val(),ii)) {
				//alert("循环发车车辆不可重复添加！");
				//return false;
			}
		}
		var data = delcarlistvalue(i,ii);
		jQuery.post("../dispatchroute_chart/route_del_car_nosend.shtml",data,function(v){
			//判断是否是更新	如果是更新不刷新车辆列
			if(v=='success') {
				addcarlistinfo(1);
			} else if(v=='run') {
				alert("车辆线路已跑过！");
			}
		});
	}
	var add_oinfo_html = "";
	function addorderinfoto(i,ii,type) {
		if(type == 1)
			add_oinfo_html = "<option>"+(i+2)+"</option>";
		else if(type == 2)
			add_oinfo_html = "<option>"+(i+1)+"</option>";
		
		if(ii == 1) {
			if(type == 1) {
				for(var v=0;v<data_list_car.length+1;v++) {
					if(v!=(i+1))
						add_oinfo_html += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car.length;v++) {
					if(v!=i)
						add_oinfo_html += "<option>"+(v+1)+"</option>";
				}
			}
			
		} else if(ii == 2) {
			if(type == 1) {
				for(var v=0;v<data_list_car2.length+1;v++) {
					if(v!=(i+1))
						add_oinfo_html += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car2.length;v++) {
					if(v!=i)
						add_oinfo_html += "<option>"+(v+1)+"</option>";
				}
			}
		} else if(ii == 3) {
			if(type == 1) {
				for(var v=0;v<data_list_car3.length+1;v++) {
					if(v!=(i+1))
						add_oinfo_html += "<option>"+(v+1)+"</option>";
				}
			} else if(type == 2) {
				for(var v=0;v<data_list_car3.length;v++) {
					if(v!=i)
						add_oinfo_html += "<option>"+(v+1)+"</option>";
				}
			}
		}
		jQuery("#routecarseqordernum").html(add_oinfo_html);
	}
	function show_rm(data,v) {
		if(v==0) {
			jQuery('#rm_route').mk_routemonitor('reload', data);
		}else if(v==1){
			jQuery('#rm_route2').mk_routemonitor('reload', data);
		}else if(v==2){
			jQuery('#rm_route3').mk_routemonitor('reload', data);
		}
	}
	function delcarlist(i,ii) {
		var data = delcarlistvalue(i,ii);
		jQuery.post("../dispatchroute_chart/route_del_car.shtml",data,function(v){
			if(v=='success') {
				if(ii==1) {
					//reloadlistcar(data_list_car,ii);
					data_list_car = [];
					searchCarRunList("1");
				}else if(ii==2) {
					data_list_car2 = [];
					searchCarRunList("1");
					//reloadlistcar(data_list_car2,ii);
				}else if(ii==3){
					data_list_car3 = [];
					searchCarRunList("1");
					//reloadlistcar(data_list_car3,ii);
				}
			} else {
				alert(v);
			}
		});
	}
	//i为发车顺序号  ii为页面列值(1,2,3)
	function addcarlist(i,ii) {
		if(jQuery("#siteStuDetail").css("display")=='none') {
			jQuery("#BgDiv").css("display","block");
			jQuery("#BgDiv").width(jQuery(window).width());
			jQuery("#BgDiv").height(jQuery(window).height());
			jQuery("#siteStuDetail").css("display","block");
		} else {
			jQuery("#BgDiv").css("display","none");
			jQuery("#siteStuDetail").css("display","none");
		}
		jQuery("#vehicle_ln").bind("click",function(){
			choiceCarln();
		});
		addorderinfoto(i,ii,1);
		jQuery("#route_list_i").val(i);
		jQuery("#route_list_ii").val(ii);
		jQuery("#addupdatetype").val("1");
		writetableinfo();
		jQuery("#popinfodelbut").hide();
		document.getElementById("send_s_time_s").disabled = true;
		document.getElementById("send_s_time_s").value =getnowtime();
		
		/* document.getElementById("route_driver_s").disabled = true; */
	}
	function drivernamenull(value) {
		return value==null?"空":value;
	}
	//i为发车顺序号  ii为页面列值(1,2,3)
	function addcarlist_onlycheckcar(i,ii) {
		var dddd = null;
		if(ii==1) 
			dddd = reflashcaristype1;
		else if(ii==2) {
			dddd = reflashcaristype2;
		}else if(ii==3) {
			dddd = reflashcaristype3;
		}
		
		if(true) {
			document.getElementById("send_s_time_s").disabled = false;
			document.getElementById("send_e_time_s").disabled = false;
			document.getElementById("vehicle_ln").disabled = false;
			
			if(dddd == '2') {
				document.getElementById("send_condition_1").disabled = true;
				document.getElementById("send_condition_span").disabled = true;
				jQuery("input[name=turnonvehiclechico][value=1]").attr("checked",'true');
			} else {
				document.getElementById("send_condition_1").disabled = false;
				document.getElementById("send_condition_span").disabled = false;
			}
			if(dddd == '1'||dddd == '0') {
				document.getElementById("send_condition_2").disabled = true;
				document.getElementById("send_condition_span2").disabled = true;
				jQuery("input[name=turnonvehiclechico][value=0]").attr("checked",'checked');
			} else {
				document.getElementById("send_condition_span2").disabled = false;
				document.getElementById("send_condition_2").disabled = false;
			}
			document.getElementById("send_condition_3").disabled = false;
			document.getElementById("send_time_s").disabled = false;
			document.getElementById("routecarseqordernum").disabled = false;
			document.getElementById("send_s_time_s").disabled = true;
			
			document.getElementById("popinfoaddupdatesure").disabled = false;
			document.getElementById("popinfodelbut").disabled = false;
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
		if(vehicle_code=='外租')
			jQuery("#vehicle_ln").val(selectln);
		else
			jQuery("#vehicle_ln").val(selectln+"("+vehicle_code+")");
		//jQuery("#vehicle_code").html(vehicle_code);
		jQuery("#driver_name").html("驾驶员："+drivernamenull(driver_name));
		jQuery("#vehicle_ln").unbind();
		document.getElementById("vehicle_ln").disabled = true;
		jQuery("#driver_name").css("color","black").unbind();
		jQuery("#driver_name").css("color","#0078c8").bind("click",function(){
			searchDriver();
		});
		document.getElementById("driver_name").disabled = false;
		jQuery("#vehicle_vin").val(selectVIN);
		addorderinfoto(i,ii,1);
		jQuery("#route_list_i").val(i);
		jQuery("#route_list_ii").val(ii);
		jQuery("#addupdatetype").val("1");
		writetableinfo_onlycar();
		jQuery("#popinfodelbut").hide();
		document.getElementById("send_s_time_s").disabled = true;
		document.getElementById("send_s_time_s").value =jQuery("#queryTime").val();//getnowtime();
		
		document.getElementById("send_e_time_s").disabled =false;
		document.getElementById("send_e_time_s").value =jQuery("#queryTime").val();//getnowtime();
		jQuery("#popinfoaddupdatesure").show();
		document.getElementById("popinfoaddupdatesure").disabled =false;
		
		document.getElementById("driver_id").value ='';
	}
	function popinfodel() {
		jQuery("#BgDiv").css("display","none");
		jQuery("#siteStuDetail").css("display","none");
		delcarlist(jQuery("#route_list_i").val(),jQuery("#route_list_ii").val());
		//searchCarRunList();
	}
	var addcar_route_id_=null;
	function addcarlistinfo(runover) {
		if(jQuery("#route_list_ii").val()==1)
			addcar_route_id_ = jQuery("#routeid1").val();
		else if(jQuery("#route_list_ii").val()==2)
			addcar_route_id_ = jQuery("#routeid2").val();
		else if(jQuery("#route_list_ii").val()==3)
			addcar_route_id_ = jQuery("#routeid3").val();
		
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
		if(runover == 0) {
			if(jQuery("input[name=turnonvehiclechico]:checked").val() == '1') {
				//如果是循环发车校验发车是否存在
				if(sendcar_return(jQuery("#vehicle_vin").val(),jQuery("#route_list_ii").val())) {
					alert("循环发车车辆不可重复添加！");
					return false;
				}
			}
		}
		var data = {
			"routeChart.route_id":addcar_route_id_,
			"routeChart.VIN":jQuery("#vehicle_vin").val(),
			"routeChart.send_condition":jQuery("input[name=turnonvehiclechico]:checked").val(),
			"routeChart.send_time":jQuery("#send_time_s").val(),
			"routeChart.send_order":jQuery("#routecarseqordernum").val(),
			"routeChart.start_time":jQuery("#send_s_time_s").val(),
			"routeChart.end_time":jQuery("#send_e_time_s").val(),
			"routeChart.exe_date":jQuery("#queryTime").val(),
			"routeChart.driver_id":jQuery("#driver_id").val(),
			"routeChart.add_flag":1
		};
		jQuery.post("../dispatchroute_chart/route_add_car.shtml",data,function(v){
			if(v.indexOf("su")>=0) {
				closeViloationDetail();
				//全部刷新改为当前修改线路刷新
				if(jQuery("#route_list_ii").val()=='1')
				DispatchrouteChartDWR.getChartoneCarList(jQuery("#vehicle_vin").val(), jQuery("#queryTime").val(),jQuery("#routeid1").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
				if(jQuery("#route_list_ii").val()=='2')
				DispatchrouteChartDWR.getChartoneCarList(jQuery("#vehicle_vin").val(), jQuery("#queryTime").val(),jQuery("#routeid2").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo2);
				if(jQuery("#route_list_ii").val()=='3')
				DispatchrouteChartDWR.getChartoneCarList(jQuery("#vehicle_vin").val(), jQuery("#queryTime").val(),jQuery("#routeid3").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo3);
				//searchCarRunList("1");
			} else {
				alert(v);
				if(jQuery("#route_list_ii").val()=='1')
				DispatchrouteChartDWR.getChartoneCarList(jQuery("#vehicle_vin").val(), jQuery("#queryTime").val(),jQuery("#routeid1").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo1);
				if(jQuery("#route_list_ii").val()=='2')
				DispatchrouteChartDWR.getChartoneCarList(jQuery("#vehicle_vin").val(), jQuery("#queryTime").val(),jQuery("#routeid2").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo2);
				if(jQuery("#route_list_ii").val()=='3')
				DispatchrouteChartDWR.getChartoneCarList(jQuery("#vehicle_vin").val(), jQuery("#queryTime").val(),jQuery("#routeid3").val(), jQuery('#user_org_id').val(), backFun_getChartInfo_charinfo3);
				//searchCarRunList("1");
			}
		});
	}
	function getnowandsearcht(type) {
		if(type == 'now') {
			var time = "<s:property value='sysdate' />";
			var arr = time.split("-");
		    var starttime = new Date(arr[0], arr[1]-1, arr[2]);
		    return starttime.getTime();
		} else if (type == 'search') {
			var time = jQuery("#queryTime").val();
			var arr = time.split("-");
		    var starttime = new Date(arr[0], arr[1]-1, arr[2]);
		    return starttime.getTime();
		}
	}
	var showdetailinfov = 0;
	function updateroutecarinfo(i,ii) {
		//判断运行中司机更换
		var runningdriver = true;
		
		var dddd = null;
		if(ii==1) 
			dddd = reflashcaristype1;
		else if(ii==2) {
			dddd = reflashcaristype2;
		}else if(ii==3) {
			dddd = reflashcaristype3;
		}
		var starttimes = getnowandsearcht("search");
	    
	    var lktimes = getnowandsearcht("now");
	    if(true) {
			document.getElementById("send_s_time_s").disabled = false;
			document.getElementById("send_e_time_s").disabled = false;
			document.getElementById("vehicle_ln").disabled = false;
			
			document.getElementById("send_condition_3").disabled = false;
			document.getElementById("send_time_s").disabled = false;
			document.getElementById("routecarseqordernum").disabled = false;
			document.getElementById("send_s_time_s").disabled = true;
			
			document.getElementById("popinfoaddupdatesure").disabled = false;
			document.getElementById("popinfodelbut").disabled = false;
		}
		var usedtyp = 1;
		var add_flag = 0;
		jQuery("#BgDiv").css("display","block");
		jQuery("#BgDiv").width(jQuery(window).width());
		jQuery("#BgDiv").height(jQuery(window).height());
		jQuery("#siteStuDetail").css("display","block");
		
		/* document.getElementById("route_driver_s").disabled = false; */
		
		if(ii == 1) {
			if(data_list_car[i].vehicle_code == '外租')
				jQuery("#vehicle_ln").val(data_list_car[i].vehicle_ln);
			else
				jQuery("#vehicle_ln").val(data_list_car[i].vehicle_ln+"("+data_list_car[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car[i].send_order);
			add_flag = data_list_car[i].add_flag;
			jQuery("#addupdatetimetype").val(add_flag);
			jQuery("#send_s_time_s").val(data_list_car[i].start_time);
			jQuery("#send_e_time_s").val(data_list_car[i].end_time);
			//jQuery("#vehicle_code").html(data_list_car[i].vehicle_code);
			
			jQuery("#driver_name").html("驾驶员："+drivernamenull(data_list_car[i].driver_name));
			jQuery("#driver_id").val(data_list_car[i].driver_id);
			if(selectVIN != data_list_car[i].VIN) {
				usedtyp = 0;
				jQuery("#routecarseqordernum").html("<option>"+data_list_car[i].send_order+"</option>");
				document.getElementById("send_s_time_s").disabled = true;
				document.getElementById("send_e_time_s").disabled = true;
			} else {
				//if((data_list_car[i].updriver_id!=null&&data_list_car[i].updriver_id.length>0)||(data_list_car[i].status!=null&&data_list_car[i].status!='0')) {
				if(data_list_car[i].status!=null&&data_list_car[i].status!='0') {//即时车辆有驾驶员上报也允许修改
					runningdriver = false;
					document.getElementById("popinfoaddupdatesure").disabled = true;
					document.getElementById("popinfodelbut").disabled = true;
				}
				if(data_list_car[i].add_flag == 0) {
					document.getElementById("send_e_time_s").disabled = true;
				} else if(data_list_car[i].add_flag == 1) {
					document.getElementById("send_e_time_s").disabled = false;
				}
			}
			showdetailinfov =showcarchangeinfo;
		} else if (ii == 2) {
			if(data_list_car2[i].vehicle_code == '外租')
				jQuery("#vehicle_ln").val(data_list_car2[i].vehicle_ln);
			else
				jQuery("#vehicle_ln").val(data_list_car2[i].vehicle_ln+"("+data_list_car2[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car2[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car2[i].send_condition+"]").attr("checked",'checked');
			//jQuery("input[name=turnonvehiclechico]").val(data_list_car2[i].send_condition);
			jQuery("#send_time_s").val(data_list_car2[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car2[i].send_order);
			add_flag = data_list_car2[i].add_flag;
			jQuery("#addupdatetimetype").val(add_flag);
			jQuery("#send_s_time_s").val(data_list_car2[i].start_time);
			jQuery("#send_e_time_s").val(data_list_car2[i].end_time);
			//jQuery("#vehicle_code").html(data_list_car2[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+drivernamenull(data_list_car2[i].driver_name));
			jQuery("#driver_id").val(data_list_car2[i].driver_id);
			if(selectVIN != data_list_car2[i].VIN) {
				usedtyp = 0;
				jQuery("#routecarseqordernum").html("<option>"+data_list_car2[i].send_order+"</option>");
				document.getElementById("send_s_time_s").disabled = true;
				document.getElementById("send_e_time_s").disabled = true;
			} else {
				//if((data_list_car2[i].updriver_id!=null&&data_list_car2[i].updriver_id.length>0)||(data_list_car2[i].status!=null&&data_list_car2[i].status!='0')) {
				if(data_list_car2[i].status!=null&&data_list_car2[i].status!='0') {//即时车辆有驾驶员上报也允许修改
					runningdriver = false;
					document.getElementById("popinfoaddupdatesure").disabled = true;
					document.getElementById("popinfodelbut").disabled = true;
				}
				if(data_list_car2[i].add_flag == 0) {
					document.getElementById("send_e_time_s").disabled = true;
				}else if(data_list_car2[i].add_flag == 1) {
					document.getElementById("send_e_time_s").disabled = false;
				}
			}
			showdetailinfov =showcarchangeinfo2;
		} else if (ii == 3) {
			if(data_list_car3[i].vehicle_code == '外租')
				jQuery("#vehicle_ln").val(data_list_car3[i].vehicle_ln);
			else
				jQuery("#vehicle_ln").val(data_list_car3[i].vehicle_ln+"("+data_list_car3[i].vehicle_code+")");
			jQuery("#vehicle_vin").val(data_list_car3[i].VIN);
			jQuery("input[name=turnonvehiclechico][value="+data_list_car3[i].send_condition+"]").attr("checked",'checked');
			jQuery("#send_time_s").val(data_list_car3[i].send_time);
			jQuery("#routecarseqordernum").val(data_list_car3[i].send_order);
			add_flag = data_list_car3[i].add_flag;
			jQuery("#addupdatetimetype").val(add_flag);
			jQuery("#send_s_time_s").val(data_list_car3[i].start_time);
			jQuery("#send_e_time_s").val(data_list_car3[i].end_time);
			//jQuery("#vehicle_code").html(data_list_car3[i].vehicle_code);
			jQuery("#driver_name").html("驾驶员："+drivernamenull(data_list_car3[i].driver_name));
			jQuery("#driver_id").val(data_list_car3[i].driver_id);
			if(selectVIN != data_list_car3[i].VIN) {
				usedtyp = 0;
				jQuery("#routecarseqordernum").html("<option>"+data_list_car3[i].send_order+"</option>");
				document.getElementById("send_s_time_s").disabled = true;
				document.getElementById("send_e_time_s").disabled = true;
			} else {
				//if((data_list_car3[i].updriver_id!=null&&data_list_car3[i].updriver_id.length>0)||(data_list_car3[i].status!=null&&data_list_car3[i].status!='0')) {
				if(data_list_car3[i].status!=null&&data_list_car3[i].status!='0') {//即时车辆有驾驶员上报也允许修改
					runningdriver = false;
					document.getElementById("popinfoaddupdatesure").disabled = true;
					document.getElementById("popinfodelbut").disabled = true;
				}
				if(data_list_car3[i].add_flag == 0) {
					document.getElementById("send_e_time_s").disabled = true;
				}else if(data_list_car3[i].add_flag == 1) {
					document.getElementById("send_e_time_s").disabled = false;
				}
			}
			showdetailinfov =showcarchangeinfo3;
		}
		if(dddd == '2') {
			document.getElementById("send_condition_1").disabled = true;
			document.getElementById("send_condition_span").disabled = true;
			/* jQuery("input[name=turnonvehiclechico][value=1]").attr("checked",'checked'); */
		} else {
			document.getElementById("send_condition_1").disabled = false;
			document.getElementById("send_condition_span").disabled = false;
		}
		if(dddd == '1'||dddd == '0') {
			document.getElementById("send_condition_2").disabled = true;
			document.getElementById("send_condition_span2").disabled = true;
			/* jQuery("input[name=turnonvehiclechico][value=0]").attr("checked",'checked'); */
		} else {
			document.getElementById("send_condition_2").disabled = false;
			document.getElementById("send_condition_span2").disabled = false;
		}
		jQuery("#vehicle_ln").unbind();
		jQuery("#driver_name").css("color","black").unbind();
		document.getElementById("driver_name").disabled = true;
		if(usedtyp==1)
			addorderinfoto(i,ii,2);
		jQuery("#route_list_i").val(i);
		jQuery("#route_list_ii").val(ii);
		jQuery("#addupdatetype").val("2");
		document.getElementById("vehicle_ln").disabled = true;
		if(usedtyp==0) {
			jQuery("#popinfoaddupdatesure").hide();
			jQuery("#popinfodelbut").hide();
		} else {
			if(starttimes>=lktimes) {
				jQuery("#popinfoaddupdatesure").show();
				jQuery("#popinfodelbut").show();
				/* if(add_flag==1)
					jQuery("#popinfodelbut").show();
				else if(add_flag==0)
					jQuery("#popinfodelbut").hide(); */
			} else {
				jQuery("#popinfoaddupdatesure").hide();
				jQuery("#popinfodelbut").hide();
			}
		}
		if(showdetailinfov=='1') {
			document.getElementById("send_s_time_s").disabled = true;
			document.getElementById("send_e_time_s").disabled = true;
			document.getElementById("vehicle_ln").disabled = true;
			
			document.getElementById("send_condition_1").disabled = true;
			document.getElementById("send_condition_2").disabled = true;
			document.getElementById("send_condition_3").disabled = true;
			document.getElementById("send_condition_span").disabled = true;
			document.getElementById("send_condition_span2").disabled = true;
			document.getElementById("send_time_s").disabled = true;
			document.getElementById("routecarseqordernum").disabled = true;
			
			document.getElementById("send_s_time_s").disabled = true;
			document.getElementById("send_e_time_s").disabled = true;
			
			document.getElementById("popinfoaddupdatesure").disabled = true;
			document.getElementById("popinfodelbut").disabled = true;
			
			/* document.getElementById("route_driver_s").disabled = true; */
		} else {
			if(starttimes>=lktimes&&runningdriver) {
				document.getElementById("driver_name").disabled = false;
				jQuery("#driver_name").css("color","#0078c8").bind("click",function(){
					searchDriver();
				});
			}
		}
	}
	function cutvehicleln(v){
		return v.length>8?v.substring(0,8):v;
	}
	function add_flag_check(v) {
		return v==0?"":"(临)";
	}
	function isonlinecolor(v,type) {
		var back = v=="offline"?"tableroute_car_info_offline":"tableroute_car_info_online";
		if(type=='1') 
			return "tableroute_car_info_online";
		else {
			return back;
		}
	}
	function changenosend(s) {
		//0失败 1成功
		return s==null?"0":s=="-1"?"0":s;
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
		tname += info.add_flag==0?"":"(临)";
		return tname;
	}
	function getcarlinemove(v,status,type) {
		//先判断是否是已执行 如果不是	判断是否是执行中
		if(v!=null&&v==1) {
			return "取消发车";
		} else {
			if(status!=null) {
				if(status=='1') {
					return "执行中";
				}
				if(status=='2') {
					return "已执行";
				} else {
					return "未执行";
				}
			}
		}
		/* if(type!=null&&type=='1')
			return "取消发车"; */
		return "";
	}
	function checklimitnum(num) {
		if(num == null)
			return '0';
		else
			return num;
	}
	function sendifsuccessbgsrc(crc,info,type) {
		if(type!=null&&type=='1') {
			return "<div class='tableroute_car_info'>";
		}
		if(crc==null||crc.length<1) {
			return "<div class='tableroute_car_info_nosend'>";
		} else {
			if(info!=null&&info=='1')
				return "<div class='tableroute_car_info_nosend'>";
			else
				return "<div class='tableroute_car_info'>";
		}
	}
	function sendifsuccess(crc,info,vehitype) {
		if(vehitype!=null&&vehitype=='1') {
			return "";
		}
		if(crc==null||crc.length<1) {
			return "";
		} else {
			if(info==null||info=='0') {
				return "<img title='尚未下发成功！' style='height:15px;vertical-align: middle;' src='../images/pageslipe/redflag.png'/>";
			}else
				return "";
		}
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
	function checklimitn(v) {
		return v==null?0:v;
	}
	var reload_tongqin = null;
	var reload_wid = null;
	var reload_htmlinfo = "";
	var commondx_send = "";
	function reloadlistcar(data_list,va) {
		var starttimes = getnowandsearcht("search");
	    
	    var lktimes = getnowandsearcht("now");

		if(va==1) {
			reload_tongqin = jQuery("#tonqincar");
		}else if(va==2){
			reload_tongqin = jQuery("#tonqincar2");
		}else if(va==3){
			reload_tongqin = jQuery("#tonqincar3");
		}
		
		reload_tongqin.empty();
		
		//var html = "";
		
		/* 车辆查询线路存在车辆一定存在 */
		if(va==1) {
			var changes = jQuery('<div></div>').attr('id', "tonqincar_change").addClass("tonqincar_list_change").appendTo(reload_tongqin);
			/* if(starttimes==lktimes) {
				commondx_send = '<div style="padding-left: 20px;padding-top: 10px;"><a href="javascript:void(0);" style="'+changesendtype_show(showcarchangeinfo)+'" id="showaddbutton_check_1" class="scommondbutton" onclick="sendcommondbt(1)">下&nbsp;发</a></div>';
				jQuery(commondx_send).appendTo(changes);
			} */
			if(starttimes>=lktimes) {
				commondx_send = '<div style="padding-left: 20px;padding-top: 10px;"><a href="javascript:void(0);" style="'+changesendtype_show(showcarchangeinfo)+'" id="showaddbutton_check_1" class="scommondbutton" onclick="sendcommondbt(1)">下&nbsp;发</a></div>';
				jQuery(commondx_send).appendTo(changes);
				/* <s:if test="#session.perUrlList.contains('111_3_3_2_3')"> */
				var divshow = "<div style='padding-left: 20px;padding-top: 10px;'><a id='buttonchangecar1' class='scommondbutton' href='javascript:void(0);' onclick='showchangebutton(this,1);' >"+changetypen(showcarchangeinfo)+"</a></div>";
				jQuery(divshow).appendTo(changes);
				/* </s:if> */
			}
		}else if(va==2) {
			var changes = jQuery('<div></div>').attr('id', "tonqincar_change2").addClass("tonqincar_list_change").appendTo(reload_tongqin);
			/* if(starttimes==lktimes) {
				commondx_send = '<div style="padding-left: 20px;padding-top: 10px;"><a href="javascript:void(0);" style="'+changesendtype_show(showcarchangeinfo2)+'" id="showaddbutton_check_2" class="scommondbutton" onclick="sendcommondbt(2)">下&nbsp;发</a></div>';
				jQuery(commondx_send).appendTo(changes);
			} */
			if(starttimes>=lktimes) {
				commondx_send = '<div style="padding-left: 20px;padding-top: 10px;"><a href="javascript:void(0);" style="'+changesendtype_show(showcarchangeinfo2)+'" id="showaddbutton_check_2" class="scommondbutton" onclick="sendcommondbt(2)">下&nbsp;发</a></div>';
				jQuery(commondx_send).appendTo(changes);
				/* <s:if test="#session.perUrlList.contains('111_3_3_2_3')"> */
				var divshow = "<div style='padding-left: 20px;padding-top: 10px;'><a id='buttonchangecar2' class='scommondbutton' href='javascript:void(0);' onclick='showchangebutton(this,2);' >"+changetypen(showcarchangeinfo2)+"</a></div>";
				jQuery(divshow).appendTo(changes);
				/* </s:if> */
			}
		}else if(va==3) {
			var changes = jQuery('<div></div>').attr('id', "tonqincar_change3").addClass("tonqincar_list_change").appendTo(reload_tongqin);
			/* if(starttimes==lktimes) {
				commondx_send = '<div style="padding-left: 20px;padding-top: 10px;"><a href="javascript:void(0);" style="'+changesendtype_show(showcarchangeinfo3)+'" id="showaddbutton_check_3" class="scommondbutton" onclick="sendcommondbt(3)">下&nbsp;发</a></div>';
				jQuery(commondx_send).appendTo(changes);
			} */
			if(starttimes>=lktimes) {
				commondx_send = '<div style="padding-left: 20px;padding-top: 10px;"><a href="javascript:void(0);" style="'+changesendtype_show(showcarchangeinfo3)+'" id="showaddbutton_check_3" class="scommondbutton" onclick="sendcommondbt(3)">下&nbsp;发</a></div>';
				jQuery(commondx_send).appendTo(changes);
				/* <s:if test="#session.perUrlList.contains('111_3_3_2_3')"> */
				var divshow = "<div style='padding-left: 20px;padding-top: 10px;'><a id='buttonchangecar3' class='scommondbutton' href='javascript:void(0);' onclick='showchangebutton(this,3);' >"+changetypen(showcarchangeinfo3)+"</a></div>";
				jQuery(divshow).appendTo(changes);
				/* </s:if> */
			}
		}

		if(data_list.length>5)
			reload_wid = data_list.length * 175+70;
		else
			reload_wid = "auto";
		
		var station = jQuery('<div></div>').attr('id', "tonqincar_list").addClass("tonqincar_list").appendTo(reload_tongqin);//css("float","left").css("width","100%")
		var div = jQuery('<div></div>').attr('id', "tonqincar_list_cl"+va).addClass("car_list_style").css("width",reload_wid).appendTo(station);

		reload_htmlinfo = "<ul>";
		reload_htmlinfo += "<li style='position:relative;width:60px;float:left;margin-left:10px;'>";
		reload_htmlinfo +="<div style='height:35px;line-height:35px;'><b>发车安排:</b></div>";
		reload_htmlinfo +="</li>";
		if (data_list && data_list.length > 0) {
			for(var v=0;v<data_list.length;v++) {
				var info = data_list[v];
				if(info.VIN == selectVIN) {
					jQuery("#vehicle_code_car_num").html("&nbsp;&nbsp;核载:"+checklimitn(info.limite_number)+"人");
					jQuery("#checkedvehiclename").children("a").css("color","black");//info.car_state=='offline'?"black":"#0078c8"
				}
				reload_htmlinfo += "<li style='position:relative;width:170px;float:left;'>";
				reload_htmlinfo += sendifsuccessbgsrc(info.crc,info.valid_flag,info.vehicle_type);//<div class='tableroute_car_info'>";
			
				/* if(changenosend(info.mesg_flag) == '0') {
					reload_htmlinfo += "<div id='"+info.VIN+va+"dpops' class='tableroute_car_info_red'>"+info.vehicle_code+"</div>";
				} else {
					reload_htmlinfo += "<div id='"+info.VIN+va+"dpops' class='tableroute_car_info_grey'>"+info.vehicle_code+"</div>";
				} */
				reload_htmlinfo +="<div id='car"+info.VIN+"' class='tableroute_car_info_s'>";
				reload_htmlinfo +="<span><a href='javascript:void(0);' class='"+isonlinecolor(info.car_state,info.vehicle_type)+"' >"+sendvehicle_codenull(info.vehicle_type,info.vehicle_code)+"</a>";
				if(starttimes==lktimes) {
					reload_htmlinfo +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;' onclick='onevmouse_click(this,\""+info.VIN+"\",\""+info.vehicle_type+"\");'>"+cutvehicleln(info.vehicle_ln)+"("+checklimitnum(info.limite_number)+"座)";
				} else {
					reload_htmlinfo +="<a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;'>"+cutvehicleln(info.vehicle_ln)+"("+info.limite_number+"座)";
				}
				reload_htmlinfo +="</a></span>";
				
				reload_htmlinfo +="<span><a href='javascript:void(0);' class='tableroute_car_info_go_l'>"+getcarlinemove(info.valid_flag,info.status,info.vehicle_type)+"</a><a href='javascript:void(0);' class='tableroute_car_info_ina' style='color:black;' onclick='updateroutecarinfo("+v+","+va+")'>"+sendtypechoice(info);
				
				if(starttimes==lktimes) {
					reload_htmlinfo +=sendifsuccess(info.crc,info.mesg_flag,info.vehicle_type)+"</a></span>";
				} else {
					reload_htmlinfo +="</a></span>";
				}
					
				reload_htmlinfo +="</div></div></li>";
				if(starttimes>=lktimes) {
					reload_htmlinfo +="<li style='position:relative;width:26px;float:left;'>";
					if(va==1) {
						if(showcarchangeinfo == '1')
							reload_htmlinfo +="<a class='changecarinfobutton' style='display:none;' href='javascript:void(0);' onclick='addcarlist_onlycheckcar("+v+","+va+")'></a>";
						else
							reload_htmlinfo +="<a class='changecarinfobutton' href='javascript:void(0);' onclick='addcarlist_onlycheckcar("+v+","+va+")'></a>";
					} else if(va==2) {
						if(showcarchangeinfo2 == '1')
							reload_htmlinfo +="<a class='changecarinfobutton2' style='display:none;' href='javascript:void(0);' onclick='addcarlist_onlycheckcar("+v+","+va+")'></a>";
						else
							reload_htmlinfo +="<a class='changecarinfobutton2' href='javascript:void(0);' onclick='addcarlist_onlycheckcar("+v+","+va+")'></a>";
					} else if(va==3) {
						if(showcarchangeinfo3 == '1')
							reload_htmlinfo +="<a class='changecarinfobutton3' style='display:none;' href='javascript:void(0);' onclick='addcarlist_onlycheckcar("+v+","+va+")'></a>";
						else
							reload_htmlinfo +="<a class='changecarinfobutton3' href='javascript:void(0);' onclick='addcarlist_onlycheckcar("+v+","+va+")'></a>";
					}
					reload_htmlinfo += "</li>";
				}
			}
			jQuery(reload_htmlinfo+"</ul>").appendTo(div);
		}
	}
	function changetypen(v) {
		return v=='1'?"调&nbsp;整":v=='2'?"撤销调整":"";
	}
	function changesendtype_show(v) {
		return v=='1'?"display:none;":"";
	}
	function clear_rm(v) {
		if(v==1) {
			jQuery('#rm_route').mk_routemonitor('clear');
			jQuery('#buttonchangecar1').remove();
			jQuery('#tonqincar #tonqincar_change').empty();
			jQuery("#tonqincar #tonqincar_list").empty();
		}else if(v==2) {
			jQuery('#rm_route2').mk_routemonitor('clear');
			jQuery('#buttonchangecar2').remove();
			jQuery('#tonqincar2 #tonqincar_change2').empty();
			jQuery("#tonqincar2 #tonqincar_list").empty();
		}else if(v==3) {
			jQuery('#rm_route3').mk_routemonitor('clear');
			jQuery('#buttonchangecar3').remove();
			jQuery('#tonqincar3 #tonqincar_change3').empty();
			jQuery("#tonqincar3 #tonqincar_list").empty();
		}
	}

	/* 实体对象定义*/
	function mk_routemonitor(routeid, up_site_arr, down_site_arr, up_car_arr, down_car_arr) {
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
	jQuery(function() {
		jQuery('#rm_route').mk_routemonitor({
				line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
				'station_callback' : function() {
					sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
					//alert('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
					// console.log('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
					//onevmouse_click("YZB12345678912346");
				},
				'car_callback' : function() {
					//alert(jQuery(this).attr('data-vin'));
					//alert('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
					// console.log('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
					//onevmouse_click(jQuery(this).attr('data-vin'));
				},
				"up_text":"",
				"tonqincar":"tonqincar",
				"add_cars":true,
				"car_color":true
		});
		jQuery('#rm_route2').mk_routemonitor({
			line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
			'station_callback' : function() {
				sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
				//alert('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
				// console.log('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
				//onevmouse_click("YZB12345678912346");
			},
			'car_callback' : function() {
				//alert(jQuery(this).attr('data-vin'));
				//alert('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
				// console.log('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
				//onevmouse_click(jQuery(this).attr('data-vin'));
			},
			"up_text":"",
			"route_up_id" : 'mk_routemonitor_route_up2',
			"up_station_id" : 'mk_up_station2',
			"route_plan_class" : 'mk-rm-route-plan',
			"route_class" : 'mk-rm-route',
			"route_title_class" : 'mk-rm-route-til',
			"route_station_class" : 'mk-rm-route-station',
			"tonqincar":"tonqincar2",
			"add_cars":true,
			"car_color":true
		});
		jQuery('#rm_route3').mk_routemonitor({
			line_id : 'line_init', // 可以在初始化的时候设置也可以在reload时设置
			'station_callback' : function() {
				sitebrwose(jQuery(this).attr('data-site-id'), jQuery(this).attr('data-site-name'), jQuery(this).attr('data-up-down'));
				//alert('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
				// console.log('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
				//onevmouse_click("YZB12345678912346");
			},
			'car_callback' : function() {
				//alert(jQuery(this).attr('data-vin'));
				//alert('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
				// console.log('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
				//onevmouse_click(jQuery(this).attr('data-vin'));
			},
			"up_text":"",
			"route_up_id" : 'mk_routemonitor_route_up3',
			"up_station_id" : 'mk_up_station3',
			"route_plan_class" : 'mk-rm-route-plan',
			"route_class" : 'mk-rm-route',
			"route_title_class" : 'mk-rm-route-til',
			"route_station_class" : 'mk-rm-route-station',
			"tonqincar":"tonqincar3",
			"add_cars":true,
			"car_color":true
		});
		
		
	});
	function sendcar_return(vin,ii) {
		if(ii == '1') {
			for(var v=0;v<data_list_car.length;v++) {
				if(data_list_car[v].VIN == vin&&data_list_car[v].send_condition == '1') 
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
		}
		return false;
	}
	function checkInputifchange(i,ii) {
		if(ii == '1') {
			if(data_list_car[i].VIN != jQuery("#vehicle_vin").val()||data_list_car[i].send_condition !=jQuery("input[name=turnonvehiclechico]:checked").val()) 
				return true;
			if(data_list_car[i].send_order != jQuery("#routecarseqordernum").val()) 
				return true;
			if(data_list_car[i].driver_id != jQuery("#driver_id").val()) 
				return true;
			if(data_list_car[i].end_time != jQuery("#send_e_time_s").val()) 
				return true;
			if(data_list_car[i].send_condition == '2') {
				if(data_list_car[i].send_time != jQuery("#send_time_s").val())
					return true;
			}
		} else if(ii == '2') {
			if(data_list_car2[i].VIN != jQuery("#vehicle_vin").val()||data_list_car2[i].send_condition !=jQuery("input[name=turnonvehiclechico]:checked").val()) 
				return true;
			if(data_list_car2[i].send_order != jQuery("#routecarseqordernum").val()) 
				return true;
			if(data_list_car2[i].driver_id != jQuery("#driver_id").val()) 
				return true;
			if(data_list_car2[i].end_time != jQuery("#send_e_time_s").val()) 
				return true;
			if(data_list_car2[i].send_condition == '2') {
				if(data_list_car2[i].send_time != jQuery("#send_time_s").val())
					return true;
			}
		} else if(ii == '3') {
			if(data_list_car3[i].VIN != jQuery("#vehicle_vin").val()||data_list_car3[i].send_condition !=jQuery("input[name=turnonvehiclechico]:checked").val()) 
				return true;
			if(data_list_car3[i].send_order != jQuery("#routecarseqordernum").val()) 
				return true;
			if(data_list_car3[i].driver_id != jQuery("#driver_id").val()) 
				return true;
			if(data_list_car3[i].end_time != jQuery("#send_e_time_s").val()) 
				return true;
			if(data_list_car3[i].send_condition == '2') {
				if(data_list_car3[i].send_time != jQuery("#send_time_s").val())
					return true;
			}
		}
		return false;
	}
</script>