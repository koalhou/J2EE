package com.neusoft.clw.util;

public class Util {
	  /**
	   * 将指定byte数组以16进制的形式打印到控制台
	   * @param hint String
	   * @param b byte[]
	   * @return void
	   */
	  public static void printHexString(String hint, byte[] b) {
	    System.out.print(hint);
	    for (int i = 0; i < b.length; i++) {
	      String hex = Integer.toHexString(b[i] & 0xFF);
	      if (hex.length() == 1) {
	        hex = '0' + hex;
	      }
	      System.out.print(hex.toUpperCase() + " ");
	    }
	    System.out.println("");
	  }

	  /**
	   *
	   * @param b byte[]
	   * @return String
	   */
	  public static String Bytes2HexString(byte[] b) {
	    String ret = "";
	    for (int i = 0; i < b.length; i++) {
	      String hex = Integer.toHexString(b[i] & 0xFF);
	      if (hex.length() == 1) {
	        hex = '0' + hex;
	      }
	      ret += hex.toUpperCase();
	    }
	    return ret;
	  }
	  
	  /**
	  *将byte数组指定开始和结束区间的数据转换成16进制string返回
	  * @param b byte[] ，begin，end (不包括最后一位)
	  * 
	  * @return String
	  */
	 public static String Bytes2HexString(byte[] b,int begin,int end) {
	   String ret = "";
	   for (int i = begin; i < end; i++) {
	     String hex = Integer.toHexString(b[i] & 0xFF);
	     if (hex.length() == 1) {
	       hex = '0' + hex;
	     }
	     ret += hex.toUpperCase();
	   }
	   return ret;
	 }

	  /**
	   * 将两个ASCII字符合成一个字节；
	   * 如："EF"--> 0xEF
	   * @param src0 byte
	   * @param src1 byte
	   * @return byte
	   */
	  public static byte uniteBytes(byte src0, byte src1) {
	    byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
	    _b0 = (byte)(_b0 << 4);
	    byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
	    byte ret = (byte)(_b0 ^ _b1);
	    return ret;
	  }

	  /**
	   * 将指定字符串src，以每两个字符分割转换为16进制形式
	   * 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9}
	   * @param src String
	   * @return byte[]
	   */
	  public static byte[] HexString2Bytes(String src ,int lenght){
	    byte[] ret = new byte[lenght];
	    byte[] tmp = src.getBytes();
	    for(int i=0; i<lenght; i++){
	      ret[i] = uniteBytes(tmp[i*2], tmp[i*2+1]);
	    }
	    return ret;
	  }
	  /**
	   * 将指定字符串src，左端补上传入字符，补到传入长度为止。
	   * 如："18" --> 00000018
	   * @param fix String,lenght int ,src String
	   * @return String
	   */
	  public static String PrefixS(String fix,int lenght,String src){
	    String ret = "";
	    
	    for(int i=0; i<lenght - src.length(); i++){
	    	ret = ret+fix;
	    }
	    return ret+src;
	  }
	  
	  /**
	   * 将指定字符串src，右端补上传入字符，补到传入长度为止。
	   * 如："18" --> 18000000
	   * @param fix String,lenght int ,src String
	   * @return String
	   */
	  public static String PostfixS(String fix,int lenght,String src){
	    String ret = "";
	    
	    for(int i=0; i<lenght - src.length(); i++){
	    	ret = ret+fix;
	    }
	    return src+ret;
	  }
	  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
