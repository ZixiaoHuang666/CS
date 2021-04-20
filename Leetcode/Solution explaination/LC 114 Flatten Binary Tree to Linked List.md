# LC 114 [Flatten Binary Tree to Linked List](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/)

![image-20210419154049675](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210419154049675.png)

---

## preThinking

选取这道题主要是为了巩固二叉树的遍历方法，这道理迭代和递归的方式都得会，初看右边展开的情况是前序遍历，那么迭代的思路应该是，知道前序遍历的顺序然后展开，最朴素的方法即完全分开做，如下图所示，

~~~java
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;//注意edge case 
        //root left right
        List<TreeNode> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();//这里容易写错，要想使用LinkedList的性质，必须声明为LinkedList，这样才能使用pollLast();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pollLast();
            list.add(cur);
            if(cur.right != null){
                stack.add(cur.right);
            }
            if(cur.left != null){
                stack.add(cur.left);
            }
        }
        for(int n = 1;n < list.size(); n++){
            TreeNode pre = list.get(n-1);
            TreeNode cur = list.get(n);
            pre.left = null;
            pre.right = cur;
        }
    }
}
~~~

## solution 2

接下来思考如何能够边分离边遍历，问题主要在于我们必须要保留右节点的信息不然右节点就没了

~~~java
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        //root left right
        TreeNode pre = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pollLast();
            if(pre != null){
                pre.left = null;
                pre.right = cur;
            }
            pre = cur;
            if(cur.right != null){
                stack.add(cur.right);
            }
            if(cur.left != null){
                stack.add(cur.left);
            }
        }
    }
}
//画一个stack模拟
~~~

## Solution3

最后一种方法递归

~~~java
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode leftroot = root.left;
        TreeNode rightroot = root.right;
        root.left = null;
        root.right = leftroot;
        flatten(leftroot);
        while(root.right != null){
            root = root.right;
        }
        root.right = rightroot;
        flatten(rightroot);
    }
}
~~~



