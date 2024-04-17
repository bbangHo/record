import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        int n = queue1.length;
        long sum1 = 0, sum2 = 0;
        
        for(int i = 0; i < n; i++) {
            sum1 += queue1[i]; 
            sum2 += queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        
        if(n == 1 && sum1 != sum2) {
            return -1;
        }
        
        if((sum1 + sum2) % 2 == 1) {
            return -1;
        }
        
        long half = (sum1 + sum2) / 2;
        int maxIterCnt = 4 * n;
        int iterCnt = 0;
        while(sum1 != sum2) {
            if(iterCnt > maxIterCnt) {
                return -1;
            }
            
            if (sum1 < sum2) {
                int n2 = q2.poll();
                sum2 -= n2;
                sum1 += n2;
                q1.add(n2);
            } else if (sum1 > sum2) {
                int n1 = q1.poll();
                sum1 -= n1;
                sum2 += n1;
                q2.add(n1);
            }
            
            iterCnt++;
        }
        
        return iterCnt;
    }
}