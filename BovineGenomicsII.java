// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

public class BovineGenomicsII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter pw = new PrintWriter("cownomics.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] spotty = new char[n][m];
		char[][] plain = new char[n][m];
		int count = 0;
		for(int i = 0; i < n; i++) {
			String genome = r.readLine();
			spotty[i] = genome.toCharArray();
		}
		for(int i = 0; i < n; i++) {
			String genome = r.readLine();
			plain[i] = genome.toCharArray();
		}
		for(int i = 0; i < m; i++) {
			for(int j = i+1; j < m; j++) {
				for(int k = j+1; k < m; k++) {
					boolean diff = true;
					Set<String> seen = new HashSet<>();
					for(int cow = 0; cow < n; cow++) {
						char[] c = {plain[cow][i], plain[cow][j], plain[cow][k]};
						seen.add(new String(c));
					}
					for(int cow = 0; cow < n; cow++) {
						char[] c = {spotty[cow][i], spotty[cow][j], spotty[cow][k]};
						if(seen.contains(new String(c))) {
							diff = false;
						}
					}
					if(diff) {
						count++;
					}
				}
			}
		}
		pw.println(count);
		
		pw.close();
	}
}
