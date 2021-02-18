import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        if (isEmpty()) {
            first.next = null;
            last = first;
        }
        else {
            oldFirst.prev = first;
            first.next = oldFirst;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldEnd = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            last.prev = null;
            first = last;
        }
        else {
            oldEnd.next = last;
            last.prev = oldEnd;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        Node retItem = new Node();
        retItem.item = first.item;
        first = first.next;
        size--;
        return retItem.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node removeLast = new Node();
        removeLast.item = last.item;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return removeLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (hasNext()) {
                Item retItem = current.item;
                current = current.next;
                return retItem;
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("cat");
        deque.addFirst("giraffe");
        deque.addLast("dog");
        deque.addFirst("leopard");

        deque.removeFirst();
        deque.removeLast();

        System.out.println("The size is: " + deque.size());
        System.out.println("Is the deque empty? " + deque.isEmpty());

        for (String s : deque) {
            System.out.println(s);
        }
    }

}

