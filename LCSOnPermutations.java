// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class LCSOnPermutations {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] seq1 = new int[n+1];
		int[] seq2 = new int[n+1];
		int[] pos = new int[n+1];
		int[] seq3 = new int[n+1];
		st = new StringTokenizer(r.readLine());
		for(int i = 1; i <= n; i++) {
			int a = Integer.parseInt(st.nextToken());
			seq1[i] = a;
			pos[a] = i;
		}
		st = new StringTokenizer(r.readLine());
		for(int i = 1; i <= n; i++) {
			int b = Integer.parseInt(st.nextToken());
			seq2[i] = b;
			seq3[i] = pos[b];
		}
		TreeSet<Integer> seq = new TreeSet<>();
		int lis = 0;
		for(int i = 1; i <= n; i++) {
			if(seq.higher(seq3[i]) == null) lis++;
			else seq.remove(seq.higher(seq3[i]));
			seq.add(seq3[i]);
		}
		pw.println(lis);
		
		pw.close();
	}
}
