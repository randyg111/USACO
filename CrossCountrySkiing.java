// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class CrossCountrySkiing {
	static boolean[][] waypoint;
	static int[][] elevation;
	static boolean[][] visited;
	static int firsti = -1;
	static int firstj = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("ccski.in"));
		PrintWriter pw = new PrintWriter("ccski.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		waypoint = new boolean[m][n];
		elevation = new int[m][n];
		visited = new boolean[m][n];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			for(int j = 0; j < n; j++) {
				elevation[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			for(int j = 0; j < n; j++) {
				waypoint[i][j] = Integer.parseInt(st.nextToken()) == 1;
				if(firsti == -1 && waypoint[i][j]) {
					firsti = i;
					firstj = j;
				}
			}
		}
		int lo = 0;
		int hi = (int) 1e9;
		while(lo < hi) {
			int mid = (lo+hi)/2;
			if(works(mid)) {
				hi = mid;
			} else {
				lo = mid+1;
			}
		}
		pw.println(hi);
		
		pw.close();
	}
	public static boolean works(int d) {
		for(boolean[] visit : visited) {
			Arrays.fill(visit, false);
		}
		ff(firsti, firstj, d);
		for(int i = 0; i < waypoint.length; i++) {
			for(int j = 0; j < waypoint[0].length; j++) {
				if(waypoint[i][j]) {
					if(!visited[i][j]) return false;
				}
			}
		}
		return true;
	}
	public static void ff(int i, int j, int d) {
		if(i < 0 || j < 0 || i >= visited.length || j >= visited[0].length) return;
		if(!visited[i][j]) {
			visited[i][j] = true;
			if(i-1 >= 0 && Math.abs(elevation[i-1][j] - elevation[i][j]) <= d) ff(i-1, j, d);
			if(i+1 < elevation.length && Math.abs(elevation[i+1][j] - elevation[i][j]) <= d) ff(i+1, j, d);
			if(j-1 >= 0 && Math.abs(elevation[i][j-1] - elevation[i][j]) <= d) ff(i, j-1, d);
			if(j+1 < elevation[0].length && Math.abs(elevation[i][j+1] - elevation[i][j]) <= d) ff(i, j+1, d);
		}
	}
}
