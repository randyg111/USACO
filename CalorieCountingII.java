// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class CalorieCountingII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int max1 = 0;
		int max2 = 0;
		int max3 = 0;
		int sum = 0;
		String line = r.readLine();
		while(line != null) {
			if(line.equals("")) {
				if(sum > max1) {
					max3 = max2;
					max2 = max1;
					max1 = sum;
				} else if(sum > max2) {
					max3 = max2;
					max2 = sum;
				} else if(sum > max3) {
					max3 = sum;
				}
				sum = 0;
			} else {
				int cal = Integer.parseInt(line);
				sum += cal;
			}
			line = r.readLine();
		}
		if(sum > max1) {
			max3 = max2;
			max2 = max1;
			max1 = sum;
		} else if(sum > max2) {
			max3 = max2;
			max2 = sum;
		} else if(sum > max3) {
			max3 = sum;
		}
		pw.println(max1+max2+max3);
		
		pw.close();
	}
}
