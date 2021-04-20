package LC.s100;

class LC5 {
    public String longestPalindrome(String s){
        if(s == null || s.length() == 0)return null;
        int n = s.length();
        char[] arr = s.toCharArray();
        boolean[][] dp = new boolean[n+1][n+1];
        int max = 1;
        int startIndex = 0,endIndex = 0;
        for(int i = 0; i <= n; i++){
            dp[i][i] = true;
        }
        for(int i = n-1; i>0;i--){
            for(int j = i+1; j <= n; j++){
                if(arr[i-1] == arr[j-1] && ((j-i)<=2 || dp[i+1][j-1])){
                    dp[i][j] = true;
                    if(j-i+1 > max){
                        max = j-i+1;
                        startIndex = i-1;
                        endIndex = j-1;
                    }
                }
            }
        }
        return s.substring(startIndex,endIndex+1);
    }
}
