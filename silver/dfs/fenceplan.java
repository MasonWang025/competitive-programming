package silver.dfs;

import java.util.*;
import java.io.*;

public class fenceplan {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("fenceplan.in"));
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

    static Cow[] cows;
    static boolean[] assigned; // which cows have been assigned to a moonet
    static int x1, y1, x2, y2; // fence top left and bottom right corners

    public static void main(String[] args) throws IOException {
        InputReader r = new InputReader();
        PrintWriter pw = new PrintWriter(new FileWriter("fenceplan.out"));

        final int N = r.nextInt();
        final int M = r.nextInt();

        // represent info in cow graph (adj. list)
        cows = new Cow[N];
        for (int i = 0; i < N; i++)
            cows[i] = new Cow(r.nextInt(), r.nextInt());

        for (int i = 0; i < M; i++) {
            int a = r.nextInt() - 1;
            int b = r.nextInt() - 1;
            cows[a].neighbors.add(b);
            cows[b].neighbors.add(a);
        }

        // pick unvisited cow and DFS to calculate perimeter of moonet
        assigned = new boolean[N];
        int minPerim = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (assigned[i])
                continue;
            System.out.println("dfs on cow " + i);
            x1 = x2 = cows[i].x;
            y1 = y2 = cows[i].y;
            dfs(i);
            minPerim = Math.min(minPerim, (2*(x2 - x1)) + (2*(y2-y1)));
        }

        pw.println(minPerim);
        pw.close(); // flushes the output once printing is done
    }

    static void dfs(int curr) {
        if (assigned[curr])
            return;

        assigned[curr] = true;
        x1 = Math.min(x1, cows[curr].x);
        x2 = Math.max(x2, cows[curr].x);
        y1 = Math.min(y1, cows[curr].y);
        y2 = Math.max(y2, cows[curr].y);
        for (int i = 0; i < cows[curr].neighbors.size(); i++)
            dfs(cows[curr].neighbors.get(i));
    }

    static class Cow {
        int x;
        int y;

        ArrayList<Integer> neighbors = new ArrayList<>();

        Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + "): " + Arrays.toString(neighbors.toArray());
        }
    }
}