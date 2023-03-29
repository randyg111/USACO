// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class EditDistance {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String a = r.readLine();
		String b = r.readLine();
		int[][] dp = new int[a.length()+1][b.length()+1];
		for(int i = 0; i <= a.length(); i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		dp[0][0] = 0;
		for(int i = 0; i <= a.length(); i++) {
			for(int j = 0; j <= b.length(); j++) {
				if(i > 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+1);
				if(j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+1);
				if(i > 0 && j > 0) {
					int cost = 1;
					if(a.charAt(i-1) == b.charAt(j-1)) cost = 0;
					dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]+cost);
				}
			}
		}
		pw.println(dp[a.length()][b.length()]);
		// display(a, b, dp);

		pw.close();
	}
	public static void display(String a, String b, int[][] dp) {
		System.err.print("  ");
		for(int i = 0; i < b.length(); i++) {
			System.err.print(b.charAt(i)+" ");
		}
		System.err.println();
		for(int i = 0; i < a.length(); i++) {
			System.err.print(a.charAt(i)+" ");
			for(int j = 0; j < b.length(); j++) {
				System.err.print(dp[i][j]+" ");
			}
			System.err.println();
		}
	}
}
