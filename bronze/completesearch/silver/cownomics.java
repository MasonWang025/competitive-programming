package bronze.completesearch.silver;

import java.io.*;
import java.util.StringTokenizer;

public class cownomics {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("cownomics.in"));
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

    static int[][] genomes;

    public static void main(String[] args) throws IOException {
        InputReader r = new InputReader();
        PrintWriter pw = new PrintWriter(new FileWriter("cownomics.out"));
        int n = r.nextInt(), m = r.nextInt();

        genomes = new int[n * 2][m];
        for (int i = 0; i < n * 2; i++) {
            char[] seq = r.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (seq[j] == 'A')
                    genomes[i][j] = 0;
                else if (seq[j] == 'C')
                    genomes[i][j] = 1;
                else if (seq[j] == 'G')
                    genomes[i][j] = 2;
                else if (seq[j] == 'T')
                    genomes[i][j] = 3;
            }
        }

        /* for (int i = 0; i < genomes.length; i++) {
            for (int j = 0; j < genomes[0].length; j++)
                System.out.print(genomes[i][j]);
            System.out.println();
        } */

        int count = 0;
        for (int ji = 0; ji < m; ji++)
            for (int jj = ji + 1; jj < m; jj++)
                for (int jk = jj + 1; jk < m; jk++)
                    if (isValidTriplet(ji, jj, jk)) count++;

        pw.println(count);
        pw.close(); // flushes the output once printing is done
    }

    public static boolean isValidTriplet(int i, int j, int k) {
        //System.out.println(i + " " + j + " " + k);

        boolean[] seen = new boolean[64];
        for (int x = 0; x < genomes.length / 2; x++)
            seen[16 * genomes[x][i] + 4 * genomes[x][j] + genomes[x][k]] = true;
        for (int x = 0; x < genomes.length / 2; x++)
            if (seen[16 * genomes[genomes.length / 2 + x][i] + 4 * genomes[genomes.length / 2 + x][j] + genomes[genomes.length / 2 + x][k]])
                return false;
        return true;
    }
}