package bronze.completesearch;

import java.io.*;
import java.util.*;

public class tracing {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("tracing.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("tracing.out"));
        int n = r.nextInt(), t = r.nextInt(); // n cows, t events
        char[] endChars = r.next().toCharArray();
        boolean[] end = new boolean[n];
        for (int i = 0; i < n; i++)
            end[i] = endChars[i] == '1';
        int[][] events = new int[251][2]; // t events of 2 cows handshaking
        for (int i = 0; i < t; i++) {
            int time = r.nextInt();
            events[time][0] = r.nextInt();
            events[time][1] = r.nextInt();
        }

        TreeSet<Integer> candidates = new TreeSet<>();
        int minK = 251;
        int maxK = 0;

        // for each starting cow, try each k (0...n)
        for (int c = 0; c < n; c++) {
            // c is patient zero
            for (int k = 0; k <= t; k++) {
                int[] num_handshakes = new int[101]; // keep track of how many more a cow can infect
                boolean[] infected = new boolean[n]; // keep track of who is infected
                infected[c] = true;

                // simulate
                for (int e = 0; e < events.length; e++) {
                    if (events[e][0] == 0)
                        continue;
                    // x and y shake
                    int x = events[e][0] - 1;
                    int y = events[e][1] - 1;
                    if (infected[x]) num_handshakes[x]++;
                    if (infected[y]) num_handshakes[y]++;
                    if (num_handshakes[x] <= k && infected[x]) infected[y] = true;
                    if (num_handshakes[y] <= k && infected[y]) infected[x] = true;
                }

                boolean validSimulation = true;
                for (int i = 0; i < n; i++) {
                    if (infected[i] != end[i]) {
                        validSimulation = false;
                        break;
                    }
                }
                if (!validSimulation)
                    continue;

                // now we know it is valid
                if (k < minK)
                    minK = k;
                if (k > maxK)
                    maxK = k;
                candidates.add(c);
            }
        }

        String maxKstr = maxK == t ? "Infinity" : maxK + "";
        pw.println(candidates.size() + " " + minK + " " + maxKstr);
        pw.close(); // flushes the output once printing is done
    }
}