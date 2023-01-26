import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		int n = Integer.parseInt(line.split(" ")[0]);
		int k = Integer.parseInt(line.split(" ")[1]);
		
		PriorityQueue<int[]> queue = new PriorityQueue<>((e1, e2) -> {
			return e2[1] - e1[1];
		});
		
		List<int[]> jewels = new ArrayList<>();
		List<Integer> bags = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			line = br.readLine();
			jewels.add(new int[] {Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1])} );
		}
		
		for (int i = 0; i < k; i++) {
			bags.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(jewels, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		Collections.sort(bags);
		
		int idx = 0;
		long sum = 0;
		
		for (int i = 0; i < k; i++) {
			int curBag = bags.get(i);
			
			while (idx < n) {
				if (jewels.get(idx)[0] <= curBag) {
					queue.add(jewels.get(idx).clone());
					idx++;
				} else {
					break;
				}
			}
			if (!queue.isEmpty()) {
				sum += queue.poll()[1];
			}
		}
		
		System.out.println(sum);
	}

}