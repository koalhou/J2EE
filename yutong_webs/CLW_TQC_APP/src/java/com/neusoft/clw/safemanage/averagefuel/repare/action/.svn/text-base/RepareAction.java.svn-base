package com.neusoft.clw.safemanage.averagefuel.repare.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.yunxing.routechart.chart.domain.RouteChart;
import com.opensymphony.xwork2.ActionContext;

public class RepareAction extends PaginationAction {
	private transient Service service;
	// 当前日期
	private String currentDay;
	private List<HashMap<String,String>> repareList;
	private Map map = new HashMap();
	private String action;
	private Map result = new HashMap();
	private InputStream excelStream;
	private String message;
	
	/**
	 * 获得当前操作用户
	 * 
	 * @return
	 */
	private UserInfo getCurrentUser() {
		return (UserInfo) ActionContext.getContext().getSession()
				.get(Constants.USER_SESSION_KEY);
	}
	//String user_org_id = getCurrentUser().getOrganizationID();

	public String gotoPage() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String msg=request.getParameter("msg");
		if(msg!=null){
			if("updsucc".equals(msg)){
				this.message="修改成功!";
			}
			if("upderror".equals(msg)){
				this.message="修改失败!";
			}
			if("addsucc".equals(msg)){
				this.message="维保添加成功!";
			}
			if("adderror".equals(msg)){
				this.message="维保添加失败!";
			}
		}
		
