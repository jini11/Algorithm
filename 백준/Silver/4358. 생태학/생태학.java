import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Map<String, Integer> map = new HashMap<String, Integer>();
		int total = 0;
		
		while (true) {
			String tree = br.readLine();
			if (tree == null || tree.length() == 0) {
				break;
			}
			
			map.put(tree, map.getOrDefault(tree, 0) + 1);
			total++;
		}
		
		List<String> list = new ArrayList<String>(map.keySet());
		Collections.sort(list);
		
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(" ").append(String.format("%.4f", ((double) (map.get(list.get(i))) * 100) / total )).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}