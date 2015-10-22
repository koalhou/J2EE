package com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action;

import org.apache.log4j.Logger;
import java.lang.Math;
import java.io.File;



public class AlarmRecode {
	protected final Logger log = Logger.getLogger(getClass());
	
	/**
	 * 基础告警
	 */
	private String ALARM_BASE_INFO = "0101010101010101010101010101010201010101010101010101111111111111";   
	                              //00001000000000000000100000000001
	
	
	
	/**
	 * 扩展告警
	 */
	private String ALARM_EXT_INFO = "01010101011111010101010111010111";
	                               //00001000000000000000100000000001
	
	/**
	 * 状态Acc
	 */
	private String STAT_INFO = "01010101011111010101010111010113";
	
	/**
	 * VEH_EXT_INFO 扩展车辆信号状态位
	 */
	private String VEH_EXT_INFO = "00001000000000000000100000000001";
	
	/**
	 * 获取上报的DVR状态
	 * @param s
	 * @return
	 */
	public String dvr_info(String s){
	    String stat="";
	    char no0=s.charAt(0);
	    stat=""+no0;
	    return stat;
	}
	
	public String stat_info(String s){
		
		String stat = "";
		
		char no32 = s.charAt(31);
		
		char[] no32tostring = new char[1];
		no32tostring[0] = no32;
		
		stat = ""+no32;
		log.info("ACC点火：" + stat);
		
		return stat;
	}
	/**
	 * 前门状态
	 * @param s
	 * @return
	 */
	public String stat_info_door(String s){
		
		String door = "";
		
		char no19 = s.charAt(18);
		
		char[] no32tostring = new char[1];
		no32tostring[0] = no19;
		
		door = ""+no19;
		log.info("door_frint：" + door);
		
		return door;
	}
	
	public String dingwei_stat_info(String s){
		
		String stat = "";
		
		char no31 = s.charAt(30);
		
		char[] no32tostring = new char[1];
		no32tostring[0] = no31;
		
		stat = ""+no31;
		log.info("定位状态：" + stat);
		
		return stat;
	}
	
	public String recode_alarm_base(String s){
		
		s.length();
		System.out.println(s.length());
		
		String alarm = "";
		
		if(s.length() < 64){
			return "";
		}
		
		char no1 = s.charAt(0);
		char no2 = s.charAt(1);
		char no3 = s.charAt(2);
		char no4 = s.charAt(3);
		char no5 = s.charAt(4);
		char no6 = s.charAt(5);
		char no7 = s.charAt(6);
		char no8 = s.charAt(7);
		char no9 = s.charAt(8);
		char no10 = s.charAt(9);
		char no11 = s.charAt(10);
		char no12 = s.charAt(11);
		char no13 = s.charAt(12);
		char no14 = s.charAt(13);
		char no15 = s.charAt(14);
		char no16 = s.charAt(15);
		char no17 = s.charAt(16);
		char no18 = s.charAt(17);
		char no19 = s.charAt(18);
		char no20 = s.charAt(19);
		char no21 = s.charAt(20);
		char no22 = s.charAt(21);
		char no23 = s.charAt(22);
		char no24 = s.charAt(23);
		char no25 = s.charAt(24);
		char no26 = s.charAt(25);
		char no27 = s.charAt(26);
		char no28 = s.charAt(27);
		char no29 = s.charAt(28);
		char no30 = s.charAt(29);
		char no31 = s.charAt(30);
		char no32 = s.charAt(31);
		char no33 = s.charAt(32);
		char no34 = s.charAt(33);
		char no35 = s.charAt(34);
		char no36 = s.charAt(35);
		char no37 = s.charAt(36);
		char no38 = s.charAt(37);
		char no39 = s.charAt(38);
		char no40 = s.charAt(39);
		char no41 = s.charAt(40);
		char no42 = s.charAt(41);
		char no43 = s.charAt(42);
		char no44 = s.charAt(43);
		char no45 = s.charAt(44);
		char no46 = s.charAt(45);
		char no47 = s.charAt(46);
		char no48 = s.charAt(47);
		char no49 = s.charAt(48);
		char no50 = s.charAt(49);
		char no51 = s.charAt(50);
		char no52 = s.charAt(51);
		char no53 = s.charAt(52);//摄像头故障
		char no54 = s.charAt(53);//TTS模块故障
		char no55 = s.charAt(54);//终端LCD或显示器故障
		char no56 = s.charAt(55);//终端主电源掉电
		char no57 = s.charAt(56);//终端主电源欠压
		char no58 = s.charAt(57);//GNSS天线短路
		char no59 = s.charAt(58);//GNSS天线未接或被剪断
		char no60 = s.charAt(59);//GNSS模块发生故障
		char no61 = s.charAt(60);
		char no62 = s.charAt(61);//疲劳驾驶
		char no63 = s.charAt(62);//车辆超速
		char no64 = s.charAt(63);//紧急 告警SOS
		
		/*if(no53 == '1'){
			log.info("摄像头故障");
			alarm += "64,";
		}
		
		if(no54 == '1'){
			log.info("TTS模块故障");
			alarm += "65,";
		}
		
		if(no55 == '1'){
			log.info("终端LCD或显示器故障");
			alarm += "66,";
		}
		if(no56 == '1'){
			log.info("终端主电源掉电");
			alarm += "67,";
		}
		if(no57 == '1'){
			log.info("终端主电源欠压");
			alarm += "68,";
		}
		if(no58 == '1'){
			log.info("GNSS天线短路");
			alarm += "69,";
		}
		if(no59 == '1'){
			log.info("GNSS天线未接或被剪断");
			alarm += "70,";
		}
		if(no60 == '1'){
			log.info("GNSS模块发生故障");
			alarm += "71,";
		}
		if(no62 == '1'){
			log.info("超时驾驶告警");
			//alarm += "超时驾驶告警,";
		}*/
		
		if(no63 == '1'){
			log.info("车辆超速");
			alarm += "32,";
		}
		if(no64 == '1'){
			log.info("紧急告警SOS");
			alarm += "40,";
		}
		
		log.info("\n本次基础告警："+alarm.replace(',', '\n'));
		
		return alarm;
		
	}
	
