package silver.customsorting;

import java.util.*;
import java.io.*;

public class rental {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("rental.in"));
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

    static int N, M, R;
    static int[] cows;
    static Shop[] milkPrices;
    static int[] rentPrices;

    public static void main(String[] args) throws IOException {
        InputReader r = new InputReader();
        PrintWriter pw = new PrintWriter(new FileWriter("rental.out"));

        // 1. input
        N = r.nextInt();
        M = r.nextInt();
        R = r.nextInt();
        cows = new int[N];
        milkPrices = new Shop[M];
        rentPrices = new int[R];
        for (int i = 0; i < N; i++)
            cows[i] = r.nextInt();
        for (int i = 0; i < M; i++)
            milkPrices[i] = new Shop(r.nextInt(), r.nextInt());
        for (int i = 0; i < R; i++)
            rentPrices[i] = r.nextInt();

        // 2. sort
        sort(cows);
        Arrays.sort(milkPrices);
        sort(rentPrices);

        // 3. maximize milkProfit(i) + rentProfit(N-i)
        long[] profits = new long[N + 1];
        {
            int index = 0;
            for (int i = 0; i < N; i++) {
                profits[i + 1] = profits[i];
                while (index < M && cows[i] > 0) {
                    int use = Math.min(cows[i], milkPrices[index].quantity);
                    profits[i + 1] += use * (long) milkPrices[index].price;
                    cows[i] -= use;
                    milkPrices[index].quantity -= use;
                    if (milkPrices[index].quantity == 0) {
                        index++;
                    }
                }
            }
        }

        {
            int a = N - 1;
            int rI = 0;
            long rentalSoFar = 0;
            while (a >= 0 && rI < R) {
                rentalSoFar += rentPrices[rI];
                profits[a] += rentalSoFar;
                rI++;
                a--;
            }
        }

        /*
         * long maxProfit = -1; for (int i = 0; i < N; i++) { if (i <= M && (N - i) <=
         * R) { long calcProfit = milkProfit(i) + rentProfit(N - i); if (calcProfit <
         * maxProfit) break; maxProfit = calcProfit; } }
         */

        long maxProfit = 0;
        for (long p : profits)
            maxProfit = Math.max(p, maxProfit);

        pw.println(maxProfit);
        pw.close(); // flushes the output once printing is done
    }

    /*
     * static long milkProfit(int c) { // c cows with the most milk sold to highest
     * payers long totalMilk = 0; for (int i = 0; i < c; i++) totalMilk += cows[i];
     *
     * long profit = 0; for (int i = 0; i < M; i++) { if (milkPrices[i].quantity >=
     * totalMilk) { // if can sell all milk profit += totalMilk *
     * milkPrices[i].price; break; // all milk sold } else { profit +=
     * milkPrices[i].quantity * milkPrices[i].price; totalMilk -=
     * milkPrices[i].quantity; } }
     *
     * return profit; }
     *
     * static long rentProfit(int c) { long profit = 0;
     *
     * for (int i = 0; i < c; i++) profit += rentPrices[i];
     *
     * return profit; }
     */

    public static void sort(int[] l) {
        Arrays.sort(l);
        for (int i = 0; i < l.length - 1 - i; i++) {
            l[i] ^= l[l.length - 1 - i];
            l[l.length - 1 - i] ^= l[i];
            l[i] ^= l[l.length - 1 - i];
        }
    }

    static class Shop implements Comparable<Shop> {
        public int quantity, price;

        public Shop(int quantity, int price) {
            this.quantity = quantity;
            this.price = price;
        }

        public int compareTo(Shop s) {
            return s.price - price;
        }
    }
}