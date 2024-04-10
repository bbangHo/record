class Solution {
    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.append(n % k);
            n /= k;
        }

        String[] nums = sb.reverse().toString().split("0");

        int cnt = 0;
        for(String s : nums) {
            if (!s.isEmpty() && isPrime(Long.parseLong(s))) {
                cnt++;
            }
        }

        return cnt;
    }

    public boolean isPrime(long num) {
        if(num < 2) {
            return false;
        }

        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }

        return true;
    }
}