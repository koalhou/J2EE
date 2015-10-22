package com.neusoft.clw.core.processor;
import java.io.File;

import com.neusoft.clw.core.xmlbean.*;
import com.neusoft.clw.core.xmlbean.ClientDocument.Client;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ParamDocument.Param;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value.Class.Enum;
public class writexml {
	 private String filename = null;

	    public writexml(String filename) {
	        super();
	        this.filename = filename;
	    }
	    public void createStudent() {
	        try {
	        	/*
	        	<?xml version="1.0" encoding="UTF-8"?>
	        	<olx dir="up" version="0.0.1" compress="false">
	        		<client></client>
	        		<function name="GAS.AUTH_LOGIN" seq="3" synchronized="true"
	        			timeout="30" needreturn="true" paramencrypt="none">
	        			<param>
	        		      <tree-object version="0.0.2">
	        			<value key="authId" class="bstr" value="aHRnbA=="></value>
	        			<value key="authPwd" class="bytes" value="NFFyY09VbTZXYXUrVnVCWDhnK0lQZz09"></value>
	        			<value key="rcode" class="str" value="0001"></value>
	        			<value key="tcode" class="str" value="00"></value>
	        			</tree-object>
	        			</param>
	        		</function>
	        	</olx>
                */
	            // Create Document
	            OlxDocument doc = OlxDocument.Factory.newInstance();
	            @SuppressWarnings("unused")
				String ssd=doc.xmlText();
	            Olx olx=doc.addNewOlx();
	            olx.setDir("up");
	            olx.setVersion("0.0.1");
	            olx.setCompress("false");
	            @SuppressWarnings("unused")
				Client client=olx.addNewClient();
	            
	            Function function=olx.addNewFunction();
	            function.setName("GAS.AUTH_LOGIN");
	            function.setSeq("3");
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
	            value1.setValue("aHRnbA");
	            	            
	            Value value2 =tree.addNewValue();
	            value2.setKey("authPwd");
	            value2.setClass1(Enum.forString("bytes"));
	            value2.setValue("NFFyY09VbTZXYXUrVnVCWDhnK0lQZz09");
	           	          
	            File xmlFile = new File(filename);
	            @SuppressWarnings("unused")
				String ss;
	            ss=doc.toString();
	            doc.save(xmlFile);
	            @SuppressWarnings("unused")
				int aa;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    public static void main(String[] args) {
			String filename = "D://test.xml";
			writexml add = new writexml(filename);
			add.createStudent();
			System.out.println("XMLBean形式 增加XML文档内容成功！");
		}
}
