import java.util.*;
class Solution {
    String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    public int solution(String s) {
        int answer = 0;
        
        for (int i = 0; i < arr.length; i++) {
            s = s.replaceAll(arr[i], i+"");
        }
        
        return Integer.parseInt(s);
    }
}