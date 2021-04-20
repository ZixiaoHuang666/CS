package LC.s100;

import java.util.HashMap;

class LC3 {
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
}
