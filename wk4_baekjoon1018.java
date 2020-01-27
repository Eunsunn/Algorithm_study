import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int[][] arr;
	public static int width;
	public static int height;
	public static int size = 8;
	public static List<int[]> rowcols = new ArrayList<>();
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static void inputData() throws IOException {
		st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		arr = new int[height][width];
		for(int i=0; i<height; i++) {
			String line = br.readLine();
			for(int j=0; j<width; j++) {
				arr[i][j] = (line.charAt(j)=='W'? 0 : 1);
			}
		}
	}
	public static void findBox(){
		for(int i=0; i<height; i++) {
			if((i+size)>height)
				break;
			for(int j=0; j<width; j++) {
				if((j+size)>width)
					break;
				int[] rowcol = {i, j};
				rowcols.add(rowcol);
			}
		}
	}
	public static int repaint(int[] point) {
		int white_first = 0;
		int black_first = 0;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				switch((i+j)%2) {
				case 0:
					if(arr[point[0]+i][point[1]+j]==0)
						white_first++;
					else
						black_first++;
					break;
				case 1:
					if(arr[point[0]+i][point[1]+j]==1)
						white_first++;
					else
						black_first++;
					break;
				}
			}
		}
		return (white_first<black_first?white_first:black_first);
	}
	public static void searchMin() {
		findBox();
		int min = repaint(rowcols.get(0));
		if(rowcols.size()==1) {
			System.out.println(min);
			return;
		}
		for(int i=0; i<rowcols.size(); i++) {
			if(min>repaint(rowcols.get(i)))
				min = repaint(rowcols.get(i));
		}
		System.out.println(min);
	}
	public static void main(String[] args) throws IOException {
		inputData();
		searchMin();
	}

}

