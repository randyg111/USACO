// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class MountainView {
	static class Peak implements Comparable<Peak> {
		int x, y;
		public Peak(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(Peak p) {
			if(p.x+p.y != x+y) return Integer.compare(p.x+p.y, x+y);
			return Integer.compare(p.y-p.x, y-x);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		Peak[] peaks = new Peak[n];
		int count = 0;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			peaks[i] = new Peak(x, y);
		}
		Arrays.sort(peaks);
		for(int i = 0; i < n; i++) {
			if(peaks[i].y-peaks[i].x > max) {
				max = peaks[i].y-peaks[i].x;
				count++;
			}
		}
		pw.println(count);
		
		pw.close();
	}
}
