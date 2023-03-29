// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class RentalService {
	static int[] cows;
	static Store[] stores;
	static long[] prefix;
	static long[] rents;
	static class Store implements Comparable<Store> {
		int gallons, cents;
		public Store(int a, int b) {
			this.gallons = a;
			this.cents = b;
		}
		public int compareTo(Store p) {
			return Integer.compare(p.cents, cents);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("rental.in"));
		PrintWriter pw = new PrintWriter("rental.out");

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		cows = new int[n];
		stores = new Store[m];
		prefix = new long[n];
		rents = new long[r];
		long profit = 0;
		for(int i = 0; i < n; i++) {
			cows[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			stores[i] = new Store(a, b);
		}
		for(int i = 0; i < r; i++) {
			rents[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(cows);
		Arrays.sort(stores);
		Arrays.sort(rents);
		for(int i = 0; i < n/2; i++) {
			int temp = cows[i];
			cows[i] = cows[n-1-i];
			cows[n-1-i] = temp;
		}
		for(int i = 0; i < r/2; i++) {
			long temp = rents[i];
			rents[i] = rents[r-1-i];
			rents[r-1-i] = temp;
		}
		for(int i = 1; i < r; i++) {
			rents[i] += rents[i-1];
		}
		int s = 0;
		for(int i = 0; i < n; i++) {
			int milk = cows[i];
			while(milk > 0 && s < m) {
				if(milk >= stores[s].gallons) {
					prefix[i] += stores[s].gallons * stores[s].cents;
					milk -= stores[s].gallons;
					s++;
				} else {
					prefix[i] += milk * stores[s].cents;
					stores[s].gallons -= milk;
					milk = 0;
				}
			}
			if(i > 0) prefix[i] += prefix[i-1];
		}
		for(int i = 0; i <= n; i++) {
			profit = Math.max(profit, sell(i)+rent(n-i));
		}
		pw.println(profit);
			
		pw.close();
	}
	public static long sell(int cow) {
		if(cow == 0) return 0;
		return prefix[cow-1];
	}
	public static long rent(int cow) {
		if(cow == 0) return 0;
		if(cow-1 >= rents.length) {
			return rents[rents.length-1];
		}
		return rents[cow-1];
	}
}
