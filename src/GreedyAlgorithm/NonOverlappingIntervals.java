package GreedyAlgorithm;
import java.util.ArrayList;
import java.util.List;
public class NonOverlappingIntervals {

    public static void main(String[] args) {
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    static class interval {

        int start;
        int end;

        public interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    // Idea is to count intervals we can choose without overlapping and then minus from total. (Similar to N Meetings).
    public static int eraseOverlapIntervals(int[][] intervals) {

        List<interval> list = new ArrayList<>();

        for(int[] r : intervals) {
            list.add(new interval(r[0], r[1]));
        }

        list.sort((a,b) -> Integer.compare(a.end, b.end));

        int count = 0;
        int lastEnd = Integer.MIN_VALUE;

        for(interval i : list) {
            if(i.start >= lastEnd) {
                count++;
                lastEnd = i.end;
            }
        }

        return list.size() - count;
    }
}
