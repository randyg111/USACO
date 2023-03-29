// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class RegolithReservoir {
	static int x = 500, y = 0;
	static int bottom = 0;
	static boolean done = false;
	static boolean[][] cave;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		cave = new boolean[1000][1000];
		String line = r.readLine();
		while(line != null) {
			String[] points = line.split(" -> ");
			int x0, y0, x, y;
			String point0 = points[0];
			int ind = point0.indexOf(",");
			x0 = Integer.parseInt(point0.substring(0, ind));
			y0 = Integer.parseInt(point0.substring(ind+1));
			for(int i = 0; i < points.length; i++) {
				String point = points[i];
				int index = point0.indexOf(",");
				x = Integer.parseInt(point.substring(0, index));
				y = Integer.parseInt(point.substring(index+1));
				bottom = Math.max(bottom, y);
				if(x == x0) {
					if(y < y0) {
						for(int j = y; j <= y0; j++) {
							cave[x][j] = true;
						}
					} else {
						for(int j = y; j >= y0; j--) {
							cave[x][j] = true;
						}
					}
				} else {
					if(x < x0) {
						for(int j = x; j <= x0; j++) {
							cave[j][y] = true;
						}
					} else {
						for(int j = x; j >= x0; j--) {
							cave[j][y] = true;
						}
					}
				}
				x0 = x;
				y0 = y;
			}
			line = r.readLine();
		}
		int count = 0;
		while(!done) {
			fall();
			cave[x][y] = true;
			x = 500;
			y = 0;
			if(!done) count++;
		}
		pw.println(count);
		
		pw.close();
	}
	public static void fall() {
		if(y > bottom) {
			done = true;
			return;
		}
		if(!cave[x][y+1]) {
			y++;
			fall();
		} else if(!cave[x-1][y+1]) {
			x--;
			y++;
			fall();
		} else if(!cave[x+1][y+1]) {
			x++;
			y++;
			fall();
		} else {
			return;
		}
	}
}
