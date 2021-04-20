# LC10 Regular Expression Matching

![image-20210420135547102](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420135547102.png)

## Edge cases

只要不为null 就可以放在dp数组中想，这种题上手已经可以基本确定题目是动态规划，还有一个特殊情况是当输入中是否存在非字母和这两个符号的存在，也不能两个** 要合法。

## preThinking

既然判断是动态规划，下一步我们需要思考有哪些状态需要保存，结果应该返回什么状态，转移方程，以及初始状态。

State，感觉应该是两个字符串的匹配情况，即dp[m+1] [n+1]

代表一号字符串出m个字母，2号字符串出n个字母时他们的匹配情况，结果即返回dp[m] [n]，

m是不具有正则匹配符号的，n具有

initial state ：当m为空串是，n=0肯定是true，n=1肯定是false，如果n>=2的情况下 n对应的符号是*这个符号可以抵消上一个符号，当然前提是输入规范，那这样的话dp[0] [n] = dp[0] [n-2],其他符号肯定都是false

当n = 0 的时候肯定m其他都是false，至此，第一行第一列被初始化完毕

Transfer function：

dp[i] [j]到底和周围的元素是什么关系，我们单独把第一个字符串第i个字母和第二个字符串第j个字母拿出来，首先考虑如果j是一个普通字符（情况12）

1. 当i，j匹配的时候，应该就等于i-1,j-1 的情况，

2. 当i，j不匹配，一定是false

3. 如果j是一个. ，情况等同第一种
4. 如果j是一个*  第一个可以把他当删除用等于i,j-2的情况，第二个可以把它当做前面那个字符使用，如果i个字符等于j-1那个字符那么就返回i-1,j-1,否则就是false

---

暂时只想到这些，来写一下

初试代码

~~~java
    public boolean isMatch(String a, String b){
        //注意考虑.*哦
        int m = a.length();
        int n = b.length();
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        dp[0][1] = false;
        //initial state
        for(int i = 2; i <= n; i++){
            if(bb[i-1] == '*')
            dp[0][i] = dp[0][i-2];
            else{
                dp[0][i] = false;
            }
        }
        for(int i = 1; i <= m; i++){
            for(int j = 1; j<=n;j++){
                char temp = bb[j-1];
                if(temp == '.' || temp == aa[i-1])
                    dp[i][j] = dp[i-1][j-1];
                if(temp == '*'){
         //这里忘了考虑.     if(bb[j-2] == aa[i-1]){
                    if(bb[j-2] == aa[i-1] || bb[j-2] == '.')
                        dp[i][j] = dp[i][j-2]||dp[i-1][j-1];
                    }
                    else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[m][n];
    }
~~~

![image-20210420144835963](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420144835963.png)

![image-20210420145241261](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420145241261.png)

![image-20210420150915247](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420150915247.png)

![image-20210420152133771](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420152133771.png)

## debug

1. 第一个错在第四种情况中中如果i个字符等于j-1个字符或者第j-1个字符是.

2. 第二个和第三个错让我意识到我思考的第四种情况当前面的字符相等的时候状态转移方程有问题，

   比如 aaaaa 和 a*作比较，当发现本位的a相同，你实际是应该把j不变继续跟之前的作比较，状态转移方程有误 

3. 最后一个方程发生在edge case考虑不周到我们刚刚在思考的时候理所当然的认为第一个字符串的第一个字符和第二个字符串的第一个字符比较一定是false，可是谁说了第二个字符串一定有字符，如果第二个字符串没有字符的话，那就会溢出所以得先判断

## 修正代码

~~~java
class Solution {
    public boolean isMatch(String a, String b){
        if(b.length() == 0) return a.length() == 0;//edge case错误
        //注意考虑.*哦
        int m = a.length();
        int n = b.length();
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        dp[0][1] = false;//把这个判断一下写到循环里就不会溢出                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
        //initial state
        for(int i = 2; i <= n; i++){
            if(bb[i-1] == '*')
            dp[0][i] = dp[0][i-2];
            else{
                dp[0][i] = false;
            }
        }
        for(int i = 1; i <= m; i++){
            for(int j = 1; j<=n;j++){
                char temp = bb[j-1];
                if(temp == '.' || temp == aa[i-1])
                    dp[i][j] = dp[i-1][j-1];
                if(temp == '*'){
                    if(bb[j-2] == aa[i-1] || bb[j -2] == '.'){//改动
                        dp[i][j] = dp[i][j-2]||dp[i-1][j];//新增情况
                    }
                    else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
~~~

