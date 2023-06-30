import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		int N = Integer.parseInt(input.split(" ")[0]);
		int M = Integer.parseInt(input.split(" ")[1]);
		
		Map<String, String> map = new HashMap<String, String>();
		
		for (int i = 0; i < N; i++) {
			String team = br.readLine();
			int num = Integer.parseInt(br.readLine());
			String[] member = new String[num];
			for (int j = 0; j < num; j++) {
				member[j] = br.readLine();
			}
			Arrays.sort(member);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < num; j++) {
				sb.append(member[j] + " ");
			}
			map.put(team, sb.toString());
		}
		
		for (int i = 0; i < M; i++) {
			String problem = br.readLine();
			int type = Integer.parseInt(br.readLine());
			if (type == 0) {
				String team = map.get(problem);
				StringTokenizer st = new StringTokenizer(team, " ");
				while (st.hasMoreTokens()) {
					System.out.println(st.nextToken());
				}
			} else {
				for (String teamName: map.keySet()) {
					String team = map.get(teamName);
					if (team.contains(problem)) {
						System.out.println(teamName);
					}
				}
			}
		}
		
	}
}
