import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int num;
        int cost;

        public Node(int a, int b) {
            num = a;
            cost = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());  // 도시 개수
        int m = Integer.parseInt(br.readLine());  // 노선 개수

        List<Node>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            g[start].add(new Node(end, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] min = new int[n + 1];
        int[] route = new int[n + 1];

        Arrays.fill(min, Integer.MAX_VALUE);
        min[start] = 0;

        Queue<Node> q = new PriorityQueue<>((n1, n2) -> {
            return n1.cost - n2.cost;
        });

        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if(min[cur.num] < cur.cost)
                continue;

            for (int i = 0; i < g[cur.num].size(); i++) {
                Node next = g[cur.num].get(i);

                if (min[next.num] > (min[cur.num] + next.cost)) {
                    min[next.num] = min[cur.num] + next.cost;
                    q.add(new Node(next.num, min[next.num]));
                    route[next.num] = cur.num;
                }
            }
        }

        int minCost = min[end];
        int count = 1;
        int node = route[end];
        List<Integer> rs = new ArrayList<>();
        rs.add(end);

        while (node != 0) {
            rs.add(node);
            node = route[node];
            count++;
        }

        System.out.println(minCost);
        System.out.println(count);
        for (int i = rs.size() - 1; i >= 0; i--) {
            System.out.print(rs.get(i) + " ");
        }
    }
}