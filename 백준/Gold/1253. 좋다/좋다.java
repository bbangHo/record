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

        Arrays.sort(arr);
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;

            while (left < right) {
                if (left == i) {
                    left++;
                } else if(right == i) {
                    right--;
                }

                if(left >= right) break;

                int sum = arr[left] + arr[right];
                if (sum == arr[i]) {
                    cnt++;
                    break;
                } else if (sum < arr[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(cnt);
    }
}