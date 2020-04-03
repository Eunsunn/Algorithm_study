import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	//문제: 백준  N-Queen
	//https://www.acmicpc.net/problem/9663
	
	public static int n;
	public static int count=0;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		//첫번째 행의 하나의 열에만 퀸을 놓을 수 있다
		//첫번째 행의 1~n열에 퀸을 놓는 경우를 모두 탐색한다
		for(int i=1; i<=n; i++) {
			//cols[i]=i번째 행의 퀸의 위치
			int[] cols = new int[n+1];
			
			//1행 i열에 퀸을 놓는다
			cols[1] = i;
			//1행 i열에 퀸을 놓았을 댸 가능한 경우를 dfs로 찾는다
			dfs(cols, 1);
		}
		System.out.println(count);
	}
	
	//row+1행부터 퀸을 놓을 수 있는지 판단한다
	public static void dfs(int[] cols, int row) {
		//기저사례: n행까지 퀸을 놓은 경우
		//count를 증가시킨다
		if(row==n) {
			count++;
			return;
		}
		//row+1행의 1~n열 중 퀸을 놓을 수 있는지 탐색한다
		for(int i=1; i<=n; i++) {
			cols[row+1]=i;
			if(canput(cols ,row+1)) 
				dfs(cols, row+1);
		}
	}
	
	//1~row행까지 같은 열 또는 대각선에 퀸이 있는지 확인한다
	public static boolean canput(int[] cols, int row) {
		//행으로 반복하면서 확인한다
		for(int i=1; i<row; i++) {
			//열 확인
			if(cols[i]==cols[row]) return false;
			//대각선 확인: (행 차이의 절대값)==(열 차이의 절대값)이면 대각선이다
			if(Math.abs(i-row)==Math.abs(cols[i]-cols[row])) return false;
		}
		return true;
	}
}
