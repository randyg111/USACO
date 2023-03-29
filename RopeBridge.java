// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class RopeBridge {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String line = r.readLine();
		int size = 1000;
		boolean[][] visited = new boolean[size][size];
		visited[size/2][size/2] = true;
		int hx = size/2, hy = size/2, tx = size/2, ty = size/2;
		while(line != null) {
			char dir = line.charAt(0);
			int steps = Integer.parseInt(line.substring(2));

			if(dir == 'U') {
				for(int j = 0; j < steps; j++) {
					hy++;
					if(hy - ty >= 2) {
						if(hx - tx == 1) {
							tx++;
						} else if (tx - hx == 1) {
							tx--;
						}
						ty++;
					} 
					
					visited[tx][ty] = true;
				}
			} else if(dir == 'D') {
				for(int j = 0; j < steps; j++) {
					hy--;
					if(ty - hy >= 2) {
						if(hx - tx == 1) {
							tx++;
						} else if(tx - hx == 1) {
							tx--;
						}
						ty--;
					}
					visited[tx][ty] = true;
				}
			} else if(dir == 'R') {
				for(int j = 0; j < steps; j++) {
					hx++;
					if(hx - tx >= 2) {
						if(hy - ty == 1) {
							ty++;
						} else if(ty - hy == 1) {
							ty--;
						}
						tx++;
					}
					visited[tx][ty] = true;
				}
			} else if(dir == 'L') {
				for(int j = 0; j < steps; j++) {
					hx--;
					if(tx - hx >= 2) {
						if(hy - ty == 1) {
							ty++;
						} else if(ty - hy == 1) {
							ty--;
						}
						tx--;
					} 
					visited[tx][ty] = true;
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
}
