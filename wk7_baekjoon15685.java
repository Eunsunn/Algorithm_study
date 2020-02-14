import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//문제: https://www.acmicpc.net/problem/15685 (백준 드래곤 커브 15685)
	public static int[] xdir = {1, 0, -1, 0};
	public static int[] ydir = {0, -1, 0, 1};
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n;
		int[] info = new int[4]; //x, y, dir, gen
		boolean[][] map = new boolean[101][101];
		n = Integer.parseInt(br.readLine());
		
		for(int c = 0; c<n; c++) {
			inputACurve(info);
			ArrayList<Integer> dirList = new ArrayList<>();
			rotate(dirList, info, map);
		}
		
		int ret = 0;
		for(int i=0; i<map.length-1; i++) {
			for(int j=0; j<map[0].length-1; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1])
					ret++;
			}
		}
		
		bw.write(String.valueOf(ret));
		bw.close();
	}
	
	public static void inputACurve(int[] info) throws IOException {
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<info.length; i++)
			info[i] = Integer.parseInt(st.nextToken());
	}
	
	public static void rotate(ArrayList<Integer> l, int[] info, boolean[][] map) {
		int x=info[0], y=info[1], dir=info[2], gen=info[3];
		l.add(dir); map[y][x] = true;
		x+=xdir[dir]; y+=ydir[dir]; map[y][x] = true; 
		int len = l.size();
		for(int g=1; g<=gen; g++) { //세대
			for(int idx=(len-1); idx>-1; idx--) { //리스트 길이
				int temp = (l.get(idx)+1)%4;
				if ((x+xdir[temp])>100 || (x+xdir[temp])<0 || (y+ydir[temp])>100 || (y+ydir[temp]<0))
					continue;
				l.add(temp);
				x += xdir[temp]; y += ydir[temp];
				map[y][x]=true;
			}
			len = l.size();
		}
	}
}

