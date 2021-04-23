# LC58 Length of last word

![image-20210423150833435](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423150833435.png)

## Pre-thinking

这道题主要恶心在edge case，

1. s为空为0
2. s只有空格必须把空格区分出来
3. 如果不用现成函数应该怎么做

## do

~~~java
class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        if(s == null || s.length() == 0) return 0;
        String[] arr = s.split(" ");
        return arr[arr.length-1].length();

    }
}
~~~

应该从后向前

~~~java
class Solution {
    public int lengthOfLastWord(String s) {
        if(s == null) return 0;
        char[] arr = s.toCharArray();
        int indexStart = arr.length-1;
        while(indexStart >=0  && arr[indexStart] == ' '){
            indexStart--;
        }
        if(indexStart < 0) return 0;
        int count = 0;
        while(indexStart >=0  && arr[indexStart] != ' '){
            count++;
            indexStart--;
        }
        return count;

    }
}
~~~

