// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class SplittingTheField {
	static class Cow implements Comparable<Cow> {
		int x, y;
		public Cow(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(Cow c) {
			if(x != c.x) return Integer.compare(x, c.x);
			return Integer.compare(y, c.y);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		Cow[] cows = new Cow[n];
		long[] areas = new long[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cows[i] = new Cow(x, y);
		}
		long minArea = Long.MAX_VALUE;
		long maxArea = 0;
		Arrays.sort(cows);
		int maxx = 0;
		int minx = Integer.MAX_VALUE;
		int maxy = 0;
		int miny = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			maxx = Math.max(cows[i].x, maxx);
			minx = Math.min(cows[i].x, minx);
			maxy = Math.max(cows[i].y, maxy);
			miny = Math.min(cows[i].y, miny);
			areas[i] = (long)(maxx-minx)*(long)(maxy-miny);
		}
		maxx = 0;
		minx = Integer.MAX_VALUE;
		maxy = 0;
		miny = Integer.MAX_VALUE;
		for(int i = n-1; i >= 1; i--) {
			maxx = Math.max(cows[i].x, maxx);
			minx = Math.min(cows[i].x, minx);
			maxy = Math.max(cows[i].y, maxy);
			miny = Math.min(cows[i].y, miny);
			areas[i-1] += (long)(maxx-minx)*(long)(maxy-miny);
		}
		for(long area : areas) {
			minArea = Math.min(area, minArea);
			maxArea = Math.max(area, maxArea);
		}
		transpose(cows);
		Arrays.fill(areas, 0);
		Arrays.sort(cows);
		maxx = 0;
		minx = Integer.MAX_VALUE;
		maxy = 0;
		miny = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			maxx = Math.max(cows[i].x, maxx);
			minx = Math.min(cows[i].x, minx);
			maxy = Math.max(cows[i].y, maxy);
			miny = Math.min(cows[i].y, miny);
			areas[i] = (long)(maxx-minx)*(long)(maxy-miny);
		}
		maxx = 0;
		minx = Integer.MAX_VALUE;
		maxy = 0;
		miny = Integer.MAX_VALUE;
		for(int i = n-1; i >= 1; i--) {
			maxx = Math.max(cows[i].x, maxx);
			minx = Math.min(cows[i].x, minx);
			maxy = Math.max(cows[i].y, maxy);
			miny = Math.min(cows[i].y, miny);
			areas[i-1] += (long)(maxx-minx)*(long)(maxy-miny);
		}
		for(long area : areas) {
			minArea = Math.min(area, minArea);
			maxArea = Math.max(area, maxArea);
		}
		pw.println(maxArea-minArea);
		
		pw.close();
	}
	public static void transpose(Cow[] cows) {
		for(Cow cow : cows) {
			int temp = cow.x;
			cow.x = cow.y;
			cow.y = temp;
		}
	}
}
