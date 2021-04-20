package LC.s100;

public class LC10 {
    public boolean isMatch(String a, String b){
        //注意考虑.*哦
        if(b.length() == 0)return a.length() == 0;
        int m = a.length();
        int n = b.length();
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        dp[0][1] = false;//因为这里要专门判断
        //initial state
        for(int i = 2; i <= n; i++){
            if(bb[i-1] == '*')
            dp[0][i] = dp[0][i-2];
            else{
                dp[0][i] = false;
            }
        }
        for(int i = 1; i <= m; i++){
            for(int j = 1; j<=n;j++){
                char temp = bb[j-1];
                if(temp == '.' || temp == aa[i-1])
                    dp[i][j] = dp[i-1][j-1];
                if(temp == '*'){
                    if(bb[j-2] == aa[i-1] || bb[j-2] == '.'){//这里忘了考虑.
                        dp[i][j] = dp[i][j-2]||dp[i-1][j];//这里新增情况
                    }
                    else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
