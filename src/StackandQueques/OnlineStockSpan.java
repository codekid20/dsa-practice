package StackandQueques;

import java.util.Stack;

public class OnlineStockSpan {

    public static void main(String[] args) {
        OnlineStockSpan s = new OnlineStockSpan();
        System.out.println(s.next(100));
        System.out.println(s.next(80));
        System.out.println(s.next(60));
        System.out.println(s.next(70));
        System.out.println(s.next(60));
        System.out.println(s.next(75));
    }
    Stack<int[]> st;
    public OnlineStockSpan() {
        st = new Stack<>();
    }

    public int next(int price) {
        int ans = 1;

        while(!st.isEmpty() && st.peek()[0] < price){
            ans += st.pop()[1];
        }

        st.push(new int[]{price, ans});

        return ans;
    }
}
