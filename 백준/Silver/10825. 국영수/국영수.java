import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		String[][] score = new String[N][4];
		for (int i = 0; i < N; i++) {
			score[i][0] = sc.next();
			score[i][1] = sc.next();
			score[i][2] = sc.next();
			score[i][3] = sc.next();
		}
		
		Arrays.sort(score, new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				if (o1[1].equals(o2[1]) && o1[2].equals(o2[2]) && o1[3].equals(o2[3])) {
					return o1[0].compareTo(o2[0]);
				}
				if (o1[1].equals(o2[1]) && o1[2].equals(o2[2])) {
					return  Integer.parseInt(o2[3]) - Integer.parseInt(o1[3]);
				}
				if (o1[1].equals(o2[1])) {
					return Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]);
				}
				return Integer.parseInt(o2[1]) - Integer.parseInt(o1[1]);
			}

			
		});
		
		for (int i = 0; i < N; i++) {
			System.out.println(score[i][0]);
		}
	}

}