import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int [] map = {
	        0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0, // 0 ~ 21
	        10, 13, 16, 19, 25, 30, 35, 40, 0,  // 22 ~ 30
	        20, 22, 24, 25, 30, 35, 40, 0,      // 31 ~ 38
	        30, 28, 27, 26, 25, 30, 35, 40, 0}; // 39 ~ 47
	static int[] horse, dice;
	static boolean[] visited;
	static int[] isSelected = new int[10];
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dice = new int[10];
		for (int i = 0; i < dice.length; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		perm(0);
		
		System.out.println(res);
	}
	
	private static void perm(int cnt) {
		if (cnt == 10) {
			int sum = 0;
			visited = new boolean[48];
			horse = new int[4]; // 몇 번째 칸
			for (int i = 0; i < 10; i++) {
				int hIdx = isSelected[i];
				
				if (isEnd(horse[hIdx])) return;
				
				// 다음 위치
				int next = move(horse[hIdx], dice[i]);
				
				if (isEnd(next)) {
					setVisited(visited, horse[hIdx], false);
					horse[hIdx] = next;
					continue;
				}
				// 다음 위치로 갈 수 없으면 return
				if (visited[next]) return;
				
				// 방문처리
				setVisited(visited, horse[hIdx], false);
				setVisited(visited, next, true);
				
				// 갈 수 있으면 이동
				horse[hIdx] = next;
				
				// 점수 갱신
				sum += map[horse[hIdx]];
			}
			res = Math.max(res, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			isSelected[cnt] = i;
			perm(cnt + 1);
		}
	}
	
	private static void setVisited(boolean[] visited, int idx, boolean flag) {
		if(idx == 20 || idx == 29 || idx == 37 || idx == 46) {
            visited[20] = flag;
            visited[29] = flag;
            visited[37] = flag;
            visited[46] = flag;
        } else if(idx == 26 || idx == 34 || idx == 43) {
            visited[26] = flag;
            visited[34] = flag;
            visited[43] = flag;
        } else if(idx == 27 || idx == 35 || idx == 44) {
            visited[27] = flag;
            visited[35] = flag;
            visited[44] = flag;
        }else if(idx == 28 || idx == 36 || idx == 45) {
            visited[28] = flag;
            visited[36] = flag;
            visited[45] = flag;
        }else {
            visited[idx] = flag;
        }
	}
	
	private static boolean isEnd(int idx) {
		if (idx == 21 || idx == 30 || idx == 38 || idx == 47)
			return true;
		return false;
	}
	
	private static int move(int idx, int dice) {
		int nextIdx = idx + dice;
		
		if(idx < 21) {
            if(nextIdx >= 21) nextIdx = 21;
        } else if(idx < 30) {
            if(nextIdx >= 30) nextIdx = 30;
        } else if(idx < 38) {
            if(nextIdx >= 38) nextIdx = 38;
        } else if(idx < 47) {
            if(nextIdx >= 47) nextIdx = 47;
        }

        if(nextIdx == 5) return 22;
        if(nextIdx == 10) return 31;
        if(nextIdx == 15) return 39;
        return nextIdx;
	}
}