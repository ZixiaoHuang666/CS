# LC 92 Decode ways

![image-20210421151525484](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210421151525484.png)

# Pre-thinking

含泪重写，这道题第一次写的时候关机了没保存

这道题初看上去其实我是没有太多思路的，因为解码规则需要考虑的情况比较多

1. Edge case：如果输入里有不含数字，字符，输入数组为空，length为0，

2. 我们可以发现解码字符最多两位，最少一位，确定思路遍历字符串，一位遍历，当遇到，1和2的时候分情况讨论

   首先1，后面能跟任何数字，2后面能够跟0-6，但两个的0都要被单独拿出来讨论如果遇到0，只能2位放在一起不能拆，遇到其他数字可以拆分，这里发现满足递归情况可以拆分为1*从这一位拆分后面的+ 1 *两位后一起拆分，这里应该要联想到动态规划了，但我没有，我们来看一下错误

## Debug

初始代码

~~~java
class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        char[] arr = s.toCharArray();
        return num(arr,0);
    }
    private int num(char[] arr, int start){
        int sum = 1;
        for(int i = start; i < arr.length; i++){
            if((arr[i] == '1' || arr[i] == '2') && i!= arr.length-1){
                i++;
                if(arr[i] != '0'){
                   return num(arr,i)+num(arr,i+1); 
                }
            }
        }
        return 1;
    }
}
~~~

完全没有考虑，如果碰上单0等这样的情况，是完全无法解码的，并且如果第一位是2，第二位是不能大于6的

其次这样算法复杂度太可怕了，考虑要用动态规划，按照我上面的架子，我们需要的是一个从后往前的动态规划

因此我们做出了改进

~~~java
class Solution {
    Integer[] dp;
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        char[] arr = s.toCharArray();
        dp = new Integer[arr.length];
        if(arr[arr.length-1] != '0') dp[arr.length-1] = 1;
        return num(arr,0);
    }
    private int num(char[] arr, int start){
        //if(start == arr.length) return 1;
        if(start == arr.length) return 1;
        if(dp[start] != null) return dp[start];
        for(int i = start; i < arr.length; i++){
            if((arr[i] == '1' || arr[i] == '2')&& i < arr.length-1){
                i++;
                if((arr[i-1] == '1' && arr[i] != '0') || (arr[i-1] == '2' && arr[i]-'6'<=0 && arr[i]!='0')){			dp[start] = num(arr,i)+num(arr,i+1); 
                   return dp[start];
                }
            }
            else if(arr[i] == '0'){
              dp[start] = 0;
              return 0;
            } 
        }
        dp[start] = 1;
        return 1;
    }
}
~~~

但其实最开始仍然不对，对于类似11这样的字符解码结果为1，因为当吧11看成一个的时候num()返回的是0，这里又错，所以得认真举例思考代码完善

不能想当然return 0；

---

## Improve

现在自己写出了菜菜的代码，我们来看看别人的代码分析问题

~~~java

class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) { 
            // a : 代表「当前位置」单独形成 item
            // b : 代表「当前位置」与「前一位置」共同形成 item
            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
            // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
            if (1 <= a && a <= 9) f[i] = f[i - 1];
            // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
            if (10 <= b && b <= 26) f[i] += f[i - 2];
        }
        return f[n];
    }
}
//其他细节：由于题目存在前导零，而前导零属于无效 item。可以进行特判，但个人习惯往字符串头部追加空格作为哨兵，追加空格既可以避免讨论前导零，也能使下标从 1 开始，简化 f[i-1] 等负数下标的判断。
~~~

进一步空间压缩

~~~java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] f = new int[3];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i % 3] = 0;
            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
            if (1 <= a && a <= 9) f[i % 3] = f[(i - 1) % 3];
            if (10 <= b && b <= 26) f[i % 3] += f[(i - 2) % 3];
        }
        return f[n % 3];
    }
}
/*补充
我们称一个解码内容为一个 item。

为根据题意，每个 item 可以由一个数字组成，也可以由两个数字组成。

数据范围为 100，很具有迷惑性，可能会有不少同学会想使用 DFS 进行爆搜。

我们可以大致分析一下这样的做法是否可行：不失一般性的考虑字符串 s 中的任意位置 i，位置 i 既可以作为一个独立 item，也可以与上一位置组成新 item，那么相当于每个位置都有两种分割选择（先不考虑分割结果的合法性问题），这样做法的复杂度是 O(2^n)的，当 n 范围是 100 时，远超我们计算机单秒运算量（10^7）。即使我们将「判断分割结果是否合法」的操作放到爆搜过程中做剪枝，也与我们的单秒最大运算量相差很远。

递归的方法不可行，我们需要考虑递推的解法。
*/
~~~

## 仿写

~~~java
class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[3];
        char[] arr = s.toCharArray();
        dp[0] = arr[0] == '0'?0:1;
        if(s.length() >= 2){
            dp[1] = arr[1] == '0'?0:1; //初始化很难弄
        }
        for(int i = 1; i < n; i++) {
            dp[2] = dp[1];
            dp[1] = dp[0];
            int a = arr[i] - '0';
            int b = (arr[i-1]-'0')*10+arr[i]-'0';
            if(a>=1 && a <=9) dp[0] = dp[1];
            if(b>=10 && b<=26) dp[0] += dp[2];

        }
        return dp[0];
    }
}
~~~

##  debug

![image-20210421160318705](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210421160318705.png)

~~~java
class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[3];
        char[] arr = s.toCharArray();
        dp[0] = arr[0] == '0'?0:1;
        dp[1] = 1;
        for(int i = 1; i < n; i++) {
            dp[2] = dp[1];
            dp[1] = dp[0];
            //!!!!!!!!
            dp[0] = 0;
            int a = arr[i] - '0';
            int b = (arr[i-1]-'0')*10+arr[i]-'0';
            if(a>=1 && a <=9) dp[0] = dp[1];
            if(b>=10 && b<=26){
                if(b == 10 || b == 20){
                    dp[0] = dp[2];
                }
                else{
                    dp[0]+=dp[2];
                }
            }

        }
        return dp[0];

    }
}
~~~

![image-20210421161133608](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210421161133608.png)

因为乱用状态压缩本来应该赋值0的地方没变，必要要添加dp[0] = 0