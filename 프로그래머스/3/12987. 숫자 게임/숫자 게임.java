import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int left = 0;
        int right = B.length - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] < B[right]) {
                answer++;
                right--;
            } else {
                left++;
            }
        }
        return answer;
    }
}
/*

A: 1 3 5 7
B: 2 2 6 8

*/