	public String recode_alarm_ext(String s){
		String alarm = "";
		
		char no1 = s.charAt(0);
		char no2 = s.charAt(1);
		char no3 = s.charAt(2);
		char no4 = s.charAt(3);
		char no5 = s.charAt(4);
		char no6 = s.charAt(5);
		char no7 = s.charAt(6);
		char no8 = s.charAt(7);
		char no9 = s.charAt(8);
		char no10 = s.charAt(9);
		char no11 = s.charAt(10);//ABS故障告警
		char no12 = s.charAt(11);//蓄电池电压报警
		char no13 = s.charAt(12);//冷却液温度过高报警
		char no14 = s.charAt(13);
		char no15 = s.charAt(14);
		char no16 = s.charAt(15);
		char no17 = s.charAt(16);
		char no18 = s.charAt(17);
		char no19 = s.charAt(18);
		char no20 = s.charAt(19);
		char no21 = s.charAt(20);
		char no22 = s.charAt(21);
		char no23 = s.charAt(22);
		char no24 = s.charAt(23);
		char no25 = s.charAt(24);//仓温报警信号
		char no26 = s.charAt(25);//缓速器高温报警信号
		char no27 = s.charAt(26);
		char no28 = s.charAt(27);//制动蹄片磨损报警
		char no29 = s.charAt(28);
		char no30 = s.charAt(29);//油压报警
		char no31 = s.charAt(30);//制动气压报警
		char no32 = s.charAt(31);
		
		if(no11 == '1'){
			log.info("ABS故障告警");
			alarm += "78,";
		}
		
		if(no12 == '1'){
			log.info("蓄电池电压报警");
			alarm += "77,";
		}
		
		if(no13 == '1'){
			log.info("冷却液温度过高报警");
			alarm += "76,";
		}
		if(no25 == '1'){
			log.info("仓温报警信号");
			alarm += "26,";
		}
		if(no26 == '1'){
			log.info("缓速器高温报警信号");
			alarm += "25,";
		}
		if(no28 == '1'){
			log.info("制动蹄片磨损报警");
			alarm += "13,";
		}
		if(no30 == '1'){
			log.info("油压报警");
			alarm += "10,";
		}
		if(no31 == '1'){
			log.info("制动气压报警");
			alarm += "09,";
		}
		
		log.info("\n本次附加告警："+alarm.replace(',', '\n'));
		
		return alarm;
		
	}
	
	
	
	
	public  static void main(String[] args){
		
		//AlarmRecode a = new AlarmRecode();
		
		//a.dingwei_stat_info("01010101011111010101010111010113");
		//a.recode_alarm_base();
		//a.recode_alarm_ext();
		//a.stat_info();
		
		//String str  = "72,40";
		//System.out.println(str.indexOf("70"));
		//System.out.println((int)Math.pow(2,4));
		
		File[] roots=File.listRoots();

	    for(int i=0;i<roots.length;i++)

	    {

	      System.out.println(roots[i].getPath());

	    }
		
	}
	 

}
