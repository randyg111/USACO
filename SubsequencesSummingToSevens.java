// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class SubsequencesSummingToSevens {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] prefix = new int[n+1];
		int max = 0;
		prefix[0] = 0;
		for(int i = 1; i <= n; i++) {
			int id = Integer.parseInt(r.readLine());
			prefix[i] = (prefix[i-1]+id) % 7;
		}
		for(int i = 0; i < 7; i++) {
			int index1 = -1;
			int index2 = -1;
			for(int j = 0; j < prefix.length; j++) {
				if(prefix[j] == i) {
					index1 = j;
					break;
				}
			}
			for(int j = prefix.length-1; j >= 0; j--) {
				if(prefix[j] == i) {
					index2 = j;
					break;
				}
			}
			int len = index2-index1;
			if(len > max) {
				max = len;
			}
		}
		pw.println(max);
		
		pw.close();
	}
}
