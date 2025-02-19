import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int n, max;
    static List<Integer> nums;
    static List<Character> operator;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;
        nums = new ArrayList<>();
        operator = new ArrayList<>();

        String input = br.readLine();
        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                operator.add(c);
            } else {
                nums.add(Integer.parseInt(String.valueOf(c)));
            }
        }

        dfs(0, nums.get(0));
        System.out.println(max);
    }

    public static void dfs(int opIdx, int result) {
        if (opIdx >= operator.size()) {
            max = Math.max(max, result);
            return;
        }

        // 괄호가 없을 때     1*2+3*4
        int nowValue = calc(result, operator.get(opIdx), nums.get(opIdx + 1));
        dfs(opIdx + 1, nowValue);

        // 괄호가 있을 때  1*(2+3)*4
        if(opIdx + 1 < operator.size()) {
            int nextValue = calc(nums.get(opIdx+1), operator.get(opIdx + 1), nums.get(opIdx + 2));
            result = calc(result, operator.get(opIdx), nextValue);
            dfs(opIdx + 2, result);
        }
    }

    public static int calc(int a, char operator, int b) {
        if (operator == '+') {
            return a + b;
        } else if (operator == '-') {
            return a - b;
        } else {
            return a * b;
        }
    }
}