// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class RucksackReorganizationII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int sum = 0;
		String line = r.readLine();
		while(line != null) {
			Set<Character> a = new HashSet<>();
			Set<Character> b = new HashSet<>();
			Set<Character> d = new HashSet<>();

			for(char c : line.toCharArray())
				a.add(c);

			line = r.readLine();
			for(char c : line.toCharArray())
				b.add(c);
			
			line = r.readLine();
			for(char c : line.toCharArray())
				d.add(c);
			a.retainAll(b);
			a.retainAll(d);
			for(char c : a) {
				// magic numbers
				if(c >= 'a')
					sum += c-96;
				else
					sum += c-38;
			}
			line = r.readLine();
		}
		pw.println(sum);
		
		pw.close();
	}
}
