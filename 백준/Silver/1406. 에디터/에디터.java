import java.util.*;
import java.io.*;

public class Main {
    public static LinkedList<String> left;
    public static LinkedList<String> right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        left = new LinkedList<>();
        right = new LinkedList<>();
        for(int i = 0; i < line.length(); i++) {
            left.add(String.valueOf(line.charAt(i)));
        }

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            String c = null;

            if(st.hasMoreTokens()) {
                c = st.nextToken();
            }

            moveCursor(command, c);
        }

        StringBuilder sb = new StringBuilder();
        while(!left.isEmpty()) {
            sb.append(left.poll());
        }

        while(!right.isEmpty()) {
            sb.append(right.poll());
        }
        System.out.println(sb.toString());
    }

    public static void moveCursor(String command, String c) {
        int leftSize = left.size();
        int rightSize = right.size();

        if(command.equals("L")) {
            if(leftSize != 0) {
                right.addFirst(left.removeLast());
            }
        } else if(command.equals("D")) {
            if(rightSize != 0) {
                left.add(right.removeFirst());
            }
        } else if(command.equals("B")) {
            if(leftSize != 0) {
                left.removeLast();
            }
        } else {        // P $
            left.add(c);
        }
    }
}