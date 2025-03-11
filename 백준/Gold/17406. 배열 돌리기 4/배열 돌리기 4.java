import java.util.*;
import java.io.*;

public class Main {
    static int[][] array;
    static int n, m, k, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        array = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Integer.MAX_VALUE;
        List<Integer>[] swapList = new ArrayList[k];
        for (int t = 0; t < k; t++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            swapList[t] = new ArrayList<>();
            swapList[t].add(r);
            swapList[t].add(c);
            swapList[t].add(s);
        }

        selectCase(swapList, 0, k);
        System.out.println(min);
    }

    static void selectCase(List<Integer>[] swapList, int deep, int size) {
        if (deep == size) {
            int[][] A = new int[n + 1][m + 1];

            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= m; j++) {
                    A[i][j] = array[i][j];
                }
            }

            for (int i = 0; i < swapList.length; i++) {
                Integer r = swapList[i].get(0);
                Integer c = swapList[i].get(1);
                Integer s = swapList[i].get(2);

                for (int q = 1; q <= s; q++) {
                    swap(A, r - q, c - q, 2 * q + 1);
                }
            }

            for (int e = 1; e <= n; e++) {
                int sum = Arrays.stream(A[e]).sum();
                min = Math.min(sum, min);
            }

            return;
        }

        for (int i = deep; i < size; i++) {
            listSwap(swapList, deep, i);
            selectCase(swapList, deep + 1, size);
            listSwap(swapList, deep, i);
        }
    }

    static void listSwap(List<Integer>[] swapList, int deep, int i) {
        List<Integer> tmp = swapList[deep];
        swapList[deep] = swapList[i];
        swapList[i] = tmp;
    }

    // r c l
    // 1 2 5
    // 5 6
    static void swap(int[][] A, int r, int c, int l) {
        int tmp = A[r][c];

        //  1 2 3 4 5   i < 6
        for (int i = r; i < (r + l - 1); i++) {
            A[i][c] = A[i + 1][c];
        }

        // 2 3 4 5    i < 6
        for (int i = c; i < (c + l - 1); i++) {
            A[r + l - 1][i] = A[r + l - 1][i + 1];
        }

        // 5 4 3 2   i > 1
        for (int i = (r + l - 1); i > r; i--) {
            A[i][c + l - 1] = A[i - 1][c + l - 1];
        }

        for (int i = (c + l - 1); i > c; i--) {
            A[r][i] = A[r][i - 1];
        }

        A[r][c + 1] = tmp;
    }
}