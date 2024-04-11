import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        /*
        신고 횟수 제한 x
        한 사람을 여러 번 신고 가능 하지만, 동일 인물에 대한 신고 횟수는 1회로 처리
        k번 이상 신고당하면 정지
        */
        
        Map<String, Set<String>> freeze = new HashMap<>();
        Map<String, Integer> user = new HashMap<>();
        
        for(String id : id_list) {
            freeze.put(id, new HashSet<String>());
            user.put(id, 0);
        }
        
        for(String s : report) {
            String[] users = s.split(" ");
            freeze.get(users[1]).add(users[0]);
        }   
        
        for(int i = 0; i < freeze.size(); i++) {
            if(freeze.get(id_list[i]).size() >= k) {
                Set<String> set = freeze.get(id_list[i]);
            
                for(String s : set) {
                    user.replace(s, user.get(s) + 1);
                }
            }
        }
        
        int[] answer = new int[user.size()];
        for(int i = 0; i < user.size(); i++) {
            answer[i] = user.get(id_list[i]);
        }
        
        return answer;
    }
}