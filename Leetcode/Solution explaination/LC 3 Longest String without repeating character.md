# LC 3 Longest String without repeating character

![d](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420191831261.png)

## pre-Thinking

找到一个不具有重复字符串的最长子串，可以看出肯定要使用哈希表和滑动窗口，每次遇到重复元素，移动start指针，进一步优化只有字母的话可以用一个数组来存。

## Edge case

String可能为空吗，如果为0？

那我们带着这个思路写一写

## Try

~~~java
class LongestStringWithoutRepeat{
    public int lengthOfLongestSubstring(String s){
        if(s == null || s.length() == 0) return 0;
        int n = s.length();
        int start = 0,end = 0;
        char[] arr = s.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        int max = 0;
        for(;end < n; end++){
            char temp = arr[end];
            if(map.containsKey(temp)){
                int index = map.get(temp);
                start = index+1;
                map.put(temp,end);
            }else{
                map.put(temp,end);
            }
            max = Math.max(max,end-start+1);
        }
       return max;
    }
}
~~~

## Debug

![image-20210420193255116](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210420193255116.png)

不能直接更新index，应该要做一个比较与当前实际的index值因为可能start已经超过了存储的值

## Solution

~~~ java
    public int lengthOfLongestSubstring(String s){
        if(s == null || s.length() == 0) return 0;
        int n = s.length();
        int start = 0,end = 0;
        char[] arr = s.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        int max = 0;
        for(;end < n; end++){
            char temp = arr[end];
            if(map.containsKey(temp)) {
                int index = map.get(temp);
                start = Math.max(index + 1, start);//这里最开始写成start = index+1,实际上start可能已经越过这个
            }
            map.put(temp, end);
            max = Math.max(max,end-start+1);
        }
        return max;
    }
~~~

