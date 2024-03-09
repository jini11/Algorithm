import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] parents;
	
	static void makeSet() { // 서로소 초기화
		parents = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int v) { // 원소의 대표 원소 찾기(재귀적으로 부모를 찾음)
		if (v == parents[v]) {
			return v;
		}
		
		return findSet(parents[v]);
	}
	
	static void union(int v, int u) { // 두 그룹을 합치는 기능
		int vRoot = findSet(v);
		int uRoot = findSet(u);
		
		if (vRoot > uRoot) {
			int tmp = vRoot;
			vRoot = uRoot;
			uRoot = tmp;
		}
		if (vRoot == uRoot) { // 그래프
			parents[vRoot] = 0;
		} else {
			parents[uRoot] = vRoot;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int idx = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if (N == 0 && M == 0) {
				break;
			}
			
			makeSet();
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				union(from, to);
			}

			Set<Integer> treeSet = new HashSet<>();
			for (int i = 1; i < N + 1; i++) {
				int ra = findSet(i);
				if (ra > 0) {
					treeSet.add(ra);
				}
			}
			
			int treeNum = treeSet.size();
			if (treeNum == 0) {
				sb.append("Case " + idx + ": No trees.\n");
			} else if (treeNum == 1) {
				sb.append("Case " + idx + ": There is one tree.\n");
			} else {
				sb.append("Case " + idx + ": A forest of " + treeNum + " trees.\n");
			}
			idx++;
		}
		
		System.out.println(sb.toString());
	}
}