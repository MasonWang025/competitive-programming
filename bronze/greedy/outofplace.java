package bronze.greedy;

import java.util.*;
import java.io.*;

public class outofplace {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("outofplace.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("outofplace.out"));
        int n = r.nextInt();
        int[] order = new int[n];
        for (int i = 0; i < n; i++)
            order[i] = r.nextInt();

        int index = n - 1;
        for (int i = 0; i < n - 1; i++) {
            if (order[i] > order[i + 1]) {
                index = i;
                break;
            }
        }

        pw.close(); // flushes the output once printing is done
    }
}