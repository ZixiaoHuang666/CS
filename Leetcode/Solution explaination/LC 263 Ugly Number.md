# LC 263  Ugly number

![image-20210429195733918](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210429195733918.png)

## PRE

​	我思路其实挺简单的，如果能被2或3或5整除，就除这个因子，看能不能变成+-1,当然要单独判断一下+-1和0

## Try

~~~java
class Solution {
    public boolean isUgly(int n) {
        if(n == 0 || n==1 || n == -1) return true;
        while(n!=1 && n!= -1){
            if(n%2 == 0||n%5 == 0 || n%3 == 0){
                if(n%5 == 0){
                    n = n/5;
                }
                if(n%3 == 0) n = n/3;
                if(n%2 == 0) n = n/2;
            }
            else return false;
        }
        return true;

    }
}
~~~

## Debug

![image-20210429195932613](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210429195932613.png)

发现读题错误，负数和0都不能是丑数

## Improve LC 264

![image-20210429204309379](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210429204309379.png)

## Pre-thinking

最蠢的方法肯定是直接暴力，我们来想想有没有什么规律，最小的是1，除开他以后，2,3,5的因子在脑海里想想树试试

2 3 5 

2,3,4,5,6,8,9

