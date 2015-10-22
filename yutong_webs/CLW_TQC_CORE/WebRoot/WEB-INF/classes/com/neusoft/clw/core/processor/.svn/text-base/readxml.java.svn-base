package com.neusoft.clw.core.processor;

import java.io.File;

import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.ClientDocument.Client;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ParamDocument.Param;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
 

public class readxml {
	private String filename = null;

	public readxml(String filename) {
		super();
		this.filename = filename;
	}

	@SuppressWarnings("unused")
	private void pt(String str) {
		System.out.println(str);
	}

	public void studentReader() {
		try {
			File xmlFile = new File(filename);
			OlxDocument doc = OlxDocument.Factory.parse(xmlFile);
			Olx olx=doc.getOlx();
			@SuppressWarnings("unused")
			String olxdir=olx.getDir();
			@SuppressWarnings("unused")
			Client client=olx.getClient();
			Function function=olx.getFunction();
			Param param=function.getParam(); 
			TreeObject  tree=param.getTreeObject();
			Value[]  valuearray=tree.getValueArray();
			for(int i=0;i<valuearray.length;i++){
				System.out.println(valuearray[i].getKey());
				System.out.println(valuearray[i].getKey());
			}
			 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String filename = "D://testwork//xmltest//src//login//logon.xml";
		readxml read = new readxml(filename);
		System.out.println("XMLBean形式 读取XML文档内容：");
		read.studentReader();
	}
}
