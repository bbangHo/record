import java.util.*;
import java.io.*;

public class Main {
    static class Metting {
        int start;
        int end;

        public Metting(int a, int b) {
            start = a;
            end = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Metting> list = new ArrayList<>();

        StringTokenizer st;
        int max = 0;
        for(int i = 0 ; i< n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            max = Math.max(max, end);
            list.add(new Metting(start, end));
        }

        list.sort((m1, m2) -> {
            if(m1.end == m2.end) {
                return m1.start - m2.start;
            }
            return m1.end - m2.end;
        });

        int cnt = 0;
        int end = 0;
        for(int i = 0; i < n; i++) {
            Metting m = list.get(i);

            if(m.start >= end && m.end >= end) {
                end = m.end;
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}