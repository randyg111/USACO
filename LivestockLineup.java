// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class LivestockLineup {
	public static List<List<String>> perms = new ArrayList<>();
	public static List<String> perm = new ArrayList<>();
	public static boolean[] chosen = new boolean[8];
	public static List<String> cows = new ArrayList<>(Arrays.asList("Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"));

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("lineup.in"));
		PrintWriter pw = new PrintWriter("lineup.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		Map<String, List<String>> pairs = new HashMap<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			String cow1 = st.nextToken();
			st.nextToken();
			st.nextToken();
			st.nextToken();
			st.nextToken();
			String cow2 = st.nextToken();
			if(!pairs.keySet().contains(cow1)) {
				List<String> moo = new ArrayList<>();
				pairs.put(cow1, moo);
			}
			if(!pairs.keySet().contains(cow2)) {
				List<String> moo = new ArrayList<>();
				pairs.put(cow2, moo);
			}
			pairs.get(cow1).add(cow2);
			pairs.get(cow2).add(cow1);
		}
		permutate();
		for(List<String> p : perms) {
			boolean invalid = false;
			for(int i = 0; i < p.size(); i++) {
				String c = p.get(i);
				if(pairs.keySet().contains(c)) {
					for(String m : pairs.get(c)) {
						if(i == 0) {
							if(!p.get(1).equals(m)) {
								invalid = true;
								break;
							}
						} else if(i == p.size()-1) {
							if(!p.get(p.size()-2).equals(m)) {
								invalid = true;
								break;
							}
							break;
						} else {
							if(!p.get(i-1).equals(m) && !p.get(i+1).equals(m)) {
								invalid = true;
								break;
							}
						}
					}
				}
				if(invalid) break;
			}
			if(!invalid) {
				for(String c : p) {
					pw.println(c);
				}
				break;
			}
		}
		
		pw.close();
	}

	public static void permutate() {
		if(perm.size() == 8) {
			List<String> p = new ArrayList<>(perm);
			perms.add(p);
		} else {
			for(int i = 0; i < 8; i++) {
				if(!chosen[i]) {
					String cow = cows.get(i);
					chosen[i] = true;
					perm.add(cow);
					permutate();
					perm.remove(perm.size()-1);
					chosen[i] = false;
				}
			}
		}
	}
}
