import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	public static class Problem implements Comparable<Problem> {
		int number;
		int level;
		public Problem(int number, int level) {
			this.number = number;
			this.level = level;
		}
		
		@Override
		public int compareTo(Problem o) {
			if (this.level == o.level) {
				return this.number - o.number;
			}
			return this.level - o.level;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		TreeSet<Problem> set = new TreeSet<>();
		Map<Integer, Integer> map = new HashMap<>();
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			set.add(new Problem(P, L));
			map.put(P, L);
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String command = st.nextToken();
			if (command.equals("add")) {
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				set.add(new Problem(P, L));
				map.put(P, L);
			} else if (command.equals("recommend")) {
				int flag = Integer.parseInt(st.nextToken());
				if (flag == 1) {
					sb.append(set.last().number).append("\n");
				} else if (flag == -1) {
					sb.append(set.first().number).append("\n");					
				}
			} else if (command.equals("solved")) {
				int number = Integer.parseInt(st.nextToken());
				set.remove(new Problem(number, map.get(number)));
				map.remove(number);
			}
		}
		System.out.println(sb.toString());
	}
}