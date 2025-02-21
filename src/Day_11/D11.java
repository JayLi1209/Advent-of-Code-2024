package Day_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D11 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_11/input.txt");
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] arr = line.split(" ");
        Queue<String> q = new LinkedList<>(Arrays.asList(arr));

        question1(q);
    }

    private static void question1(Queue<String> q){
        for(int i = 0; i < 40; ++i){
            int size = q.size();
            for(int j = 0; j < size; ++j){
//                System.out.println(j + " " + arr.get(j) + " " + tmp);
                String s = q.poll();
                int len = s.length();
                if(len % 2 == 0){
                    String s1 = s.substring(0, len/2),
                            s2 = s.substring(len/2);
                    addWithoutZero(q, s1);
                    addWithoutZero(q, s2);
                }else if(s.startsWith("0")){
                    q.add("1");
                }else{
                    String a = Long.toString(Long.parseLong(s)*2024);
//                    System.out.println(arr.get(j) + " hi " + a);
                    addWithoutZero(q, a);
                }
            }
            System.out.println("iter " + (i+1) );
        }
        System.out.println("Q1: " + q.size());
    }

    private static void addWithoutZero(Queue<String> q, String s){
        while(s.startsWith("0")){
            s = s.substring(1);
        }
        if(s.isEmpty()){
            q.add("0");
        }else{
            q.add(s);
        }
    }
}
