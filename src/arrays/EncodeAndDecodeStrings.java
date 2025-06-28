package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeAndDecodeStrings {
    public static void main(String[] args) {
        String[] s = {"leet","code"};
        String ss = encode(s);
        System.out.println(Arrays.toString(decode(ss)));
    }

    public static String encode(String s[]) {
        StringBuilder sb = new StringBuilder();
        for(String str : s){
            int len = str.length();
            sb.append(len);
            sb.append("#");
            sb.append(str);
        }

        return sb.toString();
    }

    public static String[] decode(String s) {
        List<String> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < s.length()){
            j = i;
            while (s.charAt(j) != '#'){
                j++;
            }

            int len = Integer.parseInt(s.substring(i,j));
            i = j + 1;
            j = i + len;
            list.add(s.substring(i,j));
            i = j;
        }

        String[] str = new String[list.size()];
        for (int k = 0; k < list.size(); k++) {
            str[k] = list.get(k);
        }

        return str;
    }
}
