import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, res;
    static int[][] map, sticker;
    static PriorityQueue<State> pq;

    static class State implements Comparable<State> {
        int r, c;
        public State(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(State o) {
            if (this.r == o.r) {
                return this.c - o.c;
            }
            return this.r - o.r;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int t = 0; t < K; t++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            sticker = new int[R][C];
            int num = 0;
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                    if (sticker[i][j] == 1) {
                        num++;
                    }
                }
            }

            int cnt = 0;
            while (true) {
                pq = new PriorityQueue<>();
                isAttach(sticker);
                if (pq.size() > 0) {
                    State cur = pq.poll();
                    attach(sticker, cur);
                    res += num;
                    break;
                }
                sticker = rotate(sticker);
                cnt++;
                if (cnt == 4) {
                    break;
                }
            }
        }
        System.out.println(res);
    }

    private static void attach(int[][] sticker, State cur) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 1) {
                    map[i + cur.r][j + cur.c] = sticker[i][j];
                }
            }
        }
    }

    private static void isAttach(int[][] sticker) {

        for (int i = 0; i <= N - sticker.length; i++) {
            for (int j = 0; j <= M - sticker[0].length; j++) {
                boolean flag = true;
                for (int n = 0; n < sticker.length; n++) {
                    for (int m = 0; m < sticker[0].length; m++) {
                        if (sticker[n][m] == 1 && map[i + n][j + m] == 1)  {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    pq.add(new State(i, j));
                }
            }
        }
    }

    private static int[][] rotate(int[][] sticker) {
        int n = sticker.length;
        int m = sticker[0].length;

        int[][] newSticker = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newSticker[j][n - i - 1] = sticker[i][j];
            }
        }
        return newSticker;
    }
}