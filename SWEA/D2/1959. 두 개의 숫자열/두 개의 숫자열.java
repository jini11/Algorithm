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
            int M = sc.nextInt();
            int[] A = new int[N];
            int[] B = new int[M];
            int sum = 0;
            boolean flag = false; // A가 더 짧으면 false
            
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }
            for (int i = 0; i < M; i++) {
                B[i] = sc.nextInt();
            }
            
            if (N > M) {
                flag = true;
            }
            if (N == M) {
             	for (int i = 0; i < N; i++) {
                 	sum += (A[i] * B[i]);   
                }
            } else {
             	for (int i = 0; i <= Math.abs(N - M); i++) {
                    int tmp = 0;
                    int idx = 0;
                 	for (int j = i; j < i + Math.min(N, M); j++) {
                     	   if (!flag) {
                            	tmp += (A[idx++] * B[j]);   
                           } else {
                               tmp += (A[j] * B[idx++]);
                           }
                    }
                    sum = Math.max(tmp, sum);
                }
            }
            System.out.println("#" + test_case + " " + sum);
            
		}
	}
}