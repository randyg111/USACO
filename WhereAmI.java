// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

public class WhereAmI {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("whereami.in"));
		PrintWriter pw = new PrintWriter("whereami.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		String colors = r.readLine();
		for(int i = 1; i <= n; i++) {
			Set<String> set = new HashSet<>();
			boolean found = true;
			for(int j = 0; j <= n-i; j++) {
				String s = colors.substring(j, j+i);
				if(set.contains(s)) {
					found = false;
					break;
				} else {
					set.add(s);
				}
			}
			if(found) {
				pw.println(i);
				break;
			}
		}
		
		pw.close();
	}
}
