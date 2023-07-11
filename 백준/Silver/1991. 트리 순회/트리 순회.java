import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final Node root = new Node('A', null, null);
	static StringBuilder sb;
	
	static class Node {
		char head;
		Node left;
		Node right;

		public Node(char head, Node left, Node right) {
			this.head = head;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char head = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			insert(root, head, left, right);
		}
		
		pre(root);
		sb.append("\n");
		in(root);
		sb.append("\n");
		post(root);
		sb.append("\n");
		
		System.out.println(sb.toString());
	}
	
	private static void pre(Node node) {
		if (node == null) return;
		
		sb.append(node.head);
		pre(node.left);
		pre(node.right);
	}
	
	private static void in(Node node) {
		if (node == null) return;
		
		in(node.left);
		sb.append(node.head);
		in(node.right);
	}
	
	private static void post(Node node) {
		if (node == null) return;
		
		post(node.left);
		post(node.right);
		sb.append(node.head);
	}
	
	private static void insert(Node node, char head, char left, char right) {
		if (node.head == head) {
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
				insert(node.left, head, left, right);
			}
			if (node.right != null) {
				insert(node.right, head, left, right);				
			}
		}
	}
}