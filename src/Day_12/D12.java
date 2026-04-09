package Day_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D12 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Day_12/data.txt");
        Scanner scanner = new Scanner(file);
        List<List<Character>> list = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            List<Character> tmp = new ArrayList<>();
            for(int i = 0; i < line.length(); ++i){
                tmp.add(line.charAt(i));
            }
            list.add(new ArrayList<>(tmp));
        }

        char[][] arr = list.stream()
                .map(row -> row.stream()
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString()
                        .toCharArray())
                .toArray(char[][]::new);

        question1(arr);
    }

    private static void question1(char[][] board){
        int m = board.length, n = board[0].length, result = 0;
        boolean[][] used = new boolean[m][n];
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(!used[i][j]){
                    result += dfs(board, used, i, j);
                }
            }
        }

        System.out.println("Q1: " + result);
    }

    private static int dfs(char[][] board, boolean[][] used, int row, int col){
        
    }
}
