// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class WhyDidTheCowCrossTheRoadIII {
	static boolean[][] cows;
	static boolean[][][] roads;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		cows = new boolean[n+1][n+1];
		roads = new boolean[n+1][n+1][4];
		visited = new boolean[n+1][n+1];
		for(int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			if(r2 > r1) {
				roads[r1][c1][0] = true;
				roads[r2][c2][1] = true;
			} else if(r2 < r1) {
				roads[r1][c1][1] = true;
				roads[r2][c2][0] = true;
			} else if(c2 > c1) {
				roads[r1][c1][2] = true;
				roads[r2][c2][3] = true;
			} else {
				roads[r1][c1][3] = true;
				roads[r2][c2][2] = true;
			}
		}
		for(int i = 1; i <= k; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			cows[row][col] = true;
		}
		int pairs = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(cows[i][j]) {
					pairs += distant(i, j);
				}
			}
		}
		pw.println(pairs/2);
		
		pw.close();
	}
	public static int distant(int i, int j) {
		int count = 0;
		for(int k = 1; k < visited.length; k++) {
			Arrays.fill(visited[k], false);
		}
		ff(i, j);
		for(int k = 1; k < cows.length; k++) {
			for(int l = 1; l < cows.length; l++) {
				if(cows[k][l] && !visited[k][l]) count++;
			}
		}
		return count;
	}
	public static void ff(int i, int j) {
		if(i < 1 || i >= visited.length || j < 1 || j >= visited.length) return;
		if(!visited[i][j]) {
			visited[i][j] = true;
			if(!roads[i][j][0]) ff(i+1, j);
			if(!roads[i][j][1]) ff(i-1, j);
			if(!roads[i][j][2]) ff(i, j+1);
			if(!roads[i][j][3]) ff(i, j-1);
		}
	}
}
