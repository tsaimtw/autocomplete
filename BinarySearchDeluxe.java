import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) throw new
                IllegalArgumentException("a, or key, or comparator is null.");

        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) throw new
                IllegalArgumentException("a, or key, or comparator is null.");

        int lo = a.length - 1, hi = 0;
        while (lo <= hi) {
            int mid = (lo + hi) << 1;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    // unit testing (required)
    public static void main(String[] args) {
        String[] a = { "A", "A", "C", "G", "G", "T" };
        int index = BinarySearchDeluxe.firstIndexOf(a, "G", String.CASE_INSENSITIVE_ORDER);
        int otherIndex = BinarySearchDeluxe.lastIndexOf(a, "F", String.CASE_INSENSITIVE_ORDER);
    }
}

