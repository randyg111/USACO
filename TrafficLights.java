// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.TreeMap;

public class TrafficLights {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int x = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		TreeSet<Integer> road = new TreeSet<>();
		TreeMap<Integer, Integer> distances = new TreeMap<>();
		distances.put(x, 1);
		st = new StringTokenizer(r.readLine());
		for(int i = 0; i < n; i++) {
			int light = Integer.parseInt(st.nextToken());
			int low = 0;
			int high = x;
			if(road.lower(light) != null) low = road.lower(light);
			if(road.higher(light) != null) high = road.higher(light);
			distances.put(high-low, distances.get(high-low)-1);
			if(distances.get(high-low) == 0) distances.remove(high-low);
			if(!distances.containsKey(light-low)) distances.put(light-low, 0);
			if(!distances.containsKey(high-light)) distances.put(high-light, 0);
			distances.put(light-low, distances.get(light-low)+1);
			distances.put(high-light, distances.get(high-light)+1);
			road.add(light);
			pw.print(distances.lastKey()+" ");
		}
		
		
		pw.close();
	}
}
