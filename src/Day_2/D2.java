package Day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class D2 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_2/input.txt");
        Scanner scanner1 = new Scanner(file);
        Scanner scanner2 = new Scanner(file);

        question1(scanner1);
    }

    private static void question1(Scanner scanner){
        int count = 0;
        while(scanner.hasNextLine()){
            String[] rawData = scanner.nextLine().split(" ");
            int[] data = Arrays.stream(rawData).mapToInt(Integer::parseInt).toArray();
            if(data.length == 1){
                count++;
                continue;
            }
            count++;
            boolean isAscending = data[0] < data[1];
            for(int i = 1; i < data.length; ++i){
                int diff = data[i] - data[i-1];
                if(isAscending && (diff > 3 || diff < 1)){
                    --count;
                    break;
                }
                if(!isAscending && (diff > -1 || diff < -3)){
                    --count;
                    break;
                }
            }
        }
        System.out.println(count);
    }
    private static void question2(){}
}
