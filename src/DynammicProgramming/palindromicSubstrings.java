package DynammicProgramming;

public class palindromicSubstrings {
    public static void main(String[] args) {

    }

    public static int countSubstrings(String s) {

        int total = 0;

        for (int i = 0; i < s.length(); i++) {

            // Odd-length palindrome
            total += expandAroundCentre(s, i, i);

            // even length palindrome
            total += expandAroundCentre(s, i, i + 1);
        }

        return total;
    }

    private static int expandAroundCentre(String s, int left, int right) {

        int count = 0;

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {

            count++;
            left--;
            right++;
        }

        return count;
    }
}
