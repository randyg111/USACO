// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class TasksAndDeadlines {
	static class Task implements Comparable<Task> {
		int duration, deadline;
		public Task (int a, int d) {
			duration = a;
			deadline = d;
		}
		public int compareTo(Task t) {
			return duration-t.duration;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(r.readLine());
		int n = Integer.parseInt(st.nextToken());
		long time = 0;
		long reward = 0;
		Task[] tasks = new Task[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			tasks[i] = new Task(a, d);
		}
		Arrays.sort(tasks);
		for(Task task : tasks) {
			time += task.duration;
			reward += task.deadline-time;
		}
		pw.println(reward);
		
		pw.close();
	}
}
