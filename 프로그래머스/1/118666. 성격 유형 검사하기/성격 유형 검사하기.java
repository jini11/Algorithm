import java.util.*;

class Solution {
    
    char[][] personality = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < survey.length; i++) {
            String[] sur = survey[i].split("");
            if (choices[i] == 4) continue;
            if (choices[i] > 4) {
                map.put(sur[1], map.getOrDefault(sur[1], 0) + Math.abs(choices[i] - 4));
            } else {
                map.put(sur[0], map.getOrDefault(sur[0], 0) + Math.abs(choices[i] - 4));   
            }
        }
        
        for (int i = 0; i < 4; i++) {
            int a = map.getOrDefault(Character.toString(personality[i][0]), 0);
            int b = map.getOrDefault(Character.toString(personality[i][1]), 0);
            
            if (a >= b) {
                answer += personality[i][0];
            } else {
                answer += personality[i][1];
            }
        }
        
        return answer;
    }
}

/*

1: RT
2: CF
3: JM
4: AN

매우 비동의 - 네오형(N)
매우 동의 - 어피치형(A)


N 1
C 1
M 2
T 3
A 1
사전순
TCM

hashmap -> RT, CF, JM, AN
*/