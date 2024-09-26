import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int loc;
        int time;

        public Node(int l, int t) {
            loc = l;
            time = t;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
       
        if (n >= k) {
            System.out.println(n - k);
            return;
        }

        boolean[] visited = new boolean[100000 + 1];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(n, 0));

        while (!q.isEmpty()) {
            Node h = q.poll();

            if(h.loc == k) {
                System.out.println(h.time);
                break;
            }

            visited[h.loc] = true;

            if ((h.loc * 2) <= 100000 && !visited[h.loc * 2]) {
                q.add(new Node(h.loc * 2, h.time));
            }

            if ((h.loc - 1) >= 0 && !visited[h.loc - 1]) {
                q.add(new Node(h.loc - 1, h.time + 1));
            }
            
            if ((h.loc + 1) <= 100000 && !visited[h.loc + 1]) {
                q.add(new Node(h.loc + 1, h.time + 1));
            }
        }
    }
}