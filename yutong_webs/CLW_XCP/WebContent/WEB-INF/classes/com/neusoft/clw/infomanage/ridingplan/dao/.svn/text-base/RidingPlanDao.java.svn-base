package com.neusoft.clw.infomanage.ridingplan.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.SqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.infomanage.ridingplan.domain.VehdriverInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.VehsichenInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.VssInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.Vss_SiteInfo;

public class RidingPlanDao extends SqlMapDao{
   
	public void batchAddRidingPlan(Map map, List driverList, List sichenList,
            List vssList, List vss_siteList)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        try {
            // 开启批处理
            sqlmapclient.startBatch();
            
            /*
             * 修改校车三期新需求，注释以下代码
             * mod by yg
             * */
            /*  当前分配车辆相关数据删除  add by suyingtao 2012-06-11*/
            /*getSqlMapClientTemplate().delete("RidingPlan.deleteRoute", map);
            LOG.info("新增乘车规划:SQL 删除线路=RidingPlan.deleteRoute");
            getSqlMapClientTemplate().delete("RidingPlan.delete_vehdriver",
                    map);
            LOG.info("新增乘车规划:SQL 删除司机=RidingPlan.delete_vehdriver");
            getSqlMapClientTemplate().delete("RidingPlan.delete_vehsichen",
                    map);
            LOG.info("新增乘车规划:SQL 删除司乘=RidingPlan.delete_vehsichen");
            getSqlMapClientTemplate()
                    .delete("RidingPlan.delete_xc_vss", map);
            LOG.info("新增乘车规划:SQL 删除vss订购关系表=RidingPlan.delete_xc_vss");
            getSqlMapClientTemplate().delete("RidingPlan.delete_xc_vss_site",
                    map); 
            LOG.info("新增乘车规划:SQL 删除vss_site订购关系表=RidingPlan.delete_xc_vss");*/
            /*  end  add by suyingtao 2012-06-11*/
            
            /*getSqlMapClientTemplate().update("RidingPlan.updateRoute", map);
            LOG.info("新增乘车规划:SQL 更新线路=RidingPlan.updateRoute");*/
            getSqlMapClientTemplate().insert("RidingPlan.insert_xc_trip_t",map);
            LOG.info("新增乘车规划:SQL 插入xc_trip行程表=RidingPlan.insert_xc_trip_t");
            
            if(null!=driverList){
	            for (int i = 0; i < driverList.size(); i++) {
	            	VehdriverInfo vi=(VehdriverInfo) driverList.get(i);
	            	if(!vi.getDriver_id().equals("")){
	            		getSqlMapClientTemplate().insert("RidingPlan.insert_vehdriver",
	 	                        driverList.get(i));
	 	                LOG.info("新增乘车规划:SQL 添加司机=RidingPlan.insert_vehdriver");
	 	               
	 	                LOG.info("新增乘车规划:添加司机->参数 Driver_id="+vi.getDriver_id()+",Vehicle_vin="+vi.getVehicle_vin()+",trip_id="+vi.getTrip_id());

	            	}
	           }
            }
            if(null!=sichenList){
	            for (int i = 0; i < sichenList.size(); i++) {
	            	VehsichenInfo vi=(VehsichenInfo) sichenList.get(i);
	            	if(!vi.getSteward_id().equals("")){
	            		getSqlMapClientTemplate().insert("RidingPlan.insert_vehsichen",
		                        sichenList.get(i));
		                LOG.info("新增乘车规划:SQL 添加司乘=RidingPlan.insert_vehsichen");
		               
		                LOG.info("新增乘车规划:添加司乘->参数 Steward_id="+vi.getSteward_id()+",Vehicle_vin="+vi.getVehicle_vin()+",trip_id="+vi.getTrip_id());
	            	}
	           }
            }
            for (int i = 0; i < vssList.size(); i++) {
                getSqlMapClientTemplate().insert("RidingPlan.insert_xc_vss",
                        vssList.get(i));
                LOG.info("新增乘车规划:SQL 添加VSS订购关系=RidingPlan.insert_xc_vss");
                VssInfo vi=(VssInfo) vssList.get(i);
                LOG.info("新增乘车规划:添加VSS订购关系->参数 Route_id="+vi.getRoute_id()+",Site_id="+vi.getSite_id()+",Student_id="+vi.getStudent_id()+",Vehicle_vin="+vi.getVehicle_vin()+",Vss_state="+vi.getVss_state()+",trip_id="+vi.getTrip_id());
            }
            for (int i = 0; i < vss_siteList.size(); i++) {
                getSqlMapClientTemplate().insert(
                        "RidingPlan.insert_xc_vss_site", vss_siteList.get(i));
                LOG.info("新增乘车规划:SQL 添加vss_site订购关系=RidingPlan.insert_xc_vss_site");
                Vss_SiteInfo vi=(Vss_SiteInfo) vss_siteList.get(i);
                LOG.info("新增乘车规划:添加vss_site订购关系->参数 Route_id="+vi.getRoute_id()+",Site_id="+vi.getSite_id()+",Vehicle_vin="+vi.getVehicle_vin()+",Plan_in_time="+vi.getPlan_in_time()+",Plan_out_time="+vi.getPlan_out_time()+",trip_id="+vi.getTrip_id());
            }
            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public void batchDeletedRidingPlan(Map map)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        try {
            // 开启批处理
            sqlmapclient.startBatch();
            //getSqlMapClientTemplate().delete("RidingPlan.deleteRoute", map);
            //LOG.info("删除乘车规划:SQL 删除线路=RidingPlan.deleteRoute");
            getSqlMapClientTemplate()
                    .delete("RidingPlan.delete_vehdriver", map);
            LOG.info("删除乘车规划:SQL 删除司机=RidingPlan.delete_vehdriver");
            getSqlMapClientTemplate()
                    .delete("RidingPlan.delete_vehsichen", map);
            LOG.info("删除乘车规划:SQL 删除司乘=RidingPlan.delete_vehsichen");
            getSqlMapClientTemplate().delete("RidingPlan.delete_xc_vss", map);
            LOG.info("删除乘车规划:SQL 删除vss订购关系表=RidingPlan.delete_xc_vss");
            getSqlMapClientTemplate().delete("RidingPlan.delete_xc_vss_site",
                    map);
            LOG.info("删除乘车规划:SQL 删除vss_site订购关系表=RidingPlan.delete_xc_vss");
            getSqlMapClientTemplate().delete("RidingPlan.delete_xc_trip",
                    map);
            LOG.info("删除乘车规划:SQL 删除xc_trip行程表=RidingPlan.delete_xc_trip");
            
            
            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public void batchUpdateRidingPlan(Map map, List driverList,
            List sichenList, List vssList, List vss_siteList,
            String vehicle_vin_old)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        try {
            // 开启批处理
            sqlmapclient.startBatch();
            /*
             * 修改校车三期新需求，注释以下代码
             * mod by yg
             * */
            //Map delmap = new HashMap();
            /*  按照车辆、线路删除数据，此处未删除新分配车辆的相关数据
            delmap.put("route_id", map.get("route_id"));
            delmap.put("vehicle_vin", vehicle_vin_old);
            getSqlMapClientTemplate().delete("RidingPlan.deleteRoute", delmap);
            getSqlMapClientTemplate().delete("RidingPlan.delete_vehdriver",
                    delmap);
            getSqlMapClientTemplate().delete("RidingPlan.delete_vehsichen",
                    delmap);
            getSqlMapClientTemplate()
                    .delete("RidingPlan.delete_xc_vss", delmap);
            getSqlMapClientTemplate().delete("RidingPlan.delete_xc_vss_site",
                    delmap);
            */
            
            /*  按照车辆删除数据，新旧车辆数据都删掉    add by suyingtao 2012-06-11*/
            /*  old车辆相关数据删除 */
            /*delmap.put("route_id", map.get("route_id"));
            delmap.put("vehicle_vin", vehicle_vin_old);
            getSqlMapClientTemplate().delete("RidingPlan.deleteRoute", delmap);
            LOG.info("修改乘车规划:SQL 删除线路=RidingPlan.deleteRoute");
            getSqlMapClientTemplate().delete("RidingPlan.delete_vehdriver",
                    delmap);
            LOG.info("修改乘车规划:SQL 删除司机=RidingPlan.delete_vehdriver");
            getSqlMapClientTemplate().delete("RidingPlan.delete_vehsichen",
                    delmap);
            LOG.info("修改乘车规划:SQL 删除司乘=RidingPlan.delete_vehsichen");
            getSqlMapClientTemplate()
                    .delete("RidingPlan.delete_xc_vss", delmap);
            LOG.info("修改乘车规划:SQL 删除vss订购关系表=RidingPlan.delete_xc_vss");
            getSqlMapClientTemplate().delete("RidingPlan.delete_xc_vss_site",
                    delmap);
            LOG.info("修改乘车规划:SQL 删除vss_site订购关系表=RidingPlan.delete_xc_vss");*/
            /*  当前分配车辆相关数据删除 */
            /*if(!vehicle_vin_old.equals(map.get("vehicle_vin"))){
            	LOG.info("***********当前分配车辆相关数据删除*******start************");
                getSqlMapClientTemplate().delete("RidingPlan.deleteRoute", map);
                LOG.info("修改乘车规划:SQL 删除线路=RidingPlan.deleteRoute");
                getSqlMapClientTemplate().delete("RidingPlan.delete_vehdriver",
                        map);
                LOG.info("修改乘车规划:SQL 删除司机=RidingPlan.delete_vehdriver");
                getSqlMapClientTemplate().delete("RidingPlan.delete_vehsichen",
                        map);
                LOG.info("修改乘车规划:SQL 删除司乘=RidingPlan.delete_vehsichen");
                getSqlMapClientTemplate()
                        .delete("RidingPlan.delete_xc_vss", map);
                LOG.info("修改乘车规划:SQL 删除vss订购关系表=RidingPlan.delete_xc_vss");
                getSqlMapClientTemplate().delete("RidingPlan.delete_xc_vss_site",
                        map);  
                LOG.info("修改乘车规划:SQL 删除vss_site订购关系表=RidingPlan.delete_xc_vss");
                LOG.info("***********当前分配车辆相关数据删除*******end************");
            }*/   
            /*  end  add by suyingtao 2012-06-11*/
            
            /*getSqlMapClientTemplate().update("RidingPlan.updateRoute", map);
            LOG.info("修改乘车规划:SQL 更新线路=RidingPlan.updateRoute");*/
            
            //getSqlMapClientTemplate().delete("RidingPlan.deleteRoute", map);
            //LOG.info("修改乘车规划:SQL 删除线路=RidingPlan.deleteRoute");
            
            getSqlMapClientTemplate().delete("RidingPlan.delete_vehdriver",
                    map);
            LOG.info("修改乘车规划:SQL 删除司机=RidingPlan.delete_vehdriver");
            getSqlMapClientTemplate().delete("RidingPlan.delete_vehsichen",
                    map);
            LOG.info("修改乘车规划:SQL 删除司乘=RidingPlan.delete_vehsichen");
            getSqlMapClientTemplate()
                    .delete("RidingPlan.delete_xc_vss", map);
            LOG.info("修改乘车规划:SQL 删除vss订购关系表=RidingPlan.delete_xc_vss");
            getSqlMapClientTemplate().delete("RidingPlan.delete_xc_vss_site",
                    map);  
            LOG.info("修改乘车规划:SQL 删除vss_site订购关系表=RidingPlan.delete_xc_vss");
            
            getSqlMapClientTemplate().insert("RidingPlan.insert_xc_trip_t",map);
            LOG.info("修改乘车规划:SQL 插入xc_trip行程表=RidingPlan.insert_xc_trip_t");
            
            if(null!=driverList){
	            for (int i = 0; i < driverList.size(); i++) {
	            	VehdriverInfo vi=(VehdriverInfo) driverList.get(i);
	            	if(!vi.getDriver_id().equals("")){
	            		getSqlMapClientTemplate().insert("RidingPlan.insert_vehdriver",
		                        driverList.get(i));
		                LOG.info("修改乘车规划:SQL 添加司机=RidingPlan.insert_vehdriver");
		               
		                LOG.info("修改乘车规划:添加司机->参数 Driver_id="+vi.getDriver_id()+",Vehicle_vin="+vi.getVehicle_vin()+",trip_id="+vi.getTrip_id());

	            	}
	            }
            }
            if(null!=sichenList){
	            for (int i = 0; i < sichenList.size(); i++) {
	            	VehsichenInfo vi=(VehsichenInfo) sichenList.get(i);
	            	if(!vi.getSteward_id().equals("")){
	            		getSqlMapClientTemplate().insert("RidingPlan.insert_vehsichen",
		                        sichenList.get(i));
		                LOG.info("修改乘车规划:SQL 添加司乘=RidingPlan.insert_vehsichen");
		               
		                LOG.info("修改乘车规划:添加司乘->参数 Steward_id="+vi.getSteward_id()+",Vehicle_vin="+vi.getVehicle_vin()+",trip_id="+vi.getTrip_id());
	            	}
	            }
            }
            for (int i = 0; i < vssList.size(); i++) {
                getSqlMapClientTemplate().insert("RidingPlan.insert_xc_vss",
                        vssList.get(i));
                LOG.info("修改乘车规划:SQL 添加VSS订购关系=RidingPlan.insert_xc_vss");
                VssInfo vi=(VssInfo) vssList.get(i);
                LOG.info("修改乘车规划:添加VSS订购关系->参数 Route_id="+vi.getRoute_id()+",Site_id="+vi.getSite_id()+",Student_id="+vi.getStudent_id()+",Vehicle_vin="+vi.getVehicle_vin()+",Vss_state="+vi.getVss_state()+",trip_id="+vi.getTrip_id());
            }
            for (int i = 0; i < vss_siteList.size(); i++) {
                getSqlMapClientTemplate().insert(
                        "RidingPlan.insert_xc_vss_site", vss_siteList.get(i));
                LOG.info("修改乘车规划:SQL 添加vss_site订购关系=RidingPlan.insert_xc_vss_site");
                Vss_SiteInfo vi=(Vss_SiteInfo) vss_siteList.get(i);
                LOG.info("修改乘车规划:添加vss_site订购关系->参数 Route_id="+vi.getRoute_id()+",Site_id="+vi.getSite_id()+",Vehicle_vin="+vi.getVehicle_vin()+",Plan_in_time="+vi.getPlan_in_time()+",Plan_out_time="+vi.getPlan_out_time()+",trip_id="+vi.getTrip_id());
            }
            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

}
