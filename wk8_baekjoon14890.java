import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	//문제: https://www.acmicpc.net/problem/14890 (백준 경사로)

	public static int size;
	public static int slope_len;
	public static int[][] map;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static int[] ydir = {1, 0};//행, 열방향
	public static int[] xdir = {0, 1}; 
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		slope_len = Integer.parseInt(st.nextToken());
		map = new int[size][size];
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<size; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int count=0;
		for(int i=0; i<size; i++) {
			count += rowPass(i);
			count += colPass(i);
		}
		bw.write(String.valueOf(count));
		bw.close();
	}
	
	//-방향: col iter
	public static int rowPass(int row) {
		boolean[] slp = new boolean[size];
		for(int col=0; col<size-1; col++) {
			int pres = map[row][col];
			int next = map[row][col+1];
			if(pres==next) continue;
			if(Math.abs(pres-next)>1) return 0;
			if(pres==(next+1)) { //왼쪽이 큰 경우 : 오른쪽 경사
				if(col+slope_len>=size) return 0; //범위 벗어남
				for(int j=1; j<=slope_len; j++) {//col+1부터 경사
					if(next!=map[row][col+j] || slp[col+j]) return 0; //벽 못세우는 경우(높이가 다름, 이미 있음)
					slp[col+j]=true;
				}
			}else { //오른쪽이 큰 경우: 왼쪽경사, col부터 경사로
				if(col+1-slope_len<0) return 0;
				for(int j=0; j<slope_len; j++) {//col부터 경사
					if(pres!=map[row][col-j] || slp[col-j]) return 0;
					slp[col-j]=true;
				}
			}
		}
		return 1;
	}
	//ㅣ방향: row iter
	public static int colPass(int col) {
		boolean[] slp = new boolean[size];
		for(int row=0; row<size-1; row++) {
			int pres = map[row][col];
			int next = map[row+1][col];
			if(pres==next) continue;
			if(Math.abs(pres-next)>1) return 0;
			if(pres==(next+1)) { //위에가 큰경우: 아래 경사
				if(row+slope_len>=size) return 0;
				for(int j=1; j<=slope_len; j++) { //row+1부터 경사
					if(next!=map[row+j][col] || slp[row+j]) return 0;
					slp[row+j]=true;
				}
			}else { //아래가 큰경우: 위 경사
				if(row+1-slope_len<0) return 0;
				for(int j=0; j<slope_len; j++) { //row부터 경사
					if(pres!=map[row-j][col] || slp[row-j]) return 0;
					slp[row-j]=true;
				}
			}
		}
		return 1;
	}
}

