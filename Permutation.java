import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        int count = Integer.valueOf(args[0]);

        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }

        for (int i = 1; i <= count; i++) {
            if (!randomizedQueue.isEmpty()) {
                System.out.println(randomizedQueue.dequeue());
            }
        }
    }
}
