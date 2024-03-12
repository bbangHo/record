import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        StringTokenizer st;
        
        for(int i = 0 ; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr, (a1, a2) -> {
           if(a1[1] == a2[1]) return a1[0] - a2[0];
           return a1[1] - a2[1];
        });
        
        int cnt = 0;
        int end = 0;
        for(int i = 0; i < n; i++) {
            if(end <= arr[i][0] && end <= arr[i][1]) {
                end = arr[i][1];
                cnt++;
            }
        }
        
        System.out.println(cnt);
    }
}