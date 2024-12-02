package slidingWindow;

public class repeatingCharacterReplacement {
    public static void main(String[] args) {
        int k = 2;
        String s = "ABAB";

        System.out.println(characterReplacement(s,k));

    }

    private static int characterReplacement(String s, int k) {
        int start = 0;
        int[] charCount = new int[26];
        int maxLength = 0;
        int maxFrequency = 0;

        for (int end = 0; end < s.length(); end++) {
            int currentChar = s.charAt(end) - 'A';

            charCount[currentChar]++;
            maxFrequency = Math.max(maxFrequency, charCount[currentChar]);


            Boolean isValid = (end + 1 - start - maxFrequency <= k);

            if (!isValid){
               int outgoingChar = s.charAt(start) - 'A';

               charCount[outgoingChar]--;

               start++;
            }

            maxLength = end - start + 1;
        }

        return maxLength;
    }
}
