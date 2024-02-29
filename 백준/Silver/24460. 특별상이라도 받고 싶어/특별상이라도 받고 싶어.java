import java.util.*;
import java.io.*;

public class Main {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        StringTokenizer st;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(draw(n,n,n));
    }

    public static int draw(int x, int y, int size) {
        if (size == 1)
            return arr[x][y];

        int[] tmp = new int[4];
        size /= 2;

        tmp[0] = draw(x - size, y - size, size);    // 0 1
        tmp[1] = draw(x - size, y, size);
        tmp[2] = draw(x, y - size, size);           // 2 3
        tmp[3] = draw(x, y, size);

        tmp = Arrays.stream(tmp).sorted().toArray();

        return tmp[1];
    }
}