package be.degreyt.mmdoc.utils;

public class ComparisonBuilder {

    private int comparison = 0;

    public <T extends Comparable<? super T>> ComparisonBuilder add(T first, T second) {
        if (comparison == 0) {
            if (first == null || second == null) {
                comparison = first == second ? 0 : (first == null ? 1 : -1);
                return this;
            }
            comparison = signum(first.compareTo(second));
        }
        return this;
    }

    public int compare() {
        return comparison;
    }

    private int signum(int x) {
        return x > 0 ? 1 : ( x < 0 ? -1 : 0);
    }
}
