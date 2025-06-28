package twoPointers;

import java.util.Arrays;

public class BoatsToSavePeople {
    public static void main(String[] args) {
        int[] people = {5,1,4,2};
        int limit = 6;

        System.out.println(numRescueBoats(people, limit));
    }

    // Time Complexity :- BigO(NlogN)
    // Space Complexity :- BigO(1)
    public static int numRescueBoats(int[] people, int limit) {

        int left = 0;
        int right = people.length - 1;
        int count = 0;
        Arrays.sort(people);
        while (left <= right){
            if(people[left] + people[right] > limit){
                if(people[left] > people[right]){
                    left++;
                } else {
                    right--;
                }

                count++;
            } else {
                left++;
                right--;
                count++;
            }
        }

        return count;
    }
}
