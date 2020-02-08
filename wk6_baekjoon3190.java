import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static int size;
	public static int apple;
	public static int snakeLen = 1;
	public static int time = 0;
	public static int dir = 0;
	public static int rotateNum;
	public static int[][] map;
	public static int[] head = {0, 0}; //row, col
	public static int[] tail = {0, 0};
	public static int[] xdir = {1, 0, -1, 0}; //오른쪽머리, 아래머리, 왼쪽머리, 위 머리
	public static int[] ydir = {0, 1, 0, -1}; 
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	
	public static void input() throws NumberFormatException, IOException {
		size = Integer.parseInt(br.readLine());
		apple = Integer.parseInt(br.readLine());
		map = new int[size][size];
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++)
				map[i][j] = -1;
		map[0][0] = 0;
		for(int i=0; i<apple; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 4;
		}
		rotateNum = Integer.parseInt(br.readLine());
	}
	
	public static void move() {
		head[0] += ydir[dir];
		head[1] += xdir[dir];
		if(map[head[0]][head[1]]!=4){//사과가 없으면 꼬리이동
			int tValue = map[tail[0]][tail[1]];
			map[tail[0]][tail[1]] = -1; //기존 꼬리는 삭제
			tail[0] += ydir[tValue]; //꼬리이동
			tail[1] += xdir[tValue];
		}else { //사과가 있는 경우
			snakeLen++;
		}
		map[head[0]][head[1]]=dir;
	}
	
	public static boolean isGameOver() {
		if((head[0]+ydir[dir])<0 || (head[0]+ydir[dir])>=size || (head[1]+xdir[dir])<0 || (head[1]+xdir[dir])>=size) {//벽
			return true;
		}
		if(map[head[0]+ydir[dir]][head[1]+xdir[dir]]>=0 && map[head[0]+ydir[dir]][head[1]+xdir[dir]]<4) {//몸
			return true;
		}
		return false;
	}
	
	public static void rotate(String direction) {
		if(direction.equals("D"))//오른쪽 회전
			dir = (dir+1)%4;
		else
			dir = (dir+3)%4;
		map[head[0]][head[1]] = dir;
	}
	
	public static void moveSnake() throws IOException {
		input();
		boolean gameOver = false;
		int rotateTime;
		String rotateDir;
		st = new StringTokenizer(br.readLine());
		rotateTime = Integer.parseInt(st.nextToken());
		rotateDir = st.nextToken();
		int count = 1;
		
		while(!gameOver) {
			time++;
			move();
			if(time==rotateTime) {
				rotate(rotateDir);
				//다음 rotate 정보 저장
				if(count<rotateNum) {
					st = new StringTokenizer(br.readLine());
					rotateTime = Integer.parseInt(st.nextToken());
					rotateDir = st.nextToken();
					count++;
				}
			}
			if(isGameOver())
				break;
		}
		bw.write(String.valueOf(++time));
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		moveSnake();
	}

}

