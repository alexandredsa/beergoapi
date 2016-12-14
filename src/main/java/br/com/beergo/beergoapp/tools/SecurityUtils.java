package br.com.beergo.beergoapp.tools;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {
	public static String toMD5(String msg) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(msg.getBytes(), 0, msg.length());
		return new BigInteger(1, m.digest()).toString(16);
	}
}
