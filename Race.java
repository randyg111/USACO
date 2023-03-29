// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class Race {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(r.readLine());
			pw.println(finish(k, x));
		}
		
		pw.close();
	}
	public static int finish(int k, int x) {
		int meters = k;
		int time = 0;
		for(int i = 1; i < x; i++) {
			k -= i;
			time++;
			if(k <= 0) return time;
		}
		for(int i = x; i < meters; i++) {
			k -= i;
			time++;
			if(k <= 0) return time;
			k -= i;
			time++;
			if(k <= 0) return time;
		}
		return time;
	}
}
