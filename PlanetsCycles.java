// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class PlanetsCycles {
	static int[] teleport;
	static int[] visits;
	static boolean[] visited;
	static Queue<Integer> path;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(r.readLine());
		teleport = new int[n+1];
		visits = new int[n+1];
		visited = new boolean[n+1];
		path = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(r.readLine());
		for(int i = 1; i <= n; i++) {
			int t = Integer.parseInt(st.nextToken());
			teleport[i] = t;
		}
		for(int i = 1; i <= n; i++) {
			if(visits[i] == 0) {
				dfs(i);
			}
		}
		for(int i = 1; i <= n; i++) {
			pw.print(visits[i] + " ");
		}
		
		pw.close();
	}
	public static void dfs(int i) {
		if(visited[i]) {
			boolean cycle = false;
			int length = 0;
			while(path.size() > 0) {
				int j = path.remove();
				if(j == i) {
					cycle = true;
					length = path.size() + 1;
				}
				if(cycle) {
					visits[j] = length;
				} else {
					visits[j] = visits[i] + path.size() + 1;
				}
			}
		} else {
			visited[i] = true;
			path.add(i);
			dfs(teleport[i]);
		}
	}
}
