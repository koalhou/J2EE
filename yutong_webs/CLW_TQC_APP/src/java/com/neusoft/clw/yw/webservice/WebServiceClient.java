/**
 * 
 */
package com.neusoft.clw.yw.webservice;


import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;

import com.neusoft.clw.yw.webservice.DT_SernrInfo;
import com.neusoft.clw.yw.webservice.DT_SernrInfoBus;
import com.neusoft.clw.yw.webservice.DT_SernrInfoBusDetail;
import com.neusoft.clw.yw.webservice.DT_SernrInfo_Response;
import com.neusoft.clw.yw.webservice.QBS_KYPT_SI_SernrInfo_Out;
import com.neusoft.clw.yw.webservice.QBS_KYPT_SI_SernrInfo_OutLocator;
import com.neusoft.clw.yw.webservice.SI_SernrInfo_Out;

/**
 * @author xingchengjie
 *
 */
public class WebServiceClient {

	private static Logger log = Logger.getLogger(WebServiceClient.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			testSAPWebService();
			
		} catch (Exception e) {
			System.err.println(e);
			log.error(e,e);
		}

	}
	
	public static void testMethod(String...strings) {
		for (String a : strings){
			System.out.println(a);
			
		}
	}
	
	public static void testBindBus(){
		try {
			String endpoint = "http://10.8.3.234:8080/CLW_SCD_INTERFACE/bindBusService?wsdl";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(new QName("http://ws.scd.clw.yutong.com/", "bindBus"));// WSDL里面描述的接口名称
			call.addParameter("arg0",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			String temp = "20131201";
			//call.setUseSOAPAction(true);
			String result = (String) call.invoke(new Object[] { temp });
			call.setTimeout(1000);
			// 给方法传递参数，并且调用方法
			System.out.println("result is " + result);
			
			testMethod(new String[]{"ddd","ddddddd","dddddd","dddd",});
			testMethod("eeeerrrrrr");
			
		} catch (Exception e) {
			System.err.println(e);
			log.error(e,e);
		}
	}
	
	public static void testSAPWebService() throws Exception{
		QBS_KYPT_SI_SernrInfo_Out loc = new QBS_KYPT_SI_SernrInfo_OutLocator();
		String address = "http://pitest2.yutong.com:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=QBS_KYPT&receiverParty=&receiverService=&interface=SI_SernrInfo_Out&interfaceNamespace=http%3A%2F%2Fyutong.com%2Fesb%2Fkypt%2Fecc%2FSernrInfo&j_username=huazy&j_password=yt123456";
		loc.setHTTP_PortEndpointAddress(address);
		SI_SernrInfo_Out service = loc.getHTTP_Port();
		DT_SernrInfo dinfo = new DT_SernrInfo();
		DT_SernrInfoBus bus = new DT_SernrInfoBus();
		//DT_SernrInfoBusDetail[] detail = {new DT_SernrInfoBusDetail("13C126A01-0003")};开发
		//DT_SernrInfoBusDetail[] detail = {new DT_SernrInfoBusDetail("14E144U-0012")};//生产
		DT_SernrInfoBusDetail[] detail = {new DT_SernrInfoBusDetail("13A199G-0017")};//测试
		
		bus.setDetail(detail);
		bus.setRequestSource("KYPT");
		bus.setRequestTarget("SAP");
		bus.setRequestTime("2014-02-17");
		bus.setRequestType("QUERY");
		bus.setRequestUser("TESTUSER");
		dinfo.setBus(bus);
		DT_SernrInfo_Response response = service.SI_SernrInfo_Out(dinfo);
		System.out.println(response.getBus_Response().getReturn_Sernr_Record()[0].getCph());
	}
	
	

}
