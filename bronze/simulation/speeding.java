package bronze.simulation;

import java.util.*;
import java.io.*;

public class speeding {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("speeding.in"));
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

    public static void main(String[] args) throws IOException {
        InputReader r = new InputReader();
        PrintWriter pw = new PrintWriter(new FileWriter("speeding.out"));

        int N = r.nextInt(), M = r.nextInt();

        int[] limits = new int[100];
        int[] journey = new int[100];


        for (int i = 0, loc = 0; i < N && loc < 100; i++) {
            int length = r.nextInt();
            int limit = r.nextInt();
            for (int j = loc; j < loc + length; j++)
                limits[j] = limit;
            loc += length;
        }

        for (int i = 0, loc = 0; i < M && loc < 100; i++) {
            int length = r.nextInt();
            int speed = r.nextInt();
            for (int j = loc; j < loc + length; j++)
                journey[j] = speed;
            loc += length;
        }

        int maxOver = 0;
        for (int i = 0; i < 100; i++) {
            int over = journey[i] - limits[i];
            if (over > maxOver)
                maxOver = over;
        }

        pw.println(maxOver);

        pw.close(); // flushes the output once printing is done
    }
}