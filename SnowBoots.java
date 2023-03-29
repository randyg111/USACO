// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class SnowBoots {
	static class Boot implements Comparable<Boot> {
		int depth, step, index;
		public Boot (int s, int d, int i) {
			depth = s;
			step = d;
			index = i;
		}
		public int compareTo(Boot b) {
			if(b.depth != depth) return b.depth-depth;
			return b.step-step;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter pw = new PrintWriter("snowboots.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(r.readLine());
		Map<Integer, List<Integer>> snow = new TreeMap<>(Collections.reverseOrder());
		PriorityQueue<Boot> boots = new PriorityQueue<>();
		TreeSet<Integer> tiles = new TreeSet<>();
		int[] trek = new int[b];
		for(int i = 0; i < n; i++) {
			int f = Integer.parseInt(st.nextToken());
			if(!snow.containsKey(f)) {
				List<Integer> indices = new ArrayList<>();
				snow.put(f, indices);
			}
			snow.get(f).add(i);
			tiles.add(i);
		}
		for(int i = 0; i < b; i++) {
			st = new StringTokenizer(r.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			boots.add(new Boot(s, d, i));
		}
		Iterator<Integer> depths = snow.keySet().iterator();
		int maxDepth = depths.next();
		int maxStep = 1;
		while(!boots.isEmpty()) {
			Boot boot = boots.poll();
			while(maxDepth > boot.depth) {
				for(Integer i : snow.get(maxDepth)) {
					tiles.remove(i);
					maxStep = Math.max(maxStep, tiles.higher(i)-tiles.lower(i));
				}
				maxDepth = depths.next();
			}
			if(boot.step >= maxStep) {
				trek[boot.index] = 1;
			} else {
				trek[boot.index] = 0;
			}
		}
		for(int i : trek) {
			pw.println(i);
		}
		
		pw.close();
	}
}
