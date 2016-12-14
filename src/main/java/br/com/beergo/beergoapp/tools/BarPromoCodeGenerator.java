package br.com.beergo.beergoapp.tools;

import java.util.Random;

public class BarPromoCodeGenerator {
	public static String generate() {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVXWYZ".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
}
