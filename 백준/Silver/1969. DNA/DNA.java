import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // a c g t 순
        int[][] atgcCount = new int[4][m];
        String[] atgc = new String[n];

        for(int i = 0; i < n; i++) {
            atgc[i] = br.readLine();
        }

        StringBuilder sb = new StringBuilder();
        int hammingDistance = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                char c = atgc[j].charAt(i);

                if(c == 'A') {
                    atgcCount[0][i]++;
                } else if(c == 'C') {
                    atgcCount[1][i]++;
                } else if(c == 'G') {
                    atgcCount[2][i]++;
                } else {
                    atgcCount[3][i]++;
                }
            }

            char maxCountChar = 'A';
            int maxCount = 0;
            int hDistance = 0;
            for(int j = 0; j < 4; j++) {
                if(maxCount < atgcCount[j][i]) {
                    maxCountChar = j == 0 ? 'A' : j == 1 ? 'C' : j == 2 ? 'G' : 'T';
                    maxCount = atgcCount[j][i];
                }

                hDistance += atgcCount[j][i];
            }

            sb.append(maxCountChar);
            hammingDistance += hDistance - maxCount;
        }

        System.out.println(sb.toString());
        System.out.println(hammingDistance);
    }
}