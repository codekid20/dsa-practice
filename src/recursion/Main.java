package recursion;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        int[] arr = {4, 1, 6, 9, 8};
//        reverseArray(arr, 0, arr.length);
//        System.out.println(Arrays.toString(arr));

//        String str = "aba";
//        System.out.println(isPalindrome(0, str));

//        System.out.println(fibonacci(5));
//        System.out.println(factorial(4));
//        System.out.println(digitProd(12));

//        reverseNumber1(1234);
//        System.out.println(sum);

//        System.out.println(reverseNumber2(1234));

        System.out.println(countZeroes(1111));

    }

    public static void recursion(int i, int num){
        if(i > num){
            return;
        }
        recursion(i + 1, num);
        System.out.print(i + " ");
    }

    public static void reverseArray(int[] arr, int i, int n){
        if(i >= n / 2){
            return;
        }

        swap(arr, i, n - i - 1);
        reverseArray(arr, i + 1, n);
    }

    public static void swap(int[] arr, int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;

        return;
    }

    public static boolean isPalindrome(int i, String str){
        if(i >= str.length() / 2){
            return true;
        }

        if(str.charAt(i) != str.charAt(str.length() -i - 1)){
            return false;
        }

        return isPalindrome(i + 1, str);
    }

    public static int fibonacci(int num){
        if(num <= 1){
            return num;
        }

        int last = fibonacci(num - 1);
        int secondLast = fibonacci(num - 2);

        return last + secondLast;
    }

    public static void arraySequence(int index, ArrayList<Integer> ds, int[] arr, int len){
        if (index == len){

        }
        
    }

    public static int factorial(int num){
        if(num == 1){
            return 1;
        }

        return num * factorial(num - 1);
    }

    public static int digitSum(int num){
        if(num == 0){
            return 0;
        }

        return num % 10 + digitSum(num / 10);
    }

    public static int digitProd(int num){
        if(num % 10 == num){
            return num;
        }

        return num % 10 * digitProd(num / 10);
    }

    static int sum = 0;
    public static void reverseNumber1(int num){
        if(num == 0){
            return;
        }
        int rem = num % 10;
        sum = sum * 10 + rem;

        reverseNumber1(num / 10);
    }

    public static int reverseNumber2(int num){

        int digits = (int)(Math.log10(num)) + 1;

        return helper(num, digits);
    }

    private static int helper(int num, int digits) {
        if(num % 10 == num){
            return num;
        }
        int rem = num % 10;
        return rem * (int)(Math.pow(10, digits -1)) + helper(num / 10, digits - 1 );
    }

    private static int countZeroes(int num){
        return helperCount(num, 0);
    }

    private static int helperCount(int num, int count) {
        if(num == 0){
            return count;
        }

        int rem = num % 10;
        if(rem == 0){
            return helperCount(num / 10, count + 1);
        }

        return helperCount(num / 10, count);
    }

}
