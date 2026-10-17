
import java.util.*;

public class Exercise24_3 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // creating a doube linked list
        TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();

        System.out.print("Enter five numbers: ");

        // adding five numbers to the list
        for (int i = 0; i < 5; i++) {
            list.add(input.nextDouble());
        }

        // replacing the third number
        list.set(2, 10.55);

        // displaying the list from first to last
        ListIterator<Double> iterator = list.listIterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // displaying the list from last to first
        iterator = list.listIterator(list.size());

        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + " ");
        }
        System.out.println();

        input.close();
    }
}

interface MyList<E> extends List<E> {
}

class TwoWayLinkedList<E> extends AbstractSequentialList<E>
        implements MyList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    // this node stores an element and links to both
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e) {
            element = e;
        }
    }

    // creating an empty list
    public TwoWayLinkedList() {
    }

    // creating a list from an array
    public TwoWayLinkedList(E[] elements) {
        for (int i = 0; i < elements.length; i++) {
            addLast(elements[i]);
        }
    }

    // returning the first element
    public E getFirst() {
        if (size == 0)
            return null;

        return head.element;
    }

    // returning the last element
    public E getLast() {
        if (size == 0)
            return null;

        return tail.element;
    }

    // adding an element to the beginning
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);

        newNode.next = head;

        if (head == null) {
            head = tail = newNode;
        }
        else {
            head.previous = newNode;
            head = newNode;
        }

        size++;
        modCount++;
    }

    // adding an element to the end
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);

        if (tail == null) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }

        size++;
        modCount++;
    }

    // adding an element at the specified index
    @Override
    public void add(int index, E e) {
        listIterator(index).add(e);
    }

    // returning the element at the specified index
    @Override
    public E get(int index) {
        return getNode(index).element;
    }

    // replacing an element and return the old element
    @Override
    public E set(int index, E e) {
        Node<E> current = getNode(index);

        E oldElement = current.element;
        current.element = e;

        return oldElement;
    }

    // returning the number of elements
    @Override
    public int size() {
        return size;
    }

    // removing and return the first element
    public E removeFirst() {
        if (size == 0)
            return null;

        return removeNode(head);
    }

    // removing and return the last element
    public E removeLast() {
        if (size == 0)
            return null;

        return removeNode(tail);
    }

    // removing an element at the specified index
    @Override
    public E remove(int index) {
        return removeNode(getNode(index));
    }

    // removing a node and update both links
    private E removeNode(Node<E> current) {
        E oldElement = current.element;

        if (current.previous == null) {
            head = current.next;
        }
        else {
            current.previous.next = current.next;
        }

        if (current.next == null) {
            tail = current.previous;
        }
        else {
            current.next.previous = current.previous;
        }

        size--;
        modCount++;

        return oldElement;
    }

    // finding the node at a specified index
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<E> current;

        // starting from the closest end of the list
        if (index < size / 2) {
            current = head;

            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        else {
            current = tail;

            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }

        return current;
    }

    // clearing all elements
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
        modCount++;
    }

    // returning an iterator starting at the head
    @Override
    public ListIterator<E> listIterator() {
        return new TwoWayIterator(0);
    }

    // returning an iterator starting at an index
    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        return new TwoWayIterator(index);
    }

    private class TwoWayIterator implements ListIterator<E> {

        private Node<E> nextNode;
        private Node<E> lastReturned;
        private int nextIndex;
        private int expectedModCount;

        public TwoWayIterator(int index) {
            nextIndex = index;
            expectedModCount = modCount;

            if (index == size)
                nextNode = null;
            else
                nextNode = getNode(index);
        }

        // checking whether the list has changed
        private void checkForChanges() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
        }

        // checking if there is a next element
        public boolean hasNext() {
            return nextIndex < size;
        }

        // returning the next element
        public E next() {
            checkForChanges();

            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = nextNode;
            nextNode = nextNode.next;
            nextIndex++;

            return lastReturned.element;
        }

        // checking if there is a previous element
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        // returning the previous element
        public E previous() {
            checkForChanges();

            if (!hasPrevious())
                throw new NoSuchElementException();

            if (nextNode == null)
                nextNode = tail;
            else
                nextNode = nextNode.previous;

            lastReturned = nextNode;
            nextIndex--;

            return lastReturned.element;
        }

        // returning the next index
        public int nextIndex() {
            return nextIndex;
        }

        // returning the previous index
        public int previousIndex() {
            return nextIndex - 1;
        }

        // removing the last returned element
        public void remove() {
            checkForChanges();

            if (lastReturned == null)
                throw new IllegalStateException();

            if (lastReturned == nextNode) {
                nextNode = nextNode.next;
            }
            else {
                nextIndex--;
            }

            removeNode(lastReturned);
            lastReturned = null;
            expectedModCount = modCount;
        }

        // replacing the last returned element
        public void set(E e) {
            checkForChanges();

            if (lastReturned == null)
                throw new IllegalStateException();

            lastReturned.element = e;
        }

        // adding an element at the cursor position
        public void add(E e) {
            checkForChanges();

            Node<E> newNode = new Node<>(e);

            if (nextNode == head) {
                newNode.next = head;

                if (head != null)
                    head.previous = newNode;
                else
                    tail = newNode;

                head = newNode;
            }
            else if (nextNode == null) {
                newNode.previous = tail;

                if (tail != null)
                    tail.next = newNode;
                else
                    head = newNode;

                tail = newNode;
            }
            else {
                newNode.previous = nextNode.previous;
                newNode.next = nextNode;

                nextNode.previous.next = newNode;
                nextNode.previous = newNode;
            }

            size++;
            modCount++;
            nextIndex++;
            lastReturned = null;
            expectedModCount = modCount;
        }

    }






    
}
