package com.yutong.clw.dao.analysis;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.yutong.clw.beans.app.CommandBean;
import com.yutong.clw.beans.app.RequestBean;
import com.yutong.clw.beans.app.SendExceptionSettingReq;
import com.yutong.clw.beans.app.SendHolesReq;
import com.yutong.clw.beans.app.SendLineReq;
import com.yutong.clw.beans.app.SmsData;
import com.yutong.clw.beans.exceptions.DBErrorException;
import com.yutong.clw.config.Constant;
import com.yutong.clw.nio.msg.util.IdCreater;
import com.yutong.clw.nio.msg.util.InsideMsgUtils;
import com.yutong.clw.nio.msg.value.Up_InfoContent;

public class SendCommandDAO extends AbstractDaoManager {

       private static final String SQL_INSERT="insert into clw_yw_send_command_t" +
    		"(id,sim_card_number,vehicle_vin,msg_id,send_command,send_type,packet_content,deal_state,send_seq," +
    		"remark,operate_user_id,core_id,deal_time,operate_time,SEND_TIMES,REGGRPID,CHANLE_CODE,BATCH_ID)" +
    		" values( " ;
    		//"?,?,?,?,?,?,?,?,?,?,?,?,sysdate(),sysdate())";
       //查询下发命令，两个时间参数第一个是终端在线的判断、第二个是再次发送的时间间隔。
//       private static final String GET_COMMAND_INFO = " select  a.deal_time,a.id,a.sim_card_number,a.vehicle_vin,a.msg_id,a.send_command,a.send_type,a.packet_content,a.deal_state,a.send_seq,a.remark,a.core_id,a.operate_user_id,a.operate_time,a.send_times" +
//		"  from clw_yw_send_command_t a ,clw_jc_terminal_t b " +
//		"where  b.valid_flag=0 and  (sysdate-b.update_time)*24*60*60<? and(a.deal_state ='0' or (a.deal_state in('1','2') and (sysdate-a.deal_time)*24*60*60>? ) )and a.send_times<=3 and a.vehicle_vin=b.vehicle_vin " ;
       //查询下发命令，参数是再次发送的时间间隔。
       private static final String GET_COMMAND_INFO = " select  a.deal_time,a.id,a.sim_card_number,a.vehicle_vin,a.msg_id,a.send_command,a.send_type,a.packet_content,a.deal_state,a.send_seq,a.remark,a.core_id,a.operate_user_id,a.operate_time,a.send_times" +
		"  from clw_yw_send_command_t a  " +"where    (a.deal_state ='0' or (a.deal_state in('1','2') and (sysdate-a.deal_time)*24*60*60>? ) )and a.send_times<=3 and a.send_type != '2105'" ;
		//"where a.biz_id=b.code_id and b.code_type='BIZTYPE' and  a.modify_time BETWEEN to_date(?, 'yyyy-mm-dd hh24:mi:ss') AND to_date(?, 'yyyy-mm-dd hh24:mi:ss')";
      // select  a.deal_time,a.id,a.sim_card_number,a.vehicle_vin,a.msg_id,a.send_command,a.send_type,a.packet_content,a.deal_state,a.send_seq,a.remark,a.core_id,a.operate_user_id,a.operate_time,a.send_times
       //where  b.valid_flag=0 and  (sysdate-b.update_time)*24*60*60<60*5 and(a.deal_state ='0' or (a.deal_state in('1','2') and (sysdate-a.deal_time)*24*60*60<60*10 ) )and a.send_times<=3 and a.vehicle_vin=b.vehicle_vin  

       private static final String GET_COMMAND_UPDATE1 = "update clw_yw_send_command_t t set t.send_times=t.send_times+1 ,t.deal_state='1',t.deal_time=sysdate where t.id=?"; 
       
       private static final String GET_COMMAND_UPDATE2 = "update clw_yw_send_command_t t set t.deal_state='2',t.deal_time=sysdate where t.id=?"; 
       /**
        * 获取信息列表
        * @param reqBean
        * @return
        */
       @SuppressWarnings("unchecked")
       public List<CommandBean> getRecord() {
 
         // System.out.println(selPagedSql); 
           List<CommandBean> commandBean = jdbcTemplate.query(GET_COMMAND_INFO, new Object[] {
                   60*6 },ParameterizedBeanPropertyRowMapper.newInstance(CommandBean.class));
           return commandBean;
       }
       
       /**
        * 更新发送次数状态
        * @param reqBean
     * @return 
        * @return
        */
       public int UpdateTimes(String id) {
 
         // System.out.println(selPagedSql); 
          int urow = jdbcTemplate.update (GET_COMMAND_UPDATE1, new Object[] {
                 id });
           return urow;
       }
       
