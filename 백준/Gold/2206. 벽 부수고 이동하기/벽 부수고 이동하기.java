import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int x;
        int y;
        boolean isBreak;
        int dis;

        public Node(int a, int b, boolean c, int d) {
            x = a;
            y = b;
            isBreak = c;
            dis = d;
        }
    }

    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(line.charAt(j - 1)));
            }
        }

        Queue<Node> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N + 1][M + 1][2];     // [1] = 벽을 부순 적 있는 애들의 중복 처리, [0] = 부순 적 없는 애들 중복 처리
        q.add(new Node(1, 1, true, 1));
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.x == N && node.y == M) {
                System.out.println(node.dis);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dir[i][0];
                int ny = node.y + dir[i][1];

                if (nx >= 1 && ny >= 1 && nx <= N && ny <= M) {
                    // 다음 이동할 곳이 벽인가?
                    if (board[nx][ny] == 1) { // 다음 이동 지역이 벽?
                        if (node.isBreak && !visited[nx][ny][1]) { // 벽 부술 수 있음 && 해당 위치에 간적 없음
                            visited[nx][ny][1] = true;
                            q.add(new Node(nx, ny, false, node.dis + 1));
                        }
                    } else {
                        if (node.isBreak && !visited[nx][ny][0]) {
                            visited[nx][ny][0] = true;
                            q.add(new Node(nx, ny, node.isBreak, node.dis + 1));
                        } else if(!node.isBreak && !visited[nx][ny][1]) {
                            visited[nx][ny][1] = true;
                            q.add(new Node(nx, ny, node.isBreak, node.dis + 1));
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }
}