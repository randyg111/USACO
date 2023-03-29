// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Towers {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		TreeMap<Integer, Integer> towers = new TreeMap<>();
		int count = 0;
		st = new StringTokenizer(r.readLine());
		for(int i = 0; i < n; i++) {
			int block = Integer.parseInt(st.nextToken());
			if(towers.higherKey(block) != null) {
				int bottom = towers.higherKey(block);
				towers.put(bottom, towers.get(bottom)-1);
				if(towers.get(bottom) == 0) {
					towers.remove(bottom);
				}
			}
			if(!towers.containsKey(block)) {
				towers.put(block, 0);
			}
			towers.put(block, towers.get(block)+1);
		}
		for(Integer tower : towers.values()) {
			count += tower;
		}
		pw.println(count);
		
		pw.close();
	}
}
