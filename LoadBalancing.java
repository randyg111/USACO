// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class LoadBalancing {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter pw = new PrintWriter("balancing.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[][] coords = new int[n][2];
		int[] xCoords = new int[n];
		int[] yCoords = new int[n];
		int m = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			int x = Integer.parseInt(st.nextToken());
			coords[i][0] = x;
			xCoords[i] = x;
			int y = Integer.parseInt(st.nextToken());
			coords[i][1] = y;
			yCoords[i] = y;
		}
		Arrays.sort(xCoords);
		Arrays.sort(yCoords);
		for(int i = 0; i < n-1; i++) {
			for(int j = 0; j < n-1; j++) {
				int ma = max(coords, xCoords[i]+1, yCoords[j]+1);
				if(ma < m) {
					m = ma;
				}
			}
		}
		pw.println(m);
		
		pw.close();
	}
	public static int max(int[][] coords, int x, int y) {
		int[] regions = new int[4];
		for(int i = 0; i < coords.length; i++) {
			if(coords[i][0] > x && coords[i][1] > y) {
				regions[0]++;
			} else if(coords[i][0] < x && coords[i][1] > y) {
				regions[1]++;
			} else if(coords[i][0] < x && coords[i][1] < y) {
				regions[2]++;
			} else if(coords[i][0] > x && coords[i][1] < y) {
				regions[3]++;
			}
		}
		int m = 0;
		for(int region : regions) {
			if (region > m) {
				m = region;
			}
		}
		return m;
	}
}
