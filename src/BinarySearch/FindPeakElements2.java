package BinarySearch;

public class FindPeakElements2 {
    public static void main(String[] args) {

    }

    // 1. pick a middle column and find max element in it.
    // 2. If that max element is greater then its left and right adjacent then it is the peak element.
    // 3. If left is greater than this max element, answer lies towards left, move there. (high = mid - 1).
    // 4. If right is greater, answer lies towards right. move there.(low = mid + 1)
    public static int[] findPeakGrid(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        int low = 0;
        int high = m - 1;

        while (low <= high){

            int mid = low + (high - low) / 2;

            int maxRowIndex = findMaxIndex(mat, n, m, mid);

            int left = mid - 1 >= 0 ? mat[maxRowIndex][mid - 1] : -1;
            int right = mid + 1 < m ? mat[maxRowIndex][mid + 1] : -1;

            if(mat[maxRowIndex][mid] > left && mat[maxRowIndex][mid] > right){
                return new int[]{maxRowIndex, mid};
            } else if (mat[maxRowIndex][mid] < left) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[]{-1, -1};
    }

    private static int findMaxIndex(int[][] mat, int n, int m, int col) {
        int maxValue = -1;
        int index = -1;

        for (int i = 0; i < n; i++) {
            if(mat[i][col] > maxValue){
                maxValue = mat[i][col];
                index = i;
            }
        }

        return index;
    }
}