       public int UpdateOneTimes(String id){
    	   String GET_COMMAND_UPDATEONE = "update clw_yw_send_command_t t set t.send_times=t.send_times+1 ,t.deal_state='1',t.deal_time=sysdate where t.msg_id=?"; 
    	   return jdbcTemplate.update (GET_COMMAND_UPDATEONE, new Object[] {id });
       }
       
       public int UpdateTwoTimes(String id){
    	   String GET_COMMAND_UPDATETWO = "update clw_yw_send_command_t t set t.deal_state='2',t.deal_time=sysdate where t.msg_id=?"; 
    	   return jdbcTemplate.update (GET_COMMAND_UPDATETWO, new Object[] {id });
       }
       
       public int Update2Times(String id){
    	   int urow = jdbcTemplate.update (GET_COMMAND_UPDATE2, new Object[] {id });
             return urow;
       }
       
    @SuppressWarnings("unchecked")
	public List<SmsData> getSmsData(String tripId){
    	String sql = "select t.send_time as sendTime ,r.route_class as upDown from TQC_TRIP_EXECUTE t,CLW_XC_ROUTE_T r where t.route_id = r.route_id and t.trip_id = '" + tripId + "'";
    	return jdbcTemplate.query(sql, new Object[]{}, ParameterizedBeanPropertyRowMapper.newInstance(SmsData.class));
    }
       
    /**
     * 指令入库
     * @param reqBean
     * @return
     */
    public int saveCommand(RequestBean reqBean) throws DBErrorException {
    	String uuid=UUID.randomUUID().toString().replaceAll("-", "");
    	reqBean.setSeq(InsideMsgUtils.getSeq());
    	
    	StringBuffer strSql = new StringBuffer();
    	strSql.append(SQL_INSERT);
    	strSql.append("'"+uuid+"',");
    	strSql.append("'"+reqBean.getSim_card_number()+"',");
    	strSql.append("'"+reqBean.getTerminal_id()+"',");
    	strSql.append("'"+reqBean.getMsg_id()+"',");
    	strSql.append("'"+reqBean.getSend_command()+"',");
    	strSql.append("'"+reqBean.getSend_type()+"',");
    	if(reqBean.getPacket_content().contains("'")){
    		strSql.append("'"+reqBean.getPacket_content().replace("'", "''")+"',");
    	}else{
    		strSql.append("'"+reqBean.getPacket_content()+"',");
    	}
    	strSql.append("'0',");
    	strSql.append("'"+reqBean.getSeq()+"',");
    	if(reqBean.getRemark()!=null&&!reqBean.getRemark().equals("")){
    		if(reqBean.getRemark().contains("'")){
    			strSql.append("'"+reqBean.getRemark().replace("'", "''")+"',");
    		}else{
    			strSql.append("'"+reqBean.getRemark()+"',");
    		}
    	}else{
    		strSql.append("'',");
    	}
    	strSql.append("'"+reqBean.getOperate_user_id()+"',");
    	strSql.append("'"+Constant.CORE_ID+"',");
    	strSql.append("sysdate,sysdate,0");
    	if(reqBean.getReggrpid()!=null&&!reqBean.getReggrpid().equals("")){
    		strSql.append(",'"+reqBean.getReggrpid()+"'");
    	}else{
    		strSql.append(",''");
    	}
    	if(reqBean.getChanel_code()!=null&&!reqBean.getChanel_code().equals("")){
    		strSql.append(",'"+reqBean.getChanel_code()+"'");
    	}else{
    		strSql.append(",''");
    	}
    	if(reqBean.getBatch_id()!=null&&!reqBean.getBatch_id().equals("")){
    		strSql.append(",'"+reqBean.getBatch_id()+"'");
    	}else{
    		strSql.append(",''");
    	}
    	strSql.append(")");
//    	System.out.println(strSql.toString());
    	try {
    		jdbcTemplate.execute(strSql.toString());
		} catch (Exception e) {
			throw new DBErrorException("服务器入库失败"+e.getMessage());
		}
    	
    	
    	
//      int count =jdbcTemplate.update(SQL_INSERT,  new Object[] { uuid,reqBean.getSim_card_number(),reqBean.getTerminal_id(),reqBean.getMsg_id(),
//      reqBean.getSend_command(),reqBean.getSend_type(),reqBean.getPacket_content(),reqBean.getSeq(),reqBean.getRemark(),reqBean.getOperate_user_id(),Constant.CORE_ID});

        return 0;
    }
 
    private static final String HOLESSQL_INSERT="insert into clw_yw_send_command_t" +
	"(id,sim_card_number,vehicle_vin,msg_id,send_command,send_type,packet_content,deal_state,send_seq," +
	"remark,operate_user_id,core_id,deal_time,operate_time,SEND_TIMES,REGGRPID,CHANLE_CODE,batch_id)" +
	" values( " ;
    
