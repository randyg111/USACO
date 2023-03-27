// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

public class DistinctNumbers {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(r.readLine());
		Set<Integer> nums = new HashSet<>();
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			nums.add(x);
		}
		pw.println(nums.size());
		
		pw.close();
	}
}
