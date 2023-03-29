// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class TimeIsMooney {
	public static final int MAX = 1001;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[][] dp = new int[MAX+1][n+1];
		int[] mos = new int[n+1];
		int[] cost = new int[MAX+1];
		int visits = 0;
		for(int i = 1; i <= MAX; i++) {
			cost[i] = c * i * i;
		}
		ArrayList<Integer>[] adj = new ArrayList[n+1];
		for(int i = 1; i <= n; i++) adj[i] = new ArrayList<Integer>();
		st = new StringTokenizer(r.readLine());
		for(int i = 1; i <= n; i++) {
			int mo = Integer.parseInt(st.nextToken());
			mos[i] = mo;
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
		}
		for(int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][1] = 0;
		int mm = 0;
		for(int t = 0; t < MAX-1; t++) {
			for(int i = 1; i <= n; i++) {
				if(dp[t][i] != -1) {
					for(int j : adj[i]) {
						dp[t+1][j] = Math.max(
							dp[t][i]+mos[j],
							dp[t+1][j]
						);
					}
				}
			}
			mm = Math.max(mm, dp[t][1] - cost[t]);
		}
	
		pw.println(mm);
		
		pw.close();
	}
}
