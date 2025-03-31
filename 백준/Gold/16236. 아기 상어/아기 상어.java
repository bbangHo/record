import java.util.*;
import java.io.*;

public class Main {
    static class Fish {
        int r, c, dist;

        public Fish(int a, int b, int s) {
            r = a;
            c = b;
            dist = s;
        }

        private void sizeUpCheck() {
            if (eat == size) {
                size++;
                eat = 0;
            }
        }

        public boolean findFishAndEat() {
            Queue<int[]> q = new LinkedList<>();
            Queue<Fish> eatFishList = new PriorityQueue<>((f1, f2) -> {
                if (f1.dist == f2.dist) {    // 거리가 같으면
                    if (f1.r == f2.r) {      // 위에 있는 거 우선
                        return f1.c - f2.c;    // 그래도 같으면 왼쪽에 있는 거 우선
                    }
                    return f1.r - f2.r;
                }
                return f1.dist - f2.dist;
            });

            int[][] eatTimeBoard = new int[N][N];
            boolean[][] visited = new boolean[N][N];
            q.add(new int[]{r, c});   // 현재 위치

            while (!q.isEmpty()) {
                int[] tmp = q.poll();
                int nowR = tmp[0];
                int nowC = tmp[1];
                visited[nowR][nowC] = true;

                // 현 위치에서 4방향 탐색
                for (int i = 0; i < 4; i++) {
                    int nextR = nowR + dir[i][0];
                    int nextC = nowC + dir[i][1];

                    // 경계를 벗어나는 경우
                    if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N) {
                        continue;
                    }

                    // 물고기 크기가 나보다 큰 경우
                    if(size < fishSizeBoard[nextR][nextC]) {
                        continue;
                    }

                    // 방문하지 않았으면서 지나갈 수 있는 경우
                    if (!visited[nextR][nextC]) {
                        visited[nextR][nextC] = true;
                        q.add(new int[]{nextR, nextC});
                        eatTimeBoard[nextR][nextC] = eatTimeBoard[nowR][nowC] + 1;

                        // 먹을 수 있는 물고기만 넣는다
                        if (fishSizeBoard[nextR][nextC] != 0 && size > fishSizeBoard[nextR][nextC]) {
                            eatFishList.add(new Fish(nextR, nextC, eatTimeBoard[nextR][nextC]));
                        }
                    }
                }
            }

            // 먹을 수 있는 물고기가 없다면 진행하지 않는다
            if (eatFishList.isEmpty()) {
                return false;
            }

            Fish fish = eatFishList.poll();
            r = fish.r;
            c = fish.c;
            time += eatTimeBoard[r][c];
            fishSizeBoard[r][c] = 0;
            eat++;
            sizeUpCheck();
            return true;
        }
    }

    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static Fish shark;
    static int[][] fishSizeBoard;
    static int N, eat, time, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Fish> fishList = new ArrayList<>();
        eat = 0;
        time = 0;
        size = 2;
        fishSizeBoard = new int[N][N];

        // 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int in = Integer.parseInt(st.nextToken());
                fishSizeBoard[i][j] = in;

                if (in == 9) {
                    shark = new Fish(i, j, 2);
                    fishSizeBoard[i][j] = 0;
                }
            }
        }

        while(true) {
            if(!shark.findFishAndEat()) {
                break;
            }
        }

        System.out.println(time);
    }
}