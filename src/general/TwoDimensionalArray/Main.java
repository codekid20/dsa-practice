package general.TwoDimensionalArray;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{3,2,1},{4,5,6}};
//        System.out.println(Arrays.deepToString(arr));

        for (int[] a : arr){
            System.out.println(Arrays.toString(a));
        }
    }
}
