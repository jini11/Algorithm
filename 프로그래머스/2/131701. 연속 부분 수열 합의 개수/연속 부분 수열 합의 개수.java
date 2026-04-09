import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        
        int[] arr = new int[elements.length * 2 + 1];
        arr[1] = elements[0];
        for (int i = 2; i < arr.length; i++) {
            arr[i] = elements[(i - 1) % elements.length] + arr[i-1];
            set.add(elements[(i - 1) % elements.length]);
        }
        // System.out.println(Arrays.toString(arr));
        for (int i = 2; i <= elements.length; i++) {
            // System.out.println("len: " + i);
            for (int j = i; j < elements.length + i; j++) {
                set.add(arr[j] - arr[j - i]);
                // System.out.println(arr[j] - arr[j - i]);
            }
        }
        return set.size();
    }
}