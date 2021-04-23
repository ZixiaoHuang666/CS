package LC.s400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        //dp i,代表到这一位的最长子序列，g数组记录回溯
        //初始化为1，对这之前的位数如果能整除，试着相加
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] g = new int[nums.length];
        Arrays.fill(dp,1);
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    g[i] = j+1;
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        int maxIndex = 0;
        for(int i = 1; i < dp.length;i++) {
            if(dp[i] > dp[maxIndex]){
                maxIndex = i;
            }
        }
        while(maxIndex != -1){
            res.add(nums[maxIndex]);
            maxIndex = g[maxIndex]-1;

        }
        return res;
    }
}
