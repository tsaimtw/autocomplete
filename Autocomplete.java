import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {
    Term[] auto;
    int size;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new IllegalArgumentException("Illegal Argument");
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null) throw new IllegalArgumentException("Illegal Argument");
        }
        auto = terms;
        size = auto.length;
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("Illegal Argument");
        Arrays.sort(auto);
        Comparator<Term> comparator = Term.byPrefixOrder(prefix.length());
        Term prefix1 = new Term(prefix, 0); // made a fake term and weight for prefix
        int first = BinarySearchDeluxe.firstIndexOf(auto, prefix1, comparator);
        int last = BinarySearchDeluxe.lastIndexOf(auto, prefix1, comparator);
        Term[] prefixTerms = new Term[last - first + 1];
        for (int i = first; i <= last; i++) {
            StdOut.println(auto[i]); //TEST
            prefixTerms[i] = auto[i];
        }
        comparator = Term.byReverseWeightOrder();
        Arrays.sort(prefixTerms, comparator);
        return prefixTerms;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("Illegal Argument");
        return allMatches(prefix).length;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term[] test = new Term[] {
                new Term("Alpha", 15),
                new Term("bat", 5),
                new Term("goat", 0),
                new Term("All", 1),
                new Term("Alter", 2),
                new Term("soft", 4)
        };
        int k = 2;
        Autocomplete autocomplete = new Autocomplete(test);
        Term[] results = autocomplete.allMatches("al");
        for (int i = 0; i < results.length; i++) {
            StdOut.println(results[i]);
        }


        // // read in the terms from a file
        // String filename = args[0];
        // In in = new In(filename);
        // int n = in.readInt();
        // Term[] terms = new Term[n];
        // for (int i = 0; i < n; i++) {
        //     long weight = in.readLong();           // read the next weight
        //     in.readChar();                         // scan past the tab
        //     String query = in.readLine();          // read the next query
        //     terms[i] = new Term(query, weight);    // construct the term
        // }
        //
        // // read in queries from standard input and print the top k matching terms
        // int k = Integer.parseInt(args[1]);
        // Autocomplete autocomplete = new Autocomplete(terms);
        // while (StdIn.hasNextLine()) {
        //     String prefix = StdIn.readLine();
        //     Term[] results = autocomplete.allMatches(prefix);
        //     StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
        //     for (int i = 0; i < Math.min(k, results.length); i++)
        //         StdOut.println(results[i]);
        // }
    }
}

