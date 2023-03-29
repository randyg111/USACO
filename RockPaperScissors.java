// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class RockPaperScissors {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int score = 0;
		String line = r.readLine();
		while(line != null) {
			char a = line.charAt(0);
			char b = line.charAt(2);
			if(a == 'A' && b == 'X') {
				score += 4;
			} else if(a == 'A' && b == 'Y') {
				score += 8;
			} else if(a == 'A' && b == 'Z') {
				score += 3;
			} else if(a == 'B' && b == 'X') {
				score += 1;
			} else if(a == 'B' && b == 'Y') {
				score += 5;
			} else if(a == 'B' && b == 'Z') {
				score += 9;
			} else if(a == 'C' && b == 'X') {
				score += 7;
			} else if(a == 'C' && b == 'Y') {
				score += 2;
			} else if(a == 'C' && b == 'Z') {
				score += 6;
			}
			line = r.readLine();
		}
		pw.println(score);
		
		pw.close();
	}
}
