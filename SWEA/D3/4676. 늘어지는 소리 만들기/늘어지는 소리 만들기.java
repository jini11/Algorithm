import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String word = br.readLine();
			List<Character> list = new ArrayList<>();
			
			for (int i = 0; i < word.length(); i++) {
				list.add(word.charAt(i));
			}
			int num = Integer.parseInt(br.readLine());
            int[] idx = new int[num];
			String input = br.readLine();
			for (int i = 0; i < num; i++) {
				idx[i] = Integer.parseInt(input.split(" ")[i]);
            }
			Arrays.sort(idx);
            for (int i = 0; i < num; i++) {
                int cnt = 0;
                boolean flag = false;
                for (int j = 0; j <list.size(); j++) {
                    if (list.get(j) != '-') {
                     	cnt++;   
                    }
                    if (cnt == idx[i]) {
                     	cnt = j+1;
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                 	cnt = 0;   
                }
                list.add(cnt, '-');
            }
			sb.append("#" + test_case + " " );
            for (int i = 0; i <list.size(); i++) {
             	sb.append(list.get(i));   
            }
			sb.append("\n");
		}
        System.out.println(sb.toString());
	}

}