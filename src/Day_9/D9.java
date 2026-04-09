package Day_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D9 {
    public static void main(String[] args) throws FileNotFoundException {
        // test3 shohuld give 169 with:
        /*
        0...1...2......33333
        0...1...233333......
        02..1....33333......
        021......33333......
         */
        Scanner scanner = new Scanner(new File("src/Day_9/test.txt"));
        String line = scanner.nextLine();

        int n = line.length(), cur = 0;
        List<String> s = new ArrayList<>();
        boolean isNum = true;

        for(int i = 0; i < n; ++i){
            String str = line.substring(i, i+1);
            int num = Integer.parseInt(str);
            if(num == 0){
                isNum = !isNum;
                continue;
            }
            if(isNum){
                for(int j = 0; j < num; ++j){
                    s.add(String.valueOf(cur));
                }
                ++cur;
            }else{
                for(int j = 0; j < num; ++j){
                    s.add(".");
                }
            }
            isNum = !isNum;
        }

        question1(new ArrayList<>(s));
        question2(new ArrayList<>(s));
    }

    private static void question1(List<String> s){
        int l = 0, r = s.size()-1;
        while(l <= r){
            while(!s.get(l).equals(".")){
                ++l;
            }
            while(s.get(r).equals(".")){
                --r;
            }
            if(l <= r) Collections.swap(s, l, r);
        }

        long sum = 0;
        for(int i = 0; i < s.size(); ++i){
            if(s.get(i).equals(".")) break;
            sum += (long) i * Integer.parseInt(s.get(i));
        }
        System.out.println("Q1: " + sum);
    }

    // 1. what do we count if it is more than 9?
    // 2. what do we do if don't have enough free space?
    private static void question2(List<String> s){
        int sum = 0;

        System.out.println("Q2: " + sum);
    }
}
