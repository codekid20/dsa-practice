package twoPointers;

public class validPalindrome2 {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(validPalindrome(s));
    }

    // Algorithm:
//    Start with two pointers:
//    left = 0, right = s.length() - 1
//    While left < right:
//    If s[left] == s[right], move both pointers inward
//    If s[left] != s[right]:
//    Try skipping the left character: check if s[left+1...right] is a palindrome
//    Or try skipping the right character: check if s[left...right-1] is a palindrome
//    If either of those is true → return true
//    If loop completes without any mismatches or passes the skip check → return true
    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right){
            if(s.charAt(left) != s.charAt(right)){

                return valid(s, left + 1, right) || valid(s, left, right - 1);
            }

            left++;
            right--;
        }

        return true;
    }

    private static boolean valid(String s, int left, int right) {
        while (left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
