// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

public class DontBeLast {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("notlast.in"));
		PrintWriter pw = new PrintWriter("notlast.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		Map<String, Integer> milk = new HashMap<>();
		milk.put("Bessie", 0);
		milk.put("Elsie", 0);
		milk.put("Daisy", 0);
		milk.put("Gertie", 0);
		milk.put("Annabelle", 0);
		milk.put("Maggie", 0);
		milk.put("Henrietta", 0);
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		String cow1 = "Tie";
		String cow2 = "Tie";
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			String cow = st.nextToken();
			int m = Integer.parseInt(st.nextToken());
			milk.put(cow, milk.get(cow)+m);
		}
		for(String c : milk.keySet()) {
			int m = milk.get(c);
			if(m < min1) {
				cow2 = cow1;
				min2 = min1;
				cow1 = c;
				min1 = m;
			} else if(m == min1) {
				cow1 = "Tie";
			} else if(m < min2) {
				cow2 = c;
				min2 = m;
			} else if(m == min2) {
				cow2 = "Tie";
			}
		}
		pw.println(cow2);
		
		pw.close();
	}
}
