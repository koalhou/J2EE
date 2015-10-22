package com.neusoft.tqcpt.value;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.util.Converser;
import com.neusoft.clw.vncs.util.UUIDGenerator;
import com.neusoft.tqcpt.service.FtlyBuildSQL;

public class Up_ZsptFtlyValue {
	private static final Logger log = LoggerFactory.getLogger(Up_ZsptFtlyValue.class);
	private static final String NAME = "<Up_ZsptFtlyValue>";	
	private static final DecimalFormat   df   =new DecimalFormat("#0.00");
	//指针位置
	private static int locZspt;	
 
  
	/**
	 * 功能：防偷漏油处理方法，将返回数据插入数据库
	 * 
	 * @param uhc 封装对象,buf 数据字节,idxZspt 指针开始位置
	 * @return  
	 */
	public static void setUpFtlyData(Up_InfoContent uhc,byte[] buf, int idxZspt) throws ParseException,UnsupportedEncodingException {
	  
	  /*0	纬度	DWORD	以度为单位的纬度值乘以10的6次方，精确到百万分之一度
	    4	经度	DWORD	以度为单位的经度值乘以10的6次方，精确到百万分之一度
		8	高程	WORD	海拔高度，单位为米（m）
		10	速度	WORD	1/10km/h
		12	方向	WORD	0 至 359， 正北为0，顺时针
		14	时间	BCD[6]	YY-MM-DD-hh-mm-ss（GMT+8时间）
		20	防偷漏油数据	BYTE[n]	防偷漏油数据内容		
		 ---------------------------------------------------------------------------------------------
		 | 0x82(防偷油消息头) | 0x01(协议版本标识) | 纬度 | 经度 | 海拔  | 速度  | 方向  | 上报时间  | 防偷漏油数据   |
		 --------------------------------------------------------------------------------------------*/
		//82010212340606C4F9FC006F006500B21312161819480093A70D0000CD0D
		// 获得ftlyBuildSQL对象
		FtlyBuildSQL ftlyBuildSQL = (FtlyBuildSQL) SpringBootStrap.getInstance().getBean("ftlyBuildSQL");
		// 获得 ServiceUnitSQL对象
		//ServiceUnitSQL serviceUnit=(ServiceUnitSQL) SpringBootStrap.getInstance().getBean("serviceUnitSQL");
		
		locZspt = idxZspt;	
		//跳过消息透传类型 0x82
		locZspt += 1;
		//跳过协议版本号
		locZspt += 1; 
		
		//----组装基础数据
		uhc=Up_ZsptFtlyValue.getBasicInfo(uhc,buf,"防偷漏油");
		 
		locZspt += 6;
		//----防偷油数据		
		byte ftlyData[] = new byte[buf.length-locZspt];
		System.arraycopy(buf, locZspt, ftlyData, 0, buf.length-locZspt);
		
		log.info(NAME+" <防偷漏油> VIN-->>"+uhc.getTerminalId()+" 数据--->> "+Converser.bytesToHexString(ftlyData));		
		
		//----油位异常标志  B2B1B0=000  油位正常; B2B1B0=001 偷油告警;B2B1B0=010 加油告警;B2B1B0=011之后 保留;
		int ftyLoc=0;
		byte oilboxStateData[]=new byte[1];
		System.arraycopy(ftlyData, ftyLoc, oilboxStateData, 0, 1);
		String  oilboxStateStr = Converser.hexTo2BCD(Converser.bytesToHexString(oilboxStateData));
		String  oilboxState=oilboxStateStr.substring(5, oilboxStateStr.length()).trim();
	    log.info(NAME+" <防偷漏油> 油位异常标志-->: "+oilboxState);
		//加油和偷油告警的时候，设置位置信息
		/*if("01".equals(oilboxState) || "10".equals(oilboxState)){			
			SearchGisAreaByCode sgabc=	new SearchGisAreaByCode();
			String xmlString = sgabc.getAddressInfoFrPoint(uhc.getLongitude(), uhc.getLatitude(), 0, 3000, 10);
			log.info(NAME+" <防偷漏油> xmlString -->: "+xmlString);
			if(!xmlString.equals("")){
				String zoneName=sgabc.getZoneNameByXmlString(xmlString);
				uhc.setZonename(zoneName);
			   log.info(NAME+" <防偷漏油> zoneName -->: "+zoneName);
			}else{
				uhc.setZonename("定位无效");
			}
		}*/
		
	    if("100".equals(oilboxState)){//如果是断电上报的版本信息
	    	String ftly = Converser.bytesToHexString(ftlyData);
	    	String ftlySeqId=UUIDGenerator.getUUID32();
			uhc.getZsptFtlyInfo().setFtlyId(ftlySeqId);
			uhc.getZsptFtlyInfo().setOilboxState(""+oilboxState);//油箱状态	
			uhc.getZsptFtlyInfo().setAddOill(df.format(0.00));//本次加油量
			uhc.getZsptFtlyInfo().setOilboxLevel(df.format(0.00));//油箱油位
			uhc.getZsptFtlyInfo().setOilboxMass(df.format(0.00));//油箱油量	
			byte version [] =Converser.hexStringToBytes(ftly.substring(2,6));
			uhc.getZsptFtlyInfo().setOilDeviceVersion(""+(char)version[0]+(char)version[1]+ftly.substring(6,12));//版本号
			DBBuffer.getInstance().add(ftlyBuildSQL.buildInsertZsptFtlyInfoSql(uhc));	
	    }else if("101".equals(oilboxState)){//参数查询应答
	    	ftyLoc+=2;
	    	//-----标定油量
	    	byte oilDemarcateData[]=new byte[4];
	    	System.arraycopy(ftlyData, ftyLoc, oilDemarcateData, 2, 2);
	    	//定义新数组，调整后两个数组的位置
			byte oilDemarcatNewData[]=new byte[4];
			oilDemarcatNewData[0]=oilDemarcateData[0];oilDemarcatNewData[1]=oilDemarcateData[1];
			oilDemarcatNewData[2]=oilDemarcateData[3];oilDemarcatNewData[3]=oilDemarcateData[2];
			int oilDemarcateTemp=Converser.bytes2int(oilDemarcatNewData);
			String oilDemarcate=String.valueOf(oilDemarcateTemp*0.05);
			ftyLoc+=2;
	    	//----AD落差
			byte adGapData[]=new byte[4];
	    	System.arraycopy(ftlyData, ftyLoc, adGapData, 2, 2);
	    	//定义新数组，调整后两个数组的位置
			byte adGapNewData[]=new byte[4];
			adGapNewData[0]=adGapData[0];adGapNewData[1]=adGapData[1];
			adGapNewData[2]=adGapData[3];adGapNewData[3]=adGapData[2];
			int adGapDataTemp=Converser.bytes2int(adGapNewData);
			String adGap=String.valueOf(adGapDataTemp);
			ftyLoc+=2;
	    	//----加油门限
			byte addOilLimitData[]=new byte[4];
	    	System.arraycopy(ftlyData, ftyLoc, addOilLimitData, 3, 1);
	    	int addOilLimitTemp=Converser.bytes2int(addOilLimitData);
			String addOilLimit=String.valueOf(addOilLimitTemp);
			ftyLoc+=1;
	    	//----偷油门限
			byte stealOilLimitData[]=new byte[4];
	    	System.arraycopy(ftlyData, ftyLoc, stealOilLimitData, 3, 1);
	    	int stealOilLimitTemp=Converser.bytes2int(stealOilLimitData);
			String stealOilLimit=String.valueOf(stealOilLimitTemp);
			
			uhc.getZsptFtlyInfo().setOilDemarcate(oilDemarcate);//标定油量	
			uhc.getZsptFtlyInfo().setAdGap(adGap);//AD落差
			uhc.getZsptFtlyInfo().setAddOilLimit(addOilLimit);//加油门限	
			uhc.getZsptFtlyInfo().setStealOilLimit(stealOilLimit);//偷油门限	
			DBBuffer.getInstance().add(ftlyBuildSQL.buildInsertZsptFtlyParamSql(uhc));
	    }else{
	    	ftyLoc+=1;
			//----燃油液位
			byte oilboxLevelData[]=new byte[4];
			System.arraycopy(ftlyData, ftyLoc, oilboxLevelData, 3, 1);
			int oilboxLevelTemp=Converser.bytes2int(oilboxLevelData);
			String oilboxLevel=df.format(oilboxLevelTemp*0.4);
			//log.info(NAME+" <防偷漏油> 燃油液位-->: "+oilboxLevel);
			
			ftyLoc+=3;
			//----本次加油量
			byte addOilData[]=new byte[4];
			System.arraycopy(ftlyData, ftyLoc, addOilData, 2, 2);		
			//定义新数组，调整后两个数组的位置
			byte addOilNewData[]=new byte[4];
			addOilNewData[0]=addOilData[0];addOilNewData[1]=addOilData[1];
			addOilNewData[2]=addOilData[3];addOilNewData[3]=addOilData[2];
			
			int addOilTemp=Converser.bytes2int(addOilNewData);
			String addOil=df.format(addOilTemp*0.05);
			//log.info(NAME+" <防偷漏油> 本次加油量-->: "+addOil);
			
			
			ftyLoc+=2;
			//----油箱燃油油量
			byte oilboxMassData[]=new byte[4];
			System.arraycopy(ftlyData, ftyLoc, oilboxMassData, 2, 2);
			//定义新数组，调整后两个数组的位置
			byte oilboxMassNewData[]=new byte[4];
			oilboxMassNewData[0]=oilboxMassData[0];oilboxMassNewData[1]=oilboxMassData[1];
			oilboxMassNewData[2]=oilboxMassData[3];oilboxMassNewData[3]=oilboxMassData[2];
			
			int oilboxMassTemp=Converser.bytes2int(oilboxMassNewData);
			String oilboxMass=df.format(oilboxMassTemp*0.05);		
			//log.info(NAME+" <防偷漏油> 油箱燃油油量-->: "+oilboxMass);
			
			//----赋值对象
			//String ftlySeqId=serviceUnit.getSeqFtlyId();
			String ftlySeqId=UUIDGenerator.getUUID32();
			uhc.getZsptFtlyInfo().setFtlyId(ftlySeqId);
			uhc.getZsptFtlyInfo().setOilboxState(""+oilboxState);//油箱状态		
			uhc.getZsptFtlyInfo().setAddOill(""+addOil);//本次加油量
			uhc.getZsptFtlyInfo().setOilboxLevel(""+oilboxLevel);//油箱油位
			uhc.getZsptFtlyInfo().setOilboxMass(""+oilboxMass);//油箱油量	
			DBBuffer.getInstance().add(ftlyBuildSQL.buildInsertZsptFtlyInfoSql(uhc));	
	    }
	    //----只为偷油告警发送信息		
		/*if("01".equals(oilboxState)){	
			//发送短信
			callSmsInterface(uhc);			
		}*/	
	}
	public static Up_InfoContent getBasicInfo(Up_InfoContent uhc,byte[] buf,String moduleName){	
		//纬度		
		byte latBytes[] = new byte[4];		
		System.arraycopy(buf, locZspt, latBytes, 0, 4);		
		double lattmp = Converser.bytes2int(latBytes);		
		double lat = lattmp/1000000;
		
		locZspt += 4;
		//经度		
		byte lonBytes[] = new byte[4];		
		System.arraycopy(buf, locZspt, lonBytes, 0, 4);		
		double lontmp = Converser.bytes2int(lonBytes);		
		double lon = lontmp/1000000;
		
		locZspt += 4;
		//海拔高度		
		byte elevBytes[] = new byte[4];		
		System.arraycopy(buf, locZspt, elevBytes, 2, 2);		
		double elevtmp = Converser.bytes2int(elevBytes);		
		double elev = elevtmp;
		
		locZspt += 2;
		//速度      WORD格式为什么还要new byte[4],本来可以new byte[2],
		//因为INT 类型是4个字节，所以为了避免两个字节强转出现异常,创建4个字节
		byte speedBytes[] = new byte[4];		
		System.arraycopy(buf, locZspt, speedBytes, 2, 2);		
		double speedtmp = Converser.bytes2int(speedBytes);		
		double speed = elevtmp/10;
		
		locZspt += 2;
		//方向				
		byte directionBytes[] = new byte[4];		
		System.arraycopy(buf, locZspt, directionBytes, 2, 2);		
		double direction = Converser.bytes2int(directionBytes);
		
		locZspt += 2;
		//时间		
		byte timeBytes[] = new byte[6];		
		System.arraycopy(buf, locZspt, timeBytes, 0, 6);		
		String time = Converser.bcdToStr(timeBytes, 0, 6);	
	   //log.info(NAME+"<"+moduleName+">纬度-->:"+lat+" 经度-->:"+lon+" 海拔-->:"+elev+"速度-->:"+speed+" 方向-->:"+direction+" 时间-->:"+time);		
		
		//赋值对象
		uhc.setLatitude(lat > 0 ? String.valueOf(lat):"FFFF");   //纬度
		uhc.setLongitude(lon > 0 ? String.valueOf(lon):"FFFF");//经度
		uhc.setElevation(""+elev); //海拔
		uhc.setDirection(""+direction);//方向
		uhc.setGps_speeding(""+speed);//GPS车速
		uhc.setSpeed(""+speed);    //车速			
		uhc.setTerminal_time(time);//终端上报时间			
		return uhc;
	}
	
