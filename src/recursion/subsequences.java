package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class subsequences {
    public static void main(String[] args) {
//        skip("", "abacdfa");
//        System.out.println(skipApple("aertapplebacdfa"));

//        subseq("", "abc");
//        System.out.println(subSeq("","abc"));

        int[] arr = {1, 2, 2};
        List<List<Integer>> list = subSetDuplicate(arr);
        for(List<Integer> l : list){
            System.out.println(l);
        }
    }

    static void skip(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }

        char ch = up.charAt(0);

        if(ch == 'a'){
            skip(p, up.substring(1));
        } else {
            skip(p + ch, up.substring(1));
        }
    }

    static String skip(String up){
        if(up.isEmpty()){
            return "";
        }
        char ch = up.charAt(0);

        if(ch == 'a'){
            return skip(up.substring(1));
        } else {
            return ch + skip(up.substring(1));
        }
    }

    static String skipApple(String up){
        if(up.isEmpty()){
            return "";
        }

        if(up.startsWith("apple")) {
           return skipApple(up.substring(5));
        } else {
            return up.charAt(0) + skipApple(up.substring(1));
        }
    }

    static void subseq(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        subseq(p + ch, up.substring(1));
        subseq(p, up.substring(1));
    }

    static ArrayList<String> subSeq(String p, String up){
        if(up.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> left = subSeq(p + ch, up.substring(1));
        ArrayList<String> right =   subSeq(p, up.substring(1));

        left.addAll(right);

        return left;
    }

    static List<List<Integer>> subSetIteration(int[] arr){
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());

        for(int num : arr){
           int n = outer.size();
           for(int i = 0; i < n; i++){
               ArrayList<Integer> internal = new ArrayList<>(outer.get(i));
               internal.add(num);
               outer.add(internal);
           }
        }

        return outer;
    }

    static List<List<Integer>> subSetDuplicate(int[] arr){
        Arrays.sort(arr);
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());
        int start = 0;
        int end = 0;
        for(int i=0; i < arr.length; i++){
            start = 0;
            if(i > 0 && arr[i] == arr[i-1]){
                start = end+1;
            }
            end = outer.size() - 1;
            int n = outer.size();
            for(int j = start; j< n; j++){
                ArrayList<Integer> internal = new ArrayList<>(outer.get(j));
                internal.add(arr[i]);
                outer.add(internal);
            }
        }
        return outer;
    }
}


