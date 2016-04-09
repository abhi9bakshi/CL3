/**
 * 
 */
package com.pratiksha.cl3.assignment1;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author rohan
 *
 */
public class MD5Calculation {

	public static String convertByteArrayToHexString(byte[] arrayBytes) {

		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

	@SuppressWarnings("unused")
	public static String encrypt(String message, String algorithm)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest digest = MessageDigest.getInstance(algorithm);
		byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
		
		return convertByteArrayToHexString(hashedBytes);

	}
	
	public static String encrypt(String message, String algorithm, String custom)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		message = message+custom;
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
		
		return convertByteArrayToHexString(hashedBytes);

	}
}
