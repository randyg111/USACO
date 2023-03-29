// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class MagicShip {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		long x1 = Long.parseLong(st.nextToken());
		long y1 = Long.parseLong(st.nextToken());
		st = new StringTokenizer(r.readLine());
		long x2 = Long.parseLong(st.nextToken());
		long y2 = Long.parseLong(st.nextToken());
		int n = Integer.parseInt(r.readLine());
		String s = r.readLine();
		long[][] wind = new long[n][2];
		long[][] winds = new long[n+1][2];
		for(int i = 0; i < n; i++) {
			char d = s.charAt(i);
			if(d == 'U') {
				wind[i][1]++;
			} else if(d == 'D') {
				wind[i][1]--;
			} else if(d == 'L') {
				wind[i][0]--;
			} else if(d == 'R') {
				wind[i][0]++;
			}
		}
		for(int i = 1; i <= n; i++) {
			winds[i][0] = winds[i-1][0]+wind[i-1][0];
			winds[i][1] = winds[i-1][1]+wind[i-1][1];
		}
		long lo = 0;
		long hi = (long)2e14+1;
		while(lo < hi) {
			long mid = (lo+hi)/2;
			if(reachable(x1, x2, y1, y2, mid, winds)) {
				hi = mid;
			} else {
				lo = mid+1;
			}
		}
		pw.println(hi == (long)2e14+1 ? -1 : hi);

		pw.close();
	}
	public static boolean reachable(long x1, long x2, long y1, long y2, long days, long[][] winds) {
		long cycles = days/(winds.length-1);
		int extra = (int)(days%(winds.length-1));
		long windx = winds[winds.length-1][0]*cycles + winds[extra][0];
		long windy = winds[winds.length-1][1]*cycles + winds[extra][1];
		return Math.abs(x2-x1-windx)+Math.abs(y2-y1-windy) <= days;
	}
}
