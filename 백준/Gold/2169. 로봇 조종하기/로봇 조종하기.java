import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N + 2][M + 2];
        int[][] dp = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                dp[1][1] = board[1][1];
                for (int j = 2; j <= M; j++) {
                    dp[i][j] = dp[i][j - 1] + board[i][j];
                }
            } else {
                int[] left = new int[M + 2];
                int[] right = new int[M + 2];

                // 왼 -> 오
                left[1] = dp[i - 1][1] + board[i][1];
                for (int j = 2; j <= M; j++) {
                    left[j] = Math.max(dp[i - 1][j], left[j - 1]) + board[i][j];
                }

                // 오 -> 왼
                right[M] = dp[i - 1][M] + board[i][M];
                for (int j = M - 1; j >= 1; j--) {
                    right[j] = Math.max(dp[i - 1][j], right[j + 1]) + board[i][j];
                }

                for (int j = 1; j <= M; j++) {
                    dp[i][j] = Math.max(left[j], right[j]);
                }
            }
        }
        System.out.println(dp[N][M]);
    }
}