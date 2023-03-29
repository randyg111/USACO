// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class TreetopTreeHouseII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		List<List<Integer>> grid = new ArrayList<>();
		List<List<Long>> score = new ArrayList<>();
		String line = r.readLine();
		while(line != null) {
			List<Integer> row = new ArrayList<>();
			List<Long> s = new ArrayList<>();
			for(int i = 0; i < line.length(); i++) {
				s.add(1l);
				row.add(Character.getNumericValue(line.charAt(i)));
			}
			grid.add(row);
			score.add(s);
			line = r.readLine();
		}

		for(int i = 0; i < grid.size(); i++) {
			for(int j = 0; j < grid.get(0).size(); j++) {
				int height = grid.get(i).get(j);
				long sc = 1;
				int view = 0;
				for(int k = i-1; k >= 0; k--) {
					view++;
					if(grid.get(k).get(j) >= height) break;
				}
				sc *= view;

				view = 0;
				for(int k = j+1; k < grid.get(0).size(); k++) {
					view++;
					if(grid.get(i).get(k) >= height) break;
				}
				sc *= view;

				view = 0;
				for(int k = i+1; k < grid.size(); k++) {
					view++;
					if(grid.get(k).get(j) >= height) break;
				}
				sc *= view;

				view = 0;
				for(int k = j-1; k >= 0; k--) {
					view++;
					if(grid.get(i).get(k) >= height) break;
				}
				sc *= view;
				score.get(i).set(j, sc);
			}
		}

		long max = 0;
		for(int i = 0; i < grid.size(); i++) {
			for(int j = 0; j < grid.get(0).size(); j++) {
				max = Math.max(max, score.get(i).get(j));
			}
		}
		pw.println(max);
		
		pw.close();
	}
}
