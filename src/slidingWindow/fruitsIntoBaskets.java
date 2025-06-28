package slidingWindow;

import java.util.HashMap;

public class fruitsIntoBaskets {
    public static void main(String[] args) {
        int[] nums = {1,2,3,2,2};
        System.out.println(totalFruit(nums));
    }

    public static int totalFruit(int[] fruits) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLen = 0;
        while(right < fruits.length){
            map.put(fruits[right], map.getOrDefault(fruits[right],0) + 1);

            if(map.size() > 2){
                map.put(fruits[left], map.getOrDefault(fruits[left], 0) - 1);
                if(map.get(fruits[left]) == 0){
                    map.remove(fruits[left]);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }
}
