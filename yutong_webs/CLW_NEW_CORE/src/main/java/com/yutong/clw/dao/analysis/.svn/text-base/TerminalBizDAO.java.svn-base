package com.yutong.clw.dao.analysis;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.yutong.clw.beans.app.RequestBean;
import com.yutong.clw.beans.cl.TerminalBizRecord;

public class TerminalBizDAO extends AbstractDaoManager {

    private static final String COUNT_OF_SUM = "select count(*) from CLW_YW_TERMINAL_BIZ_T a,CLW_JC_CODEDEF_T b where a.biz_id=b.code_id and b.code_type='BIZTYPE' and  "
            + "   a.modify_time BETWEEN to_date(?, 'yyyy-mm-dd hh24:mi:ss') AND to_date(?, 'yyyy-mm-dd hh24:mi:ss')";

    
    private static final String GET_DAILY_INFO = "select a.TERMINAL_ID,a.BIZ_ID,b.code_name as BIZ_NAME,a.MODIFY_TIME" +
    		" from CLW_YW_TERMINAL_BIZ_T a,CLW_JC_CODEDEF_T b " +
    		"where a.biz_id=b.code_id and b.code_type='BIZTYPE' and  a.modify_time BETWEEN to_date(?, 'yyyy-mm-dd hh24:mi:ss') AND to_date(?, 'yyyy-mm-dd hh24:mi:ss')";

    
    /**
     * 获取信息列表
     * @param reqBean
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<TerminalBizRecord> getPagedRecord(RequestBean reqBean) {
        String selPagedSql = getPagedSelSql(GET_DAILY_INFO 
        		, Integer.parseInt(reqBean.getPageNo()),
                Integer.parseInt(reqBean.getPageSize()));
//       System.out.println(selPagedSql); 
        List<TerminalBizRecord> terminalBizRecord = jdbcTemplate.query(selPagedSql, new Object[] {
                reqBean.getBeginTime(), reqBean.getEndTime() },ParameterizedBeanPropertyRowMapper.newInstance(TerminalBizRecord.class));
        return terminalBizRecord;
    }
     

    /**
     * 查询总记录数
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
