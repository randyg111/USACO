// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class DistinctValuesQueries {
	static class BIT {
		int[] bit;
		int size;

		public BIT(int n) {
			bit = new int[n+1];
			size = n;
		}
		public void add(int i, int v) {
			for(i++; i <= size; i += i&-i) {
				bit[i] += v;
			}
		}
		public int sum(int i) {
			int s = 0;
			for(i++; i > 0; i -= i&-i) {
				s += bit[i];
			}
			return s;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(r.readLine());
		int[] arr = new int[n];
		for(int i = 0; i < n; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		List<int[]>[] queries = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			queries[i] = new ArrayList<int[]>();
		}
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			a--;
			b--;
			queries[a].add(new int[]{b, i});
		}
		Map<Integer, Integer> last = new HashMap<>();
		BIT bit = new BIT(n);
		int[] solution = new int[q];
		for(int i = n-1; i >= 0; i--) {
			int val = arr[i];
			if(last.containsKey(val)) {
				bit.add(last.get(val), -1);
			}
			last.put(val, i);
			bit.add(i, 1);
			for(int[] pair : queries[i]) {
				int b = pair[0];
				int index = pair[1];
				solution[index] = bit.sum(b);
			}
		}
		for(int i = 0; i < q; i++) {
			pw.println(solution[i]);
		}
		
		pw.close();
	}
}
