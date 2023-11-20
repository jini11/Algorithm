import java.util.*;
class Solution {
    
    char[][] personality = { { 'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
    static Map<Character, Integer> map = new HashMap<>();
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        for (int i = 0; i < choices.length; i++) {
            char[] sur = survey[i].toCharArray();
            if (choices[i] == 4) continue;
            if (choices[i] > 4) {
                map.put(sur[1], map.getOrDefault(sur[1], 0) + choices[i] - 4);
            } else {
                map.put(sur[0], map.getOrDefault(sur[0], 0) + 4 - choices[i]);
            }
        }
        
        for (int i = 0; i < 4; i++) {
            int first = map.getOrDefault(personality[i][0], 0);
            int second = map.getOrDefault(personality[i][1], 0);
            if (first >= second) {
                answer += personality[i][0];
            } else {
                answer += personality[i][1];
            }
        }
        
        return answer;
    }
}