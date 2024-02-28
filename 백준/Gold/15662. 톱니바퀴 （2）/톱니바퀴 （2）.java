import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int T;
	static List<Integer>[] gears;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		gears = new LinkedList[T];
		for (int i = 0; i < T; i++) {
			gears[i] = new LinkedList<Integer>();
		}
		
		for (int i = 0; i < T; i++) {
			String input = br.readLine();
			for (int j = 0; j < input.length(); j++) {
				gears[i].add(input.charAt(j) - '0');
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			
			move(num, dir);
		}
		
		System.out.println(cntGear());
	}
	
	private static void move(int num, int dir) {
		moveLeft(num - 1, -dir);
		moveRight(num + 1, -dir);
		moveSelf(num, dir);
	}
	
	private static void moveLeft(int num, int dir) {
		if (num < 0) {
			return;
		}
		
		if (gears[num].get(2) != gears[num + 1].get(6)) {
			moveLeft(num - 1, -dir);
			moveSelf(num, dir);
		}
	}
	
	private static void moveRight(int num, int dir) {
		if (num >= T) {
			return;
		}
		
		if (gears[num].get(6) != gears[num - 1].get(2)) {
			moveRight(num + 1, -dir);
			moveSelf(num, dir);
		}
	}
	
	private static void moveSelf(int num, int dir) {
		if (dir == 1) {
			gears[num].add(0, gears[num].remove(7));
		} else {
			gears[num].add(gears[num].remove(0));
		}
	}
	
	private static int cntGear() {
		int cnt = 0;
		for (int i = 0; i < T; i++) {
			if (gears[i].get(0) == 1) {
				cnt++;
			}
		}
		return cnt;
	}
}