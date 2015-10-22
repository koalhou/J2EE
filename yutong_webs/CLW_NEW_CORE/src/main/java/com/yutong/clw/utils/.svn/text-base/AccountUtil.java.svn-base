package com.yutong.clw.utils;

import java.math.BigDecimal;

import com.yutong.clw.beans.cl.HarmDefBean;
import com.yutong.clw.beans.cl.VehicleBean;
import com.yutong.clw.config.Constant;
import com.yutong.clw.config.ContentParamName;
import com.yutong.clw.quartz.managers.cachemanager.VehicleCacheManager;

public class AccountUtil {
//	private static final Logger log = LoggerFactory.getLogger(AccountUtil.class);
	private static final String GEARS0 = "0";
	private static final String GEARS1 = "1";
	private static final String GEARS2 = "2";
	private static final String GEARS3 = "3";
	private static final String GEARS4 = "4";
	private static final String GEARS5 = "5";
	private static final String GEARS6 = "6";
	private static final String GEARS7 = "7";
	private static final String GEARS8 = "8";
	private static final String GEARSR = "12";
	
	private final static VehicleCacheManager vm = VehicleCacheManager.getInstance();
	
	public static String accountLongLat(String temp){
		if(temp.equals(Constant.F9)){
			return Constant.F4;
		}else{
			StringBuffer sb = setPlusOrMinus(temp);
			String amount = temp.substring(1, temp.length());
			int du = Converser.hexToInt(amount.substring(0, 2));
			int fen = Converser.hexToInt(amount.substring(2, 4));
			int miao = Converser.hexToInt(amount.substring(4, amount.length()));
			double ll = du+(double)(fen+(double)miao/10000)/60;	
			sb.append(ll);
			return sb.toString();
		}	
	}
	
	public static String accoutTorquePercent(String temp){
		if(!temp.equals(Constant.F3)){	
			StringBuffer sb = setPlusOrMinus(temp);
			String amount = temp.substring(1,temp.length());
			sb.append((float)Converser.hexToInt(amount)/100);
			return sb.toString();
		}else{
			return Constant.F4;
		}	
	}
	
	public static String accountTemp(String temp,String compare){
		if(temp.equals(compare)){
			return Constant.F4;
		}else{
			StringBuffer sb = setPlusOrMinus(temp);
			sb.append(Converser.hexToInt(temp.substring(1, temp.length())));
			return sb.toString();
		}
	}
	
	/**
	 * 计算发动机水温有符号16进制数是否有效，为FFF则无效
	 * @param temp
	 * @return
	 */
	public static String accountWaterTemperature(String temp){
		if(!temp.equals(Constant.F3)){
			StringBuffer sb = setPlusOrMinus(temp);
			String amount = temp.substring(1, temp.length());
			sb.append(Converser.hexToInt(amount));
			return sb.toString();
		}else{
			return Constant.F4;
		}
	}
	/**
	 * 处理有符号16进制数，第一位为正负号，0为正，1为负
	 * @param temp
	 * @return
	 */
//	public static StringBuffer setPlusOrMinus1(String temp){
//		StringBuffer sb = new StringBuffer();
//		String sign = temp.substring(0, 1);
//		if(sign.equals("0")){
//			sb.append("-");
//		}
//		return sb;
//	}
	
	public static StringBuffer setPlusOrMinus(String temp){
		StringBuffer sb = new StringBuffer();
		String sign = temp.substring(0, 1);
		if(sign.equals("1")){
			sb.append("-");
		}
		return sb;
	}
	/**
	 * 判断字符串参数是否有效，如果为compare，则为无效(FFFF)
	 * @return
	 */
	public static String setHexString(String hex,String compare){
		if(!hex.equals(compare)){
			return Converser.hexToString(hex);
		}else{
			return Constant.F4;
		}
	}
	
	public static String setHexToString(String hex,String compare){
		if(!hex.equals(compare)){
			return hex;
		}else{
			return Constant.F4;
		}
	}
	
	public static String setHexString(String hex,String compare,int times,double d){
		if(!hex.equals(compare)){
			return Converser.hexToString(hex,times,d);
		}else{
			return Constant.F4;
		}
	}
	
	public static String setHexString(String hex,String compare,int times){
		if(!hex.equals(compare)){
			return Converser.hexToString(hex,times);
		}else{
			return Constant.F4;
		}
	}
	
	public static String setHexDoubleString(String hex,String compare,int times){
		if(!hex.equals(compare)){
			return String.valueOf(Converser.hexTo2String(hex,times));
		}else{
			return Constant.F4;
		}
	}
	
