package LC.s400;

public class LC377 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i = 0; i < target+1; i++){
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}
class Solution {
    Integer[] dp;
    public int combinationSum4(int[] nums, int target) {
        //完全背包问题
        dp = new Integer[target+1];
        dp[0] = 1;
        return help(nums,target);
    }
    private int help(int[] nums, int target){
        if(target == 0){
            return 1;
        }
        if(target < 0) return 0;
        if(dp[target] != null) return dp[target];
        //0 到 index 有多少种凑成target
        int res = 0;
        for (int num : nums) {
            if (num <= target)
                res += help(nums, target - num);
        }
        dp[target] = res;
        return res;
    }
}
//记忆化递归