package com.pratiksha.cl3.assignment4;

import java.util.Random;
import java.util.Scanner;

import com.pratiksha.cl3.assignment3.ModularAirthmetic;
import com.pratiksha.cl3.assignment3.Utility;

public class MillerRabin {
	public static boolean test(int number) {
		int start = 1;
		int end = number / 2;
		int s;
		int d;
		Random random = new Random();

		// s = showRandomInteger(start, end, random);
		d = Utility.showRandomInteger(start, end, random);

		s = (number - 1) / d;
		s = (int) (Math.log10(s) / Math.log10(2));
		if (!check(s, d)) {
			test(number);
		}
		int a = Utility.showRandomInteger(start, number - 1, random);
		if (ModularAirthmetic.mod(a, d, number) == 1) {
			return true;
		}
		for (int i = 0; i <= s - 1; i++) {
			if (ModularAirthmetic.mod(a, (ModularAirthmetic.mod(2, i, 1) * d), number) == (number - 1)) {
				return true;
			}
		}
		return false;

	}

	private static boolean check(int s, int d) {
		// TODO Auto-generated method stub

		if (d % 2 == 0) {
			return false;
		} else {
			return true;
		}

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Number to test : ");
		int number = input.nextInt();
		if (test(number)) {
			System.out.println("Inconclusive");
		} else {
			System.out.println("Composite");
			;
		}
	}
}
