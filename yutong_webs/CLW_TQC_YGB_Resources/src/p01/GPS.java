package p01;

public class GPS {
	
	public static final int longitude_meters = 92;
	public static final int latitude_meters = 110;
	
	public static void main(String[] args) {
		Double longitude1 = 116.380122;
		Double latitude1 = 39.914217;
		Double longitude2 = 116.546273;
		Double latitude2 = 39.99;

		Double dis_long = (longitude2 - longitude1) * longitude_meters;
		Double dis_lati = (latitude2 - latitude1) * latitude_meters;
		
		System.out.println(Math.atan(dis_lati/dis_long) * 180 / Math.PI);
	}
}
