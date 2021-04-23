# LC 363 Max sum of rectangle No larger than k

![](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210422120716876.png)																																																																																																																						

## Pre-thinking

题意选出一个小于等于Target的最大矩形和

Edge case: 给的矩形大小等于0？ k是正数负数？

初始想找不到什么漂亮方法，我们从最暴力的开始想，先统计出给我们的矩形大小的长度和高度，比如5,4

我们先排除0的情况，长有五种宽有4种 o(MN),但是怎么计算每种矩形的大小也是个麻烦重复运算肯定是很蠢得，想到动态规划记录每个算出来的矩形面积，但是怎么记录比较高效呢，感觉还是有好多种。数字毫无规律矩形变大变小数字不一定相应变大变小，只能想到暴力搜索

但是种类也太多了小小的2*3都有18种至少卡死了，暴力搜索O(M^2N^2)，因为矩形是通过左上点坐标，和右下点坐标确定的，x，y各被使用了两次，相当于是个四维问题。

我最开始想到的记录从原点出发的各个矩形的面积，可是怎么递推就活生生卡住了，

## Hint

不得不看题解啊555，

[三叶](https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/solution/gong-shui-san-xie-you-hua-mei-ju-de-ji-b-dh8s/)

因为这是一个高维问题，涉及(x1,y1),(x2,y2)，最暴力的想法应该首先得到四维动态规划，状态转移方程应该是能计算的暴力搜索，遇到高维问题我们想要减少复杂度，必要要思考如何降维，举例比如两数之和是两个维度的选择问题，我们通过记录固定记录一个维度，枚举一个维度，来进一步优化，同样的这道题对应的四条边应该如何进行降维，如果四条边全部枚举确实会要人的命让我们看看有没有简捷的办法，这道题得联想到最大子序列和，我们用两个木板固定左右，这样就变成了一个在数组里面寻找小于k的最大子序列和问题

![image-20210422145844580](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210422145844580.png)

https://blog.csdn.net/u010900754/article/details/60457594

小于k的最大子序列和

在一个只有正数的有序数组里面求小于某个数的最大子序列和的话，思路可以是先算出大家的前缀和，枚举左边，二分查找右边因为满足有序O(NlogN) 

当有负数的税后不再满足单调不可能二分查找，怎么把复杂度从暴力的n方降下来呢

不过后来在网上看到一个更吊的思路，优化了查找过程。
要求sum(i,j)即从i到j的和，可以sum(0,j) - sum(0,i-1)。所以sum(0,j) - sum(0,i-1)<=k。

这样的话，我们用一个遍历，求出sum(0,i)，并且把所有的值存入set，然后每一次都求出一个下界，即sum(0,i)-k，然后在之前的结果中找大于等于这个界的最小值，如果存在，就是一个候选值，然后再把sum(0,i)也放入set。

如果不使用一定的数据结构，以上算法还是On2的。但是如果set的查找使用了特殊数据结构比如平衡二叉搜索树这种的，那么查找后继或者前驱就是logn级别了。这就是优化的一个方法。在java中，使用Treeset结构即可。所以下面就是一个nlogn的算法：


~~~java

	public int maxSumNoLargerThan(int[] array, int k){
		int sum = 0, max = Integer.MIN_VALUE;
		TreeSet<Integer> set = new TreeSet<>();
		set.add(0);
		for(int i = 0; i < array.length; i++){
			sum += array[i];
			Integer min = set.ceiling(sum - k);
			if(min != null)
				max = Math.max(max, sum - min);
			set.add(sum);
		}
		return max;
    }


~~~

完整代码

```java
package LC.s400;

import java.util.TreeSet;

public class LC363 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
        // O(cols ^ 2 * rows)
        for (int l = 0; l < cols; l++) { // 枚举左边界
            int[] rowSum = new int[rows]; // 左边界改变才算区域的重新开始
            for (int r = l; r < cols; r++) { // 枚举右边界
                for (int i = 0; i < rows; i++) { // 按每一行累计到 dp
                    rowSum[i] += matrix[i][r];
                }

                // 求 rowSum 连续子数组 的 和
                // 和 尽量大，但不大于 k
                max = Math.max(max, dpmax(rowSum, k));
                if(max == k) return max;
            }
        }
        return max;
    }
    public int dpmax(int[] array, int k){
        int sum = 0, max = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        for (int value : array) {
            sum += value;
            Integer min = set.ceiling(sum - k);
            if (min != null)
                max = Math.max(max, sum - min);
            if(max == k) return max;
            set.add(sum);
        }
        return max;
    }
}
```



