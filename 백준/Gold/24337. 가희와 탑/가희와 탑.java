import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        if ((a + b) >= (n + 2)) {
            System.out.println("-1");
            return;
        }

        if (a == b) {
            for (int i = 0; i < n; i++) {
                list.add(1);
            }
        } else if (a > b) {
            int cnt = n - (a + b - 1);
            for (int i = 0; i < cnt; i++) {
                list.add(1);
            }

            for (int i = 1; i <= a; i++) {
                list.add(i);
            }

            for (int i = Math.max(b - 1, 1); i > 0; i--) {
                list.add(i);
            }
        } else {
            if (a == 1) {
                list.add(b);

                int cnt = n - (a + b - 1);
                for (int i = 0; i < cnt; i++) {
                    list.add(1);
                }

                for (int i = Math.max(b - 1, 1); i > 0; i--) {
                    list.add(i);
                }
            } else {
                int cnt = n - (a + b - 1);
                for (int i = 0; i < cnt; i++) {
                    list.add(1);
                }

                for (int i = 1; i < a; i++) {
                    list.add(i);
                }

                for (int i = Math.max(b, 1); i > 0; i--) {
                    list.add(i);
                }
            }

        }

        for (int i = 0; i < n; i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}