// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class CowLand {
	static int[] enjoy, st, en, eulerInd, treeInd, segtree, minSegtree, tree, euler;
	static int eulerTimer = 0, treeTimer = 0;
	static int n;
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("cowland.in"));
		PrintWriter pw = new PrintWriter("cowland.out");

		StringTokenizer sto = new StringTokenizer(r.readLine());
		n = Integer.parseInt(sto.nextToken());
		int q = Integer.parseInt(sto.nextToken());
		sto = new StringTokenizer(r.readLine());
		enjoy = new int[n];
		st = new int[n];
		en = new int[n];
		eulerInd = new int[n];
		treeInd = new int[n];
		segtree = new int[2*n];
		minSegtree = new int[4*n-2];
		tree = new int[n];
		euler = new int[2*n-1];
		adj = new ArrayList[n];
		for(int i = 0; i < n; i++) adj[i] = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			enjoy[i] = Integer.parseInt(sto.nextToken());
		}
		for(int i = 0; i < n-1; i++) {
			sto = new StringTokenizer(r.readLine());
			int a = Integer.parseInt(sto.nextToken());
			int b = Integer.parseInt(sto.nextToken());
			a--;
			b--;
			adj[a].add(b);
			adj[b].add(a);
		}
		dfs(0, -1);
		for(int i = 0; i < 2*n-1; i++) {
			minUpdate(i, euler[i]);
		}
		for(int i = 0; i < n; i++) {
			update(st[i], enjoy[i]);
			if(en[i]+1 < n) update(en[i]+1, enjoy[i]);
		}
		for(int k = 0; k < q; k++) {
			sto = new StringTokenizer(r.readLine());
			if(Integer.parseInt(sto.nextToken()) == 1) {
				int i = Integer.parseInt(sto.nextToken());
				int v = Integer.parseInt(sto.nextToken());
				i--;
				update(st[i], enjoy[i]^v);
				if(en[i]+1 < n) update(en[i]+1, enjoy[i]^v);
				enjoy[i] = v;
			} else {
				int i = Integer.parseInt(sto.nextToken());
				int j = Integer.parseInt(sto.nextToken());
				i--;
				j--;
				int e = query(0, treeInd[i]) ^ query(0, treeInd[j]) ^ enjoy[lca(i, j)];
				pw.println(e);
			}
		}

		pw.close();
	}
	public static void dfs(int i, int p) {
		eulerInd[i] = eulerTimer;
		euler[eulerTimer++] = i;
		tree[treeTimer] = i;
		treeInd[i] = treeTimer;
		st[i] = treeTimer++;
		for(int j : adj[i]) {
			if(j != p) {
				dfs(j, i);
				euler[eulerTimer++] = i;
			}
		}
		en[i] = treeTimer-1;
	}
	public static void update(int i, int v) {
		i += n;
		segtree[i] ^= v;
		for(i /= 2; i >= 1; i /= 2) {
			segtree[i] = segtree[2*i] ^ segtree[2*i+1];
		}
	}
	public static int query(int a, int b) {
		a += n;
		b += n;
		int d = 0;
		while(a <= b) {
			if(a % 2 == 1) d ^= segtree[a++];
			if(b % 2 == 0) d ^= segtree[b--];
			a /= 2;
			b /= 2;
		}
		return d;
	}
	public static void minUpdate(int i, int v) {
		i += 2*n-1;
		minSegtree[i] = v;
		for(i /= 2; i >= 1; i /= 2) {
			minSegtree[i] = Math.min(minSegtree[2*i], minSegtree[2*i+1]);
		}
	}
	public static int minQuery(int a, int b) {
		a += 2*n-1;
		b += 2*n-1;
		int d = Integer.MAX_VALUE;
		while(a <= b) {
			if(a % 2 == 1) d = Math.min(d, minSegtree[a++]);
			if(b % 2 == 0) d = Math.min(d, minSegtree[b--]);
			a /= 2;
			b /= 2;
		}
		return d;
	}
	public static int lca(int a, int b) {
		if(a == b) {
			return a;
		}
		if(eulerInd[a] > eulerInd[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		return minQuery(eulerInd[a], eulerInd[b]);
	}
	// public static void build() {
	// 	int size = 2*n-1;
	// 	for(int i = 0; i < size; i++) {
	// 		min[i][i] = euler[i];
	// 	}
	// 	for(int i = 1; 1 << i <= size; i++) {
	// 		int w = 1 << i;
	// 		for(int a = 0; a + w - 1 < size; a++) {
	// 			min[a][a+w-1] = Math.min(min[a][a+w/2-1], min[a+w/2][a+w-1]);
	// 		}
	// 	}
	// }
	// public static int lca(int a, int b) {
	// 	if(a == b) return a;
	// 	if(eulerInd[a] < eulerInd[b]) {
	// 		a = eulerInd[a];
	// 		b = eulerInd[b];
	// 	} else {
	// 		int temp = b;
	// 		b = eulerInd[a];
	// 		a = eulerInd[temp];
	// 	}
	// 	int k = 1 << (31 - Integer.numberOfLeadingZeros(b-a+1));
	// 	return Math.min(min[a][a+k-1], min[b-k+1][b]);
	// }
}
