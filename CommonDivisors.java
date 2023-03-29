// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class CommonDivisors {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(r.readLine());
		int[] freq = new int[(int)1e6+1];
		StringTokenizer st = new StringTokenizer(r.readLine());
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			freq[x]++;
		}
		int gcd = 1;
		for(int i = (int)1e6; i > 0; i--) {
			int count = 0;
			for(int j = i; j <= 1e6; j += i) {
				count += freq[j];
			}
			if(count >= 2) {
				gcd = i;
				break;
			}
		}
		pw.println(gcd);
		
		pw.close();
	}
}
