package Graph;

import java.util.Arrays;

public class SurroundedRegions {
    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve1(board);
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Approach 1: but incorrect:

    public static void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                surround(board,row,col,visited);
            }
        }
    }

    public static void surround(char[][] board, int row, int col,boolean[][] visited){
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O' || visited[row][col]){
            return;
        }

        visited[row][col] = true;

        if(row != 0 && row != board.length - 1 && col != 0 && col != board[0].length - 1){

            board[row][col] = 'X';

        }

        surround(board, row - 1, col,visited);
        surround(board, row + 1, col,visited);
        surround(board, row, col - 1,visited);
        surround(board, row, col + 1,visited);

        return;
    }


    // Approach 2: correct
    public static void solve1(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            dfs(board, i,0);
            dfs(board, i, board[0].length - 1);
        }

        for (int i = 1; i < board[0].length - 1; i++) {
            dfs(board, 0, i);
            dfs(board, board.length - 1, i);
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if(board[row][col] == 'O'){
                    board[row][col] = 'X';
                }

                if(board[row][col] == '*'){
                    board[row][col] = 'O';
                }
            }
        }
    }

    private static void dfs(char[][] board, int row, int col) {
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length){
            return;
        }

        if(board[row][col] == 'X' || board[row][col] == '*'){
            return;
        }

        board[row][col] = '*';

        dfs(board, row-1, col);
        dfs(board, row+1, col);
        dfs(board, row, col-1);
        dfs(board, row, col+1);

        return;
    }


    // Approach 3:

    public static void solve2(char[][] board) {
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        int n = board.length;
        int m = board[0].length;

        int[][] vis = new int[n][m];

        // traverse first row and last row
        for (int i = 0; i < m; i++) {
            if(vis[0][i] == 0 && board[0][i] == 'O'){
                dfs1(0,i,vis,board,delRow,delCol);
            }

            if(vis[n-1][i] == 0 && board[n-1][i] == 'O'){
                dfs1(n - 1, i, vis, board, delRow, delCol);
            }
        }

        for (int i = 0; i < n; i++) {
            if(vis[i][0] == 0 && board[i][0] == 'O'){
                dfs1(i,0,vis,board,delRow,delCol);
            }

            if(vis[i][m-1] == 0 && board[i][m-1] == 'O'){
                dfs1(i,m-1,vis,board,delRow,delCol);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(vis[i][j] == 0 && board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void dfs1(int row, int col, int[][] vis, char[][] board, int[] delRow, int[] delCol){
        vis[row][col] = 1;
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < 4; i++) {
            int nrow = row + delRow[i];
            int ncol = col + delCol[i];

            if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0 && board[nrow][ncol] == 'O'){
                dfs1(nrow,ncol,vis,board,delRow,delCol);
            }
        }
    }
}
