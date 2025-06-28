package twoPointers;

public class validPalindrome {
    public static void main(String[] args) {
        String s = "abccba";
        System.out.println(isPalindrome3(s));
    }

    public static boolean isPalindrome(String s) {
        String str = s.toLowerCase();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = str.charAt(i);

            if (ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9') {
                sb.append(ch);
            }
        }

        if(sb.toString().equals(sb.reverse().toString())){
            return true;
        }

        return false;
    }


    public static boolean isPalindrome1(String s) {
        String str = s.toLowerCase();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = str.charAt(i);

            if (ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9') {
                sb.append(ch);
            }
        }

        String ss = sb.toString();
        int left = 0;
        int right = ss.length() - 1;

        while (left < right){
            if(ss.charAt(left) == ss.charAt(right)){
                left++;
                right--;
            } else {
                break;
            }
        }

        return !(left < right);
    }

    public static boolean isPalindrome3(String s){
        char[] ch = s.toCharArray();
        int left = 0;
        int right = ch.length - 1;

        while(left < right){
            if(ch[left] != ch[right]){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
