package Graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class kPairsWithSmallestSums {
    public static void main(String[] args) {

    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        int m = nums1.length;
        int n = nums2.length;

        List<List<Integer>> ans = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> (a[0] - b[0]));

        minHeap.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        visited[0][0] = true;

        while(k-- > 0 && !minHeap.isEmpty()){

            int[] top = minHeap.poll();

            int i = top[1];
            int j = top[2];

            ans.add(List.of(nums1[i], nums2[j]));

            if(i + 1 < m && !visited[i+1][j]){
                visited[i + 1][j] = true;
                minHeap.offer(new int[]{nums1[i+1] + nums2[j], i + 1, j});
            }

            if(j + 1 < n && !visited[i][j + 1]){
                visited[i][j + 1] = true;
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }
        return ans;
    }
}
