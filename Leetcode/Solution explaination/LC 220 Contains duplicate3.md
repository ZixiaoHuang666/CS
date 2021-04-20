![image-20210417131737175](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210417131737175.png)

## preAsking 

1. input element's  size, 最大值，如果最大值能取到int的最大最小值，在进行减操作时可能会发生溢出
2. 数据集大小10的4次方基本说明采用的算法复杂度低于O(N^2)，考虑nlogn 和线性时间
3. 思考edge case：如果k<=nums.length? 如果nums == null/nums.length = 0;

## thinking

最暴力的思路应该是，对于每个元素，找出前后相邻k的区间，[max(0,i-k),i)检查是否有大小在值[u-t,u+t]的值

可以先检索区间，在检索符合的值，两层循环O(N^2) ,这里有一个技巧永远只考虑一边的情况就不会重复因为后面还会重复访问到后面的元素所以不用同时查找前后。

另一种暴力思路也可以先划分出k+1个元素的区间，暴力寻找其中是否有相减小于t的值，只能先排序后比较

时间复杂度更高O(N^2*LOGN)

## improve1 利用更为便捷的红黑树结构

怎么改进呢，外层循环的遍历似乎很难改进，必须遍历所有元素，内层循环我们主要是为了找到一个有序的值，即小于u的最大值，和大于u的最小值，即最接近u的，第一个思路是先排序再二分查找，但排序复杂度很高甚至还不如直接遍历记录最大最小值，在比较他们是否落在[u-t,u+t]，把O(N)更改成O(logN)，思考有没有边插入边排序，同时查找复杂度也是O（logN）时间内的，因为这种题我们根本不需要全部排序，只需要找到目标范围内的有效值。因此结构第一个可以想到的是树结构，用红黑树去做，HashSet只对单个值很有作用，对于范围搜索无能为力，我们确定数据结构为TreeSet,这种想法的实现和编译时可能遇到的错误总结如下

~~~java
public class LC220_important {
    //Date input: long
    //edge cases: k >= nums.length; k = 0??nums.length = 0? nums == null? t range?
    //thought:[max(0,i-k),i) search value [nums[i]-t,nums[i]+t]
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0) return false;
        TreeSet<Long> set = new TreeSet<>();
        set.add((long)nums[0]);
        for(int i = 1; i < nums.length; i++){
            long u = nums[i]*1L;
            //long maxSmall = set.floor(u);//小技巧
            //long minLarge = set.ceiling(u);//报错可能会产生空指针异常,有可能会返回null
            Long maxSmall = set.floor(u);
            Long minLarge = set.ceiling(u);
            if((maxSmall !=null && u - maxSmall <= t) || (minLarge != null && minLarge - u <= t))
                return true;
            set.add(u);
            if(i-k>=0)
            set.remove(nums[i-k]*1L);
        }
        //only one element
        return false;
    }
}
//总结，edge case最开始少考虑了k = 0，并且设计基础类型对象的转换，从Long对象拆箱的过程中可能会出现空指针调用错误，即对象是NULL无法拆箱，容易出错，同时调用了TreeSet,floor(),ceiling,*1L多种写法，还有一个要维护的是注意要进行删除，滑动窗口删除最开始的值非常重要
注意TreeSet重复的元素不会重复存入，对这道题的没有影响
~~~

## improve2 桶排序

上述解法无法做到线性的原因是：我们需要在大小为 k 的滑动窗口所在的「有序集合」中找到与 u 接近的数。

如果我们能够将 k个数字分到 k 个桶的话，那么我们就能 O(1) 的复杂度确定是否有 [u - t, u + t][u−t,u+t] 的数字（检查目标桶是否有元素）。

这个思路可以由生日解释，我们要找到生日相差三十天的同学，没必要全部排序，我们可以先按照月份划分，如果两个同学生日在一个月是不是肯定找到，第二种情况是相邻月份，比如四月底和五月初，检查相邻月份的日子相减是否小于等于30天，这其实也是一种排序的退化，我们不需要排序所有元素，可以通过一定的数据设计使得在更小的时间复杂度内找到相应元素

难点1：如何拿到桶的编号？

比如题目中如果t = 5 ,我们可以把每5个值看成一样，0-5是一个桶，6-11是一个桶，如果查找到一样的编号就说明肯定找到相邻元素，我们只维护k个桶，大于k时删除老桶，这样Hash表记录可以把时间复杂度降到O(1)，巧妙地地方在于我们之前说过对于范围查找hash表并不在行，这种方法美妙的做了一个映射把在一个范围内的数据映射成一个index，实乃鬼灭第17集的效果，这里要提示，负数-1到-6也该是一个桶转换规则不同。

```java
class Solution {
    long size;
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        size = t + 1L;
        for (int i = 0; i < n; i++) {
            long u = nums[i] * 1L;
            long idx = getIdx(u);
            // 目标桶已存在（桶不为空），说明前面已有 [u - t, u + t] 范围的数字
            if (map.containsKey(idx)) return true;
            // 检查相邻的桶
            long l = idx - 1, r = idx + 1;
            if (map.containsKey(l) && u - map.get(l) <= t) return true;
            if (map.containsKey(r) && map.get(r) - u <= t) return true;
            // 建立目标桶
            map.put(idx, u);
            // 移除下标范围不在 [max(0, i - k), i) 内的桶
            if (i >= k) map.remove(getIdx(nums[i - k] * 1L));
        }
        return false;
    }
    long getIdx(long u) {
        return u >= 0 ? u / size : (u + 1) / size - 1;
    }
}
```

这道题的思路是数据的简化与胜利，从不用空间记录需要暴力的检查比较的平方级别，到采用增，删，查为logN的树结构，到O(1)的map。

## 后续

可以考虑846题





