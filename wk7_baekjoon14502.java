import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	//문제 : https://www.acmicpc.net/problem/14502 (백준 연구소)
	public static int row, col;
	public static int[][] map;
	public static int max = 0;
	public static Vector<int[]> virus = new Vector<>();
	public static int[] xdir = {1, -1, 0, 0}; //동서남북
	public static int[] ydir = {0, 0, 1, -1};
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	
	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[row][col];
		for(int i=0; i<row; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<col; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp==2) virus.add(new int[] {i,j});
			}
		}
	}
	
	//재귀호출을 이용해 벽 3개 세우기(map=0에)
	public static void setWalls(int count) {
		if(count==3) { //base case
			int ret = spreadVirus();
			if(ret>max)	max = ret;
			return;
		}
		for(int i=0; i<row; i++)
			for(int j=0; j<col; j++)
				if(map[i][j]==0) 
				{
					map[i][j] = 1;
					setWalls(count+1);
					map[i][j] = 0;
				}

	}
	
	public static int spreadVirus() {
		int[][] copyMap = new int[row][col];
		for(int i=0; i<row; i++) 
			for(int j=0; j<col; j++)
				copyMap[i][j] = map[i][j];
		
		Queue<int[]> q = new LinkedList<>();
		for(int i=0; i<virus.size(); i++)
			q.add(virus.get(i));
		//bfs
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			for(int dir=0; dir<4; dir++) {
				int r = temp[0]+ydir[dir]; int c = temp[1]+xdir[dir];
				if(r<0 || r>=row || c<0 || c>=col)
					continue;
				if(copyMap[r][c]==0) {
					copyMap[r][c]=2;
					q.offer(new int[] {r, c});
				}
			}
		}
		//안전지역 개수
		int safeArea = 0;
		for(int i=0; i<row; i++)
			for(int j=0; j<col; j++)
				if(copyMap[i][j]==0)
					safeArea++;
		return safeArea;
	}
	
	
	public static void main(String[] args) throws IOException {
		input();
		setWalls(0);
		bw.write(String.valueOf(max));
		bw.close();
	}

}

