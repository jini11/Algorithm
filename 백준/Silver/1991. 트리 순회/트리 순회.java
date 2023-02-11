import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static Node root = new Node('A', null, null);	// 루트 노드
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			insert(root, st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
		}

		preOrder(root);
		sb.append("\n");
		inOrder(root);
		sb.append("\n");
		postOrder(root);

		System.out.println(sb);
	}

	public static void insert(Node node, char root, char left, char right) {
		if (node.value == root) {
			if (left == '.') {		
				node.left = null;
			} else {
				node.left = new Node(left, null, null);
			}
			if (right == '.') {
				node.right = null;
			} else {
				node.right = new Node(right, null, null);
			}
		} else {
			if (node.left != null) {
				insert(node.left, root, left, right);
			}
			if (node.right != null) {
				insert(node.right, root, left, right);
			}
		}
	}

	public static void preOrder(Node node) {	// 전위순회
		if (node == null) {
			return;
		}
		sb.append(node.value);
		preOrder(node.left);
		preOrder(node.right);
	}

	public static void inOrder(Node node) {		// 즁위순회
		if (node == null) {
			return;
		}
		inOrder(node.left);
		sb.append(node.value);
		inOrder(node.right);
	}

	public static void postOrder(Node node) {	// 후위순회
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.value);
	}

	static class Node {
		private char value;
		private Node left;
		private Node right;

		Node(char value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
}