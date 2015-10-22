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
import com.neusoft.clw.core.xmlbean.ListDocument.List;
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
public final class CmdTcProcessor extends AbstractProcessor<String, NioCommunicateService> {
    public static final String COMMAND = "GAS.AUTH_LOGIN";
    private static final CmdTcProcessor PROCESSOR = new CmdTcProcessor();
    private Logger log = LoggerFactory.getLogger(ClwCommunicateHandler.class);
    private CmdTcProcessor() {
    }

    public static CmdTcProcessor getInstance() {
        return PROCESSOR;
    }
   public void valid(OlxDocument doc) {
        
    }
   public String  GetUpTcListen() {
//	   <?xml version="1.0" encoding="UTF-8"?>
//	   <olx dir="up" version="0.0.1" compress="false">
//	   	<client sessionid="e172cb46-7839-4902-b695-ca5d31478c13-A-gPORT-sessi"
//	   		authcode="0"></client>
//	   	<function name="GAS.CMD.TC" seq="88" synchronized="true"
//	   		timeout="30" needreturn="true" paramencrypt="none">
//	   		<param>
//	   			<tree-object version="0.0.2">
//	   				<value key="action" class="str" value="LISTEN"></value>
//	   				<value key="targetId" class="str"
//	   					value="803BB04476E21CB0E04400156052F593-A-dbdbdb-anybdb"></value>
//	   				<map key="param" class="" value="">
//	   					<value key="s" class="bool" value="true"></value>
//	   					<list key="noL" class="" value="">
//	   						<value key="phoneNum" class="str" value="123"></value>
//	   					</list>
//	   				</map>
//	   			</tree-object>
//	   		</param>
//	   	</function>
//	   </olx>

       OlxDocument doc = OlxDocument.Factory.newInstance();
       Olx olx=doc.addNewOlx();
       olx.setDir("up");
       olx.setVersion("0.0.1");
       olx.setCompress("false");
       Client client=olx.addNewClient();
       client.setSessionid(CoreShareInfo.sessionId);
       client.setAuthcode("0");
       
       Function function=olx.addNewFunction();
       function.setName("GAS.CMD.TC");
       function.setSeq(String.valueOf(CoreShareInfo.getSeq()));
       function.setSynchronized("true");
       function.setTimeout("30");
       function.setNeedreturn("true");
       function.setParamencrypt("none");

       Param param = function.addNewParam();;
       TreeObject tree=param.addNewTreeObject();
       tree.setVersion("0.0.2");
       Value value1 =tree.addNewValue();
       value1.setKey("action");
       value1.setClass1(Enum.forString("str"));
       value1.setValue("LISTEN");
       	            
       Value value2 =tree.addNewValue();
       value2.setKey("targetId");
       value2.setClass1(Enum.forString("str"));
       value2.setValue("803BB04476E21CB0E04400156052F593-A-dbdbdb-anybdb");

       Map map = tree.addNewMap();
       map.setKey("param");
       Value mapValue=map.addNewValue();
       mapValue.setKey("s");
       mapValue.setClass1(Enum.forString("bool"));
       mapValue.setValue("true");
       
       List list=tree.addNewList();
       list.setKey("noL");     
       Value value3=list.addNewValue();
       value3.setKey("phoneNum");
       value3.setClass1(Enum.forString("str"));
       value3.setValue("123");
       
       return doc.toString(); 
    }
	@SuppressWarnings("unchecked")
	public void handle(OlxDocument doc,NioCommunicateService nioService)throws HandleException {
		try {
//			<olx dir="down" version="0.0.1" c="false">
//			<function name="GAS.CMD.TC" seq="35" resultencrypt="null">
//				<result>
//					<tree-object version="0.0.4">
//						<map key="cr">
//							<value key="m" class="bstr"	value=""></value>
//							<value key="c" class="int" value="-2"></value>
//							<value key="targetTime" class="int" value="1260511167"></value>
//						</map>
//						<value key="m" class="bstr"	value=""></value>
//						<value key="c" class="int" value="-2"></value>
//					</tree-object>
//				</result>
//			</function>
//		</olx>

			Hashtable treeValue   =new Hashtable() ; 
			Hashtable crMapH   =new Hashtable() ; 
			Hashtable crValueH   =new Hashtable() ; 
			Olx olx=doc.getOlx();
			@SuppressWarnings("unused")
			String olxdir=olx.getDir();
			Function function=olx.getFunction();
			Result result=function.getResult(); 
			TreeObject  tree=result.getTreeObject();
			Value[]  valuearray=tree.getValueArray();
			int i;
			for(i=0;i<valuearray.length;i++){
//				System.out.println(valuearray[i].getKey()+valuearray[i].getValue());
				treeValue.put(valuearray[i].getKey(), valuearray[i].getValue()); 
			}
			Map[] map=tree.getMapArray();
			for(i=0;i<map.length;i++){ 
				crMapH.put(map[i].getKey(), map[i]); 
			}
			Value[] mapcr=((Map) crMapH.get("cr")).getValueArray();
			for(i=0;i<mapcr.length;i++){
//				System.out.println(valuearray[i].getKey()+valuearray[i].getValue());
				crValueH.put(mapcr[i].getKey(), mapcr[i].getValue()); 
			}
			if( crValueH.get("c")!=null){ 
				  if(Integer.parseInt( crValueH.get("c").toString())==0){//单个成功 
					   	
				}else{
					log.info(LogFormatter.formatMsg("CmdTcProcessor",crValueH.get("m").toString() ));
				}
			};
			
			if( treeValue.get("c")!=null){ 
			  if(Integer.parseInt( treeValue.get("c").toString())==0){//成功
				   
					  log.info(LogFormatter.formatMsg("CmdTcProcessor",treeValue.get("e").toString() ));
					  log.info(LogFormatter.formatMsg("CmdTcProcessor",treeValue.get("m").toString() ));

			}};
			nioService.send("login test".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
