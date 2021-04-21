package LC.s100;

class LC92 {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[3];
        char[] arr = s.toCharArray();
        dp[0] = arr[0] == '0'?0:1;
        dp[1] = 1;
        for(int i = 1; i < n; i++) {
            dp[2] = dp[1];
            dp[1] = dp[0];
            //!!!!!!!!
            dp[0] = 0;
            int a = arr[i] - '0';
            int b = (arr[i-1]-'0')*10+arr[i]-'0';
            if(a>=1 && a <=9) dp[0] = dp[1];
            if(b>=10 && b<=26){
                if(b == 10 || b == 20){
                    dp[0] = dp[2];
                }
                else{
                    dp[0]+=dp[2];
                }
            }

        }
        return dp[0];

    }
}
