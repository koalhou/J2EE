package com.neusoft.clw.yw.statistic.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.statistic.domain.BehaviorInfo;
import com.neusoft.clw.yw.statistic.domain.LoginStatistic;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class LoginStatisticAction extends PaginationAction {
	
	
	//登陆次数实体
	private LoginStatistic statistic;
	//业务类
	private Service service;
	//json数据map
    private Map<String, Object> map=new HashMap<String,Object>();
    private String empcode;//员工编号
    private String begindate;//开始时间
    private String enddate;//结束时间
    private String message;//导出反馈信息
    private BehaviorInfo behavior;
    
	public BehaviorInfo getBehavior() {
		return behavior;
	}

	public void setBehavior(BehaviorInfo behavior) {
		this.behavior = behavior;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBegindate() {
		return begindate;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	public LoginStatistic getStatistic() {
		return statistic;
	}

	public void setStatistic(LoginStatistic statistic) {
		this.statistic = statistic;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	/**
	 * 进入列表页面
	 * @return success
	 */
	public String init(){
		if(this.statistic==null){
			this.statistic=new LoginStatistic();
			Calendar c=Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			this.statistic.setBeginDate(DateUtil.formatDateToString(c.getTime(), "yyyy-MM-dd"));
			this.statistic.setEndDate(DateUtil.getCurrentDay());
		}
		return SUCCESS;
	}
	/**
	 * 根据条件查询登陆次数
	 */
	public String loginList(){
		String pageTitle="登陆次数列表";
    	log.info(pageTitle);
    	
		HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        int totalCount = 0;
        try {
			totalCount = this.service.getCount("Statistic.getLoginCount", statistic);
			List<LoginStatistic> list = (List <LoginStatistic>) this.service.getObjectsByPage("Statistic.getLoginList", statistic, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			this.map = getPaginationList(list, totalCount, pageIndex, rpNum);// 转换map
	        if (list.size() == 0) {
	            addActionMessage(getText("nodata.list"));
	        }
		} catch (BusinessException e) {
			
			return ERROR;
		}
        
		return SUCCESS;
	}
	/**
	 * 根据条件查询登陆次数 并导出
	 */
	public String exportLoginList(){
		String filePath = "";
        OutputStream outputStream = null;
        try {
        	List<LoginStatistic> exportlist=this.service.getObjects("Statistic.getLoginList", statistic);
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "LoginCount.xls";
            
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            String title="";
            String beginDate=this.statistic.getBeginDate();
            String endDate=this.statistic.getEndDate();
            if(endDate!=null && "".equals(endDate) && beginDate!=null && "".equals(beginDate)){
            	title="("+this.statistic.getBeginDate()+"至"+this.statistic.getEndDate()+")";
            }
            excelExporter.setTitle("登陆次数信息统计"+title);

            excelExporter.putAutoExtendSheets("exportLoginList", 0, exportlist);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export loginCount error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export loginCount error:" + e.getMessage());
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
    		String fileName = URLEncoder.encode("登陆次数信息","UTF8");
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
            setMessage("file.export.error");
            log.error("Export driver error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export loginCount error:" + e.getMessage());
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
	 * 进入登陆详情页面
	 */
	public String loginDetailPage(){
		
		return SUCCESS;
	}
	/**
	 * 根据员工编号查询登陆详情
	 * 
	 */
	public String loginDetailList(){
		
		String pageTitle="登陆次数详情列表";
    	log.info(pageTitle);
    	
		HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		
		String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        //查询参数
        this.statistic=new LoginStatistic();
        this.statistic.setEmpCode(this.empcode);
        this.statistic.setBeginDate(this.begindate);
        this.statistic.setEndDate(this.enddate);
        int totalCount = 0;
        try {
			totalCount = this.service.getCount("Statistic.getLoginDetailCount", statistic);
			List<LoginStatistic> list = (List <LoginStatistic>) this.service.getObjectsByPage("Statistic.getLoginDetailList", statistic, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			this.map = getPaginationDetailList(list, totalCount, pageIndex, rpNum);// 转换map
	        if (list.size() == 0) {
	            addActionMessage(getText("nodata.list"));
	        }
		} catch (BusinessException e) {
			
			return ERROR;
		}
        
		return SUCCESS;
	}
	/**
	 * 登陆次数查询，柱状图显示
	 */
	public String getLoginCharts(){
		try {
			List<Map<String,BigDecimal>> list=this.service.getObjects("Statistic.getLoginCharts", statistic);
			//柱状图显示格式化显示
			formatData(list);
		} catch (BusinessException e) {
			log.error("登陆次数查询，柱状图显示错误", e);
		}
		return SUCCESS; 
	}
	/**
	 * 登陆次数详情查询，柱状图显示
	 */
	public String getLoginDetailCharts(){
		try {
			List<Map<String,BigDecimal>> list=this.service.getObjects("Statistic.getLoginDetailCharts", statistic);
			//柱状图显示格式化显示
			formatData(list);
		} catch (BusinessException e) {
			log.error("登陆次数查询，柱状图显示错误", e);
		}
		return SUCCESS; 
	}
	/**
	 * 柱状图显示格式化显示
	 */
	private void formatData(List<Map<String,BigDecimal>> list){
		behavior=new BehaviorInfo();
		for(Map<String,BigDecimal> map:list){
			String timeId=map.get("TIMEID").toPlainString();
			String total=map.get("TOTAL").toPlainString();
			if("1".equals(timeId)){
				behavior.setZero2two(total);
			}else if("2".equals(timeId)){
				behavior.setTwo2four(total);
			}else if("3".equals(timeId)){
				behavior.setFour2six(total);
			}else if("4".equals(timeId)){
				behavior.setSix2eight(total);
			}else if("5".equals(timeId)){
				behavior.setEight2ten(total);
			}else if("6".equals(timeId)){
				behavior.setTen2tweleve(total);
			}else if("7".equals(timeId)){
				behavior.setTweleve2fourteen(total);
			}else if("8".equals(timeId)){
				behavior.setFourteen2sixteen(total);
			}else if("9".equals(timeId)){
				behavior.setSixteen2eighteen(total);
			}else if("10".equals(timeId)){
				behavior.setEighteen2twenty(total);
			}else if("11".equals(timeId)){
				behavior.setTwenty2twentytwo(total);
			}else if("12".equals(timeId)){
				behavior.setTwentytwo2twentyfour(total);
			}
		}
	}
	/**
     * 转换Map
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getPaginationList(List<LoginStatistic> list, int totalCount, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map<String,Object> mapData = new HashMap<String,Object>();
        for (int i = 0; i < list.size(); i++) {
        	LoginStatistic statistic = list.get(i);
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id", statistic.getEmpCode());
            cellMap.put("cell", new Object[] {
            		statistic.getEmpCode(),
            		statistic.getEmpName(),
            		statistic.getLoginCount()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 转换Map
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getPaginationDetailList(List<LoginStatistic> list, int totalCount, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map<String,Object> mapData = new HashMap<String,Object>();
        for (int i = 0; i < list.size(); i++) {
        	LoginStatistic statistic = list.get(i);
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id", statistic.getEmpCode());
            cellMap.put("cell", new Object[] {
            		statistic.getEmpCode(),
            		statistic.getEmpName(),
            		statistic.getLoginTime(),
            		statistic.getLoginType()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
}
