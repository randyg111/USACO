// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class RucksackReorganization {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int sum = 0;
		String line = r.readLine();
		while(line != null) {
			Set<Character> a = new HashSet<>();
			Set<Character> b = new HashSet<>();

			for(char c : line.substring(0, line.length()/2).toCharArray())
				a.add(c);
			for(char c : line.substring(line.length()/2).toCharArray())
				b.add(c);
			a.retainAll(b);
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
