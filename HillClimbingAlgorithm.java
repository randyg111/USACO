// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class HillClimbingAlgorithm {
	static List<List<Character>> grid = new ArrayList<>();
	static List<List<Integer>> steps = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String line = r.readLine();
		int startx = 0, starty = 0, endx = 0, endy = 0;
		while(line != null) {
			List<Character> row = new ArrayList<>();
			List<Integer> step = new ArrayList<>();
			for(int i = 0; i < line.length(); i++) {
				if(line.charAt(i) == 'S') {
					startx = grid.size();
					starty = i;
					row.add('a');
				} else if(line.charAt(i) == 'E') {
					endx = grid.size();
					endy = i;
					row.add('z');
				} else {
					row.add(line.charAt(i));
				}
				step.add(Integer.MAX_VALUE);
			}
			grid.add(row);
			steps.add(step);
			line = r.readLine();
		}

		ff(startx, starty, 0);
		pw.println(steps.get(endx).get(endy));
		
		pw.close();
	}
	public static void ff(int i, int j, int s) {
		if(steps.get(i).get(j) > s) {
			steps.get(i).set(j, s);
			if(i < grid.size()-1 && grid.get(i+1).get(j) - grid.get(i).get(j) <= 1) ff(i+1, j, s+1);
			if(i > 0 && grid.get(i-1).get(j) - grid.get(i).get(j) <= 1) ff(i-1, j, s+1);
			if(j < grid.get(0).size()-1 && grid.get(i).get(j+1) - grid.get(i).get(j) <= 1) ff(i, j+1, s+1);
			if(j > 0 && grid.get(i).get(j-1) - grid.get(i).get(j) <= 1) ff(i, j-1, s+1);
		}
	}
}
