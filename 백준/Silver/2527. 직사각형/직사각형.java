import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int x3 = Integer.parseInt(st.nextToken());
			int x4 = Integer.parseInt(st.nextToken());
			int p3 = Integer.parseInt(st.nextToken());
			int p4 = Integer.parseInt(st.nextToken());
			
			if (p2 < x4 || x1 > p3 || x2 > p4 || p1 < x3) {
				System.out.println("d");
			} else if ((x1 == p3 && p2 == x4) || (x1 == p3 && x2 == p4) || (x3 == p1 && p4 == x2) || (p1 == x3 && p2 == x4)) {
				System.out.println("c");
			} else if (p1 == x3 || x1 == p3 || x4 == p2 || x2 == p4) {
				System.out.println("b");
			} else {
				System.out.println("a");
			}
		}
	}
}