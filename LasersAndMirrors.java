// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class LasersAndMirrors {
	private static class Line {
		int val;
		char dir;
		int hashCode;

		public Line(int i, char c) {
			val = i;
			dir = c;
			this.hashCode = Objects.hash(i, c);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Line that = (Line) o;
			return val == that.val && dir == that.dir;
		}

		@Override
		public int hashCode() {
			return this.hashCode;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("lasers.in"));
		PrintWriter pw = new PrintWriter("lasers.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int xl = Integer.parseInt(st.nextToken());
		int yl = Integer.parseInt(st.nextToken());
		int xb = Integer.parseInt(st.nextToken());
		int yb = Integer.parseInt(st.nextToken());
		Map<Line, Integer> dist = new HashMap<>();
		Map<Integer, ArrayList<Integer>> xy = new HashMap<>();
		Map<Integer, ArrayList<Integer>> yx = new HashMap<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(!xy.containsKey(x)) xy.put(x, new ArrayList<Integer>());
			if(!yx.containsKey(y)) yx.put(y, new ArrayList<Integer>());
			xy.get(x).add(y);
			yx.get(y).add(x);
			dist.put(new Line(x, 'v'), -1);
			dist.put(new Line(y, 'h'), -1);
		}
		dist.put(new Line(xl, 'v'), 0);
		dist.put(new Line(yl, 'h'), 0);
		Queue<Line> bfs = new LinkedList<>();
		bfs.add(new Line(xl, 'v'));
		bfs.add(new Line(yl, 'h'));
		int d = -1;
		while(!bfs.isEmpty()) {
			Line l = bfs.poll();
			if(l.dir == 'h') {
				if(l.val == yb) {
					d = dist.get(l);
					break;
				} else if(yx.containsKey(l.val)) {
					for(int x : yx.get(l.val)) {
						Line m = new Line(x, 'v');
						if(dist.get(m) == -1) {
							dist.put(m, dist.get(l)+1);
							bfs.add(m);
						}
					}
				}
			} else {
				if(l.val == xb) {
					d = dist.get(l);
					break;
				} else if(xy.containsKey(l.val)) {
					for(int y : xy.get(l.val)) {
						Line m = new Line(y, 'h');
						if(dist.get(m) == -1) {
							dist.put(m, dist.get(l)+1);
							bfs.add(m);
						}
					}
				}
			}
		}
		pw.println(d);
		
		pw.close();
	}
}
