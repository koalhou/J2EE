package com.neusoft.clw.info.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.info.bean.RequestBean;
import com.neusoft.clw.info.bean.VehicRecord;

public class VehicInfoDAO extends AbstractDaoManager {

    private static final String COUNT_OF_VEHIC_SUM = "select count(*) from clw_cl_base_info_t a  LEFT OUTER JOIN  clw_jc_enterprise_t b on a.enterprise_id=b.enterprise_id where  "
            + " a.valid_flag = '0' and  a.modify_time > to_date(?, 'yyyy-mm-dd hh24:mi:ss') AND a.modify_time <= to_date(?, 'yyyy-mm-dd hh24:mi:ss')";

    private static final String GET_DAILY_VEHIC_INFO = "select a.vehicle_vin,a.vehicle_id,a.vehicle_code,a.vehicle_ln,a.enterprise_id, " +
    		" b.short_name,a.engine_number,a.engine_type,a.brand,a.vehicle_type_id, " +
    		" a.vehicle_color,a.limite_number,a.standard_oil,a.dead_load,a.sale_price, " +
    		" a.out_number,a.business_type,a.tyre_r,a.rear_axle_rate,a.create_time, " +
    		" a.modifier,a.modify_time,a.valid_flag,a.vaset_time,a.VEH_PAI_COLOR,a.VEH_SHENGID,a.VEH_SHIID " +
    		" from clw_cl_base_info_t a  LEFT OUTER JOIN  clw_jc_enterprise_t b on a.enterprise_id=b.enterprise_id  where " +
    		"  a.valid_flag = '0' and  a.modify_time > to_date(?, 'yyyy-mm-dd hh24:mi:ss') and a.modify_time <= to_date(?, 'yyyy-mm-dd hh24:mi:ss')";

    
    
    /**
     * 获取辆车信息列表
     * @param reqBean
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<VehicRecord> getPagedVehicRecord(RequestBean reqBean) {
        String selPagedSql = getPagedSelSql(GET_DAILY_VEHIC_INFO 
        		, Integer.parseInt(reqBean.getPageNo()),
                Integer.parseInt(reqBean.getPageSize()));
//       System.out.println(selPagedSql); 
        List<VehicRecord> vehicRecord = jdbcTemplate.query(selPagedSql, new Object[] {
                reqBean.getBeginTime(), reqBean.getEndTime() },ParameterizedBeanPropertyRowMapper.newInstance(VehicRecord.class));
        return vehicRecord;
    }
    
	@SuppressWarnings("unchecked")
	public List<VehicRecord> getAllVehicRecords(RequestBean reqBean){
    	String sql = "select a.vehicle_vin,a.vehicle_id,a.vehicle_code,a.vehicle_ln,a.enterprise_id, " +
    		" b.short_name,a.engine_number,a.engine_type,a.brand,a.vehicle_type_id, " +
    		" a.vehicle_color,a.limite_number,a.standard_oil,a.dead_load,a.sale_price, " +
    		" a.out_number,a.business_type,a.tyre_r,a.rear_axle_rate,a.create_time, " +
    		" a.modifier,a.modify_time,a.valid_flag,a.vaset_time,a.VEH_PAI_COLOR,a.VEH_SHENGID,a.VEH_SHIID " +
    		" from clw_cl_base_info_t a  LEFT OUTER JOIN  clw_jc_enterprise_t b on a.enterprise_id=b.enterprise_id where a.valid_flag = '0' ";
    	String pagesql = getPagedSelSql(sql, Integer.parseInt(reqBean.getPageNo()), Integer.parseInt(reqBean.getPageSize()));
    	return jdbcTemplate.query(pagesql, ParameterizedBeanPropertyRowMapper.newInstance(VehicRecord.class));
    }

    /**
     * 查询车辆的总记录总数
     * @param reqBean
     * @return
     */
    public int getCountOfVehicDailyOilSum(RequestBean reqBean) {
        int count = jdbcTemplate.queryForInt(COUNT_OF_VEHIC_SUM, new Object[] {
                 reqBean.getBeginTime(), reqBean.getEndTime() });
//        int count = jdbcTemplate.queryForInt(COUNT_OF_VEHIC_SUM, new Object[] {
//               "", "" });
        return count;
    }

     
    
    /**
     * 将多个ID组合成In子句
     * @param businessIdList
     * @return
     */
    @SuppressWarnings("unused")
	private String getInStrOfSelect(List<String> idList) {
        StringBuffer inStr = new StringBuffer();
        inStr.append(" in (");
        for (String id : idList) {
            inStr.append("'" + id + "',");
        }
        inStr.delete(inStr.length() - 1, inStr.length());
        inStr.append(") ");
        return inStr.toString();
    }

   

    /**
     * 获取oracle分页查询语句
     * @param selPagedCorpsOilSumSql 查询主语句(不带分页参数)
     * @param pageNo 第几页
     * @param pageSize 每页大小
     * @return
     */
    private String getPagedSelSql(String mainSelSql, int pageNo, int pageSize) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from (select X.*,rownum rn from(");
        sql.append(mainSelSql);
        sql.append(") X where rownum <= ");
        sql.append(pageSize * pageNo);
        sql.append(") where rn >= ");
        sql.append(pageSize * (pageNo - 1) + 1);
        return sql.toString();
    }

	public int getCountAllOfVehicSum() {
		String sql = "select count(*) from clw_cl_base_info_t a  LEFT OUTER JOIN  clw_jc_enterprise_t b on a.enterprise_id=b.enterprise_id where a.valid_flag = '0' ";
		return jdbcTemplate.queryForInt(sql);
	}

}
