/**
 ** Java Program to Implement Booth Algorithm
 **/

import java.util.Scanner;

/** Class Booth **/
public class Booth {
    private String resultString = "";
    private StringBuilder stringBuilder;
    public static Scanner s = new Scanner(System.in);

    /** Function to multiply **/
    public String multiply(int n1, int n2) {
        stringBuilder = new StringBuilder();
        int[] m = binary(n1);
        int[] m1 = binary(-n1);
        int[] r = binary(n2);
        int[] A = new int[9];
        int[] S = new int[9];
        int[] P = new int[9];
        for (int i = 0; i < 4; i++) {
            A[i] = m[i];
            S[i] = m1[i];
            P[i + 4] = r[i];
        }
        display(A, 'A');
        display(S, 'S');
        display(P, 'P');
        resultString += "\n";
        System.out.println();

        for (int i = 0; i < 4; i++) {
            if (P[7] == 0 && P[8] == 0)
                ;
            // do nothing
            else if (P[7] == 1 && P[8] == 0)
                add(P, S);
            else if (P[7] == 0 && P[8] == 1)
                add(P, A);
            else if (P[7] == 1 && P[8] == 1)
                ;
            // do nothing

            rightShift(P);
            display(P, 'P');
        }

        stringBuilder.append(getDecimal(P));
        // return stringBuilder.toString();
        resultString += "\n Result :  " + String.valueOf(getDecimal(P));
        System.out.println(resultString);
        String a = resultString;
        resultString = "";
        return a;
    }

    /** Function to get Decimal equivalent of P **/
    public int getDecimal(int[] B) {
        int p = 0;
        int t = 1;
        for (int i = 7; i >= 0; i--, t *= 2)
            p += (B[i] * t);
        if (p > 64)
            p = -(256 - p);
        return p;
    }

    /** Function to right shift array **/
    public void rightShift(int[] A) {
        for (int i = 8; i >= 1; i--)
            A[i] = A[i - 1];
    }

    /** Function to add two binary arrays **/
    public void add(int[] A, int[] B) {
        int carry = 0;
        for (int i = 8; i >= 0; i--) {
            int temp = A[i] + B[i] + carry;
            A[i] = temp % 2;
            carry = temp / 2;
        }
    }

    /** Function to get binary of a number **/
    public int[] binary(int n) {
        int[] bin = new int[4];
        int ctr = 3;
        int num = n;
        /** for negative numbers 2 complment **/
        if (n < 0)
            num = 16 + n;
        while (num != 0) {
            bin[ctr--] = num % 2;
            num /= 2;
        }
        return bin;
    }

    /** Function to print array **/
    public void display(int[] P, char ch) {
        resultString += "\n" + ch + " : ";
        System.out.print("\n" + ch + " : ");
        stringBuilder.append(ch + " : ");
        for (int i = 0; i < P.length; i++) {
            if (i == 4)
                resultString += " ";
            System.out.print(" ");
            stringBuilder.append(" ");
            if (i == 8)
                resultString += " ";
            System.out.print(" ");
            stringBuilder.append(" ");
            resultString += P[i];
            System.out.print(P[i]);
            stringBuilder.append(P[i]);
        }
    }
}