    /**
     * 指令入库
     * @param reqBean
     * @return
     */
    public int saveHolesCommand(RequestBean reqBean,SendHolesReq req) throws DBErrorException {
    	String uuid=UUID.randomUUID().toString().replaceAll("-", "");
    	reqBean.setSeq(InsideMsgUtils.getSeq());
    	
    	StringBuffer strSql = new StringBuffer();
    	strSql.append(HOLESSQL_INSERT);
    	strSql.append("'"+uuid+"',");
    	strSql.append("'"+reqBean.getSim_card_number()+"',");
    	strSql.append("'"+reqBean.getTerminal_id()+"',");
    	strSql.append("'"+reqBean.getMsg_id()+"',");
    	strSql.append("'"+reqBean.getSend_command()+"',");
    	strSql.append("'"+reqBean.getSend_type()+"',");
    	strSql.append("'"+req.getPacket_content()+"',");
    	strSql.append("'0',");
    	strSql.append("'"+reqBean.getSeq()+"',");
    	if(reqBean.getRemark()!=null&&!reqBean.getRemark().equals("")){
    		if(reqBean.getRemark().contains("'")){
    			strSql.append("'"+reqBean.getRemark().replace("'", "''")+"',");
    		}else{
    			strSql.append("'"+reqBean.getRemark()+"',");
    		}
    	}else{
    		strSql.append("'',");
    	}
    	strSql.append("'"+reqBean.getOperate_user_id()+"',");
    	strSql.append("'"+Constant.CORE_ID+"',");
    	strSql.append("sysdate,sysdate,0");
    	if(reqBean.getReggrpid()!=null&&!reqBean.getReggrpid().equals("")){
    		strSql.append(",'"+reqBean.getReggrpid()+"'");
    	}else{
    		strSql.append(",''");
    	}
    	if(reqBean.getChanel_code()!=null&&!reqBean.getChanel_code().equals("")){
    		strSql.append(",'"+reqBean.getChanel_code()+"'");
    	}else{
    		strSql.append(",''");
    	}
    	if(reqBean.getBatch_id()!=null&&!reqBean.getBatch_id().equals("")){
    		strSql.append(",'"+reqBean.getBatch_id()+"'");
    	}else{
    		strSql.append(",''");
    	}
    	strSql.append(")");
//    	System.out.println(strSql.toString());
    	try {
    		jdbcTemplate.execute(strSql.toString());
		} catch (Exception e) {
			throw new DBErrorException("服务器入库失败"+e.getMessage());
		}
    	
    	
    	
//      int count =jdbcTemplate.update(SQL_INSERT,  new Object[] { uuid,reqBean.getSim_card_number(),reqBean.getTerminal_id(),reqBean.getMsg_id(),
//      reqBean.getSend_command(),reqBean.getSend_type(),reqBean.getPacket_content(),reqBean.getSeq(),reqBean.getRemark(),reqBean.getOperate_user_id(),Constant.CORE_ID});

        return 0;
    }
	public int saveLineCommand(RequestBean reqBean, SendLineReq req) throws DBErrorException {
		reqBean.setSeq(InsideMsgUtils.getSeq());
		String sql = "insert into clw_yw_send_command_t" +
		"(id,sim_card_number,vehicle_vin,msg_id,send_command,send_type,packet_content,deal_state,send_seq," +
		"remark,operate_user_id,core_id,deal_time,operate_time,SEND_TIMES,REGGRPID,CHANLE_CODE,batch_id)" +
		" values( ";
		
		StringBuffer strSql = new StringBuffer();
		strSql.append(sql);
		strSql.append("'"+IdCreater.getUUid()+"',");
		strSql.append("'"+reqBean.getSim_card_number()+"',");
    	strSql.append("'"+reqBean.getTerminal_id()+"',");
    	strSql.append("'"+reqBean.getMsg_id()+"',");
    	strSql.append("'"+reqBean.getSend_command()+"',");
    	strSql.append("'"+reqBean.getSend_type()+"',");
    	strSql.append("'"+req.getPacket_content()+"',");
    	strSql.append("'0',");
    	strSql.append("'"+reqBean.getSeq()+"',");
    	if(reqBean.getRemark()!=null&&!reqBean.getRemark().equals("")){
    		if(reqBean.getRemark().contains("'")){
    			strSql.append("'"+reqBean.getRemark().replace("'", "''")+"',");
    		}else{
    			strSql.append("'"+reqBean.getRemark()+"',");
    		}
    	}else{
    		strSql.append("'',");
    	}
    	strSql.append("'"+reqBean.getOperate_user_id()+"',");
    	strSql.append("'"+Constant.CORE_ID+"',");
    	strSql.append("sysdate,sysdate,0");
    	if(reqBean.getReggrpid()!=null&&!reqBean.getReggrpid().equals("")){
    		strSql.append(",'"+reqBean.getReggrpid()+"'");
    	}else{
    		strSql.append(",''");
    	}
    	if(reqBean.getChanel_code()!=null&&!reqBean.getChanel_code().equals("")){
    		strSql.append(",'"+reqBean.getChanel_code()+"'");
    	}else{
    		strSql.append(",''");
    	}
    	if(reqBean.getBatch_id()!=null&&!reqBean.getBatch_id().equals("")){
    		strSql.append(",'"+reqBean.getBatch_id()+"'");
    	}else{
    		strSql.append(",''");
    	}
    	strSql.append(")");
    	try {
    		jdbcTemplate.execute(strSql.toString());
		} catch (Exception e) {
			throw new DBErrorException("服务器入库失败"+e.getMessage());
		}
		return 0;
		
	}
	
