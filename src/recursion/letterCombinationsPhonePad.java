package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class letterCombinationsPhonePad {
    public static void main(String[] args) {
        pad("","12");
        //System.out.println(padList("",""));
    }
    static void pad(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }

        int digit = up.charAt(0) - '0'; // this will convert '0' to 0
        for (int i = (digit - 1) * 3; i < (digit * 3); i++) {
            char ch = (char)('a' + i);
            pad(p + ch, up.substring(1));
        }
    }
    static ArrayList<String> padList(String p, String up){
        if(up.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> ans = new ArrayList<>();
        int digit = up.charAt(0) - '0'; // this will convert '0' to 0
        for (int i = (digit - 2) * 3; i < (digit - 1) * 3; i++) {
            char ch = (char)('a' + i);
            ans.addAll(padList(p + ch, up.substring(1)));
        }

        return ans;
    }
}
