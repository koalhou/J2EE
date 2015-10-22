package com.yutong.clw.dao.analysis;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.yutong.clw.beans.app.RequestBean;
import com.yutong.clw.beans.cl.TerminalRecord;

public class TerminalInfoDAO extends AbstractDaoManager {

    private static final String COUNT_OF_SUM = "select count(*) from clw_jc_terminal_t a LEFT OUTER JOIN clw_cl_tmprotocol_t  b on a.terminal_protocol_id=b.terminal_protocol_id       " +
    		"LEFT OUTER JOIN   clw_cl_tmoem_t c on a.terminal_oem_id=c.terminal_oem_id   LEFT OUTER JOIN   clw_cl_tmtype_t d on a.terminal_type_id=d.terminal_type_id  LEFT OUTER JOIN clw_jc_enterprise_t e  on a.enterprise_id = e.enterprise_id  " +
    		"LEFT OUTER JOIN  CLW_CL_SIM_T f on a.sim_card_number=f.sim_card_number and f.valid_flag='0' " +
    		"where a.modify_time > to_date(?, 'yyyy-mm-dd hh24:mi:ss') " +
    		"AND a.modify_time < = to_date(?, 'yyyy-mm-dd hh24:mi:ss') order by modify_time asc";

    private static final String GET_DAILY_INFO = "select a.id,a.terminal_id,a.enterprise_id,e.full_name as enterprise_name,nvl(a.vehicle_vin,'') vehicle_vin ,a.terminal_oem_id,c.full_name as terminal_oem_name,d.terminal_type_name,b.terminal_protocol_name,a.sim_card_number,a.communicate_id,a.video_id,a.valid_flag,a.vaset_time,a.modify_time ,f.cellphone " +  
    		"from clw_jc_terminal_t  a  LEFT OUTER JOIN clw_cl_tmprotocol_t  b on a.terminal_protocol_id=b.terminal_protocol_id       " +
    		"LEFT OUTER JOIN   clw_cl_tmoem_t c on a.terminal_oem_id=c.terminal_oem_id   LEFT OUTER JOIN   clw_cl_tmtype_t d on a.terminal_type_id=d.terminal_type_id  LEFT OUTER JOIN clw_jc_enterprise_t e  on a.enterprise_id = e.enterprise_id  " +
    		"LEFT OUTER JOIN  CLW_CL_SIM_T f on a.sim_card_number=f.sim_card_number and f.valid_flag='0'"+
    		" where a.modify_time > to_date(?, 'yyyy-mm-dd hh24:mi:ss') AND a.modify_time <= to_date(?, 'yyyy-mm-dd hh24:mi:ss') order by modify_time asc";

    /**
     * 获取终端信息列表
     * @param reqBean
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<TerminalRecord> getPagedRecord(RequestBean reqBean) {
        String selPagedSql = getPagedSelSql(GET_DAILY_INFO 
        		, Integer.parseInt(reqBean.getPageNo()),
                Integer.parseInt(reqBean.getPageSize()));
//       System.out.println(selPagedSql); 
        List<TerminalRecord> terminalRecord = jdbcTemplate.query(selPagedSql, new Object[] {
                reqBean.getBeginTime(), reqBean.getEndTime() },ParameterizedBeanPropertyRowMapper.newInstance(TerminalRecord.class));
        return terminalRecord;
    }
    
    @SuppressWarnings("unchecked")
	public List<TerminalRecord> getAllRecord(RequestBean reqBean) {
    	String sql = "select a.id,a.terminal_id,a.enterprise_id,e.full_name as enterprise_name,nvl(a.vehicle_vin,'') vehicle_vin ,a.terminal_oem_id,c.full_name as terminal_oem_name,d.terminal_type_name,b.terminal_protocol_name,a.sim_card_number,a.communicate_id,a.video_id,a.valid_flag,a.vaset_time,a.modify_time ,f.cellphone from clw_jc_terminal_t  a  LEFT OUTER JOIN clw_cl_tmprotocol_t  b on a.terminal_protocol_id=b.terminal_protocol_id LEFT OUTER JOIN clw_cl_tmoem_t c on a.terminal_oem_id=c.terminal_oem_id  LEFT OUTER JOIN clw_cl_tmtype_t d on a.terminal_type_id=d.terminal_type_id  LEFT OUTER JOIN clw_jc_enterprise_t e  on a.enterprise_id = e.enterprise_id  LEFT OUTER JOIN CLW_CL_SIM_T f on a.sim_card_number=f.sim_card_number and f.valid_flag = '0'";
        String selPagedSql = getPagedSelSql(sql 
        		, Integer.parseInt(reqBean.getPageNo()),
                Integer.parseInt(reqBean.getPageSize()));
//        System.out.println(selPagedSql); 
        return jdbcTemplate.query(selPagedSql, ParameterizedBeanPropertyRowMapper.newInstance(TerminalRecord.class));
    }

    /**
     * 查询终端的总记录总数
     * @param reqBean
     * @return
     */
    public int getCountOfSum(RequestBean reqBean) {
        int count = jdbcTemplate.queryForInt(COUNT_OF_SUM, new Object[] {
                 reqBean.getBeginTime(), reqBean.getEndTime() });
//        int count = jdbcTemplate.queryForInt(COUNT_OF_VEHIC_SUM, new Object[] {
//               "", "" });
        return count;
    }
    
    public int getCountAllOfSum(){
    	String sql = "select count(*) from clw_jc_terminal_t  a  LEFT OUTER JOIN clw_cl_tmprotocol_t  b " +
    			"on a.terminal_protocol_id=b.terminal_protocol_id LEFT OUTER JOIN clw_cl_tmoem_t c " +
    			"on a.terminal_oem_id=c.terminal_oem_id  LEFT OUTER JOIN clw_cl_tmtype_t d " +
    			"on a.terminal_type_id=d.terminal_type_id  LEFT OUTER JOIN clw_jc_enterprise_t e  " +
    			"on a.enterprise_id = e.enterprise_id  "
    			+"LEFT OUTER JOIN CLW_CL_SIM_T f on a.sim_card_number=f.sim_card_number and f.valid_flag = '0'"
    			;
    	return jdbcTemplate.queryForInt(sql); 
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

}
