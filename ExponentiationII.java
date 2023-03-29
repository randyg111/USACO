// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class ExponentiationII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			long exp = mod(b, c, (long) 1e9 + 6);
			pw.println(mod(a, exp, (long) 1e9 + 7));
		}
		
		pw.close();
	}
	public static long mod(long x, long n, long m) {
		x %= m;
		long r = 1;
		while(n > 0) {
			if(n%2 == 1) r = r * x % m;
			x = x * x % m;
			n /= 2;
		}
		return r;
	}
}
