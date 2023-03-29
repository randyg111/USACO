// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class MonkeyInTheMiddleII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String line = r.readLine();
		List<Deque<Long>> items = new ArrayList<>();
		List<Character> op = new ArrayList<>();
		List<Integer> num = new ArrayList<>();
		List<Integer> test = new ArrayList<>();
		List<Integer> t = new ArrayList<>();
		List<Integer> f = new ArrayList<>();
		List<Long> count = new ArrayList<>();

		int mod = 1;

		while(line != null) {
			count.add(0l);
			Deque<Long> start = new LinkedList<>();
			line = r.readLine();
			String[] it = line.substring(18).split(", ");
			for(String s : it) {
				start.add(Long.parseLong(s));
			}
			items.add(start);

			line = r.readLine();
			char c = line.charAt(23);
			op.add(c);
			if(!Character.isDigit(line.charAt(25))) {
				num.add(0);
			} else {
				num.add(Integer.parseInt(line.substring(25)));
			}

			line = r.readLine();
			int div = Integer.parseInt(line.substring(21));
			test.add(div);
			mod *= div;

			line = r.readLine();
			int m1 = Integer.parseInt(line.substring(29));
			line = r.readLine();
			int m2 = Integer.parseInt(line.substring(30));
			t.add(m1);
			f.add(m2);

			r.readLine();
			line = r.readLine();
		}

		for(int i = 0; i < 10000; i++) {
			for(int m = 0; m < items.size(); m++) {
				count.set(m, count.get(m) + items.get(m).size());
				while(!items.get(m).isEmpty()) {
					long item = items.get(m).remove();
					if(op.get(m) == '+') {
						item += num.get(m);
					} else {
						if(num.get(m) == 0) {
							item *= item;
						} else {
							item *= num.get(m);
						}
					}
					item %= mod;
					if(item % test.get(m) == 0) {
						items.get(t.get(m)).add(item);
					} else {
						items.get(f.get(m)).add(item);
					}
				}
			}
		}
		long max1 = 0;
		long max2 = 0;
		for(long c : count) {
			if(c > max1) {
				max2 = max1;
				max1 = c;
			} else if(c > max2) {
				max2 = c;
			}
		}
		pw.println(max1 * max2);
		
		pw.close();
	}
}
