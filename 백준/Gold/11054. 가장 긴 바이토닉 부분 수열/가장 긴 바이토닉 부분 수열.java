import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        int[] r_dp = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(r_dp, 1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    max = Math.max(max, dp[j]);
                    dp[i] = max + 1;
                }
            }
        }

        for (int i = n - 1; i >= 0 ; i--) {
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    max = Math.max(max, r_dp[j]);
                    r_dp[i] = max + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(r_dp[i] + dp[i], ans);
        }

        System.out.println(ans - 1);
    }
}