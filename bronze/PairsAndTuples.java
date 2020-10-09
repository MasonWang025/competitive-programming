package bronze;

public class PairsAndTuples {
    public static void main(String[] args) {
        Pair<Integer, String> p = new Pair(5, "hello");
        System.out.println(p.first + " " + p.second);
    }

    static class Pair<K, V> {
        K first;
        V second;

        public Pair(K first_value, V second_value) {
            first = first_value;
            second = second_value;
        }
    }
}
