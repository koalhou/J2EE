package com.neusoft.smsplatform.message.inside.msg.utils;



public final class SmsHexDumper {
    private SmsHexDumper() {
    }

    private static final byte[] highDigits;

    private static final byte[] lowDigits;

    // initialize lookup tables
    static {
        final byte[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
                'D', 'E', 'F' };

        int i;
        byte[] high = new byte[256];
        byte[] low = new byte[256];

        for (i = 0; i < 256; i++) {
            high[i] = digits[i >>> 4];
            low[i] = digits[i & 0x0F];
        }

        highDigits = high;
        lowDigits = low;
    }

    public static String getHexdump(String in) {
        return getHexdump(in.getBytes());
    }

    public static String getHexdump(byte[] bb) {

        StringBuffer out = new StringBuffer(bb.length * 2);

        // fill the first
        int byteValue = bb[0] & 0xFF;
        out.append((char) highDigits[byteValue]);
        out.append((char) lowDigits[byteValue]);
        int size = bb.length;
        size--;

        // and the others, too
        for (; size > 0; size--) {
            byteValue = bb[bb.length - size] & 0xFF;
            out.append((char) highDigits[byteValue]);
            out.append((char) lowDigits[byteValue]);
        }
        return out.toString();
    }

    public static byte[] hexStringToByte(String hex) {
        if (null == hex) {
            return null;
        }
        hex = hex.toUpperCase();
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }
    public static void main(String[] args) {
        byte[] b = {0, 0, 1, 0, 36, 49, 48, 48, 54, 49, 50, 48, 54, 51, 51, 48, 57, 0, 0, 4, 0, 2, 48};
        System.out.println("-----"+SmsHexDumper.getHexdump(b));
    }
}
