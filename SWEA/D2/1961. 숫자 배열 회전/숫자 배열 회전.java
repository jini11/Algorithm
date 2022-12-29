import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
            int[][] map = new int[N][N];
            int[][] map1 = new int[N][N];
            int[][] map2 = new int[N][N];
            int[][] map3 =  new int[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            
            System.out.println("#" + test_case);
            
            map1 = turnNinety(N, map);
            map2 = turnNinety(N, map1);
            map3 = turnNinety(N, map2);
                            
            printMap(N, map1, map2, map3);
		}
	}
    public static int[][] turnNinety(int N, int[][] map) {
    	int[][] arr = new int[N][N];
        int x = 0;
        int y = 0;
        for (int i = 0; i < N; i++) {
            y = 0;
         	for (int j = N - 1; j >= 0; j--) {
             	arr[x][y++] = map[j][i];
            }
             x += 1;
        }
        return arr;
    }
    
    public static void printMap(int N, int[][] map1, int[][] map2, int[][] map3) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < N; i++) {
         	   for (int j = 0; j < N * 3; j++) {
                	if (j >= 0 && j <= N-1) {
                        sb.append(map1[i][j]);
                    } else if (j >= N && j <= N+N-1) {
                        sb.append(map2[i][j-N]);
                    } else if (j >= N+N && j <= N*3-1) {
                     	sb.append(map3[i][j-(2*N)]);   
                    }
                                      
                   if (j == N-1 || j == 2*N-1)
                       sb.append(" ");
               }
               sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}