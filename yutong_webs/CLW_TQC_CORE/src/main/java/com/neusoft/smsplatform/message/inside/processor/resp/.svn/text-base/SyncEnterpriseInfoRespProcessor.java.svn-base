
package com.neusoft.smsplatform.message.inside.processor.resp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.smsplatform.message.dao.UpdateDAO;
import com.neusoft.smsplatform.message.inside.msg.resp.SyncEnterpriseInfoResp;
import com.neusoft.smsplatform.message.inside.processor.MessageAbstractInsideProcessor;
import com.neusoft.smsplatform.message.nio.SmsCommunicateService;


/**
 * 同步企业应答processor
 * @author chenqiong
 *
 */
public final class SyncEnterpriseInfoRespProcessor extends MessageAbstractInsideProcessor<SyncEnterpriseInfoResp, SmsCommunicateService> {

    private static final SyncEnterpriseInfoRespProcessor PROCESSOR = new SyncEnterpriseInfoRespProcessor();

    private static UpdateDAO dao = (UpdateDAO) SpringBootStrap.getInstance().getBean("updatedao");
    
    private Logger log = LoggerFactory.getLogger(SyncEnterpriseInfoRespProcessor.class);
    private SyncEnterpriseInfoRespProcessor() {
    }

    public static SyncEnterpriseInfoRespProcessor getInstance() {
        return PROCESSOR;
    }

	public SyncEnterpriseInfoResp parse(byte[] buf) throws ParseException {
        try {
        	log.info("SyncEnterpriseInfoResp:企业同步回应处理开始");
        	SyncEnterpriseInfoResp resp = new SyncEnterpriseInfoResp();
            int location = super.parseHeader(buf, resp);
            String result = new String(buf,location,SyncEnterpriseInfoResp.RESULTSIZE);
            location+=SyncEnterpriseInfoResp.RESULTSIZE;
            String enterprise_id = new String(buf,location,SyncEnterpriseInfoResp.ENTERPRISEIDSIZE).trim();
            location+=SyncEnterpriseInfoResp.ENTERPRISEIDSIZE;
            DBBuffer.getInstance().add(dao.UpdateEnterprise_SyncFlag(enterprise_id, result));
//            if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
//            	Constant.getMemcachedClient().delete(resp.getCommand()+resp.getSeqLength());
//            }
//            Constant.ytbsendcmdMap.remove(resp.getCommand()+resp.getSeqLength());
            log.info("SyncEnterpriseInfoResp:企业同步回应处理结束");
            return resp;
        } catch (Throwable t) {
            throw new ParseException("parse sync enterprise response failed.", t);
        }
    }

    public void valid(SyncEnterpriseInfoResp msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

	public void handle(SyncEnterpriseInfoResp msg,
			SmsCommunicateService communicateService) throws HandleException {
		
	}
}
