package com.kinnong.common.utils;

import java.security.MessageDigest;

public class MD5Util {
	
    public static String MD5Encode(String origin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byteArrayToHexString(md.digest(origin.getBytes("UTF-8")));
        } catch (Exception exception) {
        }
        return "";
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString(byte b[]) {
	    StringBuffer resultSb = new StringBuffer();
	    for (int i = 0; i < b.length; i++)
	        resultSb.append(byteToHexString(b[i]));
	    return resultSb.toString();
	}
	
	private static String byteToHexString(byte b) {
	    int n = b;
	    if (n < 0)
	        n += 256;
	    int d1 = n / 16;
	    int d2 = n % 16;
	    return hexDigits[d1] + hexDigits[d2];
	}


	public static void main(String[] args) {
		String result = MD5Encode("123456");
		System.out.println(result);
	}
}
