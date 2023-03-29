// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class SumOfTwoValues {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int[] vals = new int[n];
		int[] sorted = new int[n];
		st = new StringTokenizer(r.readLine());
		for(int i = 0; i < n; i++) {
			int val = Integer.parseInt(st.nextToken());
			vals[i] = val;
			sorted[i] = val;
		}
		Arrays.sort(sorted);
		int i = 0;
		int j = n-1;
		while(i != j) {
			int sum = sorted[i]+sorted[j];
			if(sum == x) {
				break;
			} else if(sum < x) {
				i++;
			} else {
				j--;
			}
		}
		if(i==j) {
			pw.println("IMPOSSIBLE");
		} else {
			int a = sorted[i];
			int b = sorted[j];
			boolean found1 = false;
			boolean found2 = false;
			for(int k = 0; k < n; k++) {
				if(found1 && found2) break;
				if(!found1 && vals[k] == a) {
					found1 = true;
					pw.print((k+1)+" ");
				} else if(!found2 && vals[k] == b) {
					found2 = true;
					pw.print((k+1)+" ");
				}
			}
		}
		pw.close();
	}
}
