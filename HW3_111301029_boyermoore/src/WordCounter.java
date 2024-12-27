import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


//到時候呼叫這個類別
//  先初始化
public class WordCounter {
    private String urlStr;
    private String content;

    //  定義到時候呼叫的函數
    public WordCounter(String urlStr) {
        this.urlStr = urlStr;
    }

    //  從網站抓資料
//    private String fetchContent() throws IOException {
//        URL url = new URL(this.urlStr);
//        URLConnection conn = url.openConnection();
//        InputStream in = conn.getInputStream();
//        BufferedReader br = new BufferedReader(new InputStreamReader(in));
//
//        StringBuilder retVal = new StringBuilder();
//        String line;
//        boolean insideTag = false;
//
//        while ((line = br.readLine()) != null) {
//            for (char c : line.toCharArray()) {
//                if (c == '<') {
//                    insideTag = true;
//                } else if (c == '>') {
//                    insideTag = false;
//                } else if (!insideTag) {
//                    retVal.append(c);
//                }
//            }
//            retVal.append('\n');
//        }
//
//        String result = retVal.toString().replaceAll("&[^;]+;", "").trim();
//        System.out.println(result);
//        return result;
//    }
    private String fetchContent() throws IOException {
        URL url = new URL(this.urlStr);
        URLConnection conn = url.openConnection();
        InputStream in = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String retVal = "";

        String line = null;

        while ((line = br.readLine()) != null) {
            retVal = retVal + line + "\n";
        }
        return retVal;

    }

    //  中間用到的算法
    public int BoyerMoore(String T, String P) {
        int i = P.length() - 1;
        int j = P.length() - 1;
        int times = 0;
        while (i < T.length()) {
            if (T.charAt(i) == P.charAt(j)) {
                if (j == 0) {
                    times++;
                    i += P.length() ;
                    j = P.length() - 1;
                } else {
                    i -= 1;
                    j -= 1;
                }
            } else {
                int l = last(T.charAt(i), P);
                i = i + P.length() - min(j, 1 + l);
                j = P.length() - 1;
            }
        }

        // Bonus: Implement Boyer-Moore Algorithm
        return times;
    }

    //  算法用到的最後出現
    public int last(char c, String P) {
        // Bonus: Implement last occurence function
        for (int i = P.length() - 1; i >= 0; i--) {
            if (P.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    //  算法用到的取比較小值
    public int min(int a, int b) {
        if (a < b) return a;
        else if (b < a) return b;
        else return a;
    }


    public int countKeyword(String keyword) throws IOException {
        if (content == null) {
            content = fetchContent();
        }

        //To do a case-insensitive search, we turn the whole content and keyword into upper-case:
        content = content.toUpperCase();
        keyword = keyword.toUpperCase();

        int retVal = 0;
        return BoyerMoore(content, keyword);
        // 1. calculates appearances of keyword (Bonus: Implement Boyer-Moore Algorithm)
    }
}
