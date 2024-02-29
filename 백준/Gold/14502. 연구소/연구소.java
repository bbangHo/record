import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int m;
    static int n;
    static int max;
    static int[][] tmp;
    static List<Point> virus = new ArrayList<>();

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        tmp = new int[n][m];    // 얕은 복사용

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());;
                if (map[i][j] == 2) {
                    virus.add(new Point(i, j));
                }
            }
        }

        setWall(0);
        System.out.println(max);
    }

    public static void setWall(int h) {
        if (h == 3) {
            tmp = deepCopy();
            for (int i = 0; i < virus.size(); i++) {
                Point point = virus.get(i);
                spreadVirus(point.x, point.y);
            }
            counting();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;  // 벽 세우기
                    setWall(h + 1);
                    map[i][j] = 0;  // 원복
                }
            }
        }
    }

    public static void counting() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tmp[i][j] == 0)
                    cnt++;
            }
        }
        max = Math.max(max, cnt);
    }

    public static void spreadVirus(int x, int y) {
        if (tmp[x][y] == 0)
            tmp[x][y] = 2;

        if(x+1 < n && tmp[x+1][y] == 0)
            spreadVirus(x + 1, y);

        if(y+1 < m && tmp[x][y+1] == 0)
            spreadVirus(x, y + 1);

        if(x-1 >= 0 && tmp[x-1][y] == 0)
            spreadVirus(x - 1, y);

        if(y-1 >= 0 && tmp[x][y-1] == 0)
            spreadVirus(x, y - 1);
    }

    public static int[][] deepCopy() {
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = map[i][j];
            }
        }

        return temp;
    }
}