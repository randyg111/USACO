import java.io.*;
import java.util.*;
public class Bitmask {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String s = br.readLine();
        String bit = "1";
        int index = s.indexOf('1');
        for(int i = index+1; i < s.length(); i++) {
            bit += Character.getNumericValue(bit.charAt(bit.length()-1)) ^ Character.getNumericValue(s.charAt(i));
        }
        pw.println(Long.parseLong(bit, 2)>>1);


        pw.close();
    }
}