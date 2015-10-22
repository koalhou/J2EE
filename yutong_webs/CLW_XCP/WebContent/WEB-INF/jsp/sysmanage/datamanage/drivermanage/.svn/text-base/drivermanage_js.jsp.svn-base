<%@page language="java" contentType="text/html;charset=utf-8"%>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/PasswordConfirmDWR.js'></script>
<script type="text/javascript">
	jQuery(function() {
		 var gala = jQuery('#gala');
		 gala.flexigrid({
		  url: '<s:url value="/driverGrid/driverList.shtml" />', 
		  dataType: 'json',    //json格式
		  height: 375,
		  width:0,
		  colModel : [ 
				{display: '序号', name : 'rowNumber', width : calcColumn(0.02,40,0), sortable : false, align: 'center'},
				{display: '<s:text name="driverinfo.driver_name" />', name : 'DRIVER_NAME', width : calcColumn(0.1,80,0), sortable : true, align: 'center', escape:true, process: reWriteLink},
		        {display: '<s:text name="driverinfo.driver_license" />',name : 'DRIVER_LICENSE', width : calcColumn(0.12,120,0), sortable : true, align: 'center', escape:true}, 
		        {display: '卡号', name : 'DRIVER_CARD_ID', width : calcColumn(0.12,120,0), sortable : true, align: 'center'},
		        {display: '<s:text name="sex" />', name : 'DRIVER_SEX', width : calcColumn(0.05,50,0), sortable : true, align: 'center', process: reWriteSex},
		        {display: '<s:text name="birthday" />', name : 'DRIVER_BIRTH', width : calcColumn(0.08,80,0), sortable : true, align: 'center'},
		        {display: '地址', name : 'DRIVER_ADDRESS', width : calcColumn(0.13,180,0), sortable : true, align: 'center', escape:true},
		        {display: '电话', name : 'DRIVER_TEL', width : calcColumn(0.13,100,0), sortable : true, align: 'center', escape:true},
		        {display: '', name : '', width : calcColumn(0.1,80,0), sortable : false, align: 'center', process: reWriteGatherLink}
		        ],
		       sortname: 'driver_id',
		       sortorder: 'asc',  //升序OR降序
		       sortable: true,   //是否支持排序
		       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
		       usepager :true,  //是否分页
		       resizable: false,  //是否可以设置表格大小
		       useRp: true,    // 是否可以动态设置每页显示的结果数
		       rp: 20,  //每页显示记录数
		       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
		       showTableToggleBtn: true   // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
		     });

		jQuery(window).resize( function() {
			testWidthHeight();
		});
		jQuery(window).load( function() {
			testWidthHeight();
		});
	});

	// 更新卡号	
	function updateCardNoById(dataId, carNo) {
		DWREngine.setAsync(false);
		var ret = false;
		PasswordConfirmDWR.updateCardNoByInputType(dataId, carNo, "driver", callBackFun);

		function callBackFun(data) {
			ret = data;
		}

		DWREngine.setAsync(true);
		
		if ("exists" == ret) {
			alert("驾驶员卡号已存在！");
		} else if ("success" == ret) {
			// 更新成功
			document.getElementById(dataId).innerHTML = carNo;
		} else if ("error" == ret) {
			alert("数据更新失败，请重新采集！");
		}
	}

	// 获取卡信息
	function getCardId(dataId) {
		try {
			var obj = document.getElementById('GenerateCardId');
			var connValue = obj.OpenComm();
			if (0 == connValue) {
				var cardId = obj.QueryCard();
				if (0 == cardId) {
					alert("未找到卡");
				} else {
					updateCardNoById(dataId, cardId);
				}
				obj.CloseComm();
			} else {
				alert("设备未连接上");
			}
		} catch (e) {
			alert("未知异常");
		}
	}

	// 采集链接
	function reWriteGatherLink(tdDiv, pid) {
		return '<a href="javascript:getCardId(' + pid + ');">'
				+ '采集' + '</a>';
	}

	//转换链接
	function reWriteLink(tdDiv, pid) {
		return '<a href="driver_edit_form.shtml?driverInfo.driver_id='
				+ pid + '">' + tdDiv + '</a>';
	}
	//判断性别
	function reWriteSex(tdDiv) {
		if (tdDiv == '0') {
			return '<s:text name="male" />';
		} else {
			return '<s:text name="female" />';
		}
	}
	//查询
	function searchDriver() {
		document.getElementById('Below_new').style.display = 'none';
		jQuery('#gala').flexOptions( {
			newp : 1,
			params : [ {
				name : 'driverInfo.driver_name',
				value : formatSpecialChar(jQuery('#driver_name').val())
			}, {
				name : 'driverInfo.driver_license',
				value : formatSpecialChar(jQuery('#driver_license').val())
			}, {
				name : 'driverInfo.driver_card',
				value : formatSpecialChar(jQuery('#driver_card').val())
			}, {
				name : 'driverInfo.organization_id',
				value : jQuery('#organizationID').val()
			} ]
		});
		jQuery('#gala').flexReload();
	}

	/** 企业组织选择 **/
	function openorganizidtree() {
		art.dialog.open(
				"<s:url value='/usm/usermanagetreeAction.shtml' />?rad="
						+ Math.random(),
				//userupdatetreeAction.shtml?userID=" + userid,
				{
					width : 260,
					height : 300,
					id : 'treeOID',
					title : ' ',
					skin : 'aero',
					limit : true,
					lock : true,
					drag : true,
					fixed : false,

					//follow: document.getElementById('organizationName'),
					yesFn : function(iframeWin, topWin) {
						//对话框iframeWin中对象
					//alert(iframeWin.test);
					var orgNameValue = iframeWin.document
							.getElementById('organizationName').value;
					var orgIDValue = iframeWin.document
							.getElementById('organizationID').value;
					//当前页面中对象
					var topOrgName = window.document
							.getElementById('organizationName');
					var topOrgID = window.document
							.getElementById('organizationID');
					//赋值
					if (topOrgName)
						topOrgName.value = orgNameValue;
					if (topOrgID)
						topOrgID.value = orgIDValue;
				}
				});
	}

	function exportDriver() {
		if (confirm("确定要导出驾驶员信息么？")) {
			document.getElementById('drivermanage_form').action = "<s:url value='/driver/exportDriver.shtml' />";
			document.getElementById('drivermanage_form').submit();
		}
	}

	//获取页面高度
	function get_page_height() {
		var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
		  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
	}
	//获取页面宽度
	function get_page_width() {
		var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
		  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
	}

	//计算控件宽高
	function testWidthHeight() {

		var h = get_page_height();
		var w = get_page_width();
		jQuery(".flexigrid").width(w - 23);
		//jQuery("#reportTab").height(h-100);
		jQuery(".bDiv").height(h - 330);

		jQuery(window).mk_autoresize({
		       height_include: '#content',
		       height_exclude: ['#header', '#footer'],
		       height_offset: 0,
		       width_include: ['#header', '#content', '#footer'],
		       width_offset: 0
		    });

		h = get_page_height();
		w = get_page_width();
		jQuery(".flexigrid").width(w - 23);
		//jQuery("#reportTab").height(h-100);
		jQuery(".bDiv").height(h - 330);
	}
</script>