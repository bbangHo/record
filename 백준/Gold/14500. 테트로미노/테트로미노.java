import java.util.*;
import java.io.*;

public class Main {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int n;
    public static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0 ; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for(int i = 0 ; i < n; i++) {
            for(int j = 0 ; j < m; j++) {
                max = Math.max(dfs(i, j, 1), max);
                max = Math.max(max, other(i, j));
            }
        }

        System.out.println(max);
    }

    public static int dfs(int y, int x, int deep) {
        if(deep == 4) return arr[y][x];

        int[] sum = new int[4];
        visited[y][x] = true;

        if(y > 0 && !visited[y - 1][x]) sum[0] = dfs(y - 1, x, deep + 1);
        if(x > 0 && !visited[y][x - 1])  sum[1] = dfs(y, x - 1, deep + 1);
        if(y < n - 1 && !visited[y + 1][x]) sum[2] = dfs(y + 1, x, deep + 1);
        if(x < m - 1 && !visited[y][x + 1]) sum[3] = dfs(y, x + 1, deep + 1);

        visited[y][x] = false;
        return arr[y][x] + Math.max(sum[0], Math.max(sum[1], Math.max(sum[2], sum[3])));
    }

    public static int other(int y, int x) { // ㅗ 모양이 중심이 중점
        int sum = 0;
        if(y - 1 >= 0 && x - 1 >= 0 && x + 1 < m) sum = Math.max(arr[y][x - 1] + arr[y][x] + arr[y][x + 1] + arr[y - 1][x], sum); // ㅗ
        if(y + 1 < n && y - 1 >= 0 && x + 1 < m) sum = Math.max(arr[y - 1][x] + arr[y][x] + arr[y + 1][x] + arr[y][x + 1], sum);    // ㅏ
        if(y + 1 < n && x - 1 >= 0 && x + 1 < m) sum = Math.max(arr[y][x - 1] + arr[y][x] + arr[y][x + 1] + arr[y + 1][x], sum);    // ㅜ
        if(y + 1 < n && y - 1 >= 0 && x - 1 >= 0) sum = Math.max(arr[y - 1][x] + arr[y][x] + arr[y + 1][x] + arr[y][x - 1], sum);    //  ㅓ
        return sum;
    }
}