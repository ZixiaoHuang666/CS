# LC 633 [ Sum of Square Numbers](https://leetcode-cn.com/problems/sum-of-square-numbers/)

![image-20210428211700225](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210428211700225.png)

## Pre-thinking

这道题最先思考有两维，a和b，有一个target c，我们利用两数之和的思路，枚举一个维度，再空间存贮一个维度，可以想出On 复杂度的算法，但是发现数量级为2的31次方不可能过，这么一说这道题还真有点像两数之和，给我们一个从小到大的平方数组，让我们找到其中两个指针的和为target，logN想到二分搜素

但是很难办有点迷茫，很难二分搜索，如果一个从左边一个从右边那也是ON，诶突然发现此On 不是全部数只是平方数，那有戏，如果这个方法是对的说明我被二分欺骗了，我甚至想对a,b分别二分搜索了，但是这样是不单调不可能的，因为a变大，b变小，还是a变小，b变大很难说，没法二分搜索，如果但用一个二分搜索枚举出一个值，也无法进一步更新指针，无法知道适合的值究竟在左边还是右边不具有大小关系，因此我们试试双指针

![image-20210428214002341](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210428214002341.png)

我分析了一下觉得按理说不应该超出的，因此我想到了一个点出现了溢出有可能，溢出后变成负数了

我们得把i*i变成long

![image-20210428214636508](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210428214636508.png)

但是超过的人很少还是很慢，有点不理解，因为我这样复杂度应该实际是Log2N了，为什么还是这么慢

看一下题解

原来有一种方法是数学

![image-20210428215440333](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210428215440333.png)

![image-20210428215459107](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210428215459107.png)

感觉不需要掌握