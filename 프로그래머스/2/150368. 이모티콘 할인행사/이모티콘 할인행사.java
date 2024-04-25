import java.util.*;
import java.util.stream.*;

class Solution {
    static int eSize = 0;
    static int uSize = 0;
    static int[] answer = new int[]{0, 0};
    
    public int[] solution(int[][] users, int[] emoticons) {
        /*
        임티 7개, 할인률 10,20,30,40% 4개 7^4 개의 경우의 수
        유저마다 임티 7개씩 돌면서 살 수 있는지 확인 100*7
        7^4 * 100 * 7 정도의 시간복잡도
        완탐
        */
        eSize = emoticons.length;
        uSize = users.length;
        
        int[] discount = new int[eSize];
        Arrays.fill(discount, 40);
        dfs(discount, 0, emoticons, users);
        
        return answer;
    }
    
    // 임티 할인율 경우의 수 구하기
    public void dfs(int[] discount, int idx, int[] emoticons, int[][] users) {
        if(idx >= eSize) return;
        if(discount[idx] < 10) return;
        
        if(idx == eSize - 1) {
            calc(discount, emoticons, users);
        }
        
        dfs(discount, idx + 1, emoticons, users);
        discount[idx] -= 10; 
        dfs(discount, idx, emoticons, users);
        discount[idx] += 10;
    }
    
    public void calc(int[] discount, int[] emoticons, int[][] users) {
        int ePlus = 0;
        int ePrice = 0;
        
        for(int i = 0; i < uSize; i++) {
            int uPrice = 0;
            
            for(int j = 0; j < eSize; j++) {    
                // 할인율이 [i][0] 보다 크면 무조건 이모티콘을 구매함
                if(users[i][0] <= discount[j]) {
                    uPrice += (1 - discount[j] / 100.0) * emoticons[j]; 
                }
            }
            
            if(uPrice >= users[i][1]) { // 이모티콘 총액이 구매비용 이상이면 임티플 가입
                ePlus++;
            } else {    // 구매비용 이하 임티플 가입 x
                ePrice += uPrice;   
            }
        }
        if(answer[0] < ePlus) {
            answer[0] = ePlus;
            answer[1] = ePrice;
        } else if(answer[0] <= ePlus) {    // 임티플 가입자 최대 갱신시
            answer[1] = Math.max(answer[1], ePrice);    
        }
    }
}