// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class ArrayDivision {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		long max = 0;
		st = new StringTokenizer(r.readLine());
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			arr[i] = x;
			max += x;
		}
		long lo = 0;
		long hi = max;
		while(lo < hi) {
			long mid = (lo+hi)/2;
			if(check(mid, arr, k)) {
				hi = mid;
			} else {
				lo = mid+1;
			}
		}
		pw.println(hi);
		
		pw.close();
	}
	public static boolean check(long sum, int[] arr, int k) {
		int subs = 1;
		long subSum = 0;
		for(int i = 0; i < arr.length; i++) {
			if(subs > k || arr[i] > sum) return false;
			if(subSum+arr[i] > sum) {
				subs++;
				subSum = 0;
			}
			subSum += arr[i];
		}
		return subs <= k;
	}
}
