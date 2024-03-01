import java.util.*;
import java.io.*;

public class Main {
    static int r;
    static int c;
    static int count;

    public static void main(String[] args) throws IOException {
        long beforeTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        count = 0;
        int size = (int) Math.pow(2, n) - 1;

        while (size != 0) {
            size /= 2;

            if (r <= size && c > size) {    
                count += (int) Math.pow(size + 1, 2);
                c -= size + 1;
            } else if (r > size && c <= size) {
                count += (int) Math.pow(size + 1, 2) * 2;
                r -= size + 1;
            } else if (r > size && c > size) {
                count += (int) Math.pow(size + 1, 2) * 3;
                c -= size + 1;
                r -= size + 1;
            }
        }
        System.out.println(count);
    }
}