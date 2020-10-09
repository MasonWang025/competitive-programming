// package bronze.simulation;

import java.util.*;
import java.io.*;

public class blocks {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("blocks.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("blocks.out"));

        int N = r.nextInt();

        int[] total = new int[26];

        for (int i = 0; i < N; i++) {
            String f = r.next();
            int[] front = new int[26];
            for (int j = 0; j < f.length(); j++)
                front[f.charAt(j) - 97]++;

            String b = r.next();
            int[] back = new int[26];
            for (int j = 0; j < b.length(); j++)
                back[b.charAt(j) - 97]++;

            for (int j = 0; j < 26; j++)
                total[j] += Math.max(front[j], back[j]);
        }

        for (int i = 0; i < 26; i++)
            pw.println(total[i]);

        pw.close(); // flushes the output once printing is done
    }
}