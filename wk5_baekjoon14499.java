import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	//문제 : https://www.acmicpc.net/problem/14499
	public static int height;
	public static int width;
	public static int[][] map;
	public static int x;
	public static int y;
	public static int rollNumber;
	public static int[] directions;
	public static int[] dice = {0, 0, 0, 0, 0, 0};//바닥, 북, 동, 서, 남, top
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	
	public static void inputData() throws IOException {
		st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		rollNumber = Integer.parseInt(st.nextToken());
		//지도 생성
		map = new int[height][width];
		for(int i=0; i<height; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<width; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		//회전 순서
		directions = new int[rollNumber];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<rollNumber; i++)
			directions[i] = Integer.parseInt(st.nextToken());
	}
	//굴릴 수 있는지 체크
	public static boolean checkRoll(int dir) {
		switch(dir) {
		case 1:
			if(y+1 >= width)
				return false;
			else
				return true;
		case 2:
			if(y-1 < 0)
				return false;
			else
				return true;
		case 3:
			if(x-1 < 0)
				return false;
			else
				return true;
		case 4:
			if(x+1 >= height)
				return false;
			else
				return true;
		}
		return false;
	}
	
	public static void changeDice(int dir) {
		int[] temp = dice.clone();
		switch(dir) {
		case 1://동
			dice[0] = temp[2];
			dice[2] = temp[5];
			dice[3] = temp[0];
			dice[5] = temp[3];
			break;
		case 2://서
			dice[0] = temp[3];
			dice[2] = temp[0];
			dice[3] = temp[5];
			dice[5] = temp[2];
			break;
		case 3://북
			dice[0] = temp[1];
			dice[1] = temp[5];
			dice[4] = temp[0];
			dice[5] = temp[4];
			break;
		case 4://남
			dice[0] = temp[4];
			dice[1] = temp[0];
			dice[4] = temp[5];
			dice[5] = temp[1];
			break;
		}
	}
	
	public static void rollaDice(int dir) throws IOException {
		if(!checkRoll(dir))
			return;
		//전개도 갱신
		changeDice(dir);
		//좌표 갱신
		switch(dir) {
		case 1:
			y++;
			break;
		case 2:
			y--;
			break;
		case 3:
			x--;
			break;
		case 4:
			x++;
			break;
		}
		//바닥&주사위 값
		if(map[x][y]==0) 
			map[x][y] = dice[0];
		else{
			dice[0] = map[x][y];
			map[x][y] = 0;
		}
		bw.write(String.valueOf(dice[5]) + "\n");
	}
	
	public static void rollAll() throws IOException {
		for(int i=0; i<rollNumber; i++) {
			rollaDice(directions[i]);
		}
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		inputData();
		rollAll();
	}
}

