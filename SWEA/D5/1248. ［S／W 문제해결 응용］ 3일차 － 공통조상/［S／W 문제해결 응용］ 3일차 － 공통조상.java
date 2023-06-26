import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	static int V, E, A, B;
	static Node[] nodes;
	static List<Integer> ancestorA, ancestorB;
	
	static class Node {
		List<Integer> children;
		int parent;
		
		Node() {
			this.children = new ArrayList<Integer>();
			this.parent = 0;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			V = sc.nextInt();
			E = sc.nextInt();
			A = sc.nextInt();
			B = sc.nextInt();
			nodes = new Node[V+1];
			ancestorA = new ArrayList<>();
			ancestorB = new ArrayList<>();
			int same = 0;
			
			for (int i = 0; i <= V; i++) {
				nodes[i] = new Node();
			}
			for (int i = 0; i < E; i++) {
				int p = sc.nextInt();
				int c = sc.nextInt();
				nodes[p].children.add(c);
				nodes[c].parent = p;
			}
			
			findAncestor(A, ancestorA);
			findAncestor(B, ancestorB);
			
			for (int i = 0; i < V; i++) {
				if (!ancestorA.get(i).equals(ancestorB.get(i))) {
					break;
				}
				same = ancestorA.get(i);
			}
			
			System.out.println("#" + tc + " " + same + " " + countNode(same));
		}
	}
	
	public static void findAncestor(int N, List<Integer> list) {
		int parent = nodes[N].parent;
		if (parent != 0) {
			findAncestor(parent, list);
		}
		list.add(N);
	}
	
	public static int countNode(int root) {
		int res = 1;
		for (int child : nodes[root].children) {
			res += countNode(child);
		}
		return res;
	}
}