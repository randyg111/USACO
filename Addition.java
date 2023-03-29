// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class Addition {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		pw.println(add(a, b));
		
		pw.close();
	}
	public static int add(int a, int b) {
		while(b > 0) {
			int carry = a & b;
			a ^= b;
			b = carry << 1;
		}
		return a;

		// My bad solution:
		// int bit = 1;
		// int carry = 0;
		// int sum = 0;
		// while(a >= bit || b >= bit) {
		// 	int abit = a & bit;
		// 	int bbit = b & bit;
		// 	sum ^= abit ^ bbit ^ carry;
		// 	if(abit > 0 && bbit > 0 || abit > 0 && carry > 0 || bbit > 0 && carry > 0) {
		// 		carry = bit << 1;
		// 	} else {
		// 		carry = 0;
		// 	}
		// 	bit <<= 1;
		// }
		// sum ^= carry;
		// return sum;
	}
}
