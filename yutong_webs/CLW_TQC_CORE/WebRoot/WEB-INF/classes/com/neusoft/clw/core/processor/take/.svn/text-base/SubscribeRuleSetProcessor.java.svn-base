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
import com.neusoft.clw.core.xmlbean.MapDocument.Map;
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
public final class SubscribeRuleSetProcessor extends AbstractProcessor<String, NioCommunicateService> {
    public static final String COMMAND = "GAS.INFO.SUBSCRIBE_RULE_SET";
    private static final SubscribeRuleSetProcessor PROCESSOR = new SubscribeRuleSetProcessor();
    private Logger log = LoggerFactory.getLogger(ClwCommunicateHandler.class);
    private SubscribeRuleSetProcessor() {
    }

    public static SubscribeRuleSetProcessor getInstance() {
        return PROCESSOR;
    }
   public void valid(OlxDocument doc) {
        
    }
   public String  GetUpXml() {
//	   <?xml version="1.0" encoding="UTF-8"?>
//	   <olx dir="up" version="0.0.1" c="false">
//	   	<client sessionid="" authcode="NO_AUTH_CODE_NOW" />
//	   	<function name="GAS.INFO.SUBSCRIBE_RULE_SET" seq="100">
//	   		<param>
//	   			<tree-object version="0.0.2">
//	   				<map key="subscribeRuleMap">
//	   					<value key="com.gas.framework.info.PositionInfo" class="str" value="2"></value>
//	   					<value key="com.gas.framework.info.NoticeInfo" class="str" value="2"></value>
//	   					<value key="com.gas.framework.info.ReportInfo" class="str" value="2"></value>
//	   					<value key="com.gas.framework.info.MessageInfo" class="str" value="2"></value>
//	   				</map>
//	   				<map key="pM">
//	   					<value key="corporation" class="str" value="xxxxxxxxxxxxxx"></value>//此处订阅要传递相应要订阅的集团id
//	   				</map>
//	   			</tree-object>
//	   		</param>
//	   	</function>
//	   </olx>
//	   注：此处必须以集团形式进行订阅，其他订阅方式不支持
       OlxDocument doc = OlxDocument.Factory.newInstance();
       Olx olx=doc.addNewOlx();
       olx.setDir("up");
       olx.setVersion("0.0.1");
       olx.setCompress("false");
       Client client=olx.addNewClient();
       client.setSessionid(CoreShareInfo.sessionId);
       client.setAuthcode("NO_AUTH_CODE_NOW");
       
       Function function=olx.addNewFunction();
       function.setName("GAS.INFO.SUBSCRIBE_RULE_SET");
       function.setSeq(String.valueOf(CoreShareInfo.getSeq()));
       
       Param param = function.addNewParam();
       TreeObject tree=param.addNewTreeObject();
       tree.setVersion("0.0.2");
       Map suMap=tree.addNewMap();
       suMap.setKey("subscribeRuleMap");
          
       Value rcode =suMap.addNewValue();
       rcode.setKey("com.gas.framework.info.PositionInfo");
       rcode.setClass1(Enum.forString("str"));
       rcode.setValue("2");
      
       Map pMMap=tree.addNewMap();
       pMMap.setKey("subscribeRuleMap");
          
       Value corporation =pMMap.addNewValue();
       corporation.setKey("corporation");
       corporation.setClass1(Enum.forString("str"));
       corporation.setValue("0100001");
       
       return doc.toString(); 
    }
	@SuppressWarnings("unchecked")
	public void handle(OlxDocument doc,NioCommunicateService nioService)throws HandleException {
		try {
//			<?xml version="1.0" encoding="UTF-8"?>
//			<olx dir="down" version="0.0.1" c="false">
//				<function name="GAS.INFO.SUBSCRIBE_RULE_SET" seq="114933"
//					resultencrypt="false">
//					<result>
//						<tree-object version="0.0.4">
//							<value key="c" class="int" value="0"></value>
//			                 <value key="e" class="bstr" value=""></value>
//							<value key="m" class="bstr" value="6K6i6ZiF6KeE5YiZ6K6+572u5oiQ5Yqf"></value>
//						</tree-object>
//					</result>
//				</function>
//			</olx>

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
			}else{
				  CoreShareInfo.sessionId="";
				  log.info(LogFormatter.formatMsg("SubscribeRuleSetProcessor",treeValue.get("e").toString() ));
				  log.info(LogFormatter.formatMsg("SubscribeRuleSetProcessor",treeValue.get("m").toString() ));
			  }
			  };
			nioService.send("login test".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
