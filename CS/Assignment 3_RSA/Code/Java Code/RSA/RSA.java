package com.pratiksha.cl3.assignment2;

import java.util.Random;
import java.util.Scanner;

import javax.crypto.Cipher;

public class RSA {
	public static void RSA(int message) {
		int p;
		int q;
		int n;
		int m;
		int e = 0;
		int d = 0;
		Random random = new Random();

		p = generatePrime();
		q = generatePrime();
		System.out.println("P : " + p);
		System.out.println("Q : " + q);
		n = p * q;
		System.out.println(n);
		m = (p - 1) * (q - 1);
		e = generatePrime(m);
		while (!(ModularAirthmetic.calulateGCD(e, m) == 1)) {
			e = generatePrime(m);
		}

		if (ModularAirthmetic.calulateGCD(e, m) == 1)
			d = ModularAirthmetic.modInverse(m, e);

		int c = ModularAirthmetic.mod(message, e, n);
		int plain = ModularAirthmetic.mod(c, d, n);
		System.out.println("Cipher : " + c);
		System.out.println("Plaintext : " + plain);
	}

	private static int generatePrime(int m) {
		// TODO Auto-generated method stub
		int num = 0;
		Random rand = new Random(); // generate a random number
		num = rand.nextInt(m) + 1;

		while (!Utility.isPrime(num)) {
			num = rand.nextInt(1000) + 1;
		}
		return num;
	}

	public static int generatePrime() {
		// TODO Auto-generated method stub
		int num = 0;
		Random rand = new Random(); // generate a random number
		num = rand.nextInt(1000) + 1;

		while (!Utility.isPrime(num)) {
			num = rand.nextInt(1000) + 1;
		}
		return num;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Message : ");
		int msg = input.nextInt();

		RSA(msg);
	}
}
