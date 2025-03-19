import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int S = Integer.parseInt(br.readLine());

        /**
         교환은 0~s번 즉 최대 s번 진행한 결과가
         사전순으로 제일 마지막인 결과를 출력하는 문제
         순열? 조합? 시간 복잡도 안됨
         */
        int cnt = 0;    // 교환 횟수
        for (int i = 0; i < N - 1 && cnt < S; i++) {
            // 값을 바꿔야 할 때
            int maxValueIdx = i;
            // 변경 가능한 횟수까지 검사한다.
            for (int j = i + 1; j < N && j <= i + (S - cnt); j++) {
                if (arr[maxValueIdx] < arr[j]) {
                    maxValueIdx = j;
                }
            }

            for (int j = maxValueIdx; j > i; j--) {
                int tmp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = tmp;
                cnt++;
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}