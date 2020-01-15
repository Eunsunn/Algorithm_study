import java.util.Scanner;

public class Main {

	//문제 : https://www.acmicpc.net/problem/11047
	public static int kinds;
	public static int amount;
	public static int[] arr;
	public static Scanner scan = new Scanner(System.in);
	
	public static void inputData() {
		kinds = scan.nextInt();
		amount = scan.nextInt();
		arr = new int[kinds];
		for(int i=0; i<kinds; i++)
			arr[i] = scan.nextInt();
	}
	
	public static void calculate() {
		int size = arr.length;
		int count = 0;
		for(int i=1; i<=size; i++) {
			int coin = arr[size-i];
			if((amount/coin)>0) {
				count += amount/coin;
				amount = amount%coin;
			}
			if(amount==0)
				break;
		}
		System.out.println(count);
	}
	public static void main(String[] args) {
		inputData();
		calculate();
	}

}

