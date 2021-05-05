# LC 1473 Paint house III

![](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210504210209329.png)

## Pre-thinking

不愧是复杂题，光理解题都很痛苦，一排m个房子，每一个房子需要被涂成n个颜色中的一种编号1到n，已经被涂色的房子不能被在涂色，要把房子分割成目标大小的街区

但这个情况真的太多了

想想最暴力的办法，从第一个开始计数，如果是0说明需要涂色，有n种涂色方案，从这里感觉出来了应该是一个动态规划问题，这道题递归确实不太可能，两个状态，到达第n个房子时已有的房子颜色数和这个颜色下最少的cost，子问题满足递推

dp[m] [target]//0表示第一个房子，我只用存最多target个值

初始化的话只有01，一种情况，target <= index

转移方程的话，首先看这位是否已经涂好了，如果和上一位颜色相同dp[index+1] [target] = dp[index] [target]

否则的话就dp[index+1] [target+1] = dp[index] [target]

好像也要判断一下如果第二位>= target直接放弃，

如果没涂好就可以进行上色分为两种情况上和前一个颜色同样的颜色，不同的颜色中最小的

最后result 是dp[m-1] [target-1];

写的时候发现不对，怎么判断上一个颜色是什么啊，呆住，发现是个三维dp 好恐怖，还得记录这一位结尾颜色

![image-20210505112616596](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210505112616596.png)



![image-20210505112630967](C:\Users\ZiXiao Huang\AppData\Roaming\Typora\typora-user-images\image-20210505112630967.png)

~~~java
class Solution {
    int INF = 0x3f3f3f3f;
    public int minCost(int[] hs, int[][] cost, int m, int n, int t) {
        int[][][] f = new int[m + 1][n + 1][t + 1];

        // 不存在分区数量为 0 的状态
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                f[i][j][0] = INF;
            }
        }

        for (int i = 1; i <= m; i++) {
            int color = hs[i - 1];
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= t; k++) {
                    // 形成分区数量大于房子数量，状态无效
                    if (k > i) {
                        f[i][j][k] = INF;
                        continue;
                    }

                    // 第 i 间房间已经上色
                    if (color != 0) {
                        if (j == color) { // 只有与「本来的颜色」相同的状态才允许被转移
                            int tmp = INF;
                            // 先从所有「第 i 间房形成新分区」方案中选最优（即与上一房间颜色不同）
                            for (int p = 1; p <= n; p++) {
                                if (p != j) {
                                    tmp = Math.min(tmp, f[i - 1][p][k - 1]);
                                }
                            }
                            // 再结合「第 i 间房不形成新分区」方案中选最优（即与上一房间颜色相同）
                            f[i][j][k] = Math.min(f[i - 1][j][k], tmp);
                        
                        } else { // 其余状态无效  
                            f[i][j][k] = INF;
                        }

                    // 第 i 间房间尚未上色
                    } else {
                        int u = cost[i - 1][j - 1]; 
                        int tmp = INF;
                        // 先从所有「第 i 间房形成新分区」方案中选最优（即与上一房间颜色不同）
                        for (int p = 1; p <= n; p++) {
                            if (p != j) {
                                tmp = Math.min(tmp, f[i - 1][p][k - 1]);
                            }
                        }
                        // 再结合「第 i 间房不形成新分区」方案中选最优（即与上一房间颜色相同）
                        // 并将「上色成本」添加进去
                        f[i][j][k] = Math.min(tmp, f[i - 1][j][k]) + u;
                    }
                }
            }
        }

        // 从「考虑所有房间，并且形成分区数量为 t」的所有方案中找答案
        int ans = INF;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, f[m][i][t]);
        }
        return ans == INF ? -1 : ans;
    }
}

~~~

