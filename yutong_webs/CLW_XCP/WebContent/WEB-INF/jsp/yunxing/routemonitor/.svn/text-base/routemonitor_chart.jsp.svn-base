<%@page language="java" contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="<s:url value='../mooko-ui/themes/default/mk.routemonitor.css' />">
<script type="text/javascript" src="<s:url value="../mooko-ui/core/mk.routemonitor.js" />"></script>
<script type="text/javascript" src="<s:url value='/scripts/sidelayerMapUitl.js'/>"></script>
<script>
$(function() {
  $('#rm_route').mk_routemonitor({
    line_id: 'line_init', // 可以在初始化的时候设置也可以在reload时设置
    'station_callback': function() {
	  sitebrwose(jQuery(this).attr('data-site-id'),jQuery(this).attr('data-site-name'),jQuery(this).attr('data-up-down'));
      //alert('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
      // console.log('线路ID->' + $(this).data('line-id') + ', 站点ID->' + $(this).data('site-id') + ', 站点名称->' + $(this).data('site-name') + ', 上下学->' + $(this).data('up-down'));
	  //onevmouse_click("YZB12345678912346");
	    },
    'car_callback': function() {
	  //alert(jQuery(this).attr('data-vin'));
      //alert('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
      // console.log('vin->' + $(this).data('vin') + ', vehicle_ln->' + $(this).data('vehicle_ln'));
      onevmouse_click(jQuery(this).attr('data-vin'));
    }
  });
});
function show_rm(data) {
	  $('#rm_route').mk_routemonitor('reload', data);
}
/* 数据样列
function show_rm() {
  $('#rm_route').mk_routemonitor('reload', {
    line_id: 'line111',
    up_data: [
      {
        'id': 'site1',
        'name': '浙江物流',
        'status': 0,
        'up': 10,
        'down': -1
      },
      {
        'id': 'site2',
        'name': '南小李庄',
        'status': 1,
        'up': 10,
        'down': 0
      },
      {
        'id': 'site3',
        'name': '南小李庄',
        'status': 2,
        'up': 10,
        'down': 5
      },
      {
        'id': 'site4',
        'name': '十八里河镇',
        'status': 3,
        'up': -1,
        'down': 5
      }
    ],
    down_data: [
      {
        'id': 'site5',
        'name': '浙江物流',
        'status': 0,
        'up': 10
      },
      {
        'id': 'site6',
        'name': '南小李庄',
        'status': 0,
        'up': 10,
        'down': 5
      },
      {
        'id': 'site7',
        'name': '十八里河镇',
        'status': 0,
        'down': 5
      }
    ],
    up_cars: [
      {
        'vin': 'vin1',
        'site_id': 'site2',
        'vehicle_ln': '豫A222222',
        'inout_flag': 50
      },
      {
        'vin': 'vin2',
        'site_id': 'site3',
        'vehicle_ln': '豫A333333',
        'inout_flag': 30
      },
      {
        'vin': 'vin3',
        'site_id': 'site1',
        'vehicle_ln': '豫A444444',
        'inout_flag': 0
      },
      {
        'vin': 'vin4',
        'site_id': 'site1',
        'vehicle_ln': '豫A555555',
        'inout_flag': 0
      },
      {
        'vin': 'vin5',
        'site_id': 'site3',
        'vehicle_ln': '豫A666666',
        'inout_flag': 30
      }
    ],
    down_cars: [
      {
        'vin': 'down_vin1',
        'site_id': 'site7',
        'vehicle_ln': '豫A222222',
        'inout_flag': 0
      },
      {
        'vin': 'down_vin2',
        'site_id': 'site5',
        'vehicle_ln': '豫A333333',
        'inout_flag': 30
      },
      {
        'vin': 'down_vin3',
        'site_id': 'site6',
        'vehicle_ln': '豫A444444',
        'inout_flag': 40
      }
    ]
  });
}
*/
function clear_rm() {
  $('#rm_route').mk_routemonitor('clear');
}

/* 实体对象定义*/
function   mk_routemonitor(routeid,up_site_arr,down_site_arr,up_car_arr,down_car_arr) 
{ 
	this.line_id = routeid; 
	this.up_data = up_site_arr;
	this.down_data = down_site_arr;
	this.up_cars = up_car_arr;
	this.down_cars = down_car_arr;
}

function   mk_routemonitor_site(site_id,site_name,site_status,up_num,down_num) 
{ 
	this.id = site_id; 
	this.name = site_name;
	this.status = site_status;
	this.up = up_num;
	this.down = down_num;
}

function   mk_routemonitor_car(vin,ln,site_id,inout_flag) 
{ 
	this.vin = vin; 
	this.site_id = site_id;
	this.vehicle_ln = ln;
	this.inout_flag = inout_flag;
}
</script>