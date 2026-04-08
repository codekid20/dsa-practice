package arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class mergeIntervals {
    public static void main(String[] args) {

    }

    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> ans = new ArrayList<>();
        ans.add(intervals[0]);

        for(int i = 1; i < intervals.length; i++){
            int[] current = intervals[i];
            int[] last = ans.get(ans.size() - 1); // last merged interval

            if(current[0] <= last[1]){
                last[1] = Math.max(last[1], current[1]);
            } else {
                // no overlap -> add as new interval
                ans.add(current);
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }
}
