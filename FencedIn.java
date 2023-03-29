// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class FencedIn {
	static class DSU {
		int[] parents;
		int[] sizes;
		public DSU(int size) {
			parents = new int[size];
			sizes = new int[size];
			Arrays.fill(parents, -1);
			Arrays.fill(sizes, 1);
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
	}
	static class Edge implements Comparable<Edge> {
		int start, end, weight;
		public Edge(int i, int j, int w) {
			start = i;
			end = j;
			weight = w;
		}
		public int compareTo(Edge e) {
			return Integer.compare(weight, e.weight);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter pw = new PrintWriter("fencedin.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] verticals = new int[n+2];
		int[] horizontals = new int[m+2];
		for(int i = 1; i <= n; i++) {
			int a = Integer.parseInt(r.readLine());
			verticals[i] = a;
		}
		for(int i = 1; i <= m; i++) {
			int b = Integer.parseInt(r.readLine());
			horizontals[i] = b;
		}
		verticals[n+1] = A;
		horizontals[m+1] = B;
		Arrays.sort(verticals);
		Arrays.sort(horizontals);
		DSU dsu = new DSU((m+1)*(n+1));
		List<Edge> pq = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m+1; j++) {
				int node = j*(n+1)+i;
				int dist = horizontals[j+1]-horizontals[j];
				pq.add(new Edge(node, node+1, dist));
			}
		}
		for(int j = 0; j < m; j++) {
			for(int i = 0; i < n+1; i++) {
				int node = j*(n+1)+i;
				int dist = verticals[i+1]-verticals[i];
				pq.add(new Edge(node, node+n+1, dist));
			}
		}
		Collections.sort(pq);
		int count = 0;
		long weight = 0;
		for(int i = 0; i < pq.size() && count < m*n+m+n; i++) {
			Edge edge = pq.get(i);
			if(dsu.union(edge.start, edge.end)) {
				weight += edge.weight;
				count++;
			}
		}
		pw.println(weight);

		pw.close();
	}
}
