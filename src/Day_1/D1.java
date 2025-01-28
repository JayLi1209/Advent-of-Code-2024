package Day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class D1 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_1/input1.txt");
        Scanner scanner1 = new Scanner(file);
        Scanner scanner2 = new Scanner(file);

        question1(scanner1);
        question2(scanner2);
    }

    private static void question2(Scanner scanner){
        Map<Integer, Integer> map = new HashMap<>(); // For right: number - # of appearances
        Map<Integer, Integer> count = new HashMap<>(); // For left: number - # of appearances
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] res = line.split("   ");
            int left = Integer.parseInt(res[0]), right = Integer.parseInt(res[1]);
            map.put(right, map.getOrDefault(right, 0) + 1);
            count.put(left, count.getOrDefault(left, 0) + 1);
        }

        long sum = 0;
        for(var e : count.entrySet()){
            sum += (long) e.getKey() * map.getOrDefault(e.getKey(), 0);
        }
        System.out.println(sum);

    }

    private static void question1(Scanner scanner){
        PriorityQueue<Integer> q1 = new PriorityQueue<>();
        PriorityQueue<Integer> q2 = new PriorityQueue<>();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] res = line.split("   ");
            q1.add(Integer.parseInt(res[0]));
            q2.add(Integer.parseInt(res[1]));
        }

        long sum = 0;

        while(!q1.isEmpty()){
            sum += (long) Math.abs(q1.poll() - q2.poll());
        }
        System.out.println(sum);
    }


}
