package com.ghulam.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ghulam.customexception.HashGenerationException;

public class HashGenerator {
	private HashGenerator() {

	}

	public static String generateMD5(String message) throws HashGenerationException {
		return hashString(message, "MD5");
	}

	public static String generateSHA1(String message) throws HashGenerationException {
		return hashString(message, "SHA-1");
	}

	public static String generateSHA256(String message) throws HashGenerationException {
		return hashString(message, "SHA-256");
	}

	private static String hashString(String message, String algorithm) throws HashGenerationException {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
			return convertByteArrayToHexString(hashedBytes);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new HashGenerationException("Could not generate hash from String");
		}
	}

	private static String convertByteArrayToHexString(byte[] hashedBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (byte hashedByte : hashedBytes) {
			stringBuffer.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}
}
