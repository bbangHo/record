import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int count = 1;

        while(b > a) {
            String strB = String.valueOf(b);

            if(b % 2 == 0) {
                b /= 2;
            } else if(strB.endsWith("1") && strB.length() > 1) {
                b = Integer.parseInt(strB.substring(0, strB.length() - 1));
            } else {
                break;
            }

            count++;
        }

        System.out.println(b != a ? -1 : count);
    }
}