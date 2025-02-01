package Day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class D2 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_2/input.txt");
        Scanner scanner1 = new Scanner(file);
        Scanner scanner2 = new Scanner(file);

        question1(scanner1);
        question2(scanner2);
    }

    private static void question1(Scanner scanner) {
        int count = 0;
        while (scanner.hasNextLine()) {
            String[] rawData = scanner.nextLine().split(" ");
            int[] data = Arrays.stream(rawData).mapToInt(Integer::parseInt).toArray();
            if (data.length == 1) {
                count++;
                continue;
            }
            count++;
            boolean isAscending = data[0] < data[1];
            for (int i = 1; i < data.length; ++i) {
                int diff = data[i] - data[i - 1];
                boolean condition = (isAscending && (diff > 3 || diff < 1)) || (!isAscending && (diff > -1 || diff < -3));
                if(condition){
                    --count;
                    break;
                }
            }
        }
        System.out.println("q1: " + count);
    }

    private static void question2(Scanner scanner) {
        int count = 0;
        while (scanner.hasNextLine()) {
            String[] rawData = scanner.nextLine().split(" ");
            int[] data = Arrays.stream(rawData).mapToInt(Integer::parseInt).toArray();

            // if length <= 3, and they are not all the same, it's always doable
            if (data.length < 4) {
                if(data.length == 1 || data.length == 2){
                    count++;
                    continue;
                }
                if (data[0] != data[1] || data[1] != data[2]) {
                    count++;
                }
                continue;
            }

            for(int i = 0; i < data.length; ++i){
                Integer[] arr = assemble(data, i);
                boolean valid = isValid(arr);
                if(valid){
                    ++count;
                    break;
                }
            }

//            int ascendCount = 0, descendCount = 0;
//
//            for (int i = 1; i < data.length; ++i) {
//                if (data[i] > data[i - 1]) {
//                    ++ascendCount;
//                } else if (data[i] < data[i - 1]) {
//                    ++descendCount;
//                }
//            }
//
//            int equals = data.length - 1 - ascendCount - descendCount;
//            int minor = Math.min(ascendCount, descendCount);
//            if (equals + minor > 1) {
//                count--;
//                continue;
//            }
//
//            boolean isAscending = ascendCount > descendCount;
//            // brute force: try every number
//            int i = 0;
//            for(; i < data.length; ++i){
//                int prev = data[0];
//                int j = 1;
//                for(; j < data.length; ++j){
//                    if(j == i) continue;
//                    int diff = data[j] - prev;
//                    boolean condition = (isAscending && (diff > 3 || diff < 1)) || (!isAscending && (diff > -1 || diff < -3));
//                    if (condition) break;
//                    prev = data[j];
//                }
//                if(j == data.length) break;
//            }
//            if(i == data.length) --count;

            // Then, it's either ascending or desc

//            boolean first = false;
//            int prev = data[0];
//            for (int i = 1; i < data.length; ++i) {
//                int diff = data[i] - prev;
//                boolean condition = (isAscending && (diff > 3 || diff < 1)) || (!isAscending && (diff > -1 || diff < -3));
//                if (condition && !first) {
//                    first = true;
//                } else if (condition) {
//                    --count;
//                    break;
//                } else {
//                    prev = data[i];
//                }
//            }
        }
        System.out.println("q2: " + count);
    }

    private static Integer[] assemble(int[] arr, int i){
        List<Integer> newArr = new ArrayList<>();
        int idx = 0;
        while(idx < arr.length){
            if(idx != i){
                newArr.add(arr[idx]);
            }
            ++idx;
        }
        return newArr.toArray(new Integer[0]);
    }

    private static boolean isValid(Integer[] arr){
        boolean isAscending = arr[0] < arr[1];
        for(int i = 1; i < arr.length; ++i){
            int diff = arr[i] - arr[i - 1];
            boolean condition = (isAscending && (diff > 3 || diff < 1)) || (!isAscending && (diff > -1 || diff < -3));
            if(condition) return false;
        }
        return true;
    }
}
