package recursion;

import java.util.ArrayList;
import java.util.Arrays;

public class recursionArray {
    public static void main(String[] args) {
        int[] arr = {1, 10, 3, 4, 5, 6, 3};
//        System.out.println(sorted(arr, 0));
//        System.out.println(linearSearch(arr, 11, 0));
//        System.out.println(linearSearch1(arr, 11, 0));

        System.out.println(findAllIndex(arr, 3, 0));
    }

    static boolean sorted(int[] arr, int index){
        if(index == arr.length - 1){
            return true;
        }

        return arr[index] < arr[index + 1] && sorted(arr, index + 1);
    }

    static boolean linearSearch(int[] arr, int target, int index){
        if(index == arr.length){
            return false;
        }

        return arr[index] == target || linearSearch(arr, target, index + 1);
    }

    static int linearSearch1(int[] arr, int target, int index){
        if(index == arr.length){
            return -1;
        }

        if(arr[index] == target){
            return index;
        } else {
            return linearSearch1(arr, target, index + 1);
        }
    }

    static ArrayList<Integer> findAllIndex(int[] arr,int target, int index){
        ArrayList<Integer> list = new ArrayList<>();
        if(index == arr.length){
            return list;
        }

        if(arr[index] == target){
            list.add(index);
        }

        ArrayList<Integer> ansFromBelowCalls = findAllIndex(arr, target, index + 1);
        list.addAll(ansFromBelowCalls);

        return list;
    }
}
