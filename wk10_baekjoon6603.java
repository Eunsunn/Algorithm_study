import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
	//문제: 백준 로또
	public static int k;
	public static int[] num;
	public static final int select = 6;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		String line;
		while((line=br.readLine())!=null) {
			st = new StringTokenizer(line);
			k = Integer.parseInt(st.nextToken());
			num = new int[k];
			for(int i=0; i<k; i++)
				num[i] = Integer.parseInt(st.nextToken());
			makeSet("", -1, 0);
			System.out.println();	
		}
		br.close();
	}
	
	public static void makeSet(String out, int endIdx, int count) {
		if(count>select) return;
        if(count==select) 
        {
            System.out.println(out);
            return;
        }
        for(int i=endIdx+1; i<k; i++)
            makeSet(out+num[i]+" ", i, count+1);   
	}

}
