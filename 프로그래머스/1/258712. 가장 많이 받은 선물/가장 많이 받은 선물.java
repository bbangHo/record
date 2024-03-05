import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        /**
        * 기록이 있다면, 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 "다음 달"에 선물을 하나 받음
        * 두 사람이 선물을 주고받은 "기록이 하나도 없거나" "주고받은 수가 같다면"
        * 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
        * '선물 지수'는 이번 달까지 자신이 친구들에게 준 선물 - 받은 선물의 수
        * 선물 지수도 같다면 다음 달에 선물을 주고받지 않습니다.
        * return 선물을 가장 많이 받을 친구가 받을 선물의 수
        * ! 이름 = 알파벳 소문자로 이루어진 길이가 10 이하인 문자열
        * ! 이름이 같은 친구는 없습니다. 
        * ! A가 B에게 선물을 준다. A != B
        */
        int n = friends.length;
        Map<String, Integer> map = new HashMap<>();  // 이름-인덱스 매핑
        int[][] table = new int[n][n];  // 주고 받은 선물
        int[] point = new int[n];
        
        int idx = 0;
        for(String f : friends) {
            map.put(f, idx++);
        }
        
        for(String g : gifts) {
            String[] str = g.split(" ");
            
            int sender = map.get(str[0]);
            int reciver = map.get(str[1]);
            
            table[sender][reciver]++;
            point[sender]++;    // 준 선물 수 -
            point[reciver]--;   // 받은 선물의 수
        }
        
        int ans = 0;
        for(int sndr = 0; sndr < n; sndr++) {
            int cnt = 0;
            for(int rcv = 0; rcv < n; rcv++) {
                if(sndr == rcv) continue;
                if(table[sndr][rcv] > table[rcv][sndr]) cnt++; 
                else if(table[sndr][rcv] == table[rcv][sndr] && point[sndr] > point[rcv]) cnt++;
            }
            System.out.println(sndr + " " + cnt);
            ans = Math.max(ans, cnt);
        }
        
        return ans;    // 위 로직에서 두 번씩 체크되기 때문에 2로 나눔
    }
}