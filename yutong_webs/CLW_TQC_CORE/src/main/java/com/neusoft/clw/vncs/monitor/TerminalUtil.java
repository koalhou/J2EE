package com.neusoft.clw.vncs.monitor;

import sun.misc.BASE64Decoder;
public class TerminalUtil {
  public TerminalUtil() {
  }

  /**
   * 货运通校验和函数 返回计算后的数
   * @param 校验和 String
   * @return 16进制 String 
   */
  public static String   VERIFYCODE(String ve){
//		String send = "4C54:8613910127452|1| ";
	   String send ;
	   send=ve;
		int sum = 0;
		for (int i = 0; i < send.length(); i++) {
//			System.out.println(send.substring(i, i + 1) + "-->asc=="
//					+ (int) send.charAt(i));
			sum += (int) send.charAt(i);
		}
		;
		
//		System.out.println("sum:" + sum);
//		System.out.println("16sum:" + Integer.toHexString(sum));
	     return  Integer.toHexString(sum);
	   }
  /**
   * 货运通校验和函数 返回计算后的数
   * @param 校验和 String
   * @return 16进制 String 
   */
  public static String   VERIFYCODE(byte ve[],int len){
//		String send = "4C54:8613910127452|1| ";
	  String a;
	  int d,sum = 0;
		for (int i = 0; i <len; i++) {
			a=Integer.toBinaryString(ve[i]);
			if(a.length()>8)
				a=a.substring(a.length()-8);
			d= Integer.parseInt(a, 2); 
			sum += d;
		}
		
//		System.out.println("sum:" + sum);
//		System.out.println("16sum:" + Integer.toHexString(sum));
	     return  Integer.toHexString(sum);
	   }
  
  /**
   * 货运通校验 函数 验证校验包是否正确。
   * @param 校验和 String
   * @return 布尔值 
   */
  public static boolean   Check_VE(byte[] inbuf){
		String sInInfo="";
		String[] sArrInfo;
		sInInfo=new String(inbuf,0,inbuf.length-2);
		sArrInfo=sInInfo.split(" "); 
//		Log.out( "sArrInfovvvvv:"+sArrInfo[sArrInfo.length-1]+"ssssssssssss");
		sInInfo=new String(inbuf,4,inbuf.length-(4+2+sArrInfo[sArrInfo.length-1].length())); 
//		Log.out("check_ve:"+sInInfo+"sArrInfo[sArrInfo.length-1]"+sArrInfo[sArrInfo.length-1]);
		int sum = 0;
		for (int i = 0; i < sInInfo.length(); i++) {
			sum += (int) sInInfo.charAt(i);
		}
		 
		if (Integer.toHexString(sum).toUpperCase().equals(sArrInfo[sArrInfo.length-1].toUpperCase()))
			{
//				Log.out("check_ve:  true");
				return true;
			}else{
//				Log.out("check_ve:  false");
				return false; 
			} 
	   }
  
  
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
  

