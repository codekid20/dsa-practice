package recursion;
import java.util.ArrayList;
import java.util.List;
public class subsets {

    public static void main(String[] args) {

    }
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();

        backtrack(0, nums, res, subset);

        return res;
    }

    private void backtrack(int start, int[] nums, List<List<Integer>> res, List<Integer> subset) {

        res.add(new ArrayList<>(subset));

        for(int i = start; i < nums.length; i++){
            subset.add(nums[i]); // Choose
            backtrack(i + 1, nums, res, subset); // Explore
            subset.remove(subset.size() - 1); // Unchoose
        }
    }
}
