package bronze.completesearch;

import java.util.*;
import java.io.*;

public class balancing {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("balancing.in"));
            tokenizer = null;
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    static int[][] cows;
    static int n, l;

    public static void main(String[] args) throws IOException {
        InputReader r = new InputReader();
        PrintWriter pw = new PrintWriter(new FileWriter("balancing.out"));
        n = r.nextInt();
        l = r.nextInt();

        cows = new int[n][2];

        for (int i = 0; i < n; i++) {
            cows[i][0] = r.nextInt();
            cows[i][1] = r.nextInt();
        }

        int[][] sortedX = Arrays.copyOf(cows, cows.length);
        int[][] sortedY = Arrays.copyOf(cows, cows.length);

        Arrays.sort(sortedX, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(sortedY, Comparator.comparingInt(o -> o[1]));

        int max = l + 1;
        for (int i = 0; i < n; i++) {
            int a = sortedX[i][0] + 1;
            int b = sortedY[i][1] + 1;
            max = Math.min(getMax(a, b), max);
        }

        pw.println(max);
        pw.close(); // flushes the output once printing is done
    }

    static int getMax(int a, int b) {
        //System.out.print(a + "," + b + ": ");
        int tl = 0, tr = 0, bl = 0, br = 0;

        for (int i = 0; i < cows.length; i++) {
            int x = cows[i][0], y = cows[i][1];
            if (x < a) {
                if (y < b)
                    bl++;
                else
                    tl++;
            } else {
                if (y < b)
                    br++;
                else
                    tr++;
            }
        }

        return Math.max(Math.max(tl, tr), Math.max(bl, br));
    }
}