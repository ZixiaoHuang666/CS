package LC.s100;
//Jump game
class LC45_55 {
    public int jump(int[] nums) {
        int numJump = 0;
        int curIndex = 0;
        while(curIndex < nums.length-1){
            int maxIndex = 1;
            int maxSum = 1;
            numJump++;
            for(int i = 1; i <= nums[curIndex]; i++){
                if(curIndex + i >= nums.length-1) return numJump;
                int curJump = i + nums[curIndex +i];
                if(curJump > maxSum){
                    maxSum = curJump;
                    maxIndex = i + curIndex;
                }
            }
            curIndex = maxIndex;
        }
        return numJump;
    }
}
//55
class Solution {
    public boolean canJump(int[] nums) {
        int curIndex = 0;
        while(curIndex < nums.length-1){
            int maxIndex = 0;
            int maxSum = 0;
            for(int i = 0; i <= nums[curIndex]; i++){
                if(curIndex + i >= nums.length-1) return true;
                int curJump = i + nums[curIndex +i];
                if(curJump > maxSum){
                    maxSum = curJump;
                    maxIndex = i + curIndex;
                }
            }
            if(maxIndex == curIndex) return false;
            curIndex = maxIndex;
        }
        return true;
    }
}
