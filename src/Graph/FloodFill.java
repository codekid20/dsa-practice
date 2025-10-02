package Graph;

import java.util.Arrays;

public class FloodFill {
    public static void main(String[] args) {
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        int sr = 1, sc = 1, color = 2;
        int[][] ans = floodFill(image,sr,sc,color);
        for(int[] i : ans){
            System.out.println(Arrays.toString(i));
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        int[][] vis = new int[n][m];

        int originalColor = image[sr][sc];

        if(originalColor == color) return image;

        fillColor(image,sr,sc,vis,originalColor, color);
        return image;
    }

    private static void fillColor(int[][] image, int sr, int sc, int[][] vis, int originalColor,int color) {
        if(sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != originalColor){
            return;
        }

        if(vis[sr][sc] == 1){
            return;
        }

        vis[sr][sc] = 1;
        image[sr][sc] = color;

        fillColor(image, sr - 1, sc, vis, originalColor, color);
        fillColor(image, sr + 1, sc, vis, originalColor, color);
        fillColor(image, sr, sc - 1, vis, originalColor, color);
        fillColor(image, sr, sc + 1, vis, originalColor, color);

        return;
    }


    // Approach 2:
    public static int[][] floodFill2(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        if(color != originalColor){
            dfs1(image, sr, sc, originalColor, color);
        }

        return image;
    }

    private static void dfs1(int[][] image, int sr, int sc, int originalColor, int color) {
        if(image[sr][sc] == originalColor){
            image[sr][sc] = color;

            if(sr >= 1){
                dfs1(image,sr - 1, sc, originalColor, color); // UP
            }

            if(sc >= 1){
                dfs1(image, sr, sc - 1, originalColor, color); // Left
            }

            if(sr + 1 < image.length){
                dfs1(image, sr + 1, sc, originalColor, color); // Down
            }

            if(sc + 1 < image[0].length){
                dfs1(image, sr, sc + 1, originalColor, color); // Right
            }
        }
    }
}
