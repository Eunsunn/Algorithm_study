import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	//문제: https://www.acmicpc.net/problem/14501 (백준 퇴사)

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static int n;
	public static int[] time;
	public static int[] price;
	public static int[] accum;
	
	public static void input() throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		time = new int[n+1];
		price = new int[n+1];
		accum = new int[n+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		Arrays.fill(accum, -1);
		bw.write(String.valueOf(maxPrice(1)));
		bw.close();
	}
	
	public static int maxPrice(int day) {
		if(day>n) return 0;
		if(day+time[day]-1>n) return maxPrice(day+1);
		if(accum[day]!=-1) return accum[day];
		int ret = Math.max(maxPrice(day+1), price[day]+maxPrice(day+time[day]));
		return accum[day]=ret;
	}
}

