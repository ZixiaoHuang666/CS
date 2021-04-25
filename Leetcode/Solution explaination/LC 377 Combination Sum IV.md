# LC 377 Combination Sum IV

![image-20210424110140425](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210424110140425.png)

## Pre-thinking

本来这道题一看到题我就以为用完全背包，典型的不真读题

~~~java
class Solution {
    Integer[][] dp;
    public int combinationSum4(int[] nums, int target) {
        //完全背包问题
        dp = new Integer[nums.length+1][target+1];
        dp[0][0] = 1;
        return help(nums,nums.length,target);
    }
    private int help(int[] nums, int index, int target){
        if(target == 0){
            
            return 1;
        } 
        if(index <= 0 || target < 0) return 0;
        if(dp[index][target] != null) return dp[index][target];
        //0 到 index 有多少种凑成target
        int curValue = nums[index-1];
        int res = help(nums,index-1,target);
        if(target >= curValue)
        res += help(nums,index,target-curValue);
        dp[index][target] = res;
        return res;
    }
}
~~~

我就写了一个完全背包一看答案就傻眼了，我求的是组合数，人家要的是排列数，对于排列数而言其实是一种变式而且写法其实比完全背包简单首先贴一个链接总结的背包问题分类

![image-20210424200642777](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210424200642777.png)

https://leetcode-cn.com/problems/combination-sum-iv/solution/xi-wang-yong-yi-chong-gui-lu-gao-ding-bei-bao-wen-/



这道题就是上诉的第三种情况我们来想清楚为什么target放在外循环，因为此处，元素顺序是影响结果的，换句话说每一步我们都可以选择任意一个nums中的元素，那么这个时候，我们就不需要index这个维度了因为记录用的元素并没有必要，事实上我们需要的是所有元素，因此问题其实简化成一维问题状态只需要记录target元素大小

~~~java
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
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= target)
            res+= help(nums,target-nums[i]);
        }
        dp[target] = res;
        return res;
    }
}
//记忆化递归
~~~

自底向上

~~~java
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i = 0; i < target+1; i++){
            for(int j = 0; j < nums.length; j++){
                if(nums[j] <= i){
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
~~~

