import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        List<int[]> spot = new ArrayList<>();
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int row = N / 2 - 1;
        int col = N / 2;
        int num = 1;
        int d = 1;
        int[] start = new int[] { N / 2 - 1, N / 2 - 1 };
        int[] end = new int[] { N / 2 + 1, N / 2 + 1};

        map[N / 2][N / 2] = num++;
        boolean flag = false;
        spot.add(new int[] {N/2, N/2});
        while (!(row == 0 && col == 0)) {
            map[row][col] = num++;
            spot.add(new int[] {row, col});
            if (flag) {
                d += 1;
                flag = false;
            }
            if (row == start[0] && col == start[1]) {
                start[0] -= 1;
                start[1] -= 1;
                end[0] += 1;
                end[1] += 1;
                d = 0;
                flag = true;
            } else if ((row == start[0] && col == end[1]) || (row == end[0] && col == end[1]) || (row == end[0] && col == start[1])) {
                d = (d + 1) % 4;
            }
            row += dr[d];
            col += dc[d];
        }
        map[0][0] = num;
        spot.add(new int[] {0, 0});

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        sb.append((spot.get(K - 1)[0] + 1) + " " + (spot.get(K - 1)[1] + 1));
        System.out.println(sb);
    }
}
