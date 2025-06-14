import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        while (true) {
            if (input.equals("end")) {
                break;
            }

            char[][] board = new char[3][3];
            int xCnt = 0;
            int oCnt = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = input.charAt(i * 3 + j);

                    if (board[i][j] == 'O') {
                        oCnt++;
                    }

                    if (board[i][j] == 'X') {
                        xCnt++;
                    }
                }
            }

            boolean bingoO = isBingo(board, 'O');
            boolean bingoX = isBingo(board, 'X');
            if (oCnt == xCnt) {
                if (bingoO && !bingoX) {
                    System.out.println("valid");
                } else {
                    System.out.println("invalid");
                }
            } else if (oCnt + 1 == xCnt) {      // x가 다음 수를 둔 경우라면
                if (!bingoO && bingoX) {        // o는 빙고가 될 수 없다. x가 빙고이다.
                    System.out.println("valid");
                } else if (oCnt + xCnt == 9 && !bingoO && !bingoX) { // 판이 꽉찬 경우이다.
                    System.out.println("valid");
                } else {
                    System.out.println("invalid");
                }
            } else {
                System.out.println("invalid");
            }

            input = br.readLine();
        }
    }

    public static boolean isBingo(char[][] board, char c) {
        return board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[1][0] != '.' && board[1][0] == c ||
                board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[1][1] != '.' && board[1][1] == c ||
                board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[1][2] != '.' && board[1][2] == c ||
                // 세로
                board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][1] != '.' && board[0][1] == c ||
                board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][1] != '.' && board[1][1] == c ||
                board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][1] != '.' && board[2][1] == c ||
                // 가로
                board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != '.' && board[1][1] == c ||
                board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[1][1] != '.'
                        && board[1][1] == c;      // 대각
    }
}

