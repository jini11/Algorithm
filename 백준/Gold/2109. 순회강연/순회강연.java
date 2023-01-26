import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<int[]> lectures = new ArrayList<int[]>();
		int maxDay = 0;
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			int p = Integer.parseInt(input.split(" ")[0]);
			int d = Integer.parseInt(input.split(" ")[1]);
			maxDay = Math.max(maxDay, d);
			lectures.add(new int[] {p, d});
		}
		
		Collections.sort(lectures, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o2[1] - o1[1];
				}
				return o2[0] - o1[0];
			}
			
		});
		
		int sum = 0;
		boolean[] checked = new boolean[maxDay + 1];
		for (int i = 0; i < n; i++) {
			for (int j = lectures.get(i)[1]; j >= 1; j--) {
				if (!checked[j]) {
					checked[j] = true;
					sum += lectures.get(i)[0];
					break;
				}
			}
		}
		
		System.out.println(sum);
	}

}