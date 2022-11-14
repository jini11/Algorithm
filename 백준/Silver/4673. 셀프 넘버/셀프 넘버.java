public class Main {

	static boolean[] arr = new boolean[10001];
	public static void main(String[] args) {
		for (int i=1; i<arr.length; i++) {
			selfNumber(i);
		}
		for (int i=1; i<arr.length; i++) {
			if (!arr[i]) {
				System.out.println(i);
			}
		}
	}	
	public static void selfNumber(int num) {
		
		int sum = num;
		sum += num / 1000;
		sum += (num % 1000) / 100;
		sum += ((num % 1000) % 100) / 10;
		sum += (((num % 1000) % 100) % 10);
		
		if (sum < 10001) {
			arr[sum] = true;
		}
	}
}