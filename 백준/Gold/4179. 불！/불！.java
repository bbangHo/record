import java.awt.Point;
import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int x;
        int y;
        int count;

        public Point(int a, int b, int c) {
            x = a;
            y = b;
            count = c;
        }
    }

    public static int[][] dir = {{-1,0},{1,0}, {0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] maze = new char[R][C];
        List<Point> fireList = new ArrayList<>();
        Point start = null;

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j = 0; j < C; j++) {
                char c = line.charAt(j);

                if(c == 'F') {
                    fireList.add(new Point(i, j, 0));
                    maze[i][j] = c;
                } else if(c == 'J') {
                    start = new Point(i, j, 0);
                    maze[i][j] = '.';
                } else {
                    maze[i][j] = c;
                }
            }
        }

        Queue<Point> q = new LinkedList<>();
        Queue<Point> fq = new LinkedList<>();
        q.add(start);
        fq.addAll(fireList);
        boolean[][] visited = new boolean[R][C];
        visited[start.x][start.y] = true;
        Point result = null;
        int fireCount = 0;

        while(!q.isEmpty()) {
            Point point = q.poll();

            if(result != null) {
                break;
            }

            if(point.count == fireCount) {
                // 불 확산
                List<Point> newFqList = new ArrayList<>();
                while (!fq.isEmpty()) {
                    Point fire = fq.poll();

                    // 불 인접 4방향에 대해서
                    for (int i = 0; i < 4; i++) {
                        int nextX = fire.x + dir[i][0];
                        int nextY = fire.y + dir[i][1];

                        if (nextX >= 0 && nextY >= 0 && nextX < R && nextY < C) {
                            char c = maze[nextX][nextY];
                            // 불이 확산 가능하면 확산
                            if (c == '.') {
                                maze[nextX][nextY] = 'F';
                                newFqList.add(new Point(nextX, nextY, 0));
                            }
                        }
                    }
                }

                fq.addAll(newFqList);
                fireCount++;
            }

            // 진호가 갈 수 있는 4방향 bfs
            for(int i = 0; i < 4; i++) {
                int nextX = point.x + dir[i][0];
                int nextY = point.y + dir[i][1];

                // 탈출 성공
                if(nextX<= -1 || nextY <= -1 || nextX >= R || nextY >= C) {
                    result = new Point(nextX, nextY, point.count + 1);
                    break;
                } else if(!visited[nextX][nextY] && maze[nextX][nextY] != '#' && maze[nextX][nextY] != 'F') {
                    visited[nextX][nextY] = true;
                    q.add(new Point(nextX, nextY, point.count + 1));
                }
            }
        }

        System.out.println(result != null ? result.count : "IMPOSSIBLE");

    }
}