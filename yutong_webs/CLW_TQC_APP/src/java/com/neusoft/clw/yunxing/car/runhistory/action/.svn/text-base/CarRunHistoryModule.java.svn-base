package com.neusoft.clw.yunxing.car.runhistory.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yunxing.car.runhistory.domain.ClwXcTripHistory;
import com.opensymphony.xwork2.ActionContext;

public class CarRunHistoryModule extends PaginationAction {
	/** service共通类 */
    private transient Service service;
    
    private ClwXcTripHistory clwXcTripHistory;
    private String route_id;
    /** 显示消息 **/
    private String message = null;
    private String errorMessage;//错误提示信息
    private Map map = new HashMap();
    private List<ClwXcTripHistory> tropmoduleList;
    /**
     * 列表信息页面
     * @return
     */
    public String tripready() {
    	if (null != message) {
            addActionMessage(getText(message));
        }
    	if (null != errorMessage) {
            addActionError(getText(errorMessage));
            return ERROR;
        }
    	try {
    		if(route_id!=null) {
				String name = (String) service.getObject("CarRunHistoryModule.getRoutenamebyid", route_id);
				clwXcTripHistory = new ClwXcTripHistory();
		    	clwXcTripHistory.setRouteId(route_id);
		    	clwXcTripHistory.setRoutename(name);
    		} else {
    			return ERROR;
    		}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return SUCCESS;
    }

    public String tripmodule_form() {
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("route_id", clwXcTripHistory.getRouteId());
    	param.put("module_desc", clwXcTripHistory.getModuleDesc());
    	param.put("name", clwXcTripHistory.getName());
    	param.put("out_flag", "");
    	try {
			service.getObject("CarRunHistoryModule.insertinfobyrouteid", param);
			
			if(param.get("out_flag").equals("0")) {
				this.setMessage("routemodule.addsuccess.message");
			} else if(param.get("out_flag").equals("1")){
				this.setErrorMessage("routemodule.errorsave.message");
				return ERROR;
			}else {
				this.setErrorMessage("routemodule.adderror.message");
				return ERROR;
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    public void tripmodule_form2() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("route_id", clwXcTripHistory.getRouteId());
    	param.put("module_desc", clwXcTripHistory.getModuleDesc());
    	param.put("name", clwXcTripHistory.getName());
    	param.put("out_flag", "");
    	try {
			service.getObject("CarRunHistoryModule.insertinfobyrouteid", param);
			
			if(param.get("out_flag").equals("0")) {
				printWriter("线路模板成功添加！");
			} else if(param.get("out_flag").equals("1")){
				printWriter("该线路模板名称已存在！");
			}else {
				printWriter("error");
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 列表信息页面
     * @return
     */
    public String tripListready() {
    	if (null != message) {
            addActionMessage(getText(message));
        }
    	if (null != errorMessage) {
            addActionError(getText(errorMessage));
        }
		if(route_id!=null) {
	    	clwXcTripHistory.setRouteId(route_id);
	    	return SUCCESS;
		} else {
			return ERROR;
		}
    }
    public String getSelectModuleList() {
        int totalCount = 0;
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        String name = request.getParameter("name");
        String create_time = request.getParameter("create_time");
        
        String route_id = request.getParameter("route_id");
        
        Map<String,Object> param = new HashMap<String,Object>();
        try {
            param.put("sortname", sortName);
            param.put("sortorder", sortOrder);
            
            param.put("route_id", route_id);
            param.put("name", name);
            param.put("create_time", create_time);
            
            param.put("rowStart", (Integer.parseInt(pageIndex) - 1)* Integer.parseInt(rpNum));
            param.put("rowEnd", Integer.parseInt(pageIndex)*Integer.parseInt(rpNum));

            totalCount = service.getCount("CarRunHistoryModule.getclwXcTripHistoryscount", param);

            tropmoduleList = (List<ClwXcTripHistory>) service.getObjects("CarRunHistoryModule.getclwXcTripHistorys", param);

            this.map = getPagination(tropmoduleList, totalCount, pageIndex);
        } catch (BusinessException e) {
            return ERROR;
        }
    	return SUCCESS;
    }
    /**
     * 转换为Map对象
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPagination(List vehcList, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < vehcList.size(); i++) {

        	ClwXcTripHistory s = (ClwXcTripHistory) vehcList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getId());

            cellMap.put("cell", new Object[] {s.getName(),
                    s.getModuleDesc(),s.getCreateTime(),s.getId(),s.getRouteId()});

            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    public void searchmoduleto() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	String moduleid = request.getParameter("id");
    	String route_id = request.getParameter("route_id");
    	
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("moduleid", moduleid);
    	param.put("route_id", route_id);
    	param.put("out_flag", "");
    	try {
			service.getObject("CarRunHistoryModule.updateinfobyid", param);
			System.out.println(param.get("out_flag"));
			
			if(param.get("out_flag").equals("0"))
				printWriter("success");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void delmoduleto() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	String moduleid = request.getParameter("id");
    	
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("moduleid", moduleid);
    	try {
			service.getObject("CarRunHistoryModule.delinfobyid", param);
			
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public ClwXcTripHistory getClwXcTripHistory() {
		return clwXcTripHistory;
	}

	public void setClwXcTripHistory(ClwXcTripHistory clwXcTripHistory) {
		this.clwXcTripHistory = clwXcTripHistory;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
    
    
}
