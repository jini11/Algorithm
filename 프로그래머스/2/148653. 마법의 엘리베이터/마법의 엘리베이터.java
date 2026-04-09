import java.util.*;

class Solution {
    int[] arr = new int[100000001];
    
    public int solution(int storey) {
        int answer = 0;
        while (storey != 0) {
            int mod = storey % 10;            
            storey /= 10;
            
            if (mod > 5) {
                answer += 10 - mod;
                storey++;
            } else if (mod < 5) {
                answer += mod;
            } else if (storey % 10 >= 5) {
                answer += 5;
                storey++;
            } else {
                answer += 5;
            }
        }
        return answer;
    }
    
    private void dfs(int cur, int cnt) {
        arr[cur] = cnt;
        if (cur == 0) return;
        for (int i = 0; i <= 8; i++) {
            int up = cur + (int) Math.pow(10, i);
            if (up >= arr.length || arr[up] != Integer.MAX_VALUE) return;
            dfs(up, cnt + 1);
        }
        for (int i = 0; i <= 8; i++) {            
            int down = cur - (int) Math.pow(10, i);
            if (down < 0 || arr[down] != Integer.MAX_VALUE) return;
            dfs(down, cnt + 1);
        }
    }
}