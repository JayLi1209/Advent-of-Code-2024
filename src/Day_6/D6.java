package Day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D6 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_6/input.txt");
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

    }
}
