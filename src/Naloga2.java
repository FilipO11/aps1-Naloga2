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
                insert(count, desc, array);
                break;
            case "select":
                select(count, desc, array);
                break;
            case "bubble":
                bubble(count, desc, array);
                break;
            case "heap":

                break;
            case "merge":

                break;
            case "quick":

                break;
            case "radix":

                break;
            case "bucket":

                break;
        }
    }

    private static void insert(boolean count, boolean desc, mySequence array) {
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

    private static void select(boolean count, boolean desc, mySequence array) {
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

    private static void bubble(boolean count, boolean desc, mySequence array) {
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

}
// VERSION 1.1