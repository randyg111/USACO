// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class FindAndReplace {
	static class Node {
		boolean leaf;
		char c;
		List<Node> children;
		long length;
		public Node(char c, boolean leaf) {
			children = new ArrayList<>();
			this.c = c;
			this.leaf = leaf;
			if(leaf) length = 1;
			else length = 0;
		}
		public void add(Node node) {
			if(length <= 1e18) {
				children.add(node);
				length += node.length;
			}
		}
		public void substring(long l, long r, StringBuilder builder) {
			if(l < r) {
				if(leaf) {
					builder.append(c);
				} else {
					long index = 0;
					for(Node child : children) {
						if(index >= l && index < r ||
						index + child.length >= l && index + child.length < r || 
						index < l && index + child.length >= r) {
							child.substring(Math.max(0, l - index),
							Math.min(child.length, r - index), builder);
						}
						index += child.length;
					}
				}
			}
				
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(br.readLine());
		long l = Long.parseLong(st.nextToken());
		long r = Long.parseLong(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		List<List<String>> ops = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			List<String> op = new ArrayList<>();
			op.add(st.nextToken());
			op.add(st.nextToken());
			ops.add(op);
		}
		Collections.reverse(ops);
		Node[] roots = new Node[26];
		for(int i = 'a'; i <= 'z'; i++) {
			roots[i - 'a'] = new Node((char) i, true);
		}
		for(List<String> op : ops) {
			char c = op.get(0).charAt(0);
			String s = op.get(1);
            if(s.length() == 0) {
                roots[c-'a'] = roots[s.charAt(0)-'a'];
            } else {
                Node temp = roots[c-'a'];
                roots[c-'a'] = new Node(c, false);
                for(int i = 0; i < s.length(); i++) {
                    if(s.charAt(i) == c) {
                        roots[c-'a'].add(temp);
                    } else {
                        roots[c-'a'].add(roots[s.charAt(i)-'a']);
                    }
                }
            }	
		}
		StringBuilder builder = new StringBuilder();
		roots[0].substring(l-1, r, builder);
		pw.println(builder);
		
		pw.close();
	}
}
