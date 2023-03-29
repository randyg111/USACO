// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.TreeSet;

public class AngryCows {
	static TreeSet<Long> bales = new TreeSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("angry.in"));
		PrintWriter pw = new PrintWriter("angry.out");

		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			bales.add(2L*Integer.parseInt(br.readLine()));
		}
		long loR = 0;
		long hiR = 1000000000;
		while(loR < hiR) {
			long midR = (loR+hiR)/2;
			long loX = bales.first();
			long hiX = bales.last();
			while(loX < hiX) {
				long midX = (loX+hiX+1)/2;
				if(detonatesLow(midX, midR)) {
					loX = midX;
				} else {
					hiX = midX-1;
				}
			}
			if(detonatesHigh(loX, midR)) {
				hiR = midR;
			} else {
				loR = midR+1;
			}
		}
		pw.printf("%.1f", loR/2.0);
		
		pw.close();
	}

	public static boolean detonatesLow(long x, long r) {
		long lo = x;
		long radius = r;
		while(lo != bales.first()) {
			long next = bales.ceiling(lo-radius);
			if(next >= lo) {
				return false;
			}
			lo = next;
			radius -= 2;
		}
		return true;
	}
	public static boolean detonatesHigh(long x, long r) {
		long hi = x;
		long radius = r;
		while(hi != bales.last()) {
			long next = bales.floor(hi+radius);
			if(next <= hi) {
				return false;
			}
			hi = next;
			radius -= 2;
		}
		return true;
	}
}
