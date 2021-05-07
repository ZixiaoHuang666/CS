# LC 1438 longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit

![image-20210423173236349](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423173236349.png)

## Pre-thinking

这道题，首先是怎么找到子数组，然后保证子数组内任意元素组合绝对值小于target，子数组的个数是一个等差数列，n方级别，枚举所有组合又是n方怎么化简？

首先如果选出子数组可以通过排序从n方-nlogn

怎么找子数组呢，只有两种情况要么，是从自己开始另起炉灶，要么是承接上一个元素，但是子问题不在独立，因为要考虑新进元素可能会破坏老元素之间的关系，不再满足，再加进去之前先判断是否能和老元素相处融洽这个方法是可以的，因为老的长度是连在一起的不可能能够变得更长，仔细一想发现还是不行，因为到这一位最长也可能比之前的位数少，只和几个老元素融洽这种情况被忽略了，子问题不独立，应该是不能使用dp的，怎么办呢

数据集大小10的5次方，只能O(N) O(NLOGN)

暴力解决必然是不可能的，涉及到多个数，并且是连续，并要要求时间复杂度非常低，可以基本得出结论滑动，那怎么滑动呢，我们发现我们可以通过维护一个最大值和最小值，这样就能直接判断新加入的元素是否在这个范围内，如果在范围内右指针贪心扩张，如果在范围外，左指针回缩，重置最大最小值，直到可以加入右边元素，如果所有元素收完了重置最大最小值为Integer.max min O(n)时间

但渐渐发现了一个问题当左边元素移动的时候怎么能快速更新最大最小值呢，我们所需要的的其实是窗口内元素的最大值 - limit和最小值+limit之间的元素，所以想要找到最大最小值我们需要一个能够快速删除，同时给出大小排序的元素，TreeSet

~~~java
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
        int start = 0;
        int res = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for(int end = 0; end < nums.length; end++) { 
            if(set.isEmpty()){
                min = Integer.MIN_VALUE;
                max = Integer.MAX_VALUE;
            }
            else{  
                max = set.first()+limit;
                min = set.last()-limit;
            }   
                while((nums[end] > max || nums[end] < min)){
                    if(start == end-1){
                        set.remove(nums[start]);
                        start++;
                        min = Integer.MIN_VALUE;
                        max = Integer.MAX_VALUE;
                        break;
                    }
                    else{
                        set.remove(nums[start]);
                        max = set.first()+limit;
                        min = set.last()-limit;
                        start++;
                    }
                }
            
            set.add(nums[end]);
            res = Math.max(res,end-start+1);
        }
        return res;

    }
}
~~~



Debug过程发现问题，TreeSet致命缺点同样的元素只会存储一个

这道题得用TreeMAP

~~~java
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
        int start = 0;
        int res = 0;
        TreeMap<Integer,Integer> set = new TreeMap<>();
        for(int end = 0; end < nums.length; end++) { 
            if(set.isEmpty()){
                min = Integer.MIN_VALUE;
                max = Integer.MAX_VALUE;
            }
            else{  
                max = set.firstKey()+limit;
                min = set.lastKey()-limit;
            }   
                while((nums[end] > max || nums[end] < min)){
                    if(start == end-1){
                        if(set.get(nums[start]) == 1){
                            set.remove(nums[start]);
                        }
                        start++;
                        min = Integer.MIN_VALUE;
                        max = Integer.MAX_VALUE;
                        break;
                    }
                    else{
                        if(set.get(nums[start]) == 1){
                            set.remove(nums[start]);
                        }
                        else
                            set.put(nums[start],set.get(nums[start])-1);
                        max = set.firstKey()+limit;
                        min = set.lastKey()-limit;
                        start++;
                    }
                }
            
            set.put(nums[end],set.getOrDefault(nums[end],0)+1);
            res = Math.max(res,end-start+1);
        }
        return res;
    }
}
~~~

AC 复杂度O(NlogN) 但这并不是最优解法，怎么快速求得窗口里面的最大值最小值呢



官方题解也可以变成看是否TreeMap里面最大值-最小值在limit内这个思路

附上单调队列解法，烦的是需要两个队列怕是，因为需要最大值和最小值

~~~java
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> queMax = new LinkedList<Integer>();
        Deque<Integer> queMin = new LinkedList<Integer>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            while (!queMax.isEmpty() && queMax.peekLast() < nums[right]) {
                queMax.pollLast();
            }
            while (!queMin.isEmpty() && queMin.peekLast() > nums[right]) {
                queMin.pollLast();
            }
            queMax.offerLast(nums[right]);
            queMin.offerLast(nums[right]);
            while (!queMax.isEmpty() && !queMin.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit) {
                if (nums[left] == queMin.peekFirst()) {
                    queMin.pollFirst();
                }
                if (nums[left] == queMax.peekFirst()) {
                    queMax.pollFirst();
                }
                left++;
            }
            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }
}


~~~

复杂度是O(N) 我们来想想原理，相当于维护了两个单调队列，如果一个最大队列，一个最小队列，当一个数进来以后如果他比之前的最大值大，可以肯定在窗口滑动到他这里之前，最大值都只可能是他，之前的最大值已经没有存的必要了，可以直接删除，但是如果后面遇到比他小的元素还是要存的万一滑动到这里了呢，所以最大队列的头即是，当前窗口中的最大值，最小队列同理

~~~java
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        LinkedList<Integer> minQueue = new LinkedList<>();
        LinkedList<Integer> maxQueue = new LinkedList<>();
        int left = 0,right = 0;
        int res = 0;
        while(right < nums.length){
            int cur = nums[right];
            while(!minQueue.isEmpty() && minQueue.peekLast() > cur){
                minQueue.pollLast();
            }
            while(!maxQueue.isEmpty() && maxQueue.peekLast() < cur){
                maxQueue.pollLast();
            }
            minQueue.offer(cur);
            maxQueue.offer(cur);
            while(!maxQueue.isEmpty() && maxQueue.peekFirst() - minQueue.peekFirst() > limit){
                int del = nums[left];
                if(del == minQueue.peekFirst()){
                    minQueue.pollFirst();
                }
                if(del == maxQueue.peekFirst()){
                    maxQueue.pollFirst();
                }
                left++;
            }
            res = Math.max(res,right-left+1);
            right++;
        }
        return res;
    }
}
~~~

自己写的AC

![image-20210505160809942](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210505160809942.png)

java优先队列删除的话只能删除一个实例但是删除复杂度O(logN),这种辅助队列的方法是更好的，

三种方法1有序集合2优先队列3相当于辅助队列，可以仔细思考辅助栈那道题的实现

