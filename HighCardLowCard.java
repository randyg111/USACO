// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.TreeSet;
import java.util.Collections;
import java.util.Iterator;

public class HighCardLowCard {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter pw = new PrintWriter("cardgame.out");

		int n = Integer.parseInt(r.readLine());
		TreeSet<Integer> first = new TreeSet<>(Collections.reverseOrder());
		TreeSet<Integer> second = new TreeSet<>();
		TreeSet<Integer> cards = new TreeSet<>();
		int points = 0;
		for(int i = 1; i <= 2*n; i++) {
			cards.add(i);
		}
		for(int i = 0; i < n/2; i++) {
			int card = Integer.parseInt(r.readLine());
			first.add(card);
			cards.remove(card);
		}
		for(int i = 0; i < n/2; i++) {
			int card = Integer.parseInt(r.readLine());
			second.add(card);
			cards.remove(card);
		}
		Iterator<Integer> half = first.iterator();
		while(half.hasNext()) {
			int c = half.next();
			int max = cards.last();
			if(max > c) {
				cards.remove(max);
				points++;
			}
		}
		half = second.iterator();
		while(half.hasNext()) {
			int c = half.next();
			int min = cards.first();
			if(min < c) {
				cards.remove(min);
				points++;
			}
		}
		pw.println(points);
		
		pw.close();
	}
}
