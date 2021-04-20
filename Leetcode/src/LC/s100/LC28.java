package LC.s100;
//brute force skip
public class LC28 {
    //KMP solution
    public int strStr(String ss, String pp) {
        //edge case:
        if(pp.length() == 0) return 0;
        int n = ss.length(), m = pp.length();
        ss  = " "+ss;
        pp  = " "+pp;
        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();
        int[] next = new int[m+1];
        for(int i = 2,j = 0; i<=m; i++){
            while(j > 0 && p[i] != p[j+1]){
                j = next[j];
            }
            if(p[i] == p[j+1]){
                j++;
            }
            next[i] = j;
        }
        for(int i = 1, j = 0; i <= n; i++) {
            while (j > 0 && p[j + 1] != s[i])
                j = next[j];
            if (p[j + 1] == s[i]) {
                j++;
            }
            if (j == m) return i - m;
        }
        return -1;
    }
}
