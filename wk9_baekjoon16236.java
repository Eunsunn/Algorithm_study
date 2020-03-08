import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main{
	//문제: https://www.acmicpc.net/problem/16236 (백준 아기 상어)

	public static int N;
	public static int[][] map;
	public static int Y, X;
	public static int size = 2;
	public static int sharks = 0;
	public static int[] ydir = {-1, 0, 0, 1}; //북, 서, 동, 남(문제에서 주어진 순서)
	public static int[] xdir = {0, -1, 1, 0};
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		
		LinkedList<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int time = 0, eatten=0, temp_time=0;
		int added = 1; // 거리가 같은 지점의 개수
		q.add(new int[] {Y, X});
		visited[Y][X] = true;
		
		//bfs
		while(!q.isEmpty() && sharks!=0) {
			int iter = added;
			added = 0;
			temp_time++;
			int minY=N, minX=N;
			for(; iter>0; iter--) { //거리가 같은 지점들 반복
				int[] pop = q.poll();
				for(int dir=0; dir<4; dir++) { //4방향
					int tempY = pop[0] + ydir[dir];
					int tempX = pop[1] + xdir[dir];
					if(tempX<0 || tempX>=N || tempY<0 || tempY>=N) continue; //좌표를 벗어나는 경우
					if(visited[tempY][tempX] || map[tempY][tempX]>size) continue; //이미 지나갔거나, 지나가지 못하는 경우
					//지나가는 경우
					added++;
					visited[tempY][tempX] = true;
					q.add(new int[] {tempY, tempX});
					if(map[tempY][tempX]<size && map[tempY][tempX]!=0)
					{
						if(tempY<minY) {
							minY = tempY;
							minX = tempX;
						}
						else if(tempY==minY && tempX<minX) {
							minY = tempY;
							minX = tempX;
						}
					}
				}
			}
			//먹었으면
			if(minY!=N) {
				time += temp_time; temp_time = 0;
				eatten++; sharks--;
				map[minY][minX] = 0;
				//queue 비워주기
				q.clear();
				q.add(new int[] {minY, minX});
				added = q.size();
				//visited비워주기
				visited = new boolean[N][N];
				visited[minY][minX] = true;
				//상어 크기 증가
				if(eatten==size) {
					size++;
					eatten = 0;
				}
			}
			
		}
		System.out.println(time);
	}
	
	public static void input() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					Y = i;
					X = j;
					map[i][j] = 0;
				}
				else if(map[i][j]!=0)
					sharks++;
			}
		}
	}
	

	
}