  public static int crc_32_tab[] = { //CRC polynomial 0xedb88320
	  0x00000000, 0x77073096, 0xee0e612c, 0x990951ba, 0x076dc419, 0x706af48f,
	  0xe963a535, 0x9e6495a3, 0x0edb8832, 0x79dcb8a4, 0xe0d5e91e, 0x97d2d988,
	  0x09b64c2b, 0x7eb17cbd, 0xe7b82d07, 0x90bf1d91, 0x1db71064, 0x6ab020f2,
	  0xf3b97148, 0x84be41de, 0x1adad47d, 0x6ddde4eb, 0xf4d4b551, 0x83d385c7,
	  0x136c9856, 0x646ba8c0, 0xfd62f97a, 0x8a65c9ec, 0x14015c4f, 0x63066cd9,
	  0xfa0f3d63, 0x8d080df5, 0x3b6e20c8, 0x4c69105e, 0xd56041e4, 0xa2677172,
	  0x3c03e4d1, 0x4b04d447, 0xd20d85fd, 0xa50ab56b, 0x35b5a8fa, 0x42b2986c,
	  0xdbbbc9d6, 0xacbcf940, 0x32d86ce3, 0x45df5c75, 0xdcd60dcf, 0xabd13d59,
	  0x26d930ac, 0x51de003a, 0xc8d75180, 0xbfd06116, 0x21b4f4b5, 0x56b3c423,
	  0xcfba9599, 0xb8bda50f, 0x2802b89e, 0x5f058808, 0xc60cd9b2, 0xb10be924,
	  0x2f6f7c87, 0x58684c11, 0xc1611dab, 0xb6662d3d, 0x76dc4190, 0x01db7106,
	  0x98d220bc, 0xefd5102a, 0x71b18589, 0x06b6b51f, 0x9fbfe4a5, 0xe8b8d433,
	  0x7807c9a2, 0x0f00f934, 0x9609a88e, 0xe10e9818, 0x7f6a0dbb, 0x086d3d2d,
	  0x91646c97, 0xe6635c01, 0x6b6b51f4, 0x1c6c6162, 0x856530d8, 0xf262004e,
	  0x6c0695ed, 0x1b01a57b, 0x8208f4c1, 0xf50fc457, 0x65b0d9c6, 0x12b7e950,
	  0x8bbeb8ea, 0xfcb9887c, 0x62dd1ddf, 0x15da2d49, 0x8cd37cf3, 0xfbd44c65,
	  0x4db26158, 0x3ab551ce, 0xa3bc0074, 0xd4bb30e2, 0x4adfa541, 0x3dd895d7,
	  0xa4d1c46d, 0xd3d6f4fb, 0x4369e96a, 0x346ed9fc, 0xad678846, 0xda60b8d0,
	  0x44042d73, 0x33031de5, 0xaa0a4c5f, 0xdd0d7cc9, 0x5005713c, 0x270241aa,
	  0xbe0b1010, 0xc90c2086, 0x5768b525, 0x206f85b3, 0xb966d409, 0xce61e49f,
	  0x5edef90e, 0x29d9c998, 0xb0d09822, 0xc7d7a8b4, 0x59b33d17, 0x2eb40d81,
	  0xb7bd5c3b, 0xc0ba6cad, 0xedb88320, 0x9abfb3b6, 0x03b6e20c, 0x74b1d29a,
	  0xead54739, 0x9dd277af, 0x04db2615, 0x73dc1683, 0xe3630b12, 0x94643b84,
	  0x0d6d6a3e, 0x7a6a5aa8, 0xe40ecf0b, 0x9309ff9d, 0x0a00ae27, 0x7d079eb1,
	  0xf00f9344, 0x8708a3d2, 0x1e01f268, 0x6906c2fe, 0xf762575d, 0x806567cb,
	  0x196c3671, 0x6e6b06e7, 0xfed41b76, 0x89d32be0, 0x10da7a5a, 0x67dd4acc,
	  0xf9b9df6f, 0x8ebeeff9, 0x17b7be43, 0x60b08ed5, 0xd6d6a3e8, 0xa1d1937e,
	  0x38d8c2c4, 0x4fdff252, 0xd1bb67f1, 0xa6bc5767, 0x3fb506dd, 0x48b2364b,
	  0xd80d2bda, 0xaf0a1b4c, 0x36034af6, 0x41047a60, 0xdf60efc3, 0xa867df55,
	  0x316e8eef, 0x4669be79, 0xcb61b38c, 0xbc66831a, 0x256fd2a0, 0x5268e236,
	  0xcc0c7795, 0xbb0b4703, 0x220216b9, 0x5505262f, 0xc5ba3bbe, 0xb2bd0b28,
	  0x2bb45a92, 0x5cb36a04, 0xc2d7ffa7, 0xb5d0cf31, 0x2cd99e8b, 0x5bdeae1d,
	  0x9b64c2b0, 0xec63f226, 0x756aa39c, 0x026d930a, 0x9c0906a9, 0xeb0e363f,
	  0x72076785, 0x05005713, 0x95bf4a82, 0xe2b87a14, 0x7bb12bae, 0x0cb61b38,
	  0x92d28e9b, 0xe5d5be0d, 0x7cdcefb7, 0x0bdbdf21, 0x86d3d2d4, 0xf1d4e242,
	  0x68ddb3f8, 0x1fda836e, 0x81be16cd, 0xf6b9265b, 0x6fb077e1, 0x18b74777,
	  0x88085ae6, 0xff0f6a70, 0x66063bca, 0x11010b5c, 0x8f659eff, 0xf862ae69,
	  0x616bffd3, 0x166ccf45, 0xa00ae278, 0xd70dd2ee, 0x4e048354, 0x3903b3c2,
	  0xa7672661, 0xd06016f7, 0x4969474d, 0x3e6e77db, 0xaed16a4a, 0xd9d65adc,
	  0x40df0b66, 0x37d83bf0, 0xa9bcae53, 0xdebb9ec5, 0x47b2cf7f, 0x30b5ffe9,
	  0xbdbdf21c, 0xcabac28a, 0x53b39330, 0x24b4a3a6, 0xbad03605, 0xcdd70693,
	  0x54de5729, 0x23d967bf, 0xb3667a2e, 0xc4614ab8, 0x5d681b02, 0x2a6f2b94,
	  0xb40bbe37, 0xc30c8ea1, 0x5a05df1b, 0x2d02ef8d
  };
  public static String  CreateCRC32(byte[] databuf, int len)
  {
	  byte[] bytes = databuf; 
	  int crc = 0xffffffff; 
	  for   (int i=0;i<len;++i)   { 
	  crc = (crc >>>8^ crc_32_tab[(crc ^ bytes[i]) & 0xff]); 
	  } 
	  crc = crc ^ 0xffffffff; 

      return Integer.toHexString(crc);
  }
 

