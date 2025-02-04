package Day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D5 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_5/input.txt");
        Scanner scanner = new Scanner(file);

        Map<Integer, List<Integer>> map = new HashMap<>();
        List<int[]> queries = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.contains("|")){
                String[] arr = line.split("\\|");
                int key = Integer.parseInt(arr[0]), value = Integer.parseInt(arr[1]);

                map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
            }else if(line.contains(",")){
                String[] arr = line.split(",");
                int[] res = new int[arr.length];
                for(int i = 0; i < arr.length; ++i){
                    res[i] = Integer.parseInt(arr[i]);
                }
                queries.add(res);
            }
        }

        question1(map, queries);
        question2(map, queries);
    }

    private static void question1(Map<Integer, List<Integer>> map, List<int[]> queries){
        int result = 0;

        for(int[] query : queries){
            int i = 1;
            for(; i < query.length; ++i){
                List<Integer> lst = map.get(query[i-1]);
                if(lst == null || !lst.contains(query[i])) break;
            }
            if(i == query.length){
                result += query[query.length/2];
            }
        }

        System.out.println("Q1: " + result);
    }

    private static void question2(Map<Integer, List<Integer>> map, List<int[]> queries){
        int result = 0;

        for(int[] query : queries){
            // use dfs
        }

        System.out.println("Q2: " + result);
    }
}
