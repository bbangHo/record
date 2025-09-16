import java.awt.Point;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] in = new int[N + 1];

        List<Integer>[] g = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int student1 = Integer.parseInt(st.nextToken());
            int student2 = Integer.parseInt(st.nextToken());

            g[student1].add(student2);
            in[student2]++;
        }

        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> result = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int num = q.poll();
            result.add(num);

            for (int next : g[num]) {
                in[next]--; // 진입 차수 감소
                if (in[next] == 0) {
                    q.add(next);
                }
            }
        }

        result.forEach((r -> System.out.print(r + " ")));
    }
}