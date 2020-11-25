package bronze.recursivesearch;

import java.io.*;
import java.util.*;

public class CreatingStrings {
        static class InputReader {
            BufferedReader reader;
            StringTokenizer tokenizer;

            public InputReader(InputStream stream) {
                reader = new BufferedReader(new InputStreamReader(stream), 32768);
                tokenizer = null;
            }

            String next() { // reads in the next string
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
            } // reads in the next int

            public long nextLong() {
                return Long.parseLong(next());
            } // reads in the next long

            public double nextDouble() {
                return Double.parseDouble(next());
            } // reads in the next double
        }

        static InputReader r = new InputReader(System.in);
        static PrintWriter pw = new PrintWriter(System.out);

        static TreeSet<String> permutations = new TreeSet<>();
        static String permutation = "";
        static char[] chars;
        static boolean[] chosen;
        static int n;

        public static void main(String[] args) {
            String input = r.next();
            n = input.length();
            chars = input.toCharArray();
            chosen = new boolean[n];

            search();

            pw.println(permutations.size());
            for (String p : permutations)
                pw.println(p);
            pw.close(); // flushes the output once printing is done
        }

        public static void search() {
            if (permutation.length() == n) {
                permutations.add(permutation);
            } else {
                for (int i = 0; i < n; i++) {
                    if (chosen[i])
                        continue;
                    permutation = permutation + chars[i];
                    chosen[i] = true;
                    search();
                    chosen[i] = false;
                    permutation = permutation.substring(0, permutation.length() - 1);
                }
            }
        }
    }