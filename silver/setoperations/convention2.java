package silver.setoperations;

import java.util.*;
import java.io.*;

public class convention2 {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader() throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("convention2.in"));
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
        PrintWriter pw = new PrintWriter(new FileWriter("convention2.out"));

        int N = r.nextInt();
        PriorityQueue<Cow> cows = new PriorityQueue<>(N);
        for (int i = 0; i < N; i++)
            cows.add(new Cow(r.nextInt(), r.nextInt(), i + 1));

        PriorityQueue<Cow> waiting = new PriorityQueue<>(new SeniorityComparator());

        Cow currCow = cows.poll();
        int time = currCow.arrivalTime + currCow.eatingTime;
        int maxWaiting = 0;

        while (!cows.isEmpty() || !waiting.isEmpty()) {
            // while there are still cows, simulate
            // move all appropriate cows to waiting queue
            while (!cows.isEmpty() && cows.peek().arrivalTime <= time)
                waiting.add(cows.poll());
            // now waiting list is full, update current cow
            // update maxWaiting if necessary
            if (!waiting.isEmpty()) {
                currCow = waiting.poll();
                //System.out.println(currCow + " waited from " + currCow.arrivalTime + " to " + time + " [" + (time - currCow.arrivalTime) + "]");
                maxWaiting = Math.max(time - currCow.arrivalTime, maxWaiting);
            } else if (!cows.isEmpty()) {
                currCow = cows.poll();
            }
            time = Math.max(time, currCow.arrivalTime) + currCow.eatingTime; // critical line
        }

        pw.println(maxWaiting);

        pw.close(); // flushes the output once printing is done
    }

    static class SeniorityComparator implements Comparator<Cow> {
        @Override
        public int compare(Cow c1, Cow c2) {
            return c1.seniority - c2.seniority;
        }
    }

    static class Cow implements Comparable<Cow> {
        int arrivalTime, eatingTime, seniority;

        Cow(int a, int t, int s) {
            arrivalTime = a;
            eatingTime = t;
            seniority = s;
        }

        @Override
        public int compareTo(Cow other) {
            if (arrivalTime == other.arrivalTime) return seniority - other.seniority;
            return arrivalTime - other.arrivalTime;
        }

        public String toString() {
            return "(a: " + arrivalTime + " t: " + eatingTime + " s: " + seniority + ")";
        }
    }
}