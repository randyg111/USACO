// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class Haircut {
    static class BIT {
        long[] bit;
        int size;

        public BIT(int n) {
            bit = new long[n+1];
            size = n;
        }
        public void add(int i, int v) {
            for(i++; i <= size; i += i&-i) {
                bit[i] += v;
            }
        }
        public long sum(int i) {
            long s = 0;
            for(i++; i > 0; i -= i&-i) {
                s += bit[i];
            }
            return s;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(r.readLine());
        int[] length = new int[n];
        for(int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            length[i] = a;
        }
        BIT bit = new BIT(n+1);
        long[] inv = new long[n];
        for(int i = 0; i < n; i++) {
            inv[i] = bit.sum(n) - bit.sum(length[i]);
            bit.add(length[i], 1);
        }
        long[] prefix = new long[n+2];
        for(int i = 0; i < n; i++) {
            prefix[length[i]+1] += inv[i];
        }
        for(int i = 1; i < n; i++) {
            prefix[i] += prefix[i-1];
        }
        for(int i = 0; i < n; i++) {
            pw.println(prefix[i]);
        }

        pw.close();
    }
}
