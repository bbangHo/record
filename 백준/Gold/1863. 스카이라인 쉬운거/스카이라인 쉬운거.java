import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int cnt = 0;
        Stack<Integer> s = new Stack<>();
        s.push(0);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 현재 빌딩 높이가 더 작으면
            while (y < s.peek()) {
                s.pop();
                cnt++;
            }

            if (y > 0 && y > s.peek()) { // 현재 빌딩 높이가 더 크면
                s.push(y);
            }

        }

        while (s.peek() > 0) {
            s.pop();
            cnt++;
        }

        System.out.println(cnt);
    }
}