	public int saveQuartzLineCommand(Up_InfoContent req,String sim,String id) throws DBErrorException {
		String rreq = InsideMsgUtils.getSeq();
		String sql = "insert into clw_yw_send_command_t" +
		"(id,sim_card_number,vehicle_vin,msg_id,send_command,send_type,packet_content,deal_state,send_seq," +
		"remark,operate_user_id,core_id,deal_time,operate_time,SEND_TIMES,TERMINAL_REQUEST)" +
		" values( ";
		
		StringBuffer strSql = new StringBuffer();
		strSql.append(sql);
		strSql.append("'"+IdCreater.getUUid()+"'");
		strSql.append(",'"+sim+"',");
    	strSql.append("'"+req.getTerminalId()+"',");
    	strSql.append("'"+id+"',");
    	strSql.append("'"+req.getCommand()+"',");
    	strSql.append("'0c',");
    	strSql.append("'',");
    	strSql.append("'4',");
    	strSql.append("'"+rreq+"',");
    	strSql.append("'路线更新查询消息',");
    	strSql.append("'"+Constant.CORE_ID+"',");
    	strSql.append("'"+Constant.CORE_ID+"',");
    	strSql.append("sysdate,sysdate,0,0");
    	strSql.append(")");
    	try {
    		jdbcTemplate.execute(strSql.toString());
		} catch (Exception e) {
			throw new DBErrorException("服务器入库失败"+e.getMessage());
		}
		return 0;
		
	}
	public int updateQuartzLine(String id,SendLineReq req) throws DBErrorException {
		StringBuffer sb = new StringBuffer();
		sb.append("update clw_yw_send_command_t set ");
		sb.append("packet_content='"+req.getPacket_content()+"'");
		sb.append(",deal_state='1'");
		sb.append(",deal_time=sysdate");
		sb.append(",send_times='1'");
		sb.append(",operate_user_id='"+Constant.CORE_ID+"'");
		sb.append(",core_id='"+Constant.CORE_ID+"'");
		sb.append(" where msg_id = '"+id+"'");
		try {
    		jdbcTemplate.execute(sb.toString());
		} catch (Exception e) {
			throw new DBErrorException("服务器入库失败"+e.getMessage());
		}
		return 0;
	}
	public void saveExceptionSettingCommand(RequestBean reqBean,
			SendExceptionSettingReq req) throws DBErrorException {
		String rreq = InsideMsgUtils.getSeq();
		String sql = "insert into clw_yw_send_command_t" +
		"(id,sim_card_number,vehicle_vin,msg_id,send_command,send_type,packet_content,deal_state,send_seq," +
		"remark,operate_user_id,core_id,deal_time,operate_time,SEND_TIMES,REGGRPID,CHANLE_CODE,batch_id,TERMINAL_EXCEPION_EVENT_SWITCH)" +
		" values( ";
		
		StringBuffer strSql = new StringBuffer();
		strSql.append(sql);
		strSql.append("'"+IdCreater.getUUid()+"'");
		strSql.append(",'"+reqBean.getSim_card_number()+"',");
    	strSql.append("'"+reqBean.getTerminal_id()+"',");
    	strSql.append("'"+reqBean.getMsg_id()+"',");
    	strSql.append("'"+req.getCommand()+"',");
    	strSql.append("'02',");
    	strSql.append("'',");
    	strSql.append("'0',");
    	strSql.append("'"+rreq+"',");
    	strSql.append("'终端异常参数设置',");
    	strSql.append("'"+Constant.CORE_ID+"',");
    	strSql.append("'"+Constant.CORE_ID+"',");
    	strSql.append("sysdate,sysdate,0,0");
    	strSql.append(")");
    	try {
    		jdbcTemplate.execute(strSql.toString());
		} catch (Exception e) {
			throw new DBErrorException("服务器入库失败"+e.getMessage());
		}
	}
}
