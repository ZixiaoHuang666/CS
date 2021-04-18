package LC.s900;

import java.util.TreeMap;

public class LC846 {
    public boolean isNStraightHand(int[] hand, int W) {
        //Edge case
        if(hand == null || hand.length < W || hand.length%W != 0) return false;
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int a:hand){
            map.put(a,map.getOrDefault(a,0)+1);
        }
        while(!map.isEmpty()){
            int min = map.firstKey();
            for(int i = 0; i < W; i++) {
                if (map.containsKey(min + i)) {
                    if (map.get(min + i) == 1) map.remove(min+i);
                    else map.put(min+i, map.get(min+i) - 1);
                }
                else return false;
            }
        }
        return true;
    }
}
