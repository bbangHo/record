import java.util.*;
import java.io.*;

public class Main {
    static int[] colorPaperCnt;
    static int[][] board;
    static int n, res;

    public static void main(String[] args) throws IOException {
        n = 10;
        res = Integer.MAX_VALUE;
        colorPaperCnt = new int[6];
        board = new int[n + 1][n + 1];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 1);
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    public static void dfs(int r, int c) {
        // 종료 후 최소값 계산
        if (r == 10 && c == 11) {
            res = Math.min(getPaperCnt(), res);
            return;
        }

        // 색종이 5초과
        if (!isOver()) {
            return;
        }

        if (c >= 11) {
            dfs(r + 1, 1);
            return;
        }

        if (board[r][c] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (isAttach(r, c, size) && colorPaperCnt[size] < 5) {
                    attach(r, c, size, 0); // 붙이기
                    colorPaperCnt[size]++;
                    dfs(r, c + 1);
                    attach(r, c, size, 1); // 떼기
                    colorPaperCnt[size]--;
                }
            }
        } else {
            dfs(r, c + 1);
        }
    }

    public static int getPaperCnt() {
        int sum = 0;
        for (int i = 1; i <= 5; i++) {
            sum += colorPaperCnt[i];
        }

        return sum;
    }

    public static boolean isOver() {
        for (int i = 1; i <= 5; i++) {
            if (colorPaperCnt[i] > 5) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAttach(int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if(i < 1 || i > 10 || j < 1 || j > 10) {
                    return false;
                }

                if (board[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }


    // 색종이 붙이기
    public static void attach(int r, int c, int size, int value) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                board[i][j] = value;
            }
        }
    }
}