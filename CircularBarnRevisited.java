// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class CircularBarnRevisited {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] rooms = new long[2*N+1];
		long[][] dp = new long[K+1][N+1];
		for(int i = 0; i < N; i++) {
			long r = Long.parseLong(br.readLine());
			rooms[i] = r;
			rooms[i+N] = r;
		}
		long result = Long.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < K; j++) {
				Arrays.fill(dp[j], Long.MAX_VALUE);
			}
			dp[0][N] = 0;

			for(int k = i+1; k <= K; k++) {
				for(int j = i; j < N; j++) {
					long val = 0;
					for(int a = j+1; a <= N; a++) {
						val += rooms[a-i-1] * (a-j-2*i-1);
						dp[k-i][j-i] = Math.min(dp[k-i][j-i], val + dp[k-i-1][a-i]);
					}
				}
			}
			result = Math.min(result, dp[K][0]);
		}
		pw.println(result);
		
		pw.close();
	}
}
