import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            switch (command) {
                case 1:
                    int num = Integer.parseInt(st.nextToken());
                    stack.push(num);
                    break;
                case 2:
                    if (stack.isEmpty()) {
                        sb.append("-1\n");
                        break;
                    }
                    sb.append(stack.pop() + "\n");
                    break;
                case 3:
                    sb.append(stack.size() + "\n");
                    break;
                case 4:
                    if (stack.isEmpty()) {
                        sb.append("1\n");
                    } else {
                        sb.append("0\n");
                    }
                    break;
                case 5:
                    if (stack.isEmpty()) {
                        sb.append("-1\n");
                        break;
                    }
                    sb.append(stack.peek() + "\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
