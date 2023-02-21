import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

public class Main {
	static char[] arr;
	static boolean[] isSelected;
	static int size;
	static List<int[]> pointList = new ArrayList<int[]>();
	static TreeSet<String> res = new TreeSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> open = new Stack<>();
		arr = br.readLine().toCharArray();

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '(') {
				open.push(i);
			} else if (arr[i] == ')') {
				int openIdx = open.pop();
				pointList.add(new int[] {openIdx, i});
			}
		}
		size = pointList.size();
		isSelected = new boolean[pointList.size()];
		subset(0);

		for (String ans : res) {
			System.out.println(ans);
		}
	}

	private static void subset(int cnt) {
		if (cnt == size) {
			String sample = makeBracket();
			if (sample.length() != arr.length) {
				res.add(sample);
			}
			return;
		}

		isSelected[cnt] = true;
		subset(cnt + 1);

		isSelected[cnt] = false;
		subset(cnt + 1);
	}
	
	private static String makeBracket() {	
		StringBuilder sb = new StringBuilder();
		boolean[] out = new boolean[arr.length];
		for (int i = 0; i < size; i++) {
			if (isSelected[i]) {
				int[] idx = pointList.get(i);
				out[idx[0]] = true;
				out[idx[1]] = true;
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (!out[i]) {
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}
}