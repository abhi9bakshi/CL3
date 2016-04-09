package com.pratiksha.cl3.assignment4;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.pratiksha.cl3.assignment2.RSA;

public class DSA {

	public static int l;
	public static int n;
	public static int p;
	public static int q;
	public static int g;
	public static int h;
	public static int k;
	public static int r;
	public static int x;
	public static int y;
	public static int s;

	public static String message = "10";
	static Random random = new Random();

	public static void parameterGeneration() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println("Generation");
		q = Utility.showRandomInteger(1, 1000, random);
		while (true) {
			if (MillerRabin.test(q) == true) {
				break;
			} else {
				q = RSA.generatePrime();
			}
		}
		int count = 0;
		for (int i = 0; i < 50; i++) {
			if (MillerRabin.test(q)) {
				count++;
			} else {
				continue;
			}
		}

		if (count > 5) {
			if (Utility.isPrime(q)) {
				// p = getP(q);
				// h = Utility.showRandomInteger(1, q - 1, random);
				// g = ModularAirthmetic.mod(h, ((p - 1) / q), p);
				p = 1279;
				q = 71;
				g = 1157;
				System.out.println("Parameters : ");
				System.out.println("p : " + p);
				System.out.println("q : " + q);
				System.out.println("g : " + g);
				signing();
			}
		} else {
			// parameterGeneration();
		}

	}

	public static void signing() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		System.out.println("\nSigning");
		k = Utility.showRandomInteger(1, q - 1, random);
		k = 10;
		r = ModularAirthmetic.mod(ModularAirthmetic.mod(g, k, p), 1, q);
		while (r == 0) {
			// k = Utility.showRandomInteger(1, q - 1, random);
			k = 10;
			r = ModularAirthmetic.mod(ModularAirthmetic.mod(g, k, p), 1, q);

		}
		int temp = ModularAirthmetic.modInverse(q, k);
		x = Utility.showRandomInteger(1, q - 1, random);
		x = 15;
		y = ModularAirthmetic.mod(g, x, p);
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

		int temp1 = hashedBytes.length;
		temp1 = 123;
		int temp2 = r * x;
		int temp3 = temp * (temp1 + temp2);
		s = ModularAirthmetic.mod(temp3, 1, q);
		while (s == 0) {
			// k = Utility.showRandomInteger(1, q - 1, random);
			k = 10;
			r = ModularAirthmetic.mod(ModularAirthmetic.mod(g, k, p), 1, q);
			while (r == 0) {
				// k = Utility.showRandomInteger(1, q - 1, random);
				k = 10;
				r = ModularAirthmetic.mod(ModularAirthmetic.mod(g, k, p), 1, q);

			}
			temp = ModularAirthmetic.modInverse(q, k);
			x = Utility.showRandomInteger(1, q - 1, random);
			y = ModularAirthmetic.mod(g, x, p);
			digest = MessageDigest.getInstance("MD5");
			hashedBytes = digest.digest(message.getBytes("UTF-8"));

			temp1 = hashedBytes.length;
			temp1 = 123;
			temp2 = r * x;
			temp3 = temp * (temp1 + temp2);
			s = ModularAirthmetic.mod(temp3, 1, q);
		}
		System.out.println("r : " + r);
		System.out.println("s : " + s);
		verification();
	}

	private static void verification() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		System.out.println("\nVerification");
		if (!(r < 0 || r < q)) {
			System.out.println("Rejected");
		}
		if (!(s < 0 || s < q)) {
			System.out.println("Rejected");
		}
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

		int temp1 = hashedBytes.length;
		temp1 = 123;
		int w = ModularAirthmetic.modInverse(q, s);
		// System.out.println(w);
		int u1 = ModularAirthmetic.mod((temp1 * w), 1, q);

		int u2 = ModularAirthmetic.mod((r * w), 1, q);

		double temp2 = ModularAirthmetic.mod(g, u1, p);

		double temp3 = ModularAirthmetic.mod(y, u2, p);

		int temp4 = (int) (temp2 * temp3);
		int v = ModularAirthmetic.mod(ModularAirthmetic.mod(temp4, 1, p), 1, q);
		// System.out.println(v);
		if (v == r) {
			System.out.println("\n*****Signature Verified*****");
		}
	}

	public static void main(String[] args)
			throws NumberFormatException, NoSuchAlgorithmException, UnsupportedEncodingException {
		parameterGeneration();
	}

	private static int getP(int q) {
		// TODO Auto-generated method stub
		int mod = 1;
		List<Integer> multiples = new ArrayList<Integer>();
		for (int i = 2; i < 50; i++) {
			multiples.add(i * q);
		}
		for (Integer integer : multiples) {
			// System.out.println(integer);
		}
		Random random = new Random();
		int p = 0;
		p = multiples.get(new Random().nextInt(multiples.size())) + 1;
		mod = ModularAirthmetic.mod(p - 1, 1, q);
		return p;
	}

	public static int hex2decimal(String s) {
		String digits = "0123456789ABCDEF";
		s = s.toUpperCase();
		int val = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int d = digits.indexOf(c);
			val = 16 * val + d;
		}
		return val;
	}

}
