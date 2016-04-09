package com.pratiksha.cl3.assignment4;

import java.util.ArrayList;
import java.util.List;

public class ModularAirthmetic {
	public static int calulateGCD(int a, int b) {
		int remainder = 0;
		if (a == b) {
			return a;
		}
		if (a == 0 || b == 0) {
			if (a == 0) {
				return b;
			}
			if (b == 0) {
				return a;
			}
			if (a == 0 && b == 0) {
				return 0;
			}
		}
		if (a > 0 && b > 0) {
			if (b > a) {
				a = a + b;
				b = a - b;
				a = a - b;
			}
			remainder = ModularAirthmetic.mod(a, 1, b);
			return calulateGCD(b, remainder);
		}
		return -1;
	}

	public static int mod(int a, int b, int c) {
		// TODO Auto-generated method stub
		String binB = Integer.toBinaryString(b);
		int Bdigits = binB.length();
		List<Integer> AtoBmodC = new ArrayList<Integer>();
		for (int i = 0; i < Bdigits; i++) {
			AtoBmodC.add(1);
		}
		int power = 1;
		int product = 0;
		for (int i = 0; i < Bdigits; i++) {
			if (i == 0) {
				AtoBmodC.set(0, myMod(a, c));
			} else {
				AtoBmodC.set(i, myMod(AtoBmodC.get(i - 1) * AtoBmodC.get(i - 1), c));
			}
			if (binB.charAt(Bdigits - 1 - i) == '1') {
				if (product == 0) {
					product = AtoBmodC.get(i);
				} else {
					product *= AtoBmodC.get(i);
				}
				product = myMod(product, c);
			}
			power *= 2;
		}
		int result = myMod(product, c);
		return result;
	}

	public static int myMod(int a, int b) {
		// TODO Auto-generated method stub
		return (int) (a - Math.floor(a / b) * b);
	}

	public static int modInverse(int a, int m) {
		int a1 = 1;
		int a2 = 0;
		int a3 = a;
		int b1 = 0;
		int b2 = 1;
		int b3 = m;
		int q = 0;
		if (calulateGCD(a, m) == 1) {
			while (b3 != 0) {

				//System.out.println(q + " " + a1 + " " + a2 + " " + a3 + " " + b1 + " " + b2 + " " + b3);
				q = a3 / b3;
				int temp1 = a1 - (q * (b1));
				int temp2 = a2 - (q * (b2));
				int temp3 = a3 - (q * (b3));
				a1 = b1;
				a2 = b2;
				a3 = b3;
				b1 = temp1;
				b2 = temp2;
				b3 = temp3;
			}
		} else {
			System.out.println("Numbers are not co-prime");
			return 0;
		}
		if (a2 > b2) {
			return a2;
		} else {
			if (a2 < 0) {
				return b2 + a2;
			} else {
				return b2 - a2;
			}

		}

	}

	public static void main(String[] args) {
		System.out.println(calulateGCD(26, 7));
		System.out.println(mod(26, 1, 7));
		System.out.println(modInverse(11, 77));
	}
}
