import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for(int i = 0 ; i < T; i++){
            int N = Integer.parseInt(bf.readLine());
            int []tree = new int[N+1];

            StringTokenizer st;
            List<Integer> pathA = new ArrayList<>();
            List<Integer> pathB = new ArrayList<>();

            int A = 0;
            int B = 0;
            
            for(int j = 0; j < N-1; j++){
                st = new StringTokenizer(bf.readLine());
                A = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                tree[B] = A;
            }
            
            st = new StringTokenizer(bf.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            
            while(A!=0){
                pathA.add(A);
                A= tree[A];
            }
            while(B!=0){
                pathB.add(B);
                B= tree[B];
            }

            boolean found = false;
            for (Integer a : pathA) {
                for (Integer b : pathB) {
                    if (Objects.equals(a, b)) {
                        found = true;
                        System.out.println(a);
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }

        }
    }
}