import java.awt.Point;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] buildTime = new int[N + 1];
            int[] dp = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
                dp[i] = buildTime[i];
            }

            List<Integer>[] buildSequence = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                buildSequence[i] = new ArrayList<>();
            }

            int[] indegree = new int[N + 1];
            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                buildSequence[start].add(end);
                indegree[end]++;
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }

            while (!q.isEmpty()) {
                Integer start = q.poll();

                for (Integer end : buildSequence[start]) {
                    indegree[end]--;
                    if (indegree[end] == 0) {
                        q.add(end);
                    }
                    dp[end] = Math.max(dp[start] + buildTime[end], dp[end]);
                }
            }

            int targetBuilding = Integer.parseInt(br.readLine());
            System.out.println(dp[targetBuilding]);
        }
    }
}