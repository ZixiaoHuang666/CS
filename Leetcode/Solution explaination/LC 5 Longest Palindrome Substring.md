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

啊我回来了这道题时间空间复杂度O(n^2)，竟然有比它更好的方法，类似的方法还有中心扩散法和 Manacher 算法

## Improve 中心扩散法

外循环选择一个点作为中心，向两边进行扩散，记录最长的子串，分为奇数和偶数情况讨论，算法复杂度O(n^2)，但空间复杂度降为O(1)

Try

~~~java
class Solution{
    public String longestPalindrome(String s){
        if(s == null || s.length() == 0) return null;
        int n = s.length();
        int max = 1;
        int startIndex = 0,endIndex = 0;
        char[] arr = s.toCharArray();
        for(int i = 0; i < n; i++){
            int[] temp = getString(arr,i);
           // if(temp[1] - temp[0] > max){//问题1
            if(temp[1] - temp[0]+ 1 > max){
                max = temp[1]-temp[0];
                startIndex = temp[0];
                endIndex = temp[1];
            }
        }
        return s.substring(startIndex,endIndex+1);        
    }
    private int[] getString(char[] arr, int i){
        int[] max = new int[2];
        //奇数 
        int left = i-1,right = i+1;
        while(left >=0 && right < arr.length && arr[left] == arr[right]){
            max[0] = left;
            max[1] = right;
            left--;
            right++;
        }
        int maxS = max[1] - max[0];
        //even
        left= i-1;
        right = i;
        while(left >=0 && right < arr.length && arr[left] == arr[right]){
            if(right - left > maxS){
                maxS = right-left;
                max[0] = left;
                max[1] = right;
               // left--;  问题二造成死循环
               // right++;
            }
                left--;
                right++;
        }
        return max;
    }
}

~~~

### Debug

![image-20210420205118860](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420205118860.png)

第一个问题在于跟max比较的时候忘了加1；

![image-20210420205723605](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420205723605.png)

第二个问题在于把left-- right++放到了循环里面

![image-20210420210356078](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420210356078.png)

比动态规划居然快得多

## Manacher 算法

这是一个比较冷门的算法，使用范围也比较单一，只能用于解决**「回文串」**问题。

Manacher 确实是*回文串**问题的最优解。

但即使是 LeetCode 上所有关于「回文串」的问题，没有一道是必须通过 的 Manacher 算法才能 AC。

因此我这里直接给解决方案（可以直接当做模板来使用），而不再讨论算法的具体实现原理。

Manacher 算法较长，为了避免回文串长度奇偶问题的分情况讨论，我会对原字符进行处理，在边界和字符之间插入占位符。

使用了这样的技巧之后，**「当非占位字符作为回文串的中心时，对应了回文串长度为奇数的情况；当占位字符作为回文串的中心时，对应了回文串长度为偶数的情况。」**。

举个例子：

![image-20210420210705550](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420210705550.png)

~~~java

class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        char[] chars = manacherString(s);
        int n = chars.length;
        int[] pArr = new int[n];
        int C = -1, R = -1, pos = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            pArr[i] = i < R ? Math.min(pArr[C * 2 - i], R - i) : 1;
            while (i + pArr[i] < n && i - pArr[i] > -1) {
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (pArr[i] > max) {
                max = pArr[i];
                pos = i;
            }
        }
        int offset = pArr[pos];
        StringBuilder sb = new StringBuilder();
        for (int i = pos - offset + 1; i <= pos + offset - 1; i++) {
            if (chars[i] != '#') sb.append(chars[i]);
        }
        return sb.toString();
    }
    char[] manacherString(String s) {
        char[] chars = new char[s.length() * 2 + 1];
        for (int i = 0, idx = 0; i < chars.length; i++) {
            chars[i] = (i & 1) == 0 ? '#' : s.charAt(idx++);
        }
        return chars;
    }
}
~~~

O(N) O(1)

![image-20210420211443103](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420211443103.png)