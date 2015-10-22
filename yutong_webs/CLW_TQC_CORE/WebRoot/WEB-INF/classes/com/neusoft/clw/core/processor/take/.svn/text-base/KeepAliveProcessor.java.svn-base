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
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ResultDocument.Result;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.vncs.nio.ClwCommunicateHandler;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-11-24 下午03:30:48
 */
public final class KeepAliveProcessor extends AbstractProcessor<String, NioCommunicateService> {
    public static final String COMMAND = "GAS.KEEP_ALIVE";
    private static final KeepAliveProcessor PROCESSOR = new KeepAliveProcessor();
    @SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(ClwCommunicateHandler.class);
    private KeepAliveProcessor() {
    }

    public static KeepAliveProcessor getInstance() {
        return PROCESSOR;
    }
   public void valid(OlxDocument doc) {
        
    }
   public String  GetUpXml() {
      
       return ""; 
    }
	@SuppressWarnings("unchecked")
	public void handle(OlxDocument doc,NioCommunicateService nioService)throws HandleException {
		try {
//			<?xml version="1.0" encoding="UTF-8"?>
//			<olx dir="down" version="0.0.1" c="false">
//				<function name="GAS.KEEP_ALIVE" seq="258" resultencrypt="false">
//					<result>
//						<tree-object version="0.0.3">
//							<valuekey="time" class="int" value="1249453970351">
//			</value>
//							<value key="c" class="int" value="0"></value>
//							<valuekey="m" class="bstr" value="5oyB5rS75Zue5bqU">
//			</value>
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
//				System.out.println(valuearray[i].getKey()+valuearray[i].getValue());
				treeValue.put(valuearray[i].getKey(), valuearray[i].getValue()); 
			}
			if( treeValue.get("c")!=null){ 
			  if(Integer.parseInt( treeValue.get("c").toString())==0){//成功
				 
			}};
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
