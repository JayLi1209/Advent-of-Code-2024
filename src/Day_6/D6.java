package Day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class D6 {

    private static final int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_6/test.txt");
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

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        int[] startCopy = Arrays.copyOf(start, start.length);
        question1(visited, startCopy, arr);
        question2(visited, start, arr);
    }

    private static boolean[][] question1(boolean[][] visited, int[] cur, char[][] board){
        int m = board.length, n = board[0].length;
        int count = 0, curDir = 0;
        boolean stop = false;
//        System.out.println("start: " + cur[0] + " " + cur[1]);
        while(!stop){
            while(cur[0] < m-1 && cur[1] < n-1 && cur[0] > 0 && cur[1] > 0 &&
                    // next one is not #
                    board[cur[0] + dirs[curDir % 4][0]][cur[1] + dirs[curDir % 4][1]] != '#'){
                if(!visited[cur[0]][cur[1]]){
                    visited[cur[0]][cur[1]] = true;
                    ++count;
                }
                cur[0] += dirs[curDir % 4][0];
                cur[1] += dirs[curDir % 4][1];
            }
//            System.out.println("after traversal " + curDir + ": " + cur[0] + " " + cur[1]);
            // On the edge
            if(cur[0] == m-1 || cur[1] == n-1 || cur[0] == 0 || cur[1] == 0){
                if(!visited[cur[0]][cur[1]]){
                    visited[cur[0]][cur[1]] = true;
                    ++count;
                }
                stop = true;
            }
            curDir++;
        }

        System.out.println("Q1: " + count);
        return visited;
    }

    // obstacle is on visited places
    // loop detection: same orientation on a visited node
    private static void question2(boolean[][] visited, int[] cur, char[][] board){
        int count = 0;
        int x = cur[0], y = cur[1];
        for(int i = 0; i < visited.length; ++i){
            for(int j = 0; j < visited[0].length; ++j){
                boolean[][] seen = new boolean[visited.length][visited[0].length];
                int[][] orientation = new int[visited.length][visited[0].length];
                for(int[] o : orientation) Arrays.fill(o, -1);
                if(visited[i][j]){
//                    System.out.println("enter :" + i + " " + j + " " + cur[0] + " " + cur[1]);
                    count += putObstacle(i, j, seen, orientation, cur, board);
                    cur = new int[]{x, y};
                    break;
                }
            }
        }
        System.out.println("Q2: " + count);
    }

    private static int putObstacle(int i, int j, boolean[][] visited, int[][] orientation, int[] cur, char[][] board){
        board[i][j] = '#';

        int m = board.length, n = board[0].length;
        int curDir = 0;
        while(true){
            while(cur[0] < m-1 && cur[1] < n-1 && cur[0] > 0 && cur[1] > 0 &&
                    // next one is not #
                    board[cur[0] + dirs[curDir % 4][0]][cur[1] + dirs[curDir % 4][1]] != '#'){
                if(!visited[cur[0]][cur[1]]){
                    visited[cur[0]][cur[1]] = true;
                }
                orientation[cur[0]][cur[1]] = curDir%4;
                cur[0] += dirs[curDir % 4][0];
                cur[1] += dirs[curDir % 4][1];
            }
//            System.out.println("after traversal " + curDir + ": " + cur[0] + " " + cur[1]);
            // On the edge
            if(cur[0] == m-1 || cur[1] == n-1 || cur[0] == 0 || cur[1] == 0){
                board[i][j] = '.';
                return 0;
            }
            if(curDir % 4 == orientation[cur[0]][cur[1]]){
                board[i][j] = '.';
//                System.out.println("never reached here!");
                return 1;
            }
            curDir++;
            // This...is just ugly code!
            if(curDir > 10000){
                board[i][j] = '.';
                return 1;
            }
        }

    }
}
