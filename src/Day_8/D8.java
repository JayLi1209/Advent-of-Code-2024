package Day_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D8 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_8/input.txt");
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
        boolean[][] visited2 = new boolean[arr.length][arr[0].length];
        Map<Character, List<int[]>> map = new HashMap<>();
        for(int i = 0; i < arr.length; ++i){
            for(int j = 0; j < arr[0].length; ++j){
                char c = arr[i][j];
                if(c != '.'){
                    map.computeIfAbsent(c, k -> new ArrayList<>()).add(new int[]{i, j});
                }
            }
        }
        questions(arr, visited, visited2, map);
    }

    private static void questions(char[][] board, boolean[][] visited, boolean[][] visited2,  Map<Character, List<int[]>> map){
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                char c = board[i][j];
                if (c != '.') {
                    if(!visited2[i][j]){
                        ++count2;
                        visited2[i][j] = true;
                    }
                    List<int[]> points = map.get(c);
                    count += markPoints(points, visited);
                    count2 += markPoints2(points, visited2);
                }
            }
        }
        System.out.println("Q1: " + count);
        System.out.println("Q2: " + count2);
    }

    private static int markPoints2(List<int[]> points, boolean[][] visited){
        int count = 0;
        for(int i = 0; i < points.size(); ++i){
            int[] pt1 = points.get(i);
            for(int j = 0; j < points.size(); ++j){
                if(i == j) continue;
                int[] pt2 = points.get(j);
                int[] diff = {pt2[0] - pt1[0], pt2[1] - pt1[1]};
                // pt1[0] - n * (pt2[0] + pt1[0]) ,  pt1[1] - n * (pt2[1] + pt2[1])
//                System.out.println("diff: " + diff[0] + " " + diff[1]);
                int x = pt1[0] - diff[0], y = pt1[1] - diff[1];
                while(isValid(x, y, visited)){
                    if(!visited[x][y]){
                        visited[x][y] = true;
                        ++count;
                    }
                    x -= diff[0];
                    y -= diff[1];
                }
//                System.out.println("points: " + x + " " + y);
//                visited[x][y] = true;
//                System.out.println(x + " " + y);
//                ++count;
            }
        }
        return count;
    }

    private static boolean isValid(int x, int y, boolean[][] visited){
        return x >= 0 && y >= 0 && x < visited.length && y < visited[0].length;
    }

    private static int markPoints(List<int[]> points, boolean[][] visited){
        int count = 0;
        for(int i = 0; i < points.size(); ++i){
            int[] pt1 = points.get(i);
            for(int j = 0; j < points.size(); ++j){
                if(i == j) continue;
                int[] pt2 = points.get(j);
                int[] diff = {pt2[0] - pt1[0], pt2[1] - pt1[1]};
//                System.out.println("diff: " + diff[0] + " " + diff[1]);
                int x = pt1[0] - diff[0], y = pt1[1] - diff[1];
//                System.out.println("points: " + x + " " + y);
                if(x < 0 || y < 0 || x >= visited.length || y >= visited[0].length || visited[x][y]) continue;
                visited[x][y] = true;
//                System.out.println(x + " " + y);
                ++count;
            }
        }
        return count;
    }


}
