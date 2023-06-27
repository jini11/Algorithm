import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				String s = st.nextToken();
				map.put(s, map.getOrDefault(s, 0) + 1);
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				String s = st.nextToken();
				map.put(s, map.getOrDefault(s, 0) + 1);
			}
			
			int ans = 0;
			for (Map.Entry<String, Integer> entry: map.entrySet()) {
				if (entry.getValue() > 1) {
					ans++;
				}
			}
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb.toString());
	}
}
