// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class SupplyStacksII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		List<Deque<Character>> stacks = new ArrayList<>();
		String line = r.readLine();
		int num = (line.length()-3)/4+1;
		for(int i = 0; i <= num; i++) {
			Deque<Character> stack = new LinkedList<>();
			stacks.add(stack);
		}
		while(!line.equals("")) {
			int index = line.indexOf("[");
			while(index != -1) {
				stacks.get(index/4+1).add(line.charAt(index+1));
				index = line.indexOf("[", index+1);
			}
			line = r.readLine();
		}
		line = r.readLine();
		while(line != null) {
			int i1 = line.indexOf("move");
			int i2 = line.indexOf("from");
			int i3 = line.indexOf("to");

			int n = Integer.parseInt(line.substring(i1+5, i2-1));
			int s1 = Integer.parseInt(line.substring(i2+5, i3-1));
			int s2 = Integer.parseInt(line.substring(i3+3));

			Iterator<Character> it = stacks.get(s1).descendingIterator();
			for(int i = 0; i < stacks.get(s1).size()-n; i++) {
				it.next();
			}
			for(int i = 0; i < n; i++) {
				stacks.get(s2).addFirst(it.next());
				it.remove();
			}

			line = r.readLine();
		}
		for(int i = 1; i < stacks.size(); i++) {
			pw.print(stacks.get(i).peek());
		}
	
		pw.close();
	}
}
