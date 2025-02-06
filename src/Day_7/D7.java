package Day_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.Scanner;

public class D7 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_7/input.txt");
        Scanner scanner1 = new Scanner(file);
        Scanner scanner2 = new Scanner(file);

        questions(scanner1);
    }

    private static void questions(Scanner scanner){
        long result = 0;
        long result2 = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            String[] split = line.split(":");
            String[] nums = split[1].split(" ");
            long target = Long.parseLong(split[0]);
            int[] arr = new int[nums.length-1];
            for(int i = 1; i < nums.length; ++i){
                arr[i-1] = Integer.parseInt(nums[i]);
            }

            if(recursion(arr, target, arr[0], 1)){
                result += target;
            }

            if(recursion2(arr, target, arr[0], 1)){
                result2 += target;
            }
        }
        System.out.println("Q1: " + result);
        System.out.println("Q2: " + result2);
    }

    private static boolean recursion(int[] arr, long tar, long cur, int idx){
        if(cur == tar && idx == arr.length){
            return true;
        }
        if(idx >= arr.length || cur > tar){
            return false;
        }
        return recursion(arr, tar, cur + arr[idx], idx+1) ||
                recursion(arr, tar, cur * arr[idx], idx+1);
    }

    private static boolean recursion2(int[] arr, long tar, long cur, int idx){
        if(cur == tar && idx == arr.length){
            return true;
        }
        if(idx >= arr.length || cur > tar){
            return false;
        }

        int len = String.valueOf(arr[idx]).length();
        return recursion2(arr, tar, cur + arr[idx], idx+1) ||
                recursion2(arr, tar, cur * arr[idx], idx+1) ||
                recursion2(arr, tar, cur * (long)Math.pow(10, len) + arr[idx], idx+1);
    }

}
