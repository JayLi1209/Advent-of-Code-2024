package Day_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D12 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_12/input.txt");
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
        int m = board.length, n = board[0].length, result = 0, num = 1;
        int[][] used = new int[m][n];
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(used[i][j] == 0){
                    num++;
                    int peri = dfs(board, used, i, j, board[i][j], num);
                    int area = countArea(used, num);
                    result += peri * area;
                    // System.out.println(board[i][j] + " " + result + " " + peri + " " + area);
                }
            }
        }

        System.out.println("Q1: " + result);
    }

    private static int countArea(int[][] used, int num){
        int count = 0;
        for(int i = 0; i < used.length; ++i){
            for(int j = 0; j < used[0].length; ++j){
                if(used[i][j] == num) ++count;
            }
        }
        return count;
    }

    private static int dfs(char[][] board, int[][] used, int row, int col, char c, int num){
        if(!valid_grid(board, row, col) || used[row][col] != 0 || board[row][col] != c){
            return 0;
        }

        used[row][col] = num;

        int count = additional_perims(board, row, col, board[row][col]);

        return count + 
            dfs(board, used, row+1, col, c, num)+
            dfs(board, used, row-1, col, c, num)+
            dfs(board, used, row, col+1, c, num)+
            dfs(board, used, row, col-1, c, num);
    }


    private static boolean valid_grid(char[][] board, int r, int c){
        // System.out.println("hello");
        // System.out.println(" board[0].length()" + board[0]);
        return r >= 0 && c >= 0 && r < board.length && c < board[0].length;
    }

    private static int additional_perims(char[][] board, int row, int col, char c){
        int count = 0;
        if(!valid_grid(board, row-1, col) || board[row-1][col] != c){
            count += 1;
        }
        if(!valid_grid(board, row+1, col) || board[row+1][col] != c){
            count += 1;
        }
        if(!valid_grid(board, row, col-1) || board[row][col-1] != c){
            count += 1;
        }
        if(!valid_grid(board, row, col+1) || board[row][col+1] != c){
            count += 1;
        }
        return count;
    }
}
