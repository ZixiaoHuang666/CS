# LC 5 Longest Palindrome Substring

![image-20210420194512342](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420194512342.png)

---

题目还是比较好理解的，找到字符串中最长的回文串，题目要求分成两步首先得判断一个串是不是回文串，回文很容易联想动态规划，有思路了

动态规划先想四个地方：

state：二维从i到j

initial：空串是不是回文串？ 所以ii 可以初始化为true

result：dp[0] [n]

Transfer function: if  i和j对应字符相同 &&  (j-i <=2 || (i+1,j-1)) true i倒着 

## Edge case

给的字符串是不是空或者为0；

## Try

~~~java
class Solution{
    public String longestPalindrome(String s){
        if(s == null || s.length() == 0)return null;
        int n = s.length();
        char[] arr = s.toCharArray();
        boolean[][] dp = new boolean[n+1][n+1];
        int max = 1;
        int startIndex = 0,endIndex = 0;
        for(int i = 0; i <= n; i++){
            dp[i][i] = true;
        }
        for(int i = n-1; i>0;i--){
            for(int j = i+1; j <= n; j++){
                if(arr[i-1] == arr[j-1] && ((j-i)<=2 || dp[i+1][j-1])){
                    dp[i][j] = true;
                    if(j-i+1 > max){
                        max = j-i+1;
                        startIndex = i-1;
                        endIndex = j-1;
                    }
                }
            }
        }
        return s.substring(startIndex,endIndex+1);
    }
}
~~~

一次AC 啊这那就没有Debug环节啦