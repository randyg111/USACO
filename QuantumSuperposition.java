// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class QuantumSuperposition {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());
		int m1 = Integer.parseInt(st.nextToken());
		int m2 = Integer.parseInt(st.nextToken());
		boolean[][] dp1 = new boolean[n1+1][1001];
		boolean[][] dp2 = new boolean[n2+1][1001];
		boolean[] dp = new boolean[2001];
		int[] inDeg1 = new int[n1+1];
		ArrayList<Integer>[] adj1 = new ArrayList[n1+1];
		for(int i = 1; i <= n1; i++) adj1[i] = new ArrayList<Integer>();
		for(int i = 0; i < m1; i++) {
			st = new StringTokenizer(r.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj1[u].add(v);
			inDeg1[v]++;
		}
		int[] inDeg2 = new int[n1+1];
		ArrayList<Integer>[] adj2 = new ArrayList[n2+1];
		for(int i = 1; i <= n2; i++) adj2[i] = new ArrayList<Integer>();
		for(int i = 0; i < m2; i++) {
			st = new StringTokenizer(r.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj2[u].add(v);
			inDeg2[v]++;
		}

		Queue<Integer> top = new LinkedList<>();
		for(int i = 1; i <= n1; i++) {
			if(inDeg1[i] == 0) top.add(i);
		}
		dp1[1][0] = true;
		while(!top.isEmpty()) {
			int i = top.poll();
			for(int j : adj1[i]) {
				for(int k = 0; k < 1000; k++) {
					if(dp1[i][k]) dp1[j][k+1] = true;
				}
				inDeg1[j]--;
				if(inDeg1[j] == 0) top.add(j);
			}
		}

		for(int i = 1; i <= n2; i++) {
			if(inDeg2[i] == 0) top.add(i);
		}
		dp2[1][0] = true;
		while(!top.isEmpty()) {
			int i = top.poll();
			for(int j : adj2[i]) {
				for(int k = 0; k < 1000; k++) {
					if(dp2[i][k]) dp2[j][k+1] = true;
				}
				inDeg2[j]--;
				if(inDeg2[j] == 0) top.add(j);
			}
		}

		for(int i = 0; i <= 1000; i++) {
			for(int j = 0; j <= 1000; j++) {
				if(dp1[n1][i] && dp2[n2][j]) {
					dp[i+j] = true;
				}
			}
		}

		int Q = Integer.parseInt(r.readLine());
		for(int i = 0; i < Q; i++) {
			int q = Integer.parseInt(r.readLine());
			if(dp[q]) {
				pw.println("Yes");
			} else {
				pw.println("No");
			}
		}
		
		pw.close();
	}
}
