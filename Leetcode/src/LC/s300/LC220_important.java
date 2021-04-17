package LC.s300;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 Given an integer array nums and two integers k and t,
 return true if there are two distinct indices i and j in the array
 such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 */
public class LC220_important {
    //Date input: long
    //edge cases: k >= nums.length; k = 0??nums.length = 0? nums == null? t range?
    //thought:[max(0,i-k),i) search value [nums[i]-t,nums[i]+t]
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0 || k == 0) return false;//一开始忘了判断k==0报错
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
            if(i-k>=0)//这两行一开始没写报错
            set.remove(nums[i-k]*1L);
        }
        //only one element
        return false;
    }

}
class Solution2{
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
