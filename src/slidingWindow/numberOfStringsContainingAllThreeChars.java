package slidingWindow;

public class numberOfStringsContainingAllThreeChars {
    public static void main(String[] args) {
        String s = "aaacb";
        System.out.println(numberOfSubstrings2(s));
    }

    public static int numberOfSubstrings(String s) {
        int[] lastSeen = {-1, -1, -1};
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
            if(lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1){
                count += (1 + Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2])));
            }
        }

        return count;
    }

    public static int numberOfSubstrings2(String s) {
        // {2, 4, 3}
        int[] lastSeen = {-1, -1, -1};
        int count = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            lastSeen[s.charAt(i) - 'a'] = i;
            if(lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1){
                count += (s.length() - Math.max(lastSeen[0], Math.max(lastSeen[1], lastSeen[2])));
            }
        }

        return count;
    }
}
