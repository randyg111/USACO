// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class RoomAllocation {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int k = Integer.parseInt(st.nextToken());
		int rooms = 0;
		Map<Integer, List<Integer>> arrivals = new TreeMap<>();
		Map<Integer, List<Integer>> departures = new TreeMap<>();
		int[] customers = new int[k];
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(!arrivals.containsKey(a)) {
				List<Integer> index = new LinkedList<>();
				arrivals.put(a, index);
			}
			if(!departures.containsKey(b)) {
				List<Integer> index = new LinkedList<>();
				departures.put(b, index);
			}
			arrivals.get(a).add(i);
			departures.get(b).add(i);
		}
		Iterator<Integer> arr = arrivals.keySet().iterator();
		Iterator<Integer> dep = departures.keySet().iterator();
		int end = dep.next();
		while(arr.hasNext()) {
			int start = arr.next();
			for(Integer i : arrivals.get(start)) {
				boolean found = false;
				while(!found && dep.hasNext()) {
					if(end >= start) {
						break;
					} else {
						customers[i] = customers[departures.get(end).remove(0)];
						found = true;
						if(departures.get(end).size() == 0) end = dep.next();
					}
				}
				if(!found) {
					rooms++;
					customers[i] = rooms;
				}
			}
		}
		pw.println(rooms);
		for(int customer : customers) {
			pw.print(customer+" ");
		}
		
		pw.close();
	}
}
