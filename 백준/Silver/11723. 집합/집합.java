import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int S = 0;
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String command = st.nextToken();
			
			if (command.equals("all")) {
				S = (1 << 21) - 1;
			} else if (command.equals("empty")) {
				S = 0;
			} else {
				int num = Integer.parseInt(st.nextToken());
				if (command.equals("add")) {
					S = S | (1 << num);
				} else if (command.equals("remove")) {
					S = S & ~(1 << num);
				} else if (command.equals("check")) {
					if ((S & (1 << num)) != 0) {
						sb.append("1\n");
					} else {
						sb.append("0\n");
					}
				} else { // toggle
					if ((S & (1 << num)) != 0) {
						S = S & ~(1 << num);
					} else {
						S = S | (1 << num);
					}
				}
			}
		}
		System.out.println(sb.toString());
	}
}