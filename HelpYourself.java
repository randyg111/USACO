// // Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class HelpYourself {
	public static final long MOD = (long) 1e9 + 7;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] range = new int[2*n+2];
		int[] left = new int[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			left[i] = l;
			range[l]++;
			range[r]--;
		}
		long[] pow2 = new long[n];
		pow2[0] = 1;
		for(int i = 1; i < n; i++) {
			pow2[i] = pow2[i-1] * 2 % MOD;
		}
		for(int i = 1; i <= 2*n; i++) {
			range[i] += range[i-1];
		}
		long comp = 0;
		for(int l : left) {
			comp += pow2[n-1-range[l-1]];
			comp %= MOD;
		}
		pw.println(comp);
		
		pw.close();
	}

}

// LEGACY CODE

// Source: https://usaco.guide/general/io

// import java.io.*;
// import java.util.StringTokenizer;

// public class HelpYourself {
// 	public static final long MOD = (long) 1e9 + 7;
// 	public static void main(String[] args) throws IOException {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		PrintWriter pw = new PrintWriter(System.out);

// 		StringTokenizer st = new StringTokenizer(br.readLine());
// 		int n = Integer.parseInt(st.nextToken());
// 		int[] range = new int[2*n+1];
// 		int[] intersect = new int[n+1];
// 		for(int i = 0; i < n; i++) {
// 			st = new StringTokenizer(br.readLine());
// 			int l = Integer.parseInt(st.nextToken());
// 			int r = Integer.parseInt(st.nextToken());
// 			range[l]++;
// 			range[r]--;
// 		}
// 		for(int i = 1; i <= 2*n; i++) {
// 			if(range[i] == 1) {
// 				intersect[range[i-1]+1]++;
// 			}
// 			range[i] = range[i-1] + range[i];
// 		}
// 		long[] fac = new long[n+1];
// 		fac[0] = 1;
// 		for(int i = 1; i <= n; i++) {
// 			fac[i] = fac[i-1] * i % MOD;
// 		}
// 		long[] combo = new long[n+1];
// 		for(int i = 0; i <= n; i++) {
// 			combo[i] = fac[n] * pow(fac[i], MOD-2) % MOD * pow(fac[n-i], MOD-2) % MOD;
// 		}
// 		long sum = 0;
// 		for(int i = 1; i <= n; i++) {
// 			sum += i * combo[i];
// 			sum %= MOD;
// 		}
// 		for(int i = 2; i <= n; i++) {
// 			if(i % 2 == 0) {
// 				sum -= (n-i+1)*intersect[i];
// 			} else {
// 				sum += (n-i+1)*intersect[i];
// 			}
// 			sum %= MOD;
// 		}
// 		pw.println(sum);
		
// 		pw.close();
// 	}

// 	public static long pow(long x, long n) {
// 		x %= MOD;
// 		long r = 1;
// 		while(n > 0) {
// 			if(n%2 == 1) r = r * x % MOD;
// 			x = x * x % MOD;
// 			n /= 2;
// 		}
// 		return r;
// 	}
// }

