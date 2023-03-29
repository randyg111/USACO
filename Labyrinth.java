// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class Labyrinth {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int startx = 0, starty = 0, endx = 0, endy = 0;
		char[][] map = new char[n][m];
		int[][] dist = new int[n][m];
		char[][] dir = new char[n][m];
		for(int i = 0; i < n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		for(int i = 0; i < n; i++) {
			String s = r.readLine();
			for(int j = 0; j < m; j++) {
				char c = s.charAt(j);
				map[i][j] = c;
				if(c == 'A') {
					startx = i;
					starty = j;
				} else if(c == 'B') {
					endx = i;
					endy = j;
				}
			}
		}
		dist[startx][starty] = 0;
		Queue<int[]> bfs = new LinkedList<>();
		bfs.add(new int[]{startx, starty});
		while(!bfs.isEmpty()) {
			int[] coords = bfs.poll();
			boolean found = false;
			boolean add = false;
			int i = coords[0];
			int j = coords[1];
			if(i > 0 && dist[i-1][j] == Integer.MAX_VALUE) {
				if(map[i-1][j] == '.') {
					bfs.add(new int[]{i-1, j});
					add = true;
				} else if(map[i-1][j] == 'B') {
					add = true;
					found = true;
				}
				if(add) {
					dist[i-1][j] = dist[i][j] + 1;
					dir[i-1][j] = 'U';
					add = false;
				}
			}
			if(j > 0 && dist[i][j-1] == Integer.MAX_VALUE) {
				if(map[i][j-1] == '.') {
					bfs.add(new int[]{i, j-1});
					add = true;
				} else if(map[i][j-1] == 'B') {
					add = true;
					found = true;
				}
				if(add) {
					dist[i][j-1] = dist[i][j] + 1;
					dir[i][j-1] = 'L';
					add = false;
				}
				
			}
			if(j < dist[0].length-1 && dist[i][j+1] == Integer.MAX_VALUE) {
				if(map[i][j+1] == '.') {
					bfs.add(new int[]{i, j+1});
					add = true;
				} else if(map[i][j+1] == 'B') {
					add = true;
					found = true;
				}
				if(add) {
					dist[i][j+1] = dist[i][j] + 1;
					dir[i][j+1] = 'R';
					add = false;
				}
			}
			if(i < dist.length-1 && dist[i+1][j] == Integer.MAX_VALUE) {
				if(map[i+1][j] == '.') {
					bfs.add(new int[]{i+1, j});
					add = true;
				} else if(map[i+1][j] == 'B') {
					add = true;
					found = true;
				}
				if(add) {
					dist[i+1][j] = dist[i][j] + 1;
					dir[i+1][j] = 'D';
					add = false;
				}
			} 
			if(found) break;
		}
		if(dist[endx][endy] == Integer.MAX_VALUE) {
			pw.println("NO");
		} else {
			pw.println("YES");
			pw.println(dist[endx][endy]);
			int x = endx, y = endy;
			Deque<Character> rev = new LinkedList<>();
			while(x != startx || y != starty) {
				rev.addFirst(dir[x][y]);
				if(dir[x][y] == 'L') {
					y++;
				} else if(dir[x][y] == 'R') {
					y--;
				} else if(dir[x][y] == 'U') {
					x++;
				} else if(dir[x][y] == 'D') {
					x--;
				} else {
					break;
				}
			}
			while(!rev.isEmpty()) {
				pw.print(rev.remove());
			}
		}
		
		pw.close();
	}
}
