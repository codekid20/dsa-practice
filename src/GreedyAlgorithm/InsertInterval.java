package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
    public static void main(String[] args) {
        int[][] arr = {{1,3}, {6,9}};
        int[] newArr = {0,2};

        System.out.println(Arrays.deepToString(insert(arr, newArr)));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {

        int n = intervals.length;
        int i = 0;
        ArrayList<int[]> ans = new ArrayList<>();

        while (i < n && intervals[i][1] < newInterval[0]){

            ans.add(intervals[i]);
            i++;
        }

        while (i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        ans.add(newInterval);


        while (i < n) {
            ans.add(intervals[i]);
            i++;
        }

        return ans.toArray(new int[ans.size()][]);

    }
}
