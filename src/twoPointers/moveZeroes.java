package twoPointers;

import java.lang.reflect.Array;
import java.util.Arrays;

public class moveZeroes {
    public static void main(String[] args) {
        int[] arr = {0,1,0,3,12};
        moveZero(arr);
    }

    private static void moveZero(int[] arr) {
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != 0){
               arr[i] = arr[j];
               i++;
            }
        }

        if(i < arr.length) {
            for (int j = i; j < arr.length; j++) {
                arr[j] = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
