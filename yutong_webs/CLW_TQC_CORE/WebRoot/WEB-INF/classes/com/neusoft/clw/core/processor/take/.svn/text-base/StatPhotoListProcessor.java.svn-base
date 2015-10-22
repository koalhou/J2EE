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
public final class StatPhotoListProcessor extends AbstractProcessor<String, NioCommunicateService> {
    public static final String COMMAND = "GAS.STAT.PHOTO.LIST";
    private static final StatPhotoListProcessor PROCESSOR = new StatPhotoListProcessor();
    private Logger log = LoggerFactory.getLogger(ClwCommunicateHandler.class);
    private StatPhotoListProcessor() {
    }

    public static StatPhotoListProcessor getInstance() {
        return PROCESSOR;
    }
   public void valid(OlxDocument doc) {
        
    }
   public String  GetUpXml() {
//<?xml version="1.0" encoding="UTF-8"?>
//   <olx dir="up" version="0.0.1" compress="false">
//	<client sessionid="e172cb46-7839-4902-b695-ca5d31478c13-A-gPORT-sessi"
//		authcode="0"></client>
//	<function name="GAS.STAT.PHOTO.LIST" seq="118" synchronized="true"
//		timeout="30" needreturn="true" paramencrypt="none">
//		<param>
//			<tree-object version="0.0.2">
//				<value key="beginTime" class="time" value="1259664540000"></value>
//				<value key="endTime" class="time" value="1271328540000"></value>
//				<list key="targetIds" class="" value="">
//					<value key="id" class="str"
//						value="803BB04476E21CB0E04400156052F593-A-dbdbdb-anybdb"></value>
//				</list>
//			</tree-object>
//		</param>
//	</function>
//</olx>

       OlxDocument doc = OlxDocument.Factory.newInstance();
       Olx olx=doc.addNewOlx();
       olx.setDir("up");
       olx.setVersion("0.0.1");
       olx.setCompress("false");
       Client client=olx.addNewClient();
       client.setSessionid(CoreShareInfo.sessionId);
       client.setAuthcode("0");
       
       Function function=olx.addNewFunction();
       function.setName("GAS.STAT.PHOTO.LIST");
       function.setSeq(String.valueOf(CoreShareInfo.getSeq()));
       function.setSynchronized("true");
       function.setTimeout("30");
       function.setNeedreturn("true");
       function.setParamencrypt("none");
       
       Param param = function.addNewParam();;
       TreeObject tree=param.addNewTreeObject();
       tree.setVersion("0.0.2");       
       Value value1=tree.addNewValue();
       value1.setKey("beginTime");
       value1.setClass1(Enum.forString("time"));
       value1.setValue("1259664540000");
       
       Value value2=tree.addNewValue();
       value2.setKey("endTime");
       value2.setClass1(Enum.forString("time"));
       value2.setValue("1271328540000");
       
       List list=tree.addNewList();
       list.setKey("targetIds");
       Value value3=list.addNewValue();
       value3.setKey("id");
       value3.setClass1(Enum.forString("str"));
       value3.setValue("803BB04476E21CB0E04400156052F593-A-dbdbdb-anybdb");
       
       return doc.toString(); 
    }
	@SuppressWarnings("unchecked")
	public void handle(OlxDocument doc,NioCommunicateService nioService)throws HandleException {
		try {
			int i=0;
			List[] indexList ;
			Hashtable indexListH   =new Hashtable() ; 
			@SuppressWarnings("unused")
			int totalPhotoCount=0;
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
			totalPhotoCount=Integer.parseInt( treeValueH.get("totalCount").toString());
			//获取照片列表
			indexList=tree.getListArray();
			for(i=0;i<indexList.length;i++){ 
				indexListH.put(indexList[i].getKey(), indexList[i]); 
			}
			List photoIndexList=(List) indexListH.get("photoIndexList");
			Value[] photoList=photoIndexList.getValueArray();
			for(i=0;i<photoList.length;i++){ 
				photoList[i].getValue(); 
			}
			//获取照片
			Map[] map=tree.getMapArray();
			for(i=0;i<map.length;i++){ 
				MapH.put(map[i].getKey(), map[i]); 
			}
			map=((Map) MapH.get("resultPhotoMap")).getMapArray();
			//遍历每一个终端照片信息
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
