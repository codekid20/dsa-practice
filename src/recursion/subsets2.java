package recursion;
import java.util.*;
public class subsets2 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(0, nums, ans, subset);

        return ans;
    }

    private void backtrack(int start, int[] nums, List<List<Integer>> ans, List<Integer> subset) {
        ans.add(new ArrayList<>(subset));
        for (int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i - 1]) continue;
            subset.add(nums[i]);
            backtrack(i + 1, nums, ans, subset);
            subset.remove(subset.size() - 1);
        }
    }
}
