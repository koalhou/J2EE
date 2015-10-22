package com.neusoft.clw.safemanage.averagefuel.passengerstat.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class PassengerStatAction extends PaginationAction {
	
	
	private transient Service service;
    private String user_org_id;
	// 页面进入时，查询默认开始时间
	private String begintime;

	// 页面进入时，查询默认结束时间
	private String endtime;
	
	private InputStream excelStream;
	
	private String routeId;
	
	private String route_class;
	
	
	public String getEnterpreseId(String x){
		InputStream is = Constants.class.getResourceAsStream("/enterprise.properties");
	     Properties prop = new Properties();
	     try {
	         prop.load(is);
	     } catch (FileNotFoundException e) {
	       System.out.print("11111111");
	     } catch (IOException e) {
	     	System.out.print("11111111");
	     }

      if(x.equals("0")){
    	  return   (String)prop.get("yutong_enterprise_id");
      } 
      if(x.equals("1")){
    	  return   (String)prop.get("yichang_enterprise_id");
      }
      if(x.equals("2")){
    	  return   (String)prop.get("erchang_enterprise_id");
      }
	return "";
	}
	 
	

	public String gotoPage() {
		if (StringUtils.isEmpty(endtime)) {
			endtime = DateUtil.getCurrentDay();
		}
		if (StringUtils.isEmpty(begintime)) {
			begintime = DateUtil.getPreNDay(-6);// 这一月的第一天
		}
		user_org_id = getCurrentUser().getOrganizationID();
		//return "success";
		return "passengerStat";
		//return "passengerStatDetail";
	}
	public String gotoPageDetail() {
		if (StringUtils.isEmpty(endtime)) {
			endtime = DateUtil.getCurrentDay();
		}
		if (StringUtils.isEmpty(begintime)) {
			begintime = DateUtil.getPreNDay(-6);// 这一月的第一天
		}
		user_org_id = getCurrentUser().getOrganizationID();
		return "passengerStatDetail";
	}
	//导出Excel
	public String exportPassengerStatExcel() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Map m = new HashMap();
		//m.put("route_id", StringUtil.toStringList(routeId));
		m.put("enterprise_id",getEnterpreseId("0"));//导出宇通通勤车下的所有线路
		m.put("begin_time", begintime);
		m.put("end_time", endtime);
		m.put("route_class", route_class);
		putDataOnOutputStream(out, m);
		excelStream = new ByteArrayInputStream(out.toByteArray());
		return "excel";
	}
	//导出Excel，查询数据，将数据写入Excel
	public void putDataOnOutputStream(OutputStream os, Map param) {
		Label label;    
        WritableWorkbook workbook;
        try {
        	workbook = Workbook.createWorkbook(os);
        	WritableFont wf2 = new WritableFont(WritableFont.ARIAL,16,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
        	WritableCellFormat wcfTitle = new WritableCellFormat(wf2);
        	wcfTitle.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
        	wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直对齐

        	WritableSheet sheet0 = workbook.createSheet("汇总报表", 0);
        	sheet0.setRowView(0, 780, false);
        	sheet0.setRowView(1, 400, false);
        	sheet0.setRowView(2, 400, false);
        	sheet0.setColumnView(0, 12);
        	sheet0.setColumnView(1, 18);
        	sheet0.setColumnView(2, 9);
        	
        	label = new Label(0, 0, "客流统计("+begintime+"~"+endtime.substring(0, 10)+")", wcfTitle);
        	sheet0.addCell(label);
        	
        	sheet0.mergeCells(0, 1, 0, 2);
        	sheet0.mergeCells(1, 1, 1, 2);
        	sheet0.mergeCells(2, 1, 2, 2);

        	wf2   =   new   WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
        	WritableCellFormat wcfTitle1 = new WritableCellFormat(wf2);
        	wcfTitle1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
        	wcfTitle1.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直对齐
        	wcfTitle1.setBackground(Colour.GRAY_25);

        	label = new Label(0, 1, "组织", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(1, 1, "线路名称", wcfTitle1);
        	sheet0.addCell(label);
        	label = new Label(2, 1, "类别", wcfTitle1);
        	sheet0.addCell(label);
        	
        	List lSum = service.getObjects("PassengerStat.getPassengerStatExcelSum", param);
        	
        	String route_name_sum = "";
        	String short_name_sum = "";
        	int titleNumber_sum = 3;//日期列，从第3列开始
        	int rowNumber_sum = 3;//第几行数据，从3开始，前面是表头
        	int totalColumn_sum = 0;//总列数
        	int siteSum_sum = 0;//横向合计：同一线路，所有日期
        	boolean isAdd = true;//是否向合计List中添加元素
        	boolean isMerged = false;
        	List<Integer> dateSum_sum = new ArrayList<Integer>();//纵向合计：同一日期
        	for(int i = 0; i < lSum.size(); i++) {
        		Map passengerStat = (HashMap)lSum.get(i);
        		if(i == 0) {
        			String rq = (String) passengerStat.get("RQ");
        			route_name_sum = (String) passengerStat.get("ROUTE_NAME");
        			short_name_sum = (String) passengerStat.get("SHORT_NAME");
        			String route_class = (String) passengerStat.get("ROUTE_CLASS");
        			String person_count = (String) passengerStat.get("PERSON_COUNT");
        			sheet0.setColumnView(titleNumber_sum, 10);
        			label = new Label(titleNumber_sum, 1, rq, wcfTitle1);//表头：日期
        			sheet0.addCell(label);
        			label = new Label(titleNumber_sum, 2, getWeek(rq), wcfTitle1);//表头：星期
        			sheet0.addCell(label);
        			
        			label = new Label(0, rowNumber_sum, short_name_sum);//内容：组织
        			sheet0.addCell(label);
        			label = new Label(1, rowNumber_sum, route_name_sum);//内容：线路名称
        			sheet0.addCell(label);
        			label = new Label(2, rowNumber_sum, route_class);//内容：类别
        			sheet0.addCell(label);
        			label = new Label(titleNumber_sum, rowNumber_sum, person_count);//内容：人数
        			sheet0.addCell(label);
        			
        			totalColumn_sum = titleNumber_sum;
        			titleNumber_sum++;
        			siteSum_sum = siteSum_sum + Integer.parseInt(person_count);
        			dateSum_sum.add(Integer.parseInt(person_count));
        		} else if(i > 0) {
        			String rq = (String) passengerStat.get("RQ");
        			String route_name1 = (String) passengerStat.get("ROUTE_NAME");
        			String short_name = (String) passengerStat.get("SHORT_NAME");
        			String route_class = (String) passengerStat.get("ROUTE_CLASS");
        			String person_count = (String) passengerStat.get("PERSON_COUNT");
        			if(route_name1.equals(route_name_sum) && short_name.equals(short_name_sum)) {
        				sheet0.setColumnView(titleNumber_sum, 10);
            			label = new Label(titleNumber_sum, 1, rq, wcfTitle1);//表头：日期
            			sheet0.addCell(label);
            			label = new Label(titleNumber_sum, 2, getWeek(rq), wcfTitle1);//表头：星期
            			sheet0.addCell(label);
            			
            			label = new Label(titleNumber_sum, rowNumber_sum, person_count);//内容：人数
            			sheet0.addCell(label);
            			
            			totalColumn_sum = titleNumber_sum;
            			siteSum_sum = siteSum_sum + Integer.parseInt(person_count);
            			if(isAdd) {//第一次向List中添加
            				dateSum_sum.add(Integer.parseInt(person_count));
            			} else {//以后修改list
            				dateSum_sum.set(titleNumber_sum - 3, dateSum_sum.get(titleNumber_sum - 3) + Integer.parseInt(person_count));
            			}
            			titleNumber_sum++;
        			} else {
        				if(!isMerged) {
        					sheet0.mergeCells(titleNumber_sum, 1, titleNumber_sum, 2);
                			label = new Label(titleNumber_sum, 1, "合计", wcfTitle1);//表头：合计
                			sheet0.addCell(label);
                			isMerged = true;
        				}
        				label = new Label(titleNumber_sum, rowNumber_sum, String.valueOf(siteSum_sum));//横向合计
            			sheet0.addCell(label);
        				
            			siteSum_sum = 0;
            			dateSum_sum.set(0, Integer.parseInt(person_count) + dateSum_sum.get(0));
        				rowNumber_sum++;
        				titleNumber_sum = 3;
        				isAdd = false;//修改为false，以后修改合计List
        				
            			label = new Label(0, rowNumber_sum, short_name);//内容：组织
            			sheet0.addCell(label);
            			label = new Label(1, rowNumber_sum, route_name1);//内容：线路名称
            			sheet0.addCell(label);
            			label = new Label(2, rowNumber_sum, route_class);//内容：类别
            			sheet0.addCell(label);
            			label = new Label(titleNumber_sum, rowNumber_sum, person_count);//内容：人数
            			sheet0.addCell(label);
            			titleNumber_sum++;
            			route_name_sum = route_name1;
            			short_name_sum = short_name;
        			}
        			
        		}
        	}
        	
        	label = new Label(titleNumber_sum, rowNumber_sum, String.valueOf(siteSum_sum));//表头：日期
			sheet0.addCell(label);
        	sheet0.mergeCells(0, 0, totalColumn_sum + 1, 0);//表头合并单元格
        	
        	label = new Label(2, rowNumber_sum + 1, "合计");//表头：日期
			sheet0.addCell(label);
			
			int totalSum = 0;//总合计
        	for(int i = 0; i < dateSum_sum.size(); i++) {
        		totalSum += dateSum_sum.get(i);
        		label = new Label(i + 3, rowNumber_sum + 1, String.valueOf(dateSum_sum.get(i)));//表头：日期
    			sheet0.addCell(label);
        	}
        	
        	label = new Label(dateSum_sum.size() + 3, rowNumber_sum + 1, String.valueOf(totalSum));//表头：日期
			sheet0.addCell(label);
        	
        	/**
        	 * 明细报表
        	 */
        	WritableSheet sheet = workbook.createSheet("明细报表", 1);

        	sheet.setRowView(0, 780, false);
        	sheet.setRowView(1, 400, false);
        	sheet.setRowView(2, 400, false);
        	sheet.setColumnView(0, 12);
        	sheet.setColumnView(1, 18);
        	sheet.setColumnView(2, 9);
        	sheet.setColumnView(3, 18);
        	
        	label = new Label(0, 0, "客流统计("+begintime+"~"+endtime.substring(0, 10)+")", wcfTitle);
        	sheet.addCell(label);
        	
        	sheet.mergeCells(0, 1, 0, 2);
        	sheet.mergeCells(1, 1, 1, 2);
        	sheet.mergeCells(2, 1, 2, 2);
        	sheet.mergeCells(3, 1, 3, 2);

        	wf2   =   new   WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
        	wcfTitle = new WritableCellFormat(wf2);
        	wcfTitle.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
        	wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直对齐
        	wcfTitle.setBackground(Colour.GRAY_25);

        	label = new Label(0, 1, "组织", wcfTitle);
        	sheet.addCell(label);
        	label = new Label(1, 1, "线路名称", wcfTitle);
        	sheet.addCell(label);
        	label = new Label(2, 1, "类别", wcfTitle);
        	sheet.addCell(label);
        	label = new Label(3, 1, "站点", wcfTitle);
        	sheet.addCell(label);
        	
        	List l = service.getObjects("PassengerStat.getPassengerStatExcel", param);
        	String site_name = "";
        	String site_id = "";
        	String route_name = "";
        	String route_id = "";
        	int titleNumber = 4;//日期列，从第4列开始
        	int rowNumber = 3;//第几行数据，从3开始，前面是表头
        	int totalColumn = 0;//总列数
        	int siteSum = 0;//横向合计：同一站点，所有日期
        	List<Integer> dateSum = new ArrayList<Integer>();//纵向合计：同一日期
        	isAdd = true;//是否向合计List中添加元素
        	isMerged = false;
        	for(int i = 0; i < l.size(); i++) {
        		Map passengerStat = (HashMap)l.get(i);
        		if(i == 0) {
        			String rq = (String) passengerStat.get("RQ");
        			route_name = (String) passengerStat.get("ROUTE_NAME");
        			site_name = (String) passengerStat.get("SITE_NAME");
        			site_id = (String) passengerStat.get("SITE_ID");
        			route_id = (String) passengerStat.get("ROUTE_ID");
        			String short_name1 = (String) passengerStat.get("SHORT_NAME");
        			String route_class = (String) passengerStat.get("ROUTE_CLASS");
        			String person_count = (String) passengerStat.get("PERSON_COUNT");
        			sheet.setColumnView(titleNumber, 10);
        			label = new Label(titleNumber, 1, rq, wcfTitle);//表头：日期
        			sheet.addCell(label);
        			label = new Label(titleNumber, 2, getWeek(rq), wcfTitle);//表头：星期
        			sheet.addCell(label);
        			
        			label = new Label(0, rowNumber, short_name1);//内容：组织
        			sheet.addCell(label);
        			label = new Label(1, rowNumber, route_name);//内容：线路名称
        			sheet.addCell(label);
        			label = new Label(2, rowNumber, route_class);//内容：类别
        			sheet.addCell(label);
        			label = new Label(3, rowNumber, site_name);//内容：站点
        			sheet.addCell(label);
        			label = new Label(titleNumber, rowNumber, person_count);//内容：人数
        			sheet.addCell(label);
        			
        			totalColumn = titleNumber;
        			titleNumber++;
        			siteSum = siteSum + Integer.parseInt(person_count);
        			dateSum.add(Integer.parseInt(person_count));
        		} else if(i > 0) {
        			String rq = (String) passengerStat.get("RQ");
        			String route_name1 = (String) passengerStat.get("ROUTE_NAME");
        			String route_id1 = (String) passengerStat.get("ROUTE_ID");
        			String site_name1 = (String) passengerStat.get("SITE_NAME");
        			String site_id1 = (String) passengerStat.get("SITE_ID");
        			String short_name1 = (String) passengerStat.get("SHORT_NAME");
        			String route_class = (String) passengerStat.get("ROUTE_CLASS");
        			String person_count = (String) passengerStat.get("PERSON_COUNT");
        			if(site_id1.equals(site_id) && route_id1.equals(route_id)) {
        				sheet.setColumnView(titleNumber, 10);
            			label = new Label(titleNumber, 1, rq, wcfTitle);//表头：日期
            			sheet.addCell(label);
            			label = new Label(titleNumber, 2, getWeek(rq), wcfTitle);//表头：星期
            			sheet.addCell(label);
            			
            			label = new Label(titleNumber, rowNumber, person_count);//内容：人数
            			sheet.addCell(label);
            			
            			totalColumn = titleNumber;
            			siteSum = siteSum + Integer.parseInt(person_count);
            			if(isAdd) {//第一次向List中添加
            				dateSum.add(Integer.parseInt(person_count));
            			} else {//以后修改list
            				dateSum.set(titleNumber - 4, dateSum.get(titleNumber - 4) + Integer.parseInt(person_count));
            			}
            			titleNumber++;
        			} else {
        				if(!isMerged) {
        					sheet.mergeCells(titleNumber, 1, titleNumber, 2);
            				label = new Label(titleNumber, 1, "合计", wcfTitle);//表头：合计
                			sheet.addCell(label);
                			isMerged = true;
        				}
            			label = new Label(titleNumber, rowNumber, String.valueOf(siteSum));//横向合计
            			sheet.addCell(label);
        				
            			siteSum = 0;
            			dateSum.set(0, Integer.parseInt(person_count) + dateSum.get(0));
        				rowNumber++;
        				titleNumber = 4;
        				isAdd = false;//修改为false，以后修改合计List
        				
        				if(route_name1.equals(route_name)) {//线路名称相同，只显示第一条
                			label = new Label(0, rowNumber, "");//内容：组织
                			sheet.addCell(label);
                			label = new Label(1, rowNumber, "");//内容：线路名称
                			sheet.addCell(label);
        				} else {
                			label = new Label(0, rowNumber, short_name1);//内容：组织
                			sheet.addCell(label);
                			label = new Label(1, rowNumber, route_name1);//内容：线路名称
                			sheet.addCell(label);
                			
                			route_name = route_name1;
        				}
            			label = new Label(2, rowNumber, route_class);//内容：类别
            			sheet.addCell(label);
            			label = new Label(3, rowNumber, site_name1);//内容：站点
            			sheet.addCell(label);
            			label = new Label(titleNumber, rowNumber, person_count);//内容：人数
            			sheet.addCell(label);
            			titleNumber++;
            			site_name = site_name1;
            			site_id = site_id1;
            			route_id = route_id1;
        			}
        			
        		}
        	}
        	label = new Label(titleNumber, rowNumber, String.valueOf(siteSum));//表头：日期
			sheet.addCell(label);
        	sheet.mergeCells(0, 0, totalColumn + 1, 0);//表头合并单元格
        	
        	label = new Label(3, rowNumber + 1, "合计");//表头：日期
			sheet.addCell(label);
			
			totalSum = 0;//总合计
        	for(int i = 0; i < dateSum.size(); i++) {
        		totalSum += dateSum.get(i);
        		label = new Label(i + 4, rowNumber + 1, String.valueOf(dateSum.get(i)));//表头：日期
    			sheet.addCell(label);
        	}
        	
        	label = new Label(dateSum.size() + 4, rowNumber + 1, String.valueOf(totalSum));//表头：日期
			sheet.addCell(label);
        	
        	workbook.write();
        	workbook.close();
        }catch (Exception e) {
        	e.printStackTrace();
        }
	}
	public static void main(String[] args) {
		
		
		InputStream is = Constants.class.getResourceAsStream("/enterprise.properties");
	     Properties prop = new Properties();
	     try {
	         prop.load(is);
	     } catch (FileNotFoundException e) {
	       System.out.print("11111111");
	     } catch (IOException e) {
	     	System.out.print("11111111");
	     }
		       
		        System.out.println( (String)prop.get("yutong_enterprise_id"));

		        System.out.println((String)prop.get("yichang_enterprise_id"));
		
		String dongString=(String)prop.get("yutong_enterprise_id");
		System.out.println(dongString.replace("\"", ""));
		
		
		
//		List<Integer> l = new ArrayList<Integer>();
//		l.add(1);
//		l.add(2);
//		l.set(0, 5);
//		l.set(1, 10);
//		//l.set(2, 13);
//		System.out.println(l);
	}
	public static String getWeek(String date) throws ParseException {
		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"}; 
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(sdf.parse(date));
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		}
		return weeks[week_index];
	}
	
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
    public String getUser_org_id() {
        return user_org_id;
    }
    public void setUser_org_id(String user_org_id) {
        this.user_org_id = user_org_id;
    }
	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	public String getDownloadFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String downloadFileName = "客流统计" + (sf.format(new Date()).toString())+ ".xls";
		try {
			downloadFileName = java.net.URLEncoder.encode(downloadFileName, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getRoute_class() {
		return route_class;
	}
	public void setRoute_class(String route_class) {
		this.route_class = route_class;
	}
}
