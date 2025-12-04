import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();

        for(int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }

        String reverse = sb.toString();
        int len = str.length();
        int maxLen = 0;
        for(int i = len - 1; i >= 0; i--) {
            String strSub = str.substring(i, len);
            String revSub = reverse.substring(0, len - i);

            if(strSub.equals(revSub) && strSub.length() > maxLen) {
                maxLen = strSub.length();
            }
        }

        System.out.println(len + (len - maxLen));
    }
}