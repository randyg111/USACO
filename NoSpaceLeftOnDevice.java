// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class NoSpaceLeftOnDevice {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		Map<Integer, Long> size = new HashMap<>();
		Map<Integer, Integer> parent = new HashMap<>();
		Map<Integer, List<Integer>> child = new HashMap<>();
		Map<Integer, Boolean> done = new HashMap<>();
		Deque<Integer> files = new LinkedList<>();
		Map<Integer, Integer> it = new HashMap<>();

		String line = r.readLine();
		boolean ls = false;
		int curr = 0;
		size.put(0, 0l);
		List<Integer> childs = new ArrayList<>();
		child.put(0, childs);
		done.put(0, false);
		it.put(0, 0);

		int id = 1;
		long test = 0;
		long total = 0;
		while(line != null) {
			StringTokenizer st = new StringTokenizer(line);
			String s = st.nextToken();
			if(ls) {
				if(s.equals("$")) {
					ls = false;
					size.put(curr, size.get(curr) + total);
					total = 0;
				} else if(s.equals("dir")) {
					parent.put(id, curr);
					size.put(id, 0l);
					it.put(id, 0);
					List<Integer> children = new ArrayList<>();
					child.put(id, children);
					child.get(curr).add(id);
					done.put(id, false);
					id++;
				} else {
					int len = Integer.parseInt(s);
					total += len;
					test += len;
				}
			}
			if(!ls) {
				String c = st.nextToken();
				if(c.equals("cd")) {
					String f = st.nextToken();
					if(f.equals("..")) {
						curr = parent.get(curr);
					} else if(f.equals("/")) {
						curr = 0;
					} else {
						curr = child.get(curr).get(it.get(curr));
						it.put(parent.get(curr), it.get(parent.get(curr))+1);
					}
				} else {
					ls = true;
				}
			}
			line = r.readLine();
		}
		size.put(curr, size.get(curr) + total);
		files.add(0);
		while(!files.isEmpty()) {
			if(done.get(files.peek()) || child.get(files.peek()).size() == 0) {
				int f = files.remove();
				if(f != 0) size.put(parent.get(f), size.get(parent.get(f)) + size.get(f));
			} else {
				done.put(files.peek(), true);
				for(int f : child.get(files.peek())) {
					files.addFirst(f);
				}
			}
		}
		long sum = 0;
		for(long val : size.values()) {
			if(val <= 100000) sum += val;
		}
		pw.println(sum);
		pw.println(size.get(0));
		pw.println(test);
		
		pw.close();
	}
}
