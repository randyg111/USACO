// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class TuningTroubleII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String line = r.readLine();
		int[] freq = new int[26];
		for(int i = 0; i < 14; i++) {
			freq[line.charAt(i)-97]++;
		}
		int index = -1;
		for(int i = 14; i < line.length(); i++) {
			boolean rep = false;
			for(int j = 0; j < 26; j++) {
				if(freq[j] > 1) rep = true;
			}
			if(!rep) {
				index = i;
				break;
			} else {
				freq[line.charAt(i)-97]++;
				freq[line.charAt(i-14)-97]--;
			}
		}
		pw.println(index);
		
		pw.close();
	}
}
