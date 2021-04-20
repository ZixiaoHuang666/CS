# LC 26,80 删除有序数组的重复元素

![image-20210418163504918](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210418163504918.png)

---

pre Analysis：题目是比较简单的，初看上去，26题是保留一个元素，80题是相同元素可以保留2个，我们进一步拓展这个问题为，相同元素可以保留k个，这是一道读写指针的经典题，难度在于分析指针如何移动

---

## Edge cases

这道题给的元素应该只用分析，数组数据类型，数组是否为空，如何处理nums.length < = k,应该直接返回数组长度

## 具体实现

~~~java
    public int removeDuplicates(int[] nums) {
        int k = 1; //定义能够保留的同一元素个数
        if(nums.length <= k) return nums.length;//处理edge case
        int write = k;//第一个要处理的元素是数组下标为k的元素
        for(int read = k; read < nums.length; read++){//读指针同样也是
            if(nums[read] != nums[write - k]){//观察读指针是否与写指针前的第k个元素相等
                //如果不等read write向后移动，如果相等应该只移动read
                nums[write++] = nums[read];
            }
        }
        return write;
    }
~~~





