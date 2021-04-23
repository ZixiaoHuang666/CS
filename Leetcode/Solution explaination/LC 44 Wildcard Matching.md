# LC 44 Wildcard Matching

![image-20210422173620326](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210422173620326.png)

---

## Pre-thinking

从看到题来说我的思路很容易想到了字符串匹配，因此联想动态规划

状态同样是m+1 n+1代表第几个字符，初始化我们需要思考当第一个字符串为0的时候除了当n = 0 true, n=1如果是* true，在之后 n-1是True && 本位是*

Transfer function：

写到这里的时候我突然意识到这道题其实是没有删除操作的，对于p的任意字母不可能存在删除，唯一麻烦的地方在于对于*符号什么时候交给他身后的元素，这里可能需要一个记忆化递归，我们先试试写出不带递归的版本

## Try

~~~java
class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int p = p.length();
        ?/s==0,p==0?
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        return match(ss,pp,0,0);
        
        

    }
    private boolean match(char[] arr1,char[] arr2, int start1,int start2){
        while(start1 < arr1.length && start2 < arr2.length){
            if(arr1[start1] == arr2[start2] || arr2[start2] == '?'){
                start++;
                start++;
            }
            else if(arr2[start2] == '*'){
                if(start2 == arr2.length -1) return true;
                else{
                    boolean case1 = match[arr1,arr2,start1,start2+1];//empty
                    if(case1) return true;
                    char next = arr2[start+1];
                    for(int i = start1, i < arr1.length; i++){
                        if(arr1[i] == next && match(arr1,arr2,i,start2+1)){
                            return true;
                        }
                    }
                    return false;
                }
                
            }
            else{
                return false;
            }
        }
        if(start1 == arr1.length && start2 == arr2.length) return true;
        else return false;      
    }
}
~~~

## Debug

没有考虑初始情况比如a为空 b为****

改了很多语法错误

没有考虑* * * *

更改后

~~~java
class Solution {
    public boolean isMatch(String s, String p) {
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        return match(ss,pp,0,0);
    }
    private boolean match(char[] arr1,char[] arr2, int start1,int start2){
        while(start1 < arr1.length && start2 < arr2.length){
            if(arr1[start1] == arr2[start2] || arr2[start2] == '?'){
                start1++;
                start2++;
            }
            else if(arr2[start2] == '*'){
                while(start2 != arr2.length -1 && arr2[start2+1] == '*'){
                    start2++;
                }                  
                if(start2 == arr2.length -1) return true;
                else{
                    boolean case1 = match(arr1,arr2,start1,start2+1);//empty
                    if(case1) return true;
                    char next = arr2[start2+1];

                    for(int i = start1; i < arr1.length; i++){
                        if((arr1[i] == next || next == '?') && match(arr1,arr2,i,start2+1)){
                            return true;
                        }
                    }
                    return false;
                }
                
            }
            else{
                return false;
            }
        }
        while(start2 < arr2.length && arr2[start2] == '*'){
                start2++;
        }        
        if(start1 == arr1.length && start2 == arr2.length) return true;
        else return false;      
    }
}
//没考虑******
~~~

现在时间复杂度不行修改成动态规划

~~~java
class Solution {
    Boolean[][] dp;
    public boolean isMatch(String s, String p) {
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        dp = new Boolean[ss.length][pp.length];
        return match(ss,pp,0,0);
    }
    private boolean match(char[] arr1,char[] arr2, int start1,int start2){
        int x = start1,y = start2;
        while(start1 < arr1.length && start2 < arr2.length){
            if(dp[start1][start2]!=null) return dp[start1][start2];
            if(arr1[start1] == arr2[start2] || arr2[start2] == '?'){
                start1++;
                start2++;
            }
            else if(arr2[start2] == '*'){
                while(start2 != arr2.length -1 && arr2[start2+1] == '*'){
                    start2++;
                }                  
                if(start2 == arr2.length -1){
                     dp[x][y] = true;
                     return true;
                     }
                else{
                    boolean case1 = match(arr1,arr2,start1,start2+1);//empty
                    if(case1){
                        dp[x][y] = true;
                        return true;
                    }
                    char next = arr2[start2+1];

                    for(int i = start1; i < arr1.length; i++){
                        if((arr1[i] == next || next == '?') && match(arr1,arr2,i,start2+1)){
                            dp[x][y] = true;
                            return true;
                        }
                    }
                    dp[x][y] = false;
                    return false;
                }
                
            }
            else{
                dp[x][y] = false;
                return false;
            }
        }
        while(start2 < arr2.length && arr2[start2] == '*'){
                start2++;
        }        
        if(start1 == arr1.length && start2 == arr2.length){
            //dp[x][y] = true;
            return true;
        } 
        else{
            //dp[x][y] = false;
            return false;
        }      
    }
}
//没考虑******
~~~



我不知道为什么从一开始似乎可以小小的做到的题判断了无数的边界条件，错了无数次，调成这样，感觉自己写的代码一点都不好看，有点depressed

