import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		List<int[]> domino = new ArrayList<int[]>(5);
		
		for (int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			domino.add(new int[] {a, l});
		}
		
		Collections.sort(domino, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		int cnt = 1;
		int num = domino.get(0)[0];
		for (int i = 0; i < n; i++) {
			if (num < domino.get(i)[0]) {
				cnt++;
			}
			num = domino.get(i)[0] + domino.get(i)[1];
		}
		
		System.out.println(cnt);
	}
}