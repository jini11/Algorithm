import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String command = br.readLine();
            command = command.replaceAll("RR", "");
            char[] commands = command.toCharArray();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            List<Integer> list = new ArrayList<>();
            while (st.hasMoreTokens()) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            boolean rotate = false;
            boolean isDelete = false;
            for (int i = 0; i < commands.length; i++) {
                if (commands[i] == 'R') {
                    rotate = !rotate;
                } else {
                    if (list.size() == 0) {
                        isDelete = true;
                        break;
                    }
                    if (!rotate) {
                        list.remove(0);
                    } else {
                        list.remove(list.size() - 1);
                    }
                }
            }
            if (list.size() > 0) {
                sb.append(listToString(list, rotate) + "\n");
            } else {
                if (isDelete) {
                    sb.append("error\n");
                } else {
                    sb.append("[]\n");
                }
            }
        }
        System.out.println(sb.toString());
    }

    public static String listToString(List<Integer> list, boolean rotate) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (!rotate) {
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i)).append(",");
            }
        } else {
            for (int i = list.size() - 1; i >= 0; i--) {
                sb.append(list.get(i)).append(",");
            }
        }
        sb.delete(sb.length()-1, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
