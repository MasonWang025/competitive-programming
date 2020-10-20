//package bronze.completesearch;

import java.io.*;
import java.util.*;

public class gymnastics {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("gymnastics.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("gymnastics.out"));
        int k = r.nextInt();
        int n = r.nextInt();

        int[][] scores = new int[k][n];

        // input
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                scores[i][j] = r.nextInt();
            }
        }

        int pairs = 0;
        for (int c1 = 1; c1 <= n; c1++) {
            for (int c2 = 1; c2 <= n; c2++) {
                if (c1 == c2)
                    continue;
                boolean consistent = true;
                // is c1 better than c2 for all k sessions?
                for (int p = 0; p < k; p++) {
                    int c1_index = -1;
                    // position of c1
                    for (int i = 0; i < n; i++)
                        if (scores[p][i] == c1)
                            c1_index = i;

                    int c2_index = -1;
                    // position of c2
                    for (int i = 0; i < n; i++)
                        if (scores[p][i] == c2)
                            c2_index = i;

                    if (c2_index < c1_index) {
                        //System.out.println("(" + c1 + ", " + c2 + "): " + c1_index + " " + c2_index);
                        consistent = false;
                        break;
                    }
                }
                if (consistent) {
                    //System.out.println("(" + c1 + ", " + c2 + ")");
                    pairs++;
                }
            }
        }

        pw.println(pairs);

        pw.close(); // flushes the output once printing is done
    }
}