// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;

public class ModernArtII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("art2.in"));
		PrintWriter pw = new PrintWriter("art2.out");

		int n = Integer.parseInt(r.readLine());
		int[] painting = new int[n];
		boolean[] used = new boolean[n+1];
		int[] rounds = new int[n+1];
		for(int i = 0; i < n; i++) {
			painting[i] = Integer.parseInt(r.readLine());
		}
		Stack<int[]> stack = new Stack<>();
		boolean found = true;
		for(int i = 0; i < n; i++) {
			int color = painting[i];
			if(color == 0) {
				stack.clear();
			} else if(used[color]) {
				found = false;
				while(!stack.isEmpty()) {
					int[] pair = stack.pop();
					if(pair[0] == color) {
						found = true;
						rounds[pair[1]+1]++;
						break;
					}
				}
				if(found) {
					rounds[i+1]--;
					stack.add(new int[]{color, i});
				} else {
					break;
				}
			} else {
				used[color] = true;
				rounds[i]++;
				rounds[i+1]--;
				stack.add(new int[]{color, i});
			}
		}
		if(!found) {
			pw.println(-1);
		} else {
			int max = rounds[0];
			for(int i = 1; i < n; i++) {
				rounds[i] += rounds[i-1];
				max = Math.max(max, rounds[i]);
			}
			pw.println(max);
		}
		
		pw.close();
	}
}
