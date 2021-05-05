# LC 137 Single number II

![image-20210430125352323](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210430125352323.png)

## PRE

这道题一看知道肯定是位运算的题，可是奈何不会做啊，HashMap我不说了运用额外空间，但是这道题肯定是希望位运算

我们只能看答案

https://leetcode-cn.com/problems/single-number-ii/solution/gong-shui-san-xie-yi-ti-san-jie-ha-xi-bi-fku8/

## Solution1 HashMap(skip)

## Solution 2 Bit Operation

![image-20210430134027130](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210430134027130.png)

简单来说该位都会出现三次，求模就好了可以适用于出现很多次的题

~~~java
class Solution {
    public int singleNumber(int[] nums) {
        int[] bit = new int[32];
        for(int a:nums){
            int b = 1;
            for(int i = 0; i < 32; i++) {
                bit[i] += (a&b)>0?1:0;//这里最开始错了不能直接加a&b
                b = b<<1;
            }
        }
        for(int i = 0; i < 32; i++){
            bit[i]%=3;
        }
        int sum = 0 ,base = 1;
        for(int i = 0; i < 32; i++){
            sum+= bit[i]*base;
            base *=2;
        return sum;
    }
}
~~~

## Debug

![image-20210430132730338](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210430132730338.png)

第一个错误在代码上标出来了我刚刚忘考虑负数了，最后一位应该是负的

~~~java
class Solution {
    public int singleNumber(int[] nums) {
        int[] bit = new int[32];
        for(int a:nums){
            int b = 1;
            for(int i = 0; i < 32; i++) {
                if(i == 31){
                    bit[i] += (a&b)<0?1:0;
                    b = b<<1;
                } 
                else{
                    bit[i] += (a&b)>0?1:0;
                    b = b<<1;
                }
            }
        }
        for(int i = 0; i < 32; i++){
            bit[i]%=3;
        }
        int sum = 0 ,base = 1;
        for(int i = 0; i < 32; i++){
            if(i == 31) sum += -bit[i]*base;
            else{
                sum+= bit[i]*base;
                base *=2;
            }
        }
        return sum;
    }
}
~~~

这是一个有符号数的情况取的时候都得注意符号位的应对

当然三叶姐那种倒着取的方法实际上更好，但会破坏原数组

~~~java

class Solution {
    public int singleNumber(int[] nums) {
        int[] cnt = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if (((x >> i) & 1) == 1) {
                    cnt[i]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((cnt[i] % 3 & 1) == 1) {
                ans += (1 << i);
            }
        }
        return ans;
    }
}

~~~

## Solution 3 DFA

![image-20210430143752398](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210430143752398.png)

![image-20210430143807145](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210430143807145.png)

~~~java

class Solution {
    public int singleNumber(int[] nums) {
        int one = 0, two = 0;
        for(int x : nums){
            one = one ^ x & ~two;
            two = two ^ x & ~one;
        }
        return one;
    }
}
。
~~~

//https://leetcode-cn.com/problems/single-number-ii/solution/dong-hua-tu-jie-yi-ding-neng-hui-by-yuan-bn22/

位运算题目总结要研究

