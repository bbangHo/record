class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] board = new int[rows + 1][columns + 1];
        
        int idx = 1;
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= columns; j++) {
                board[i][j] = idx++;
                // System.out.print(board[i][j] + " ");
            }
            // System.out.println();
        }
        
        // r1, c1, r2, c2
        // (r1, c1) (r2, c1) 조합으로 c1 == c2 가 될 때 까지 반복 (왼->오 방향)
        // (r1 + 1, c1) (r1 + 1, c2) 조합으로 r1 == r2 - 1 가 될 때 까지 반복 (위 -> 아래 방향, 모소리 중복 제거)
        
        int[] minList = new int[queries.length];
        int index = 0;
        
        for(int i = 0; i < queries.length; i++) {
            int r1 = queries[i][0];
            int c1 = queries[i][1];
            int r2 = queries[i][2];
            int c2 = queries[i][3];
            
            int tmp1 = board[r1][c1];
            int tmp2 = board[r1][c2];
            int tmp3 = board[r2][c2];
            int tmp4 = board[r2][c1];
            int min = Math.min(tmp1, Math.min(tmp2, Math.min(tmp3, tmp4)));
            
            for(int r = r1; r < r2; r++) {
                min = Math.min(min, board[r + 1][c1]);
                board[r][c1] = board[r + 1][c1];
            }
            
            for(int c = c1; c < c2; c++) {
                min = Math.min(min, board[r2][c + 1]);
                board[r2][c] = board[r2][c + 1];
            }
            
            for(int r = r2; r > r1; r--) {
                min = Math.min(min, board[r - 1][c2]);
                board[r][c2] = board[r - 1][c2];
            }
            
            for(int c = c2; c > c1; c--) {
                min = Math.min(min, board[r1][c - 1]);
                board[r1][c] = board[r1][c - 1];
            }
                
            board[r1][c1 + 1] = tmp1;
            board[r1 + 1][c2] = tmp2;
            board[r2][c2 - 1] = tmp3;
            board[r2 - 1][c1] = tmp4;
            
            minList[index++] = min;
        }
        
        return minList;
    }
}