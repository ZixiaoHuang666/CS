# Leetcode Hot 100                               Combination & Permutation

要分清排列与组合，排列的话强调顺序，要想具体得到排列只能使用回溯算法爆算

组合的话12 21是没有区别的

但如果只要求个数，就要考虑动态规划方法了。

## 46 Permutations

Q :Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

? : Edge condition or things I need to make it clear?

<details> <summary>Pre</summary> <p> what if the input array is null? </p> <p>number is distinct!</p> the date input must be small, the time complexity is n to the power of n;</p>   
</details>
<details> <summary>Solution1 Hint</summary> <p>My first thought is BackTracking, but we also need a boolean array to determine whether it is used or not to avoid duplication</p>  
<p>
</details>

---

~~~java
//my Solution
import java.util.*;
//因为是静态方法，所以调用的也必须是静态变量，独属于类
class Program {
	private static List<List<Integer>> res;
	private static List<Integer> temp;
	private static boolean[] isUsed;
  public static List<List<Integer>> getPermutations(List<Integer> array) {
    // Write your code here.
      //对于静态变量的初始化必须要放在下面，不然res，temp运行第二个testcase就不会初始化
		res = new ArrayList<>();
		temp = new ArrayList<>();
		if(array == null || array.size() == 0) return res;
		int n = array.size();
		isUsed = new boolean[n];
		recur(array,0);
		return res;
  }
  private static void recur(List<Integer> array, int index){
		if(index == array.size()){
			res.add(new ArrayList<>(temp));//n!次
		}
		else{
			for(int i = 0; i < array.size(); i++){
				if(!isUsed[i]){
					isUsed[i] = true;
					temp.add(array.get(i));     //O(n)*调用的次数,我们知道递归树的最后一层即
					recur(array,index+1);      //n!次，深度为n,我们估计为递归次数n*n!
					temp.remove(temp.size()-1);
					isUsed[i] = false;
				}
			}
		}		
	}
}
//n factorial permutations
//时间复杂度：O(n*n*n!)
//空间复杂度：O(n*n!)//每个长度n*n！个变式
~~~

---

[回溯组合类问题](https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/)

<details> <summary>Inplace Solution2</summary> How to implement in place? </details>

---

~~~java
import java.util.*;

class Program {
	private static List<List<Integer>> res;
  public static List<List<Integer>> getPermutations(List<Integer> array) {
		res = new ArrayList<>();
    if(array == null || array.size() == 0){
			return res;
		}
		recur(array,0);
		return res; 
  }
	private static void recur(List<Integer> array, int index){
		if(index == array.size()){
			res.add(new ArrayList<Integer>(array));
		}
		else{
			for(int j = index; j < array.size(); j++){
				swap(array,index,j);
				recur(array,index+1);//N*N！次但是循环内O(1)复杂度
				swap(array,j,index);
			}
		}		
	}
	private static void swap(List<Integer> list, int i, int j){
		int temp = list.get(i);
		list.set(i,list.get(j));
		list.set(j,temp);
	}
}
//空间复杂度O(n*n!)
//时间复杂度O(n*n!)
//tip:数组转换如果是对象  new ArrayList<Element>(Arrays.asList(array));
//如果是基础数据类型只能用Stream，所有操作都在原数组内完成，使判断删除时间减少
~~~

### 47 Duplicate Permutations

Given a collection of numbers, `nums`, that might contain duplicates, return all possible unique permutations in any order.

---

<details>如果重复，我们在每一个深度的递归上都只能使用一次数字，因此两种方法，第一种可以在本次递归建立一个HashMap存放已经使用过的数据，如果在本 层已经使用过则不取，第二种思路则是我们可以先排序，如果当前元素index>1并且它的值等于上一个元素，且他的上一个元素并没用用过则跳过本数字，反之使用。</details>

- Solution 1

  ~~~java
  class Solution {
      private static List<List<Integer>> res;
      private static List<Integer> temp;
      private static boolean[] used;
      public List<List<Integer>> permuteUnique(int[] nums) {
          used = new boolean[nums.length];
          res = new ArrayList<>();
          temp = new ArrayList<>();
          recur(0,nums);
          return res;
      }
      private void recur(int index,int[] nums){
          if(index == nums.length){
              res.add(new ArrayList<>(temp));
          }
          else{[1,1,1,2]
              HashSet<> set
              for(int i = 0; i < nums.length; i++){
              if(!used[i] && !set.contains(nums[i])){
                  used[i] = true;
                  set.add(nums[i]);
                  temp.add(nums[i]);
                  recur(index+1,nums);
                  temp.remove(temp.size()-1);
                  used[i] = false;
              }
              else{
                      continue;
                  }
              }
          }
      }
  }
  ~~~

