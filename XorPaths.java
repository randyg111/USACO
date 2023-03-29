// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class XorPaths {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		long[][] grid = new long[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			for(int j = 0; j < m; j++) {
				long a = Long.parseLong(st.nextToken());
				grid[i][j] = a;
			}
		}
		int moves = n + m - 2;
		Map<Long, Long>[][] count = new Map[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				count[i][j] = new HashMap<>();
			}
		}
		int left = moves/2;
		for(int i = 0; i < 1 << left; i++) {
			long sum = grid[0][0];
			int x = 0;
			int y = 0;
			boolean in = true;
			for(int j = 0; j < left; j++) {
				if((i & (1 << j)) > 0) {
					x++;
				} else {
					y++;
				}
				if(x < n && y < m) {
					sum ^= grid[x][y];
				} else {
					in = false;
					break;
				}
			}
			if(in) {
				count[x][y].put(sum, count[x][y].getOrDefault(sum, 0l) + 1);
			}
		}
		long total = 0;
		int right = moves - left - 1;
		for(int i = 0; i < 1 << right; i++) {
			long sum = grid[n-1][m-1];
			int x = n-1;
			int y = m-1;
			boolean in = true;
			for(int j = 0; j < right; j++) {
				if((i & (1 << j)) > 0) {
					x--;
				} else {
					y--;
				}
				if(x >= 0 && y >= 0) {
					sum ^= grid[x][y];
				} else {
					in = false;
					break;
				}
			}
			if(in) {
				if(x > 0) {
					total += count[x-1][y].getOrDefault(k ^ sum, 0l);
				}
				if(y > 0) {
					total += count[x][y-1].getOrDefault(k ^ sum, 0l);
				}
			}
		}
		if(moves == 0) {
			pw.println(grid[0][0] == k ? 1 : 0);
		} else {
			pw.println(total);
		}

		pw.close();
	}
}
