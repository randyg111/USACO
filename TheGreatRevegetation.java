// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class TheGreatRevegetation {
	static ArrayList<Edge>[] adj;
	static int[] color;
	static boolean impossible = false;
	static class Edge {
		boolean same;
		int index;
		public Edge(boolean s, int i) {
			same = s;
			index = i;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("revegetate.in"));
		PrintWriter pw = new PrintWriter("revegetate.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n+1];
		color = new int[n+1];
		for(int i = 1; i <= n; i++) adj[i] = new ArrayList<Edge>();
		int m = Integer.parseInt(st.nextToken());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			boolean s = st.nextToken().equals("S");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(s, b));
			adj[b].add(new Edge(s, a));
		}
		int count = 0;
		for(int i = 1; i <= n; i++) {
			if(color[i] == 0) {
				count++;
				dfs(i, 1);
			}
		}
		if(!impossible) {
			pw.print("1");
			for(int i = 0; i < count; i++) {
				pw.print("0");
			}
		} else {
			pw.print("0");
		}

		pw.close();
	}
	public static void dfs(int i, int c) {
		if(color[i] == 0) {
			color[i] = c;
			for(Edge j : adj[i]) {
				if(j.same) {
					dfs(j.index, c);
				} else {
					dfs(j.index, c==1 ? 2 : 1);
				}
			}
		} else {
			if(color[i] != c) impossible = true;
		}
	}
}
