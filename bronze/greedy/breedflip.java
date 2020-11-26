package bronze.greedy;

import java.util.*;
import java.io.*;

public class breedflip {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("breedflip.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("breedflip.out"));
        int n = r.nextInt();
        char[] a = r.next().toCharArray();
        char[] b = r.next().toCharArray();

        boolean mismatch = false;
        int flips = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                if (!mismatch) {
                    mismatch = true;
                    flips++;
                }
            } else {
                mismatch = false;
            }
        }

        pw.println(flips);

        pw.close(); // flushes the output once printing is done
    }
}