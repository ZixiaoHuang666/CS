# Distribute Coins in Binary Tree

![image-20210419111537784](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210419111537784.png)

---

## preThinking：

拿到这个题首先理解题意，从感觉上树的问题肯定是个递归问题，要分析根节点，左子树，右子树之间的关系，很快能发现每个节点应该只含有一个硬币，但是题目求解的是移动的次数，如果从上往下想，根节点把多出来的硬币向下分，首先不知道左子树或者右子树具体的缺少的硬币，有上给左右，上给左，上给右，右给上再给左，左给上给右，左给上，右给上，多种情况，这里可以思索要不要写一个函数统计某一个子树的硬币个数，但是这样的遍历将是O(N)复杂度，每层不断计算下层的节点个数的话从上往下遍历复杂度太高O(N^2)，那再思考从下往上，感觉更合理复杂度上，但是也存在一个问题，有可能下面的节点会出现欠费的情况，根在左加右数量并不够，因此这道题陷入了僵局，我开始思路困难，无从下手

## Edge case

主要思考root == null 情况应该就好了

## Solution

首先题目确实应该从下往上进行破局，既然没有其他的方法就要勇敢地往下想，我们要敢于欠钱，我们想如果一个节点金币为0，那么他需要喊他的爸爸给他一块钱这是唯一路径必定正确，我们认为这个节点的流动为-1，如果节点金币为3，那它必然交给他的爸爸两个金币，我们只考虑一步减少思考量，也是唯一路径，不必在考虑他的爸爸会不会分给其他的儿子，流动即为2，那么根节点的流动应该等于什么呢？怎么把这个流动欠款和我们的交换次数划上联系，我们先从例子中推导试一试，如果一个父亲值为0有一个0的左儿子，3的右儿子，左树流动性-1，右树为2，交换的实际次数应该是2次（右给父，父给子），父亲的流动性应该也是-1，举了这一个例子以后逐渐有了方向，我每次应该把左和右节点变成0，然后把欠款或者要给出来的堆积在父节点上。这样就能清晰算出移动的次数，这里要指出，一次只能移动一个硬币。次数是币值的绝对值。（初始流动值= 自身节点值-1）

算法逻辑是，先找到最底下的树，当根节点是null是直接返回0，然后分别得到左子树，左子树的交换次数后，再来得到本层的交换次数，我们先把节点自身值减去1，然后分别计算左子树，右子树的流动值（注意判空），即他们的val看是否为0，如果为0说明不需要流动，如果不为0，本层交换次数加上流动的绝对值。最后返回左加右加本层的流动值

~~~java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int distributeCoins(TreeNode root) {
        if(root == null) return 0;
        int levelRight = 0,levelLeft = 0;
        int left = distributeCoins(root.left);
        int right = distributeCoins(root.right);
        root.val -=1;
        if(root.left != null){
            int temp = root.left.val;
            if(temp != 0){
               levelLeft+= Math.abs(temp);
               root.val += temp;
               root.left.val = 0;
            }
        }

        if(root.right != null){
            int temp = root.right.val;
            if(temp != 0){
               levelRight+=Math.abs(temp);
               root.val += temp;
               root.right.val = 0;
            }
        }
        return left+right+levelRight+levelLeft;        
    }
}
~~~



