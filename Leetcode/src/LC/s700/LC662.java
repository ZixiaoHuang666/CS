package LC.s700;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LC662 {//DFS solution
    int max = 1;
    HashMap<Integer, Integer> map;

    public int widthOfBinaryTree(TreeNode root) {
        map = new HashMap<>();//record the length of every level;
        recur(0, 0, root);
        return max;
    }

    private void recur(int depth, int index, TreeNode root) {
        if (root == null) return;
        if (!map.containsKey(depth)) map.put(depth, index);
        else {
            int temp = index - map.get(depth) + 1;
            if (temp > max) {
                max = temp;
            }
        }
        if (root.left != null)
            recur(depth + 1, 2 * index, root.left);
        if (root.right != null) {
            recur(depth + 1, 2 * index + 1, root.right);
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//Second Solution BFS
class modNode {
    int index;
    TreeNode node;
    public modNode(int val,TreeNode node){
        index = val;
        this.node = node;
    }
}
class BFS{
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

