// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class CowNavigation {
	private static class Vertex {
		int x1, y1, x2, y2;
		char dir1, dir2;
		int id;
		public Vertex(int i1, int j1, int i2, int j2, char d1, char d2) {
			x1 = i1;
			x2 = i2;
			y1 = j1;
			y2 = j2;
			dir1 = d1;
			dir2 = d2;
			id = Objects.hash(i1, j1, i2, j2, d1, d2);
		}
		public boolean equals(Object obj) {
			if(this == obj)
				return true;
			if(obj == null || obj.getClass()!= this.getClass())
				return false;
			Vertex v = (Vertex) obj;
			return (v.x1 == this.x1  && v.y1 == this.y1 &&
					v.dir1 == this.dir1 && v.x2 == this.x2  && v.y2 == this.y2 &&
					v.dir2 == this.dir2 && v.id == this.id);
		}

		public int hashCode() {
			return this.id;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("cownav.in"));
		PrintWriter pw = new PrintWriter("cownav.out");

		int n = Integer.parseInt(r.readLine());
		char[][] barn = new char[n][n];
		for(int i = n-1; i >= 0; i--) {
			barn[i] = r.readLine().toCharArray();
		}
		int length = 0;
		Map<Vertex, Integer> dist = new HashMap<>();
		Queue<Vertex> bfs = new LinkedList<>();
		Vertex vertex = new Vertex(0, 0, 0, 0, 'U', 'R');
		dist.put(vertex, 0);
		bfs.add(vertex);
		while(!bfs.isEmpty()) {
			Vertex v = bfs.poll();
			int dis = dist.get(v);
			boolean first = v.x1 == n-1 && v.y1 == n-1;
			boolean second = v.x2 == n-1 && v.y2 == n-1;
			if(first && second) {
				length = dis;
				break;
			}
			Vertex v1 = null, v2 = null, v3 = null;
			if(v.dir1 == 'U') {
				if(v.x1 < n-1 && v.y2 < n-1) {
					if(barn[v.x1+1][v.y1] == 'E' && barn[v.x2][v.y2+1] == 'E') {
						v1 = new Vertex(first ? v.x1 : v.x1+1, v.y1, v.x2, second ? v.y2 : v.y2+1, 'U', 'R');
					} else if(barn[v.x1+1][v.y1] == 'H' && barn[v.x2][v.y2+1] == 'E') {
						v1 = new Vertex(v.x1, v.y1, v.x2, second ? v.y2 : v.y2+1, 'U', 'R');
					} else if(barn[v.x1+1][v.y1] == 'E' && barn[v.x2][v.y2+1] == 'H') {
						v1 = new Vertex(first ? v.x1 : v.x1+1, v.y1, v.x2, v.y2, 'U', 'R');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'U', 'R');
					}
				} else if(v.x1 < n-1) {
					if(barn[v.x1+1][v.y1] == 'E') {
						v1 = new Vertex(first ? v.x1 : v.x1+1, v.y1, v.x2, v.y2, 'U', 'R');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'U', 'R');
					}
				} else if(v.y2 < n-1) {
					if(barn[v.x2][v.y2+1] == 'E') {
						v1 = new Vertex(v.x1, v.y1, v.x2, second ? v.y2 : v.y2+1, 'U', 'R');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'U', 'R');
					}
				} else {
					v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'U', 'R');
				}
				v2 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'L', 'U');
				v3 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'R', 'D');
			} else if(v.dir1 == 'R') {
				if(v.y1 < n-1 && v.x2 > 0) {
					if(barn[v.x1][v.y1+1] == 'E' && barn[v.x2-1][v.y2] == 'E') {
						v1 = new Vertex(v.x1, first ? v.y1 : v.y1+1, second ? v.x2 : v.x2-1, v.y2, 'R', 'D');
					} else if(barn[v.x1][v.y1+1] == 'H' && barn[v.x2-1][v.y2] == 'E') {
						v1 = new Vertex(v.x1, v.y1, second ? v.x2 : v.x2-1, v.y2, 'R', 'D');
					} else if(barn[v.x1][v.y1+1] == 'E' && barn[v.x2-1][v.y2] == 'H') {
						v1 = new Vertex(v.x1, first ? v.y1 : v.y1+1, v.x2, v.y2, 'R', 'D');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'R', 'D');
					}
				} else if(v.y1 < n-1) {
					if(barn[v.x1][v.y1+1] == 'E') {
						v1 = new Vertex(v.x1, first ? v.y1 : v.y1+1, v.x2, v.y2, 'R', 'D');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'R', 'D');
					}
				} else if(v.x2 > 0) {
					if(barn[v.x2-1][v.y2] == 'E') {
						v1 = new Vertex(v.x1, v.y1, second ? v.x2 : v.x2-1, v.y2, 'R', 'D');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'R', 'D');
					}
				} else {
					v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'R', 'D');
				}
				v2 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'U', 'R');
				v3 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'D', 'L');
			} else if(v.dir1 == 'D') {
				if(v.x1 > 0 && v.y2 > 0) {
					if(barn[v.x1-1][v.y1] == 'E' && barn[v.x2][v.y2-1] == 'E') {
						v1 = new Vertex(first ? v.x1 : v.x1-1, v.y1, v.x2, second ? v.y2 : v.y2-1, 'D', 'L');
					} else if(barn[v.x1-1][v.y1] == 'H' && barn[v.x2][v.y2-1] == 'E') {
						v1 = new Vertex(v.x1, v.y1, v.x2, second ? v.y2 : v.y2-1, 'D', 'L');
					} else if(barn[v.x1-1][v.y1] == 'E' && barn[v.x2][v.y2-1] == 'H') {
						v1 = new Vertex(first ? v.x1 : v.x1-1, v.y1, v.x2, v.y2, 'D', 'L');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'D', 'L');
					}
				} else if(v.x1 > 0) {
					if(barn[v.x1-1][v.y1] == 'E') {
						v1 = new Vertex(first ? v.x1 : v.x1-1, v.y1, v.x2, v.y2, 'D', 'L');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'D', 'L');
					}
				} else if(v.y2 > 0) {
					if(barn[v.x2][v.y2-1] == 'E') {
						v1 = new Vertex(v.x1, v.y1, v.x2, second ? v.y2 : v.y2-1, 'D', 'L');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'D', 'L');
					}
				} else {
					v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'D', 'L');
				}
				v2 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'R', 'D');
				v3 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'L', 'U');
			} else if(v.dir1 == 'L') {
				if(v.y1 > 0 && v.x2 < n-1) {
					if(barn[v.x1][v.y1-1] == 'E' && barn[v.x2+1][v.y2] == 'E') {
						v1 = new Vertex(v.x1, first ? v.y1 : v.y1-1, second ? v.x2 : v.x2+1, v.y2, 'L', 'U');
					} else if(barn[v.x1][v.y1-1] == 'H' && barn[v.x2+1][v.y2] == 'E') {
						v1 = new Vertex(v.x1, v.y1, second ? v.x2 : v.x2+1, v.y2, 'L', 'U');
					} else if(barn[v.x1][v.y1-1] == 'E' && barn[v.x2+1][v.y2] == 'H') {
						v1 = new Vertex(v.x1, first ? v.y1 : v.y1-1, v.x2, v.y2, 'L', 'U');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'L', 'U');
					}
				} else if(v.y1 > 0) {
					if(barn[v.x1][v.y1-1] == 'E') {
						v1 = new Vertex(v.x1, first ? v.y1 : v.y1-1, v.x2, v.y2, 'L', 'U');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'L', 'U');
					}
				} else if(v.x2 < n-1) {
					if(barn[v.x2+1][v.y2] == 'E') {
						v1 = new Vertex(v.x1, v.y1, second ? v.x2 : v.x2+1, v.y2, 'L', 'U');
					} else {
						v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'L', 'U');
					}
				} else {
					v1 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'L', 'U');
				}
				v2 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'D', 'L');
				v3 = new Vertex(v.x1, v.y1, v.x2, v.y2, 'U', 'R');
			}
			if(!dist.keySet().contains(v1)) {
				bfs.add(v1);
				dist.put(v1, dis+1);
			}
			if(!dist.keySet().contains(v2)) {
				bfs.add(v2);
				dist.put(v2, dis+1);
			}
			if(!dist.keySet().contains(v3)) {
				bfs.add(v3);
				dist.put(v3, dis+1);
			}
		}
		pw.println(length);

		pw.close();
	}
}
