// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class CreatingStringsII {
	public static final long MOD = (long) 1e9 + 7;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int[] freq = new int[26];
		String s = r.readLine();
		for(int i = 0; i < s.length(); i++) {
			freq[s.charAt(i)-97]++;
		}
		long[] fac = new long[s.length()+1];
		fac[0] = 1;
		for(int i = 1; i <= s.length(); i++) {
			fac[i] = fac[i-1] * i % MOD;
		}
		long count = fac[s.length()];
		for(int i = 0; i < 26; i++) {
			count = count * pow(fac[freq[i]], MOD-2) % MOD;
		}
		pw.println(count);
		
		pw.close();
	}

	public static long pow(long x, long n) {
		x %= MOD;
		long r = 1;
		while(n > 0) {
			if(n%2 == 1) r = r * x % MOD;
			x = x * x % MOD;
			n /= 2;
		}
		return r;
	}
}
