package Day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D3 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_3/input.txt");
        Scanner scanner1 = new Scanner(file);
        Scanner scanner2 = new Scanner(file);

        question1(scanner1);
        question2(scanner2);
    }

    private static void question1(Scanner scanner){
        while(scanner.hasNext()){
            String s = scanner.next();


        }
    }

    private static void question2(Scanner scanner){

    }
}
