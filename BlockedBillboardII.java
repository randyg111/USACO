// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class BlockedBillboardII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int l1 = Integer.parseInt(st.nextToken());
		int b1 = Integer.parseInt(st.nextToken());
		int r1 = Integer.parseInt(st.nextToken());
		int t1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(r.readLine());
		int l2 = Integer.parseInt(st.nextToken());
		int b2 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int t2 = Integer.parseInt(st.nextToken());

		if(l2 <= l1 && r2 >= r1) {
			if(b2 <= b1 && t2 >= t1) {
				pw.println(0);
			} else if(b2 <= b1 && t2 <= t1) {
				pw.println((r1-l1)*(t1-t2));
			} else if(t2 >= t1 && b2 <= t1) {
				pw.println((r1-l1)*(b2-b1));
			} else {
				pw.println((r1-l1)*(t1-b1));
			}
		} else if(b2 <= b1 && t2 >= t1) {
			if(l2 <= l1 && r2 >= l1) {
				pw.println((r1-r2)*(t1-b1));
			} else if(r2 >= r1 && l2 <= r1) {
				pw.println((l2-l1)*(t1-b1));
			} else {
				pw.println((r1-l1)*(t1-b1));
			}
		} else {
			pw.println((r1-l1)*(t1-b1));
		}

		pw.close();
	}
}
