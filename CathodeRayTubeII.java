// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class CathodeRayTubeII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String line = r.readLine();
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
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 40; j++) {
				if(Math.abs(j - val[i * 40 + j + 1]) <= 1) {
					pw.print("#");
				} else {
					pw.print(".");
				}
			}
			pw.println();
		}
		
		pw.close();
	}
}
