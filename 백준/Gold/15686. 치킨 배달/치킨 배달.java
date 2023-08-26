import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, res;
    static int[][] map;
    static List<int[]> chickens;
    static List<int[]> homes;
    static int[] selectedChicken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        chickens = new ArrayList<>();
        homes = new ArrayList<>();
        selectedChicken = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chickens.add(new int[] {i, j});
                } else if (map[i][j] == 1) {
                    homes.add(new int[] {i, j});
                }
            }
        }

        res = Integer.MAX_VALUE;
        combi(0, 0);

        System.out.println(res);
    }
    private static void combi(int cnt, int start) {
        if (cnt == M) {
            res = Math.min(res, getDistance());
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            selectedChicken[cnt] = i;
            combi(cnt + 1, i + 1);
        }
    }

    private static int getDistance() {
        int sum = 0;

        for (int i = 0; i < homes.size(); i++) {
            int distance = Integer.MAX_VALUE;
            for (int j = 0; j < selectedChicken.length; j++) {
                int temp = Math.abs(homes.get(i)[0] - chickens.get(selectedChicken[j])[0])
                        + Math.abs(homes.get(i)[1] - chickens.get(selectedChicken[j])[1]);
                distance = Math.min(distance, temp);
            }
            sum += distance;
        }
        return sum;
    }
}