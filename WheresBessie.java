// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class WheresBessie{
	static char[][] image;
	static boolean[][] visited;
	static Set<Character> colors = new HashSet<>();
	static int[] count = new int[26];
	static class Rectangle {
		int left, right, top, bottom;
		public Rectangle (int l, int r, int t, int b) {
			left = l;
			right = r;
			top = t;
			bottom = b;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("where.in"));
		PrintWriter pw = new PrintWriter("where.out");

		int n = Integer.parseInt(br.readLine());
		image = new char[n][n];
		visited = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < n; j++) {
				image[i][j] = s.charAt(j);
			}
		}
		List<Rectangle> PCLs = new ArrayList<>();
		for(int l = 0; l < n; l++) {
			for(int r = l+1; r < n; r++) {
				for(int t = 0; t < n; t++) {
					for(int b = t+1; b < n; b++) {
						if(works(l, r, t, b)) {
							PCLs.add(new Rectangle(l, r, t, b));
						}
					}
				}
			}
		}
		int PCLcount = 0;
		for(int i = 0; i < PCLs.size(); i++) {
			Rectangle PCL1 = PCLs.get(i);
			boolean max = true;
			for(int j = 0; j < PCLs.size(); j++) {
				Rectangle PCL2 = PCLs.get(j);
				if(i != j && PCL1.left >= PCL2.left && PCL1.right <= PCL2.right 
				&& PCL1.top >= PCL2.top && PCL1.bottom <= PCL2.bottom) {
					max = false;
					break;
				}
			}
			if(max) PCLcount++;
		}
		pw.println(PCLcount);
		
		pw.close();
	}
	public static boolean works(int l, int r, int t, int b) {
		colors.clear();
		for(int i = t; i <= b; i++) {
			for(int j = l; j <= r; j++) {
				colors.add(image[i][j]);
			}
		}
		if(colors.size() != 2) return false;
		Arrays.fill(count, 0);
		for(int i = t; i <= b; i++) {
			for(int j = l; j <= r; j++) {
				visited[i][j] = false;
			}
		}
		for(int i = t; i <= b; i++) {
			for(int j = l; j <= r; j++) {
				if(!visited[i][j]) {
					ff(i, j, l, r, t, b);
					count[image[i][j]-'A']++;
				}
			}
		}
		boolean one = false;
		boolean multiple = false;
		for(Character color : colors) {
			if(count[color-'A'] == 1) one = true;
			else multiple = true;
		}
		return one && multiple;
	}
	public static void ff(int i, int j, int l, int r, int t, int b) {
		if(!visited[i][j]) {
			visited[i][j] = true;
			if(i > t && image[i-1][j] == image[i][j]) ff(i-1, j, l, r, t, b);
			if(i < b && image[i+1][j] == image[i][j]) ff(i+1, j, l, r, t, b);
			if(j > l && image[i][j-1] == image[i][j]) ff(i, j-1, l, r, t, b);
			if(j < r && image[i][j+1] == image[i][j]) ff(i, j+1, l, r, t, b);
		}
	}
}
