import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
 
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
 
        boolean[][] arr = new boolean[N + 1][N + 1];
 
        boolean[][] reverse_arr = new boolean[N + 1][N + 1];
 
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
 
            arr[x][y] = true;
            reverse_arr[y][x] = true;
        }
 
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (arr[i][k] && arr[k][j]) {
                        arr[i][j] = true;
                    }
 
                    if (reverse_arr[i][k] && reverse_arr[k][j]) {
                        reverse_arr[i][j] = true;
                    }
                }
            }
        }
 
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] |= reverse_arr[i][j];
            }
        }
 
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }
 
                if (!arr[i][j]) {
                    cnt++;
                }
            }
 
            sb.append(cnt + "\n");
        }
 
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
 
}