class Solution {
    public int removeElement(int[] nums, int val) {
        int i=0,ans=0;
        int n = nums.length>0?nums.length:0;
        if(n==1 && nums[0]!=val) return n;
        for( int j=0; j<n;j++){
            if(nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        
        return i;
    }
}