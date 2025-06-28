package Trees;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        binaryTree tree = new binaryTree();
        tree.populate(scanner);
        tree.display();
    }
}
