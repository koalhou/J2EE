var newOilControl = function() {
	this.mapFlag = 0;
	this.ftleL;
	this.mapabc = new mapabcObjdiv();
};
var so = null;
newOilControl.prototype = {
	/** 页面初始化时间 */

	loadMap : function(latitude, longitude, mapId, vin, state, type) {
		var that = this;
		var width = parseInt($('#rightImgPicChange').width());
		if (type == '0') {
			jQuery("#" + mapId).html("");
			that.mapabc.mapInitMorePoint2(longitude, latitude, vin, mapId,
					state, "map");
			that.mapFlag = 1;
		} else {
			that.mapabc.showMapNoOverlays2(longitude, latitude, vin, mapId,
					state, "map");
			// that.mapabc.maiInit(mapId);
		}
	},
	loadImg : function() {
		var that = this;
		var width = $('#leftImgPicChange').width();
		var start_time = jQuery("#start_time").val();
		var end_time = jQuery("#end_time").val();
		var vinCode = $("#vehicle_vin").val();
		var vehicleLn = $("#vehicle_ln").val();
		if ($.trim(start_time).length == 0 || $.trim(end_time).length == 0) {
			alert("时间不能为空");
			return false;
		}
		if (string2Date(start_time) > string2Date(end_time)) {
			alert("开始时间应早于结束时间！");
			return false;
		}
		if (GetDateDiff(start_time, end_time, 'day') > 5) {
			alert("查询时间段应控制在5天内！");
			return false;
		}
		$("#leftImgPicChange").empty();
		//$("#amline").css("display", "none");
		// $("#rightImgPicChange").html("");
		//$("#tmpImg").remove();
		jQuery.post('getOilInfo.shtml',
						{
							'vehicle_vin' : vinCode,
							'start_time' : start_time + ' 00:00:00',
							'end_time' : end_time + ' 23:59:59',
							'vehicle_ln' : vehicleLn
						},
						function(data) {
							var xmlDoc = eval(data)[0]["chartData"];

							// 地图
							var ftlyInfo = eval(data)[1]['ftlyInfo'];
							that.ftleL = ftlyInfo.split("-");
							if (xmlDoc != null && "null" == xmlDoc) {
								$("#leftImgPicChange")
										.append(
												'<img id="tmpImg" width="100%" height="200" src="../images/kcptImages/no_data2.png" style="position: relative;">');
							} else {
									so = new SWFObject(
											"../scripts/amcharts/amline.swf",
											"amline", width, "200", "10", "#FFFFFF");
									so.addVariable("path", "../scripts/amcharts/");
									so.addVariable("chart_data", xmlDoc);
									so.addVariable(
											"chart_settings",
											"<settings><decimals_separator>.</decimals_separator><add_time_stamp>true</add_time_stamp><background><color>#FFFFFF</color><alpha>100</alpha></background><plot_area><margins><top>40</top></margins></plot_area><labels><label><y>10</y><width>520</width><align>center</align><text><![CDATA[<b><font size='12'>"
											+ that.ftleL[that.ftleL.length - 1]
											+ "  油量趋势图(单位:L)"
											+ "</font></b>]]></text></label></labels><grid><y-left><approx_count>6</approx_count></y-left><y-right><approx_count>6</approx_count></y-right></grid></settings>");
									so.addVariable("preloader_color", "#000000");
									so.addParam("wmode", "transparent");
									so.write("leftImgPicChange");
							}
							var latitude = new Array();
							var longitude = new Array();
							var state = new Array();// ftleL.length
													// 地图上面坐标点最多可融入160-170个点
							if (that.ftleL.length > 1) {
								for ( var i = 0; i < that.ftleL.length - 1; i++) {
									var value = that.ftleL[i].split(',');
									latitude[i] = value[0];
									longitude[i] = value[1];
									state[i] = value[2];
								}
							}
							that.loadMap(latitude, longitude,
									"rightImgPicChange", vinCode, state,
									that.mapFlag);
						});
	},

	// 筛选地图上的加油偷油告警位置
	addOrLose : function() {
		var that = this;
		var vinCode = jQuery('#vehicle_vin').val();
		var latitudeCheckbox = new Array();
		var longitudeCheckbox = new Array();
		var stateCheckbox = new Array();
		var indexL = 0;
		if (that.ftleL.length > 1) {
			for ( var i = 0; i < that.ftleL.length - 1; i++) {
				var value = that.ftleL[i].split(',');
				if (document.getElementById("addOilCheckbox").checked == true) {
					if (value[2] == '10') {
						latitudeCheckbox[indexL] = value[0];
						longitudeCheckbox[indexL] = value[1];
						stateCheckbox[indexL] = value[2];
						indexL++;
					}
				}

				if (document.getElementById("loseOilCheckbox").checked == true) {
					if (value[2] == '001') {
						latitudeCheckbox[indexL] = value[0];
						longitudeCheckbox[indexL] = value[1];
						stateCheckbox[indexL] = value[2];
						indexL++;
					}
				}
			}

			that.loadMap(latitudeCheckbox, longitudeCheckbox,
					"rightImgPicChange", vinCode, stateCheckbox, that.mapFlag);
		}
	},
	maptoolbarIsshow : function(option) {
		var map = document.getElementById("maptoolbar");
		var down = document.getElementById("fudong_down");

		if (option == "up") {
			map.style.display = "none";
			down.style.display = "";
		} else if (option == "down") {
			map.style.display = "";
			down.style.display = "none";
		}

	}
};

function getDateParam() {
	var arr = [];
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e2) {
			xmlHttp = false;
		}
	}

	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest();
	}

	xmlHttp.open("GET", "null.txt", false);
	xmlHttp.setRequestHeader("Range", "bytes=-1");
	xmlHttp.send(null);

	severtime = new Date(xmlHttp.getResponseHeader("Date"));

	// 获取服务器日期
	var curYear = severtime.getFullYear();
	var curMonth = severtime.getMonth() + 1;
	var curDay = severtime.getDate();
	
	if (curMonth < 10) {
		curMonth = '0' + curMonth;
	}
	arr[0] = curYear + "-" + curMonth + "-" + curDay;

	//var dateP = new Date();
	severtime.setYear(curYear);
	//severtime.setMonth(curMonth);
	severtime.setDate(curDay);
	severtime.setDate(severtime.getDate() - 2);
	
	curMonth = severtime.getMonth()+1;
	
	if (curMonth < 10) {
		curMonth = '0' + curMonth;
	}
	
	arr[1] = severtime.getFullYear()
			+ "-"
			+ curMonth + "-" + severtime.getDate();

	return arr;
}

var newOilControlObj = null;
jQuery(function() {
	newOilControlObj = new newOilControl();
	var dataArr = getDateParam();
	jQuery("#start_time").val(dataArr[0]);
	jQuery("#end_time").val(dataArr[0]);
	newOilControlObj.loadImg();
});

function getSwfObjIds(id)
{
  var chartRef=null;
  if (navigator.appName.indexOf("Microsoft Internet")==-1) {
    if (document.embeds && document.embeds[id])
      chartRef = document.embeds[id]; 
	else
	chartRef  = window.document[id];
  }
  else {
    chartRef = window[id];
  }
  if (!chartRef)
	chartRef  = document.getElementById(id);
  
  return chartRef;
}

window.unload = function() {
	// delete newOilControlObj;
};
