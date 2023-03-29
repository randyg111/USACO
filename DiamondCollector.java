// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class DiamondCollector {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter pw = new PrintWriter("diamond.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int max = 0;
		int[] sizes = new int[n];
		int[] right = new int[n];
		int[] left = new int[n];
		for(int i = 0; i < n; i++) {
			sizes[i] = Integer.parseInt(r.readLine());
		}
		Arrays.sort(sizes);
		int i = 0;
		int j = 0;
		while(j < n) {
			if(sizes[j]-sizes[i] > k) {
				i++;
			} else {
				right[i] = Math.max(right[i], j-i+1);
				left[j] = Math.max(left[j], j-i+1);
				j++;
			}
		}
		for(int l = 1; l < n; l++) {
			left[l] = Math.max(left[l], left[l-1]);
		}
		for(int l = n-2; l >= 0; l--) {
			right[l] = Math.max(right[l], right[l+1]);
		}
		for(int l = 1; l < n; l++) {
			max = Math.max(max, left[l-1]+right[l]);
		}
		pw.println(max);
		
		pw.close();
	}
}
