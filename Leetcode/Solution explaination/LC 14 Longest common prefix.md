# LC 14 Longest common prefix

![image-20210421162707241](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210421162707241.png)

# Pre-thinking

只要input不是null的边界条件，第一个想法那就从第一个字符开始取，一直到有一个不行了往后增加，还有一个想法是从整个单词比较，如果不行可以看看谁的长度更小，换一个，或者减一，用startswith 函数判断

~~~java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        String lcp = strs[0];
        for(int i = 1; i< strs.length; i++){
            while(!strs[i].startsWith(lcp)){
                
                lcp = lcp.substring(0,lcp.length()-1);
            }
        }
        return lcp;
    }
}
~~~

## 分析

第一种思路，两两比较，找出两者中的公共前缀继续比外循环O(N)内循环O(M) 复杂度O(n*m)

第二种可以想到的思路，sort()，排序后比较第一个和第三个的common prefix O(NlogN)

因为长度是MN平均 实际是O(MNlogMN)



