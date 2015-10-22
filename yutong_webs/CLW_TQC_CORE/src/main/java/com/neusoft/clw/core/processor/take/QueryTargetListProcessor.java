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
import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.vncs.nio.ClwCommunicateHandler;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-11-24 下午03:30:48
 */
public final class QueryTargetListProcessor extends AbstractProcessor<String, NioCommunicateService> {
    public static final String COMMAND = "GAS.QUERY.TARGET.LIST";
    private static final QueryTargetListProcessor PROCESSOR = new QueryTargetListProcessor();
    private Logger log = LoggerFactory.getLogger(ClwCommunicateHandler.class);
    private QueryTargetListProcessor() {
    }

    public static QueryTargetListProcessor getInstance() {
        return PROCESSOR;
    }
   public void valid(OlxDocument doc) {
        
    }
   public String  GetUpXml() {
//	   <?xml version="1.0" encoding="UTF-8"?>
//	   <olx dir="up" version="0.0.1" c="false">
//	   	<client sessionid="2f4062e2-7961-44f1-aff3-808f42dbc450-A-gPORT-sessi"
//	   		corpid="" operatorid="此处传获取的集团下操作员的id" ></client>//此处corpid传递相应集团的id
//	   	<function name="GAS.QUERY.TARGET.LIST" seq="100">
//	   		<param>
//	   			<tree-object version="0.0.2"></tree-object>
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
       client.setCorpid("");
       client.setOperatorid("集团操作员");
       
       Function function=olx.addNewFunction();
       function.setName("GAS.QUERY.TARGET.LIST");
       function.setSeq(String.valueOf(CoreShareInfo.getSeq()));

       Param param = function.addNewParam();;
       TreeObject tree=param.addNewTreeObject();
       tree.setVersion("0.0.2");       
       return doc.toString(); 
    }
	@SuppressWarnings("unchecked")
	public void handle(OlxDocument doc,NioCommunicateService nioService)throws HandleException {
		try {
			int i=0;
			@SuppressWarnings("unused")
			List[] targetIndexList ;
			@SuppressWarnings("unused")
			int totalCount=0;
			Hashtable carMapH=new Hashtable();//存放车辆信息
			Hashtable MapH=new Hashtable();			
			Hashtable treeValueH   =new Hashtable() ; 
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
			if( treeValueH.get("c")!=null){ 
				 if(Integer.parseInt( treeValueH.get("c").toString())==0){//成功
					   	
					}else{
						log.info(LogFormatter.formatMsg("CmdTstProcessor",treeValueH.get("m").toString() ));
					}
			};
			totalCount=Integer.parseInt( treeValueH.get("totalCount").toString());
			targetIndexList=tree.getListArray();
			 
			Map[] map=tree.getMapArray();
			for(i=0;i<map.length;i++){ 
				MapH.put(map[i].getKey(), map[i]); 
			}
			map=((Map) MapH.get("targetMap")).getMapArray();
			for(i=0;i<map.length;i++){ 
				carMapH.put(map[i].getKey(), map[i]); 
			}
			 
			Map crMap=(Map) carMapH.get("e685753b-5d1b-4212-8462-fdfccf1d2fdd-X-WebCo-EVehi");//获得一个车辆信息
			@SuppressWarnings("unused")
			Value[]  crArray=crMap.getValueArray();

			
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
