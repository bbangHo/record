import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        /*
        n개 집, 최대 cap 개 싣기 가능 
        픽업을 가장 먼집부터 픽업하고, 가는길에 모든 집에 배달을 하면 될 듯
        4
        1 0 3 1 2
        0 3 0 2 3
        
        4
        1 0 1 0 0 
        0 3 0 1 0
        */
        
        long dist = 0;
        
        for(int i = n - 1; i >= 0;) {
            if(deliveries[i] == 0 && pickups[i] == 0) {
                i--;
                continue;
            }
            
            calc(deliveries, cap, i);
            calc(pickups, cap, i);
            
            dist += (i + 1) * 2;     
        }
        
        return dist;
    }
    
    public void calc(int[] list, int cap, int i) {
        while(i >= 0) {
            if(list[i] > cap) {
                list[i] -= cap;
                break;
            } else {
                cap -= list[i];
                list[i--] = 0;
            }
        }
    }
}