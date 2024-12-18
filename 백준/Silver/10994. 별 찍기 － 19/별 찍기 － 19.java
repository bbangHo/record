import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int size = 1 + 4 * (n - 1);

        boolean[][] star = new boolean[size][size];
        checkStar(0, 0, size, star);

        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(star[i][j]) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

    public static void checkStar(int r, int c, int size, boolean[][] star) {
        if(size == 1) {
            star[r][c] = true;
            return;
        }

        for(int i = r; i < size + r; i++) {
            star[r][i] = true;
            star[i][r] = true;
            star[size + r - 1][i] = true;
            star[i][size + r - 1] = true;
        }

        checkStar(r + 2, c + 2, size - 4, star);
    }
}