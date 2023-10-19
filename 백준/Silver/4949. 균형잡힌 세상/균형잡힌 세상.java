import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

public class Main {
	static final String[] res = { "yes", "no" };
	static final String op = "()[]";
	static final char[] opArr = { '(', ')', '[', ']' };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// () []
		while (true) {
			String input = br.readLine();
			int flag = 0;
			if (input.equals(".")) {
				break;
			}
			Stack<Character> stack = new Stack<>();
			
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				if (op.contains(Character.toString(c))) {
					if (stack.isEmpty()) {
						stack.push(c);
					} else {
						if (c == opArr[0] || c == opArr[2]) {
							stack.push(c);
						} else if (c == opArr[1]) {
							char top = stack.peek();
							if (top == opArr[0]) {
								stack.pop();
							} else {
								stack.push(c);
							}
						} else if (c == opArr[3]) {
							char top = stack.peek();
							if (top == opArr[2]) {
								stack.pop();
							} else {
								stack.push(c);
							}
						}
					}
				}
			}
			if (!stack.isEmpty()) {
				flag = 1;
			}
			sb.append(res[flag]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}