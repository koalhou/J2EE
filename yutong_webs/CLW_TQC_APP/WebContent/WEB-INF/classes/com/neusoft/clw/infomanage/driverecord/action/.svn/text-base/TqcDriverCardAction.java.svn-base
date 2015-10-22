/*******************************************************************************
 * @(#)UploadUtilAction.java 2012-5-3
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.infomanage.driverecord.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.infomanage.driverecord.domain.TqcDriverCard;
import com.neusoft.clw.safemanage.averagefuel.ranking.domain.rankingExport;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunHistory;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="mailto:jin.p@neusoft.com">lis </a>
 * @version $Revision 1.1 $ 2012-5-3 下午03:50:23
 */
public class TqcDriverCardAction extends BaseAction {
    /** service共通类 */
    private transient Service service;
    private String errorMessage;
    private String message;
    private Map<String,Object> map = new HashMap<String,Object>();
    
    public String init() {
    	
    	if (StringUtils.isEmpty(endtime)) {
			endtime= DateUtil.getCurrentDay();
	    }
		if (StringUtils.isEmpty(begintime)) {
        	begintime=DateUtil.getMonthFirstDay1();//这一月的第一天   
        }
    	
        return SUCCESS;
    }
    /**
     * 查询大区与站点关联信息
     * 
     * */
    public String driveRecordList() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	UserInfo user = getCurrentUser();
    	int totalCount = 0;
    	
    	String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
    	
    	Map<String, Object> enmap = new HashMap<String, Object>();
        enmap.put("sortname",sortName);
        enmap.put("sortorder",sortOrder);
        enmap.put("rowStart",(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
        enmap.put("rowEnd",(Integer.parseInt(pageIndex))*Integer.parseInt(rpNum));

        enmap.put("drivername",request.getParameter("drivername"));
        enmap.put("vehiclecode",request.getParameter("vehiclecode"));
        enmap.put("organization_id",request.getParameter("organization_id"));
        enmap.put("vehiclevin",request.getParameter("vehiclevin"));
        enmap.put("begintime",request.getParameter("begintime"));
        enmap.put("endtime",request.getParameter("endtime"));
        List<TqcDriverCard> cardPlanList = new ArrayList<TqcDriverCard>();
        try {
        	if(request.getParameter("vehiclevin")!=null&&!"".equals(request.getParameter("vehiclevin"))) {
	        	totalCount = service.getCount("driverecord.getdriverecordvinListCount", enmap);
	        	cardPlanList = (List<TqcDriverCard>) service.getObjects("driverecord.getdriverecordvinList", enmap);
        	} else if(request.getParameter("organization_id")!=null&&!"".equals(request.getParameter("organization_id"))) {
        		totalCount = service.getCount("driverecord.getdriverecordListCount", enmap);
	        	cardPlanList = (List<TqcDriverCard>) service.getObjects("driverecord.getdriverecordList", enmap);
        	} else {
        		return SUCCESS;
        	}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.map = getPagination(cardPlanList, totalCount, pageIndex, rpNum);
    	return SUCCESS;
    }
    public Map<String,Object> getPagination(List<TqcDriverCard> ridingPlanList, int totalCountDay, String pageIndex, String rpNum) {
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        Map<String,Object> mapData = new LinkedHashMap<String,Object>();
        for (int i = 0; i < ridingPlanList.size(); i++) {
        	TqcDriverCard s = (TqcDriverCard) ridingPlanList.get(i);

            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();

            cellMap.put("id", s.getAutoId());

            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getDriver_name(),
                    s.getVehicle_code(),
                    s.getVehicle_ln(),
                    s.getRoute_name(),
                    s.getTerminal_time()
                    });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
  //导出表格
    //导出表格必须是String格式，其中有强制转换的部分代码
	public String exportRanking() throws UnsupportedEncodingException {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String modelTitle = getText("驾驶员刷卡记录导出");
		UserInfo user = getCurrentUser();
		
		int totalCount = 0;
        List<TqcDriverCard> cardPlanList = new ArrayList<TqcDriverCard>();
        List <TqcDriverCard> exportlist = new ArrayList<TqcDriverCard>();
        
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        
		Map<String, Object> enmap = new HashMap<String, Object>();
        enmap.put("sortname",sortName);
        enmap.put("sortorder",sortOrder);

        enmap.put("drivername",request.getParameter("drivername"));
        enmap.put("vehiclecode",request.getParameter("choiceroutename"));
        enmap.put("organization_id",request.getParameter("user_org_id"));
        enmap.put("vehiclevin",request.getParameter("vehicle_vin"));
        enmap.put("begintime",request.getParameter("begintime"));
        enmap.put("endtime",request.getParameter("endtime"));

		//查询SQLMap，车辆VIN还没有传过去
		try {
			if(getRe_flag()==1) {
	        	cardPlanList = (List<TqcDriverCard>) service.getObjects("driverecord.exportdriverecordvinList", enmap);
        	} else if(getRe_flag()==0) {
	        	cardPlanList = (List<TqcDriverCard>) service.getObjects("driverecord.exportdriverecordList", enmap);
        	} else {
        		return SUCCESS;
        	}
        } catch (BusinessException e) {
            setMessage("info.db.error");
            //log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            //log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        }
        
        for (int i = 0; i < cardPlanList.size(); i++) {
            exportlist.add(cardPlanList.get(i));
        }
		
		String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "DriveRecord.xls";

			// add by liuja start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by liuja stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			//excelExporter.setTitle("驾驶员刷卡记录");
            excelExporter.setTitle("驾驶员刷卡记录("+request.getParameter("begintime")+ "~"+request.getParameter("endtime")+")");
			if(exportlist == null || exportlist.size()<1){
            	exportlist.add(new TqcDriverCard());
            }

		    excelExporter.putAutoExtendSheets("exportDriveRecord", 0,exportlist);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error(modelTitle + "异常", e);
			return ERROR;
		} catch (Exception e) {
			log.error(modelTitle + "异常", e);
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
		String fileName=URLEncoder.encode("驾驶员刷卡统计","UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-disposition",
				"attachment;filename="+fileName+"-" +DateUtil.getCurrentDayTime()+ ".xls");
		response.setContentType("application/msexcel; charset=\"utf-8\"");

		FileInputStream fileInputStream = null;
		OutputStream out = null;
		try {
			// 下载刚生成的文件
			fileInputStream = new FileInputStream(filePath);
			out = response.getOutputStream();
			int i = 0;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
		} catch (FileNotFoundException e) {
			log.error(modelTitle + "异常", e);
			return ERROR;
		} catch (Exception e) {
			log.error(modelTitle + "异常", e);
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
			// 设置操作描述
			//this.addOperationLog(formatLog(modelTitle, null));
			 // 设置操作描述
            this.addOperationLog("驾驶员刷卡导出");
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.CLW_P_CODE);
			// 设置所属模块
			//this.setModuleId(MouldId.XCP_CAR_RUN_HISTORY_EXP);
		}
		log.info(modelTitle + "结束");
		// 导出文件成功
		return null;
	}
    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }
    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	private int re_flag;  //组织还是车辆，0：组织，1：车辆
    public int getRe_flag() {
        return re_flag;
    }

    public void setRe_flag(int re_flag) {
    	this.re_flag = re_flag;
    }
    
  //页面进入时，查询默认开始时间
	private String begintime;
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	
	//页面进入时，查询默认结束时间
	 private String endtime;
	 public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
}
