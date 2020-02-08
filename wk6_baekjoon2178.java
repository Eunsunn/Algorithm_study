import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
		public static int height, width;
			public static int[][] map;
				public static Queue<int[]> q = new LinkedList<int[]>();
					public static boolean isStart;
						public static int[] xdir = {1, -1, 0, 0}; //동 서 남 북
							public static int[] ydir = {0, 0, +1, -1};
								
								public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
									public static StringTokenizer st;
										public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
											
											
											public static void input() throws IOException {
														st = new StringTokenizer(br.readLine());
																height = parseInt(st.nextToken());
																		width = parseInt(st.nextToken());
																				map = new int[height][width];
																						for(int i=0; i<height; i++) {
																										String ln = br.readLine();
																													for(int j=0; j<width; j++) {
																																		map[i][j] = ln.charAt(j)-'0';
																																					}
																															}
																							}
												public static int parseInt(String str) {
															return Integer.parseInt(str);
																}
													
													
													public static void BFS() throws IOException {
																if(!isStart) {
																				q.add(new int[] {0,0});
																							isStart=true;
																									}
																		while(q.size()>0) {
																						int[] prev = q.poll();
																									for(int i=0; i<4; i++) {
																														if((prev[0]+ydir[i])<0 || (prev[0]+ydir[i])>=height || (prev[1]+xdir[i])<0 ||(prev[1]+xdir[i])>=width)
																																				continue;
																																		int[] next = getNextCoor(i, prev);
																																						if(next[0]==0 && next[1]==0)
																																												continue;
																																										if(isWall(next))
																																																continue;
																																														if(getValue(next)==1) {
																																																				map[next[0]][next[1]] = getValue(prev)+1;
																																																									q.offer(next);
																																																													}
																																																		else {
																																																								map[next[0]][next[1]] = getValue(prev)+1 < getValue(next) ? getValue(prev)+1 : getValue(next); 
																																																												}
																																																					}
																											}
																				bw.write(String.valueOf(map[height-1][width-1]));
																						bw.close();
																							}
														
														public static boolean isWall(int[] rowCol) {
																	if(getValue(rowCol)==0)
																					return true;
																			else
																							return false;
																				}
															
															public static int getValue(int[] rowCol) {
																		return map[rowCol[0]][rowCol[1]];
																			}
																
																public static int[] getNextCoor(int dir, int[] prev) {
																			return new int[] {prev[0]+ydir[dir], prev[1]+xdir[dir]};
																				}

																	
																	public static void main(String[] args) throws IOException {
																				input();
																						BFS();
																							}
}

