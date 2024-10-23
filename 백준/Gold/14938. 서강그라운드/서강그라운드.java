import java.util.*;
import java.io.*;


public class Main {
    static class Village {
        int num;
        int len;

        public Village(int a, int b) {
            num = a;
            len = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] itemNum = new int[n + 1];
        List<Village>[] villageList = new ArrayList[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            itemNum[i] = Integer.parseInt(st.nextToken());
            villageList[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            villageList[v1].add(new Village(v2, len));
            villageList[v2].add(new Village(v1, len));
        }

        Queue<Village> q = new PriorityQueue<>((n1, n2) -> {
            return n1.len - n2.len;
        });
        int result = 0;
        for (int i = 1; i <= n; i++) {
            q.add(new Village(i, 0));
            int[] dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[i] = 0;

            while (!q.isEmpty()) {
                Village now = q.poll();

                if (dist[now.num] < now.len) {
                    continue;
                }

                for (int j = 0; j < villageList[now.num].size(); j++) {
                    Village next = villageList[now.num].get(j);
                    if (now.len + next.len <= m) {
                        q.add(new Village(next.num, now.len + next.len));
                        dist[next.num] = now.len + next.len;
                    }
                }
            }

            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[j] <= m) {
                    sum += itemNum[j];
                }
            }

            result = Math.max(sum, result);
        }

        System.out.println(result);
    }
}