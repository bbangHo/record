import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        long min = 2_000_000_001;
        long max = 2_000_000_001;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            min = Math.min(min, arr[i]);
        }

        int ans = 0;
        while(min < max) {
            long mid = (min + max) / 2;
            long sum = 0;

            for (int i = 0; i < n; i++) {
                if(arr[i] < mid)
                    sum += mid - arr[i];
            }

            if(sum <= k) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        System.out.println(max - 1);
    }
}