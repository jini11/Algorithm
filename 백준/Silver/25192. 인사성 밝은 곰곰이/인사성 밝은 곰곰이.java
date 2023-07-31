import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			if (input.equals("ENTER")) {
				map = new HashMap<>();
				continue;
			}
			if (!map.containsKey(input)) {
				map.put(input, 1);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}