	public static String setToString(String hex,String compare){
		if(!hex.equals(compare)){
			return hex;
		}else{
			return Constant.F4;
		}
	}
	
//	public static String accounthex(String hex){
//		StringBuffer sb = new StringBuffer();
//		for(int i=0;i<hex.length();i=i+2){
//			sb.append(Converser.hexToString(hex.substring(i,i+2)));
//		}
//		return sb.toString();
//	}
	
	/**
	 * 计算变速箱比
	 * 
	 * @param vin
	 * @param speeding
	 * @param engine_rotate_speed
	 * @return
	 */
	public static String accountRatio(String vin, String speeding,
			String engine_rotate_speed) {
		VehicleBean vb = vm.getValue(vin);
		// System.out.println(vb);
		String ratio;
		if (vb != null) {
			double d;
			if (vb.getTyre_r() != null && !vb.getTyre_r().equals("") && vb.getRear_axle_rate() != null && !vb.getRear_axle_rate().equals("")
					&& speeding != null && !speeding.equals("")
					&& engine_rotate_speed != null
					&& !engine_rotate_speed.equals("")&&!engine_rotate_speed.equals("FFFF")&&!speeding.equals("0.0")&&!speeding.equals("0")) {
				// 轮胎滚动半径
				double rk = Double.parseDouble(vb.getTyre_r())/1000;
				// 后桥速比
				double rar = Double.parseDouble(vb.getRear_axle_rate());
				d = (double)0.377 * rk * Double.parseDouble(engine_rotate_speed) / (rar *Double.parseDouble(speeding));
				BigDecimal bg = new BigDecimal(d);
//				double f1 = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
//				System.out.println(bg.setScale(3, BigDecimal.ROUND_HALF_UP).toString());
				ratio = ContentParamName.checkLength(bg.setScale(3, BigDecimal.ROUND_HALF_UP).toString(),ContentParamName.RATIO_BASE);
				return ratio;
			} else {
				return "-";
			}
		} else {
			return "-";
		}
	}

	/**
	 * 计算档位
	 * 
	 * @param vin
	 * @param speeding
	 * @param engine_rotate_speed
	 * @return
	 */
	public static String accountGears(String vin, String speeding,
			String engine_rotate_speed) {
		if (vin != null && !vin.equals("") && speeding != null
				&& !speeding.equals("") && engine_rotate_speed != null
				&& !engine_rotate_speed.equals("")
				&&accountRatio(vin, speeding,engine_rotate_speed)!=null
				&&!accountRatio(vin, speeding,engine_rotate_speed).equals("")
				&&!accountRatio(vin, speeding, engine_rotate_speed).equals("-")) {
			double ratio = Double.valueOf(accountRatio(vin, speeding,engine_rotate_speed));
			if (ratio < 7.3 && ratio > 6.8) {
				return GEARS1;
			} else if (ratio < 4.3 && ratio > 3.8) {
				return GEARS2;
			} else if (ratio < 2.7 && ratio > 2.1) {
				return GEARS3;
			} else if (ratio < 1.8 && ratio > 1.2) {
				return GEARS4;
			} else if (ratio < 1.1 && ratio > 0.9) {
				return GEARS5;
			} else if (ratio < 0.89 && ratio > 0.7) {
				return GEARS6;
			} else if(ratio<6.7 && ratio>6.1) {
				return GEARSR;
			}else {
				return GEARS0;
			}
		} else {
			return "-";
		}
	}
	/**
	 * 宇通杯二档起步告警
	 * @param speed
	 * @param gears
	 * @param hdf 
	 * @return
	 */
	public static String accountGear2_spdAlarm(String speed,String gears, HarmDefBean hdf){	
//		log.info("!!!!!!!!!!##@@#########accountGear2_spdAlarm1");
		if(hdf.getGear2_spd()!=null&&!hdf.getGear2_spd().equals("")){
//			log.info("!!!!!!!!!!##@@#########accountGear2_spdAlarm2");
			if(gears.equals(GEARS2)&&Double.parseDouble(speed)<Double.parseDouble(hdf.getGear2_spd())){
//				log.info("!!!!!!!!!!##@@#########accountGear2_spdAlarm3");
				return "1";
			}else{
//				log.info("!!!!!!!!!!##@@#########accountGear2_spdAlarm4");
				return "0";
			}
		}else{
//			log.info("!!!!!!!!!!##@@#########accountGear2_spdAlarm5");
			return "0";
		}
		
	}
	/**
	 * 空挡滑行
	 * @param speed
	 * @param gears
	 * @param hdf 
	 * @return
	 */
	public static String accountEgear_runAlarm(String speed,String gears, HarmDefBean hdf){
//		log.info("!!!!!!!!!!##@@#########accountEgear_runAlarm0");
		if(hdf.getEgear_spd()!=null&&!hdf.getEgear_spd().equals("")){
//			log.info("!!!!!!!!!!##@@#########accountEgear_runAlarm1");
			if(gears.equals(GEARS0)&&Double.parseDouble(speed)>Double.parseDouble(hdf.getEgear_spd())){
//				log.info("!!!!!!!!!!##@@#########accountEgear_runAlarm2");
				return "1";
			}else{
//				log.info("!!!!!!!!!!##@@#########accountEgear_runAlarme");
				return "0";
			}
		}else{
//			log.info("!!!!!!!!!!##@@#########accountEgear_runAlarme");
			return "0";
		}
	}
	
