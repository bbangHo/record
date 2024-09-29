import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] tree = new ArrayList[n + 1];
        
        for(int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            
            tree[n1].add(n2);
            tree[n2].add(n1);
        }
        
        boolean[] visited = new boolean[n + 1];
        int[] perent = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        
        visited[1] = true;
        for(int i = 0; i < tree[1].size(); i++) {
            int node = tree[1].get(i);
            q.add(node);
            perent[node] = 1;
        }
        
        while(!q.isEmpty()) {
            int node = q.poll();
            visited[node] = true;
            
            for(int i = 0; i < tree[node].size(); i++) {
                int tmp = tree[node].get(i);
                if(!visited[tmp]) {
                    perent[tmp] = node;
                    q.add(tmp);
                }
            }
        }
        
        for(int i = 2; i <= n; i++) {
            System.out.println(perent[i]);
        } 
    }
}