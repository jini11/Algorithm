class Solution {
    public int majorityElement(int[] nums) {
        int size = nums.length;
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0;i<size;i++)
        {
            hm.put(nums[i],hm.getOrDefault(nums[i],0)+1);
        }
        for(int key:hm.keySet())
        {
            if(hm.get(key)>size/2)
            {
                return key;
            }
        }
        return -1;
    }
}