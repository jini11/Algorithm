import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] arr = new int[N];		// 원본 배열
		int[] clone = new int[N];	// 정렬할 배열
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = clone[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(clone);	// 오름차순 정렬

		int cnt = 0;
		for (int i = 0; i < N; i++) {			// 숫자 카운트 해줌
			if (!map.containsKey(clone[i])) {
				map.put(clone[i], cnt++);
			}
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(map.get(arr[i]) + " ");
		}
		System.out.println(sb.toString());
	}
}