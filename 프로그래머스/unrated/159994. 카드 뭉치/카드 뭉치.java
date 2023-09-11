import java.util.*;
class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";       
        
        Map<String, Integer> map = new HashMap<>();
        Queue<String> queue1 = new ArrayDeque<>();
        Queue<String> queue2 = new ArrayDeque<>();
        
        for (int i = 0; i < cards1.length; i++) {
            queue1.add(cards1[i]);
            map.put(cards1[i], 1);
        }
        for (int i = 0; i < cards2.length; i++) {
            queue2.add(cards2[i]);
            map.put(cards2[i], 2);
        }
        
        for (int i = 0; i < goal.length; i++) {
            String word = goal[i];
            if (!map.containsKey(word)) {
                return "No";
            }
            if (map.get(word) == 1) {
                if (!outWord(queue1, word)) {
                    return "No";
                }
            } else {
                if (!outWord(queue2, word)) {
                    return "No";
                }
            }
            map.remove(word);
        }
        
        return answer;
    }
    
    private static boolean outWord(Queue<String> queue, String word) {
        while (!queue.isEmpty()) {
            String temp = queue.poll();
            if (temp.equals(word)) {
                return true;
            } 
            return false;
        }
        return false;
    }
}

/*
입력값 〉 ["a", "b", "c"], ["d", "e", "f"], ["a", "d", "f"]
기댓값 〉 "No"
*/