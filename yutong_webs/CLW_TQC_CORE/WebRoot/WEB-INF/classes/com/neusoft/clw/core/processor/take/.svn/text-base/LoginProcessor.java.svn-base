/*******************************************************************************
 * @(#)BindRespProcessor.java 2008-11-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.core.processor.take;

//import com.neusoft.tag.app.inside.msg.resp.BindResp;
//import com.neusoft.tag.app.inside.processor.AbstractInsideProcessor;
import java.util.Hashtable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.core.nio.NioCommunicateService;
import com.neusoft.clw.core.processor.AbstractProcessor;
import com.neusoft.clw.core.processor.CoreShareInfo;
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.ClientDocument.Client;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ParamDocument.Param;
import com.neusoft.clw.core.xmlbean.ResultDocument.Result;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class.Enum;
import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.vncs.nio.ClwCommunicateHandler;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-11-24 下午03:30:48
 */
public final class LoginProcessor extends AbstractProcessor<String, NioCommunicateService> {
    public static final String COMMAND = "GAS.AUTH_LOGIN";
    private static final LoginProcessor PROCESSOR = new LoginProcessor();
    private Logger log = LoggerFactory.getLogger(ClwCommunicateHandler.class);
    private LoginProcessor() {
    }

    public static LoginProcessor getInstance() {
        return PROCESSOR;
    }
   public void valid(OlxDocument doc) {
        
    }
   public String  GetUpXml() {
       OlxDocument doc = OlxDocument.Factory.newInstance();
       Olx olx=doc.addNewOlx();
       olx.setDir("up");
       olx.setVersion("0.0.1");
       olx.setCompress("false");
       @SuppressWarnings("unused")
       Client client=olx.addNewClient();
       
       Function function=olx.addNewFunction();
       function.setName("GAS.AUTH_LOGIN");
       function.setSeq(String.valueOf(CoreShareInfo.getSeq()));
       function.setSynchronized("true");
       function.setTimeout("30");
       function.setNeedreturn("true");
       function.setParamencrypt("none");

       Param param = function.addNewParam();;
       TreeObject tree=param.addNewTreeObject();
       tree.setVersion("0.0.2");
       Value value1 =tree.addNewValue();
       value1.setKey("authId");
       value1.setClass1(Enum.forString("bstr"));
       value1.setValue("aHRnbA==");
       	            
       Value value2 =tree.addNewValue();
       value2.setKey("authPwd");
       value2.setClass1(Enum.forString("bytes"));
       value2.setValue("NFFyY09VbTZXYXUrVnVCWDhnK0lQZz09");
       
       Value rcode =tree.addNewValue();
       rcode.setKey("rcode");
       rcode.setClass1(Enum.forString("str"));
       rcode.setValue("0001");
       
       @SuppressWarnings("unused")
	Value tcode =tree.addNewValue();
       rcode.setKey("tcode");
       rcode.setClass1(Enum.forString("str"));
       rcode.setValue("00");
  
       return doc.toString(); 
    }
	@SuppressWarnings("unchecked")
	public void handle(OlxDocument doc,NioCommunicateService nioService)throws HandleException {
		try {
			Hashtable treeValue   =new Hashtable() ; 
			Olx olx=doc.getOlx();
			@SuppressWarnings("unused")
			String olxdir=olx.getDir();
			Function function=olx.getFunction();
			Result result=function.getResult(); 
			TreeObject  tree=result.getTreeObject();
			Value[]  valuearray=tree.getValueArray();
			for(int i=0;i<valuearray.length;i++){
				System.out.println(valuearray[i].getKey()+valuearray[i].getValue());
				treeValue.put(valuearray[i].getKey(), valuearray[i].getValue()); 
			}
			if( treeValue.get("c")!=null){ 
			  if(Integer.parseInt( treeValue.get("c").toString())==0){//成功
				  if( treeValue.get("sessionid")!=null){ 
					  CoreShareInfo.sessionId=treeValue.get("sessionid").toString();
				  }else{
					  CoreShareInfo.sessionId="";
					  log.info(LogFormatter.formatMsg("CorCommunicateHandler",treeValue.get("e").toString() ));
					  log.info(LogFormatter.formatMsg("CorCommunicateHandler",treeValue.get("m").toString() ));
				  }
				
			}};
			nioService.send("login test".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
