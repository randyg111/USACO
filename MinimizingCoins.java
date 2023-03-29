// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class MinimizingCoins {
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
			int coin = Integer.parseInt(st.nextToken());
			coins[i] = coin;
		}
		dp[0] = 0;
		for(int i = 1; i <= x; i++) {
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < n; j++) {
				if(i-coins[j] >= 0 && dp[i-coins[j]] >= 0) {
					min = Math.min(min, dp[i-coins[j]]+1);
				}
			}
			if(min == Integer.MAX_VALUE) {
				dp[i] = -1;
			} else {
				dp[i] = min;
			}
		}
		System.out.println(dp[x]);
		
		pw.close();
	}
}
