package Heaps;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {
    public static void main(String[] args) {
        String s = "zrhmhyevkojpsegvwolkpystdnkyhcjrdvqtyhucxdcwm";
        System.out.println(reorganizeString(s));
    }

    public static String reorganizeString(String s) {
        int[] charCount = new int[26];

        for(char ch : s.toCharArray()){
            charCount[ch - 'a']++;
        }

        int max = 0;
        int letter = 0;

        for (int i = 0; i < charCount.length; i++) {
            if(charCount[i] > max){
                max = charCount[i];
                letter = i;
            }
        }

        if(max > (s.length() + 1) / 2){
            return "";
        }

        char[] res = new char[s.length()];

        int idx = 0;

        while(charCount[letter] > 0){
            res[idx] = (char) (letter + 'a');
            idx += 2;
            charCount[letter] --;
        }

        for(int i = 0; i < charCount.length; i++){
            while (charCount[i] > 0){
                if(idx >= res.length){ // IT COULD BE POSSIBLE I STILL HAVE SOME SLOTS LEFT TO PLACE A CHARACTER AFTER PLACING ALL THE CHARACTERS OF MAX FREQUENCY. THAT IS WHY I WILL FIRST PLACE THAT CHARACTER IN THE END AND THEN IF REACHES END OF THE STRING RESET THE POINTER BACK TO 1
                    idx = 1;
                }


                res[idx] = (char) (i + 'a');
                idx += 2;
                charCount[i]--;
            }
        }

        return String.valueOf(res);
    }


    public static String reorganizeString2(String s) {
        Map<Character, Integer> map  = new HashMap<>();
        for(char c : s.toCharArray()){
            int count = map.getOrDefault(c, 0) + 1;

            if(count > (s.length() + 1) / 2){
                return "";
            }

            map.put(c, count);
        }


        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[1] - a[1]);

        for (char c : map.keySet()){
            pq.add(new int[]{c, map.get(c)});
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()){
            int[] first = pq.poll();
            if(sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)){
                sb.append((char) first[0]);

                if(--first[1] > 0){
                    pq.add(first);
                }
            } else {
                int[] second = pq.poll();
                sb.append((char) second[0]);
                if(--second[1] > 0){
                    pq.add(second);
                }

                pq.add(first);
            }
        }

        return sb.toString();
    }
}
