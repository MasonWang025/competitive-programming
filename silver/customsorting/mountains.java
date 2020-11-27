package silver.customsorting;

import java.util.*;
import java.io.*;

public class mountains {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("mountains.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));

        int N = r.nextInt();
        Mountain[] mountains = new Mountain[N];
        boolean[] obscured = new boolean[N];
        for (int i = 0; i < N; i++)
            mountains[i] = new Mountain(r.nextInt(), r.nextInt());

        Arrays.sort(mountains);

        for (int i = 0; i < N; i++)
            System.out.println(mountains[i].x1 + ", " + mountains[i].x2);

        for (int i = 0; i < N - 1; i++) {
            if (obscured[i])
                continue;
            for (int j = i + 1; j < N; j++) {
                if (mountains[j].x2 <= mountains[i].x2) {
                    obscured[j] = true;
                } else {
                    break;
                }
            }
        }

        int visible = N;
        for (int i = 0; i < N; i++)
            if (obscured[i]) visible--;

        pw.println(visible);
        pw.close(); // flushes the output once printing is done
    }

    static class Mountain implements Comparable<Mountain> {
        int x1;
        int x2;

        Mountain(int px, int py) {
            this.x1 = px - py;
            this.x2 = px + py;
        }

        @Override
        public int compareTo(Mountain m) {
            if (x1 == m.x1) return m.x2 - x2;
            return x1 - m.x1;
        }
    }
}