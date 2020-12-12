package silver.dfs;

import java.util.*;
import java.io.*;

public class mootube {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("mootube.in"));
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

    static Video[] videos;

    // could have instead simply implemented with ArrayList<Edge>[] b/c Video.index isn't ever used anyways
    public static void main(String[] args) throws IOException {
        InputReader r = new InputReader();
        PrintWriter pw = new PrintWriter(new FileWriter("mootube.out"));

        final int N = r.nextInt();
        final int Q = r.nextInt();

        // store in graph (adj. lists)
        videos = new Video[N];
        for (int i = 0; i < N; i++)
            videos[i] = new Video(i);

        for (int i = 0; i < N - 1; i++) {
            int p = r.nextInt() - 1;
            int q = r.nextInt() - 1;
            int relevance = r.nextInt();
            videos[p].neighbors.add(new Edge(q, relevance));
            videos[q].neighbors.add(new Edge(p, relevance));
        }

        // for each question, dfs and count
        for (int q = 0; q < Q; q++) {
            boolean[] visited = new boolean[N];
            pw.println(dfs(r.nextInt(), r.nextInt() - 1, visited) - 1);
        }

        pw.close(); // flushes the output once printing is done
    }

    static int dfs(int k, int curr, boolean[] visited) {
        if (visited[curr])
            return 0;

        visited[curr] = true;
        int ret = 1;
        ArrayList<Edge> currNeighbors = videos[curr].neighbors;
        for (int i = 0; i < currNeighbors.size(); i++) {
            if (currNeighbors.get(i).weight < k)
                continue;
            ret += dfs(k, currNeighbors.get(i).node, visited);
        }

        return ret;
    }

    static class Video {
        int index;
        ArrayList<Edge> neighbors = new ArrayList<>();

        Video(int index) {
            this.index = index;
        }

        public String toString() {
            return index + ": " + Arrays.toString(neighbors.toArray());
        }
    }

    static class Edge {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public String toString() {
            return "(n:" + node + ", w:" + weight + ")";
        }
    }
}