# LC 8 [String to Integer (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/)

![image-20210420161925202](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420161925202.png)

---

一道非常经典的题目

## Pre-thinking

把一个输入字符串转换为数字，我们得考虑，首先字符串为null,长度为0应该怎么处理，是否字符串里只有数字字符，第一位会不会是正负号，首位的空格部分应该要被清掉，是否考虑溢出

corner case非常多，错了两次第一个是s = s.trim() 才可以要学会使用

第二个是break前忘了加else，这个错误已经犯了好几次了

~~~java
class Solution {
/*把一个输入字符串转换为数字，我们得考虑，首先字符串为null,长度为0应该怎么处理，是否字符串里只有数字字符，第一位会不会是正负号，首位的空格部分应该要被清掉，是否考虑溢出*/
    public int myAtoi(String s) {
        if(s.length() == 0) return 0;
        s = s.trim();
        char[] str = s.toCharArray();
        int bound = Integer.MAX_VALUE/10;
        int number = 0;
        boolean isNeg = false;
        for(int i = 0; i < str.length; i++){
            if(i == 0){
                if(str[i] == '+'){}
                else if(str[i] == '-') isNeg = true;
                else if(Character.isDigit(str[i])){
                    number+= str[i]-'0';
                }
                else{
                    return 0;
                }
            }
            else{
                if(Character.isDigit(str[i])){
                    int digit = str[i]-'0';
                    if(number < bound || (number == bound && digit <= 7)){
                        number = number*10+digit;
                    }
                    else if(number == bound && digit == 8 && isNeg){
                        return Integer.MIN_VALUE;
                    }
                    else{
                        return isNeg?Integer.MIN_VALUE:Integer.MAX_VALUE;
                    }
                }
                else break;
            }          
        }
        return isNeg?-number:number;
    }
}
~~~

