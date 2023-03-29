// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class MooTube {
	private static class DSU {
		int[] sizes;
		int[] parents;
		public DSU(int n) {
			sizes = new int[n];
			parents = new int[n];
			Arrays.fill(sizes, 1);
			Arrays.fill(parents, -1);
		}
		public int find(int x) {
			return parents[x] == -1 ? x : (parents[x] = find(parents[x]));
		}
		public boolean union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			if(rootX == rootY) return false;
			if(sizes[rootX] > sizes[rootY]) {
				parents[rootY] = rootX;
				sizes[rootX] += sizes[rootY];
			} else {
				parents[rootX] = rootY;
				sizes[rootY] += sizes[rootX];
			}
			return true;
		}
		public int size(int x) {
			return sizes[find(x)];
		}
	}
	private static class Query implements Comparable<Query> {
		int vid, r;
		public Query(int k, int v) {
			r = k;
			vid = v;
		}
		public int compareTo(Query q) {
			return q.r - r;
		}
	}
	private static class Edge implements Comparable<Edge> {
		int u, v, r;
		public Edge(int p, int q, int k) {
			u = p;
			v = q;
			r = k;
		}
		public int compareTo(Edge e) {
			return e.r - this.r;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Edge[] edges = new Edge[n-1];
		int Q = Integer.parseInt(st.nextToken());
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(p, q, r);
		}
		Arrays.sort(edges);
		DSU dsu = new DSU(n+1);
		Query[] queries = new Query[Q];
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			queries[q] = new Query(k, v);
		}
		int[] map = new int[Q];
		Integer[] indices = new Integer[Q];
		for(int i = 0; i < Q; i++) indices[i] = i;
		int[] sizes = new int[Q];
		Arrays.sort(indices, (a, b) -> queries[a].compareTo(queries[b]));
		Arrays.sort(queries);
		for(int i = 0; i < Q; i++) {
			map[indices[i]] = i;
		}
		int i = 0;
		for(int q = 0; q < Q; q++) {
			Query query = queries[q];
			while(i < edges.length) {
				Edge edge = edges[i];
				if(edge.r >= query.r) {
					dsu.union(edge.u, edge.v);
					i++;
				} else {
					break;
				}
			}
			sizes[q] = dsu.size(query.vid)-1;
		}
		for(int q = 0; q < Q; q++) {
			pw.println(sizes[map[q]]);
		}
		
		pw.close();
	}
}