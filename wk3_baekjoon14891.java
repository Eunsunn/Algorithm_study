import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	//문제 : https://www.acmicpc.net/problem/14891
	public static LinkedList<Integer> g1;
	public static LinkedList<Integer> g2;
	public static LinkedList<Integer> g3;
	public static LinkedList<Integer> g4;
	public static int rotate; //회전 횟수
	public static int[][] rotateInfo; //회전 정보 : 톱니바퀴 숫자, 방향
	public static boolean[] isRotate; //한 회전마다 톱니바퀴들이 회전할지 저장
	public static Scanner scan = new Scanner(System.in);
	
	public static void inputData() {
		g1 = inputGear();
		g2 = inputGear();
		g3 = inputGear();
		g4 = inputGear();
		rotate = scan.nextInt();
		rotateInfo = new int[rotate][2];
		for(int i=0; i<rotate; i++)
			for(int j=0; j<2; j++)
				rotateInfo[i][j] = scan.nextInt();
	}
	//톱니 하나 얻기
	public static LinkedList<Integer> inputGear(){
		LinkedList<Integer> ls = new LinkedList<>();
		String[] line = scan.nextLine().split("");
		for(int i=0; i<8; i++)
			ls.add(Integer.parseInt(line[i]));
		return ls;
	}
	//회전할 톱니 숫자로 톱니 객체 얻기
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
	//톱니 하나 회전시키는 함수
	public static void rotateAGear(int gearNum, int direction) {
		LinkedList<Integer> ls = getGear(gearNum); //해당 톱니 객체를 얻어
		switch(direction) {
		case 1: //시계방향 회전
			int last = ls.get(ls.size()-1);
			ls.remove(ls.size()-1);
			ls.add(0, last);
			break;
		case -1: //반시계방향 회전
			ls.add(ls.get(0));
			ls.remove(0);
			break;
		}	
	}
	//전체 톱니바퀴들의 회전 방향 얻기
	public static int[] getDirection(int gear, int direction) {
		int[] dir = {1, -1, 1, -1};
		int[] reverse = {-1, 1, -1, 1};
		if(dir[gear-1]==direction)
			return dir;
		else
			return reverse;
	}
	//톱니들 회전여부 : 왼쪽, 오른쪽으로 나눠서 재귀적 탐색
	public static void checkRotate(int gear) {
		for(int i=gear; i>1; i--) {
			if(isRotate[i-1]==false)
				break;
			checkLeft(i);
		}
		for(int i=gear; i<4; i++) {
			if(isRotate[i-1]==false)
				break;
			checkRight(i);
		}
	}
	//왼쪽 톱니 회전여부 체크
	public static void checkLeft(int gear) {
		if(gear>1) {
			if(getGear(gear-1).get(2)!=getGear(gear).get(6))
				isRotate[gear-2] = true;
		}
	}
	//오른쪽 톱니 회전여부 체크
	public static void checkRight(int gear) {
		if(gear<4) {
			if(getGear(gear).get(2)!=getGear(gear+1).get(6))
				isRotate[gear] = true;
		}
	}
	//입력받은 수만큼 톱니 회전
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
	//결과 출력
	public static void printResult() {
		int total = 0;
		total += g1.get(0)*1;
		total += g2.get(0)*2;
		total += g3.get(0)*4;
		total += g4.get(0)*8;
		System.out.println(total);
	}
	public static void main(String[] args) {
		inputData();
		rotateGears();
		printResult();
	}

}

