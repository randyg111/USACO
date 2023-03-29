// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class Subtree {
	static ArrayList<Integer>[] adj;
	static ArrayList<Long>[] pref, suff;
	static long MOD;
	static long[] dp1, dp2;
	static int[] index, parent;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		MOD = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n];
		pref = new ArrayList[n];
		suff = new ArrayList[n];
		dp1 = new long[n];
		dp2 = new long[n];
		index = new int[n];
		parent = new int[n];
		for(int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
			pref[i] = new ArrayList<Long>();
			suff[i] = new ArrayList<Long>();
		}
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(r.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			x--;
			y--;
			adj[x].add(y);
			adj[y].add(x);
		}
		dfs1(0, -1, -1);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < adj[i].size(); j++) {
				if(adj[i].get(j) != parent[i]) {
					if(pref[i].size() == 0) {
						pref[i].add(dp1[adj[i].get(j)]);
					} else {
						pref[i].add(pref[i].get(pref[i].size()-1) * dp1[adj[i].get(j)] % MOD);
					}
				}
			}
			for(int j = adj[i].size()-1; j >= 0; j--) {
				if(adj[i].get(j) != parent[i]) {
					if(suff[i].size() == 0) {
						suff[i].add(dp1[adj[i].get(j)]);
					} else {
						suff[i].add(suff[i].get(suff[i].size()-1) * dp1[adj[i].get(j)] % MOD);
					}
				}
			}
		}
		dp2[0] = 1;
		dfs2(0, -1);
		for(int i = 0; i < n; i++) {
			pw.println(((dp1[i]-1) * dp2[i]) % MOD);
		}
		
		pw.close();
	}
	public static void dfs1(int i, int p, int n) {
		index[i] = n;
		parent[i] = p;
		if(p >= 0 && adj[i].size() == 1) {
			dp1[i] = 2;
		}
		int ind = 0;
		long prod = 1;
		for(int j : adj[i]) {
			if(j != p) {
				dfs1(j, i, ind);
				prod *= dp1[j];
				prod %= MOD;
				ind++;
			}
		}
		dp1[i] = prod + 1;
	}
	public static void dfs2(int i, int p) {
		if(p >= 0) {
			if(index[i] == 0) {
				if(suff[p].size() >= 2) 
					dp2[i] = dp2[p] * suff[p].get(suff[p].size()-2) + 1;
				else dp2[i] = dp2[p] + 1;
			} else if(index[i] == suff[p].size()-1) {
				if(pref[p].size() >= 2) 
					dp2[i] = dp2[p] * pref[p].get(pref[p].size()-2) + 1;
				else dp2[i] = dp2[p] + 1;
			} else {
				dp2[i] = dp2[p] * pref[p].get(index[i]-1) % MOD * suff[p].get(suff[p].size()-2-index[i]) + 1;
			}
		}
		dp2[i] %= MOD;
		for(int j : adj[i]) {
			if(j != p) {
				dfs2(j, i);
			}
		}
	}
}
