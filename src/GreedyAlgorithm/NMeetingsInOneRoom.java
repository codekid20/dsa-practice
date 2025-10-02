package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class NMeetingsInOneRoom {
    public static void main(String[] args) {

    }

    static class Meeting {

        int start;
        int end;

        public Meeting(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    public static int maxMeetings(int[] start, int[] end) {

        int n = start.length;

        List<Meeting> meetings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            meetings.add(new Meeting(start[i], end[i]));
        }

        meetings.sort((a,b) -> Integer.compare(a.end, b.end));

        int count = 0;
        int lastEnd = Integer.MIN_VALUE;

        for(Meeting m : meetings) {
            if(m.start > lastEnd) {
                count++;
                lastEnd = m.end;
            }
        }

        return count;
    }
}
