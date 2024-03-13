import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        sb.append(br.readLine());

        while (str.length() < sb.length()) {
            if (sb.toString().endsWith("A")) {    // 마지막에 A를 추가하는 연산을 했따. = A를 제거한다.
                sb.deleteCharAt(sb.length() - 1);
            } else {    // 마지막에 뒤집고 B 추가 연산을 했다. = B를 삭제하고 뒤집는다.
                sb.deleteCharAt(sb.length() - 1);
                sb = new StringBuilder(sb.toString()).reverse();
            }
        }

        System.out.println(sb.toString().equals(str) ? 1 : 0);
    }
}