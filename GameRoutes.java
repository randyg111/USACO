// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class GameRoutes {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] adj = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) adj[i] = new ArrayList<Integer>();
		int[] inDeg = new int[n+1];
		long[] dp = new long[n+1];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			inDeg[b]++;
		}
		Queue<Integer> top = new LinkedList<>();
		for(int i = 1; i <= n; i++) {
			if(inDeg[i] == 0) top.add(i);
		}
		dp[1] = 1;
		while(!top.isEmpty()) {
			int i = top.poll();
			for(int j : adj[i]) {
				dp[j] += dp[i];
				dp[j] %= 1000000007;
				inDeg[j]--;
				if(inDeg[j] == 0) {
					top.add(j);
				}
			}
		}
		pw.println(dp[n]);
		
		pw.close();
	}
}
