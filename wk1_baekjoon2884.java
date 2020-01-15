import java.util.Scanner;

public class Main {

	//문제 : https://www.acmicpc.net/problem/2884
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String inp = scan.nextLine();
		String[] splt = inp.split(" ");
		int hour = Integer.parseInt(splt[0]);
		int minute = Integer.parseInt(splt[1]);
		
		if(minute<45) {
			if(hour==0)
				hour = 23;
			else
				hour--;
			minute+=15;
		}else {
			minute-=45;
		}
		String out = hour + " " + minute;
		System.out.println(out);
	}

}

