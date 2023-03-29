// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class SumOfThreeValues {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(r.readLine());
		int[] vals = new int[n];
		int[] sorted = new int[n];
		for(int i = 0; i < n; i++) {
			int val = Integer.parseInt(st.nextToken());
			vals[i] = val;
			sorted[i] = val;
		}
		Arrays.sort(sorted);
		int a = 0;
		int b = 0;
		int c = 0;
		boolean found = false;
		for(int k = 0; k < n; k++) {
			int i = 0;
			int j = n-1;
			while(i != j) {
				int sum = sorted[i]+sorted[j]+sorted[k];
				if(i == k) {
					i++;
				} else if(j == k) {
					j--;
				} else if(sum == x) {
					a = sorted[i];
					b = sorted[j];
					c = sorted[k];
					found = true;
					break;
				} else if(sum < x) {
					i++;
				} else {
					j--;
				}
			}
			if(found) break;
		}
		if(!found) {
			pw.println("IMPOSSIBLE");
		} else {
			boolean found1 = false;
			boolean found2 = false;
			boolean found3 = false;
			for(int k = 0; k < n; k++) {
				if(found1 && found2 && found3) break;
				if(!found1 && vals[k] == a) {
					found1 = true;
					pw.print((k+1)+" ");
				} else if(!found2 && vals[k] == b) {
					found2 = true;
					pw.print((k+1)+" ");
				} else if(!found3 && vals[k] == c) {
					found3 = true;
					pw.print((k+1)+" ");
				}
			}
		}
		
		pw.close();
	}
}
