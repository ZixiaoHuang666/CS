# LC 35 Search insert Position

![image-20210421170919016](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210421170919016.png)

## Pre-thinking

这道题一看二分搜索，edge case是数组为空，如果找到返回下标，如果没找到那就返回插入位置

更具体化的来说，如果没有找到，我们要返回比它大的第一个元素的下标

## Try

~~~java
class Solution{
    public int searchInsert(int[] arr, int t){
        int left = 0,right = arr.length-1;
        int minMax = arr.length;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(arr[mid] == t){
                return mid;
            }
            else if(arr[mid] < t){
                left = mid+1;
            }
            else{
                minMax = Math.min(minMax,mid);
                right = mid-1;
            }
        }
        return minMax;        
    }
}
~~~

AC 问题不大

Solution 二分变式

~~~java
class Solution{
    public int searchInsert(int[] arr, int t){
        int left = 0,right = arr.length;  //数组长5 0/5 23-5 4
        while(left < right){
            int mid = left + (right - left)/2;
            if(arr[mid] == t){
                return mid;
            }
            else if(arr[mid] < t){
                left = mid+1;
            }
            else{
                right = mid;//因为left<right 永不可能在这里死循环
            }
        }
        return right;        
    }
}
~~~

1. 为什么不能取小于等于，这样的右边界的点将永远无法取到