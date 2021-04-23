# LC 63 Unique Path II

![image-20210423162623236](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423162623236.png)

## Pre-Thinking

感觉还是可以Dp 就是判断条件进一步变换，要先考虑是否上面和左边是否超出边界或者是障碍物就可以进行转移

## Try

~~~java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = 1;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int upr = i-1;
                int left = j-1;
                if(upr>=0 && obstacleGrid[upr][j] != 1){
                    dp[i][j] += dp[upr][j];
                }
                if(left>=0 && obstacleGrid[i][left] != 1){
                    dp[i][j] += dp[i][left];
                }
            }
        }
        return dp[row-1][col-1];
    }
}
~~~

## Debug

![image-20210423163913409](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423163913409.png)

忘了考虑自己是障碍物

![image-20210423164032735](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210423164032735.png)

起始点是障碍物？？？

~~~java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = obstacleGrid[0][0] == 1?0:1;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(obstacleGrid[i][j] == 1) continue;
                int upr = i-1;
                int left = j-1;
                if(upr>=0 && obstacleGrid[upr][j] != 1){
                    dp[i][j] += dp[upr][j];
                }
                if(left>=0 && obstacleGrid[i][left] != 1){
                    dp[i][j] += dp[i][left];
                }
            }
        }
        return dp[row-1][col-1];
    }
}
~~~

