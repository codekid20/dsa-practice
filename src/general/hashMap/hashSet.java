package general.hashMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class hashSet {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);

        System.out.println(Arrays.toString(set.toArray())); // CONVERTS SET ELEMENTS TO ARRAY

        // Iterates over set elements
        for(int num : set){
            System.out.println(num);
        }

        // Another way to iterate
//        Iterator<Integer> it = set.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }

    }
}
