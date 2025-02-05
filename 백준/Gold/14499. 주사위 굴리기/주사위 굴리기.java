import java.util.*;
import java.io.*;

public class Main {
    static int[] ns = new int[2];    // 남북
    static int[] ew = new int[2];    // 동서
    static int top = 0;
    static int bottom = 0;
    static int x = 0;
    static int y = 0;

    /*
               ns[0]
        ew[0]   top   ew[1]   bottom
               ns[1]
               bottom
        */
    public static void roll(int dir) {
        if(dir == 1) { // 동
            int tmp = ew[0];
            ew[0] = bottom;
            bottom = ew[1];
            ew[1] = top;
            top = tmp;
        } else if(dir == 2) { //서
            int tmp = ew[0];
            ew[0] = top;
            top = ew[1];
            ew[1] = bottom;
            bottom = tmp;
        } else if(dir == 3) { //북
            int tmp = ns[0];
            ns[0] = top;
            top = ns[1];
            ns[1] = bottom;
            bottom = tmp;
        } else {    // 남
            int tmp = ns[0];
            ns[0] = bottom;
            bottom = ns[1];
            ns[1] = top;
            top = tmp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) {
            int dir = Integer.parseInt(st.nextToken());

            if(dir == 1) {
                if(y + 1 < m) {
                    y++;
                } else {
                    continue;
                }
            } else if(dir == 2) {
                if(y - 1 >= 0) {
                    y--;
                } else {
                    continue;
                }
            } else if(dir == 3) {
                if(x - 1 >= 0) {
                    x--;
                } else {
                    continue;
                }
            } else {
                if(x + 1 < n) {
                    x++;
                } else {
                    continue;
                }
            }

            roll(dir);

            if(map[x][y] == 0) {
                map[x][y] = bottom;
            } else {
                bottom = map[x][y];
                map[x][y] = 0;
            }

            System.out.println(top);
        }
    }
}