	public static String accountGear_unfitAlarm(String speed,String gears,HarmDefBean hdf){
		if(gears.equals(GEARS0)){
//			log.info("!!!!!!!!!!##@@#########0"+GEARS0);
			return accountGear_unfit(speed, hdf.getGear0_spd_l(), hdf.getGear0_spd_u());
		}else if(gears.equals(GEARS1)){
			//log.info("!!!!!!!!!!##@@#########1"+GEARS1);
			return accountGear_unfit(speed, hdf.getGear1_spd_l(), hdf.getGear1_spd_u());
		}else if(gears.equals(GEARS2)){
//			log.info("!!!!!!!!!!##@@#########2"+GEARS2);
			return accountGear_unfit(speed, hdf.getGear2_spd_l(), hdf.getGear2_spd_u());
		}else if(gears.equals(GEARS3)){
//			log.info("!!!!!!!!!!##@@#########3"+GEARS3);
			return accountGear_unfit(speed, hdf.getGear3_spd_l(), hdf.getGear3_spd_u());	
		}else if(gears.equals(GEARS4)){
//			log.info("!!!!!!!!!!##@@#########4"+GEARS4);
			return accountGear_unfit(speed, hdf.getGear4_spd_l(), hdf.getGear4_spd_u());
		}else if(gears.equals(GEARS5)){
//			log.info("!!!!!!!!!!##@@#########5"+GEARS5);
			return accountGear_unfit(speed, hdf.getGear5_spd_l(), hdf.getGear5_spd_u());
		}else if(gears.equals(GEARS6)){
//			log.info("!!!!!!!!!!##@@#########6"+GEARS6);
			return accountGear_unfit(speed, hdf.getGear6_spd_l(), hdf.getGear6_spd_u());
		}else if(gears.equals(GEARS7)){
			return accountGear_unfit(speed, hdf.getGear7_spd_l(), hdf.getGear7_spd_u());
		}else if(gears.equals(GEARS8)){
			return accountGear_unfit(speed, hdf.getGear8_spd_l(), hdf.getGear8_spd_u());
		}else{
//			log.info("!!!!!!!!!!##@@#########9"+GEARS6);
			return "0";
		}
	}
	
	public static String accountGear_unfit(String speed,String gear_l,String gear_u){
//		log.info("!!!!!!!!!!##@@@@#########0GEARS");
		if(gear_l!=null&&!gear_l.equals("")&&gear_u!=null&&!gear_u.equals("")){
//			log.info("!!!!!!!!!!##@@@@#########1GEARS");
			if(Double.parseDouble(speed)<Double.parseDouble(gear_l)||Double.parseDouble(speed)>Double.parseDouble(gear_u)){
//				log.info("!!!!!!!!!!##@@@@#########2GEARS");
				return "1";
			}else{
//				log.info("!!!!!!!!!!##@@@@#########3GEARS");
				return "0";
			}
		}else{
//			log.info("!!!!!!!!!!##@@@@#########3GEARS");
			return "0";
		}
	}
	
	public static String getServer_Ip(String value){
		int location = 0;
		StringBuffer sb = new StringBuffer();
		byte[] bytevalue = value.getBytes();
		byte[] first = new byte[2];
		System.arraycopy(bytevalue, location,first , 0, 2);
		location+=2;
		byte[] second = new byte[2];
		System.arraycopy(bytevalue, location, second, 0, 2);
		location+=2;
		byte[] third = new byte[2];
		System.arraycopy(bytevalue, location, third, 0, 2);
		location+=2;
		byte[] fourth = new byte[2];
		System.arraycopy(bytevalue, location, fourth, 0, 2);
		location+=2;
		sb.append(Converser.hexToString(new String(first)));
		sb.append(".");
		sb.append(Converser.hexToString(new String(second)));
		sb.append(".");
		sb.append(Converser.hexToString(new String(third)));
		sb.append(".");
		sb.append(Converser.hexToString(new String(fourth)));
		return sb.toString();
	}
	
	
	public static void main(String[] args){
		System.out.println(AccountUtil.accountLongLat("134684325"));
		System.out.println((double)100/6);
		
		System.out.println(accountTemp("0FF", "FFF"));
	}
	
}
