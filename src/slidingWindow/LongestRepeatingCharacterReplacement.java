package slidingWindow;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        String s = "ABAB";
        int k = 2;

        System.out.println(characterReplacement(s,k));
    }

    public static int characterReplacement(String s, int k) {
        int left = 0;
        int right = 0;
        int maxLen = 0;
        int maxFreq = 0;
        int[] charCount = new int[26];

        while(right < s.length()){
//            charCount[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, ++charCount[s.charAt(right) - 'A']);

            while (right - left + 1 - maxFreq > k){
                charCount[s.charAt(left) - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }
}
