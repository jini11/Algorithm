import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static char[][] map;
    static boolean[][] visited;
    static List<Integer> house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        house = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '0' || visited[i][j]) continue;
                house.add(bfs(i, j));
            }
        }

        Collections.sort(house);
        System.out.println(house.size());
        for (int num : house) {
            System.out.println(num);
        }
    }

    private static int bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[r][c] = true;
        queue.add(new int[] {r, c});

        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        int houseCnt = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == '0') continue;
                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});
                houseCnt++;
            }
        }
        return houseCnt;
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= N;
    }
}
