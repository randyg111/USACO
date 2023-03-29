// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class DistressSignalII {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int product = 1;
        List<String> packets = new ArrayList<>();
        packets.add("[[2]]");
        packets.add("[[6]]");
        String packet1 = r.readLine();
        while(packet1 != null) {
            String packet2 = r.readLine();
            packets.add(packet1);
            packets.add(packet2);

            r.readLine();
            packet1 = r.readLine();
        }
        packets.sort(new Comparator<>() {
            public int compare(String a, String b) {
                return customCompare(a, b);
            }
        });
        for(int i = 0; i < packets.size(); i++) {
            if(packets.get(i).equals("[[2]]") || packets.get(i).equals("[[6]]")) {
                product *= i+1;
            }
        }
        pw.println(product);

        pw.close();
    }
    public static int customCompare(String p1, String p2) {
        if(p2.length() == 0) {
            if(p1.length() == 0) {
                return 0;
            } else {
                return 1;
            }
        } else if(p1.length() == 0) {
            return -1;
        }
        if(p1.charAt(0) == '[' && p2.charAt(0) == '[') {
            int b1 = bracket(p1);
            int b2 = bracket(p2);

            int c = customCompare(p1.substring(1, b1), p2.substring(1, b2));
            if(c != 0 || b1 == p1.length()-1 || b2 == p2.length()-1) return c;
            else return customCompare(p1.substring(b1+2), p2.substring(b2+2));

        } else if(p1.charAt(0) == '[') {
            int b1 = bracket(p1);
            int i = p2.indexOf(",");
            if(i == -1) i = p2.length();

            int c = customCompare(p1.substring(1, b1), p2.substring(0, i));
            if(c != 0 || i == p2.length() || b1 == p1.length()-1) return c;
            else return customCompare(p1.substring(b1+2), p2.substring(i+1));
        } else if(p2.charAt(0) == '[') {
            int b2 = bracket(p2);
            int i = p1.indexOf(",");
            if(i == -1) i = p1.length();

            int c = customCompare(p1.substring(0, i), p2.substring(1, b2));
            if(c != 0 || i == p1.length() || b2 == p2.length()-1) return c;
            else return customCompare(p1.substring(i+1), p2.substring(b2+2));
        } else {
            int i1 = p1.indexOf(",");
            int i2 = p2.indexOf(",");
            int in1, in2;

            if(i1 == -1 && i2 == -1) {
                in1 = Integer.parseInt(p1);
                in2 = Integer.parseInt(p2);
                return in1 - in2;
            } else if(i1 >= 0 && i2 == -1) {
                in1 = Integer.parseInt(p1.substring(0, i1));
                in2 = Integer.parseInt(p2);
                if(in1 - in2 != 0) return in1 - in2;
                else return 1;
            } else if(i1 == -1 && i2 >= 0) {
                in1 = Integer.parseInt(p1);
                in2 = Integer.parseInt(p2.substring(0, i2));
                if(in1 - in2 != 0) return in1 - in2;
                else return -1;
            } else {
                in1 = Integer.parseInt(p1.substring(0, i1));
                in2 = Integer.parseInt(p2.substring(0, i2));
                if(in1 - in2 != 0) return in1 - in2;
                else return customCompare(p1.substring(i1+1), p2.substring(i2+1));
            }
        }
    }
    public static int bracket(String p) {
        int depth = 0;
        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '[') {
                depth++;
            } else if(p.charAt(i) == ']') {
                depth--;
                if(depth == 0) return i;
            }
        }
        return depth;
    }
}
