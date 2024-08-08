import java.util.*;

class Solution {
    class Term {
        int start;
        int end;
        int len;
        
        public Term(int a, int b) {
            start = a;
            end = b;
            len = b - a;
        }
    }
    
    public int[] solution(int[] sequence, int k) {
        /*
        비내림차순? a1 <= a2 <= a3 <= ... 이라한다.
        오름차순은 a1 < a2 < a3 < ....
        long 사용할 필요 없음. 각 원소가 최대 1,000 이고 최대 구간 백만 즉 합이 10억을 넘기진 못함
        시간 제한이 1초라면 O(N) 안에 풀어야함
        처음 딱 보자말자 누적합을 이용하는 게 어떨까라는 생각이 들었음(구간 연속합)
        누적합 + 투썸
        1 2 3 5 8 12 17
        
        */
        
        int[] prefixSum = new int[sequence.length];
        prefixSum[0] = sequence[0];
        for(int i = 1; i < sequence.length; i++) {
            prefixSum[i] = sequence[i] + prefixSum[i - 1];
        }
        
        int left = 0; 
        int right = sequence.length - 1;
        Queue<Term> pq = new PriorityQueue<>((t1, t2) -> {
            if(t1.len == t2.len) 
                return t1.start - t2.start;
            
            return t1.len - t2.len;
        });
                                  
        pq.add(new Term(0, right));
        
        
        for(int i = 0; i < right; i++) {
            if(prefixSum[i] == k) 
                pq.add(new Term(0, i));
        }
        
        while(left < right) {
            int sum = prefixSum[right] - prefixSum[left];
            // System.out.print(sum + " " + left + " " + right);
            if(sum == k) {
                left++;
                pq.add(new Term(left, right));
            } else if(sum < k) {
                // System.out.print(" up");
                left++;
                if(right + 1 < sequence.length)
                    right++;
            } else {
                right--;
            }
            // System.out.println();
        }
        
        // System.out.println();
        // for(Term t : pq) {
        //     System.out.println(t.start + " " + t.end + " " + t.len);
        // }
                         
        Term t = pq.poll();
        return new int[]{t.start, t.end};
    }
}