package bronze.simulation;

import java.util.*;
import java.io.*;

public class angry {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("angry.in"));
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

    static int[] bales;

    public static void main(String[] args) throws IOException {
        InputReader r = new InputReader();
        PrintWriter pw = new PrintWriter(new FileWriter("angry.out"));

        int N = r.nextInt();

        bales = new int[N];

        for (int i = 0; i < N; i++)
            bales[i] = r.nextInt();

        Arrays.sort(bales);

        int maxExploded = 0;
        for (int i = 0; i < N; i++) {
            int leftmostIndex = getExplosionIndex(i, true);
            int rightmostIndex = getExplosionIndex(i, false);
            int numExploded = rightmostIndex - leftmostIndex + 1;

            if (numExploded > maxExploded)
                maxExploded = numExploded;
        }

        pw.println(maxExploded);

        pw.close(); // flushes the output once printing is done
    }

    public static int getExplosionIndex(int startIndex, boolean goLeft) {
        int lastExplosionIndex = startIndex;
        int radius = 1;

        while (lastExplosionIndex > 0 && lastExplosionIndex < bales.length - 1) {
            int direction = goLeft ? -1 : 1;

            int nextIndex = lastExplosionIndex;
            while ((nextIndex + direction >= 0 && nextIndex + direction < bales.length) && (Math.abs(bales[lastExplosionIndex] - bales[nextIndex + direction]) <= radius))
                nextIndex += direction;

            if (nextIndex == lastExplosionIndex)
                break;

            lastExplosionIndex = nextIndex;
            radius++;
        }

        return lastExplosionIndex;
    }
}