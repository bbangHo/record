class Solution {
        static int[] ansArr;
        static int max = Integer.MIN_VALUE;

        public int[] solution(int n, int[] info) {
            dfs(new int[11], n, 0, 0, info);

            if (ansArr == null)
                return new int[]{-1};

            return ansArr;
        }

        public void dfs(int[] arr, int n, int idx, int cnt, int[] apeach) {
            if (cnt > n) return;

            // 화살을 모두 소비
            if (idx == 11) {
                int apScore = 0, liScore = 0;
                for (int i = 0; i < 11; i++) {
                    if (arr[i] > 0 && arr[i] > apeach[i]) {
                        liScore += 10 - i;
                    } else if (apeach[i] > 0) {
                        apScore += 10 - i;
                    }
                }

                // 차이 크기 갱신
                if (liScore - apScore > max && liScore - apScore > 0) {
                    max = liScore - apScore;
                    ansArr = arr.clone();
                }

                // 크기 차이가 같으면 낮은 점수를 맞춘 경우를 찾는다.
                else if (liScore - apScore == max && liScore - apScore > 0) {
                    for (int i = 10; i >= 0; i--) {
                        if (arr[i] > ansArr[i]) {
                            ansArr = arr.clone();
                            break;
                        } else if (arr[i] < ansArr[i])
                            break;
                    }
                }

                return;
            }

            for (int i = 0; i <= apeach[idx] + 1; i++) {
                arr[idx] += i;
                dfs(arr, n, idx + 1, cnt + i, apeach);
                arr[idx] = 0;
            }
        }
    }