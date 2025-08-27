import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();
        String S = "";
        String T = "";
        int cnt = 0;
        Map<String, String> map = new HashMap<>();


        for (int i = 0; i < N; i++) {
            list.add(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            String word = list.get(i);
            for (int j = 0; j < word.length(); j++) {
                String prefix = word.substring(0, j + 1);
                if (map.containsKey(prefix)) {
                    if (j + 1 > cnt) {
                        S = map.get(prefix);
                        T = word;
                        cnt = j + 1;
                    } else if (j + 1 == cnt) {
                        String s = map.get(prefix);
                        String t = word;

                        if(list.indexOf(S) > list.indexOf(s)) {
                            S = s;
                            T = t;
                        }
                    }
                } else {
                    map.put(prefix, word);
                }
            }
        }

        System.out.println(S);
        System.out.println(T);
    }
}