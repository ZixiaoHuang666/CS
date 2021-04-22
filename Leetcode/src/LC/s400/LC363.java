package LC.s400;

import java.util.TreeSet;

public class LC363 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
        // O(cols ^ 2 * rows)
        for (int l = 0; l < cols; l++) { // 枚举左边界
            int[] rowSum = new int[rows]; // 左边界改变才算区域的重新开始
            for (int r = l; r < cols; r++) { // 枚举右边界
                for (int i = 0; i < rows; i++) { // 按每一行累计到 dp
                    rowSum[i] += matrix[i][r];
                }

                // 求 rowSum 连续子数组 的 和
                // 和 尽量大，但不大于 k
                max = Math.max(max, dpmax(rowSum, k));
                if(max == k) return k;
            }
        }
        return max;
    }
    public int dpmax(int[] array, int k){
        int sum = 0, max = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        for (int value : array) {
            sum += value;
            Integer min = set.ceiling(sum - k);
            if (min != null)
                max = Math.max(max, sum - min);
            if(max == k) return max;
            set.add(sum);
        }
        return max;
    }
}
