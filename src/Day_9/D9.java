package Day_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class D9 {

    static class Seg {
        String val;   // "." for gap, otherwise file id as string like "0", "10", "123"
        int len;      // number of blocks

        Seg(String val, int len) {
            this.val = val;
            this.len = len;
        }

        boolean isGap() {
            return val.equals(".");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/Day_9/input.txt"));
        String line = scanner.nextLine();

        int n = line.length(), cur = 0;
        List<String> s = new ArrayList<>();
        List<Seg> new_s = new ArrayList<>();
        boolean isNum = true;

        for (int i = 0; i < n; ++i) {
            int num = line.charAt(i) - '0';

            if (isNum) {
                for (int j = 0; j < num; ++j) {
                    s.add(String.valueOf(cur));
                }
                new_s.add(new Seg(String.valueOf(cur), num));
                ++cur;
            } else {
                for (int j = 0; j < num; ++j) {
                    s.add(".");
                }
                new_s.add(new Seg(".", num));
            }
            isNum = !isNum;
        }

        question1(new ArrayList<>(s));
        question2(new ArrayList<>(new_s));
    }

    private static void question1(List<String> s) {
        int l = 0, r = s.size() - 1;
        while (l <= r) {
            while (l < s.size() && !s.get(l).equals(".")) {
                ++l;
            }
            while (r >= 0 && s.get(r).equals(".")) {
                --r;
            }
            if (l <= r) Collections.swap(s, l, r);
        }

        long sum = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (s.get(i).equals(".")) break;
            sum += (long) i * Integer.parseInt(s.get(i));
        }
        System.out.println("Q1: " + sum);
    }

    private static void question2(List<Seg> s) {
        for (int r = s.size() - 1; r >= 0; --r) {
            Seg file = s.get(r);
            if (file.isGap() || file.len == 0) continue;

            for (int l = 0; l < r; ++l) {
                Seg gap = s.get(l);
                if (!gap.isGap()) continue;

                if (gap.len >= file.len) {
                    int oldFileLen = file.len;
                    String oldFileVal = file.val;

                    // move file into leftmost fitting gap
                    s.set(l, new Seg(oldFileVal, oldFileLen));

                    // leftover gap after placing file
                    if (gap.len > oldFileLen) {
                        s.add(l + 1, new Seg(".", gap.len - oldFileLen));
                        if (l < r) r++; // file's old position shifted right by insertion
                    }

                    // old file position becomes gap
                    s.set(r, new Seg(".", oldFileLen));
                    break;
                }
            }
        }

        long sum = 0;
        long pos = 0;

        for (Seg seg : s) {
            if (seg.len == 0) continue;

            if (seg.isGap()) {
                pos += seg.len;
            } else {
                long id = Long.parseLong(seg.val);
                for (int k = 0; k < seg.len; ++k) {
                    sum += pos * id;
                    ++pos;
                }
            }
        }

        System.out.println("Q2: " + sum);
    }
}