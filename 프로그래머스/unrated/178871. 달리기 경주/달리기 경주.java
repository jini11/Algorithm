import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        List<String> names = Arrays.asList(players);
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }
        for (int i = 0; i < callings.length; i++) {
            int idx = map.get(callings[i]);
            String temp = players[idx];
            players[idx] = players[idx-1];
            players[idx-1] = temp;
            map.put(players[idx], map.get(players[idx]) + 1);
            map.put(callings[i], map.get(callings[i]) - 1);
        }
        return players;
    }
}