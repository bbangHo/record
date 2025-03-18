import java.util.*;
import java.io.*;

public class Main {
    static int N, L, R;
    static int[][] countries;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] unionCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        countries = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {    // 하루동안의 인구이동을 계산 하는 로직
            boolean movedDone = false;    // 인구 이동이 일어났는지 확인
            unionCheck = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!unionCheck[i][j]) {
                        movedDone |= bfs(i, j);
                    }
                }
            }

            if (!movedDone) {
                break;
            }

            day++;
        }

        System.out.println(day);
    }

    public static boolean bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> unionList = new ArrayList<>();

        int[] nowPoint = {i, j};
        q.add(nowPoint);
        unionList.add(nowPoint);
        unionCheck[i][j] = true;
        int totalHumanNum = countries[i][j];

        while (!q.isEmpty()) {
            int[] point = q.poll();
            int nowR = point[0];
            int nowC = point[1];

            // 현재 나라 기준으로 동서남북 연합 되는지 확인
            for (int k = 0; k < 4; k++) {
                int r = nowR + dir[k][0];
                int c = nowC + dir[k][1];

                // 땅을 벗어나지 않으면서 이미 연합이 된 나라들은 제외
                if (r >= 0 && c >= 0 && r < N && c < N && !unionCheck[r][c] ) {
                    int diff = Math.abs(countries[r][c] - countries[nowR][nowC]);

                    // 인구수 차이가 L이상 R이하라면 연합
                    if (L <= diff && diff <= R) {
                        unionCheck[r][c] = true;
                        int[] nextPoint = {r, c};
                        q.add(nextPoint);
                        unionList.add(nextPoint);
                        totalHumanNum += countries[r][c];
                    }
                }
            }
        }

        if (unionList.size() == 1) {
            return false;
        }

        // 인구 이동
        int unionHumanNum = totalHumanNum / unionList.size();
        for (int[] p : unionList) {
            int r = p[0];
            int c = p[1];
            countries[r][c] = unionHumanNum;
        }

        return true;
    }
}