# LC 60 Permutation Sequence

![image-20210423152840196](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423152840196.png)

---

## Pre-thinking

这道题看着是真好理解题意，两道中等题的组合，贼熟悉，全排列你用BT做过，next combination你也做过，但是把这两个结合在一起还是好头秃啊

这两个题能产生一种很萌的解法不断调用next combination，但是那显然很蠢，我感觉我要利用数字规律加快我们的寻找过程

比如3位数字一共六种最小是123,132,213,231,312,321, 1-2 1开头，3-4 2开头，5-6 3开头

4位数字一共24种 1234,通过大的位数就能判断出来头位数字

1,2,3,4 各是n-1！种 

~~~java
class Solution {
    public String getPermutation(int n, int k) {
        if(fac(n) < k) return "";
        boolean[] nums = new boolean[n+1];
        nums[0] = true;
        StringBuilder str = new StringBuilder();
        int digit = 1;
        while(digit != n+1){//这里错了，应该是n+1而不是n
            int batch = fac(n-digit);//6
            int batchsum = n + 1-digit;//4组
            int order = 1;//第几xiao
            while(k > batch){
                k -= batch;
                order++;
            }
            
            int curdigit = foundSmall(nums,order);
            str.append(curdigit);
            digit++;
            
        }
        return str.toString();

    }
    private int foundSmall(boolean[] nums, int order){        
           for(int i = 1; i < nums.length; i++){
               if(!nums[i]){
                   order--;
                   if(order == 0){
                       nums[i] = true;
                       return i;
                   } 
               }
           }
           return -1;     
    }
    private int fac(int a){
        if(a == 0) return 1;
        int sum = 1;
        while(a>=1){
            sum*=a;
            a--;
        }
        return sum;
    }
}
~~~

AC

