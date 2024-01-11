import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = "";
		
		while (true) {
			input = br.readLine();
		
			if (input.equals("end")) {
				break;
			}
			
			boolean vowels = false;
			boolean three = true;
			boolean twice = true;
			
			int vCnt = 0;
			int aCnt = 0;
			char before = '-';
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				
				if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
					vowels = true;
					vCnt++;
					aCnt = 0;
				} else {
					aCnt++;
					vCnt = 0;
				}
				
				if (c == before && c != 'e' && c != 'o') {
					twice = false;
				}
				
				if (vCnt == 3 || aCnt == 3) {
					three = false;
					break;
				}
				before = c;
			}
			
			if (vowels && three && twice) {
				System.out.println("<" + input + "> is acceptable.");
			} else {
				System.out.println("<" + input + "> is not acceptable.");
			}
		}
	}
}