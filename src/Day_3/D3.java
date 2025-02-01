package Day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class D3 {
    // TODO: this is nasty code. How to clean up?
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/Day_3/input.txt");
        Scanner scanner1 = new Scanner(file);
        Scanner scanner2 = new Scanner(file);

        question1(scanner1);
        question2(scanner2);
    }

    private static void question1(Scanner scanner){
        long result = 0;
        char[] set = new char[]{'m', 'u', 'l', '('};
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            int n = s.length();

            for(int i = 0; i < n; ++i){
                char c = s.charAt(i);
                if(c == 'm'){
                    int j;
                    for(j = i; j < i+set.length && j < n; ++j){
                        if(s.charAt(j) != set[j-i]){
                            i = j;
                            break;
                        }
                    }
                    if(j == i+set.length){
                        int tmp = parse(s, i+set.length);
                        if(tmp != Integer.MAX_VALUE){
                            result += tmp;
                        }
                    }
                }
            }

        }
        System.out.println("Q1: " + result);
    }

    private static int parse(String s, int idx){
        int num1 = 0, num2 = 0;
        boolean numOne = true;
        int i = idx;
        while(i < s.length()){
            char c = s.charAt(i);
            if(c == ','){
                numOne = false;
            }else if(Character.isDigit(c)){
                if(numOne){
                    num1 = num1 * 10 + Integer.parseInt(String.valueOf(c));
                }else{
                    num2 = num2 * 10 + Integer.parseInt(String.valueOf(c));
                }
            }else if (c == ')'){
                if(num1 == 0 || num2 == 0) return Integer.MAX_VALUE;
                return num1 * num2;
            }else{
                return Integer.MAX_VALUE;
            }
            ++i;
        }
        return num1 * num2;
    }

    private static void question2(Scanner scanner){
        long result = 0;
        char[] set = new char[]{'m', 'u', 'l', '('};
        char[] yes = new char[]{'d', 'o', '(', ')'};
        char[] no = new char[] {'d', 'o', 'n', '\'', 't', '(', ')'};
        boolean accept = true;
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            int n = s.length();

            // same logic as in question 1.
            for(int i = 0; i < n; ++i){
                char c = s.charAt(i);
                if(c == 'm' && accept){
                    int j;
                    for(j = i; j < i+set.length && j < n; ++j){
                        if(s.charAt(j) != set[j-i]){
                            i = j;
                            break;
                        }
                    }
                    if(j == i+4){
                        int tmp =  parse(s, i+4);
                        if(tmp != Integer.MAX_VALUE){
                            result += tmp;
                        }
                    }
                }else if(c == 'd'){
                    int j;
                    for(j = i; j < i+yes.length && j < n; ++j){
                        if(s.charAt(j) != yes[j-i]){
                            break;
                        }
                    }
                    if(j == i+yes.length){
//                        i = j; // cannot do this, since it skips by one,
//                        // something like domul(12,12). It skips the m.
//                        System.out.println("yes at " + i  + " result is " + result + " " + s);
                        accept = true;
                    }

                    int k;
                    for(k = i; k < i+no.length && k < n; ++k){
                        if(s.charAt(k) != no[k-i]){
                            break;
                        }
                    }
                    if(k == i+no.length){
//                        i = k; // same reason here
//                        System.out.println("no at " + i  + " result is " + result + " " + s);
                        accept = false;
                    }

                }
            }

        }
        System.out.println("Q2: " + result);
    }
}
