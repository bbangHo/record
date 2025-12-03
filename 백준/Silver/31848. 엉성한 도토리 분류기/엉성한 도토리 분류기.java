import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        int[] holeSize = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            holeSize[i] = a[i] + i;

            if (i > 0) {
                holeSize[i] = Math.max(holeSize[i - 1], holeSize[i]);
            }
        }



        int Q = Integer.parseInt(br.readLine());
        int[] s = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            System.out.print(lowerBound(holeSize, 0, N, s[i]) + 1 + " ");
        }
    }

    private static int lowerBound(int[] holeSize, int left, int right, int tar) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (holeSize[mid] >= tar) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}