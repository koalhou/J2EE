package com.neusoft.clw.ws.service;

import java.util.Properties;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.yw.webservice.DT_SernrInfo;
import com.neusoft.clw.yw.webservice.DT_SernrInfoBus;
import com.neusoft.clw.yw.webservice.DT_SernrInfoBusDetail;
import com.neusoft.clw.yw.webservice.DT_SernrInfo_Response;
import com.neusoft.clw.yw.webservice.DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record;
import com.neusoft.clw.yw.webservice.PBS_KYPT_SI_SernrInfo_Out;
import com.neusoft.clw.yw.webservice.PBS_KYPT_SI_SernrInfo_OutLocator;
import com.neusoft.clw.yw.webservice.QBS_KYPT_SI_SernrInfo_Out;
import com.neusoft.clw.yw.webservice.QBS_KYPT_SI_SernrInfo_OutLocator;
import com.neusoft.clw.yw.webservice.SI_SernrInfo_Out;


public class VehicleRecord {
    public DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record getVehicleRecord(String autoSn){
    	DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record result = null;
    	try{
			Properties prop = new Properties();
			prop.load(VehicleRecord.class.getClassLoader().getResourceAsStream("config.properties"));
			String address = prop.getProperty("sapwebserviceurl");
			address = new String(address.getBytes("ISO8859-1"), "GBK");
			
			SI_SernrInfo_Out service = null;
			if(null != address && address.indexOf("PBS_KYPT") > 0){
				PBS_KYPT_SI_SernrInfo_Out loc = new PBS_KYPT_SI_SernrInfo_OutLocator();
				loc.setHTTP_PortEndpointAddress(address);
				service = loc.getHTTP_Port();
			}else if(null != address && address.indexOf("QBS_KYPT") > 0){
				QBS_KYPT_SI_SernrInfo_Out loc = new QBS_KYPT_SI_SernrInfo_OutLocator();
				loc.setHTTP_PortEndpointAddress(address);
				service = loc.getHTTP_Port();
			}
			
			DT_SernrInfo dinfo = new DT_SernrInfo();
			DT_SernrInfoBus bus = new DT_SernrInfoBus();
			DT_SernrInfoBusDetail[] detail = {new DT_SernrInfoBusDetail(autoSn)};
			bus.setDetail(detail);
			bus.setRequestSource("KYPT");
			bus.setRequestTarget("SAP");
			bus.setRequestTime(DateUtil.getCurrentDay());
			bus.setRequestType("QUERY");
			bus.setRequestUser("TQC_ADMIN");
			dinfo.setBus(bus);
			DT_SernrInfo_Response response = service.SI_SernrInfo_Out(dinfo);
			DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record [] record = response.getBus_Response().getReturn_Sernr_Record();
			result = record[0];
    	} catch (Exception e) {
			e.printStackTrace();
        }
		return result;
    }
    public static void main(String[] args) {
    	try {
	    	String endpoint = "http://localhost:8081/CLW_TQC/services/getVehicleRecord";
	    	Service service = new Service();
	    	Call call = (Call) service.createCall();
	    	call.setTargetEndpointAddress(new java.net.URL(endpoint));
	    	call.setOperationName("getVehicleRecord");
	    	javax.xml.namespace.QName dtoqn = new javax.xml.namespace.QName("urn:BeanService", "DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record");
	    	Class dtocls = DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record.class;
	    	call.registerTypeMapping(dtocls, dtoqn,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							dtocls, dtoqn),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							dtocls, dtoqn));
	    	String json = "13A199G-0016";
			Object ret = (Object) call.invoke(new Object[] {json});
			DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record aa = (DT_SernrInfo_ResponseBus_ResponseReturn_Sernr_Record) ret;
			System.out.println(aa.getCph());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
}
