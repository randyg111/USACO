// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class RangeUpdateQueries {
	static class BIT {
		private long[] bit;
		private int len;

		public BIT(int len) {
			bit = new long[len+1];
			this.len = len;
		}
		public void add(int i, int val) {
			for(i++; i <= len; i += (i&-i)) {
				bit[i] += val;
			}
		}
		public long sum(int i) {
			long s = 0;
			for(; i > 0; i -= (i&-i)) {
				s += bit[i];
			}
			return s;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(r.readLine());
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] diff = new int[n];
		diff[0] = arr[0];
		for(int i = 1; i < n; i++) {
			diff[i] = arr[i]-arr[i-1];
		}
		BIT bit = new BIT(n);
		for(int i = 0; i < n; i++) {
			bit.add(i, diff[i]);
		}
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(r.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int u = Integer.parseInt(st.nextToken());
				bit.add(a-1, u);
				if(b < n) bit.add(b, -u);
			} else {
				int k = Integer.parseInt(st.nextToken());
				pw.println(bit.sum(k));
			}
		}

		pw.close();
	}
}
