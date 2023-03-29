// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.*;

public class CampCleanupII {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int count = 0;
		String line = r.readLine();
		while(line != null) {
			int i1 = line.indexOf("-");
			int i2 = line.indexOf(",");
			int i3 = line.lastIndexOf("-");
			int a1 = Integer.parseInt(line.substring(0,i1));
			int b1 = Integer.parseInt(line.substring(i1+1,i2));
			int a2 = Integer.parseInt(line.substring(i2+1,i3));
			int b2 = Integer.parseInt(line.substring(i3+1));
			if(!(a1 > b2 || a2 > b1))
				count++;
			line = r.readLine();
		}
		pw.println(count);
		
		pw.close();
	}
}
