import java.util.Scanner;

@SuppressWarnings("All")
interface Sequence<T> {
    T get(int i);

    void set(int i, T x);

    void add(T x);

    void swap(int i, int j);

    int size();
}

@SuppressWarnings("All")
public class Naloga2 {

    private static class mySequence implements Sequence<Integer>{
        private int[] array;
        private int size;
        private int capacity;

        public mySequence() {
            capacity = 42;
            array = new int[capacity];
        }

        @Override
        public Integer get(int i) {
            return array[i];
        }

        @Override
        public void set(int i, Integer x) {
            array[i] = x;
        }

        @Override
        public void add(Integer x) {
            if(size == capacity) resize();
            set(size++, x);
        }

        private void resize() {
            capacity *= 2;
            int[] newA = new int[capacity];

            for (int i = 0; i < size; i++) {
                newA[i] = array[i];
            }

            array = newA;
        }

        @Override
        public void swap(int i, int j) {
            int tmp = get(i);
            set(i, get(j));
            set(j, tmp);
        }

        @Override
        public int size() {
            return size;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx < size; idx++) {
                sb.append(array[idx] + " ");
            }
            return sb.toString().trim();
        }
    }

    private static mySequence array = new mySequence();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            // output separation
            if (input.equals("")) {
                System.out.println("\n####################\n");
                continue;
            }
            array = processArray(scanner.nextLine());
            processInput(input);
            reinitialize();
        }
    }

    private static mySequence processArray(String input) {
        Scanner scanner = new Scanner(input);
        mySequence array = new mySequence();
        int e;

        while (scanner.hasNext()) {
            e = scanner.nextInt();
            array.add(e);
        }

        return array;
    }

    private static void reinitialize() {
        array = new mySequence();
    }

    private static void processInput(String input) {
        Scanner scanner = new Scanner(input);
        boolean count = scanner.next().equals("count");
        String sort = scanner.next();
        boolean desc = scanner.next().equals("down");

        if(!count) System.out.println(array.toString());
        switch (sort){
            case "insert":
                insertSort(count, desc);
                break;
            case "select":
                selectSort(count, desc);
                break;
            case "bubble":
                bubbleSort(count, desc);
                break;
            case "heap":
                heapSort(count, desc);
                break;
            case "merge":
                mergeSort(count, desc);
                break;
            case "quick":
                quickSort(count, desc);
                break;
            case "radix":

                break;
            case "bucket":

                break;
        }
    }

    private static void insertSort(boolean count, boolean desc) {
        int m, c, tmp, i;
        StringBuilder stats = new StringBuilder();
        m = c = i = 0;

        // Unsorted
        while (++i < array.size()){
            tmp = array.get(i);
            m++;
            for(int j=i-1; j >= 0; j--){
                c++;
                // tmp is smaller than array[j]
                if (desc && tmp > array.get(j) || !desc && tmp < array.get(j)) {
                    // move array[j] to the right
                    array.set(j+1, array.get(j));
                    m++;
                }
                // tmp is bigger than array[j]
                else {
                    // insert tmp
                    array.set(j+1, tmp);
                    m++;
                    break;
                }
                // tmp is smallest elt
                if (j == 0) {
                    // insert tmp as first elt
                    array.set(0, tmp);
                    m++;
                }
            }
            if (!count) {
                StringBuilder sb = new StringBuilder();
                for (int idx = 0; idx < array.size(); idx++) {
                    sb.append(array.get(idx) + " ");
                    if (idx == i) sb.append("| ");
                }
                System.out.println(sb.toString().trim());
            }
        }

        // Extra sorts
        if (count) {
            stats.append(m + " " + c);
            m = c = i = 0;

            // Sorted
            while (++i < array.size()){
                tmp = array.get(i);
                m++;
                for(int j=i-1; j >= 0; j--){
                    c++;
                    // tmp is smaller than array[j]
                    if (desc && tmp > array.get(j) || !desc && tmp < array.get(j)) {
                        // move array[j] to the right
                        array.set(j+1, array.get(j));
                        m++;
                    }
                    // tmp is bigger than array[j]
                    else {
                        // insert tmp
                        array.set(j+1, tmp);
                        m++;
                        break;
                    }
                    // tmp is smallest elt
                    if (j == 0) {
                        // insert tmp as first elt
                        array.set(0, tmp);
                        m++;
                    }
                }
            }
            stats.append(" | " + m + " " + c);

            m = c = i = 0;
            // Sorted in reverse
            desc = !desc;
            while (++i < array.size()){
                tmp = array.get(i);
                m++;
                for(int j=i-1; j >= 0; j--){
                    c++;
                    // tmp is smaller than array[j]
                    if (desc && tmp > array.get(j) || !desc && tmp < array.get(j)) {
                        // move array[j] to the right
                        array.set(j+1, array.get(j));
                        m++;
                    }
                    // tmp is bigger than array[j]
                    else {
                        // insert tmp
                        array.set(j+1, tmp);
                        m++;
                        break;
                    }
                    // tmp is smallest elt
                    if (j == 0) {
                        // insert tmp as first elt
                        array.set(0, tmp);
                        m++;
                    }
                }
            }
            stats.append(" | " + m + " " + c);

            System.out.println(stats.toString());
        }
    }

    private static void selectSort(boolean count, boolean desc) {
        int m, c, minIdx;
        StringBuilder stats = new StringBuilder();
        m = c = 0;

        // Unsorted
        for (int i = 0; i < array.size()-1; i++) {
            // register array[i] as min
            minIdx = i;

            // find min
            for (int j = i + 1; j < array.size(); j++) {
                c++;
                // array[j] is smaller than current min
                if (desc && array.get(j) > array.get(minIdx) || !desc && array.get(j) < array.get(minIdx)) {
                    // register array[j] as min
                    minIdx = j;
                }
            }

            // swap array[i] and min
            array.swap(i, minIdx);
            m+=3;

            // print progress
            if (!count) {
                StringBuilder sb = new StringBuilder();
                for (int idx = 0; idx < array.size(); idx++) {
                    sb.append(array.get(idx) + " ");
                    if (idx == i) sb.append("| ");
                }
                System.out.println(sb.toString().trim());
            }
        }

        // Extra sorts
        if (count) {
            stats.append(m + " " + c);
            m = c = 0;

            // Sorted
            for (int i = 0; i < array.size()-1; i++) {
                // register array[i] as min
                minIdx = i;

                // find min
                for (int j = i + 1; j < array.size(); j++) {
                    c++;
                    // array[j] is smaller than current min
                    if (desc && array.get(j) > array.get(minIdx) || !desc && array.get(j) < array.get(minIdx)) {
                        // register array[j] as min
                        minIdx = j;
                    }
                }

                // swap array[i] and min
                array.swap(i, minIdx);
                m+=3;
            }
            stats.append(" | " + m + " " + c);
            m = c = 0;

            // Sorted in reverse
            desc = !desc;
            for (int i = 0; i < array.size()-1; i++) {
                // register array[i] as min
                minIdx = i;

                // find min
                for (int j = i + 1; j < array.size(); j++) {
                    c++;
                    // array[j] is smaller than current min
                    if (desc && array.get(j) > array.get(minIdx) || !desc && array.get(j) < array.get(minIdx)) {
                        // register array[j] as min
                        minIdx = j;
                    }
                }

                // swap array[i] and min
                array.swap(i, minIdx);
                m+=3;
            }
            stats.append(" | " + m + " " + c);

            System.out.println(stats.toString());
        }
    }

    private static void bubbleSort(boolean count, boolean desc) {
        StringBuilder stats = new StringBuilder();
        int m, c, i, lastSwap;
        m = c = 0;
        i = -1;

        // Unsorted
        while (i < array.size() - 2) {
            lastSwap = array.size() - 2;

            // from end to lastSwap
            for (int j = array.size()-1; j > i+1; j--) {
                c++;
                // array[j] is smaller than predecessor
                if (desc && array.get(j) > array.get(j-1) || !desc && array.get(j) < array.get(j-1)) {
                    // swap array[j] and predecessor
                    array.swap(j, j-1);
                    m += 3;
                    // register as last swap
                    lastSwap = j-1;
                }
            }

            // jump to last swap
            i = lastSwap;
            //if (i >= array.size() - 1) break;

            // print progress
            if (!count) {
                StringBuilder sb = new StringBuilder();
                for (int idx = 0; idx < array.size(); idx++) {
                    sb.append(array.get(idx) + " ");
                    if (idx == i) sb.append("| ");
                }
                System.out.println(sb.toString().trim());
            }
        }

        // Extra sorts
        if (count) {
            stats.append(m + " " + c);
            m = c = 0;
            i = -1;

            // Sorted
            while (i < array.size() - 2) {
                lastSwap = array.size() - 2;

                // from end to lastSwap
                for (int j = array.size()-1; j > i+1; j--) {
                    c++;
                    // array[j] is smaller than predecessor
                    if (desc && array.get(j) > array.get(j-1) || !desc && array.get(j) < array.get(j-1)) {
                        // swap array[j] and predecessor
                        array.swap(j, j-1);
                        m += 3;
                        // register as last swap
                        lastSwap = j-1;
                    }
                }

                // jump to last swap
                i = lastSwap;
            }
            stats.append(" | " + m + " " + c);
            m = c = 0;
            i = -1;

            // Sorted in reverse
            desc = !desc;
            while (i < array.size() - 2) {
                lastSwap = array.size() - 2;

                // from end to lastSwap
                for (int j = array.size()-1; j > i+1; j--) {
                    c++;
                    // array[j] is smaller than predecessor
                    if (desc && array.get(j) > array.get(j-1) || !desc && array.get(j) < array.get(j-1)) {
                        // swap array[j] and predecessor
                        array.swap(j, j-1);
                        m += 3;
                        // register as last swap
                        lastSwap = j-1;
                    }
                }

                // jump to last swap
                i = lastSwap;
            }
            stats.append(" | " + m + " " + c);

            System.out.println(stats);
        }
    }

    private static void heapSort(boolean count, boolean desc) {
        StringBuilder stats = new StringBuilder();
        int m, c;
        int[] heapifyStats;
        m = c = 0;

        // Unsorted

        // heapify original
        for (int i = array.size() / 2 - 1; i >= 0; i--) {
            heapifyStats = heapify(desc, array.size(), i);
            m += heapifyStats[0];
            c += heapifyStats[1];
        }

        // extract and heapify
        for (int end = array.size() - 1; end >= 0; end--) {
            // print progress
            if (!count) {
                StringBuilder sb = new StringBuilder();
                for (int idx = 0; idx < array.size(); idx++) {
                    sb.append(array.get(idx) + " ");
                    if (idx == end) sb.append("| ");
                }
                System.out.println(sb.toString().trim());
            }

            if (end == 0) break;

            // swap
            array.swap(end, 0);
            m+=3;

            // heapify
            heapifyStats = heapify(desc, end, 0);
            m += heapifyStats[0];
            c += heapifyStats[1];
        }

        // Extra sorts
        if (count) {
            stats.append(m + " " + c);
            m = c = 0;

            // Sorted

            // heapify original
            for (int i = array.size() / 2 - 1; i >= 0; i--) {
                heapifyStats = heapify(desc, array.size(), i);
                m += heapifyStats[0];
                c += heapifyStats[1];
            }

            // extract and heapify
            for (int end = array.size() - 1; end > 0; end--) {
                // swap
                array.swap(end, 0);
                m+=3;

                // heapify
                heapifyStats = heapify(desc, end, 0);
                m += heapifyStats[0];
                c += heapifyStats[1];
            }
            stats.append(" | " + m + " " + c);
            m = c = 0;

            // Sorted in reverse

            desc = !desc;
            // heapify original
            for (int i = array.size() / 2 - 1; i >= 0; i--) {
                heapifyStats = heapify(desc, array.size(), i);
                m += heapifyStats[0];
                c += heapifyStats[1];
            }

            // extract and heapify
            for (int end = array.size() - 1; end > 0; end--) {
                // swap
                array.swap(end, 0);
                m+=3;

                // heapify
                heapifyStats = heapify(desc, end, 0);
                m += heapifyStats[0];
                c += heapifyStats[1];
            }
            stats.append(" | " + m + " " + c);

            System.out.println(stats);
        }
    }

    private static int[] heapify(boolean desc, int end, int root) {
        int m, c;
        m = c = 0;
        int[] stats = {0, 0};

        // initialize max as root
        int max = root;

        // root is a leaf (left does not exist)
        if (2*root + 1 >= end) return stats;

        // left is greater than root
        c++;
        if (desc && array.get(2*root + 1) < array.get(max) || !desc && array.get(2*root + 1) > array.get(max))
            max = 2*root + 1;

        // right exists
        if (2*root + 2 < end) {
            // right is greater than max
            c++;
            if (desc && array.get(2 * root + 2) < array.get(max) || !desc && array.get(2 * root + 2) > array.get(max))
                max = 2 * root + 2;
        }

        // max is not root
        if (max != root) {
            // swap
            array.swap(max, root);
            m += 3;

            // heapify max child's subtree
            int[] recursionStats = heapify(desc, end, max);
            m += recursionStats[0];
            c += recursionStats[1];
        }

        stats[0] = m;
        stats[1] = c;
        return stats;
    }

    private static void mergeSort(boolean count, boolean desc) {
        StringBuilder stats = new StringBuilder();
        int[] sortStats;

        // Unsorted
        sortStats = sort(count, desc, 0, array.size() - 1);

        // Extra sorts
        if (count) {
            stats.append(sortStats[0] + " " + sortStats[1]);

            // Sorted
            sortStats = sort(count, desc, 0, array.size() - 1);
            stats.append(" | " + sortStats[0] + " " + sortStats[1]);

            // Sorted in reverse
            desc = !desc;
            sortStats = sort(count, desc, 0, array.size() - 1);
            stats.append(" | " + sortStats[0] + " " + sortStats[1]);

            System.out.println(stats);
        }
    }

    private static int[] merge(boolean count, boolean desc, int l, int m, int r) {
        int[] stats = {0, 0};

        // sizes of subarrays
        int n1 = m - l + 1;
        int n2 = r - m;

        // temporary arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // copy elts to temp arrays
        for (int i = 0; i < n1; ++i){
            L[i] = array.get(l + i);
            stats[0]++; // m++
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = array.get(m + 1 + j);
            stats[0]++; // m++
        }

        // indexes for iteration (i for L, j for R, k for main)
        int i = 0, j = 0;
        int k = l;

        // merge temp arrays (copy into main)
        while (i < n1 && j < n2) {
            stats[1]++; // c++
            // L[i] is smaller than R[j]
            if (desc && L[i] >= R[j] || !desc && L[i] <= R[j]) {
                array.set(k, L[i]);
                stats[0]++; // m++
                i++;
            }
            // R[j] is smaller than L[i]
            else {
                array.set(k, R[j]);
                stats[0]++; // m++
                j++;
            }
            k++;
        }

        // copy remains of L
        while (i < n1) {
            array.set(k, L[i]);
            stats[0]++; // m++
            i++;
            k++;
        }

        // copy remains of R
        while (j < n2) {
            array.set(k, R[j]);
            stats[0]++; // m++
            j++;
            k++;
        }

        // print progress
        if (!count) {
            StringBuilder sb = new StringBuilder();
            for (int idx = l; idx < r+1; idx++) {
                sb.append(array.get(idx) + " ");
            }
            System.out.println(sb.toString().trim());
        }

        return stats;
    }

    private static int[] sort(boolean count, boolean desc, int l, int r) {
        int[] stats = {0, 0}, recursionStats;

        // array is larger than 1
        if (l < r) {
            // calculate split point
            int m = l + (r - l) / 2;

            // print progress
            if (!count) {
                StringBuilder sb = new StringBuilder();
                for (int idx = l; idx < r+1; idx++) {
                    sb.append(array.get(idx) + " ");
                    if (idx == m) sb.append("| ");
                }
                System.out.println(sb.toString().trim());
            }

            // sort first split
            recursionStats = sort(count, desc, l, m);
            stats[0] += recursionStats[0];
            stats[1] += recursionStats[1];
            // sort second split
            recursionStats = sort(count, desc, m + 1, r);
            stats[0] += recursionStats[0];
            stats[1] += recursionStats[1];

            // merge splits
            recursionStats = merge(count, desc, l, m, r);
            stats[0] += recursionStats[0];
            stats[1] += recursionStats[1];
        }

        return stats;
    }

    private static void quickSort(boolean count, boolean desc) {

    }

}
// VERSION 3.0