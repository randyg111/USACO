// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class Moocast {
	static ArrayList<Integer> adj[];
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[][] coords = new int[n][3];
		int max = 0;
		adj = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		visited = new boolean[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			coords[i][0] = x;
			coords[i][1] = y;
			coords[i][2] = p;
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(Math.ceil(Math.sqrt(Math.pow(Math.abs(coords[i][0]-coords[j][0]), 2)
				 + Math.pow(Math.abs(coords[i][1]-coords[j][1]), 2))) <= coords[i][2]) {
					adj[i].add(j);
				}
			}
		}
		for(int i = 0; i < n; i++) {
			Arrays.fill(visited, false);
			max = Math.max(max, dfs(i));
		}
		pw.println(max);
		
		pw.close();
	}
	public static int dfs(int i) {
		if(!visited[i]) {
			visited[i] = true;
			int count = 1;
			for(int j : adj[i]) {
				count += dfs(j);
			}
			return count;
		}
		return 0;
	}
}
