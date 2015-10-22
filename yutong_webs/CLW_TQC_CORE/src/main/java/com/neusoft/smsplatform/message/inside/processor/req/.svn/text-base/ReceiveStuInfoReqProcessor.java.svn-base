package com.neusoft.smsplatform.message.inside.processor.req;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.smsplatform.message.bean.StuIdBean;
import com.neusoft.smsplatform.message.dao.UpdateDAO;
import com.neusoft.smsplatform.message.inside.msg.req.ReceiveStuInfoReq;
import com.neusoft.smsplatform.message.inside.msg.resp.ReceiveStuInfoResp;
import com.neusoft.smsplatform.message.inside.processor.MessageAbstractInsideProcessor;
import com.neusoft.smsplatform.message.nio.SmsCommunicateService;
import com.neusoft.smsplatform.message.util.SendMethod;


/**
 * 接收学生信息处理processor
 * @author user
 *
 */
public final class ReceiveStuInfoReqProcessor extends MessageAbstractInsideProcessor<ReceiveStuInfoReq, SmsCommunicateService> {

    private static final ReceiveStuInfoReqProcessor PROCESSOR = new ReceiveStuInfoReqProcessor();

    private static UpdateDAO dao = (UpdateDAO) SpringBootStrap.getInstance().getBean("updatedao");
    private Logger log = LoggerFactory.getLogger(ReceiveStuInfoReqProcessor.class);
    private ReceiveStuInfoReqProcessor() {
    }

    public static ReceiveStuInfoReqProcessor getInstance() {
        return PROCESSOR;
    }

	public ReceiveStuInfoReq parse(byte[] buf) throws ParseException {
        try {
        	log.info("ReceiveStuInfoReq:接收短信平台学生同步处理开始");
        	ReceiveStuInfoReq req = new ReceiveStuInfoReq();
            int location = super.parseHeader(buf, req);
            req.setStu_id(new String(buf,location,ReceiveStuInfoReq.STUIDSIZE).trim());
            location+=ReceiveStuInfoReq.STUIDSIZE;
            req.setStu_card_id(new String(buf,location,ReceiveStuInfoReq.STUCARDIDSIZE).trim());
            location+=ReceiveStuInfoReq.STUCARDIDSIZE;
            req.setStu_code(new String(buf,location,ReceiveStuInfoReq.STUCODESIZE).trim());
            location+=ReceiveStuInfoReq.STUCODESIZE;
            req.setStu_name(new String(buf,location,ReceiveStuInfoReq.STUNAMESIZE).trim());
            location+=ReceiveStuInfoReq.STUNAMESIZE;
            req.setStu_sex(new String(buf,location,ReceiveStuInfoReq.STUSEXSIZE).trim());
            location+=ReceiveStuInfoReq.STUSEXSIZE;
            req.setStu_birth(new String(buf,location,ReceiveStuInfoReq.STUBIRTHSIZE).trim());
            location+=ReceiveStuInfoReq.STUBIRTHSIZE;
            req.setStu_address(new String(buf,location,ReceiveStuInfoReq.STUADDRESSSIZE).trim());
            location+=ReceiveStuInfoReq.STUADDRESSSIZE;
            req.setStu_province(new String(buf,location,ReceiveStuInfoReq.PROVINCESIZE).trim());
            location+=ReceiveStuInfoReq.PROVINCESIZE;
            req.setStu_city(new String(buf,location,ReceiveStuInfoReq.CITYSIZE).trim());
            location+=ReceiveStuInfoReq.CITYSIZE;
            req.setStu_district(new String(buf,location,ReceiveStuInfoReq.COUNTYSIZE).trim());
            location+=ReceiveStuInfoReq.COUNTYSIZE;
            req.setStu_school(new String(buf,location,ReceiveStuInfoReq.STUSCHOOLSIZE).trim());
            location+=ReceiveStuInfoReq.STUSCHOOLSIZE;
            req.setStu_class(new String(buf,location,ReceiveStuInfoReq.STUCLASSSIZE).trim());
            location+=ReceiveStuInfoReq.STUCLASSSIZE;
            req.setStu_remark(new String(buf,location,ReceiveStuInfoReq.STUREMARKSIZE).trim());
            location+=ReceiveStuInfoReq.STUREMARKSIZE;
            req.setTeacher_name(new String(buf,location,ReceiveStuInfoReq.TEACHERNAMESIZE).trim());
            location+=ReceiveStuInfoReq.TEACHERNAMESIZE;
            req.setTeacher_tel(new String(buf,location,ReceiveStuInfoReq.TEACHERTELSIZE).trim());
            location+=ReceiveStuInfoReq.TEACHERTELSIZE;
            req.setRelative_name(new String(buf,location,ReceiveStuInfoReq.RELATIVENAMESIZE).trim());
            location+=ReceiveStuInfoReq.RELATIVENAMESIZE;
            req.setRelative_tel(new String(buf,location,ReceiveStuInfoReq.RELATIVETELSIZE).trim());
            location+=ReceiveStuInfoReq.RELATIVETELSIZE;
            req.setRelative_type(new String(buf,location,ReceiveStuInfoReq.RELATIVETYPESIZE).trim());
            location+=ReceiveStuInfoReq.RELATIVETYPESIZE;
            req.setEnterprise_id(new String(buf,location,ReceiveStuInfoReq.ENTERPRISEIDSIZE).trim());
            location+=ReceiveStuInfoReq.ENTERPRISEIDSIZE;
            req.setOrganization_id(new String(buf,location,ReceiveStuInfoReq.ORGANIZATIONIDSIZE).trim());
            location+=ReceiveStuInfoReq.ORGANIZATIONIDSIZE;
            
            if(location!=Integer.parseInt(req.getMsgLength())){
            	throw new Exception("接收短信平台学生的协议错误！");
            }
            
            log.info("ReceiveStuInfoReq:接收短信平台学生同步处理结束");
            return req;
        } catch (Throwable t) {
            throw new ParseException("parse sync stu response failed.", t);
        }
    }

