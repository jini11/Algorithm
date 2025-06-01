class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        
        for (int mid = 1; mid < cookie.length; mid++) {
            int left = mid - 1;
            int right = mid;
            int leftSum = cookie[left];
            int rightSum = cookie[right];
            while (true) {
                if (leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                }
                
                
                if (leftSum <= rightSum && left > 0) {
                    left--;
                    leftSum += cookie[left];
                } else if (leftSum > rightSum && right < cookie.length - 1) {
                    right++;
                    rightSum += cookie[right];
                } else {
                    break;
                }
            }
        }
        
        
        return answer;
    }
}

/*
1번부터 N번까지 바구니
첫째 I번 바구니부터 M번 바구니까지
둘째 M + 1 ~ R번 바구니
받을 과자 수 같아야 함
아들에게 줄 수 있는 가장 많은 과자 수

투포인터
*/