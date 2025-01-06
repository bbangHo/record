import java.util.*;
import java.io.*;

public class Main {
    static class Student {
        String name;
        int koreaScore;
        int englishScore;
        int mathScore;

        public Student(String n, int k, int e, int m) {
            name = n;
            koreaScore = k;
            englishScore = e;
            mathScore = m;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Student> students = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int ks =  Integer.parseInt(st.nextToken());
            int es =  Integer.parseInt(st.nextToken());
            int ms =  Integer.parseInt(st.nextToken());

            students.add(new Student(name, ks, es, ms));
        }

        students.stream()
                .sorted((s1, s2) -> {
                    if(s1.koreaScore == s2.koreaScore) {
                        if(s1.englishScore == s2.englishScore) {
                            if(s1.mathScore == s2.mathScore) {
                                return s1.name.compareTo(s2.name);
                            }

                            return s2.mathScore - s1.mathScore;
                        }

                        return s1.englishScore - s2.englishScore;
                    }

                    return s2.koreaScore - s1.koreaScore;
                })
                .forEach(s -> System.out.println(s.name));
    }
}