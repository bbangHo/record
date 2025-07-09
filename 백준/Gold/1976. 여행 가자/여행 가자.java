import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[] unf = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            unf[i] = i;
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= N; j++) {
                int conn = Integer.parseInt(st.nextToken());
                if(conn == 1) {
                    union(unf, i, j);
                }
            }
        }

        int[] list = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= M; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < M; i++) {
            if(find(unf, list[i]) != find(unf, list[i + 1])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    public static void union(int[] unf, int a, int b) {
        int findA = find(unf, a);
        int findB = find(unf, b);

        if(findA != findB) {
            if(a > b) {
                unf[findB] = findA;
            } else {
                unf[findA] = findB;
            }
        }
    }

    public static int find(int[] unf, int tar) {
        if(unf[tar] != tar) return unf[tar] = find(unf, unf[tar]);
        else return tar;
    }
}