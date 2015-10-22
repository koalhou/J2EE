
package com.yutong.axxc.parents.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class URLCoderUtil {

	public static String charset = "UTF-8";

	/**
	 * 批量URL转义
	 * 
	 * @param strings
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String[] decoder(String... strings) {
		String[] str = new String[strings.length];
		for (int i = 0; i < strings.length; i++) {
			try {
				str[i] = URLDecoder.decode(strings[i], charset);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return str;
	}
}
