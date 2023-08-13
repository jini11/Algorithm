class Solution {
    public int[] plusOne(int[] digits) {
        
        int temp = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] + 1 < 10) {
                digits[i] = digits[i] + temp;
                temp = 0;
                return digits;
            }
            if (i == 0) {
                int[] arr = new int[digits.length + 1];
                arr[0] = 1;
                return arr;
            }
            
            digits[i] = (digits[i] + temp) % 10;
        }
        
        return digits;
    }
}