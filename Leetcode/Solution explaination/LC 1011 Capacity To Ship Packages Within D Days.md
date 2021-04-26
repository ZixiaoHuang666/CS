# LC 1011 Capacity To Ship Packages Within D Days

![image-20210426110203963](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210426110203963.png)

## Pre-thinking

1. Orders matter
2. 拆分过程中，船的最小容量一定大于最大的单个包裹，上限应该就是一天送完然后模拟一下情况，最大的res是所有包裹重量相加，最小是最大包裹的重量，这个过程中二分搜索，可以结果数组中有true有false找到第一个true，还需要写一个判断函数判断这个方案是否可行，判断过程需要O(D)或者ON时间复杂度在NlogN，看一下数据集输入大小5*10^4应该可行

~~~java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0,max = 0;
        for(int weight:weights){
            sum+=weight;
            max = Math.max(weight,max);
        }
        int left = max,right = sum;
        int min = sum;
        while(left <= right){
            int mid = left+(right-left)/2;
            if(isValid(weights,D,mid)){
                min = Math.min(min,mid);
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }
        return min;


    }
    private boolean isValid(int[] weights, int D, int capacity){
        int day = 1;
        int index = 0;
        while(day <= D){
            int curCapcity = capacity;
            while(index < weights.length && curCapcity >= weights[index]){
                curCapcity -= weights[index];
                index++;
            }
            if(index == weights.length) return true;
            day++;
        }
        return false;
    }
}
~~~

## Debug

错了几个地方，第一个加强for循环忘了加int 写成了 for(weight:weights)，还有一个问题是忘了day++，然后的问题是curCapcity >= weights[index] 忘了加等于



## improve

其实可以直接给粗略边界1到1e8 省掉计算最大值的过程

这样时间也很快

~~~java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int left = 1,right = (int)1e8;
        int min = right;
        while(left <= right){
            int mid = left+(right-left)/2;
            if(isValid(weights,D,mid)){
                min = Math.min(min,mid);
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }
        return min;


    }
    private boolean isValid(int[] weights, int D, int capacity){
        int day = 1;
        int index = 0;
        while(day <= D){
            int curCapcity = capacity;
            while(index < weights.length && curCapcity >= weights[index]){
                curCapcity -= weights[index];
                index++;
            }
            if(index == weights.length) return true;
            day++;
        }
        return false;
    }
}
~~~



