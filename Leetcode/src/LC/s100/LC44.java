package LC.s100;

class LC44 {
    Boolean[][] dp;
    public boolean isMatch(String s, String p) {
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        dp = new Boolean[ss.length][pp.length];
        return match(ss,pp,0,0);
    }
    private boolean match(char[] arr1,char[] arr2, int start1,int start2){
        int x = start1,y = start2;
        while(start1 < arr1.length && start2 < arr2.length){
            if(dp[start1][start2]!=null) return dp[start1][start2];
            if(arr1[start1] == arr2[start2] || arr2[start2] == '?'){
                start1++;
                start2++;
            }
            else if(arr2[start2] == '*'){
                while(start2 != arr2.length -1 && arr2[start2+1] == '*'){
                    start2++;
                }
                if(start2 == arr2.length -1){
                    dp[x][y] = true;
                    return true;
                }
                else{
                    boolean case1 = match(arr1,arr2,start1,start2+1);//empty
                    if(case1){
                        dp[x][y] = true;
                        return true;
                    }
                    char next = arr2[start2+1];

                    for(int i = start1; i < arr1.length; i++){
                        if((arr1[i] == next || next == '?') && match(arr1,arr2,i,start2+1)){
                            dp[x][y] = true;
                            return true;
                        }
                    }
                    dp[x][y] = false;
                    return false;
                }

            }
            else{
                dp[x][y] = false;
                return false;
            }
        }
        while(start2 < arr2.length && arr2[start2] == '*'){
            start2++;
        }
        if(start1 == arr1.length && start2 == arr2.length){
            //dp[x][y] = true;
            return true;
        }
        else{
            //dp[x][y] = false;
            return false;
        }
    }
}
//没考虑******
