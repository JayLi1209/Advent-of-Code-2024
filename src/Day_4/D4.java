package Day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D4 {

    private static int count = 0;
    private static int count2 = 0;
    private static final char[] truth = {'X', 'M', 'A', 'S'};
    private static final int[][] dirs = {{1,0}, {1,1}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}};
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_4/input.txt");
        Scanner scanner = new Scanner(file);

        List<List<Character>> board = new ArrayList<>();

        int numLine = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            board.add(new ArrayList<>());
            for(int i = 0; i < line.length(); ++i){
                board.get(numLine).add(line.charAt(i));
            }
            ++numLine;
        }

        char[][] arr = board.stream()
                .map(row -> row.stream()
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString()
                        .toCharArray())
                .toArray(char[][]::new);

        question1(arr);
        question2(arr);
    }

    private static void question1(char[][] board){
        int m = board.length, n = board[0].length;
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(board[i][j] == 'X'){
                    for(int k = 0; k < 8; ++k){
                        dfs(board, i, j, 0, k);
                    }
                }
            }
        }
        System.out.println("Q1: " + count);
    }

    private static void dfs(char[][] board, int i, int j, int idx, int k){
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != truth[idx]){
            return;
        }

        if(idx == 3) {
            count++;
            return;
        }
        dfs(board, i+dirs[k][0], j+dirs[k][1], idx+1, k);
    }

    private static void question2(char[][] board){
        int m = board.length, n = board[0].length;
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(board[i][j] == 'A'){
                    judge(board, i, j);
                }
            }
        }
        System.out.println("Q2: " + count2);
    }

    private static void judge(char[][] board, int i, int j){
        if(i < 1 || j < 1 || i >= board.length-1 || j >= board[0].length-1){
            return;
        }
        boolean condition1 = (board[i-1][j-1] == 'M' && board[i+1][j+1] == 'S') ||
                (board[i-1][j-1] == 'S' && board[i+1][j+1] == 'M');
        boolean condition2 = (board[i-1][j+1] == 'M' && board[i+1][j-1] == 'S') ||
                (board[i-1][j+1] == 'S' && board[i+1][j-1] == 'M');
        if(condition1 && condition2) count2++;
    }
}
