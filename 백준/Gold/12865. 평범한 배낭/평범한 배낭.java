import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] item = new int[n + 1][k + 1];
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            item[i][0] = w;
            item[i][1] = v;
        }

        for (int i = 1; i <= n; i++) {
            for (int m = 0; m <= k; m++) {// 무게
                if (m >= item[i][0])
                    dp[i][m] = Math.max(item[i][1] + dp[i - 1][m - item[i][0]], dp[i - 1][m]);
                else
                    dp[i][m] = dp[i - 1][m];
            }
        }

        System.out.println(dp[n][k]);
    }
}