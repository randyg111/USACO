// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class GrassPlanting {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] degrees = new int[n+1];
		int max = 0;
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(r.readLine());
			int f1 = Integer.parseInt(st.nextToken());
			int f2 = Integer.parseInt(st.nextToken());
			degrees[f1]++;
			degrees[f2]++;
		}
		for(int degree : degrees) {
			if (degree > max) {
				max = degree;
			}
		}
		max++;
		pw.println(max);
		
		pw.close();
	}
}
