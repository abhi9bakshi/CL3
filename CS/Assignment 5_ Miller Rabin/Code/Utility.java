package com.pratiksha.cl3.assignment4;

import java.util.Random;

public class Utility {
	public static boolean isPrime(int inputNum) {
		if (inputNum <= 3 || inputNum % 2 == 0)
			return inputNum == 2 || inputNum == 3;

		int divisor = 3;
		while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0))
			divisor += 2;
		return inputNum % divisor != 0;
	}

	public static boolean checkPrime(int n) {
		for (int i = 1; i <= (int) Math.sqrt(n); i++) {
			if (ModularAirthmetic.mod(n, 1, i) == 0) {
				return false;
			} else {
				continue;
			}
		}
		return true;
	}

	public static boolean check(int s, int d) {
		// TODO Auto-generated method stub
		if (d % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static int showRandomInteger(int aStart, int aEnd, Random aRandom) {
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		long range = (long) aEnd - (long) aStart + 1;
		long fraction = (long) (range * aRandom.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}

	public static void main(String[] args) {
		System.out.println(Utility.isPrime(8));
	}
}
