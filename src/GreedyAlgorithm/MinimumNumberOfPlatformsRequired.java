package GreedyAlgorithm;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MinimumNumberOfPlatformsRequired {
    public static void main(String[] args) {

    }

    public static int minPlatform(int[] arr, int[] dep) {

        int n = arr.length;
        Arrays.sort(arr);
        Arrays.sort(dep);
        int ans = 0;
        int j = 0;
        int count = 0;

        for (int k : arr) {

            while (j < n && dep[j] < k) {
                count--;
                j++;
            }

            count++;

            ans = Math.max(count, ans);
        }

        return ans;
    }
}
