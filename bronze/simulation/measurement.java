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

        Update[] updates = new Update[N];
        ArrayList<Cow> cows = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int day = r.nextInt();
            String name = r.next();
            int amount = r.nextInt();

            // does cow exist
            Cow cowToUpdate = null;
            for (Cow c : cows) {
                if (c.name.equals(name))
                    cowToUpdate = c;
            }

            if (cowToUpdate == null) {
                cowToUpdate = new Cow(name);
                cows.add(cowToUpdate);
            }

            updates[i] = new Update(day, cowToUpdate, amount);
        }

        Arrays.sort(updates);

        int max = 7;
        int numDaysToChange = 0;
        int lastDayChanged = -1;
        for (int i = 0; i < updates.length; i++) {
            int prevAmt = updates[i].cowToUpdate.milk;
            int newAmt = prevAmt + updates[i].amount;
            updates[i].cowToUpdate.milk = newAmt;

            boolean needToChange = false;
            if (prevAmt == max && newAmt != max)
                needToChange = true;
            if (newAmt > max) {
                max = newAmt;
                needToChange = true;
            }
            if (newAmt == max && prevAmt != max)
                needToChange = true;

            if (lastDayChanged != updates[i].day && needToChange) {
                lastDayChanged = updates[i].day;
                numDaysToChange++;
            }
        }

        pw.println(numDaysToChange);

        pw.close(); // flushes the output once printing is done
    }

    static class Cow {
        String name;
        int milk;

        Cow (String name) {
            this.name = name;
            this.milk = 7;
        }
    }

    static class Update implements Comparable<Update> {
        int day;
        Cow cowToUpdate;
        int amount;

        Update(int day, Cow cowToUpdate, int amount) {
            this.day = day;
            this.cowToUpdate = cowToUpdate;
            this.amount = amount;
        }

        @Override
        public int compareTo(Update u) {
            return Integer.compare(this.day, u.day);
        }
    }
}