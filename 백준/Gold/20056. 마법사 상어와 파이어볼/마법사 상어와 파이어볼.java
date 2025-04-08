import java.util.*;
import java.io.*;

public class Main {
    static class Fireball {
        int r, c, m, s, d;

        public Fireball(int q, int w, int e, int y, int t) {
            r = q;
            c = w;
            m = e;
            s = y;
            d = t;
        }

        public Fireball(int q, int w, Fireball fireball) {
            r = q;
            c = w;
            m = fireball.m;
            s = fireball.s;
            d = fireball.d;
        }
    }

    static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Fireball>[][] board = new LinkedList[N + 1][N + 1];
        List<Fireball> fireballs = new ArrayList<>();
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                board[r][c] = new LinkedList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireballs.add(new Fireball(r, c, m, s, d));
        }

        for (int k = 0; k < K; k++) {
            // 파이어볼 이동
            for (Fireball fireball : fireballs) {
                // 방향 d로 s칸 이동
                int dirIndex = fireball.d;
                int moveR = fireball.r + (dir[dirIndex][0] * fireball.s % N);
                int moveC = fireball.c + (dir[dirIndex][1] * fireball.s % N);

                if (moveR < 1) {
                    moveR += N;
                }

                if (moveC < 1) {
                    moveC += N;
                }

                if (moveR > N) {
                    moveR -= N;
                }

                if (moveC > N) {
                    moveC -= N;
                }

                board[moveR][moveC].add(new Fireball(moveR, moveC, fireball));
            }

            fireballs.clear();

            // 합쳐진 파이어볼 찾기 및 조정
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    Queue<Fireball> fq = board[r][c];
                    int sumBallNum = fq.size();

                    if (fq.size() > 1) {
                        // 파이어볼 합쳐짐
                        int sumM = 0; // 질량
                        int sumS = 0; // 속력
                        boolean isEven = true; // 짝수방향
                        boolean isOdd = true; // 홀수방향

                        while (!fq.isEmpty()) {
                            Fireball fireball = fq.poll();
                            sumM += fireball.m;
                            sumS += fireball.s;
                            isEven &= fireball.d % 2 == 0;
                            isOdd &= fireball.d % 2 == 1;
                        }

                        // 합쳐지는 파이어볼 방향
                        int startD = isEven || isOdd ? 0 : 1;

                        // 4개로 나눠진다
                        for (int i = 0; i < 4; i++) {
                            int divideM = sumM / 5; // 질량m은 (합쳐진 파이어볼 질량m의 합)/5
                            if (divideM == 0) { // 질량 0 ==소멸
                                break;
                            }

                            int divideS = sumS / sumBallNum;    //속력s는 (합쳐진 파이어볼의 속력s의 합)/합쳐진 파이어볼 개수
                            fireballs.add(new Fireball(r, c, divideM, divideS, startD));
                            startD += 2;
                        }
                    } else if(fq.size() == 1) {
                        fireballs.add(fq.poll());
                    }

                    fq.clear();
                }
            }
        }

        int totalM = 0;
        for (Fireball fireball : fireballs) {
            totalM += fireball.m;
        }

        System.out.println(totalM);
    }
}