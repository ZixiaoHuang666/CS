# 4 [Median of Two Sorted Arrays](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/)

![image-20210420172042068](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420172042068.png)

---

## Pre-thinking

寻找两个排序数组的中位数，这道题初看题意还是比较好理解的

## Edge case

如果有一个数组为空或者长度为0，分别会有什么影响，两个呢？要考虑一个数组为0；

## Solution

最朴实的想法应该是重新排序，最暴力的想法，复杂度为NlogN，但是没有很好的利用好手头的工具

第二个想法是双指针移动从头到尾比较线性时间复杂度能搞定，但这种方法太具体的，完全没必要一个一个比，我们只需要找到中位数，二分查找的进阶升级应该是这道题的最优解法，

接下来分析代码的重复框架，如果数组长度为奇数，中位数只有一个，但是如果是偶数的话是两个值/2，我们可以转换为找到两个有序数组中第k大的元素

如果一个有序数组里找第k大直接返回就好了，但是两个制造了困难，每个数组我们需要使用两根指针标记位置，每次我们从每个数组找到还要找元素的一半(或者能取用的最多元素)，然后比较抛弃一半，在进行下一次递归，只有当一个数组用尽或者当剩下元素为1时我们可以找到中位数

现在照着这个思路我们来试试写代码,一下为初始代码

~~~java
    public int findMedianSortedArrays(int[] arr1, int[] arr2){
        //consider if one array is 0
        int m = arr1.length;
        int n = arr2.length;
        if((m+n)%2 != 0) return findKth(arr1,arr2,0,m-1,0,n-1,(m+n+1)/2);
        return (findKth(arr1,arr2,0,m-1,0,n-1,(m+n)/2)+
                findKth(arr1,arr2,0,m-1,0,n-1,(m+n+2)/2))/2;


    }
    private int findKth(int[] arr1,int[] arr2,int s1,int e1,int s2,int e2,int k){
        int element1 = e1 - s1 + 1;
        int element2 = e2 - s2 + 1;
        if(element1 > element2) return findKth(arr2,arr1,s2,e2,s1,e1,k);//first array small
        if(element1 == 0) return arr2[k-1];
        if(k == 1) return Math.min(arr1[s1],arr2[s2]);
        int use1 = Math.min(element1,k/2);//A中取的元素
        int use2 = Math.min(element2,k/2);
        int index1 = s1+ use1 -1, index2 = s2 + use2 -1;
        if(arr1[index1]  < arr2[index2]){//drop arr1
            return findKth(arr1,arr2,index1+1,e1,s2,e2,k-use1);
        }
        else{
            return findKth(arr1,arr2,s1,e1,index2+1,e2,k-use2);
        }
    }
~~~

## Debug

![image-20210420175646033](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420175646033.png)

除法忘了转换成double int永远只有整数，。最开始我的函数声明就错了，中位数可以是小数修改

![image-20210420180512595](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420180512595.png)

修改后出现第二个问题

发现注释行少了s2+k-1而不是直接从0开始

修正后的最终代码为

~~~java
class Solution {
    public double findMedianSortedArrays(int[] arr1, int[] arr2){
        //consider if one array is 0
        int m = arr1.length;
        int n = arr2.length;
        if((m+n)%2 != 0) return findKth(arr1,arr2,0,m-1,0,n-1,(m+n+1)/2);
        return ((findKth(arr1,arr2,0,m-1,0,n-1,(m+n)/2)+
                findKth(arr1,arr2,0,m-1,0,n-1,(m+n+2)/2))/2.0);


    }
    private int findKth(int[] arr1,int[] arr2,int s1,int e1,int s2,int e2,int k){
        int element1 = e1 - s1 + 1;
        int element2 = e2 - s2 + 1;
        if(element1 > element2) return findKth(arr2,arr1,s2,e2,s1,e1,k);//first array small
        //if(element1 == 0) return arr2[k-1];
        if(element1 == 0) return arr2[s2+k-1];
        if(k == 1) return Math.min(arr1[s1],arr2[s2]);
        int use1 = Math.min(element1,k/2);//A中取的元素
        int use2 = Math.min(element2,k/2);
        int index1 = s1+ use1 -1, index2 = s2 + use2 -1;
        if(arr1[index1]  < arr2[index2]){//drop arr1
            return findKth(arr1,arr2,index1+1,e1,s2,e2,k-use1);
        }
        else{
            return findKth(arr1,arr2,s1,e1,index2+1,e2,k-use2);
        }
    }
    
}
~~~

