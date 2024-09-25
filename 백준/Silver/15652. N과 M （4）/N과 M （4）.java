import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] arr;
    static StringBuilder sb;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        dfs(1, 0);
        System.out.println(sb);
    }
    
    public static void dfs(int at, int deep) {
        if(deep == m) {
            for(int num : arr) {
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }
        
        for(int i = at; i <= n; i++) {
            arr[deep] = i;
            dfs(i, deep + 1);
        }
    }
}