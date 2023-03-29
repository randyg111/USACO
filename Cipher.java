import java.io.*;
import java.util.*;
public class Cipher {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String s = br.readLine();
            x %= 26;
            for(char c : s.toCharArray()) {
                if(c >= 'a' && c <= 'z') {
                    if(c-x < 'a') pw.print((char) (c + 26 - x));
                    else pw.print((char) (c-x));
                } else if(c >= 'A' && c <= 'Z') {
                    if(c-x < 'A') pw.print((char) (c + 26 - x));
                    else pw.print((char) (c-x));
                } else {
                    pw.print(c);
                }
            }
            pw.println();
        }

        pw.close();
    }
}