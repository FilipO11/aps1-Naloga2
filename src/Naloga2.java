@SuppressWarnings("All")
interface Sequence<T> {
    T get(int i);

    void set(int i, T x);

    void add(T x);

    void swap(int i, int j);
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
            if(++size == capacity) resize();
            set(size, x);
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
    }


}
