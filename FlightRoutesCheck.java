// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class FlightRoutesCheck {
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer>[] rev;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n+1];
		rev = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<Integer>();
			rev[i] = new ArrayList<Integer>();
		}
		int m = Integer.parseInt(st.nextToken());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			rev[b].add(a);
		}
		boolean conn = true;
		int citya = 0;
		int cityb = 0;
		visited = new boolean[n+1];
		visited[0] = true;
		dfs1(1);
		for(int i = 0; i <= n; i++) {
			if(!visited[i]) {
				citya = 1;
				cityb = i;
				conn = false;
				break;
			}
		}
		if(conn) {
			visited = new boolean[n+1];
			visited[0] = true;
			dfs2(1);
			for(int i = 0; i <= n; i++) {
				if(!visited[i]) {
					citya = i;
					cityb = 1;
					conn = false;
					break;
				}
			}
		}
		if(conn) {
			pw.println("YES");
		} else {
			pw.println("NO");
			pw.println(citya+" "+cityb);
		}
		
		pw.close();
	}
	public static void dfs1(int a) {
		if(!visited[a]) {
			visited[a] = true;
			for(int b : adj[a]) {
				dfs1(b);
			}
		}
	}
	public static void dfs2(int a) {
		if(!visited[a]) {
			visited[a] = true;
			for(int b : rev[a]) {
				dfs2(b);
			}
		}
	}
}
