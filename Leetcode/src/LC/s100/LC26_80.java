package LC.s100;
//读写指针经典题，删除有序数组重复的元素，同一个元素最多k个
public class LC26_80 {
    public int removeDuplicates(int[] nums) {
        int k = 1;
        if(nums.length <= k) return nums.length;
        int write = k;
        for(int read = k; read < nums.length; read++){
            if(nums[read] != nums[write - k]){
                nums[write++] = nums[read];
            }
        }
        return write;
    }
}
