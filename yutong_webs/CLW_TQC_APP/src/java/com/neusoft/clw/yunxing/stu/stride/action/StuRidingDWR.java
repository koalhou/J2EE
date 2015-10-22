package com.neusoft.clw.yunxing.stu.stride.action;

import java.util.List;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yunxing.routechart.chart.domain.RouteChart;
/**
 * @author <a href="mailto:fanxiaoyu@neusoft.com">fanxiaoyu</a>
 */
public class StuRidingDWR extends BaseAction{

    /** service共通类 */
    private transient Service service;
    
    /**
     * 通过路线ID获取相关车辆车信息
     * @param route_id
     * @param user_org_id
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<RouteChart> getVehByRoute(String route_id, String user_org_id){
        log.info("通过线路查询 线路上的车,线路编号："+route_id);
        
        List<RouteChart> result = null;
        if(route_id != null && !"".equals(route_id)){
            RouteChart queryObj = new RouteChart();
            queryObj.setRoute_id(route_id);
            queryObj.setUser_organization_id(user_org_id);
            try {
                result = service.getObjects("StuRide.getVehByRouteid", queryObj);
            } catch (BusinessException e) {
                log.info("StuRidingDWR.getVehByRouteid-result cause error:", e);
            }            
        }
        return result;
    }
    
    /**
     * 通过路线ID获取相关站点信息
     * @param route_id
     * @param user_org_id
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<RouteChart> getSiteByRoute(String route_id){
        log.info("通过线路查询 线路上的车,线路编号："+route_id);
        
        List<RouteChart> result = null;
        if(route_id != null && !"".equals(route_id)){
            RouteChart queryObj = new RouteChart();
            queryObj.setRoute_id(route_id);
            try {
                result = service.getObjects("StuRide.getSiteByRouteid", queryObj);
            } catch (BusinessException e) {
                log.info("StuRidingDWR.getSiteByRoute-result cause error:", e);
            }            
        }
        return result;
    }
    
    
    /**
     * 如果为null则转化为""
     * @param strVar
     * @return
     */
    private String chanNullToEmpty(String strVar) {
        return strVar != null ? strVar : "";
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    } 

}
