# LC 9 Palindrome Number



![image-20210420155553924](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420155553924.png)

## pre-thinking

---

首先看到这道题判断是否是回文，先想特殊情况，如果x是负数，如果x末位是0，且不是0 直接是false

然后想想怎么翻转，还要防止溢出，最好的办法就是只反转一半

如果是偶数1111,11 - 11

11211 -11-112

## Solution

~~~java
class Solution {
    public boolean isPalindrome(int x) {
        if(x == 0) return true;
        if(x<0 || x%10 == 0) return false;
        int reverseNumber = 0;
        //
        while(reverseNumber < x){
            int digit = x%10;
            reverseNumber = reverseNumber*10+digit;
            x = x/10;
        }
        if(x == reverseNumber || x == reverseNumber/10){
            return true;
        }
        return false;
    }
}
~~~

