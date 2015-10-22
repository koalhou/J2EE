package com.neusoft.smsplatform.message.inside.processor.resp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.smsplatform.message.dao.UpdateDAO;
import com.neusoft.smsplatform.message.inside.msg.resp.SyncStuInfoResp;
import com.neusoft.smsplatform.message.inside.processor.MessageAbstractInsideProcessor;
import com.neusoft.smsplatform.message.nio.SmsCommunicateService;



/**
 * 同步学生应答processor
 * @author chenqiong
 *
 */
public final class SyncStuInfoRespProcessor extends MessageAbstractInsideProcessor<SyncStuInfoResp, SmsCommunicateService> {

    private static final SyncStuInfoRespProcessor PROCESSOR = new SyncStuInfoRespProcessor();

    private static UpdateDAO dao = (UpdateDAO) SpringBootStrap.getInstance().getBean("updatedao");
    private Logger log = LoggerFactory.getLogger(SyncStuInfoRespProcessor.class);
    private SyncStuInfoRespProcessor() {
    }

    public static SyncStuInfoRespProcessor getInstance() {
        return PROCESSOR;
    }

	public SyncStuInfoResp parse(byte[] buf) throws ParseException {
        try {
        	log.info("SyncStuInfoResp:学生同步回应处理开始");
        	SyncStuInfoResp resp = new SyncStuInfoResp();
            int location = super.parseHeader(buf, resp);
            String result = new String(buf,location,SyncStuInfoResp.RESULTSIZE);
            location+=SyncStuInfoResp.RESULTSIZE;
            String stu_id = new String(buf,location,SyncStuInfoResp.STUIDSIZE).trim();
            location+=SyncStuInfoResp.STUIDSIZE;
            DBBuffer.getInstance().add(dao.UpdateStu_SyncFlag(stu_id, result));
//            if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
//            	Constant.getMemcachedClient().delete(resp.getCommand()+resp.getSeqLength());
//            }
//            Constant.ytbsendcmdMap.remove(resp.getCommand()+resp.getSeqLength());
            log.info("SyncStuInfoResp:学生同步回应处理结束");
            return resp;
        } catch (Throwable t) {
            throw new ParseException("parse sync stu response failed.", t);
        }
    }

    public void valid(SyncStuInfoResp msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

    public void handle(SyncStuInfoResp msg, SmsCommunicateService communicateService) throws HandleException {
    }
}
