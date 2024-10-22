import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int num;
        int weight;

        public Node(int a, int b) {
            num = a;
            weight = b;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Node>[] list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Queue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            return n2.weight - n1.weight;
        });

        int[] d = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;

        for (int i = 0; i < list[start].size(); i++) {
            Node node = list[start].get(i);
            pq.add(node);
            d[node.num] = node.weight;
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (d[now.num] > now.weight) continue;

            for (int i = 0; i < list[now.num].size(); i++) {
                Node next = list[now.num].get(i);

                int minWeight = Math.min(now.weight, next.weight);
                if (minWeight > d[next.num]) {
                    d[next.num] = minWeight;
                    pq.add(new Node(next.num, d[next.num]));
                }
            }
        }

        System.out.println(d[end]);

    }
}