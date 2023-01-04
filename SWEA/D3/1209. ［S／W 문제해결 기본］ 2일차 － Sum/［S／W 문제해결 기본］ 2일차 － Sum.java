import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static final int MAX = 100;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int[][] arr = new int[MAX][MAX];
			int max = 0;	
            int tc = sc.nextInt();
            for (int i = 0 ;i < MAX; i++) {
             	for (int j = 0; j < MAX; j++) {
                 	arr[i][j] = sc.nextInt();   
                }
            }
            
            int leftSum = 0;
            int rightSum = 0;
            for (int i = 0; i < MAX; i++) {
                int rowSum = 0;
                int colSum = 0;
             	for (int j = 0; j < MAX; j++) {
                    if (i == j) leftSum += arr[i][j];
                    if (i + j == MAX - 1) rightSum += arr[i][j];
                 	rowSum += arr[i][j];
                    colSum += arr[j][i];
                }
                max = Math.max(max, rowSum);
                max = Math.max(max, colSum);
            }
            max = Math.max(max, leftSum);
            max = Math.max(max, rightSum);
            System.out.println("#" + tc + " " + max);
		}
	}
}