package Graph;

public class BattleShipsInABoard {
    public static void main(String[] args) {
        char[][] board = {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
        System.out.println(countBattleships(board));
    }

    public static int countBattleships(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;
        int numberOfBattleship = 0;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++) {
                if(board[row][col] == 'X'){
                    numberOfBattleship++;
                    sink(board,row,col);
                }
            }
        }

        return numberOfBattleship;
    }

    private static void sink(char[][] board, int row, int col) {

        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'X') {
            return;
        }

        board[row][col] = '.';
        sink(board, row - 1, col);
        sink(board, row + 1, col);
        sink(board, row, col - 1);
        sink(board, row, col + 1);

        return;
    }
}
