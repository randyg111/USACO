// Source: https://usaco.guide/general/io

import java.io.*;

public class IcyPerimeter {
	static char[][] blobs;
	static boolean[][] visited;
	static int area;
	static int perim;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(r.readLine());
		blobs = new char[n][n];
		visited = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			String s = r.readLine();
			for(int j = 0; j < n; j++) {
				blobs[i][j] = s.charAt(j);
			}
		}
		int maxArea = 0;
		int maxPerim = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j] && blobs[i][j] == '#') {
					area = 0;
					perim = 0;
					floodfill(i, j);
				}
				if(area > maxArea) {
					maxArea = area;
					maxPerim = perim;
				} else if(area == maxArea) {
					maxPerim = Math.min(maxPerim, perim);
				}
			}
		}
		pw.println(maxArea+" "+maxPerim);

		pw.close();
	}
	public static void floodfill(int i, int j) {
		if(i >= 0 && i < blobs.length && j >= 0 && j < blobs.length 
		&& blobs[i][j] == '#') {
			if(visited[i][j]) return;
			visited[i][j] = true;
			area++;
			floodfill(i-1, j);
			floodfill(i+1, j);
			floodfill(i, j-1);
			floodfill(i, j+1);
		} else {
			perim++;
		}
	}
}
