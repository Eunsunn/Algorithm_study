import java.util.Scanner;

public class Main {
	//문제 : https://www.acmicpc.net/problem/14503
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String first = scan.nextLine();
		int row = Integer.parseInt(first.split(" ")[0]);
		int col = Integer.parseInt(first.split(" ")[1]);
		String second = scan.nextLine();
		int y = Integer.parseInt(second.split(" ")[0]);
		int x = Integer.parseInt(second.split(" ")[1]);
		int direction = Integer.parseInt(second.split(" ")[2]);
		int[][] map = new int[row][col];
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				map[i][j] = scan.nextInt();
			}
		}
		int count = 1;
		while(true) {
			if(direction==0) {//북쪽, 왼쪽이동 = x--
				map[y][x]=2;
				if(map[y-1][x]*map[y+1][x]*map[y][x-1]*map[y][x+1]!=0) {
					if(map[y+1][x]==1) {
						break;
					}
					else {
						y++;
						continue;
					}
				}
				switch(map[y][x-1]) {
				case 0:
					x--;
					count++;
					direction= (direction+3)%4;
					break;
				case 1:
				case 2:
					direction= (direction+3)%4;
					continue;
				}
			}else if(direction==1) {//동쪽, 왼쪽이동 = y-- 
				map[y][x]=2;
				if(map[y-1][x]*map[y+1][x]*map[y][x-1]*map[y][x+1]!=0) {
					if(map[y][x-1]==1){
						break;
					}
					else {
						x--;
						continue;
					}
				}
				switch(map[y-1][x]) {
				case 0:
					y--;
					count++;
					direction= (direction+3)%4;
					break;
				case 1:
				case 2:
					direction= (direction+3)%4;
					continue;
				}
			}else if(direction==2) {//남쪽, 왼쪽이동 = x++
				map[y][x]=2;
				if(map[y-1][x]*map[y+1][x]*map[y][x-1]*map[y][x+1]!=0) {
					if(map[y-1][x]==1){
						break;
					}
					else {
						y--;
						continue;
					}
				}
				switch(map[y][x+1]) {
				case 0:
					x++;
					count++;
					direction= (direction+3)%4;
					break;
				case 1:
				case 2:
					direction= (direction+3)%4;
					continue;
				}
			}else if(direction==3) {//서쪽, 왼쪽이동 = y++
				map[y][x]=2;
				if(map[y-1][x]*map[y+1][x]*map[y][x-1]*map[y][x+1]!=0) {
					if(map[y][x+1]==1){
						break;
					}
					else {
						x++;
						continue;
					}
				}
				switch(map[y+1][x]) {
				case 0:
					y++;
					count++;
					direction= (direction+3)%4;
					break;
				case 1:
				case 2:
					direction= (direction+3)%4;
					continue;
				}
			}
		}
		System.out.println(count);
	}

}

