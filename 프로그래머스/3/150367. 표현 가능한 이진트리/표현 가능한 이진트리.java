class Solution {
    public int[] solution(long[] numbers) {
        /*
        순회는 왼 - 중 - 오 순
        95 = 1011111 안되는 이유: 101 즉, 루트의 왼쪽 자식이 더미지만 그 더미의 자식들은 더미가 아님 = 성립 x
        1. 수를 이진수로 변환해서 루트 노드 찾기 = 제일 중앙에 있는 이진수
        2. 서브트리의 루트노드 찾아서 0(더미) 인지 확인
        3. 0인데 자식중에 1이 있는지 확인
        4. 자식 중에 1이 있으면 모순이니 flase 리턴
        ex) 63 = 011 1 111 O
        111 = 110 1 111 O
        5 = 1 0 1 X
        */
        int[] answer = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i++) {
            String binary = toBinary(numbers[i]);
            
            if (binary.equals("0")) {
                answer[i] = 0;
            } else if(isDummy(0, binary.length() - 1, binary)) {
                answer[i] = 1;
            } 
        }
        
        return answer;
    }
    
    public String toBinary(Long num) {
        StringBuilder sb = new StringBuilder(Long.toBinaryString(num));
        int n = 0;
        
        while(sb.length() > (int)Math.pow(2, n) - 1) {
            n++;
        }
        
        while(sb.length() < (int)Math.pow(2, n) - 1) {
            sb.insert(0, '0');
        }
        
        return sb.toString();
    }
    
    public boolean isDummy(int left, int right, String binary) {
        if(left >= right) {
            return true;
        }
        
        int root = (left + right) / 2;
        
        if(binary.charAt(root) == '0') {
            for(int i = left; i <= right; i++) {
                if(binary.charAt(i) == '1') {
                    return false;
                }
            }
        } 
        
        return isDummy(left, root - 1, binary) && isDummy(root + 1, right, binary);
        
    }
    
}