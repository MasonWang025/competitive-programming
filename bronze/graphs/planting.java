package bronze.graphs;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class planting {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("planting.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("planting.out"));

        int n = r.nextInt();
        int[] numNeighbors = new int[n];

        for (int i = 0; i < n - 1; i++) {
            numNeighbors[r.nextInt() - 1]++;
            numNeighbors[r.nextInt() - 1]++;
        }

        int maxNeighbors = 0;
        for (int i = 0; i < n; i++)
            maxNeighbors = Math.max(maxNeighbors, numNeighbors[i]);

        pw.println(maxNeighbors + 1);

        pw.close(); // flushes the output once printing is done
    }
}