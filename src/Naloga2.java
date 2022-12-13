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
        mySequence a = array;
        m = c = i = 0;

        // Unsorted
        while (++i < a.size()){
            tmp = a.get(i);
            m++;
            for(int j=i-1; j >= 0; j--){
                c++;
                // tmp is smaller than a[j]
                if ((tmp < a.get(j)) != desc) {
                    // move a[j] to the right
                    a.set(j+1, a.get(j));
                    m++;
                }
                // tmp is bigger than a[j]
                else {
                    // insert tmp
                    a.set(j+1, tmp);
                    m++;
                    break;
                }
                // tmp is smallest elt
                if (j == 0) {
                    // insert tmp as first elt
                    a.set(0, tmp);
                    m++;
                }
            }
            if (!count) {
                StringBuilder sb = new StringBuilder();
                for (int idx = 0; idx < a.size(); idx++) {
                    sb.append(a.get(idx) + " ");
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
            while (++i < a.size()){
                tmp = a.get(i);
                m++;
                for(int j=i-1; j >= 0; j--){
                    c++;
                    // tmp is smaller than a[j]
                    if ((tmp < a.get(j)) != desc) {
                        // move a[j] to the right
                        a.set(j+1, a.get(j));
                        m++;
                    }
                    // tmp is bigger than a[j]
                    else {
                        // insert tmp
                        a.set(j+1, tmp);
                        m++;
                        break;
                    }
                    // tmp is smallest elt
                    if (j == 0) {
                        // insert tmp as first elt
                        a.set(0, tmp);
                        m++;
                    }
                }
            }
            stats.append(" | " + m + " " + c);

            m = c = i = 0;
            // Sorted in reverse
            desc = !desc;
            while (++i < a.size()){
                tmp = a.get(i);
                m++;
                for(int j=i-1; j >= 0; j--){
                    c++;
                    // tmp is smaller than a[j]
                    if ((tmp < a.get(j)) != desc) {
                        // move a[j] to the right
                        a.set(j+1, a.get(j));
                        m++;
                    }
                    // tmp is bigger than a[j]
                    else {
                        // insert tmp
                        a.set(j+1, tmp);
                        m++;
                        break;
                    }
                    // tmp is smallest elt
                    if (j == 0) {
                        // insert tmp as first elt
                        a.set(0, tmp);
                        m++;
                    }
                }
            }
            stats.append(" | " + m + " " + c);

            System.out.println(stats.toString());
        }
    }

    private static void select(boolean count, boolean desc, mySequence array) {
        int m, c, /*tmp,*/ minIdx;
        StringBuilder stats = new StringBuilder();
        mySequence a = array;
        m = c = 0;

        // Unsorted
        for (int i = 0; i < a.size()-1; i++) {
            // register a[i] as min
            minIdx = i;

            // find min
            for (int j = i + 1; j < a.size(); j++) {
                c++;
                // a[j] is smaller than current min
                if ((a.get(j) < a.get(minIdx)) != desc) {
                    // register a[j] as min
                    minIdx = j;
                }
            }

            // swap a[i] and min
            a.swap(i, minIdx);
            m+=3;

            // print progress
            if (!count) {
                StringBuilder sb = new StringBuilder();
                for (int idx = 0; idx < a.size(); idx++) {
                    sb.append(a.get(idx) + " ");
                    if (idx == i) sb.append("| ");
                }
                System.out.println(sb.toString().trim());
            }
        }

        if (count) {
            stats.append(m + " " + c);
            m = c = 0;

            // Sorted
            for (int i = 0; i < a.size()-1; i++) {
                // register a[i] as min
                minIdx = i;

                // find min
                for (int j = i + 1; j < a.size(); j++) {
                    c++;
                    // a[j] is smaller than current min
                    if ((a.get(j) < a.get(minIdx)) != desc) {
                        // register a[j] as min
                        minIdx = j;
                    }
                }

                // swap a[i] and min
                a.swap(i, minIdx);
                m+=3;
            }
            stats.append(" | " + m + " " + c);
            m = c = 0;

            // Sorted in reverse
            desc = !desc;
            for (int i = 0; i < a.size()-1; i++) {
                // register a[i] as min
                minIdx = i;

                // find min
                for (int j = i + 1; j < a.size(); j++) {
                    c++;
                    // a[j] is smaller than current min
                    if ((a.get(j) < a.get(minIdx)) != desc) {
                        // register a[j] as min
                        minIdx = j;
                    }
                }

                // swap a[i] and min
                a.swap(i, minIdx);
                m+=3;
            }
            stats.append(" | " + m + " " + c);

            System.out.println(stats.toString());
        }
    }

}
