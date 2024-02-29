import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in); 
		 String str = sc.next(); 
		 Integer i = Integer.parseInt(sc.next()); 
		 sc.close();
		 
        System.out.println(str.charAt(i-1));

	}
}