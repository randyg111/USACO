// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class Division {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		pw.println(divide(a, b));
		
		pw.close();
	}
	public static int divide(int dividend, int divisor) {
		int bit = 1;
		int quotient = 0;
		while(divisor << 1 <= dividend) {
			divisor <<= 1;
			bit <<= 1;
		}
		while(bit > 0) {
			if(dividend >= divisor) {
				dividend -= divisor;
				quotient += bit;
			}
			divisor >>= 1;
			bit >>= 1;
		}
		return quotient;
	}
}
