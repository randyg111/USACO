// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class CoinCombinationsI {
	public static final long MOD = (long) 1e9 + 7;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int[] coins = new int[n];
		int[] dp = new int[x+1];
		st = new StringTokenizer(r.readLine());
		for(int i = 0; i < n; i++) {
			int c = Integer.parseInt(st.nextToken());
			if(c <= x) dp[c] = 1;
			coins[i] = c;
		}
		for(int i = 1; i <= x; i++) {
			for(int j = 0; j < n; j++) {
				if(i-coins[j] >= 0) {
					dp[i] += dp[i-coins[j]];
					dp[i] %= MOD;
				}
			}
		}
		System.out.println(dp[x]);

		pw.close();
	}
}
