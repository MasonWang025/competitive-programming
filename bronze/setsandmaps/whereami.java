package bronze.setsandmaps;

import java.io.*;
import java.util.*;

public class whereami {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("whereami.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("whereami.out"));
        int N = r.nextInt();

        String colors = r.next();

        TreeSet<String> discovered;

        // go through all segments of length l
        for (int l = 0; l < N; l++) {
            discovered = new TreeSet<>();
            boolean unique = true;
            // segment starting index i
            for (int i = 0; i < N - l; i++) {
                String segment = colors.substring(i, i + l + 1);

                if (discovered.contains(segment)) {
                    System.out.println("discovered");
                    unique = false;
                    break;
                }

                discovered.add(segment);
            }

            if (unique) {
                pw.println(l + 1);
                break;
            }
        }

        pw.close(); // flushes the output once printing is done
    }
}
