import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int n;
	public static int[] sanggeun;
	public static int m;
	public static int[] numbercard;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	
	public static void inputData() throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		sanggeun = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			sanggeun[i] = Integer.parseInt(st.nextToken());
		
		m = Integer.parseInt(br.readLine());
		numbercard = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++)
			numbercard[i] = Integer.parseInt(st.nextToken());
	}
	
	public static void binarySearch(int target) throws IOException {
		int middle;
		int min=0;
		int max=n-1;
		boolean find = false;
		while(max-min>1) {
			if(sanggeun[min]==target || sanggeun[max]==target) {
				find = true;
				break;
			}
			middle = min + (max-min)/2;
			if(sanggeun[middle]==target) {
				find = true;
				break;
			}
			else if(sanggeun[middle]>target) {
				max = middle;
			}
			else if(sanggeun[middle]<target) {
				min = middle;
			}
		}
		bw.write((find==true? 1: 0) + " ");
	}
	
	public static void searchAll() throws IOException {
		Arrays.sort(sanggeun);
		for(int i=0; i<m; i++)
			binarySearch(numbercard[i]);
		bw.close();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		inputData();
		searchAll();
	}

}

