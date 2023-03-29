// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class ConventionII {
	static class Cow implements Comparable<Cow> {
		int seniority, arrival, duration;
		public Cow (int i, int a, int t) {
			seniority = i;
			arrival = a;
			duration = t;
		}
		public int compareTo(Cow c) {
			if(arrival != c.arrival) return Integer.compare(arrival, c.arrival);
			return Integer.compare(seniority, c.seniority);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("convention2.in"));
		PrintWriter pw = new PrintWriter("convention2.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int time = 0;
		int max = 0;
		PriorityQueue<Cow> cows = new PriorityQueue<>();
		PriorityQueue<Cow> line = new PriorityQueue<>(n, (Cow c1, Cow c2) -> c1.seniority-c2.seniority);
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			Cow c = new Cow(i, a, t);
			cows.add(c);
		}
		Cow eat = cows.poll();
		time = eat.arrival;
		while(!cows.isEmpty()) {
			Cow cow = cows.poll();
			if(line.isEmpty() && cow.arrival >= eat.arrival+eat.duration) {
				eat = cow;
				time = eat.arrival;
			} else {
				line.add(cow);
			}
			if(!cows.isEmpty()) {
				while(!line.isEmpty() && time+eat.duration < cows.peek().arrival) {
					time += eat.duration;
					eat = line.poll();
					max = Math.max(max, time-eat.arrival);
				}
			} else {
				while(!line.isEmpty()) {
					time += eat.duration;
					eat = line.poll();
					max = Math.max(max, time-eat.arrival);
				}
			}
		}
		pw.println(max);
		
		pw.close();
	}
}
