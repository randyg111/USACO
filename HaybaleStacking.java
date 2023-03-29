// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class HaybaleStacking {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("stacking.in"));
		PrintWriter pw = new PrintWriter("stacking.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] diffs = new int[n+1];
		int[] stacks = new int[n+1];
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			diffs[a]++;
			diffs[b]--;
		}
		for(int i = 1; i < diffs.length; i++) {
			stacks[i] = stacks[i-1]+diffs[i];
		}
		Arrays.sort(stacks);
		pw.println(stacks[(n+1)/2]);
		
		pw.close();
	}
}
