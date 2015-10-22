package com.neusoft.tqcpt.value;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.util.Converser;

public class Up_ZsptValue {
	private static final Logger log = LoggerFactory.getLogger(Up_ZsptValue.class);
	private static final String NAME = "<Up_ZsptValue>";	
	private static final DecimalFormat   df   =new DecimalFormat("#0.00");
 
	/**
	 * 功能： 解析上行透传协议,根据协议类型调用不同专属应用方法<客车平台>
	 * 
	 * @param location,buf,uhc
	 * @return  
	 */
	public static void getUp_ZSContent_Kcpt(String appType,byte[] dataArray,
			Up_InfoContent uhc) throws Exception {
		
		//设置车辆所属平台
		uhc.setPtFlag("0");	
		//客车平台不需要跳过 0x01(版本协议) 
		//校车:(byte)0x80=-128,远程诊断:(byte)0x81=-127/129,防偷油:(byte)0x82=-126/130,胎压监测:(byte)0x83=-125/131
		//防偷漏油
		if("130".equals(appType)){
			log.info(NAME+"--------<客车平台> 防偷漏油---------->: "+Converser.bytesToHexString(dataArray));
			Up_ZsptFtlyValue.setUpFtlyData(uhc,dataArray,-1);
		}
		/*//远程诊断
		if ("129".equals(appType)){//透传诊断消息
			log.info(NAME+"--------<客车平台> 远程诊断---------->: "+Converser.bytesToHexString(dataArray));			
			Up_ZsptDiagnosisValue.setUpDiagnosisData(uhc,dataArray,-1,"0");
		}
		
		//胎压监测
		if("131".equals(appType)){
			log.info(NAME+"--------<客车平台> 胎压监测---------->: "+Converser.bytesToHexString(dataArray));
			Up_ZsptTpmsValue.setUpTpmsData(uhc,dataArray,-1);
		}
		//车辆限速
		if("132".equals(appType)){
			log.info(NAME+"--------<客车平台> 车辆限速---------->: "+Converser.bytesToHexString(dataArray));
			Up_ZsptClxsValue.setUpClxsData(uhc,dataArray,-1);
		}*/
	}
	
	/**
	 * 功能： 解析上行透传协议,根据协议类型调用不同专属应用方法<校车平台>
	 * 
	 * @param location,buf,uhc
	 * @return  
	 */
	public static void getUp_ZSContent_Xcpt(int location, byte[] buf,
			Up_InfoContent uhc) throws Exception {
		
		//设置车辆所属平台
		uhc.setPtFlag("1");			
		location += ContentParamName.REGULARLEN;//跳过参数ID		
		//取出透传消息长度
		int bytelen01 = Integer.parseInt(new String(buf, location,
				ContentParamName.REGULARLEN * 2), 16);
		location += ContentParamName.REGULARLEN * 2;		
		//获取消息总长度
		int len = ContentParamName.getWhole_len(buf);		
		//获取消息剩余长度
		int neironglen = len - location;		
		
		//取出VNAP封装的透传内容		
	    /* arraycopy(Object src, int srcPos,Object dest,int destPos,int length)
		       参数：src - 源数组。srcPos - 源数组中的起始位置。dest - 目标数组。 
                destPos - 目标数据中的起始位置。length - 要复制的数组元素的数量。 */	
		byte[] SeriaNetContent = new byte[bytelen01];
		System.arraycopy(buf, location, SeriaNetContent, 0, bytelen01);
		
		//取出808协议中表示的消息类型，判断是否为专属应用透传
		byte cmdtype[] = new byte[1];
		System.arraycopy(SeriaNetContent, 0, cmdtype, 0, 1);
		
		//校车平台不需要跳过 0x01(版本协议) 
		//校车:(byte)0x80=-128,远程诊断:(byte)0x81=-127,防偷油:(byte)0x82=-126,胎压监测:(byte)0x83=-125
		//防偷漏油
		if(cmdtype[0]==-126){
			log.info(NAME+"--------<通勤车平台> 防偷漏油---------->: "+Converser.bytesToHexString(SeriaNetContent));
			Up_ZsptFtlyValue.setUpFtlyData(uhc,SeriaNetContent,0);
		}
		/*//远程诊断
		if (cmdtype[0]==-127){//透传诊断消息
			log.info(NAME+"--------<校车平台> 远程诊断---------->: "+Converser.bytesToHexString(SeriaNetContent));
			Up_ZsptDiagnosisValue.setUpDiagnosisData(uhc,SeriaNetContent,0,"1");
		}		
		//胎压监测
		if(cmdtype[0]==-125){
			log.info(NAME+"--------<校车平台> 胎压监测---------->: "+Converser.bytesToHexString(SeriaNetContent));
			Up_ZsptTpmsValue.setUpTpmsData(uhc,SeriaNetContent,0);
		}*/
	}
	
   
	public static Up_InfoContent getBasicInfo(Up_InfoContent uhc,byte[] buf,String moduleName,int locZspt){	
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
		uhc.setLatitude(""+lat);   //纬度
		uhc.setLongitude(""+lon);//经度
		uhc.setElevation(""+elev); //海拔
		uhc.setDirection(""+direction);//方向
		uhc.setGps_speeding(""+speed);//GPS车速
		uhc.setSpeed(""+speed);    //车速			
		uhc.setTerminal_time(time);//终端上报时间			
		return uhc;
	}
	public short[] byteArray2ShortArray(byte[] data, int items) {
		short[] retVal = new short[items];
		for (int i = 0; i < retVal.length; i++){
		retVal[i] = (short) ((data[i * 2]&0xff) | (data[i * 2+1]&0xff) << 8);
		}
		return retVal;   
	}
	
	// 将空值转换为空字符串
	public static String nullToStr(String str) {
		return str == null || str.equals("null") ? "" : str.trim();
	}	
}