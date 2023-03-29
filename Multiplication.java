// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class Multiplication {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		pw.println(multiply(a, b));

		pw.close();
	}
	public static int multiply(int a, int b) {
		int product = 0;
		int bit = 0;
		while(1 << bit <= b) {
			if((1 << bit & b) > 0) product += a << bit;
			bit++;
		}
		return product;
	}
}
