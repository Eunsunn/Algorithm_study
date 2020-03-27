import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static int MAX = -(int) Math.pow(10, 10);
	public static int MIN = (int) Math.pow(10,10);
	public static int n;
	public static int number[];
	public static int operators[]; //+, -, x, /
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		n = Integer.parseInt(br.readLine());
		number = new int[n];
		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			number[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++)
			operators[i] = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> op = new LinkedList<>();
		int[] selected = new int[4];
		operation(op, selected);
		System.out.println(MAX);
		System.out.println(MIN);
	}
	
	//op: 연산자, selected: 각 연산자가 선택된 개수
	public static void operation(LinkedList<Integer> op, int[] selected) {
		//연산자 순열을 완성한 경우
		if(op.size()==n-1)
		{
			int prev = number[0];
			for(int i=1; i<number.length; i++) {
				int o = op.get(i-1);
				switch(o) {
				case 0: //+
					prev += number[i];
					break;
				case 1: //-
					prev -= number[i];
					break;
				case 2: //x
					prev *= number[i];
					break;
				case 3: // /
					if(prev<0) {
						prev = (-prev)/number[i];
						prev = -prev;
					}
					else
						prev /= number[i];
					break;
				}
			}
			MAX = (prev>MAX? prev: MAX);
			MIN = (prev<MIN? prev: MIN);
			return;
		}
		
		//완성하지 못한 경우
		for(int i=0; i<4; i++) {
			if(selected[i]==operators[i])
				continue;
			else {
				int idx = op.size();
				op.add(i);
				selected[i] +=1;
				operation(op, selected);
				op.remove(idx);
				selected[i] -= 1;
			}
				
		}
		
	}

	
}
