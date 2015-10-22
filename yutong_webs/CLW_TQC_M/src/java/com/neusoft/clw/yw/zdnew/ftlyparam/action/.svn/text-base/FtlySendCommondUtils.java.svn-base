package com.neusoft.clw.yw.zdnew.ftlyparam.action;

import org.apache.log4j.Logger;

import com.neusoft.clw.common.util.Base64;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.servlet.ds.MemData;
import com.neusoft.clw.yw.xj.monitor.ds.SendCommandInfo;
import com.neusoft.clw.yw.zdnew.ftlyparam.ds.FtlyParamInfo;

public class FtlySendCommondUtils {
	
protected static final Logger log = Logger.getLogger(FtlySendCommondUtils.class);

    public SendCommandInfo getSendCommand(FtlyParamInfo bean, String sendType) {
    	SendCommandInfo  sendCommandInfo = new SendCommandInfo();
    	try{
	    	// 设置主键
			sendCommandInfo.setCommandId(UUIDGenerator.getUUID32());
			// 设置SIM卡号
			sendCommandInfo.setSimCardNumber(bean.getSimCardNumber());
			// 设置车辆VIN号
			sendCommandInfo.setVehicleVin(bean.getVin());
			// 消息ID
			String messageId = UUIDGenerator.getUUID32();
			// 设置消息ID
			sendCommandInfo.setMessageId(messageId);
			// 设置下发子类型
			sendCommandInfo.setSendType("2009");
			// 设置备注
			if(sendType.equals("1"))
				sendCommandInfo.setRemark("油量标定设置");
			else
				sendCommandInfo.setRemark("油量标定查询");
			// 设置核心ID
			if (null != MemData.getAppcfgList()) {
				sendCommandInfo.setCoreId(MemData.getAppcfgList().get(0).getCoreId());
		    }
			// 设置用户ID
			sendCommandInfo.setOperateUserId(bean.getCreater());
			StringBuffer sb = new StringBuffer();
	        sb.append("00042900");
	        sb.append("01");
	        sb.append(format(Integer.toHexString(messageId.getBytes("GBK").length), 2));
			sb.append(messageId);
			byte [] packentContent = downMsgStr(sb.toString(),analyseTerminalParams(bean,sendType));
			sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
			String content = encoder.encode(packentContent);
			sendCommandInfo.setPacketContent(content);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return sendCommandInfo;
    	
    }
    
	public static String format(String str, int len) {
        while (str.length() < len) {
            str = "0" + str;
        }
        return str;
    }
	
	public byte[] downMsgStr(String prefix,byte[] bb) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(prefix);
		byte contentByte[]=bb;
		sb.append("02");
		sb.append(format(Integer.toHexString(contentByte.length), 4));
		byte[] cc=sb.toString().getBytes();
		byte[] dd = new byte[cc.length+bb.length];
		System.arraycopy(cc, 0, dd, 0, cc.length);		
		System.arraycopy(bb, 0, dd, cc.length, bb.length);		
		return dd;
		
	}
	
