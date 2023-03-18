// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

public class WhyDidTheCowCrossTheRoadII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String circle = r.readLine();
		int crosses = 0;
		for(char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
			Set<Character> cross = new HashSet<>();
			int first = circle.indexOf(alphabet);
			int last = circle.lastIndexOf(alphabet);
			for(int i = first+1; i < last; i++) {
				char point = circle.charAt(i);
				if(cross.contains(point)) {
					cross.remove(point);
				} else {
					cross.add(point);
				}
			}
			crosses += cross.size();
		}
		pw.println(crosses/2);
		
		pw.close();
	}
}
