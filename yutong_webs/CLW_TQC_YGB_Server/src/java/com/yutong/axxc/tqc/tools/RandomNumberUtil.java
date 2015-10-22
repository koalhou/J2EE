
package com.yutong.axxc.tqc.tools;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;


public class RandomNumberUtil {

	private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	/**
	 * 随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位)
	 * 
	 * @param digit
	 *            用户指定随机数据的位数
	 */
	public static long randomLong(final int digit) {
		if (digit >= 19 || digit <= 0) {
			throw new IllegalArgumentException(
					"digit should between 1 and 18(1<=digit<=18)");
		}
		final String s = RandomStringUtils.randomNumeric(digit - 1);
		return Long.parseLong(getPrefix() + s);
	}

	public static String randomString(final int digit) {
		if (digit >= 19 || digit <= 0) {
			throw new IllegalArgumentException(
					"digit should between 1 and 18(1<=digit<=18)");
		}
		return getPrefix() + RandomStringUtils.randomNumeric(digit - 1);
	}

	/**
	 * 随机产生在指定位数之间的long型数据,位数包括两边的值,minDigit<=maxDigit
	 * 
	 * @param minDigit
	 *            用户指定随机数据的最小位数 minDigit>=1
	 * @param maxDigit
	 *            用户指定随机数据的最大位数 maxDigit<=18
	 */
	public static long randomLong(final int minDigit, final int maxDigit) {
		if (minDigit > maxDigit) {
			throw new IllegalArgumentException("minDigit > maxDigit");
		}
		if (minDigit <= 0 || maxDigit >= 19) {
			throw new IllegalArgumentException("minDigit <=0 || maxDigit>=19");
		}
		return randomLong(minDigit + getDigit(maxDigit - minDigit));
	}

	private static int getDigit(final int max) {
		return RandomUtils.nextInt(max + 1);
	}

	/**
	 * 保证第一位不是零
	 * 
	 * @return
	 */
	private static String getPrefix() {
		return prefix[RandomUtils.nextInt(9)] + "";
	}

	public static void main(final String[] aa) {

		for (int i = 0; i < 100; i++) {
			System.out.println(RandomNumberUtil.randomString(10));
		}
	}
}
