// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class CalorieCounting {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int max = 0;
		int sum = 0;
		String line = r.readLine();
		while(line != null) {
			if(line.equals("")) {
				max = Math.max(max, sum);
				sum = 0;
			} else {
				int cal = Integer.parseInt(line);
				sum += cal;
			}
			line = r.readLine();
		}
		pw.println(max);
		
		pw.close();
	}
}
