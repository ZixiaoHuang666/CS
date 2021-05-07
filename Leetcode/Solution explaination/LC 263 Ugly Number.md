# LC 263  Ugly number

![image-20210429195733918](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210429195733918.png)

## PRE

​	我思路其实挺简单的，如果能被2或3或5整除，就除这个因子，看能不能变成+-1,当然要单独判断一下+-1和0

## Try

~~~java
class Solution {
    public boolean isUgly(int n) {
        if(n == 0 || n==1 || n == -1) return true;
        while(n!=1 && n!= -1){
            if(n%2 == 0||n%5 == 0 || n%3 == 0){
                if(n%5 == 0){
                    n = n/5;
                }
                if(n%3 == 0) n = n/3;
                if(n%2 == 0) n = n/2;
            }
            else return false;
        }
        return true;

    }
}
~~~

## Debug

![image-20210429195932613](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210429195932613.png)

发现读题错误，负数和0都不能是丑数

## Improve LC 264

![image-20210429204309379](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210429204309379.png)

## Pre-thinking

最蠢的方法肯定是直接暴力，我们来想想有没有什么规律，最小的是1，除开他以后，2,3,5的因子在脑海里想想树试试

2 3 5 

2,3,4,5,6,8,9

思路是每一个数都有乘235三种情况，我们记录三个指针每次添加最小的进去

~~~java
class Solution {
    public int nthUglyNumber(int n) {
        // ans 用作存储已有丑数（从下标 1 开始存储，第一个丑数为 1）
        int[] ans = new int[n + 1];
        ans[1] = 1;
        // 由于三个有序序列都是由「已有丑数」*「质因数」而来
        // i2、i3 和 i5 分别代表三个有序序列当前使用到哪一位「已有丑数」下标（起始都指向 1）
        for (int i2 = 1, i3 = 1, i5 = 1, idx = 2; idx <= n; idx++) {
            // 由 ans[iX] * X 可得当前有序序列指向哪一位
            int a = ans[i2] * 2, b = ans[i3] * 3, c = ans[i5] * 5;
            // 将三个有序序列中的最小一位存入「已有丑数」序列，并将其下标后移
            int min = Math.min(a, Math.min(b, c));
            // 由于可能不同有序序列之间产生相同丑数，因此只要一样的丑数就跳过（不能使用 else if ）
            if (min == a) i2++; 
            if (min == b) i3++;
            if (min == c) i5++;
            ans[idx] = min;
        }
        return ans[n];
    }
}
~~~

## 变式 [313. Super Ugly Number](https://leetcode-cn.com/problems/super-ugly-number/)

![image-20210507123804809](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210507123804809.png)

factor变成随意个数了，需要一个数组来存储index

~~~java
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] res = new int[n+1];
        res[1] = 1;
        int[] index = new int[primes.length];
        Arrays.fill(index,1);
        for(int i = 2; i < n+1; i++){
            int min = Integer.MAX_VALUE;
            for(int id = 0; id < index.length; id++){
                min = Math.min(min,res[index[id]]*primes[id]);
            }
            res[i] = min;
            for(int j = 0; j < index.length; j++){
                if(res[index[j]]*primes[j] == min){
                    index[j]++;
                }
            }
        }
        return res[n];                        
    }
}
~~~

## 变式 [1201. Ugly Number III](https://leetcode-cn.com/problems/ugly-number-iii/)

![image-20210507123724415](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210507123724415.png)

注意这道题重新定义了丑数，只要能被整除就算是丑数，正因为这样，按照之前丑数的做法需要O(N)的枚举复杂度但是这种解法已经不再是最优的了，我们可以把算法复杂度提升到logN级别因此数据集大小急剧扩大，这道题应该怎么做呢，另外1不再是丑数。

想到二分，但是有一个麻烦的事情如果对235单独二分怎么知道到底是第几个呢，返回第n个条件怎么办

![image-20210507142403234](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210507142403234.png)

![image-20210507142947379](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210507142947379.png)

很难这道题，是878困难的升级版

