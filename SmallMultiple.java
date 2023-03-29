// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class SmallMultiple {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int k = Integer.parseInt(r.readLine());
		boolean[] visited = new boolean[k];
		int weight = 0;
		Deque<int[]> bfs = new LinkedList<>();
		bfs.add(new int[]{1, 0});
		while(!bfs.isEmpty()) {
			int[] nw = bfs.removeFirst();
			int n = nw[0];
			weight = nw[1];
			if(n == 0) break;
			if(!visited[n]) {
				visited[n] = true;
				bfs.addFirst(new int[]{10*n % k, weight});
				bfs.addLast(new int[]{(n+1) % k, weight+1});
			}
		}
		weight++;
		pw.println(weight);
		
		pw.close();
	}
}
