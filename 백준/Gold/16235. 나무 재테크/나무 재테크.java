import java.util.*;
import java.io.*;

public class Main {
    static class Tree {
        int r;
        int c;
        int age;

        public Tree(int a, int b, int d) {
            r = a;
            c = b;
            age = d;
        }
    }

    static int n, m, k;
    static int[][] A, ground;
    static Deque<Tree> trees;
    static Deque<Tree> deadTrees;
    static int[] dirR = { -1, -1, -1, 0, 1, 1, 1, 0 };
    static int[] dirC = { -1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());    // n*n
        m = Integer.parseInt(st.nextToken());    //
        k = Integer.parseInt(st.nextToken());    // k년

        A = new int[n + 1][n + 1];
        ground = new int[n + 1][n + 1];
        trees = new LinkedList<>();
        deadTrees = new LinkedList<>();

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5;
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(r, c, age));
        }

        for(int i = 0; i < k; i++) {
            spring();
            summer();
            autumn();
            wintter();
        }

        System.out.println(trees.size());
    }

    public static void spring() {
        int size = trees.size();
        for(int i = 0; i < size; i++) {
            Tree t = trees.poll();

            if(t.age <= ground[t.r][t.c]) {
                ground[t.r][t.c] -= t.age++;
                trees.add(t);
            } else {
                deadTrees.add(t);
            }
        }
    }

    public static void summer() {
        while(!deadTrees.isEmpty()) {
            Tree t = deadTrees.poll();
            ground[t.r][t.c] += t.age / 2;
        }
    }

    public static void autumn() {
        List<Tree> temp = new ArrayList<>();
        for(Tree t : trees) {
            if(t.age % 5 == 0) {
                temp.add(t);
            }
        }

        int size = temp.size();
        for(int i = 0; i < size; i++) {
            Tree t = temp.get(i);

            for(int j = 0; j < 8; j++) {
                int r = t.r + dirR[j];
                int c = t.c + dirC[j];

                if(r > 0 && c > 0 && r <= n && c <= n) {
                    trees.addFirst(new Tree(r, c, 1));
                }
            }
        }
    }

    public static void wintter() {
        for(int r = 1; r <= n; r++) {
            for(int c = 1; c <= n; c++) {
                ground[r][c] += A[r][c];
            }
        }
    }
}