// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class BovineGenomics {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<Set<Character>> spotty = new ArrayList<>();
		List<Set<Character>> plain = new ArrayList<>();
		int count = 0;
		for(int i = 0; i < n; i++) {
			String genome = r.readLine();
			for(int j = 0; j < m; j++) {
				if(i == 0) {
					Set<Character> cow = new HashSet<>();
					cow.add(genome.charAt(j));
					spotty.add(cow);
				} else {
					spotty.get(j).add(genome.charAt(j));
				}
			}
		}
		for(int i = 0; i < n; i++) {
			String genome = r.readLine();
			for(int j = 0; j < m; j++) {
				if(i == 0) {
					Set<Character> cow = new HashSet<>();
					cow.add(genome.charAt(j));
					plain.add(cow);
				} else {
					plain.get(j).add(genome.charAt(j));
				}
			}
		}
		for(int i = 0; i < m; i++) {
			spotty.get(i).retainAll(plain.get(i));
			if(spotty.get(i).size() == 0) {
				count++;
			}
		}
		pw.println(count);
		
		pw.close();
	}
}
