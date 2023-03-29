// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class DivisorAnalysis {
	public static final long MOD = (long) 1e9 + 7;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		long[][] pf = new long[n][2];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			long x = Long.parseLong(st.nextToken());
			long k = Long.parseLong(st.nextToken());
			pf[i][0] = x;
			pf[i][1] = k;
		}
		pw.println(num(pf)+" "+sum(pf)+" "+prod(pf));
		
		pw.close();
	}

	public static long num(long[][] pf) {
		long n = 1;
		for(int i = 0; i < pf.length; i++) {
			n = n * (pf[i][1] + 1) % MOD;
		}
		return n;
	}

	public static long sum(long[][] pf) {
		long s = 1;
		for(int i = 0; i < pf.length; i++) {
			s = s * (pow(pf[i][0], pf[i][1]+1) - 1) % MOD * pow(pf[i][0]-1, MOD-2) % MOD;
		}
		return s;
	}

	public static long prod(long[][] pf) {
		long p = 1;
		for(int i = 0; i < pf.length; i++) {
			p = p * pow(pf[i][0], pf[i][1]) % MOD;
		}
		boolean square = true;
		for(int i = 0; i < pf.length; i++) {
			if(square && pf[i][1] % 2 == 1) {
				pf[i][1] /= 2;
				square = false;
			}
			p = pow(p, pf[i][1]+1);
		}
		if(square) {
			p = 1;
			for(int i = 0; i < pf.length; i++) {
				p = p * pow(pf[i][0], pf[i][1]/2) % MOD;
			}
			for(int i = 0; i < pf.length; i++) {
				p = pow(p, pf[i][1]+1);
			}
		}
		return p;
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
