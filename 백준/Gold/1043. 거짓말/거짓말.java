import java.util.*;
import java.io.*;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int p = 0;
        if (k != 0) p = Integer.parseInt(st.nextToken());
        Set<Integer> know = new HashSet<>();
        know.add(p);

        for (int i = 0; i < k - 1; i++) {
            int num = Integer.parseInt(st.nextToken());
            union(p, num);
            know.add(num);
        }

        List<Integer>[] parts = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            parts[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int pCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < pCount; j++) {
                int num = Integer.parseInt(st.nextToken());
                parts[i].add(num);
            }
        }

        for (List<Integer> party : parts) {
            boolean update = false;
            for (Integer num : party) {
                if (know.contains(num)) {
                    for (Integer nn : party) {
                        union(p, nn);
                        know.add(nn);
                        update = true;
                    }
                    break;
                }
            }

            if (!update) {
                for (int i = 0; i < party.size() - 1; i++) {
                    union(party.get(i), party.get(i + 1));
                }
            }

        }

        int find = find(p);
        for (int i = 1; i <= n; i++) {
            if (find == find(parent[i])) {
                know.add(i);
            }
        }

        int count = m;
        for (List<Integer> party : parts) {
            for (Integer num : party) {
                if (know.contains(num)) {
                    count--;
                    break;
                }
            }
        }

        System.out.println(count);
    }

    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}