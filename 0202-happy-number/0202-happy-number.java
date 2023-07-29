class Solution {
    public boolean isHappy(int n) {
        String num = Integer.toString(n);
        boolean flag = true;
        
        while (true) {
            int sum = 0;
            for (int i = 0; i < num.length(); i++) {
                int temp = num.charAt(i) - '0';
                sum += Math.pow(temp, 2);
            }
            
            num = Integer.toString(sum);
            if (num.length() == 1) {
                if (num.equals("1")) {
                    return true;
                }
                if (!flag) break;
                flag = false;
            } 
        }
        
        if (num.equals("1")) {
            return true;
        }
        return false;
    }
}