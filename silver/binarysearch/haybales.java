package silver.binarysearch;

import java.util.*;
import java.io.*;

public class haybales {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("haybales.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));

        int n = r.nextInt();
        int q = r.nextInt();
        int[] bales = new int[n];

        for (int i = 0; i < n; i++)
            bales[i] = r.nextInt();

        Arrays.sort(bales);

        for (int i = 0; i < q; i++) {
            int s = r.nextInt(), e = r.nextInt();
            int bi = Arrays.binarySearch(bales, s);
            if (bi < 0)
                bi = Math.abs(bi + 1);
            int ti = Arrays.binarySearch(bales, e);
            if (ti < 0)
                ti = Math.abs(ti + 2);
            pw.println(ti - bi + 1);
        }

        pw.close(); // flushes the output once printing is done
    }
}