// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class TreetopTreeHouse {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		List<List<Integer>> grid = new ArrayList<>();
		List<List<Boolean>> vis = new ArrayList<>();
		String line = r.readLine();
		while(line != null) {
			List<Integer> row = new ArrayList<>();
			List<Boolean> v = new ArrayList<>();
			for(int i = 0; i < line.length(); i++) {
				v.add(false);
				row.add(Character.getNumericValue(line.charAt(i)));
			}
			grid.add(row);
			vis.add(v);
			line = r.readLine();
		}
		for(int i = 0; i < grid.size(); i++) {
			int max = -1;
			for(int j = 0; j < grid.get(0).size(); j++) {
				if(grid.get(i).get(j) > max) {
					vis.get(i).set(j, true);
					max = grid.get(i).get(j);
				}
			}
		}
		for(int i = 0; i < grid.size(); i++) {
			int max = -1;
			for(int j = grid.get(0).size()-1; j >= 0; j--) {
				if(grid.get(i).get(j) > max) {
					vis.get(i).set(j, true);
					max = grid.get(i).get(j);
				}
			}
		}
		for(int j = 0; j < grid.get(0).size(); j++) {
			int max = -1;
			for(int i = 0; i < grid.size(); i++) {
				if(grid.get(i).get(j) > max) {
					vis.get(i).set(j, true);
					max = grid.get(i).get(j);
				}
			}
		}
		for(int j = 0; j < grid.get(0).size(); j++) {
			int max = -1;
			for(int i = grid.size()-1; i >= 0; i--) {
				if(grid.get(i).get(j) > max) {
					vis.get(i).set(j, true);
					max = grid.get(i).get(j);
				}
			}
		}
		int count = 0;
		for(int i = 0; i < grid.size(); i++) {
			for(int j = 0; j < grid.get(0).size(); j++) {
				if(vis.get(i).get(j)) count++;
			}
		}
		pw.println(count);
		
		pw.close();
	}
}
