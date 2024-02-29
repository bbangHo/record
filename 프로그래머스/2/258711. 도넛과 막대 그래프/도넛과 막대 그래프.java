import java.util.*;

class Solution {
    static boolean[] visited;
    static int[] in;
    static int[] out;
    
    public int[] solution(int[][] edges) {
        int n = 0;
        in = new int[1000001];
        out = new int[1000001];
        
        for(int[] e : edges) {
            out[e[0]]++;
            in[e[1]]++;
            n = Math.max(n, Math.max(e[0], e[1]));
        }
        
        int[] ans = new int[4];
        
        for(int i = 1; i < n + 1; i++) {
            if(in[i] == 0 && out[i] >= 2) {
                ans[0] = i;
            } else if(out[i] == 0) {    // 막대, out이 0 인 노드를 찾으면 찾을 수 있음
                ans[2]++;
            } else if(out[i] == 2 && in[i] > 0) {   // 8자, out = 2 이면서 in 0 이상인 노드를 찾으면 찾을 수 있음
                ans[3]++;
            }
        }
        
        // 도넛은 그래프 개수 - (막대 + 8자 개수)
        ans[1] = out[ans[0]] - (ans[2] + ans[3]);
        
        return ans;
    }
}