  public static String CreateCRC32Ut(byte[] databuf,int len)
{
 
		char ut_32_crc[][] = new char[TerminalUtil.crc_32_tab.length][32];
		String str2;
		long crc_l;
		for (int i = 0; i < TerminalUtil.crc_32_tab.length; i++) {
			crc_l = TerminalUtil.crc_32_tab[i] & 0xffffffff;
			crc_l = crc_l & 0xffffffff;
			str2 = Long.toBinaryString(crc_l);
			str2 = TerminalUtil.PrefixS("0", 64, str2);
			ut_32_crc[i] = str2.substring(str2.length() - 32, str2.length())
					.toCharArray();
		}

		char t_c32[] = new char[32];
		char oldcrc32_c32[] = new char[32];
		char crc32_c32[] = new char[32];
		char oldcrc_c32[] = new char[32];
		char c_c8[] = new char[8];
		byte c;
		int charcnt;
		str2 = TerminalUtil.PrefixS("0", 64, "0");
		oldcrc32_c32 = str2.substring(str2.length() - 32, str2.length())
				.toCharArray();
		charcnt = 0;
		int id;
		for (int j = 0; j < len; j++) {
			t_c32 = (crc32_mv_right(oldcrc32_c32, 24));
			id = Integer.valueOf(String.valueOf(t_c32), 2).intValue();
			oldcrc_c32 = ut_32_crc[id];
			c = databuf[charcnt];
			str2 = Integer.toBinaryString(c);
			str2 = TerminalUtil.PrefixS("0", 32, str2);
			// System.out.println(str2);
			c_c8 = str2.substring(str2.length() - 8, str2.length())
					.toCharArray();
			for (int i = 0; i < 32; i++) {
				if (i >= 32 - 8)
					t_c32[i] = c_c8[i - 24];
				else
					t_c32[i] = '0';
			}
			oldcrc32_c32 = crc32_or_1(crc32_mv_lift(oldcrc32_c32, 8), t_c32);
			oldcrc32_c32 = crc32_or(oldcrc32_c32, oldcrc_c32);

			charcnt++;
		}
		//		   
		crc32_c32 = oldcrc32_c32;
//		System.out.println(Long.valueOf(String.valueOf(crc32_c32), 2));
		return Long.toHexString(Long.valueOf(String.valueOf(crc32_c32), 2).longValue());
}
  public static char[] crc32_or(char a[], char b[]) {
		char t[] = new char[a.length];
		int ia, ib;
		for (int i = 0; i < a.length; i++) {
			ia = Integer.valueOf(String.valueOf(a[i])).intValue();
			ib = Integer.valueOf(String.valueOf(b[i])).intValue();
			;

			if (ia + ib == 2) {
				t[i] = '0';
			}
			if (ia + ib == 1) {
				t[i] = '1';
			}
			if (ia + ib == 0) {
				t[i] = '0';
			}
		}
		return t;
	}

	public static char[] crc32_or_1(char a[], char b[]) {
		char t[] = new char[a.length];
		int ia, ib;
		for (int i = 0; i < a.length; i++) {
			ia = Integer.valueOf(String.valueOf(a[i])).intValue();
			ib = Integer.valueOf(String.valueOf(b[i])).intValue();
			;

			if (ia + ib == 2) {
				t[i] = '1';
			}
			if (ia + ib == 1) {
				t[i] = '1';
			}
			if (ia + ib == 0) {
				t[i] = '0';
			}
		}
		return t;
	}

	public static char[] crc32_mv_lift(char a[], int bt) {
		char t[] = new char[a.length];
		for (int i = 0; i < a.length; i++) {
			if (i < a.length - bt)
				t[i] = a[i + bt];
			else
				t[i] = '0';
		}
		a = t;
		return t;

	}

	public static char[] crc32_mv_right(char a[], int bt) {
		char t[] = new char[a.length];
		for (int i = 0; i < a.length; i++) {
			if (i < bt)
				t[i] = '0';
			else
				t[i] = a[i - bt];
		}
		a = t;
		return t;

	}
	

	// 将 s 进行 BASE64 编码
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}

	public static String gbEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		System.out.println("unicodeBytes is: " + unicodeBytes);
		return unicodeBytes;
	}

	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}
}
