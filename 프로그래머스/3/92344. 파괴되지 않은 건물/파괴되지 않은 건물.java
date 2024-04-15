class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] prefixSum = new int[n + 1][m + 1];

        for(int[] s : skill) {
            s[5] *= s[0] == 1 ? -1 : 1;

            prefixSum[s[1]][s[2]] += s[5];
            prefixSum[s[1]][s[4] + 1] -= s[5];
            prefixSum[s[3] + 1][s[2]] -= s[5];
            prefixSum[s[3] + 1][s[4] + 1] += s[5];
        }


        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                prefixSum[j + 1][i] += prefixSum[j][i]; // 위에서 아래로 누적합
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                prefixSum[i][j + 1] += prefixSum[i][j]; // 왼 -> 오 누적합
            }
        }


        int answer = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] + prefixSum[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}