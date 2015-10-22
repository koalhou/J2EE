package com.neusoft.clw.infomanage.ridingplan.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.SqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.infomanage.ridingplan.domain.RidingReady;
import com.neusoft.clw.infomanage.ridingplan.domain.VssInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.Vss_SiteInfo;

public class RidingPlanDao extends SqlMapDao{
   
	public void batchAddRidingPlan(List<VssInfo> vssList)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        try {
            sqlmapclient.startBatch();// 开启批处理
            getSqlMapClientTemplate().insert("RidingPlan.insert_tqc_trip_execute",vssList.get(0));
            LOG.info("新增乘车规划:SQL 插入xc_trip行程表=RidingPlan.insert_xc_trip_execute");
            for (int i = 0; i < vssList.size(); i++) {
                getSqlMapClientTemplate().insert("RidingPlan.insert_xc_vss",vssList.get(i));
                LOG.info("新增乘车规划:SQL 添加VSS订购关系=RidingPlan.insert_xc_vss");
                VssInfo vi=(VssInfo) vssList.get(i);
                LOG.info("新增乘车规划:添加VSS订购关系->参数 Student_id="+vi.getStudent_id()+",Vehicle_vin="+vi.getVehicle_vin());
            }
            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
	public void batchAddRidingPlan2(Map map, List vssList, List vss_siteList)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        try {
            // 开启批处理
            sqlmapclient.startBatch();
            
            /*for (int i = 0; i < vssList.size(); i++) {
                getSqlMapClientTemplate().insert("RidingPlan.insert_xc_vss",vssList.get(i));
                LOG.info("新增乘车规划:SQL 添加VSS订购关系=RidingPlan.insert_xc_vss");
                VssInfo vi=(VssInfo) vssList.get(i);
                LOG.info("新增乘车规划:添加VSS订购关系->参数 Route_id="+vi.getRoute_id()+",Site_id="+vi.getSite_id()+",Student_id="+vi.getStudent_id()+",Vehicle_vin="+vi.getVehicle_vin()+",Vss_state="+vi.getVss_state()+",trip_id="+vi.getTrip_id());
            }*/
            for (int i = 0; i < vss_siteList.size(); i++) {
                getSqlMapClientTemplate().insert("RidingPlan.insert_xc_vss_site", vss_siteList.get(i));
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

    public void batchDeletedRidingPlan(Map<String,Object> map)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        try {
            // 开启批处理
            sqlmapclient.startBatch();
            RidingReady info = new RidingReady();
            info.setTrip_id(map.get("trip_id").toString());
            getSqlMapClientTemplate().delete("RidingPlan.delete_xc_vss", info);
            LOG.info("删除乘车规划:SQL 删除vss订购关系表=RidingPlan.delete_xc_vss");
//            getSqlMapClientTemplate().delete("RidingPlan.delete_xc_trip", map);//delete_xc_trip
//            LOG.info("删除乘车规划:SQL 删除xc_trip行程表=RidingPlan.delete_xc_trip");
            getSqlMapClientTemplate().delete("RidingPlan.delete_tqc_trip_execute", map);//delete_xc_trip
            LOG.info("删除乘车规划:SQL 删除xc_trip行程表exe=RidingPlan.delete_tqc_trip_execute");
            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public void batchUpdateRidingPlan(RidingReady ridingReady,List vssList, String vehicle_vin_old)throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        try {
            // 开启批处理
            sqlmapclient.startBatch();
            // 删除原有车与人关系  添加新选择人员
        	getSqlMapClientTemplate().delete("RidingPlan.delete_xc_vss", ridingReady);
        	LOG.info("修改乘车规划:SQL 删除vss订购关系表=RidingPlan.delete_xc_vss");
        	for (int i = 0; i < vssList.size(); i++) {
                 getSqlMapClientTemplate().insert("RidingPlan.insert_xc_vss",vssList.get(i));
                 LOG.info("修改乘车规划:SQL 添加VSS订购关系=RidingPlan.insert_xc_vss");
                 VssInfo vi=(VssInfo) vssList.get(i);
                 LOG.info("修改乘车规划:添加VSS订购关系->参数 Student_id="+vi.getStudent_id()+",Vehicle_vin="+vi.getVehicle_vin());
            }
        	getSqlMapClientTemplate().update("RidingPlan.update_xc_trip_exe_time", ridingReady);
            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

}
