package bronze.simulation;

import java.util.*;
import java.io.*;

public class measurement {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("measurement.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        int N = r.nextInt();

        int bessie = 7, elsie = 7, mildred = 7;
        int max = 7;
        boolean b = true, e = true, m = true;
        int[] day = new int[N];
        String[] cow = new String[N];
        int[] change = new int[N];

        for (int i = 0; i < N; i++) {
            day[i] = r.nextInt();
            cow[i] = r.next();
            change[i] = r.nextInt();
        }

        int dayChanges = 0;
        for (int d = 1; d <= 100; d++) {
            boolean currDayChanged = false;
            for (int i = 0; i < N; i++) {
                if (day[i] == d) {
                    if (cow[i].equals("Bessie"))
                        bessie += change[i];
                    else if (cow[i].equals("Elsie"))
                        elsie += change[i];
                    else
                        mildred += change[i];

                    int newMax = Math.max(Math.max(bessie, elsie), mildred);
                    boolean bNext = bessie == newMax, eNext = elsie == newMax, mNext = mildred == newMax;

                    if (!currDayChanged && b != bNext || e != eNext || m != mNext) {
                        currDayChanged = true;
                        dayChanges++;
                    }

                    b = bNext;
                    e = eNext;
                    m = mNext;
                }
            }
        }

        pw.println(dayChanges);

        pw.close(); // flushes the output once printing is done
    }
}