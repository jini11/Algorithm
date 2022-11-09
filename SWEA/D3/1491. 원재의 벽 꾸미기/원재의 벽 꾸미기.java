import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			long n = sc.nextLong();
			long a = sc.nextLong();
			long b = sc.nextLong();
			long min = Integer.MAX_VALUE;
            long sum = 0;
            for (long i=1; i<=n; i++) {
             	for (long j=1; i*j<n+1; j++) {
                 	sum = a * Math.abs(i - j) + b * (n - i * j);
                    if(min > sum) min = sum;
                }
            }
			
			System.out.println("#" + test_case + " " + min);
			
		}
	}
}