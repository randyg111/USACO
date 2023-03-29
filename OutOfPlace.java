// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class OutOfPlace {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("outofplace.in"));
		PrintWriter pw = new PrintWriter("outofplace.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] heights = new int[n];
		int b = 0;
		boolean right = false;
		boolean left = false;
		int swaps = 0;
		for(int i = 0; i < n; i++) {
			int h = Integer.parseInt(r.readLine());
			heights[i] = h;
		}
		for(int i = 0; i < n-1; i++) {
			if(heights[i+1] < heights[i]) {
				if(i == 0 || (i != n-2 && heights[i+2] < heights[i])) {
					right = true;
					b = i;
				} else {
					left = true;
					b = i+1;
				}
			}
		}
		if(right) {
			for(int i = b+1; i < n; i++) {
				if(heights[i] >= heights[b]) break;
				if(heights[i] != heights[i-1]) {
					swaps++;
				}
			}
		}
		if(left) {
			for(int i = b-1; i >= 0; i--) {
				if(heights[i] <= heights[b]) break;
				if(heights[i] != heights[i+1]) {
					swaps++;
				}
			}
		}
		pw.println(swaps);
		
		pw.close();
	}
}
