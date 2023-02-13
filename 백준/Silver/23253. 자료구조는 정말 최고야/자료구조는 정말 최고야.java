import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String result = "";
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			int k = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			int before = Integer.parseInt(st.nextToken());
			for (int j = 1; j < k; j++) {
				int book = Integer.parseInt(st.nextToken());
				if (before < book) {
					result = "No";
					break;
				}
                before = book;
			}
		}
		if (result.equals("")) {
			result = "Yes";
		}
		
		System.out.println(result);
	}
}