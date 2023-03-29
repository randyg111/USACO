// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class QueueComposite {
	public static final long MOD = 998244353;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int q = Integer.parseInt(st.nextToken());
		int start = 0;
		int end = -1;
		long[][] func = new long[500000][2];
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(r.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n == 0) {
				long a = Long.parseLong(st.nextToken());
				long b = Long.parseLong(st.nextToken());
				end++;
				func[end][0] = a;
				func[end][1] = b;
			} else if(n == 1) {
				start++;
			} else {
				long x = Long.parseLong(st.nextToken());
				for(int j = start; j <= end; j++) {
					x = (x%MOD * func[j][0]) % MOD;
					x = (x%MOD + func[j][1]) % MOD;
				}
				pw.println(x);
			}
		}
		
		pw.close();
	}
}
