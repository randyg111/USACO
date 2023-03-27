// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Kayaking {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(r.readLine());
		List<Integer> weights = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 2*n; i++) {
			int w = Integer.parseInt(st.nextToken());
			weights.add(w);
		}
		Collections.sort(weights);
		for(int i = 0; i < 2*n; i++) {
			for(int j = i+1; j < 2*n; j++) {
				int s = sumDiff(weights, i, j);
				if(s < min) {
					min = s;
				}
			}
		}
		pw.println(min);

		pw.close();
	}
	public static int sumDiff(List<Integer> weights, int i, int j) {
		int sum = 0;
		boolean skip1 = false;
		boolean skip2 = false;
		for(int k = 0; k < weights.size(); k+= 2) {
			if(!skip1 && i <= k) {
				skip1 = true;
				k++;
			}
			if(!skip2 && j <= k) {
				skip2 = true;
				k++;
			}
			if(k >= weights.size()-1) {
				break;
			}
			sum += weights.get(k+1)-weights.get(k);
		}
		return sum;
	}
}
