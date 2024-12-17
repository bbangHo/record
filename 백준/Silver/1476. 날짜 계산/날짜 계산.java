import java.io.*;
import java.util.*;

public class Main {
    static int e, s, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        e = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int a = 1, b = 1, c = 1;
        int count = 1;

        while(!isResult(a, b, c)) {
            a++; b++; c++; count++;

            if(a >= 16) a = 1;
            if(b >= 29) b = 1;
            if(c >= 20) c = 1;
        }

        System.out.println(count);
    }

    public static boolean isResult(int a, int b, int c) {
        if(a == e && b == s && c == m) {
            return true;
        }

        return false;
    }
}