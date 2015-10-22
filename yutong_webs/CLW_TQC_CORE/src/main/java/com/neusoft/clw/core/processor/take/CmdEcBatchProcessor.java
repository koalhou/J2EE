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
public final class CmdEcBatchProcessor extends AbstractProcessor<String, NioCommunicateService> {
    public static final String COMMAND = "GAS.CMD.EC.BATCH";
    private static final CmdEcBatchProcessor PROCESSOR = new CmdEcBatchProcessor();
    private Logger log = LoggerFactory.getLogger(ClwCommunicateHandler.class);
    private CmdEcBatchProcessor() {
    }

    public static CmdEcBatchProcessor getInstance() {
        return PROCESSOR;
    }
   public void valid(OlxDocument doc) {
        
    }
   public String  GetUpXml() {
//	   <?xml version="1.0" encoding="UTF-8"?>
//	   <olx dir="up" version="0.0.1" compress="false">
//	   	<client sessionid="e172cb46-7839-4902-b695-ca5d31478c13-A-gPORT-sessi"
//	   		authcode="0"></client>
//	   	<function name="GAS.CMD.EC.BATCH" seq="105" synchronized="true"
//	   		timeout="30" needreturn="true" paramencrypt="none">
//	   		<param>
//	   			<tree-object version="0.0.2">
//	   				<value key="needBatchReturn" class="bool" value="true"></value>
//	   				<value key="action" class="str" value="TAKE_PHOTO"></value>
//	   				<list key="targetIds" class="" value="">
//	   					<value key="targetId" class="str"
//	   						value="04ef9f03-b844-4452-8315-4336821fd615-X-WebCo-EVehi"></value>
//	   				</list>
//	   				<value key="extId" class="str" value="extid"></value>
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
       function.setName("GAS.CMD.EC.BATCH");
       function.setSeq(String.valueOf(CoreShareInfo.getSeq()));
       function.setSynchronized("true");
       function.setTimeout("30");
       function.setNeedreturn("true");
       function.setParamencrypt("none");

       Param param = function.addNewParam();;
       TreeObject tree=param.addNewTreeObject();
       tree.setVersion("0.0.2");
       Value value1 =tree.addNewValue();
       value1.setKey("needBatchReturn");
       value1.setClass1(Enum.forString("bool"));
       value1.setValue("true");
       	            
       Value value2 =tree.addNewValue();
       value2.setKey("action");
       value2.setClass1(Enum.forString("str"));
       value2.setValue("TAKE_PHOTO");
       
       Value value3 =tree.addNewValue();
       value3.setKey("extId");
       value3.setClass1(Enum.forString("bstr"));
       value3.setValue("extid");
       
       List list=tree.addNewList();
       list.setKey("targetIds");
       Value listValue=list.addNewValue();
       listValue.setKey("targetId");
       listValue.setClass1(Enum.forString("str"));
       listValue.setValue("803BB04476E21CB0E04400156052F593-A-dbdbdb-anybdb");
                     
       return doc.toString(); 
    }
	@SuppressWarnings("unchecked")
	public void handle(OlxDocument doc,NioCommunicateService nioService)throws HandleException {
		try {
//			<?xml version="1.0" encoding="UTF-8"?>
//			<olx dir="down" version="0.0.1" c="false">
//				<function name="GAS.CMD.EC.BATCH" seq="105" resultencrypt="null">
//					<result>
//						<tree-object version="0.0.4">
//							<value key="fail" class="int" value="0"></value>
//							<map key="crMap">
//								<map key="04ef9f03-b844-4452-8315-4336821fd615-X-WebCo-EVehi">
//									<map key="cr">
//										<value key="targetTime" class="int" value="1271327291"></value>
//										<value key="c" class="int" value="0"></value>
//										<value key="m" class="bstr"
//											value=""></value>
//									</map>
//								</map>
//							</map>
//							<value key="c" class="int" value="0"></value>
//							<value key="m" class="bstr" value=""></value>
//							<value key="success" class="int" value="1"></value>
//						</tree-object>
//					</result>
//				</function>
//			</olx>

			int i=0;
			Hashtable treeValueH   =new Hashtable() ; 
			Hashtable crMapH   =new Hashtable() ; 
			Hashtable mapH   =new Hashtable() ; 
			Hashtable crH   =new Hashtable() ; 
			Hashtable crValueH   =new Hashtable() ; 
			Olx olx=doc.getOlx();
			@SuppressWarnings("unused")
			String olxdir=olx.getDir();
			Function function=olx.getFunction();
			Result result=function.getResult(); 
			TreeObject  tree=result.getTreeObject();
			Value[]  valuearray=tree.getValueArray();
			for(i=0;i<valuearray.length;i++){			 
				treeValueH.put(valuearray[i].getKey(), valuearray[i].getValue()); 
			}
			
			Map[] map=tree.getMapArray();
			for(i=0;i<map.length;i++){ 
				crMapH.put(map[i].getKey(), map[i]); 
			}
			map=((Map) crMapH.get("crMap")).getMapArray();
			for(i=0;i<map.length;i++){ 
				mapH.put(map[i].getKey(), map[i]); 
			}
			map=((Map) mapH.get("803BB04476E21CB0E04400156052F593-A-dbdbdb-anybdb")).getMapArray();
			for(i=0;i<map.length;i++){ 
				crH.put(map[i].getKey(), map[i]); 
			}
			Map crMap=(Map) crH.get("cr");
			Value[]  crArray=crMap.getValueArray();
			for(i=0;i<crArray.length;i++){			 
				crValueH.put(crArray[i].getKey(), crArray[i].getValue()); 
			}
			if( crValueH.get("c")!=null){ 
				  if(Integer.parseInt( crValueH.get("c").toString())==0){//单个成功 
					   	
				}else{
					log.info(LogFormatter.formatMsg("CmdTstProcessor",crValueH.get("m").toString() ));
				}
			};
			
			
			if( treeValueH.get("c")!=null){ 
				@SuppressWarnings("unused")
				String success= treeValueH.get("success").toString();
				 if(Integer.parseInt( treeValueH.get("c").toString())==0){//总体成功
					   	
					}else{
						log.info(LogFormatter.formatMsg("CmdTstProcessor",treeValueH.get("m").toString() ));
					}
			};
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
