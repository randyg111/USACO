// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class EvenOutdegreeEdges {
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer>[] adj2;
	static int visit = 1;
	static int[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n+1];
		adj2 = new ArrayList[n+1];
		visited = new int[n+1];
		for(int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<Integer>();
			adj2[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		dfs(1, -1);
		boolean impossible = false;
		for(int i = 1; i <= n; i++) {
			if(adj2[i].size()%2 != 0) {
				impossible = true;
				break;
			}
		}
		if(impossible) {
			pw.println("IMPOSSIBLE");
		} else {
			for(int i = 1; i <= n; i++) {
				for(Integer j : adj2[i]) {
					pw.println(i+" "+j);
				}
			}
		}

		pw.close();
	}
	public static void dfs(int i, int p) {
		visited[i] = visit++;
		for(Integer j : adj[i]) {
			if(visited[j] == 0) {
				dfs(j, i);
				if(adj2[j].size()%2 == 0) {
					adj2[i].add(j);
				} else {
					adj2[j].add(i);
				}
			} else if(j != p && visited[j] < visited[i]) {
				adj2[i].add(j);
			}
		}
	}
}
