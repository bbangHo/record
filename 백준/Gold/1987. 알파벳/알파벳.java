import java.util.*;
import java.io.*;

public class Main {
    static Set<Character> visited;
    static char[][] arr;
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            String line = br.readLine();

            for (int j = 1; j <= c; j++) {
                arr[i][j] = line.charAt(j - 1);
            }
        }

        visited = new HashSet<>();
        System.out.println(dfs(1, 1, 1));
    }

    public static int dfs(int x, int y, int deep) {
        if (x < 1 || y < 1 || x > r || y > c || visited.contains(arr[x][y])) {
            return deep - 1;
        }

        visited.add(arr[x][y]);
        int max = Math.max(dfs(x - 1, y, deep + 1),
                Math.max(dfs(x, y + 1, deep + 1),
                        Math.max(dfs(x + 1, y, deep + 1), dfs(x, y - 1, deep + 1))));
        visited.remove(arr[x][y]);
        return max;
    }
}