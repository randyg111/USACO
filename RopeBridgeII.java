// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class RopeBridgeII {
	static int[][] pos = new int[10][2];
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String line = r.readLine();
		int size = 1000;
		boolean[][] visited = new boolean[size][size];
		visited[size/2][size/2] = true;
		for(int i = 0; i < 10; i++) {
			pos[i][0] = size/2;
			pos[i][1] = size/2;
		}
		while(line != null) {
			char dir = line.charAt(0);
			int steps = Integer.parseInt(line.substring(2));

			if(dir == 'U') {
				for(int j = 0; j < steps; j++) {
					pos[0][1]++;
					update();
					visited[pos[9][0]][pos[9][1]] = true;
				}
			} else if(dir == 'D') {
				for(int j = 0; j < steps; j++) {
					pos[0][1]--;
					update();
					visited[pos[9][0]][pos[9][1]] = true;
				}
			} else if(dir == 'R') {
				for(int j = 0; j < steps; j++) {
					pos[0][0]++;
					update();
					visited[pos[9][0]][pos[9][1]] = true;
				}
			} else if(dir == 'L') {
				for(int j = 0; j < steps; j++) {
					pos[0][0]--;
					update();
					visited[pos[9][0]][pos[9][1]] = true;
				}
			}

			line = r.readLine();
		}
		int count = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(visited[i][j]) count++;
			}
		}
		pw.println(count);
		
		pw.close();
	}
	public static void update() {
		for(int i = 1; i < 10; i++) {
			if(pos[i-1][1] - pos[i][1] >= 2) {
				if(pos[i-1][0] - pos[i][0] >= 1) {
					pos[i][0]++;
				} else if (pos[i][0] - pos[i-1][0] >= 1) {
					pos[i][0]--;
				}
				pos[i][1]++;
			} else if(pos[i][1] - pos[i-1][1] >= 2) {
				if(pos[i-1][0] - pos[i][0] >= 1) {
					pos[i][0]++;
				} else if(pos[i][0] - pos[i-1][0] >= 1) {
					pos[i][0]--;
				}
				pos[i][1]--;
			} else if(pos[i-1][0] - pos[i][0] >= 2) {
				if(pos[i-1][1] - pos[i][1] >= 1) {
					pos[i][1]++;
				} else if(pos[i][1] - pos[i-1][1] >= 1) {
					pos[i][1]--;
				}
				pos[i][0]++;
			} else if(pos[i][0] - pos[i-1][0] >= 2) {
				if(pos[i-1][1] - pos[i][1] >= 1) {
					pos[i][1]++;
				} else if(pos[i][1] - pos[i-1][1] >= 1) {
					pos[i][1]--;
				}
				pos[i][0]--;
			} else {
				break;
			}
		}
	}
}
