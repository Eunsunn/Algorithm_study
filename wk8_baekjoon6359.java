import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
	//문제: https://www.acmicpc.net/problem/6359 (백준 6359, 만취한 상범)

	static int cases;
	static int n;
	static boolean[] cache;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		cases = Integer.parseInt(br.readLine());
		for(int c=0; c<cases; c++) {
			n = Integer.parseInt(br.readLine());
			cache = new boolean[n+1];
			for(int i=1; i<n+1; i++) {
				for(int j=1; j*i<n+1; j++) {
					if(cache[i*j])
						cache[i*j] = false;
					else
						cache[i*j] = true;
				}
			}
		int count = 0;
		for(int i=1; i<n+1; i++)
			if(cache[i]) count++;
		bw.write(String.valueOf(count) + "\n");
		bw.flush();
		}
		br.close();
		bw.close();
	}
}
