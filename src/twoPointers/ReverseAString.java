package twoPointers;

public class ReverseAString {
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        System.out.println(s);
        reverseString(s);
        System.out.println(s);

    }

    public static void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right){
            char c = s[left];
            s[left] = s[right];
            s[right] = c;

            left++;
            right--;
        }
    }
}
