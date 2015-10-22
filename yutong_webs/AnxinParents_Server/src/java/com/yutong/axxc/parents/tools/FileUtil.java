package com.yutong.axxc.parents.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.cxf.common.util.Base64Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	public static String readFile(String fileName)  {
		FileInputStream fis=null;   
		try {
			 File tempFile = new File(fileName);
			    	fis = new FileInputStream(tempFile);
			    	byte[] buf = new byte[(int) tempFile.length()];
					fis.read(buf);
					return Base64Utility.encode(buf).trim();
				} catch (Exception e) {
					logger.error("读取文件异常"+fileName, e);
				}finally{
					try {
						if(fis!=null){
							fis.close();
						}
					} catch (IOException e) {
						logger.error("关闭文件流异常", e);
					}
				}
		return null;
	}
	
	public static int writeFile(String base64Str,String fileName) throws Exception  {
		    FileOutputStream fos=null;
		    File file3 = new File(fileName);
		    byte[] buf1=Base64Utility.decode(base64Str);
			fos =new FileOutputStream(file3);
			fos.write(buf1);
			fos.flush();
			fos.close();
			return buf1.length;
	}
}
