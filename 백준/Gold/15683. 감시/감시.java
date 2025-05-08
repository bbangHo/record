import java.util.*;
import java.io.*;

public class Main {
    static class Cctv {
        int num, x, y;

        public Cctv(int a, int b, int c) {
            num = a;
            x = b;
            y = c;
        }
    }

    static int N, M;
    static int[][] board;
    static int min;
    static List<Cctv> cctvList;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        min = Integer.MAX_VALUE;

        cctvList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;

                if (num > 0 && num < 6) {
                    cctvList.add(new Cctv(num, i, j));
                }
            }
        }

        dfs(0, cctvList.size(), copy(board));
        System.out.println(min);
    }

    public static void dfs(int idx, int size, int[][] copyBoard) {
        if (idx == size) {
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (copyBoard[i][j] == 0) {
                        cnt++;
                    }
                }
            }

            min = Math.min(min, cnt);
            return;
        }

        Cctv cctv = cctvList.get(idx);
        for (int dirIdx = 0; dirIdx < 4; dirIdx++) {
            if (cctv.num == 1) {
                int[][] copy = copy(copyBoard);
                lineCheck(cctv, dirIdx, copy);
                dfs(idx + 1, size, copy);
            } else if (cctv.num == 2 && dirIdx < 2) {
                int[][] copy = copy(copyBoard);
                lineCheck(cctv, dirIdx, copy);
                lineCheck(cctv, dirIdx + 2, copy);
                dfs(idx + 1, size, copy);
            } else if (cctv.num == 3) {
                int[][] copy = copy(copyBoard);
                lineCheck(cctv, dirIdx, copy);
                lineCheck(cctv, dirIdx + 1, copy);
                dfs(idx + 1, size, copy);
            } else if (cctv.num == 4) {
                int[][] copy = copy(copyBoard);
                lineCheck(cctv, dirIdx, copy);
                lineCheck(cctv, dirIdx + 2, copy);
                lineCheck(cctv, dirIdx + 3, copy);
                dfs(idx + 1, size, copy);
            } else if (cctv.num == 5 && dirIdx == 0) {
                int[][] copy = copy(copyBoard);
                lineCheck(cctv, dirIdx, copy);
                lineCheck(cctv, dirIdx + 1, copy);
                lineCheck(cctv, dirIdx + 2, copy);
                lineCheck(cctv, dirIdx + 3, copy);
                dfs(idx + 1, size, copy);
            }
        }
    }

    public static int[][] copy(int[][] target) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = target[i][j];
            }
        }
        return copy;
    }

    public static void lineCheck(Cctv cctv, int dirIdx, int[][] copy) {
        int x = cctv.x + dir[dirIdx % 4][0];
        int y = cctv.y + dir[dirIdx % 4][1];

        while (true) {
            if (x < 0 || y < 0 || x >= N || y >= M) {
                break;
            }

            if (copy[x][y] == 6) {
                break;
            } else {
                copy[x][y] = 7;
            }

            x += dir[dirIdx % 4][0];
            y += dir[dirIdx % 4][1];
        }
    }
}