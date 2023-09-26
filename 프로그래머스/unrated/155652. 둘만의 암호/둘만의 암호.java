import java.util.*;
class Solution {
    static boolean[] alpha = new boolean[26];
    public String solution(String s, String skip, int index) {
        String answer = "";
        for (int i = 0; i < skip.length(); i++) {
            alpha[skip.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < s.length(); i++) {
            int curIdx = s.charAt(i) - 'a';
            int cnt = 0;
            while (cnt < index) {
                curIdx++;
                if (curIdx == 26) curIdx = 0;
                if (alpha[curIdx]) {
                    continue;
                }
                cnt++;
            }
            answer += (char) (curIdx + 'a');
        }
        return answer;
    }
}