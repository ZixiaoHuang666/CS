# LC 45&55 Jump game I II

![image-20210423133430563](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423133430563.png)

## Pre-thinking

这道题其实是很给人惊喜的一道题，初始看起来像是dp，但是一分析，跳的远的子问题暗中包含跳的近的，是一个可以取局部最优的情况，是做过的难得的贪心算法

我们一步一步给出思路，首先最容易想到的方法是递归，即如果可以跳3步，我们可以选择跳1,2,3步但是一看数据集大小，爆搜是不可能解决问题的，因此我们下一步想到记忆化搜索，我们使用数组存放既可以从前到后，也可以从后到前，一维数组就ok，但这道题，动态规划仍然不是最好的办法，因为每一步跳跃其实都存在最优解，这道题是贪心算法，

对于55题，如果，某一步所有跳跃的长度最长都为0 就说明跳不动了，永远沦陷

45 题就不用考虑这个记一下跳跃次数即可

~~~java
//45
class Solution {

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
~~~

~~~java
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
~~~

