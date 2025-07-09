import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        List<Integer>[] cities = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            cities[i] = new ArrayList<>();
            for(int j = 1; j <= N; j++) {
                int conn = Integer.parseInt(st.nextToken());

                if(cities[i].isEmpty()) {
                    cities[i].add(i);
                }

                if(conn == 1) {
                    cities[i].add(j);
                }
            }
        }

        List<Integer> visitPlanCities = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            visitPlanCities.add(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(visitPlanCities.get(0));

        while(!q.isEmpty()) {
            int cityNum = q.poll();

            for(int nextCity : cities[cityNum]) {
                visited[cityNum] = true;
                if(!visited[nextCity]) {
                    q.add(nextCity);
                }
            }
        }

        for(int visiteCityNum : visitPlanCities) {
            if(!visited[visiteCityNum]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");

    }
}