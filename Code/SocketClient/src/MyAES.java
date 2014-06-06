/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-6
 * Project  : SocketServer
 * Filename : MyAES.java
 * 
 * All rights reserved.
 */
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyAES {
	public static String encrypt(String content, String password) {
		String result = null;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = content.getBytes("UTF-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] resultByte = cipher.doFinal(byteContent);
			result = parseByte2HexStr(resultByte);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String decrypt(String content, String password) {
		String result = null;
		try {
			byte[] data = parseHexStr2Byte(content);
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] resultByte = cipher.doFinal(data);
			result = new String(resultByte, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
