// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class TheMeetingPlaceCannotBeChanged {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] coord = new int[n];
		int[] speed = new int[n];
		st = new StringTokenizer(r.readLine());
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			coord[i] = x;
		}
		st = new StringTokenizer(r.readLine());
		for(int i = 0; i < n; i++) {
			int v = Integer.parseInt(st.nextToken());
			speed[i] = v;
		}
		double lo = 0;
		double hi = 1e9;
		double ans = 1e9;
		while(lo < hi) {
			double mid = (lo+hi)/2;
			if(works(mid, coord, speed)) {
				hi = mid-0.0000001;
				ans = mid;
			} else {
				lo = mid+0.0000001;
			}
		}
		pw.println(ans);
		
		pw.close();
	}
	public static boolean works(double t, int[] coord, int[] speed) {
		double left = 0;
		double right = 1e9;
		for(int i = 0; i < coord.length; i++) {
			left = Math.max(left, coord[i]-speed[i]*t);
			right = Math.min(right, coord[i]+speed[i]*t);
		}
		return left <= right;
	}
}
