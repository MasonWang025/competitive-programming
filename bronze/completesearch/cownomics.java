package bronze.completesearch;

import java.io.*;
import java.util.StringTokenizer;

public class cownomics {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("cownomics.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("cownomics.out"));
        int n = r.nextInt();
        int m = r.nextInt();
        char[][] genomes = new char[n * 2][m];
        for (int i = 0; i < n * 2; i++)
            genomes[i] = r.next().toCharArray();

        int count = 0;
        for (int j = 0; j < m; j++) {
            // for each column
            char[] spotGenomes = new char[n];
            for (int i = 0; i < n; i++)
                spotGenomes[i] = genomes[i][j];

            boolean distinguishable = true;
            for (int i = 0; i < n; i++) {
                if (containsChar(spotGenomes, genomes[n + i][j])) {
                    distinguishable = false;
                    break;
                }
            }

            if (distinguishable) count++;
        }

        pw.println(count);
        pw.close(); // flushes the output once printing is done
    }

    public static boolean containsChar(char[] arr, char n) {
        for (char c : arr)
            if (c == n)
                return true;
        return false;
    }
}