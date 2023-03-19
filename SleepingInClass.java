// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class SleepingInClass {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int t = Integer.parseInt(r.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(r.readLine());
			int sum = 0;
			int max = 0;
			List<Integer> periods = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(r.readLine());
			for(int j = 0; j < n; j++) {
				int a = Integer.parseInt(st.nextToken());
				sum += a;
				if(a > max) {
					max = a;
				}
				periods.add(a);
			}
			for(int j = max; j <= sum; j++) {
				if(j == 0) {
					pw.println(0);
				} else if(sum%j == 0) {
					boolean k = mods(j, periods);
					if(k) {
						pw.println(periods.size()-sum/j);
						break;
					}
				}
			}
		}
		
		pw.close();
	}
	public static boolean mods(int poss, List<Integer> periods) {
		int h = poss;
		for(int i = 0; i < periods.size(); i++) {
			h -= periods.get(i);
			if(h < 0) {
				return false;
			} else if (h == 0) {
				h = poss;
			}
		}
		return true;
	}
}
