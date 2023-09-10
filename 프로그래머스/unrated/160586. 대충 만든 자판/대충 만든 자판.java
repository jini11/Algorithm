import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < keymap.length; i++) {
            for (int j = 0; j < keymap[i].length(); j++) {
                char word = keymap[i].charAt(j);
                if (map.containsKey(word)) {
                    map.put(word, Math.min(map.get(word), j));
                } else {
                    map.put(word, j);
                }
            }
        }
        
        for (int i = 0; i < targets.length; i++) {
            int cnt = 0;
            for (int j = 0; j < targets[i].length(); j++) {
                char targetWord = targets[i].charAt(j);
                if (map.containsKey(targetWord)) {
                    cnt += (map.get(targetWord) + 1);
                } else {
                    cnt = -1;
                    break;
                }
            }
            answer[i] = cnt;
        }
        
        return answer;
    }
}