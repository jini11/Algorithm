import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N =  Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		List<Integer> keys = new ArrayList<Integer>(map.keySet());
		
		Collections.sort(keys, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(map.get(o2), map.get(o1));
			}
			
		});
		
		Iterator<Integer> it = keys.iterator();
		while (it.hasNext()) {
			Integer res = it.next();
			for (int i = 0; i < map.get(res); i++) {
				System.out.print(res + " ");
			}
		}
	}
}