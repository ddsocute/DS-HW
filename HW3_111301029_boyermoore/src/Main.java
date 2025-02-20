//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
//        測試算法
//        String test = "abcabcabc";
//        WordCounter counter = new WordCounter("hooh");
//        System.out.println( counter.BoyerMoore(test, "abc"));
        System.out.println("Please type URL and Keyword:");
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String urlStr = sc.next();
            String keyword = sc.next();
            WordCounter counter = new WordCounter(urlStr);
            System.out.println(keyword + " appears " + counter.countKeyword(keyword) + " times.");
        }

        sc.close();
    }
}

