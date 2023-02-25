import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String pquery;
    private long pweight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query.equals(null) || weight < 0) {
            throw new IllegalArgumentException("Query is null or weight is neg");
        }

        pquery = query;
        pweight = weight;

    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new WeightCompare();
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) throw new IllegalArgumentException("r is less than 0");
        return new PrefixCompare(r);
    }

    private static class WeightCompare implements Comparator<Term> {
        public int compare(Term a, Term b) {
            return (int) (a.pweight - b.pweight);
        }
    }

    private static class PrefixCompare implements Comparator<Term> {
        int r;

        public PrefixCompare(int r) {
            this.r = r;
        }

        public int compare(Term a, Term b) {
            if (a.pquery.length() < r) {
                return a.pquery.compareTo(b.pquery);
            }
            String rQuery = a.pquery.substring(0, r);
            return rQuery.compareTo(b.pquery);
        }
    }


    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.pquery.compareTo(that.pquery);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return pweight + "  " + pquery;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();

        Term[] test = new Term[] {
                new Term("What", 15),
                new Term("Is", 5),
                new Term("This", 0)
        };

        for (int i = 0; i < 1000000000; i++) {
            Selection.sort(test);
            Selection.sort(test, Term.byPrefixOrder(3));
            Selection.sort(test, Term.byReverseWeightOrder());
        }


        StdOut.println(timer.elapsedTime());

        for (int i = 0; i < 3; i++) {
            StdOut.println(test[i].toString());
        }

    }

}

