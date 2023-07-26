class Solution {
    public int romanToInt(String s) {
        char[] roman = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] value = {1, 5, 10, 50, 100, 500, 1000};
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < roman.length; i++) {
            map.put(roman[i], value[i]);
        }
        
        int sum = map.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 1; i > 0; i--) {

            int cur = map.get(s.charAt(i));
            int next = map.get(s.charAt(i-1));
            if (cur > next) {
                sum -= next;
            } else {
                sum += next;
            }
        }
        // sum += map.get(s.charAt(0));
        
        return sum;
    }
}