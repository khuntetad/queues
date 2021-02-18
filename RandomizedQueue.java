import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void resize(int c) {
        Item[] temp = (Item[]) new Object[c];

        int k = 0;
        for (int i = 0; i < size; i++) {
            if (items[i] != null) {
                temp[k++] = items[i];
            }
        }
        items = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item != null) {
            if (size == items.length) {
                resize(2 * items.length);
            }
            items[size] = item;
            size++;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            int rand = StdRandom.uniform(size);
            Item retItem = items[rand];
            items[rand] = items[size - 1];
            items[size - 1] = null;

            if (size != 0) {
                size--;
            }
            return retItem;
        }

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            int rand = StdRandom.uniform(size);
            return items[rand];
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        public boolean hasNext() {
            return size > 0;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int rand = StdRandom.uniform(size);
            Item retItem = items[rand];

            if (rand != (size - 1)) {
                items[rand] = items[size - 1];
            }
            items[size - 1] = null;
            size--;
            return retItem;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(3);
        queue.enqueue(6);
        queue.enqueue(9);
        System.out.println("has next: " + queue.iterator().hasNext());
        System.out.println("size: " + queue.size());
        System.out.println("sample: " + queue.sample());
        queue.dequeue();
        for (Integer i : queue) {
            System.out.println(i);
        }


    }

}
