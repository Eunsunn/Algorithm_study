import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int kinds;
	public static int amount;
	public static int[] arr;
	public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	
	public static void inputData() throws IOException {
		st = new StringTokenizer(bf.readLine());
		kinds = Integer.parseInt(st.nextToken());
		amount = Integer.parseInt(st.nextToken());
		arr = new int[kinds];
		for(int i=0; i<kinds; i++)
			arr[i] = Integer.parseInt(bf.readLine());
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
	public static void main(String[] args) throws IOException {
		inputData();
		calculate();
	}
}

