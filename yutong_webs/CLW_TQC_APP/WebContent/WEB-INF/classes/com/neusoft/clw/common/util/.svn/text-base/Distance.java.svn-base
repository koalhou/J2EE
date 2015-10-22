package com.neusoft.clw.common.util;

public class Distance {

	private static final double EARTH_RADIUS = 6378137;

	public static final int longitude_meters = 92;
	public static final int latitude_meters = 110;
	
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double GetDistance(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	
	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static double GetDistance(String longitude1, String latitude1, String longitude2, String latitude2) {
		Double lng1 = Double.valueOf(longitude1);
		Double lat1 = Double.valueOf(latitude1);
		Double lng2 = Double.valueOf(longitude2);
		Double lat2 = Double.valueOf(latitude2);
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
	public static double GetAngle(double lng1, double lat1, double lng2, double lat2){
		Double dis_long = (lng1 - lng2) * longitude_meters;
		Double dis_lati = (lat1 - lat2) * latitude_meters;
		
		return Math.atan(dis_lati/dis_long) * 180 / Math.PI;
	}
	
	public static double GetAngle(String longitude1, String latitude1, String longitude2, String latitude2){
		Double lng1 = Double.valueOf(longitude1);
		Double lat1 = Double.valueOf(latitude1);
		Double lng2 = Double.valueOf(longitude2);
		Double lat2 = Double.valueOf(latitude2);
		
		Double dis_long = (lng1 - lng2) * longitude_meters;
		Double dis_lati = (lat1 - lat2) * latitude_meters;
		
		return Math.atan(dis_lati/dis_long) * 180 / Math.PI;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double distance = GetDistance(113.688727,34.704612,113.688871,34.689595);
		System.out.println("Distance is:" + distance);
	}

}
