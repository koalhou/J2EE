package com.neusoft.clw.itsmanage.oilmanage.runstatistic.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.struts2.ServletActionContext;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.itsmanage.oilmanage.runstatistic.domain.PubDetail;
import com.neusoft.clw.itsmanage.oilmanage.runstatistic.domain.RepaireDetail;
import com.neusoft.clw.itsmanage.oilmanage.runstatistic.domain.RunStatisticEntity;
import com.neusoft.clw.itsmanage.oilmanage.runstatistic.domain.RunStatisticSearch;
import com.neusoft.clw.itsmanage.oilmanage.runstatistic.domain.StCheckEntity;
import com.neusoft.clw.itsmanage.oilmanage.runstatistic.domain.WorkDetail;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.yt.nidizhi.SearchresultDocument;
import com.neusoft.yt.nidizhi.SearchresultDocument.Searchresult;
import com.neusoft.yt.nidizhi.SearchresultDocument.Searchresult.List.Spatial;
import com.neusoft.yt.nidizhi.SearchresultDocument.Searchresult.List.Spatial.Roadlist.Road;
import com.opensymphony.xwork2.ActionContext;

public class RunStatisticAction extends PaginationAction{
	/** service共通类 */
    private transient Service service;
    
    private Map<String, Object> map = new HashMap<String, Object>();
    //运营统计实体类
    private RunStatisticSearch searchVO=new RunStatisticSearch();
    
    private RunStatisticEntity entity=new RunStatisticEntity();
    //通勤明细
    private WorkDetail work=new WorkDetail();
    //公差
    private PubDetail pub=new PubDetail();
	//维修
    private RepaireDetail repaire=new RepaireDetail();
    
    private Map<String, Object> subMap = new HashMap<String, Object>();
    
    //员工刷卡记录
    private StCheckEntity stcheck=new StCheckEntity();
    
    
	public StCheckEntity getStcheck() {
		return stcheck;
	}

	public void setStcheck(StCheckEntity stcheck) {
		this.stcheck = stcheck;
	}

	public RepaireDetail getRepaire() {
		return repaire;
	}

	public void setRepaire(RepaireDetail repaire) {
		this.repaire = repaire;
	}

	public PubDetail getPub() {
		return pub;
	}

	public void setPub(PubDetail pub) {
		this.pub = pub;
	}

	public WorkDetail getWork() {
		return work;
	}

	public void setWork(WorkDetail work) {
		this.work = work;
	}
	
	public Map<String, Object> getSubMap() {
		return subMap;
	}

	public void setSubMap(Map<String, Object> subMap) {
		this.subMap = subMap;
	}

	public RunStatisticSearch getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(RunStatisticSearch searchVO) {
		this.searchVO = searchVO;
	}

	public RunStatisticEntity getEntity() {
		return entity;
	}

