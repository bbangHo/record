import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] line = new int[n + 1][3];
        
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());    
            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
            line[i][2] = Integer.parseInt(st.nextToken());
        }
        
        int[][] max = new int[n + 1][3];
        int[][] min = new int[n + 1][3];
        
        for(int i = 1; i <= n; i++) {
            max[i][0] = Math.max(max[i - 1][0], max[i - 1][1]) + line[i][0]; 
            max[i][1] = Math.max(max[i - 1][0], Math.max(max[i - 1][1], max[i - 1][2])) + line[i][1]; 
            max[i][2] = Math.max(max[i - 1][1], max[i - 1][2]) + line[i][2]; 
            
            min[i][0] = Math.min(min[i - 1][0], min[i - 1][1]) + line[i][0]; 
            min[i][1] = Math.min(min[i - 1][0], Math.min(min[i - 1][1], min[i - 1][2])) + line[i][1]; 
            min[i][2] = Math.min(min[i - 1][1], min[i - 1][2]) + line[i][2]; 
        }
        
        int rMax = 0;
        int rMin = 2_147_483_647;
        for(int i = 0; i < 3; i++) {
            rMax = Math.max(rMax, max[n][i]);
            rMin = Math.min(rMin, min[n][i]);
        }
        
        System.out.println(rMax + " " + rMin);
        
    }
}