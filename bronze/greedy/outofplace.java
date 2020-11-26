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
        int[] heights = new int[n];
        int[] sorted = new int[n];

        for (int i = 0; i < n; i++)
            sorted[i] = heights[i] = r.nextInt();

        Arrays.sort(sorted);

        int diff = 0;
        for (int i = 0; i < n; i++)
            if (sorted[i] != heights[i])
                diff++;

        pw.println(Math.max(0, diff - 1));

        pw.close(); // flushes the output once printing is done
    }
}