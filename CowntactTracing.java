// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class CowntactTracing {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("tracing.in"));
		PrintWriter pw = new PrintWriter("tracing.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		boolean[] cows = new boolean[n];
		boolean[] sim = new boolean[n];
		Map<Integer, List<Integer>> timeline = new TreeMap<>();
		List<Integer> zeros = new ArrayList<>();
		int max = -1;
		int min = Integer.MAX_VALUE;
		boolean found = false;
		int[] shakes = new int[n];
		boolean fail = false;
		for(int i = 0; i < n; i++) {
			int b = Character.getNumericValue((char)r.read());
			cows[i] = b==1;
		}
		r.readLine();
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(r.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			timeline.put(t, Arrays.asList(x-1, y-1));
		}
		for(int i = 0; i < n; i++) {
			if(cows[i]) {
				Arrays.fill(sim, false);
				sim[i] = true;
				for(Integer t : timeline.keySet()) {
					if(sim[timeline.get(t).get(0)] && cows[timeline.get(t).get(1)]) {
						sim[timeline.get(t).get(1)] = true;
					} else if(sim[timeline.get(t).get(1)] && cows[timeline.get(t).get(0)]) {
						sim[timeline.get(t).get(0)] = true;
					}
				}
				if(Arrays.equals(cows, sim)) {
					zeros.add(i);
				}
			}
		}
		for(int i = 0; i < zeros.size(); i++) {
			Arrays.fill(sim, false);
			Arrays.fill(shakes, 0);
			sim[zeros.get(i)] = true;
			for(Integer t : timeline.keySet()) {
				if(sim[timeline.get(t).get(0)]) {
					if(cows[timeline.get(t).get(1)]) {
						if(!sim[timeline.get(t).get(1)]) {
							if(found) {
								fail = true;
								break;
							}
							sim[timeline.get(t).get(1)] = true;
						} else {
							shakes[timeline.get(t).get(1)]++;
						}
						shakes[timeline.get(t).get(0)]++;
					} else {
						found = true;
					}
				} else if(sim[timeline.get(t).get(1)]) {
					if(cows[timeline.get(t).get(0)]) {
						if(!sim[timeline.get(t).get(0)]) {
							if(found) {
								fail = true;
								break;
							}
							sim[timeline.get(t).get(0)] = true;
						}
						shakes[timeline.get(t).get(1)]++;
					} else {
						found = true;
					}
				}
			}
			if(!fail) {
				int m = 0;
				for(int s : shakes) {
					if(s > m) {
						m = s;
					}
				}
				if(m < min) {
					min = m;
				}
				if(found) {
					if(min > max) {
						max = min;
					}
					found = false;
				}
			} else {
				zeros.remove(i);
				i--;
				fail = false;
				found = false;
			}
		}
		
		pw.print(zeros.size() + " " + min + " ");
		if(max > -1) {
			pw.println(max);
		} else {
			pw.println("Infinity");
		}
		
		pw.close();
	}
}
