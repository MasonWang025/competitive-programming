package bronze.recursivesearch;

import java.io.*;
import java.util.*;

public class AppleDivision {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        String next() { // reads in the next string
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
        } // reads in the next int

        public long nextLong() {
            return Long.parseLong(next());
        } // reads in the next long

        public double nextDouble() {
            return Double.parseDouble(next());
        } // reads in the next double
    }

    static InputReader r = new InputReader(System.in);
    static PrintWriter pw = new PrintWriter(System.out);

    static int n;
    static long[] weights;
    public static void main(String[] args) {
        n = r.nextInt();

        weights = new long[n];

        for (int i = 0; i < n; i++)
            weights[i] = r.nextLong();

        pw.println(solve(0, 0, 0));

        pw.close(); // flushes the output once printing is done
    }

    static long solve(int index, long sum1, long sum2) {
        if (index == n)
            return Math.abs(sum1 - sum2);
        return Math.min(solve(index + 1, sum1 + weights[index], sum2), solve(index + 1, sum1, sum2 + weights[index]));
    }
}