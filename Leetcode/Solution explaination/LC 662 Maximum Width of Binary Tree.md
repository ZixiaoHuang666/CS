# [Maximum Width of Binary Tree](https://leetcode-cn.com/problems/maximum-width-of-binary-tree/)

![image-20210419135206291](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210419135206291.png)

---

## preThinking

本题很快就能想到使用BFS，但是发现问题的难点是需要把null的地方也表示计算出来，到这里可以想到几种记录null节点的方法，第一个在null的时候插入一个特殊节点代表null，每次遇到这个这个节点就再生出两个特殊节点，不过创建节点麻烦了点，且最后一排还需要特殊处理。好像唯一好一点的方法都一定需要一个index来计数，利用树的节点的儿子分别是当前层2x 和2x+1，这样来记录，那就延伸出一个点如何记录这个值呢？如果采用BFS，得对树结构进行改进，如果使用DFS，还得记录当前层数要记录每一层出现过的最小index和最大index，这里还有一个小细节，如果使用中序遍历或者后续遍历，每一层最先到达的一定是第一个节点，记录最小index就好

## BFS solution

~~~java
class modNode {
    int index;
    TreeNode node;
    public modNode(int val,TreeNode node){
        index = val;
        this.node = node;
    }
}
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<modNode> queue = new LinkedList<>();
        int max = 1;
        queue.offer(new modNode(0,root));
        while(!queue.isEmpty()){
            int n = queue.size();
            int firstIndex = 0,endIndex = 0;
            for(int i = 0; i<n; i++){
                modNode temp = queue.poll();
                if(i == 0) firstIndex = temp.index;
                if(i == n-1) endIndex = temp.index;
                if(temp.node.left != null){
                    queue.offer(new modNode(temp.index*2,temp.node.left));
                }
                if(temp.node.right != null){
                    queue.offer(new modNode(temp.index*2+1,temp.node.right));
                }
            }
            max = Math.max(max,endIndex-firstIndex+1);          
        }
        return max;
    }
}
~~~

## DFS solution

~~~java
class Solution {
    int max = 1;
    HashMap<Integer,Integer> map;
    public int widthOfBinaryTree(TreeNode root) {
        map = new HashMap<>();//record the length of every level;
        recur(0,0,root);
        return max;
    }
    private void recur(int depth, int index, TreeNode root){
        if(root == null) return;
        if(!map.containsKey(depth)) map.put(depth,index);
        else{
            int temp = index - map.get(depth) + 1;
            if(temp > max){
                max = temp;
            }
        }
        if(root.left != null)
        recur(depth+1,2*index,root.left);
        if(root.right != null){
            recur(depth+1,2*index+1,root.right);
        }
    }
}
~~~

