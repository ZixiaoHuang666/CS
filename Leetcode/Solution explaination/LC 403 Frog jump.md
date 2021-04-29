# LC 403 Frog Jump

![](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210429102941122.png)

## Pre-thinking

题意分析：我们有变量 上次跳跃长度k，可选择这次跳跃的长度k-1,k,k+1，我们需要跳上下一块石头，这道题一思考肯定递归爆搜是解决不了的，明确是动态规划

状态，index，k，第一级楼梯为1，跳到第二级楼梯可以为2，k<楼梯个数是肯定的（想办法精确计算一下）

返回第一级楼梯 k=1 是否为true，数据集是2000， On平方应该二维dp是可行的，

初始化的话dp[最后一级楼梯] [] = true 

转移方程，dp[curindex] [k] = dp[curindex + k-1] [k-1] || dp[currentIndex +K] [K] || dp[currentindex +k+1] [k+1]

如果curIndx > 最后一级直接false，感觉递归好写点

但是在写的过程中困难重重即使多用了一个HashSet记录，但是对细微处到底是应该Boolean还是boolean能无法确定因为感觉很迷惑，不过最后还是决定Boolean，如果跳到的index在水中，直接false

## Try

~~~java
class Solution {
    Boolean[][] dp;
    HashSet<Integer> set = new HashSet<>();
    public boolean canCross(int[] stones) {
        //18
        int a = 0, k = 1;
        while(a <= stones[stones.length-1]){
            k++;
            a = a+k;
        }
        //最大就是k-1，下标代表速度， 不用0
        int lastStonenum = stones[stones.length-1];
        dp = new Boolean[lastStonenum+1][k];//石头编号
        //initial
        for(int i = 0; i<k;i++){
            dp[lastStonenum][i] = true;
        }
        for(int i = 0; i < stones.length; i++){
            set.add(stones[i]);
        }

        return help(0,1);
    }
    private boolean help(int index,int k){
        if(!set.contains(index)) return false;
        else if(dp[index][k] != null) return dp[index][k];
        else{
            if(k>1)
            dp[index][k] = help(index+k,k)||help(index+k+1,k+1)||help(index+k-1,k-1);
            else if(k == 1){
                dp[index][k] = help(index+k,k)||help(index+k+1,k+1);
            }
            return dp[index][k];
        }
    }
}
~~~

## Debug

![image-20210429114104669](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210429114104669.png)

忘了限制第一跳只能是1

![image-20210429114610110](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210429114610110.png)

该死的加1溢出了，数组最大长度也只能开Integer.MAX_VALUE,这样跑是不科学的，我只要有一个石头坐标很大就会导致开一个巨大的数组，这怎么办呢，查了一下java数组最多是int的最大值，这样动态规划显然是不行的，这种情况可能需要灵活的结构，不再用数组存放，我们用Hashmap来记录，一瞬间思路活了，我们发现最开始计算k的过程都是一个以2的31次方平方量级的，这是不可能实现的，再用一个小技巧 index：k，这样就可以变成一个String，boolean记录的哈希表，居然AC了开心，改了结构以后okk

~~~java
class Solution {
    //index:k
    HashMap<String,Boolean> map = new HashMap<>();
    HashSet<Integer> set = new HashSet<>();
    int lastStonenum;
    public boolean canCross(int[] stones) {
        //18
        //最大就是k-1，下标代表速度， 不用0
        lastStonenum = stones[stones.length-1];
        //dp = new Boolean[lastStonenum+1][k];//石头编号
        //initial

        for(int i = 0; i < stones.length; i++){
            set.add(stones[i]);
        }

        return help(0,1);
    }
    private boolean help(int index,int k){
        String temp = pack(index,k);
        if(index == lastStonenum)return true;
        if(!set.contains(index)) return false;
        //else if(dp[index][k] != null) return dp[index][k];
        else if(map.containsKey(temp)) return map.get(temp);
        else{
            if(index == 0) map.put(temp,help(index+1,1)); 
            else if(k>1)
            map.put(temp,help(index+k,k)||help(index+k+1,k+1)||help(index+k-1,k-1));
            else if(k == 1){
            map.put(temp,help(index+k,k)||help(index+k+1,k+1));
            }
            return map.get(temp);
        }
    }
    private String pack(int index,int k){
        return String.valueOf(index)+":"+String.valueOf(k);
    }
}
~~~







