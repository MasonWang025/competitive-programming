package bronze.greedy;

import java.util.*;
import java.io.*;

public class reststops {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("reststops.in"));
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

    static int N, L;
    static long rF, rB;
    static long[][] restStops;

    public static void main(String[] args) throws IOException {
        InputReader r = new InputReader();
        PrintWriter pw = new PrintWriter(new FileWriter("reststops.out"));

        // input
        L = r.nextInt();
        N = r.nextInt();
        rF = r.nextLong();
        rB = r.nextLong();
        restStops = new long[N][2];
        for (int i = 0; i < N; i++) {
            restStops[i][0] = r.nextLong();
            restStops[i][1] = r.nextLong();
        }

        Arrays.sort(restStops, Comparator.comparingLong(a -> a[1]));

        long location = 0;
        long total = 0;
        for (int i = restStops.length - 1; i >= 0; i--) {
            if (restStops[i][0] < location)
                continue; // already passed

            // how much does total increase
            long distance = restStops[i][0] - location;
            // meter * seconds/meter => seconds
            long timeDiff = distance * (rF - rB);

            total += restStops[i][1] * timeDiff;

            // farmer has caught up
            location = restStops[i][0];
        }

        pw.println(total);

        pw.close(); // flushes the output once printing is done
    }
}