		if (StringUtils.isEmpty(currentDay)) {
			currentDay = DateUtil.getCurrentDay();
		}
		return SUCCESS;
	}
	
	public String gotoAddRepare() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		action = request.getParameter("action");
		return SUCCESS;
	}
	
	public String gotoUpdateRepare() {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
			action = request.getParameter("action");
			String repare_id = request.getParameter("repare_id");
			Map pMap = new HashMap();
			pMap.put("repare_id", repare_id);
			map = (HashMap)service.getObject("Repare.getRepareById", pMap);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	
	
	
	public String getRepare() {
		
		UserInfo user = getCurrentUser();
		
		Map<String,String> map = new HashMap<String,String>();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		map.put("begintime", request.getParameter("begintime"));
		map.put("endtime", request.getParameter("endtime"));
		map.put("fix_type", request.getParameter("fix_type"));
		map.put("vehicle_vin", request.getParameter("vehicle_vin"));
		map.put("user_org_id", user.getOrganizationID());
		
		
		String rpNum = request.getParameter("rp");
		String pageIndex = request.getParameter("page");
		
		int totalCount = 0;
		try {
			totalCount = service.getCount("Repare.getRepareCount", map);
			
			if(totalCount>0)
				repareList = service.getObjectsByPage("Repare.getRepare",map, (Integer.parseInt(pageIndex) - 1)* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			else
				repareList = new ArrayList<HashMap<String,String>>();
			
			this.map = getPagination(repareList, totalCount, pageIndex, rpNum);
			
		} catch (BusinessException e) {
			log.info("getRepare error end");
			return ERROR;
		}

		return SUCCESS;
	}

	public Map getPagination(List<HashMap<String,String>> repareList, int totalCountDay, String pageIndex, String rpNum) {
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Map<String,Object> mapData = new LinkedHashMap<String,Object>();
		for (int i = 0; i < repareList.size(); i++) {
			Map<String,String> s = (HashMap<String,String>) repareList.get(i);

			Map<String,Object> cellMap = new LinkedHashMap<String,Object>();

			cellMap.put("id", s.get("REPARE_ID"));
			cellMap.put("cell", new Object[] {
					s.get("UPDATE_TIME"),
					s.get("VEHICLE_CODE"),
					s.get("VEHICLE_LN"),
					s.get("FAULT_DESC"),
					s.get("FAULT_TYPE"), //维保项目
					s.get("FIX_TYPE"),
					s.get("FIX_FLAG"),
					s.get("OWNER"),
					s.get("COST_TIME"),
					s.get("COST_COMPONENT"),
					s.get("REPARE_ID"),
					s.get("REPARE_ID")
		  });

			mapList.add(cellMap);

		}

		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String addRepare() {
		try {
			Map<String,String> map = new HashMap<String,String>();
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
			String vehicle_vin = request.getParameter("vehicle_vin");
			String fault_type = request.getParameter("fault_type");
			String fault_type1 = request.getParameter("fault_type1");
			String fault_desc = request.getParameter("fault_desc");
			String fix_type = "";
			String fix_flag = request.getParameter("fix_flag");
			String cost_time = request.getParameter("cost_time");
			String cost_component = request.getParameter("cost_component");
			String cost_time1 = request.getParameter("cost_time1");
			String cost_component1 = request.getParameter("cost_component1");
			String owner = request.getParameter("owner");
			String update_by = getCurrentUser().getUserID();
			String update_time = request.getParameter("update_time");
			//格式化数据字
			double icost_time=0;
			double icost_component=0;
			double icost_time1=0;
			double icost_component1=0;
			if(StringUtils.isNotEmpty(cost_time)){
				icost_time=Double.parseDouble(cost_time);
			}
			if(StringUtils.isNotEmpty(cost_component)){
				icost_component=Double.parseDouble(cost_component);
			}
			if(StringUtils.isNotEmpty(cost_time1)){
				icost_time1=Double.parseDouble(cost_time1);
			}
			if(StringUtils.isNotEmpty(cost_component1)){
				icost_component1=Double.parseDouble(cost_component1);
			}
			cost_time=formatDigit(icost_time);
			cost_component=formatDigit(icost_component);
			cost_time1=formatDigit(icost_time1);
			cost_component1=formatDigit(icost_component1);
			
			cost_time=String.valueOf(icost_time);
			cost_time=String.valueOf(icost_time);
			map.put("vehicle_vin", vehicle_vin);
			map.put("update_by", update_by);
			map.put("update_time", update_time);
	
			if(!StringUtils.isEmpty(fault_type)) {
				fix_type = "0";//维修
				map.put("fix_type", fix_type);
				map.put("cost_time", cost_time);
				map.put("cost_component", cost_component);
				map.put("fault_type", fault_type);
				map.put("fault_desc", fault_desc);
				map.put("fix_flag", fix_flag);
				map.put("owner", owner);
				service.insert("Repare.addRepare", map);
			}
			if(!StringUtils.isEmpty(fault_type1)) {
				fix_type = "1";//保养
				map.put("fix_type", fix_type);
				map.put("cost_time", cost_time1);
				map.put("cost_component", cost_component1);						
				map.put("fault_type", fault_type1);
				map.put("fault_desc", "--");
				map.put("fix_flag", "2");
				map.put("owner", "--");
				service.insert("Repare.addRepare", map);
			}
			this.result.put("returns", "success");
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 格式化数字，把“.0”去掉
	 * @return
	 */
	private String formatDigit(double digit){
		String sd=String.valueOf(digit);
		int idx=sd.indexOf(".");
		String substr=sd.substring(idx+1);
		if("0".equals(substr)){
			return sd.substring(0, idx);
		}
		return sd;
	}
	public String updateRepare() {
		try {
			Map<String,String> map = new HashMap<String,String>();
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
			String vehicle_vin = request.getParameter("vehicle_vin");
			String fault_type = request.getParameter("fault_type");
			String fault_desc = request.getParameter("fault_desc");
			String fix_flag = request.getParameter("fix_flag");
			String cost_time = request.getParameter("cost_time");
			String cost_component = request.getParameter("cost_component");
			String owner = request.getParameter("owner");
			String update_by = getCurrentUser().getUserID();
			String update_time = request.getParameter("update_time");
			String repare_id = request.getParameter("repare_id");
			String valid_flag = request.getParameter("valid_flag");
			//格式化数据字
			double icost_time=0;
			double icost_component=0;
			if(StringUtils.isNotEmpty(cost_time)){
				icost_time=Double.parseDouble(cost_time);
			}
			if(StringUtils.isNotEmpty(cost_component)){
				icost_component=Double.parseDouble(cost_component);
			}
			cost_time=formatDigit(icost_time);
			cost_component=formatDigit(icost_component);
			
			map.put("vehicle_vin", vehicle_vin);
			map.put("update_by", update_by);
			map.put("update_time", update_time);
			map.put("cost_time", cost_time);
			map.put("cost_component", cost_component);
			map.put("fault_type", fault_type);
			map.put("fault_desc", fault_desc);
			map.put("fix_flag", fix_flag);
			map.put("owner", owner);
			map.put("repare_id", repare_id);
			map.put("valid_flag", valid_flag);
			service.update("Repare.updateRepareById", map);

			this.result.put("returns", "success");
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//导出Excel
	public String exportRepareExcel() {
		UserInfo user = getCurrentUser();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Map<String,String> map = new HashMap<String,String>();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		map.put("begintime", request.getParameter("begintime"));
		map.put("endtime", request.getParameter("endtime"));
		map.put("fix_type", request.getParameter("fix_type"));
		map.put("vehicle_vin", request.getParameter("vehicle_vin"));
		map.put("user_org_id", user.getOrganizationID());
		putDataOnOutputStream(out, map);
		excelStream = new ByteArrayInputStream(out.toByteArray());
		return "excel";
	}
	
	//导出Excel，查询数据，将数据写入Excel
	public void putDataOnOutputStream(OutputStream os, Map param) {
		Label label;    
        WritableWorkbook workbook;
        String begintime = (String)param.get("begintime");
        String endtime = (String)param.get("endtime");
        try {
        	workbook = Workbook.createWorkbook(os);
        	WritableFont wf2 = new WritableFont(WritableFont.ARIAL,16,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
        	WritableCellFormat wcfTitle = new WritableCellFormat(wf2);
        	wcfTitle.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
        	wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直对齐
        	
        	WritableSheet sheet0 = workbook.createSheet("维保记录", 0);
        	
        	sheet0.mergeCells(0, 1, 0, 2);
        	sheet0.mergeCells(1, 1, 1, 2);
        	sheet0.mergeCells(2, 1, 2, 2);
        	sheet0.mergeCells(3, 1, 2, 2);
        	sheet0.mergeCells(4, 1, 2, 2);
        	sheet0.mergeCells(5, 1, 2, 2);
        	sheet0.mergeCells(6, 1, 2, 2);
        	sheet0.mergeCells(7, 1, 2, 2);
        	
        	sheet0.mergeCells(8, 1, 10, 1);
        	
        	label = new Label(0, 0, "维保记录("+begintime+"~"+endtime.substring(0, 10)+")", wcfTitle);
        	sheet0.addCell(label);
        	
        	wf2   =   new   WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
        	WritableCellFormat wcfTitle1 = new WritableCellFormat(wf2);
        	wcfTitle1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
        	wcfTitle1.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直对齐
        	wcfTitle1.setBackground(Colour.GRAY_25);
        	
        	label = new Label(0, 1, "维保日期", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(1, 1, "班车号", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(2, 1, "车牌号", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(3, 1, "故障描述", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(4, 1, "维保项目", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(5, 1, "类别", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(6, 1, "是否正常维修", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(7, 1, "责任人", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(8, 1, "维保费用", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(8, 2, "工时", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(9, 2, "配件", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(10, 2, "合计", wcfTitle1);
        	sheet0.addCell(label);
        	
        	List lSum = service.getObjects("Repare.getRepareExcel", param);
        	float totalSum = 0;
        	float timeSum = 0;
        	float componentSum = 0;
        	if(lSum != null && lSum.size() > 0) {
        		for(int i = 0; i < lSum.size(); i++) {
        			Map mRepare = (HashMap)lSum.get(i);
        			String update_time = (String)mRepare.get("UPDATE_TIME");
        			String vehicle_code = (String)mRepare.get("VEHICLE_CODE");
        			String vehicle_ln = (String)mRepare.get("VEHICLE_LN");
        			String fault_desc = (String)mRepare.get("FAULT_DESC");
        			String fault_type = (String)mRepare.get("FAULT_TYPE");
        			String fix_type = (String)mRepare.get("FIX_TYPE");
        			String fix_flag = (String)mRepare.get("FIX_FLAG");
        			String owner = (String)mRepare.get("OWNER");
        			String cost_time = (String)mRepare.get("COST_TIME");
        			if(cost_time == null || "".equals(cost_time)) {
        				cost_time = "0";
        			}
        			String cost_component = (String)mRepare.get("COST_COMPONENT");
        			if(cost_time == null || "".equals(cost_time)) {
        				cost_component = "0";
        			}
        			
        			label = new Label(0, i + 3, update_time);
                	sheet0.addCell(label);
                	label = new Label(1, i + 3, vehicle_code);
                	sheet0.addCell(label);
                	label = new Label(2, i + 3, vehicle_ln);
                	sheet0.addCell(label);
                	label = new Label(3, i + 3, fault_desc);
                	sheet0.addCell(label);
                	label = new Label(4, i + 3, fault_type);
                	sheet0.addCell(label);
                	label = new Label(5, i + 3, fix_type);
                	sheet0.addCell(label);
                	label = new Label(6, i + 3, fix_flag);
                	sheet0.addCell(label);
                	label = new Label(7, i + 3, owner);
                	sheet0.addCell(label);
                	label = new Label(8, i + 3, cost_time);
                	sheet0.addCell(label);
                	label = new Label(9, i + 3, cost_component);
                	sheet0.addCell(label);
                	label = new Label(10, i + 3, String.valueOf((Float.valueOf(cost_time) + Float.valueOf(cost_component))));
                	sheet0.addCell(label);
                	
                	totalSum += Float.valueOf(cost_time) + Float.valueOf(cost_component);
                	timeSum += Float.valueOf(cost_time);
                	componentSum += Float.valueOf(cost_component);
        		}
        		label = new Label(0, lSum.size() + 3, "合计");
            	sheet0.addCell(label);
            	label = new Label(1, lSum.size() + 3, "/");
            	sheet0.addCell(label);
            	label = new Label(2, lSum.size() + 3, "/");
            	sheet0.addCell(label);
            	label = new Label(3, lSum.size() + 3, "/");
            	sheet0.addCell(label);
            	label = new Label(4, lSum.size() + 3, "/");
            	sheet0.addCell(label);
            	label = new Label(5, lSum.size() + 3, "/");
            	sheet0.addCell(label);
            	label = new Label(6, lSum.size() + 3, "/");
            	sheet0.addCell(label);
            	label = new Label(7, lSum.size() + 3, "/");
            	sheet0.addCell(label);
            	label = new Label(8, lSum.size() + 3, String.valueOf(timeSum));
            	sheet0.addCell(label);
            	label = new Label(9, lSum.size() + 3, String.valueOf(componentSum));
            	sheet0.addCell(label);
            	label = new Label(10, lSum.size() + 3, String.valueOf(totalSum));
            	sheet0.addCell(label);
        	}
        	
        	workbook.write();
        	workbook.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	

	

	public String getDownloadFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String downloadFileName = "维保记录" + (sf.format(new Date()).toString())+ ".xls";
		try {
			downloadFileName = java.net.URLEncoder.encode(downloadFileName, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}

	public List<HashMap<String, String>> getRepareList() {
		return repareList;
	}

	public void setRepareList(List<HashMap<String, String>> repareList) {
		this.repareList = repareList;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
