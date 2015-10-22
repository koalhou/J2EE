package test;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.vncs.util.Converser;

public class testPost extends Thread {
	//protected static final Logger LOGGER = Logger.getLogger(testPost.class);
	public int nFun = 1;
	private static final String[] files = new String[]{ ""
		, "D:\\宇通test\\通知.xml"
		};
	
	public static void main(String[] args) throws Exception {
		//BasicConfigurator.configure();
		//LOGGER.setLevel(Level.ALL);

		@SuppressWarnings("unused")
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String pass=Base64.encode(new String(Converser.Encryptbyte("test")));
		System.out.println("::::::::"+pass);
		URL url = new URL("http://10.10.124.100:8080/vncs/app");
//      url =new URL("http://localhost:8010/vncs/app");
//		URL url = new URL("http://10.10.18.207:8009/vspe_oil/vspe");
//      url = new URL("http://61.189.53.223:14008/vspe_oil/vspe");
		
		@SuppressWarnings("unused")
		int nFun =   1; // 1,2,3,4   
//		  testPost.test(nFun, url);
		 testPost.test(1, url);
		for (int i = 1; i <= 12; i++) {
		    if(0==i%files.length) continue;
		    // testVspePost.test(i%files.length, url); 
//		     reader.skip(1);

		    // System.out.println("回车键继续...");
		    // reader.readLine();
        }
		System.out.println("------------- Test finished! ---------------");
	}

	private testPost( ) {   
	}
	
	public static  void test(int nFun, URL url) {
		HttpURLConnection connection = null;
		try {
			connection = initeConnection(url);
			
			String strSend = xmlToString(nFun);
			 
			sendXml(connection, strSend);	
			System.out.println("======================================================================");
			System.out.println("已经发送");
			//System.out.println("请求xml内容:" + strSend);
			System.out.println("请求xml内容_:" + OlxDocument.Factory.parse(StringUtils.trimToNull(strSend.toString())).xmlText() );
			System.out.println("等待"+connection.getURL()+"响应消息...");
			
			StringBuffer strReceive = new StringBuffer();  
			int nStatusCode = receiveXml(connection, strReceive);
			System.out.println("HTTP响应码: " + nStatusCode + "  " + connection.getResponseMessage());
			
			if (200 == nStatusCode) {
			    System.out.println("x-status-code =  " + connection.getHeaderField("x-status-code"));
	            System.out.println("x-status-desc =  " + Base64.decode(connection.getHeaderField("x-status-desc"), "UTF-8"));
	            System.out.println("strReceive.length  = " + strReceive.length());
	            //System.out.println("strReceive:content = " + strReceive);
	            System.out.println(OlxDocument.Factory.parse(strReceive.toString()).toString());
	            System.out.println("===============================end====================================");
            } else {  
                System.out.println("消息:" + connection.getResponseMessage());
                System.out.println("ContentLength" + connection.getContentLength());                
                System.out.println("===============================end====================================");
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		    System.out.println(e.getMessage() +"\r\n" + e.getCause());
		} finally {
			connection.disconnect();
			connection = null;
		}
	}
	
   public static int receiveXml(HttpURLConnection connection, StringBuffer strXml) throws IOException {
        try {
            int nStatusCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String strCurrentLine = null;
            while ((strCurrentLine = reader.readLine()) != null) {
                strXml.append(strCurrentLine + "\n");
            }
            return nStatusCode;
        } catch (Exception e) {
   
            System.out.println("receiveXml()中出现异常:" + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

	public static HttpURLConnection initeConnection(URL url) throws IOException     {
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setConnectTimeout(30);
		return connection;
	}

	public static String xmlToString(int nFileNo) throws IOException {
		File file = new File(files[nFileNo]);
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		String strXml = "", strLine = "";
		while ((strLine = in.readLine()) != null) {
		  //strXml += strLine + "\r\n"; 
		    strXml += strLine; 		
		}
		strXml += "\n";
		return strXml;
	}

	public static void sendXml(HttpURLConnection connection, String strXml) throws IOException {
		// 命令行
		connection.setRequestProperty("POST", "/ HTTP/1.1");
		//
		// 头部参数
		//connection.setRequestProperty("Content-length", "" + strXml.getBytes().length);
		connection.setRequestProperty("Cache-Control", "no-cache");
		connection.setRequestProperty("x-protocol-version", "0.0.1");
		connection.setRequestProperty("x-service-type", "vspe:01/1.0");
		//
		// 内容
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		out.write(strXml);
		//
		out.flush();
		out.close();
	}


}
