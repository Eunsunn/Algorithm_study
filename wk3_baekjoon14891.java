import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	//문제 : https://www.acmicpc.net/problem/14891
	public static LinkedList<Integer> g1;
	public static LinkedList<Integer> g2;
	public static LinkedList<Integer> g3;
	public static LinkedList<Integer> g4;
	public static int rotate; 
	public static int[][] rotateInfo;
	public static boolean[] isRotate; 
	public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	
	public static void inputData() throws NumberFormatException, IOException {
		g1 = inputGear();
		g2 = inputGear();
		g3 = inputGear();
		g4 = inputGear();
		rotate = Integer.parseInt(bf.readLine());
		rotateInfo = new int[rotate][2];
		for(int i=0; i<rotate; i++)
		{
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<2; j++)
				rotateInfo[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	public static LinkedList<Integer> inputGear() throws IOException{
		LinkedList<Integer> ls = new LinkedList<>();
		String line = bf.readLine();
		for(int i=0; i<8; i++)
			ls.add(line.charAt(i) - '0');
		return ls;
	}
	public static LinkedList<Integer> getGear(int gearNum){
		LinkedList<Integer> ls;
		switch(gearNum) {
		case 1:
			 ls = g1;
			break;
		case 2:
			ls = g2;
			break;
		case 3:
			ls = g3;
			break;
		case 4:
			ls = g4;
			break;
		default:
			ls = null;
		}
		return ls;
	}
	public static void rotateAGear(int gearNum, int direction) {
		LinkedList<Integer> ls = getGear(gearNum);
		switch(direction) {
		case 1:
			int last = ls.get(ls.size()-1);
			ls.remove(ls.size()-1);
			ls.add(0, last);
			break;
		case -1: 
			ls.add(ls.get(0));
			ls.remove(0);
			break;
		}	
	}
	public static int[] getDirection(int gear, int direction) {
		int[] dir = {1, -1, 1, -1};
		int[] reverse = {-1, 1, -1, 1};
		if(dir[gear-1]==direction)
			return dir;
		else
			return reverse;
	}
	public static void checkRotate(int gear) {
		checkLeft(gear);
		checkRight(gear);
	}
	public static void checkLeft(int gear) {
		if (gear<2)
			return;
		if(getGear(gear-1).get(2)!=getGear(gear).get(6)) {
			isRotate[gear-2] = true;
			checkLeft(gear-1);
		}
	}
	public static void checkRight(int gear) {
		if(gear>3)
			return;
		if(getGear(gear).get(2)!=getGear(gear+1).get(6)) {
			isRotate[gear] = true;
			checkRight(gear+1);
		}
	}
	public static void rotateGears() {
		for(int i=0; i<rotateInfo.length; i++) {
			int gear = rotateInfo[i][0];
			int direction = rotateInfo[i][1];
			int[] dir = getDirection(gear, direction);
			isRotate = new boolean[4];
			isRotate[gear-1] = true;
			checkRotate(gear);
			if(isRotate[0])
				rotateAGear(1, dir[0]);
			if(isRotate[1])
				rotateAGear(2, dir[1]);
			if(isRotate[2])
				rotateAGear(3, dir[2]);
			if(isRotate[3])
				rotateAGear(4, dir[3]);		
		}
	}
	public static void printResult() {
		int total = 0;
		for(int i=0; i<4; i++) {
			total += getGear(i+1).get(0)*(Math.pow(2, i));
		}
		System.out.println(total);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		inputData();
		rotateGears();
		printResult();
	}

}
