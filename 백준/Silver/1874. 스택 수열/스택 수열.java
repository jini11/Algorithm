import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();

		int N = Integer.parseInt(br.readLine());
		
		int number = 1;
		for (int t = 0; t < N; t++) {
			int cur = Integer.parseInt(br.readLine());
			
			if (cur >= number) {
				for (int i = 0, size = cur - number; i <= size; i++) {
					stack.add(number++);
					sb.append("+").append("\n");
				}
			}
			if (stack.peek() == cur) {
				sb.append("-").append("\n");
				stack.pop();
			} else if (stack.peek() > cur) {
				sb = new StringBuilder("NO");
				break;
			}
		}
		
		System.out.println(sb.toString());
	}
}