- Solution 2只需要更改判断条件为

  ~~~java
   public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
          if (idx == nums.length) {
              ans.add(new ArrayList<Integer>(perm));
              return;
          }
          for (int i = 0; i < nums.length; ++i) {[1,1,1,2,3]
                                                   
              if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                  continue;
              }
              perm.add(nums[i]);
              vis[i] = true;
              backtrack(nums, ans, idx + 1, perm);
              vis[i] = false;
              perm.remove(idx);
          }
      }
  }
  ~~~

- Solution 3:(针对于原题中的第二个方法也可以增加一个HashSet)

  ~~~java
  class Solution {
      private static List<List<Integer>> res;
      public List<List<Integer>> permuteUnique(int[] array) {
  		res = new ArrayList<>();
          if(array == null || array.length == 0){
  			return res;
  		}
  		recur(array,0);
  		return res; 
    }
  	private static void recur(int[] array, int index){
  		if(index == array.length){
              ArrayList<Integer> list  = new ArrayList<>();
  			for(int a:array)
  				list.add(a);
  			res.add(list);
  		}
  		else{
              HashSet<Integer> set = new HashSet<>();
  			for(int j = index; j < array.length; j++){
                  if(set.contains(array[j])){
                      continue;
                  }
                  set.add(array[j]);
  				swap(array,index,j);
  				recur(array,index+1);//N*N！次但是循环内O(1)复杂度
  				swap(array,j,index);
  			}
  		}		
  	}
  	private static void swap(int[] list, int i, int j){
  		int temp = list[i];
  		list[i] = list[j];
  		list[j] = temp;
  	}
  }
  ~~~

  ---

  

## 39 [ Combination Sum](https://leetcode-cn.com/problems/combination-sum/)

Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.



Question: First we need to assure that the candidate's number is distinct and all of them are positive, if they are not positive, how can we end the recursion? if it is not distinct,w what should we do.

~~~java
class Solution {
    List<List<Integer>> res;
    List<Integer> temp;   
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        recur(0,candidates,target);
        return res;
    }
    private void recur(int index, int[] candidates, int target){
        if(target == 0){
            res.add(new ArrayList<>(temp));
        }
        else{
            if(index >= candidates.length)
                return;
            for(int i = index; i < candidates.length; i++){
                if(candidates[i] <= target){//
                    temp.add(candidates[i]);
                    recur(i, candidates, target - candidates[i]);
                    temp.remove(temp.size()-1);
                }
            }
        }

    }
}
~~~

这种高复杂度的题很适合一些手段，比如以上代码可以进一步排序剪枝

如果follow up 负数，这个题将会死循环-5,5-5,5比如

## 40[ Combination Sum II](https://leetcode-cn.com/problems/combination-sum-ii/)

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

仍然没有负数，但是给的数组可能重复并且不能重复取同一元素。用Hashmap在同层筛选，排序剪枝。

或者排序后在同层检测不能相等否则continue，即大于index

~~~java
class Solution {
    List<List<Integer>> res;
    List<Integer> temp;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        Arrays.sort(candidates);
        recur(0,candidates,target);
        return res;
    }
    private void recur(int index, int[] candidates, int target){
        if(target == 0){
            res.add(new ArrayList<>(temp));
        }
        else{
            if(index >= candidates.length)
                return;
            for(int i = index; i < candidates.length; i++){
                if(i > index && candidates[i] == candidates[i-1]){
                    continue;
                }
                if(candidates[i] <= target){
                    temp.add(candidates[i]);
                    recur(i+1, candidates, target - candidates[i]);//
                    temp.remove(temp.size()-1);
                }
                if(candidates[i]>target){
                    break;
                }
            }
        }

    }
}
//时间复杂度O(2^n*n)
//空间复杂度O(n)
~~~

这道题即使Follow up负数，也能完成因为只用一次，唯一就是要重新思考剪枝

## 216 [Combination Sum III](https://leetcode-cn.com/problems/combination-sum-iii/)

Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

~~~java
class Solution {
    List<List<Integer>> res;
    List<Integer> temp;
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        recur(1,k,n);
        return res;

    }
    private void recur(int number, int pickcount, int target){
        if(target == 0){
            if(pickcount == 0)
                res.add(new ArrayList<>(temp));
            else
                return;            
        }
        else{
            for(int i = number; i < 11 - pickcount; i++){
                if(i <= target){
                    temp.add(i);
                    recur(i+1,pickcount-1,target-i);
                    temp.remove(temp.size()-1);
                }
                else{
                    break;
                }
            }
        }
    }
}
~~~







