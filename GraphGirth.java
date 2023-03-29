// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class GraphGirth {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] adj = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
		int[] girth = new int[n+1];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}
		Queue<int[]> bfs = new LinkedList<>();
		int cycle = Integer.MAX_VALUE;
		
		for(int i = 1; i <= n; i++) {
			Arrays.fill(girth, -1);
			bfs.add(new int[]{i, 0});
			while(!bfs.isEmpty()) {
				int[] ap = bfs.poll();
				int a = ap[0];
				int p = ap[1];
				girth[a] = girth[p] + 1;
				for(int b : adj[a]) {
					if(b == p) continue;
					if(girth[b] == -1) {
						bfs.add(new int[]{b, a});
					} else {
						cycle = Math.min(cycle, girth[a] + girth[b] + 1);
					}
				}
			}
		}
		pw.println(cycle == Integer.MAX_VALUE ? -1 : cycle);
		
		pw.close();
	}
}
