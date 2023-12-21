import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int up = 0, down = 0;
            for (int i = 0 ; i < arr.length; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i+1] > arr[i]) {
                    down = Math.max(down, arr[i+1] - arr[i]);
                } else {
                    up = Math.max(up, arr[i] - arr[i+1]);
                }
            }

            sb.append("#").append(t).append(" ").append(down).append(" ").append(up).append("\n");
        }

        System.out.println(sb.toString());
	}
}