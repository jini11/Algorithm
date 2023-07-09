import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < a; i++) {
			map.put(Integer.parseInt(st.nextToken()), 1);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < b; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (map.containsKey(n)) {
				map.remove(n);
			}
		}
		
		if (map.size() == 0) {
			System.out.println(0);
		} else {
			List<Integer> list = new ArrayList<Integer>(map.keySet());
			Collections.sort(list);
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i) + " ");
			}
		}
	}
}