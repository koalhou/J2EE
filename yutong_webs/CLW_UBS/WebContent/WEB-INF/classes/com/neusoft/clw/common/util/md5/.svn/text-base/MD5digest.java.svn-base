package com.neusoft.clw.common.util.md5;

import java.security.MessageDigest;

public final class MD5digest {

    private static final int HEX = 16;

    private static final int ADD_NUMBER = 256;

    // 十六进制下数字到字符的映射数组
    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private MD5digest() {

    }

    /** * 把inputString加密 */
    public static String generatePassword(String inputString) {
        return encodeByMD5(inputString);
    }

    /**
     * 验证输入的密码是否正确
     * @param password 加密后的密码
     * @param inputString 输入的字符串
     * @return 验证结果，TRUE:正确 FALSE:错误
     */
    public static boolean validatePassword(String password, String inputString) {
        // if (password.equals(encodeByMD5(inputString))) {
        // return true;
        // } else {
        // return false;
        // }
        return (password.equals(encodeByMD5(inputString))) ? true : false;
    }

    /** 对字符串进行MD5加密 */
    private static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                // 创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                // 将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 转换字节数组为十六进制字符串
     * @param 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /** 将一个字节转化成十六进制形式的字符串 */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = ADD_NUMBER + n;
        }
        int d1 = n / HEX;
        int d2 = n % HEX;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    public static void main(String[] args) {
        String pwd1 = "admin";
        String pwd2 = "";
        // MD5digest cipher = new MD5digest();
        System.out.println("未加密的密码:" + pwd1);
        // 将123加密
        pwd2 = MD5digest.generatePassword(pwd1);
        System.out.println("加密后的密码:" + pwd2);

        System.out.print("验证密码是否下确:");
        if (MD5digest.validatePassword(pwd2, pwd1)) {
            System.out.println("正确");
        } else {
            System.out.println("错误");
        }
    }
}