	public void setEntity(RunStatisticEntity entity) {
		this.entity = entity;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	private String message = null;
    /**
     * 从菜单进入页面
     */
	public String readyPage(){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		String yesterday=DateUtil.formatDateToString(c.getTime(), "yyyy-MM-dd");
		this.searchVO.setBeginTime(yesterday);
		this.searchVO.setEndTime(yesterday);
		this.searchVO.setYesterday(yesterday);
		return SUCCESS;
	}
	/**
	 * 列表页面
	 */
	public String list(){
		UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
		//获得昨天的日期
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		String yesterday=DateUtil.formatDateToString(c.getTime(), "yyyy-MM-dd");
		HttpServletRequest request = getCurrentRequest();
		//本月查询：查询当月1号至昨天的数据
		if("month".equals(this.searchVO.getDateFlag())){
			this.searchVO.setBeginTime(DateUtil.getMonthFirstDay().substring(0, 10));
			this.searchVO.setEndTime(yesterday);
		}else if("year".equals(this.searchVO.getDateFlag())){
			this.searchVO.setBeginTime(DateUtil.getYear()+"-01-01");
			this.searchVO.setEndTime(yesterday);
		}
		
		searchVO.setUser_organization_id(currentUser.getOrganizationID());
		//分页
		String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        int totalCount = 0;
		try {
			totalCount=this.service.getCount("RunStatisticManage.getRunStatisticCount", searchVO);
			List<RunStatisticEntity> list=this.service.getObjectsByPage("RunStatisticManage.getRunStatisticList", searchVO, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			//添加列表为空提示
			if (list.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }else{
            	this.map = getPagination(list, totalCount, pageIndex, rpNum);// 转换map
            }
		} catch (BusinessException e) {
			this.log.error("运营统计列表查询出错", e);
		}
		return SUCCESS;
	}
	/**
	 * 导出列表
	 * 
	 */
	private List<RunStatisticEntity> getExportList(){
		UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
		String yesterday=DateUtil.getPreDay();
		if("month".equals(this.searchVO.getDateFlag())){
			this.searchVO.setBeginTime(DateUtil.getMonthFirstDay().substring(0, 10));
			this.searchVO.setEndTime(yesterday);
		}else if("year".equals(this.searchVO.getDateFlag())){
			this.searchVO.setBeginTime(DateUtil.getYear()+"-01-01");
			this.searchVO.setEndTime(yesterday);
		}
		searchVO.setUser_organization_id(currentUser.getOrganizationID());
		//查询列表
		List<RunStatisticEntity> list=new ArrayList<RunStatisticEntity>();
		try {
			list=this.service.getObjects("RunStatisticManage.getRunStatisticList", searchVO);
		} catch (BusinessException e) {
			this.log.error("导出运营统计列表查询出错", e);
		}
		return list;
	}
	/**
	 * 导出
	 */
	public String exportList(){
		OutputStream fout=null;
		HttpServletResponse response=this.getResponse("utf-8", "application/vnd.ms-excel");
		// 进行转码，使其支持中文文件名  
		try {
			List<RunStatisticEntity> list=getExportList();//查询数据列表
			
			String fileName="运营统计-"+DateUtil.getCurrentDayTime();
	   	    fileName = URLEncoder.encode(fileName, "UTF-8");  
	        response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
	        fout=response.getOutputStream();
	        
			WritableWorkbook wwb = Workbook.createWorkbook(fout);
			WritableSheet sheet =wwb.createSheet("运营统计", 0);
			
			sheet.setRowView(0, 400);
			//样式
			WritableCellFormat cellFormat=new WritableCellFormat();
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormat.setBackground(Colour.GRAY_25);
			//字体
			WritableFont wf=new WritableFont(WritableFont.TIMES,10, WritableFont.BOLD);
			cellFormat.setFont(wf);
			
			
			//大标题
			String colTitle="运营统计("+searchVO.getBeginTime()+"~"+searchVO.getEndTime()+")";
			Label label = new Label(0, 0, colTitle,cellFormat);
			sheet.addCell(label);
			sheet.mergeCells(0, 0, 16, 0);
			/*
			 * 列标题合并
			 */
			String[] colsTitle={"厂区","班车号","车牌号","驾驶员"};
			int len=colsTitle.length;
			for(int i=0;i<len;i++){
				sheet.addCell(new Label(i,1,colsTitle[i],cellFormat));
				sheet.mergeCells(i, 1, i, 2);
			}
			//合并三列
			int mlen=len;
			sheet.addCell(new Label(mlen,1,"行驶里程(km)",cellFormat));
			sheet.mergeCells(mlen, 1, mlen+2, 1);
			
			sheet.addCell(new Label(mlen,2,"载重里程",cellFormat));
			
			sheet.addCell(new Label(mlen+1,2,"空驶里程",cellFormat));
			
			sheet.addCell(new Label(mlen+2,2,"合计",cellFormat));
			//油耗
			mlen=mlen+3;
			sheet.addCell(new Label(mlen,1,"耗油量(L)",cellFormat));
			sheet.mergeCells(mlen, 1, mlen, 2);
			//百公里油耗
			mlen=mlen+1;
			sheet.addCell(new Label(mlen,1,"百公里油耗(L)",cellFormat));
			sheet.mergeCells(mlen, 1, mlen, 2);
			//服务人次
			mlen=mlen+1;
			sheet.addCell(new Label(mlen,1,"服务人次",cellFormat));
			sheet.mergeCells(mlen, 1, mlen, 2);
			//合并三列
			mlen=mlen+1;
			sheet.addCell(new Label(mlen,1,"出勤次数",cellFormat));
			sheet.mergeCells(mlen, 1, mlen+2, 1);
			
			sheet.addCell(new Label(mlen,2,"早班",cellFormat));
			
			sheet.addCell(new Label(mlen+1,2,"晚班",cellFormat));
			
			sheet.addCell(new Label(mlen+2,2,"合计",cellFormat));
			//合并两列
			mlen=mlen+3;
			sheet.addCell(new Label(mlen,1,"维修",cellFormat));
			sheet.mergeCells(mlen, 1, mlen+1, 1);
			
			sheet.addCell(new Label(mlen,2,"维修次数",cellFormat));
			sheet.addCell(new Label(mlen+1,2,"维修费用",cellFormat));
			//合并两列
			mlen=mlen+2;
			sheet.addCell(new Label(mlen,1,"公差",cellFormat));
			sheet.mergeCells(mlen, 1, mlen+1, 1);
			
			sheet.addCell(new Label(mlen,2,"公差次数",cellFormat));
			sheet.addCell(new Label(mlen+1,2,"公差里程(km)",cellFormat));
			//写数据,从第三行写
			//格式化数字
			//NumberFormat nf=new NumberFormat("#.##");
			//WritableCellFormat wcf=new WritableCellFormat(nf);
			DecimalFormat df = new DecimalFormat("0.0");
			
			/*
			 * 填充数据
			 */
			double load_mileage_sum=0;
			double empty_mileage_sum=0;
			double total_mileage_sum=0;
			double total_oil_sum=0;
			String oil_100_sum = "";
			int load_number_sum=0;
			int morning_cnt_sum=0;
			int night_cnt_sum=0;
			int total_cnt_sum=0;
			int repaire_cnt_sum=0;
			double cost_component_sum=0;
			int p_cnt_sum=0;
			double p_mileage_sum=0;
			
			int dataRows=list.size();
			for(int i=0;i<dataRows;i++){
				RunStatisticEntity run=list.get(i);
				sheet.addCell(new Label(0,i+3,run.getShort_name()));
				sheet.addCell(new Label(1,i+3,run.getVehicle_code()));
				sheet.addCell(new Label(2,i+3,run.getVehicle_ln()));
				sheet.addCell(new Label(3,i+3,run.getDriver_name()));
				//数字
				double load_mileage=Double.valueOf("/".equals(run.getLoad_mileage())?"0":run.getLoad_mileage());
				double empty_mileage=Double.valueOf("/".equals(run.getEmpty_mileage())?"0":run.getEmpty_mileage());
				double total_mileage=Double.valueOf("/".equals(run.getTotal_mileage())?"0":run.getTotal_mileage());//Double.valueOf(run.getTotal_mileage());
				double total_oil=Double.valueOf("/".equals(run.getTotal_oil())?"0":run.getTotal_oil());//Double.valueOf(run.getTotal_oil());
				double oil_100=Double.valueOf("/".equals(run.getOil_100())?"0":run.getOil_100());//Double.valueOf(run.getOil_100());
				int load_number=Integer.valueOf("/".equals(run.getLoad_number())?"0":run.getLoad_number());//Integer.valueOf(run.getLoad_number());
				int morning_cnt=Integer.valueOf("/".equals(run.getMorning_cnt())?"0":run.getMorning_cnt());//Integer.valueOf(run.getMorning_cnt());
				int night_cnt=Integer.valueOf("/".equals(run.getNight_cnt())?"0":run.getNight_cnt());//Integer.valueOf(run.getNight_cnt());
				int total_cnt=Integer.valueOf("/".equals(run.getTotal_cnt())?"0":run.getTotal_cnt());//Integer.valueOf(run.getTotal_cnt());
				int repaire_cnt=Integer.valueOf("/".equals(run.getRepare_cnt())?"0":run.getRepare_cnt());//Integer.valueOf(run.getRepare_cnt());
				double cost_component=Double.valueOf("/".equals(run.getCost_component())?"0":run.getCost_component());//Double.valueOf(run.getCost_component());
				int p_cnt=Integer.valueOf("/".equals(run.getP_cnt())?"0":run.getP_cnt());//Integer.valueOf(run.getP_cnt());
				double p_mileage=Double.valueOf("/".equals(run.getP_mileage())?"0":run.getP_mileage());//Double.valueOf(run.getP_mileage());
				
				sheet.addCell(new jxl.write.Label(4,i+3,run.getLoad_mileage()));
				sheet.addCell(new jxl.write.Label(5,i+3,run.getEmpty_mileage()));
				sheet.addCell(new jxl.write.Label(6,i+3,run.getTotal_mileage()));
				sheet.addCell(new jxl.write.Label(7,i+3,run.getTotal_oil()));
				sheet.addCell(new jxl.write.Label(8,i+3,run.getOil_100()));
				sheet.addCell(new jxl.write.Label(9,i+3,run.getLoad_number()));
				sheet.addCell(new jxl.write.Label(10,i+3,run.getMorning_cnt()));
				sheet.addCell(new jxl.write.Label(11,i+3,run.getNight_cnt()));
				sheet.addCell(new jxl.write.Label(12,i+3,run.getTotal_cnt()));
				sheet.addCell(new jxl.write.Label(13,i+3,run.getRepare_cnt()));
				sheet.addCell(new jxl.write.Label(14,i+3,run.getCost_component()));
				sheet.addCell(new jxl.write.Label(15,i+3,run.getP_cnt()));
				sheet.addCell(new jxl.write.Label(16,i+3,run.getP_mileage()));
				
				//合计计算
				load_mileage_sum += load_mileage;
				empty_mileage_sum += empty_mileage;
				total_mileage_sum += total_mileage;
				total_oil_sum += total_oil;
				

				//double oil_100_sum=0;
				load_number_sum += load_number;
				morning_cnt_sum += morning_cnt;
				night_cnt_sum += night_cnt;
				total_cnt_sum += total_cnt;
				repaire_cnt_sum += repaire_cnt;
				cost_component_sum += cost_component;
				p_cnt_sum += p_cnt;
				p_mileage_sum += p_mileage;
			}
			
			oil_100_sum = df.format(((total_mileage_sum == (double) 0) ? (double) 0.0 : total_oil_sum*100/total_mileage_sum ));
			//把合计添加到最后一行
			int lastRow=dataRows+3;
			sheet.addCell(new Label(0,lastRow,"合计"));
			sheet.addCell(new Label(1,lastRow,""));
			sheet.addCell(new Label(2,lastRow,""));
			sheet.addCell(new Label(3,lastRow,""));
			
			sheet.addCell(new jxl.write.Number(4,lastRow,load_mileage_sum));
			sheet.addCell(new jxl.write.Number(5,lastRow,empty_mileage_sum));
			sheet.addCell(new jxl.write.Number(6,lastRow,total_mileage_sum));
			sheet.addCell(new jxl.write.Number(7,lastRow,total_oil_sum));
			//sheet.addCell(new jxl.write.Number(6,lastRow,oil_100_sum));
			//sheet.addCell(new Label(8,lastRow,"/"));
			sheet.addCell(new Label(8,lastRow,oil_100_sum));
			sheet.addCell(new jxl.write.Number(9,lastRow,load_number_sum));
			sheet.addCell(new jxl.write.Number(10,lastRow,morning_cnt_sum));
			sheet.addCell(new jxl.write.Number(11,lastRow,night_cnt_sum));
			sheet.addCell(new jxl.write.Number(12,lastRow,total_cnt_sum));
			sheet.addCell(new jxl.write.Number(13,lastRow,repaire_cnt_sum));
			sheet.addCell(new jxl.write.Number(14,lastRow,cost_component_sum));
			sheet.addCell(new jxl.write.Number(15,lastRow,p_cnt_sum));
			sheet.addCell(new jxl.write.Number(16,lastRow,p_mileage_sum));
			
        	wwb.write();
        	wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 进入出勤明细页面
	 * @return
	 */
	public String workDetailPage(){
		HttpServletRequest request = getCurrentRequest();
		String dateFlag=request.getParameter("dateFlag");//判断是选中的本月，本年，自定义
		
		if("month".equals(dateFlag)){
			work.setStart_time(DateUtil.getMonthFirstDay().substring(0, 10));
			//work.setEnd_time(DateUtil.getMonthLastDay().substring(0, 10));
			work.setEnd_time(DateUtil.getPreDay());
			work.setPre_date(DateUtil.getPreDay());
		}else if("year".equals(dateFlag)){
			work.setStart_time(DateUtil.getYear()+"-01-01");
			//work.setEnd_time(DateUtil.getYear()+"-12-31");
			work.setEnd_time(DateUtil.getPreDay());
			work.setPre_date(DateUtil.getPreDay());
		}else{
			work.setStart_time(request.getParameter("start_time"));
			work.setEnd_time(request.getParameter("end_time"));
			//work.setEnd_time(DateUtil.getPreDay());
			work.setPre_date(DateUtil.getPreDay());
		}
		work.setVehicle_vin(request.getParameter("vin"));
		work.setRoute_class(request.getParameter("class"));
		return SUCCESS;
	}
	/**
	 * 出勤明细
	 * @return
	 */
	public String workDetailList(){
		HttpServletRequest request = getCurrentRequest();
		//分页
		String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        work.setSortName(sortName);
        work.setSortOrder(sortOrder);
        int totalCount = 0;
		try {
			totalCount=this.service.getCount("RunStatisticManage.getWorkDetailCount", work);
			List<WorkDetail> list=this.service.getObjectsByPage("RunStatisticManage.getWorkDetail", work, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			//添加列表为空提示
			if (list.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }else{
            	subMap=getWorkDetailPagination(list, totalCount, pageIndex, rpNum);
            }
			
		} catch (BusinessException e) {
			this.log.error("出勤明细查询出错", e);
		}
		return SUCCESS;
	}
	/**
	 * 进入维修明细页面
	 */
	public String repaireDetailPage(){
		HttpServletRequest request = getCurrentRequest();
		String dateFlag=request.getParameter("dateFlag");//判断是选中的本月，本年，自定义
		
		if("month".equals(dateFlag)){
			repaire.setStart_time(DateUtil.getMonthFirstDay().substring(0, 10));
			repaire.setEnd_time(DateUtil.getPreDay());
			repaire.setPre_date(DateUtil.getPreDay());
		}else if("year".equals(dateFlag)){
			repaire.setStart_time(DateUtil.getYear()+"-01-01");
			repaire.setEnd_time(DateUtil.getPreDay());
			repaire.setPre_date(DateUtil.getPreDay());
		}else{
			repaire.setStart_time(request.getParameter("start_time"));
			repaire.setEnd_time(request.getParameter("end_time"));
			repaire.setPre_date(DateUtil.getPreDay());
		}
		repaire.setVehicle_vin(request.getParameter("vin"));
		return SUCCESS;
	}
	/**
	 * 维修明细
	 */
	public String repaireDetailList(){
		HttpServletRequest request = getCurrentRequest();
		//分页
		String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        repaire.setSortName(sortName);
        repaire.setSortOrder(sortOrder);
        int totalCount = 0;
		try {
			totalCount=this.service.getCount("RunStatisticManage.getRepaireDetailCount", repaire);
			List<RepaireDetail> list=this.service.getObjectsByPage("RunStatisticManage.getRepaireDetail", repaire, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			//添加列表为空提示
			if (list.size() == 0) {
                addActionMessage(getText("nodata.list"));
                return SUCCESS;
            }
			//当前页是否包含合计
			boolean bool=false;
			//如果当前请求是最后一页，则添加合计选项
			int irp=Integer.parseInt(rpNum);
			int totalPage=(totalCount+irp+1)/irp;//总页数
			if(Integer.parseInt(pageIndex)==totalPage){//当前页数等于总页数
				bool=true;
				//添加合计
				List<RepaireDetail> toatalList=this.service.getObjects("RunStatisticManage.getRepaireDetail", repaire);
				double time_sum=0;
				double part_sum=0;
				double total=0;
				for(RepaireDetail pd:toatalList){
					time_sum += Double.parseDouble(pd.getWorkTimeFee());
					part_sum += Double.parseDouble(pd.getPartFee());
					total += Double.parseDouble(pd.getTotalFee());
				}
				RepaireDetail sum=new RepaireDetail();
				sum.setWorkTimeFee(String.valueOf(time_sum));
				sum.setPartFee(String.valueOf(part_sum));
				sum.setTotalFee(String.valueOf(total));
				list.add(sum);
			}
			//格式成map
			subMap=getRepaireDetailPagination(list, totalCount, pageIndex, rpNum,bool);// 转换map
		} catch (BusinessException e) {
			this.log.error("维修明细查询出错", e);
		}
		return SUCCESS;
	}
	/**
	 * 进入 公差明细 页面
	 */
	public String pubDetailPage(){
		HttpServletRequest request = getCurrentRequest();
		String dateFlag=request.getParameter("dateFlag");//判断是选中的本月，本年，自定义
		
		if("month".equals(dateFlag)){
			pub.setStart_time(DateUtil.getMonthFirstDay().substring(0, 10));
			pub.setEnd_time(DateUtil.getPreDay());
			pub.setPre_date(DateUtil.getPreDay());
		}else if("year".equals(dateFlag)){
			pub.setStart_time(DateUtil.getYear()+"-01-01");
			pub.setEnd_time(DateUtil.getPreDay());
			pub.setPre_date(DateUtil.getPreDay());
		}else{
			pub.setStart_time(request.getParameter("start_time"));
			pub.setEnd_time(request.getParameter("end_time"));
			pub.setPre_date(DateUtil.getPreDay());
		}
		pub.setVehicle_vin(request.getParameter("vin"));
		return SUCCESS;
	}
	/**
	 * 公差明细
	 */
	public String pubDetailList(){
		HttpServletRequest request = getCurrentRequest();
		//分页
		String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        pub.setSortName(sortName);
        pub.setSortOrder(sortOrder);
        int totalCount = 0;
		try {
			totalCount=this.service.getCount("RunStatisticManage.getPubDetailCount", pub);
			List<PubDetail> list=this.service.getObjectsByPage("RunStatisticManage.getPubDetail", pub, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			//添加列表为空提示
			if (list.size() == 0) {
                addActionMessage(getText("nodata.list"));
                return SUCCESS;
            }
			//当前页是否包含合计
			boolean bool=false;
			//如果当前请求是最后一页，则添加合计选项
			int irp=Integer.parseInt(rpNum);
			int totalPage=(totalCount+irp+1)/irp;//总页数
			if(Integer.parseInt(pageIndex)==totalPage){//当前页数等于总页数
				bool=true;
				//公差明细合计
				List<PubDetail> totalList=this.service.getObjects("RunStatisticManage.getPubDetail", pub);
				int per_sum=0;//承载人数
				double mileage=0;
				for(PubDetail pd:totalList){
					per_sum += Integer.parseInt(pd.getCount());
					mileage += Double.parseDouble(pd.getMileage());
				}
				PubDetail sum=new PubDetail();
				sum.setCount(String.valueOf(per_sum));
				sum.setMileage(String.valueOf(mileage));
				list.add(sum);
			}
			
			subMap=getPubDetailPagination(list, totalCount, pageIndex, rpNum,bool);// 转换map
		} catch (BusinessException e) {
			this.log.error("公差明细查询出错", e);
		}
		return SUCCESS;
	}
    /**
     * 得到当前请求对象
     */
    private HttpServletRequest getCurrentRequest(){
    	return (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    }
    /**
     * 员工刷卡记录
     */
    public String stcheckList(){
    	HttpServletRequest request = getCurrentRequest();
		//分页
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
		String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        stcheck.setSortName(sortName); 
        stcheck.setSortOrder(sortOrder);
        if(null == stcheck.getOrganization_id() ||"".equals(stcheck.getOrganization_id())){
        	stcheck.setOrganization_id(currentUser.getOrganizationID());
        }
        int totalCount = 0;
		try {
			totalCount=this.service.getCount("RunStatisticManage.getStckeckCount", stcheck);
			List<StCheckEntity> list=this.service.getObjectsByPage("RunStatisticManage.getStckeckList", stcheck, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			
			//getAddressInfo(list);
			//添加列表为空提示
			if (list.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }else{
            	this.map = getStCheckPagination(list, totalCount, pageIndex, rpNum);// 转换map
            }
		} catch (BusinessException e) {
			this.log.error("员工刷卡记录列表查询出错", e);
		}
		return SUCCESS;
    }
    /**
     * 批量获取地理位置
     */
    private List<StCheckEntity> getAddressInfo(List<StCheckEntity> list){
    	if(list==null){
    		return null;
    	}
    	StringBuffer sb=new StringBuffer();
    	for(StCheckEntity st:list){
    		String lon=st.getLocation();
    		String lat=st.getLatitude();
    		if(lon!=null && !"".equals(lon) && !"FFFF".equals(lon) && lat!=null && !"".equals(lat) && !"FFFF".equals(lat)){
    			sb.append(lon+","+lat+";");
    		}
    	}
    	if(sb.length()<1){
    		return list;
    	}
    	String address=getAddressinfo(sb.toString());
    	if(!"".equals(address)){
    		String[] addrArr=address.split(",");
    		for(int i=0;i<list.size();i++){
    			StCheckEntity st=list.get(i);
        		String lon=st.getLocation();
        		String lat=st.getLatitude();
        		if(lon!=null && !"".equals(lon) && !"FFFF".equals(lon) && lat!=null && !"".equals(lat) && !"FFFF".equals(lat)){
        			st.setLocation(addrArr[i]);
        		}
        	}
    	}
    	return list;
    }
    /**
     * 进入员工刷页面
     */
    public String stcheckPage(){
    	stcheck.setStart_time(DateUtil.getCurrentDay());
    	stcheck.setEnd_time(DateUtil.getCurrentDay());
    	if (null != message) {
			if("50000".equals(message)){
				addActionError(getText("无法导出，系统最多可一次导出5W条记录!"));
			}
		}
    	return SUCCESS;
    }
    /**
     * 员工刷卡记录导出
     */
    public String exportStCheckList(){
		String filePath = "";
        OutputStream outputStream = null;
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
        if(null == stcheck.getOrganization_id() ||"".equals(stcheck.getOrganization_id())){
        	stcheck.setOrganization_id(currentUser.getOrganizationID());
        }
        try {
        	List<StCheckEntity> exportlist=this.service.getObjects("RunStatisticManage.getStckeckList", stcheck);
        	if(exportlist.size()> 50000){
            	setMessage("50000");
            	 //request.setAttribute("tipMessage", getMessage()); 
            	return ERROR;
            }
        	getAddressInfo(exportlist);
        	
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "stCheckExport.xls";
            
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("员工刷卡记录("+stcheck.getStart_time()+"~"+stcheck.getEnd_time()+")");

            excelExporter.putAutoExtendSheets("stCheckExport", 0, exportlist);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            log.error("Export emp  read card error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export emp  read card error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
        // 设置下载文件属性
        FileInputStream fileInputStream = null;
        OutputStream out = null;
        try {
        	// 设置下载文件属性
    		String fileName = URLEncoder.encode("员工刷卡记录","UTF8");
    		HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("Content-disposition", "attachment;filename="+fileName+"-" + DateUtil.formatDateToString(new Date(), "yyyyMMddHHmmss") + ".xls");
	        response.setContentType("application/msexcel; charset=\"utf-8\"");
    	        
            // 下载刚生成的文件
            fileInputStream = new FileInputStream(filePath);
            out = response.getOutputStream();
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
        	log.error("Export emp  read card error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
        	log.error("Export emp  read card error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
        return null;
	}
    /**
     * 转换Map 维修明细
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getRepaireDetailPagination(List<RepaireDetail> list, int totalCount, String pageIndex, String rpNum,boolean isShowSum) {
        List<Object> mapList = new ArrayList<Object>();
        Map<String,Object> mapData = new HashMap<String,Object>();
        int size=list.size();
        String seq="0";
        for (int i = 0; i < list.size(); i++) {
        	if(i==size-1 && isShowSum){
        		seq="合计";
        	}else{
        		seq=String.valueOf(i+1);
        	}
        	RepaireDetail info=list.get(i);
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id", info.getVehicle_vin());
            cellMap.put("cell", new Object[] {
            		seq,
            		info.getUpdate_time(),
            		info.getFault_desc(),
            		info.getFault_type(),
            		info.getFix_flag(),
            		info.getDriver_name(),
            		info.getWorkTimeFee(),
            		info.getPartFee(),
            		info.getTotalFee()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 转换Map 公差明细
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getPubDetailPagination(List<PubDetail> list, int totalCount, String pageIndex, String rpNum,boolean isShowSum) {
        List<Object> mapList = new ArrayList<Object>();
        Map<String,Object> mapData = new HashMap<String,Object>();
        int size=list.size();
        String seq="0";
        for (int i = 0; i < size; i++) {
        	PubDetail info=list.get(i);
        	if(i==size-1 && isShowSum){
        		seq="合计";
        	}else{
        		seq=String.valueOf(i+1);
        	}
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id", info.getVehicle_vin());
            cellMap.put("cell", new Object[] {
            		seq,
            		info.getRoute_name(),
            		info.getCount(),
            		info.getLimite_number(),
            		info.getStart_time(),
            		info.getEnd_time(),
            		info.getMileage(),
            		info.getDriver_name()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 转换Map 通勤明细
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getWorkDetailPagination(List<WorkDetail> list, int totalCount, String pageIndex, String rpNum) {
        List<Object> mapList = new ArrayList<Object>();
        Map<String,Object> mapData = new HashMap<String,Object>();
        for (int i = 0; i < list.size(); i++) {
        	WorkDetail info=list.get(i);
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id", info.getVehicle_vin());
            cellMap.put("cell", new Object[] {
            		i+1,
            		info.getRoute_name(),
            		info.getRoute_class(),
            		info.getLoad_number(),
            		info.getLimite_number(),
            		info.getStart_time(),
            		info.getEnd_time(),
            		info.getTotal_mileage(),
            		info.getDriver_name()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 转换Map 运营统计列表
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getPagination(List<RunStatisticEntity> list, int totalCount, String pageIndex, String rpNum) {
        List<Object> mapList = new ArrayList<Object>();
        Map<String,Object> mapData = new HashMap<String,Object>();
        for (int i = 0; i < list.size(); i++) {
        	RunStatisticEntity info=list.get(i);
        	//double load_mileage=Double.valueOf(info.getLoad_mileage())+Double.valueOf(info.getP_mileage());
			//double getTotal_mileage=Double.valueOf(info.getLoad_mileage())+Double.valueOf(info.getEmpty_mileage());
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id", info.getVehicle_vin());
            cellMap.put("cell", new Object[] {
            		info.getShort_name(),
            		info.getVehicle_code(),
            		info.getVehicle_ln(),
            		info.getDriver_name(),
            		info.getLoad_mileage(),
            		info.getEmpty_mileage(),
            		info.getTotal_mileage(),
            		info.getTotal_oil(),
            		info.getOil_100(),
            		info.getLoad_number(),
            		info.getMorning_cnt(),
            		info.getNight_cnt(),
            		info.getTotal_cnt(),
            		info.getRepare_cnt(),
            		info.getCost_component(),
            		info.getP_cnt(),
            		info.getP_mileage()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 转换Map 员工刷卡记录
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getStCheckPagination(List<StCheckEntity> list, int totalCount, String pageIndex, String rpNum) {
        List<Object> mapList = new ArrayList<Object>();
        Map<String,Object> mapData = new HashMap<String,Object>();
        for (int i = 0; i < list.size(); i++) {
        	StCheckEntity info=list.get(i);
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            //日期格式化
            String sdate=info.getTerminal_time();
            if(sdate!=null && !"".equals(sdate)){
            	Date d=DateUtil.parseStringToDate(sdate, "yyyyMMddHHmmss");
            	sdate=DateUtil.formatDateToString(d, "yyyyMMddHHmmss");
            }
            cellMap.put("id", sdate+info.getEmp_code());
            cellMap.put("cell", new Object[] {
            		info.getEmp_name(),
            		info.getEmp_code(),
            		info.getEmp_card_id(),
            		info.getOrg_name(),
            		info.getVehicle_code(),
            		info.getVehicle_ln(),
            		info.getDriver_name(),
            		info.getRoute_name(),
            		info.getSite_name(),
            		info.getTerminal_time(),
            		info.getLocation()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 根据坐标返回地理信息
     * @param points
     * @return
     */
    private String getAddressinfo(String points){
    	String result="";
    	
    	try {
			PostMethod postMethodProxyRequest = new PostMethod(
					"http://apis.mapabc.com/rgeocode/simple");

			postMethodProxyRequest.addParameter("sid", "7001");
			postMethodProxyRequest.addParameter("resType", "xml");
			postMethodProxyRequest.addParameter("encode", "UTF-8");
			postMethodProxyRequest.addParameter("key", getText("map.key"));
			postMethodProxyRequest.addParameter("region", points);
			//postMethodProxyRequest.addParameter("range", "50");
			postMethodProxyRequest.addParameter("roadnum", "1");
			postMethodProxyRequest.addParameter("crossnum", "0");
			postMethodProxyRequest.addParameter("poinum", "0");
			postMethodProxyRequest.addParameter("show_near_districts", "false");
			
			HttpClient httpClient = new HttpClient();
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(3000);
//			httpClient.getHostConfiguration().setProxy("proxy.neusoft.com",
//					8080);//上线屏蔽
			httpClient.getParams().setContentCharset("UTF-8");

			int app = httpClient.executeMethod(postMethodProxyRequest);
			
			String s = postMethodProxyRequest.getResponseBodyAsString();
			log.info("逆地址查询应答码：" + app+";逆地址查询结果:"+s);
	        if(app==200){
	        	SearchresultDocument srdoc=SearchresultDocument.Factory.parse(s);
				Searchresult seares=srdoc.getSearchresult();
				if(null!=seares.getList()){
					if(null!=seares.getList().getSpatialArray()){
						for (int i=0;i<seares.getList().getSpatialArray().length;i++){
							Spatial sp=seares.getList().getSpatialArray(i);
							if(null!=sp.getRoadlist()){
								if (null!=sp.getRoadlist().getRoadArray() && sp.getRoadlist().getRoadArray().length>0){
									Road rd=sp.getRoadlist().getRoadArray(0);
									if(null!=rd){
										result=result+rd.getName()+"附近,";
									}else{
										result=result+" ,";
									}
								}else{
									result=result+" "+",";
								}
							}else{
								result=result+" "+",";
							}
							
						}
					}
					
				}
	        }

		} catch (HttpException e) {
			log.error("获取逆地址查询时出错", e);
		} catch (Exception e) {
			log.error("获取逆地址查询时出错", e);
		}
		return result;
    }
}
