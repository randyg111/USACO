// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MilkVisits {
	static char[] breed;
	static int[] comp;
	static int component = 1;
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("milkvisits.in"));
		PrintWriter pw = new PrintWriter("milkvisits.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		breed = new char[n+1];
		comp = new int[n+1];
		adj = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) adj[i] = new ArrayList<Integer>();
		String s = r.readLine();
		for(int i = 0; i < n; i++) {
			breed[i+1] = s.charAt(i);
		}
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(r.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj[x].add(y);
			adj[y].add(x);
		}
		for(int i = 1; i <= n; i++) {
			if(comp[i] == 0) {
				dfs(i);
				component++;
			}
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			if(comp[a] != comp[b]) {
				pw.print(1);
			} else if (breed[a] == c) {
				pw.print(1);
			} else {
				pw.print(0);
			}
		}
		
		pw.close();
	}
	public static void dfs(int i) {
		if(comp[i] == 0) {
			comp[i] = component;
			for(Integer j : adj[i]) {
				if(breed[j] == breed[i]) dfs(j);
			}
		}
	}
}
