import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	//문제 : https://www.acmicpc.net/problem/1094 (백준 막대기)

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int x = Integer.parseInt(br.readLine()); //만들고 싶은 수
		int max = 64;
		int sum=0;
		int count = 0;
		
		while(max>x) max/=2;
		while(sum<x) {
			if((max+sum)<=x) {
				sum+=max;
				count++;
			}
			max/=2;
		}
		
		bw.write(String.valueOf(count));
		bw.close();
	}
	
}

