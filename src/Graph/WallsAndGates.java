package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    public static void main(String[] args) {

    }

    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if(rooms[r][c] == 0){
                    queue.offer(new int[]{r,c});
                }
            }
        }

        int distance = 0;
        int[] directions = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()){
            distance++;
            for (int i = 0; i < queue.size(); i++) {
                int[] position = queue.poll();
                int row = position[0];
                int col = position[1];
                for (int j = 0; j < 4; j++) {
                    int nrow = row + directions[j];
                    int ncol = col + directions[j + 1];

                    if(nrow >= 0 && nrow < rows && ncol >= 0 && ncol < cols && rooms[nrow][ncol] == Integer.MAX_VALUE){
                        rooms[nrow][ncol] = distance; // rooms[nrow][ncol] = rooms[row][col];
                        queue.offer(new int[]{nrow,ncol});
                    }
                }
            }
        }
    }
}