	 /**
     * 分析设置的参数
     * @param bean
     * @return
     */
    private byte [] analyseTerminalParams(FtlyParamInfo bean,String sendType) {
    	byte []content = new byte[10];	
        if("1".equals(sendType)){//设置
        	content[0] = (byte)0x82;
        	content[1] = (byte)0x01; 
        	content[2] = (byte)0x05;
			content[3] = (byte)0x01; 
        	if(checkValue(bean.getOilDemarcate())){
        		String demaracte = bean.getOilDemarcate();
        		String d1= String.valueOf(Integer.parseInt(demaracte)*20);
        		byte[] oilDemarcate= short2Bytes(Short.parseShort(d1));	
        		//定义新数组，调整后两个数组的位置
    			byte oilDemarcateNewData[]=new byte[2];
    			oilDemarcateNewData[0]=oilDemarcate[1];oilDemarcateNewData[1]=oilDemarcate[0];
        		System.arraycopy(oilDemarcateNewData, 0, content, 4, oilDemarcateNewData.length);
        	}else{
        		byte[] oilDemarcate= short2Bytes(Short.parseShort("0"));	
        		System.arraycopy(oilDemarcate, 0, content, 4, oilDemarcate.length);
        	}
        	if(checkValue(bean.getAdGap())){
        		byte[] adGap= short2Bytes(Short.parseShort(bean.getAdGap()));	
        		//定义新数组，调整后两个数组的位置
        		byte adGapNewData[]=new byte[2];
        		adGapNewData[0]=adGap[1];adGapNewData[1]=adGap[0];
        		System.arraycopy(adGapNewData, 0, content, 6, adGapNewData.length);
        	}else{
        		byte[] adGap= short2Bytes(Short.parseShort("0"));	
        		System.arraycopy(adGap, 0, content, 6, adGap.length);
        	}
        	if(checkValue(bean.getAddOilLimit())){
        		int addOilLimit = Integer.parseInt(bean.getAddOilLimit());
        		content[8] = (byte) addOilLimit;// 
        	}else
        		content[8] = (byte) 0;// 
        	if(checkValue(bean.getStealOilLimit())){
        		int stealOilLimit = Integer.parseInt(bean.getStealOilLimit());
        		content[9] = (byte) stealOilLimit;// 
        	}else
        		content[9] = (byte) 0;// 
        }else{//查询
        	byte[] oilDemarcate= short2Bytes(Short.parseShort("0"));	
        	byte[] adGap= short2Bytes(Short.parseShort("0"));	
        	int addOilLimit = 0;
        	int stealOilLimit = 0;
        	content[0] = (byte)0x82;
        	content[1] = (byte)0x01; 
        	content[2] = (byte)0x05;
			content[3] = (byte)0x00; 
			System.arraycopy(oilDemarcate, 0, content, 4, oilDemarcate.length);
			System.arraycopy(adGap, 0, content, 6, adGap.length);
			content[8] = (byte) addOilLimit;// 
			content[9] = (byte) stealOilLimit;// 
        }
        
        return content;
     }
    
    /**
     * 判断字符是否为空 true：非空 false：空【终端参数设置专用】
     * @param str
     * @return
     */
    private boolean checkValue(String str) {
        if(str == null || str.length() == 0 ) {
            return false;
        }
        return true;
    }
    
    public static byte[] short2Bytes(short b) {
        byte[] shortBuf = new byte[2];
        shortBuf[1] = (byte) (b & 0xff);
        shortBuf[0] = (byte) ((b >>> 8) & 0xff);
        return shortBuf;
    }
    
    public static void main(String [] args){
    	sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    	try{
    		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    		StringBuffer sb = new StringBuffer();
    		sb.append("00042900012038ace6bfb18c433d9a7a70e444dd85bf02");
    		byte []content = new byte[10];	
        	content[0] = (byte)0x82;
        	content[1] = (byte)0x01; 
        	content[2] = (byte)0x05;
			content[3] = (byte)0x01; 
			String demaracte = "19";
    		String d1= String.valueOf(Integer.parseInt(demaracte)*20);
    		byte[] oilDemarcate= short2Bytes(Short.parseShort(d1));	
    		
    		//定义新数组，调整后两个数组的位置
			byte oilDemarcateNewData[]=new byte[2];
			oilDemarcateNewData[0]=oilDemarcate[1];oilDemarcateNewData[1]=oilDemarcate[0];
    		System.arraycopy(oilDemarcateNewData, 0, content, 4, oilDemarcateNewData.length);
    		byte[] adGap= short2Bytes(Short.parseShort("380"));	
    		byte adGapNewData[]=new byte[2];
    		adGapNewData[0]=adGap[1];adGapNewData[1]=adGap[0];
    		System.arraycopy(adGapNewData, 0, content, 6, adGapNewData.length);
    		int addOilLimit = Integer.parseInt("1");
    		content[8] = (byte) addOilLimit;// 
    		int stealOilLimit = Integer.parseInt("1");
    		content[9] = (byte) stealOilLimit;// 
    		String c = encoder.encode(content);
			System.out.println(new String(c));
    		
    		
    		sb.append(format(Integer.toHexString(content.length), 4));
    		byte[] cc=sb.toString().getBytes();
    		System.out.println(new String(encoder.encode(cc)));
    		
    		byte[] dd = new byte[cc.length+content.length];
    		System.arraycopy(cc, 0, dd, 0, cc.length);		
    		System.arraycopy(content, 0, dd, cc.length, content.length);	
    		
    		
			String aa = encoder.encode(dd);
			System.out.println(new String(aa));
        byte [] a =decoder.decodeBuffer("MDAwNDI5MDAwMTIwNzgwNDM5N2NjYzUwNDAxYmI2YzhjYjdmMzBlZTE3NTEwMjAwMGGCAQUBAAEA AQEB");
        System.out.println(new String(a));
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}
