import java.util.*;

class Solution {
    static int max = 0;
    static List<List<Integer>> tree;

    public int solution(int[] info, int[][] edges) {
        tree = new ArrayList<>();

        for(int i = 0; i < info.length; i++) {
            tree.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            tree.get(e[0]).add(e[1]);
        }

        ArrayList<Integer> check = new ArrayList<>();
        check.add(0);
        dfs(check, 0, 0, 0, info);

        return max;
    }

    public void dfs(List<Integer> visitable, int idx, int sheep, int wolf, int[] info) {
        if(info[idx] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if(sheep - wolf <= 0) return;

        max = Math.max(max, sheep);

        List<Integer> next = new ArrayList<>();
        next.addAll(visitable);
        next.remove(Integer.valueOf(idx));

        for(int child : tree.get(idx)) {
            next.add(child);
        }

        for(int n : next) {
            dfs(next, n, sheep, wolf, info);
        }
    }
}