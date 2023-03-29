// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class CathodeRayTube {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String line = r.readLine();
		int sum = 0;
		int cycle = 1;
		int x = 1;
		int[] val = new int[1000];
		while(line != null) {
			String in = line.substring(0, 4);
			if(in.equals("noop")) {
				val[cycle] = x;
				cycle++;
			} else {
				int add = Integer.parseInt(line.substring(5));
				val[cycle] = x;
				val[cycle+1] = x;
				x += add;
				cycle += 2;
			}
			line = r.readLine();
		}
		sum += 20*val[20] + 60*val[60] + 100*val[100] + 140*val[140] + 180*val[180] + 220*val[220];
		pw.println(sum);
		
		pw.close();
	}
}
