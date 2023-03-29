// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class IndependentSet {
	static ArrayList<Integer>[] adj;
	static final long MOD = (long) 1e9+7;
	static long[] dp1, dp2;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n];
		dp1 = new long[n];
		dp2 = new long[n];
		Arrays.fill(dp1, 1);
		Arrays.fill(dp2, 1);
		for(int i = 0; i < n; i++) adj[i] = new ArrayList<Integer>();
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(r.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			x--;
			y--;
			adj[x].add(y);
			adj[y].add(x);
		}
		dfs(0, -1);
		pw.println((dp1[0]+dp2[0])%MOD);
		
		pw.close();
	}
	public static void dfs(int i, int p) {
		for(int j : adj[i]) {
			if(j != p) {
				dfs(j, i);
				dp1[i] *= dp2[j];
				dp1[i] %= MOD;

				dp2[i] *= dp1[j]+dp2[j];
				dp2[i] %= MOD;
			}
		}
		dp1[i] %= MOD;
		dp2[i] %= MOD;
	}
}