    public void valid(ReceiveStuInfoReq msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

    @SuppressWarnings("unchecked")
	public void handle(ReceiveStuInfoReq msg, SmsCommunicateService communicateService) throws HandleException {
    	int sequence = 0;
    	String result = null;
    	String stu_id = null;
    	List list = null;
    	try {
	    	if(msg.getStu_id()!=null&&!msg.getStu_id().equals("")){
	    		sequence = dao.selectOracleSequence();
	    		DBBuffer.getInstance().add(dao.updateStuInfoByStuId(msg,sequence));
	    	}else{
	    		list = dao.selectStuIdByCardId(msg.getStu_card_id());
	    		if(list !=null&&list.size()>0){
	    			StuIdBean stuidbean = (StuIdBean) list.get(0);
	    			stu_id = stuidbean.getStu_id();
	    			msg.setStu_id(stu_id);
	    			DBBuffer.getInstance().add(dao.updateStuInfoByCardId(msg));
	    		}else{
	    			sequence = dao.selectOracleSequence();
		    		stu_id = String.valueOf(sequence);
					DBBuffer.getInstance().add(dao.insertStuInfo(msg,sequence));
	    		}
	    	}
	    	result = "0";
		} catch (Exception e) {
			result = "1";
			e.printStackTrace();
		}
		ReceiveStuInfoResp resp = new ReceiveStuInfoResp();
    	resp.setResult(result);
    	if(msg.getStu_id()!=null&&!msg.getStu_id().equals("")){
    		log.info("ReceiveStuInfoReqProcessor:接收到该学生"+msg.getStu_id()+"同步信息，待发送应答");
    		resp.setStu_id(msg.getStu_id());
    	}else{
    		log.info("ReceiveStuInfoReqProcessor:接收到该学生"+stu_id+"同步信息，待发送应答");
    		resp.setStu_id(stu_id);		
    	}
    	resp.setMsgLength(String.valueOf(ReceiveStuInfoResp.total));
    	resp.setCommand(ReceiveStuInfoResp.COMMAND);
    	resp.setSeqLength(msg.getSeqLength());
    	SendMethod.SendCommand(resp.getBytes());
    	log.info("ReceiveStuInfoReqProcessor:处理后成功反馈处理结果给短信！");
    }
}
