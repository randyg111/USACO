// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class CowGymnastics {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("gymnastics.in"));
		PrintWriter pw = new PrintWriter("gymnastics.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] ranks = new int[n][k];
		int pairs = 0;
		for(int session = 0; session < k; session++) {
			st = new StringTokenizer(r.readLine());
			for(int rank = n; rank > 0; rank--) {
				int cow = Integer.parseInt(st.nextToken());
				ranks[cow-1][session] = rank;
			}
		}
		for(int cow1 = 0; cow1 < n; cow1++) {
			for(int cow2 = 0; cow2 < n; cow2++) {
				boolean consistent = true;
				for(int session = 0; session < k; session++) {
					if(ranks[cow1][session] <= ranks[cow2][session]) {
						consistent = false;
					}
				}
				if(consistent) {
					pairs++;
				}
			}
		}
		pw.println(pairs);

		pw.close();
	}
}
