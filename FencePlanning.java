// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class FencePlanning {
	static int[][] coords;
	static ArrayList<Integer>[] adj;
	static int maxx, maxy, minx, miny;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		coords = new int[n+1][2];
		adj = new ArrayList[n+1];
		visited = new boolean[n+1];
		int min = Integer.MAX_VALUE;
		for(int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(r.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			coords[i][0] = x;
			coords[i][1] = y;
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		for(int i = 1; i <= n; i++) {
			if(!visited[i]) {
				maxx = 0;
				maxy = 0;
				minx = Integer.MAX_VALUE;
				miny = Integer.MAX_VALUE;
				dfs(i);
				min = Math.min(min, 2*(maxx-minx+maxy-miny));
			}
		}
		pw.println(min);
		
		pw.close();
	}
	public static void dfs(int a) {
		if(!visited[a]) {
			visited[a] = true;
			maxx = Math.max(maxx, coords[a][0]);
			maxy = Math.max(maxy, coords[a][1]);
			minx = Math.min(minx, coords[a][0]);
			miny = Math.min(miny, coords[a][1]);
			for(int b : adj[a]) {
				dfs(b);
			}
		}
	}
}
