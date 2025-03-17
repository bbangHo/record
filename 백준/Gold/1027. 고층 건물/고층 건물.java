import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] buildings = new int[n + 1];
        int[] cnt = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            // 왼쪽으로
            double standardLean = -1_000_000_000;
            for (int j = i - 1; j > 0; j--) {
                double lean = (double) (buildings[j] - buildings[i]) / (i - j);

                if (lean > standardLean) {
                    standardLean = lean;
                    cnt[i]++;
                }
            }

            standardLean = -1_000_000_000;
            for (int j = i + 1; j <= n; j++) {
                double lean = (double) (buildings[j] - buildings[i]) / (j - i);

                if (lean > standardLean) {
                    standardLean = lean;
                    cnt[i]++;
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, cnt[i]);
        }

        System.out.println(max);
    }
}