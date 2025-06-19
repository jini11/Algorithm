import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static int[][] foodArr;

    static class Tree {
        int x;
        int y;
        int age;
        boolean isDead;

        public Tree(int x, int y, int age, boolean isDead) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.isDead = isDead;	
        }
    }

    static List<Tree> liveTreeList = new ArrayList<>();  // 현재 살아있는 나무의 리스트
    static Queue<Integer> deadTreeQueue = new ArrayDeque<>();  // 죽어있는 나무의 큐

    static int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        foodArr = new int[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                foodArr[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int tx = Integer.parseInt(st.nextToken()) - 1;
            int ty = Integer.parseInt(st.nextToken()) - 1;
            int tAge = Integer.parseInt(st.nextToken());

            liveTreeList.add(new Tree(tx, ty, tAge, false));
        }

        while (K-- > 0) {
            springTree();
            summerTree();
            fallTree();
            winterTree();
        }
        
        bw.write(Integer.toString(liveTreeList.size()));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void springTree() {

        for (int i = 0; i < liveTreeList.size(); i++) {
            Tree tree = liveTreeList.get(i);
            
            if (map[tree.x][tree.y] >= tree.age) { 
                map[tree.x][tree.y] -= tree.age;
                tree.age += 1;
                continue;
            }
            
            deadTreeQueue.add(i);
        }
    }

    private static void summerTree() {
        while (!deadTreeQueue.isEmpty()) {
        	
            Tree tree = liveTreeList.get(deadTreeQueue.poll());
            
            int age = tree.age;
            map[tree.x][tree.y] += age / 2;
            
            tree.isDead = true;
        }
    }

    private static void fallTree() {
        List<Tree> newTreeList = new ArrayList<>();

        for (int i = 0; i < liveTreeList.size(); i++) {
            Tree tree = liveTreeList.get(i);
            
            if (!tree.isDead) {
            	int x = tree.x;
                int y = tree.y;

                if (tree.age % 5 == 0) {

                    for (int j = 0; j < 8; j++) {
                        int xf = x + dx[j];
                        int yf = y + dy[j];

                        if (xf >= 0 && xf < N && yf >= 0 && yf < N) {
                        	newTreeList.add(new Tree(xf, yf, 1, false));
                        }
                    }
                }
            }
        }
        
	      for (Tree tree : liveTreeList) {
	    	   if (!tree.isDead) {
	    		   newTreeList.add(tree);
			   }
		  }
        
        liveTreeList = newTreeList;
    }

    private static void winterTree() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += foodArr[i][j];
            }
        }
    }
}
