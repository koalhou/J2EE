package com.neusoft.clw.core.processor;

//import com.neusoft.communicate.ICommunicateService;
//import com.neusoft.tag.app.inside.msg.InsideMsg;
//import com.neusoft.tag.inside.utils.InsideMsgUtils;
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.nio.client.ICommunicateService;

/**
 * @author pudong
 */
@SuppressWarnings("hiding")
public abstract class AbstractProcessor<String,V extends ICommunicateService>
        implements IProcessor<String,V> {

   

    public void validxml(OlxDocument doc ) throws InvalidMessageException {
         
    }
}
