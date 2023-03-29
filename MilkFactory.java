// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MilkFactory {
	static Map<Integer, List<Integer>> adj = new TreeMap<>();
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("factory.in"));
		PrintWriter pw = new PrintWriter("factory.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		boolean found = false;
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(adj.get(b) == null) {
				List<Integer> list = new ArrayList<>();
				adj.put(b, list);
			}
			adj.get(b).add(a);
		}
		for(Integer i : adj.keySet()) {
			count = 0;
			dfs(i);
			if(count == n) {
				found = true;
				pw.println(i);
				break;
			}
		}
		if(!found) {
			pw.println("-1");
		}
		
		pw.close();
	}

	public static void dfs(int i) {
		count++;
		if(adj.get(i) != null) {
			for(Integer j : adj.get(i)) {
				dfs(j);
			}
		}
	}
}
