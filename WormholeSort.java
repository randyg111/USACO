// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class WormholeSort {
	static int[] cows;
	static ArrayList<Wormhole>[] adj;
	static int minWidth;
	static int component;
	static int[] comp;
	static class Wormhole {
		int pos, width;
		public Wormhole(int p, int w) {
			pos = p;
			width = w;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("wormsort.in"));
		PrintWriter pw = new PrintWriter("wormsort.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		cows = new int[n+1];
		comp = new int[n+1];
		adj = new ArrayList[n+1];
		for(int i = 1; i <= n; i++) adj[i] = new ArrayList<Wormhole>();
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(r.readLine());
		for(int i = 1; i <= n; i++) {
			int p = Integer.parseInt(st.nextToken());
			cows[i] = p;
		}
		int max = 0;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[a].add(new Wormhole(b, w));
			adj[b].add(new Wormhole(a, w));
			max = Math.max(max, w);
		}
		int lo = 0;
		int hi = max+1;
		while(lo < hi) {
			int mid = (lo+hi+1)/2;
			if(works(mid)) {
				lo = mid;
			} else {
				hi = mid-1;
			}
		}
		if(hi == max+1) {
			pw.println("-1");
		} else {
			pw.println(lo);
		}
		
		pw.close();
	}
	public static boolean works(int min) {
		minWidth = min;
		component = 1;
		Arrays.fill(comp, 0);
		for(int i = 1; i < comp.length; i++) {
			if(comp[i] == 0) {
				dfs(i);
				component++;
			}
		}
		for(int i = 1; i < cows.length; i++) {
			if(comp[i] != comp[cows[i]]) return false;
		}
		return true;
	}
	public static void dfs(int i) {
		if(comp[i] == 0) {
			comp[i] = component;
			for(Wormhole j : adj[i]) {
				if(j.width >= minWidth) dfs(j.pos);
			}
		}
	}
}
