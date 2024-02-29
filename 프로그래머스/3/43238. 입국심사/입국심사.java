import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long sum = 0;
        long max = (long)times[times.length - 1] * n;
        long min = times[0];
        
        while(min < max) {
            long avg = (min + max) / 2;
            
            long tmp = 0;
            for(int time : times) {
                tmp += avg / time;
            }
            
            if(tmp < n) {
                min = avg + 1;
            } else {
                max = avg;
            }
        }
        return max;
    }
}