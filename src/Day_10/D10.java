package Day_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D10 {
    private static final char tar[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static int count1 = 0;
    private static int count2 = 0;
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_10/input.txt");
        Scanner scanner = new Scanner(file);

        List<List<Character>> board = new ArrayList<>();
        int[] start = new int[2];

        int numLine = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            board.add(new ArrayList<>());
            for(int i = 0; i < line.length(); ++i){
                if(line.charAt(i) == '^'){
                    start[0] = numLine;
                    start[1] = i;
                }
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

        List<int[]> nines = new ArrayList<>();

        for(int i = 0; i < arr.length; ++i){
            for(int j = 0; j < arr[0].length; ++j){
                if(arr[i][j] == '9'){
                    nines.add(new int[]{i, j});
                }
            }
        }

        question1(arr, nines);
        question2(arr, nines);
    }

    private static void question1(char[][] arr, List<int[]> nines){
        for(int i = 0; i < arr.length; ++i){
            for(int j = 0; j < arr[0].length; ++j){
                if(arr[i][j] == '0'){
                    for (int[] nine : nines) {
                        boolean[][] visited = new boolean[arr.length][arr[0].length];
                        int tmp = reachable(arr, nine, i, j, 0, visited);
                        if(tmp >= 1) count1 ++;
                    }
                }
            }
        }
        System.out.println("Q1: " + count1);
    }

    private static void question2(char[][] arr, List<int[]> nines){
        for(int i = 0; i < arr.length; ++i){
            for(int j = 0; j < arr[0].length; ++j){
                if(arr[i][j] == '0'){
                    for (int[] nine : nines) {
                        boolean[][] visited = new boolean[arr.length][arr[0].length];
                        int tmp = reachable(arr, nine, i, j, 0, visited);
                        count2 += tmp;
                    }
                }
            }
        }
        System.out.println("Q2: " + count2);
    }

    private static int reachable(char[][] board, int[] target, int i, int j, int idx,
                                 boolean[][] visited){
        if(i < 0 || j < 0 || i >= board.length || j >= board.length || visited[i][j] || board[i][j] != tar[idx]){
            return 0;
        }
        if(idx == 9 && i == target[0] && j == target[1]){
            return 1;
        }else if(idx == 9){
            return 0;
        }
        return reachable(board, target, i+1, j, idx+1, visited) +
                reachable(board, target, i-1, j, idx+1, visited) +
                reachable(board, target, i, j+1, idx+1, visited) +
                reachable(board, target, i, j-1, idx+1, visited);
    }


}
