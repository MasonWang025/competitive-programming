package silver.dfs;

import java.util.*;
import java.io.*;

public class moocast {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("moocast.in"));
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

    static boolean[][] canTransmit;

    public static void main(String[] args) throws IOException {
        InputReader r = new InputReader();
        PrintWriter pw = new PrintWriter(new FileWriter("moocast.out"));

        // input
        final int N = r.nextInt();
        int[][] cows = new int[N][3];
        for (int i = 0; i < N; i++) {
            cows[i][0] = r.nextInt();
            cows[i][1] = r.nextInt();
            cows[i][2] = r.nextInt();
        }

        // store info in graph (matrix rep.)
        canTransmit = new boolean[N][N]; // cow i can transmit to cow j
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                canTransmit[i][j] = Math.hypot(cows[i][0] - cows[j][0], cows[i][1] - cows[j][1]) <= cows[i][2];

        // dfs starting from each node
        int result = 1;
        for (int i = 0; i < N; i++) {
            boolean[] canHear = new boolean[N];
            result = Math.max(result, dfs(i, canHear));
        }

        pw.println(result);
        pw.close(); // flushes the output once printing is done
    }

    static int dfs(int i, boolean[] canHear) {
        if (canHear[i])
            return 0;

        canHear[i] = true;
        int result = 1;
        for (int j = 0; j < canTransmit[i].length; j++)
            if (canTransmit[i][j])
                result += dfs(j, canHear);

        return result;
    }
}