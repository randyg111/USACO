// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class SpeedingTicket {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("speeding.in"));
		PrintWriter pw = new PrintWriter("speeding.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int[] road = new int[100];
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int mile = 0;
		int max = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			int length = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());
			for(int j = 0; j < length; j++) {
				road[mile] = limit;
				mile++;
			}
		}
		mile = 0;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine());
			int length = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			for(int j = 0; j < length; j++) {
				if(speed-road[mile] > max) {
					max = speed-road[mile];
				}
				mile++;
			}
		}
		pw.println(max);
		
		pw.close();
	}
}
