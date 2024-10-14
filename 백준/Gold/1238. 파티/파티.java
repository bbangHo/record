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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<Node>[] g = new ArrayList[n + 1];
        List<Node>[] rg = new ArrayList[n + 1];

        for(int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            g[start].add(new Node(end, cost));
            rg[end].add(new Node(start, cost));
        }


        int[] d = new int[n + 1];
        int[] rd = new int[n + 1];

        Arrays.fill(d, Integer.MAX_VALUE);
        Arrays.fill(rd, Integer.MAX_VALUE);

        d[x] = rd[x] = 0;

        dijk(d, g, x);
        dijk(rd, rg, x);


        int max = 0;
        for(int i = 1; i <= n; i++) {
            max = Math.max(max, d[i] + rd[i]);
        }

        System.out.println(max);
    }

    public static void dijk(int[] d, List<Node>[] g, int x) {
        Queue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            return n1.cost - n2.cost;
        });

        pq.add(new Node(x, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(d[cur.num] < cur.cost)
                continue;

            for(Node next : g[cur.num]) {
                int dist = cur.cost + next.cost;
                if(d[next.num] > dist) {
                    d[next.num] = dist;
                    pq.add(new Node(next.num, d[next.num]));
                }
            }
        }
    }
}