// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class Investigation {
	static class Pair<K, V> {
		public K a;
		public V b;
		public Pair(K a, V b) {
			this.a = a;
			this.b = b;
		}
		public K getKey() {
			return a;
		}
		public V getValue() {
			return b;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Pair<Integer, Long>>[] adj = new ArrayList[n+1];
		for(int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<Pair<Integer, Long>>();
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Long c = Long.parseLong(st.nextToken());
			adj[a].add(new Pair<Integer, Long>(b, c));
		}
		long[] dist = new long[n+1];
		int[] dist2 = new int[n+1];
		Arrays.fill(dist, Long.MAX_VALUE);
		PriorityQueue<Pair<Integer, Long>> pq = new PriorityQueue<>(
			(a, b) -> Long.compare(a.getValue(), b.getValue()));
		pq.add(new Pair<Integer, Long>(1, 0l));
		int[] min = new int[n+1];
		int[] max = new int[n+1];
		long[] count = new long[n+1];
		dist[1] = 0;
		count[1] = 1;
		while(!pq.isEmpty()) {
			Pair<Integer, Long> pair = pq.poll();
			int node = pair.getKey();
			long d = pair.getValue();
			if(d != dist[node]) continue;
			for(Pair<Integer, Long> p : adj[node]) {
				int j = p.getKey();
				long newDist = d + p.getValue();
				if(newDist < dist[j]) {
					dist[j] = newDist;
					min[j] = min[node]+1;
					max[j] = max[node]+1;
					count[j] = count[node];
					pq.add(new Pair<Integer, Long>(j, newDist));
				} else if(newDist == dist[j]) {
					count[j] += count[node];
					count[j] %= 1000000007;
					min[j] = Math.min(min[j], min[node]+1);
					max[j] = Math.max(max[j], max[node]+1);
				}
			}
		}
		pw.println(dist[n] + " " + count[n] + " " + min[n] + " " + max[n]);
		
		pw.close();
	}
}
