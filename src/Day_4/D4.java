package Day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D4 {
    private static int count = 0;

    private static final char[] truth = {'X', 'M', 'A', 'S'};
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
                    boolean[][] visited = new boolean[m][n];
                    dfs(board, i, j, 0, visited);
                }
            }
        }
        System.out.println("Q1: " + count);
    }

    private static void dfs(char[][] board, int i, int j, int idx, boolean[][] visited){
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != truth[idx] || visited[i][j]){
            return;
        }

        visited[i][j] = true;

        if(idx == 3) {
            count++;
            return;
        }
        dfs(board, i+1, j, idx+1, visited);
        dfs(board, i-1, j, idx+1, visited);
        dfs(board, i, j+1, idx+1, visited);
        dfs(board, i, j-1, idx+1, visited);
        dfs(board, i+1, j+1, idx+1, visited);
        dfs(board, i-1, j-1, idx+1, visited);
        dfs(board, i-1, j+1, idx+1, visited);
        dfs(board, i+1, j-1, idx+1, visited);
    }

    private static void question2(char[][] board){

    }
}
