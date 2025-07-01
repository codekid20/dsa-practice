package DynammicProgramming;

public class shortestCommonSupersequence {
    public static void main(String[] args) {
        String str1 = "aaaaaaaa", str2 = "aaaaaaaa";
        System.out.println(shortestCommonSupersequence(str1,str2));
    }

    public static String shortestCommonSupersequence(String str1, String str2) {

        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        for (int pos1 = 1; pos1 <= n; pos1++) {
            for (int pos2 = 1; pos2 <=  m; pos2++) {
                if(str1.charAt(pos1 - 1) == str2.charAt(pos2 - 1)){
                    dp[pos1][pos2] = 1 + dp[pos1 - 1][ pos2 - 1];
                } else {
                    dp[pos1][pos2] = Math.max(dp[pos1 - 1][ pos2], dp[pos1] [pos2 - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = n;
        int j = m;

        while (i > 0 && j > 0){
            if(str1.charAt(i-1) == str2.charAt(j-1)){
                sb.append(str1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                sb.append(str1.charAt(i-1));
                i--;
            } else {
                sb.append(str2.charAt(j-1));
                j--;
            }
        }

        while(i > 0) {
            sb.append(str1.charAt(i-1));
            i--;
        }

        while (j > 0){
            sb.append(str2.charAt(j-1));
            j--;
        }

        return sb.reverse().toString();
    }
}
