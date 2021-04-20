# LC 28  implement strStr()

字符串匹配三种方法

![image-20210420095059273](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420095059273.png)

---

## Brute force

边界条件处理的很不好，首先边界条件包括S和s可能长度相同的情况，都为空串，一个为空串，这些情况都得考虑到。这样匹配时间复杂度其实是O(M*N);

~~~java
class Solution {
    public int strStr(String S, String s) {
        //edge case not considered
        int m = S.length(), n = s.length();
        if(m == 0 && n == 0) return 0;
        for(int i = 0; i <= m-n; i++){
            String temp = S.substring(i,i+n);
            if(temp.equals(s)){
                return i;
            }
        }
        return -1;
    }
}
~~~

## KMP

子串复杂度O(M)时间内建立一个匹配数组

![010FD8AE2B79FFE03DC3735ACD224A6A.png](https://pic.leetcode-cn.com/1618846927-xFAEXE-010FD8AE2B79FFE03DC3735ACD224A6A.png)

![B9497542844478144BED83E9ADA0C12F.png](https://pic.leetcode-cn.com/1618847960-lkVIDM-B9497542844478144BED83E9ADA0C12F.png)

![161584A2D930A7B91092A2C3872D9DE5.png](https://pic.leetcode-cn.com/1618847981-wncoqJ-161584A2D930A7B91092A2C3872D9DE5.png)

![6127EBA37435560C20BB8B15D5B790B6.png](https://pic.leetcode-cn.com/1618847995-vRWimV-6127EBA37435560C20BB8B15D5B790B6.png)

在实际编码时，通常我会往原串和匹配串头部追加一个空格（哨兵）。

目的是让 j 下标从 0 开始，省去 j 从 -1 开始的麻烦。

整个过程与上述分析完全一致，一些相关的注释我已经写到代码里。

~~~java
class Solution {
    // KMP 算法
    // ss: 原串(string)  pp: 匹配串(pattern)
    public int strStr(String ss, String pp) {
        if (pp.isEmpty()) return 0;
        
        // 分别读取原串和匹配串的长度
        int n = ss.length(), m = pp.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && p[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++
            if (p[i] == p[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == p[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }

        return -1;
    }
}
~~~

第一步建立前缀数组，看已经出现过的字符中有没有后缀等于前缀，且不能等于本身

aaabbab 

第0位默认为0

第一位aa 前后看最长都是a 默认为1

第二位aaa 为2

第三位aaab 为0，前缀数组记录了怎么回退，









