import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Main {
    static int n, min, totalHuman;
    static int[] human;
    static List<Integer>[] cites;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        min = Integer.MAX_VALUE;
        n = Integer.parseInt(br.readLine());
        human = new int[n + 1];
        totalHuman = 0;
        cites = new ArrayList[n + 1];
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            human[i] = Integer.parseInt(st.nextToken());
            cites[i] = new ArrayList<>();
            totalHuman += human[i];
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int nearCitySize = Integer.parseInt(st.nextToken());

            for (int j = 0; j < nearCitySize; j++) {
                int nearCityNum = Integer.parseInt(st.nextToken());
                cites[i].add(nearCityNum);
                cites[nearCityNum].add(i);
            }
        }

        comb(1, new boolean[n + 1]);  // 여기 포함되어 있지 않은 구역은 다른 지역구

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void comb(int idx, boolean[] visited) {
        if (idx == n + 1) {
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    groupA.add(i);
                } else {
                    groupB.add(i);
                }
            }

            if (checking(groupA) && checking(groupB)) {
                min = Math.min(counting(groupA, groupB), min);
            }

            return;
        }

        visited[idx] = true;
        comb(idx + 1, visited);
        visited[idx] = false;
        comb(idx + 1, visited);
    }

    public static boolean checking(List<Integer> group) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int size = group.size();
        if (size == 0 || size == n) {
            return false;
        }

        q.add(group.get(0));
        while (!q.isEmpty()) {
            int now = q.poll();
            visited[now] = true;
            for (Integer next : cites[now]) {
                if (!visited[next] && group.contains(next)) {
                    q.add(next);
                }
            }
        }

        for (int i : group) {
            if(!visited[i]) {
                return false;
            }
        }

        return true;
    }

    public static int counting(List<Integer> a, List<Integer> b) {
        int numA = 0;
        int numB = 0;
        for (Integer i : a) {
            numA += human[i];
        }

        for (Integer i : b) {
            numB += human[i];
        }

        return Math.abs(numA - numB);
    }
}