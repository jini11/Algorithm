import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Solution {
	static final int STAIR_SIZE = 2;
	static int PEOPLE_SIZE;
	static int N;
	static int[][] map, dist;
	static List<Person> people;
	static Stair[] stairs;
	static boolean[] select;
	static int time;

	static class Person {
		int idx, row, col;
		boolean isDown;

		public Person(int idx, int row, int col, boolean isDown) {
			this.idx = idx;
			this.row = row;
			this.col = col;
			this.isDown = isDown;
		}
	}

	static class Stair {
		int row, col, height;
		boolean isFull;

		public Stair(int row, int col, int height, boolean isFull) {
			this.row = row;
			this.col = col;
			this.height = height;
			this.isFull = isFull;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			stairs = new Stair[2];
			people = new ArrayList<>();
			time = Integer.MAX_VALUE;
			int peopleCnt = 0;
			int stairCnt = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						people.add(new Person(peopleCnt++, i, j, false));
					} else if (map[i][j] > 1) {
						stairs[stairCnt++] = new Stair(i, j, map[i][j], false);
					}
				}
			}

			PEOPLE_SIZE = peopleCnt;
			calcDistance();

			select = new boolean[PEOPLE_SIZE];
			subset(0);

			sb.append("#").append(tc).append(" ").append(time).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void calcDistance() {
		dist = new int[PEOPLE_SIZE][STAIR_SIZE];
		for (int i = 0; i < PEOPLE_SIZE; i++) {
			dist[i][0] = getDistance(people.get(i), stairs[0]);
			dist[i][1] = getDistance(people.get(i), stairs[1]);
		}
	}

	private static int getDistance(Person person, Stair stair) {
		return Math.abs(person.row - stair.row) + Math.abs(person.col - stair.col);
	}

	private static void subset(int cnt) {
		if (cnt == PEOPLE_SIZE) {
			List<Integer> people1 = new ArrayList<>();
			List<Integer> people2 = new ArrayList<>();
			int[][] copyDist = copyDistance();

			for (int i = 0; i < select.length; i++) {
				if (select[i]) {
					people1.add(i);
				} else {
					people2.add(i);
				}
			}

			int finishTime = Math.max(simul(copyDist, people1, 0), simul(copyDist, people2, 1));
			time = Math.min(time, finishTime);
			return;
		}
		select[cnt] = true;
		subset(cnt + 1);

		select[cnt] = false;
		subset(cnt + 1);
	}

	private static int[][] copyDistance() {
		int[][] copy = new int[PEOPLE_SIZE][STAIR_SIZE];
		for (int i = 0; i < PEOPLE_SIZE; i++) {
			copy[i] = dist[i].clone();
		}
		return copy;
	}

	private static int simul(int[][] dist, List<Integer> some, int stairNum) {
		List<Integer> waitList = new ArrayList<>();
		resetPeople(some);
		resetStairs(stairNum);

		int cnt = 1;
		int stairMax = 0;
		while (true) {
			waitList.clear();
			if (cnt >= time)
				break;
			for (Integer idx : some) {
				Person person = people.get(idx);
				if (person.isDown)
					continue;
				if (dist[idx][stairNum] == 0) { // 계단에 도착했으면
					waitList.add(idx);
				} else {
					dist[idx][stairNum]--; // 계단 내려가기
					if (dist[idx][stairNum] < (-1) * stairs[stairNum].height) { // 계단을 끝까지 내려감
						stairMax--;
						person.isDown = true;
					}
				}
			}
			if (stairMax < 3) {
				stairs[stairNum].isFull = false;
			}
			for (Integer idx : waitList) { // 계단 대기 사람들 관리
				if (!stairs[stairNum].isFull) {
					dist[idx][stairNum]--;
					stairMax++;
				}
				if (stairMax == 3) {
					stairs[stairNum].isFull = true;
				}
			}

			if (isOut(some)) {
				break;
			}
			cnt++;
		}
		return cnt;
	}

	private static boolean isOut(List<Integer> some) {
		for (Integer idx : some) {
			if (!people.get(idx).isDown) {
				return false;
			}
		}
		return true;
	}

	private static void resetPeople(List<Integer> some) {
		for (Integer idx : some) {
			people.get(idx).isDown = false;
		}
	}

	private static void resetStairs(int stairNum) {
		stairs[stairNum].isFull = false;
	}
}