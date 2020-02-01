import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class tournament {
	//문제 : https://www.acmicpc.net/problem/1057
	public static int participants;
	public static Integer[] p = new Integer[2];
	public static int count = 0;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	
	public static void inputData() throws IOException {
		st = new StringTokenizer(br.readLine());
		participants = Integer.parseInt(st.nextToken());
		for(int i=0; i<p.length; i++)
			p[i] = Integer.parseInt(st.nextToken());
	}
	//라운드에서 참가번호를 계산
	public static void roundNumber(Integer[] arr) {
		for(int i=0; i<arr.length; i++) {
			if(p[i]%2 == 0) {
				p[i] /= 2;
			}else {
				p[i] = (p[i]+1)/2;
			}
		}
	}
	
	public static void battle() throws IOException {
		while (true) {
			roundNumber(p);
			count++;
			if(p[0]==p[1]) //갱신한 번호가 같으면 대결
				break;
		}
		if(count==0) { //만나지 않는 경우
			bw.write(String.valueOf(-1));
		}else { //만나는 경우
			bw.write(String.valueOf(count));
		}
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		inputData();
		battle();
	}
	
}

