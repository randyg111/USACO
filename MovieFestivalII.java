// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class MovieFestivalII {
	static class Movie implements Comparable<Movie> {
		int start, end;
		public Movie (int a, int b) {
			start = a;
			end = b;
		}
		public int compareTo(Movie m) {
			return end-m.end;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		TreeMap<Integer, Integer> club = new TreeMap<>();
		club.put(0, k);
		int total = 0;
		PriorityQueue<Movie> movies = new PriorityQueue<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			movies.add(new Movie(a, b));
		}
		while(!movies.isEmpty()) {
			Movie movie = movies.poll();
			if(club.floorKey(movie.start) != null) {
				int e = club.floorKey(movie.start);
				club.put(e, club.get(e)-1);
				if(club.get(e) == 0) {
					club.remove(e);
				}
				if(!club.containsKey(movie.end)) {
					club.put(movie.end, 0);
				}
				club.put(movie.end, club.get(movie.end)+1);
				total++;
			}
		}
		pw.println(total);
		
		pw.close();
	}
}
