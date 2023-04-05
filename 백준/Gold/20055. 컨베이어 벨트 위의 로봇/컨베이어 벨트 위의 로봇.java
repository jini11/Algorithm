import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N, K;
	static int[] con;
	static int[] robot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		con = new int[2 * N];
		robot = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			con[i] = Integer.parseInt(st.nextToken());
		}

		int res = 0;
		while (checkConvey()) {

			rotate();

			if (con[0] > 0) {
				robot[0] = 1;
				con[0] -= 1;
			}
			res++;
		}
		System.out.println(res);
	}

	private static void rotate() {
		int up = con[con.length - 1];
		for (int i = con.length - 1; i > 0; i--) {
			con[i] = con[i - 1];
		}
		con[0] = up;

		for (int i = N - 1; i > 0; i--) {
			robot[i] = robot[i - 1];
		}
		robot[0] = 0;

		robot[N - 1] = 0;
		for (int i = N - 1; i > 0; i--) {
			if (robot[i - 1] == 1 && robot[i] == 0 && con[i] >= 1) {
				robot[i] = 1;
				robot[i - 1] = 0;
				con[i] -= 1;
			}
		}
	}

	private static boolean checkConvey() {
		int zero = 0;
		for (int i = 0; i < con.length; i++) {
			if (con[i] == 0)
				zero++;
		}
		if (zero >= K) {
			return false;
		}
		return true;
	}
}