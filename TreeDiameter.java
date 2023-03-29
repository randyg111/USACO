// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class TreeDiameter {
	static int diameter;
	static int farthest;
	static boolean[] visited;
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n+1];
		visited = new boolean[n+1];
		for(int i = 0; i <= n; i++) adj[i] = new ArrayList<Integer>();
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		dfs(1, 0);
		Arrays.fill(visited, false);
		diameter = 0;
		dfs(farthest, 0);
		pw.println(diameter);
		
		pw.close();
	}
	public static void dfs(int i, int d) {
		if(!visited[i]) {
			visited[i] = true;
			if(d > diameter) {
				diameter = d;
				farthest = i;
			}
			for(Integer j : adj[i]) {
				dfs(j, d+1);
			}
		}
	}
}
