import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        String bomb = br.readLine();

        Stack<Character> s = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            boolean isSame = true;
            s.push(str.charAt(i));

            if(s.size() >= bomb.length()) {
                for(int j = 0; j < bomb.length(); j++) {
                    if(!s.get(s.size() - bomb.length() + j).equals(bomb.charAt(j))) {
                        isSame = false;
                        break;
                    }
                }

                if(isSame) {
                    for(int j = 0; j < bomb.length(); j++) {
                        s.pop();
                    }
                }
            }
        }

        if(s.empty())
            System.out.println("FRULA");
        else {
            for(Character c : s) {
                sb.append(c);
            }

            System.out.println(sb.toString());
        }

    }
}