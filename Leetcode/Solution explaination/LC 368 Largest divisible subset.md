# LC [368. Largest Divisible Subset](https://leetcode-cn.com/problems/largest-divisible-subset/)

![image-20210423110635612](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423110635612.png)

## Pre-asking

is the array unique? is there 0? all positive 

nums.length?  单个元素？

## Pre-thinking

s似乎只用处理元素为1的情况，因为需要求出具体的solution，排除动态规划，j是i的倍数或者i是j的倍数通过排序减少情况一定是后者是前者的情况，我内心是考虑暴力搜索的backTraking，但是感觉时间复杂度应该会超出，给的数据集是10的三次方，还有一个困难点我总觉得能很快找到是不是已选数字的公倍数，但是我除了暴力现在没有思考到，怎么简化这道题呢

## Hint

![image-20210423113539418](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423113539418.png)

小丑竟是我自己，确实我也很头疼啊，这个数量级backTracking感觉肯定无法解决刚何况他要求最大的子集，我不遍历完是无法知道最大子集是谁的，那这道题这么办

https://leetcode-cn.com/problems/largest-divisible-subset/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-0a3jc/

我们来接着当小丑好了，真想不出来，开始看题解

递归解不了，思考递推

好的第一个思路误区，如果要发现倍数只用当前和最后一个数是倍数，那如果这样的话我有点思路诶，如果是倍数且长度加一感觉我可以再用一个数据记录上一位，这样未来我就可以进行回查

接着看题解

![image-20210423115102796](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423115102796.png)

是的这就是我原来考虑回溯思路要说的，就是即使这个数确实是倍数但是可以取也可以不取

![image-20210423121429511](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423121429511.png)

![image-20210423121443395](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423121443395.png)

~~~java
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
~~~

写的代码一遍AC，其中记录值那里为了避免数组本身初始化的0，做了一点小处理。

## 总结

感觉这道题我学到的一个很重要的点是，如果是单个样例，动态规划也是可以还原的，按照数据量级排除BT是很好的思路，不过不可以因为要输出样例把DP一网打尽，少的情况还是可以的