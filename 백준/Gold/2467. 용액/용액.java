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

        int leftValue = Integer.MIN_VALUE;
        int rightValue = Integer.MAX_VALUE;
        int closerZeroSum = Integer.MAX_VALUE;
        int leftIndex = 0;
        int rightIndex = N - 1;

        while (leftIndex < rightIndex) {
            int sum = arr[leftIndex] + arr[rightIndex];
            if (sum == 0) {
                System.out.println(arr[leftIndex] + " " + arr[rightIndex]);
                return;
            }

            if (closerZeroSum > Math.abs(sum)) {
                closerZeroSum = Math.abs(sum);
                leftValue = arr[leftIndex];
                rightValue = arr[rightIndex];
            }

            if (sum > 0) {
                rightIndex--;
            } else {
                leftIndex++;
            }
        }

        System.out.println(leftValue + " " + rightValue);
    }
}

