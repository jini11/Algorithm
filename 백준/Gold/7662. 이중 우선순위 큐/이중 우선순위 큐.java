import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		TreeMap<Integer, Integer> map;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			map = new TreeMap<>();

			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				char command = st.nextToken().charAt(0);
				int number = Integer.parseInt(st.nextToken());
				if (command == 'I') {
					map.put(number, map.getOrDefault(number, 0) + 1);
				} else if (command == 'D') {
					if (!map.isEmpty()) {
						int key = 0;
						if (number == 1) {
							key = map.lastKey();
						} else if (number == -1) {
							key = map.firstKey();
						}
						int value = map.get(key);
						if (value == 1) {
							map.remove(key);
						} else {
							map.put(key, value - 1);
						}
					}
				}
			}
			if (map.size() == 0) {
				sb.append("EMPTY").append("\n");
			} else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}