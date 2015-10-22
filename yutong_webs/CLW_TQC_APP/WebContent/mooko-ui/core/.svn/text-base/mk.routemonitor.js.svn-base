/**
 * |- 线路监控 |- mk.routemonitor.js |- RouteMonitor |- 参数： |- width_exclude:
 * 弹出层相距页面顶部距离，默认为100px |- width_include: 弹出层宽度，默认为500px |- width_offset:
 * 弹出层高度，默认为500px |- height_exclude: 弹出层欲加载的页面地址，默认为空 |- height_include:
 * 是否当鼠标离开自动隐藏 |- height_offset |- 依赖： |- Parser: mk.parser.js |- 2012-08-14 |-
 * Author: <a mailto="hegq@neusoft.com">Puras.He</a>
 */
(function($, undefined) {
	function init(target) {
		if($.data(target, 'mk-routemonitor')&&$.data(target, 'mk-routemonitor').options) {
		var opts = $.data(target, 'mk-routemonitor').options;
		var elm = $(target);
		elm.addClass(opts.route_plan_class); // 增加线路外层div
		var route_up = $('<div></div>').attr('id', opts.route_up_id).addClass(opts.route_class); // // 上学
		//var route_down = $('<div></div>').attr('id', opts.route_down_id).addClass(opts.route_class); // .text(opts.down_text); // 放学
		$('<div></div>').addClass(opts.route_title_class).text(opts.up_text).appendTo(route_up);
		
		
		//$('<div></div>').addClass(opts.route_title_class).text(opts.down_text).appendTo(route_down);
		// var up_station = $('<div></div>').attr('id',
		// opts.up_station_id).addClass(opts.route_station_class).appendTo(route_up);
		// var down_station = $('<div></div>').attr('id',
		// opts.down_station_id).addClass(opts.route_station_class).appendTo(route_down);
		route_up.appendTo(elm);
		//route_down.appendTo(elm);

		//新增通勤车辆 height: 90px; overflow-y: auto;
		var route_up_car = $('<div></div>').attr('id', opts.tonqincar).addClass("tonqincar_CSS");//absolute
		
		jQuery('<div></div>').attr('id', "tonqincar_change").addClass("tonqincar_list_change").appendTo(route_up_car);
		jQuery('<div></div>').attr('id', "tonqincar_list").addClass("tonqincar_list").appendTo(route_up_car);
		route_up_car.appendTo(elm);
		
		__resize(target);
		// up: 0, down: 1
		__add_all_station(target);

		$(window).resize(function() {
			__resize(target);
		});
		}
	}

	function __resize(target) {
		if($.data(target, 'mk-routemonitor')&&$.data(target, 'mk-routemonitor').options) {
		var opts = $.data(target, 'mk-routemonitor').options;
		var width = 0;
		if (opts.up_data && opts.down_data) {
			width = opts.up_data.length > opts.down_data.length ? opts.up_data.length
					* opts.station_length
					: opts.down_data.length * opts.station_length;
		} else if (opts.up_data) {
			width = opts.update.length * opts.station_length;
		} else if (opts.down_data) {
			width = opts.down_data.length * opts.station_length;
		}
		width = width + 85;
		if (width < $(target).width()) {
			width = $(target).width() -5;//4 40
		}
		//$('#' + opts.route_up_id).width(width);
		//$('#' + opts.tonqincar).width(width);
		}
	}

	function __add_all_station(target) {
		if($.data(target, 'mk-routemonitor')&&$.data(target, 'mk-routemonitor').options) {
		var opts = $.data(target, 'mk-routemonitor').options;
		// 在添加站点之前，将车辆重合时生成的层清除
		__clear_route(target);
		__add_station(target, opts.up_flag);
		//__add_station(target, opts.down_flag);
		}
	}
	function sitenamecut(name) {
		if(jQuery.trim(name).length > 8)
			return jQuery.trim(name).substring(0,8) + "...";
		else
			return jQuery.trim(name);
	}
	function __add_station(target, flag) {
		if($.data(target, 'mk-routemonitor')&&$.data(target, 'mk-routemonitor').options) {
		var opts = $.data(target, 'mk-routemonitor').options;
		var route = null;
		var station_id = '';
		var data_list = null;
		if (flag == opts.up_flag) {
			route = $('#' + opts.route_up_id);
			station_id = opts.up_station_id;
			data_list = opts.up_data;
		} else if (flag == opts.down_flag) {
			route = $('#' + opts.route_down_id);
			station_id = opts.down_station_id;
			data_list = opts.down_data;
		}
		var station = $('<div></div>').attr('id', station_id).addClass(opts.route_station_class).appendTo(route);
		var station_sn = $('<div></div>').css("float","left").css("margin-top","25px").width(60).appendTo(station);
		var station_site_sn = $('<div><b>站点分布:</b></div>').appendTo(station_sn);
		if (data_list && data_list.length > 0) {
			var station_list = $('<ul></ul>').css("margin-top","20px").appendTo(station);
			if (data_list.length == 1) {
				var site = data_list[0];
				var bg_class = '';
				var station = $('<li></li>').attr('id', site.id).addClass(bg_class).appendTo(station_list);
				// edit by suyingtao 20121030 begin
				var station_site = $('<div></div>').addClass('mk-rm-site').appendTo(station);

				var station_btn = $('<div></div>').addClass('mk-rm-btn').appendTo(station_site);
				var site_btn_class = 'mk-rm-site-blue';
				if (site.status == 0)
					site_btn_class = 'mk-rm-site-green';
				if (site.status == 1)
					site_btn_class = 'mk-rm-site-yellow';
				if (site.status == 3)
					site_btn_class = 'mk-rm-site-red';
				if (site.status == 2)
					site_btn_class = 'mk-rm-site-purple';
				$('<a href="javascript:void(0);"></a>').addClass(site_btn_class).appendTo(station_btn).attr(
				{
					'data-line-id' : opts.line_id,
					'data-site-id' : site.id,
					'data-site-name' : site.name,
					'data-up-down' : (flag == opts.up_flag ? 'up' : 'down')
				}).bind('click', opts.station_callback);

				var station_name = $('<div></div>').appendTo(station_site);
				$('<a href="javascript:void(0);" title="'+site.name+'"></a>').text(sitenamecut(site.name)).appendTo(station_name).attr({
					'data-line-id' : opts.line_id,
					'data-site-id' : site.id,
					'data-site-name' : site.name,
					'data-up-down' : (flag == opts.up_flag ? 'up' : 'down')
				}).bind('click', opts.station_callback);
				// edit by suyingtao 20121030 end
				/*
				 * var btn_class = 'mk-rm-btn-green'; if (site.status == 1)
				 * btn_class = 'mk-rm-btn-blue'; if (site.status == 2) btn_class =
				 * 'mk-rm-btn-yellow'; if (site.status == 3) btn_class =
				 * 'mk-rm-btn-red'; var station_btn = $('<div></div>').addClass(btn_class).appendTo(station);
				 * $('<a href="javascript:void(0);"></a>').text(site.name).appendTo(station_btn).attr({
				 * 'data-line-id': opts.line_id, 'data-site-id': site.id,
				 * 'data-site-name': site.name, 'data-up-down': (flag ==
				 * opts.up_flag ? 'up' : 'down') }).bind('click',
				 * opts.station_callback);
				 */
			} else {
				$.each(data_list,function(idx) {
					var site = data_list[idx];
					var bg_class = 'line-bg-mid';
					if (idx == 0)
						bg_class = 'line-bg-left';
					if (idx == data_list.length - 1)
						bg_class = 'line-bg-right';
					var station = $('<li></li>').attr('id',site.id).addClass(bg_class).appendTo(station_list);

					// edit by suyingtao 20121030 begin
					var station_site = $('<div></div>').addClass('mk-rm-site').appendTo(station);

					var station_btn = $('<div></div>').addClass('mk-rm-btn').appendTo(station_site);
					var site_btn_class = 'mk-rm-site-blue';
					if(opts.add_cars) {
						if(opts.car_color) {
							if (site.status == 0)
								site_btn_class = 'mk-rm-site-green';
							if (site.status == 1)
								site_btn_class = 'mk-rm-site-yellow';
							if (site.status == 2)
								site_btn_class = 'mk-rm-site-red';
							if (site.status == 3)
								site_btn_class = 'mk-rm-site-purple';
						}
					}
					$('<a href="javascript:void(0);"></a>').addClass(site_btn_class).appendTo(station_btn).attr({
						'data-line-id' : opts.line_id,
						'data-site-id' : site.id,
						'data-site-name' : site.name,
						'data-up-down' : (flag == opts.up_flag ? 'up': 'down')
					}).bind('click',opts.station_callback);

					var station_name = $('<div></div>').appendTo(station_site);
					$('<a href="javascript:void(0);" title="'+site.name+'"></a>').text(sitenamecut(site.name)).appendTo(station_name).attr({
						'data-line-id' : opts.line_id,
						'data-site-id' : site.id,
						'data-site-name' : site.name,
						'data-up-down' : (flag == opts.up_flag ? 'up'
								: 'down')
					}).bind('click',opts.station_callback);
				});
			}
			if(opts.add_cars)
				__add_cars(target, flag);
		}
		}
	}
	function __reload_route(target, param) {
		if($.data(target, 'mk-routemonitor')&&$.data(target, 'mk-routemonitor').options) {
		var opts = $.data(target, 'mk-routemonitor').options;
		$.extend(opts, param);
		__resize(target);
		__add_all_station(target);
		}
	}
	function __change_cars(target, param) {
		if($.data(target, 'mk-routemonitor')&&$.data(target, 'mk-routemonitor').options) {
			var opts = $.data(target, 'mk-routemonitor').options;
			$.extend(opts, param);
			__resize(target);
			__add_all_station(target);
		}
	}

	function __add_cars(target, flag) {
		if($.data(target, 'mk-routemonitor')&&$.data(target, 'mk-routemonitor').options) {
		var opts = $.data(target, 'mk-routemonitor').options;
		var data_list = null;
		var route = null;
		if (flag == opts.up_flag) {
			route = $('#' + opts.route_up_id);
			data_list = opts.up_cars;
		} else if (flag == opts.down_flag) {
			route = $('#' + opts.route_down_id);
			data_list = opts.down_cars;
		}
		if (data_list && data_list.length > 0) {
			var sites = route.find('li');
			var site_width = route.find('li').width();
			var title_width = 50;//$('.'+opts.route_title_class).width()+70;
			var cars = new Array();
			$.each(data_list,function(idx) {
				var car = data_list[idx];

				var gcars = $.grep(cars,function(item, gidx) {
					var f = false;
					var car_arr = item.car_arr;
					$.each(car_arr,function(geidx) {
						var gecar = car_arr[geidx];
						f = (gecar.site_id == car.site_id && gecar.inout_flag == car.inout_flag);
					});
					return f;
				});
				if (gcars && gcars.length == 1) {
					var ccar = gcars[0];
					var idx_flag = 0;
					$.each(cars, function(cidx) {
						if (ccar.key == cars[cidx].key) {
							idx_flag = cidx;
							return;
						}
					});
					cars[idx_flag] = {
						'key' : ccar.key,
						'car_arr' : $.merge(ccar.car_arr,[ car ])
					};
				} else {
					cars[cars.length] = {
						'key' : car.site_id + '-'+ car.inout_flag,
						'car_arr' : [ car ]
					};
				}
			});
			$.each(cars,function(idx) {
				var car_obj = cars[idx];
				var car_d = null;
				//if(route.find(".mk-rm-route-station .mk-rm-box").length<1)
					car_d = $('<div></div>').attr('id',car_obj.key).addClass('mk-rm-box').appendTo(route.find(".mk-rm-route-station"));
				//else
					//car_d = route.find(".mk-rm-route-station .mk-rm-box");
				var car_num, car_ico;
				
				if (car_obj.car_arr.length == 1) {
					/*var car_num = $('<div></div>').addClass('mk-rm-bus-num').text(car_obj.car_arr[0].vehicle_ln).appendTo(car_d);*/
					var car_num = $('<a></a>').addClass('mk-rm-bus-num').text(car_obj.car_arr[0].vehicle_ln).appendTo(car_d);
					car_num.hide();
					var car_ico = $('<a href="javascript:void(0);" data-vin="'+ car_obj.car_arr[0].vin+ '" data-vehicle_ln="'
									+ car_obj.car_arr[0].vehicle_ln + '"></a>').addClass('mk-rm-bus').appendTo(car_d);
					
					car_ico.bind('click', opts.car_callback);
					car_ico.hover(function() {jQuery(this).prev().show();
						},function() {jQuery(this).prev().hide();
					});
					
					var site_idx = 1;
					$.each(sites,function(sidx) {
						var site = sites[sidx];
						if ($(site).attr('id') == car_obj.car_arr[0].site_id) {
							site_idx = sidx + 1;
							return;
						}
					});
					var car_left = title_width + site_width * site_idx - site_width / 2
							+ site_width * car_obj.car_arr[0].inout_flag / 100 - 26;
					if (idx == 0) {
						car_d.css({
							'margin-left' : car_left,
							'margin-top' : -50
						});
					} else {
						car_d.css({
							'margin-left' : car_left,
							'margin-top' : -50
						});
					}
					car_num.css({
						'margin-top' : -55
					});
					
					car_ico.css({
						'margin-top' : -35,
						'margin-left' : 25
					});
					
					/*var le = opts.tonqincar.substring(opts.tonqincar.length-1,opts.tonqincar.length);
					if(le == '2' || le == '3') {
						$("#"+car_obj.car_arr[0].vin+le+"dpops").removeClass().addClass("tableroute_car_info_blue");
					}else if (le == 'r') {
						$("#"+car_obj.car_arr[0].vin+"1dpops").removeClass().addClass("tableroute_car_info_blue");
					}*/
					/*for(var le=1;le<4;le++) {
						if($("#"+car_obj.car_arr[0].vin+le+"dpops").length>0) {
							$("#"+car_obj.car_arr[0].vin+le+"dpops").removeClass().addClass("tableroute_car_info_blue");
						}
					}*/
				} else {
					var marginlefts = 13;
					for(var v=0;v<car_obj.car_arr.length;v++) {
						var car_num = $('<div></div>').addClass('mk-rm-bus-num').text(car_obj.car_arr[v].vehicle_ln).appendTo(car_d);
						car_num.hide();
						var car_ico = $('<a href="javascript:void(0);" data-vin="'+ car_obj.car_arr[v].vin+ '" data-vehicle_ln="'
								+ car_obj.car_arr[0].vehicle_ln
								+ '"></a>').addClass('mk-rm-bus').appendTo(car_d);
						
						car_ico.bind('click', opts.car_callback);
						car_ico.hover(
								function() {jQuery(this).prev().show();
							},function() {jQuery(this).prev().hide();
						});
						var site_idx = 1;
						$.each(sites,function(sidx) {
							var site = sites[sidx];
							if ($(site).attr('id') == car_obj.car_arr[0].site_id) {
								site_idx = sidx + 1;
								return;
							}
						});
						
						var car_left = title_width + site_width * site_idx - site_width / 2
								+ site_width * car_obj.car_arr[0].inout_flag / 100 - 26;
						if (idx == 0) {
							car_d.css({
								'margin-left' : car_left,
								'margin-top' : -50
							});
							marginlefts = marginlefts+12;
							car_ico.css({
								'margin-top' : -35,
								'margin-left' : marginlefts
							});
						} else {
							car_d.css({
								'margin-left' : car_left,
								'margin-top' : -50
							});
							car_ico.css({
								'margin-top' : -35,
								'margin-left' : 25
							});
						}
						car_num.css({
							'margin-top' : -55
						});
						
						/*var le = opts.tonqincar.substring(opts.tonqincar.length-1,opts.tonqincar.length);
						if(le == '2' || le == '3')
							$("#"+car_obj.car_arr[0].vin+le+"dpops").removeClass().addClass("tableroute_car_info_blue");
						else if (le == 'r') {
							$("#"+car_obj.car_arr[0].vin+"1dpops").removeClass().addClass("tableroute_car_info_blue");
						}*/
						/*for(var le=1;le<4;le++) {
							if($("#"+car_obj.car_arr[v].vin+le+"dpops").length>0) {
								$("#"+car_obj.car_arr[v].vin+le+"dpops").removeClass().addClass("tableroute_car_info_blue");
							}
						}*/
						
					}
					
					/*var car_num = $('<div></div>').addClass(
							'mk-rm-bus-num').text('......')
							.css('cursor', 'pointer').appendTo(
									car_d);
					var car_list_parent = $('<div></div>').addClass(opts.route_car_num_list).hide().appendTo($('body'));
					car_list_parent.css({
						'background-color' : '#fff',
						'padding' : '5px'
					});
					var car_list = $('<ul></ul>').appendTo(car_list_parent);
					car_list_parent.bind('mouseleave',function() {
						car_list_parent.hide();
					})
					$.each(car_obj.car_arr, function(inidx) {
						var incar = car_obj.car_arr[inidx];
						var car_li = $('<li></li>').appendTo(car_list);
						$('<a href="javascript:void(0);" data-vin="'
							+ incar.vin
							+ '" data-vehicle_ln="'
							+ incar.vehicle_ln
							+ '"></a>').text(incar.vehicle_ln).appendTo(car_li).bind('click',opts.car_callback);
					});
					car_num.bind('click',function(e) {
						var xx = e.clientX+ document.body.scrollLeft- document.body.clientLeft|| 0;
						var yy = e.clientY+ document.body.scrollTop- document.body.clientTop|| 0;
						car_list_parent.css({
							'position' : 'absolute',
							'left' : xx,
							'top' : yy,
							'z-index' : 999
						});
						car_list_parent.show();
					});
					var car_ico = $('<a href="javascript:void(0);"></a>').addClass('mk-rm-bus').appendTo(car_d);*/
				}

				
			});
		}
		}
	}

	function __clear_route(target) {
		if($.data(target, 'mk-routemonitor')&&$.data(target, 'mk-routemonitor').options) {
			var opts = $.data(target, 'mk-routemonitor').options;
	
			$('#' + opts.route_up_id).children().not('.' + opts.route_title_class).remove();
			//$('#' + opts.route_down_id).children().not('.' + opts.route_title_class).remove();
			$('.' + opts.route_car_num_list).remove();
		}
	}

	$.fn.mk_routemonitor = function(options, param) {
		if (typeof options == 'string') {
			return $.fn.mk_routemonitor.methods[options](this, param);
		}

		options = options || {};
		return this.each(function() {
			var state = $.data(this, 'mk-routemonitor');
			if (state) {
				$.extend(state.options, options);
			} else {
				$.data(this, 'mk-routemonitor', {
					options : $.extend({}, $.fn.mk_routemonitor.defaults,
							$.fn.mk_routemonitor.parse_options(this), options)
				});
			}
			init(this);
		});
	};
	$.fn.mk_routemonitor.parse_options = function() {
	};

	$.fn.mk_routemonitor.methods = {
		reload : function(elm, param) {
			return elm.each(function() {
				__reload_route(this, param);
			});
		},
		change_cars : function(elm, param) {
			return elm.each(function() {
				__change_cars(this, param);
			});
		},
		clear : function(elm) {
			return elm.each(function() {
				__clear_route(this);
			});
		},
		reloadcar : function(elm, param) {
			return __add_station_car(this, param);
		}
	};
	$.fn.mk_routemonitor.defaults = {
		route_up_id : 'mk_routemonitor_route_up',
		route_down_id : 'mk_routemonitor_route_down',
		up_station_id : 'mk_up_station',
		down_station_id : 'mk_down_station',
		route_plan_class : 'mk-rm-route-plan',
		route_class : 'mk-rm-route',
		route_title_class : 'mk-rm-route-til',
		route_station_class : 'mk-rm-route-station',
		route_car_num_list : 'mk-rm-car-num-list',
		up_flag : 0,
		up_text : '上班',
		down_flag : 1,
		down_text : '放学',
		station_length : 140,
		station_callback : function() {
		},
		car_callback : function() {
		},
		line_id : null,
		up_data : null,
		down_data : null,
		up_cars : null,
		down_cars : null,
		tonqincar:null,
		add_cars:false,
		car_color:false
	};
})(jQuery);