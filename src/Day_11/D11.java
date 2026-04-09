package Day_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D11 {

    private static Map<String, List<String>> map = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_11/input.txt");
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] arr = line.split(" ");
        Queue<String> q = new LinkedList<>(Arrays.asList(arr));

        question(q);
    }


    private static void question(Queue<String> q){
        for(int i = 0; i < 75; ++i){
            int size = q.size();
            for(int j = 0; j < size; ++j){
//                System.out.println(j + " " + arr.get(j) + " " + tmp);
                String s = q.poll();
//                if(map.containsKey(s)){
//                    List<String> values = map.get(s);
//                    for(String val : values) addWithoutZero(q, val);
//                    continue;
//                }
                int len = s.length();
                if(len % 2 == 0){
                    String s1 = s.substring(0, len/2),
                            s2 = s.substring(len/2);
                    addWithoutZero(q, s1);
                    addWithoutZero(q, s2);
//                    List<String> str = new ArrayList<>();
//                    str.add(s1);
//                    str.add(s2);
//                    map.put(s, new ArrayList<>(str));
                }else if(s.startsWith("0")){
                    q.add("1");
//                    map.computeIfAbsent(s, _ -> new ArrayList<>()).add("1");
                }else{
                    String a = Long.toString(Long.parseLong(s)*2024);
                    addWithoutZero(q, a);
//                    map.computeIfAbsent(s, _ -> new ArrayList<>()).add(a);
                }
            }
            System.out.println("iter: " + (i+1) +" with size :" + q.size());
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
