import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String input = br.readLine();
			boolean[][] check = new boolean[4][14];
			String answer = "";
			
			for (int i = 0; i < input.length(); i += 3) {
				String word = input.substring(i, i+3);
				String card = word.substring(0,1);
				int number = Integer.parseInt(word.substring(1, 3));
				int idx = 0;
				if (card.equals("S")) {
					idx = 0;
				} else if (card.equals("D")) {
					idx = 1;
				} else if (card.equals("H")) {
					idx = 2;
				} else {
					idx = 3;
				}
				if (check[idx][number]) {
					answer = "ERROR";
				}
				check[idx][number] = true;
			}
			System.out.print("#" + test_case + " ");
			if (answer.equals("")) {
				for (int i = 0; i < 4; i++) {
					int cnt = 0;
					for (int j = 1; j < 14; j++) {
						if (!check[i][j]) cnt++;
					}
					System.out.print(cnt + " ");
				}
			} else {
				System.out.print(answer);
			}
			System.out.println();
		}
	}

}