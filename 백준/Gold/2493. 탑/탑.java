import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] res = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            if (arr[stack.peek()] <= arr[i]) {
                while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                    int idx = stack.pop();
                    res[idx] = i + 1;
                }
            }
            stack.push(i);
        }
        for (int i = 0; i < N; i++) {
            System.out.print(res[i] + " ");
        }
    }
}