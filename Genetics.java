// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class Genetics {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		String[] seq = new String[n];
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			seq[i] = s;
		}
		int[] val = new int['Z'];
		val['A'] = 0;
		val['C'] = 1;
		val['G'] = 2;
		val['T'] = 3;
		int[] weight = new int[n];
		long total = 0;
		for(int i = 0; i < n; i++) {
			int rand = (int) (Math.random() * Integer.MAX_VALUE);
			weight[i] = rand;
			total += rand;
		}
		long[][] count = new long[m][4];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				count[j][val[seq[i].charAt(j)]] += weight[i];
			}
		}
		int index = -1;
		for(int i = 0; i < n; i++) {
			long sum = 0;
			for(int j = 0; j < m; j++) {
				char c = seq[i].charAt(j);
				for(int v = 0; v < 4; v++) {
					if(val[seq[i].charAt(j)] != v) sum += count[j][v];
				}
			}
			if(sum == (total - weight[i]) * k) {
				index = i+1;
				break;
			}
		}
		pw.println(index);
		
		pw.close();
	}
}