	//测试
	public static void  main(String[] args){
		
		//byte[] buf =Converser.hexStringToBytes("82010212328206C4FA120072000000B21312161820180093A70D0000CD0D");
		byte[] buf =Converser.hexStringToBytes("820102118F8F06C7EB73004F0176005713121816252800775C0A0000090D");
		
		System.out.println("-----buf.length----->>:"+buf.length);
		
		int locZspt = 4;	
		//跳过消息透传类型 0x82
		locZspt += 1;
		//跳过协议版本号
		locZspt += 1; 
		
		locZspt += 6;
		//----防偷油数据		
		byte[] ftlyData = new byte[buf.length-locZspt];
		System.arraycopy(buf, locZspt, ftlyData, 0, buf.length-locZspt);
		
		ftlyData=Converser.hexStringToBytes("093AB0D0000CD0D");
		
		int ftyLoc=0;
		byte oilboxStateData[]=new byte[1];
		System.arraycopy(ftlyData, ftyLoc, oilboxStateData, 0, 1);
		String  oilboxStateStr = Converser.hexTo2BCD(Converser.bytesToHexString(oilboxStateData));
		String  oilboxState=oilboxStateStr.substring(5, oilboxStateStr.length()).trim();
		System.out.println(NAME+" <防偷漏油> 油位异常标志-->: "+oilboxState);
		
		ftyLoc+=1;
		//----燃油液位
		byte oilboxLevelData[]=new byte[4];
		System.arraycopy(ftlyData, ftyLoc, oilboxLevelData, 3, 1);
		int oilboxLevelTemp=Converser.bytes2int(oilboxLevelData);
		String oilboxLevel=df.format(oilboxLevelTemp*0.4);
		System.out.println(NAME+" <防偷漏油> 燃油液位-->: "+oilboxLevel);
		
		ftyLoc+=3;
		//----本次加油量
		byte addOilData[]=new byte[4];
		System.arraycopy(ftlyData, ftyLoc, addOilData, 2, 2);		
		//定义新数组，调整后两个数组的位置
		byte addOilNewData[]=new byte[4];
		addOilNewData[0]=addOilData[0];addOilNewData[1]=addOilData[1];
		addOilNewData[2]=addOilData[3];addOilNewData[3]=addOilData[2];
		
		int addOilTemp=Converser.bytes2int(addOilNewData);
		String addOil=df.format(addOilTemp*0.05);
		System.out.println(NAME+" <防偷漏油> 本次加油量-->: "+addOil);
		
		
		ftyLoc+=2;
		//----油箱燃油油量
		byte oilboxMassData[]=new byte[4];
		System.arraycopy(ftlyData, ftyLoc, oilboxMassData, 2, 2);
		//定义新数组，调整后两个数组的位置
		byte oilboxMassNewData[]=new byte[4];
		oilboxMassNewData[0]=oilboxMassData[0];oilboxMassNewData[1]=oilboxMassData[1];
		oilboxMassNewData[2]=oilboxMassData[3];oilboxMassNewData[3]=oilboxMassData[2];
		
		int oilboxMassTemp=Converser.bytes2int(oilboxMassNewData);
		String oilboxMass=df.format(oilboxMassTemp*0.05);		
		System.out.println(NAME+" <防偷漏油> 油箱燃油油量-->: "+oilboxMass);
	}
}