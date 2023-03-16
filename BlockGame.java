// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

public class BlockGame {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("blocks.in"));
		PrintWriter pw = new PrintWriter("blocks.out");

		int n = Integer.parseInt(r.readLine());
		int[] blocks = new int[26];
		for(int i = 0; i < n; i++) {
			int[] letters = new int[26];
			StringTokenizer st = new StringTokenizer(r.readLine());
			String word1 = st.nextToken();
			String word2 = st.nextToken();
			for(int j = 0; j < word1.length(); j++) {
				int val = word1.charAt(j)-97;
				letters[val]++;
				blocks[val]++;
			}
			for(int j = 0; j < word2.length(); j++) {
				int val = word2.charAt(j)-97;
				if(letters[val] == 0) {
					blocks[val]++;
				} else {
					letters[val]--;
				}
			}
		}
		for(int i = 0; i < 26; i++) {
			pw.println(blocks[i]);
		}

		pw.close();
	}
}
