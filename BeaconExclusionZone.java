// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class BeaconExclusionZone {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String line = r.readLine();
		int l = 2000000;
		List<List<Integer>> sensors = new ArrayList<>();
		List<List<Integer>> beacons = new ArrayList<>();
		Map<Integer, List<Integer>> pos = new TreeMap<>();
		Set<Integer> cont = new HashSet<>();
		while(line != null) {
			int i1 = line.indexOf("=");
			int i2 = line.indexOf(",");
			int i3 = line.indexOf(":");
			int x = Integer.parseInt(line.substring(i1+1, i2));
			int y = Integer.parseInt(line.substring(i2+4, i3));
			List<Integer> coords = new ArrayList<>();
			coords.add(x);
			coords.add(y);
			sensors.add(coords);

			i1 = line.lastIndexOf("x");
			i2 = line.lastIndexOf(",");
			x = Integer.parseInt(line.substring(i1+2, i2));
			y = Integer.parseInt(line.substring(i2+4));
			if(y == l && !pos.keySet().contains(x)) {
				List<Integer> index = new ArrayList<>();
				index.add(-1);
				pos.put(x, index);
			}
			coords = new ArrayList<>();
			coords.add(x);
			coords.add(y);
			beacons.add(coords);
			line = r.readLine();
		}
		for(int i = 0; i < sensors.size(); i++) {
			int x = sensors.get(i).get(0);
			int y = sensors.get(i).get(1);
			int d = dist(x, y, beacons.get(i).get(0), beacons.get(i).get(1));
			int v = Math.abs(y-l);
			int h = d-v;
			if(h == 0) {
				if(pos.keySet().contains(x)) {
					pos.get(x).add(i);
				} else {
					List<Integer> index = new ArrayList<>();
					index.add(i);
					pos.put(x, index);
				}
			} else if(h > 0) {
				if(pos.keySet().contains(x-h)) {
					pos.get(x-h).add(i);
				} else {
					List<Integer> index = new ArrayList<>();
					index.add(i);
					pos.put(x-h, index);
				}
				if(pos.keySet().contains(x+h)) {
					pos.get(x+h).add(i);
				} else {
					List<Integer> index = new ArrayList<>();
					index.add(i);
					pos.put(x+h, index);
				}
			}
			
		}

		int sum = 0;
		int prev = 0;
		for(int x : pos.keySet()) {
			for(int i : pos.get(x)) {
				if(i == -1) {
					if(!cont.isEmpty()) sum--;
					continue;
				}
				pw.println(x + " " + i);
				if(cont.isEmpty()) {
					prev = x;
				}
				if(cont.contains(i)) {
					cont.remove(i);
					if(cont.isEmpty()) {
						sum += x - prev + 1;
					}
				} else {
					cont.add(i);
				}
			}
		}
		pw.println(sum);
		
		pw.close();
	}
	public static int dist(int a, int b, int x, int y) {
		return Math.abs(a-x) + Math.abs(b-y);
	}
}
