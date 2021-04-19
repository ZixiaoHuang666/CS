package LC.s1000;
public class LC979 {
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
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
