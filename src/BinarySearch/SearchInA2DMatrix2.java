package BinarySearch;

public class SearchInA2DMatrix2 {
    public static void main(String[] args) {

    }

    public boolean searchMatrix(int[][] matrix, int target) {

        int n = matrix.length;
        int m = matrix[0].length;

        int row = 0;
        int col = m - 1;

        while (row < n && col >= 0){

            if(matrix[row][col] == target){
                return true;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                col--;
            }
        }

        return false;
    }
}
