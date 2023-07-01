import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> map = new HashMap<String, Integer>();
		Set<String> set = new TreeSet<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), ".");
			st.nextToken();
			String type = st.nextToken();
			map.put(type, map.getOrDefault(type, 0) + 1);
			set.add(type);
		}
		List<String> list = new ArrayList<String>(set);
		Collections.sort(list);
		for (String file : list) {
			System.out.println(file + " " + map.get(file));
		}
	}
}