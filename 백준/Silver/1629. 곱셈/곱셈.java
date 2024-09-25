import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        System.out.println(func(a,b,c));
    }
    
    public static long func(int a, int b, int c) {
        if (b == 1) {
            return a % c;
        }

        long temp = func(a, b / 2 , c);

        if (b % 2 == 0) {
            return temp * temp % c;
        } else {
            return (temp * temp % c) * a % c;
        }
    }
}