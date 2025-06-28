package recursion;

import java.util.ArrayList;

public class permutations {
    public static void main(String[] args) {
        String str = "abc";
//        System.out.println(str.substring(0,1));
//        System.out.println(str.substring(1,str.length()));
//        permutation("", str);
//        System.out.println(permutationList("",str));
        System.out.println(permutationCount("", str));
    }

    static void permutation(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String first = p.substring(0, i);
            String second = p.substring(i, p.length());
            permutation(first + ch + second,up.substring(1));

        }
    }

    static ArrayList<String> permutationList(String p, String up){
        if(up.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i <= p.length(); i++) {
            String first = p.substring(0, i);
            String second = p.substring(i, p.length());
            ArrayList<String> l = permutationList(first + ch + second,up.substring(1));
            ans.addAll(l);
        }

        return ans;
    }

    static int permutationCount(String p, String up){
        if(up.isEmpty()){
            return 1;
        }
        int count = 0;
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String first = p.substring(0, i);
            String second = p.substring(i, p.length());
            count += permutationCount(first + ch + second,up.substring(1));
        }

        return count;
    }
}
