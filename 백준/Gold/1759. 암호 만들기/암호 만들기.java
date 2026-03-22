import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static char[] alpha, selected;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alpha = new char[C];
        selected = new char[L];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alpha);
        combi(0, 0);

        System.out.println(sb.toString());
    }

    private static void combi(int cnt, int start) {
        if (cnt == L) {
            String sen = "";
            for (int i = 0; i < L; i++) {
                sen += selected[i];
            }
            if (isPossible(sen)) {
                sb.append(sen + "\n");
            }
            return;
        }
        for (int i = start; i < C; i++) {
            selected[cnt] = alpha[i];
            combi(cnt + 1, i + 1);
        }
    }

    private static boolean isPossible (String sen) {
        int len = sen.length();
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if (sen.charAt(i) == 'a' || sen.charAt(i) == 'e' || sen.charAt(i) == 'i' || sen.charAt(i) == 'o' || sen.charAt(i) == 'u') {
                cnt++;
            }
        }
        if (cnt >= 1 && len - cnt >= 2) {
            return true;
        }
        return false;
    }
}