import java.io.*;
import java.util.*;

public class Main {
    public static char LEFT = '<';
    public static char RIGHT = '>';
    public static char BACK_SPACE = '-';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int cursor = 0;
            LinkedList<Character> q = new LinkedList();
            String str = br.readLine();

            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);

                if (c == LEFT) {
                    cursor -= cursor == 0 ? 0 : 1;
                    continue;
                }

                if (c == RIGHT) {
                    cursor += cursor >= q.size() ? 0 : 1;
                    continue;
                }

                if (c == BACK_SPACE) {
                    if (cursor != 0) {
                        q.remove(--cursor);
                    }
                    continue;
                }

                q.add(cursor++, c);
            }

            StringBuilder sb = new StringBuilder();
            while (!q.isEmpty()) {
                sb.append(q.poll());
            }

            System.out.println(sb.toString());
        }
    }
}